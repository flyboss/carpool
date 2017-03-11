package com.carpool.website.service;

import com.carpool.domain.UserEntity;
import com.carpool.domain.UserUnreceivedChatRecord;
import com.carpool.website.dao.RoomEntityRepository;
import com.carpool.website.dao.UserUnreceivedRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.text.DateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deado on 2016/12/25.
 */
@Service
public class UnreadWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private UserUnreceivedRecordRepository userUnreceivedRecordRepository;

    @Autowired
    private RoomEntityRepository roomEntityRepository;

    @Autowired
    private UserService userService;

    private static Map<String, WebSocketSession> userSession = new HashMap<>();


    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message){

        this.pushMsgToOneUser(session);
    }

    public void pushMsgToOnlineRoomMembers(Integer roomId){
        Collection<UserEntity> userList = this.roomEntityRepository.findById(roomId).getUserParticipate();
        for(UserEntity ue : userList){
            if(userSession.containsKey(ue.getId())){
                this.pushMsgToOneUser(userSession.get(ue.getId()));
            }
        }
    }

    private void pushMsgToOneUser(WebSocketSession session){
        try{
            String userId = session.getPrincipal().getName();
            String userProfileImg = userService.getUserProfileImgSrc(userId);
            List<UserUnreceivedChatRecord> uucList = this.userUnreceivedRecordRepository.findByUserId(userId);

            //加入到static的session map里
            if(userSession.containsKey(userId)){
                userSession.replace(userId,session);
            }else{
                userSession.put(userId, session);
            }

            this.userUnreceivedRecordRepository.deleteByUserId(userId);
            if(0 == uucList.size()){
                return;
            }

            for(UserUnreceivedChatRecord uuc:uucList){

                StringBuilder msg = new StringBuilder("{ \"count\":");
                msg.append(uucList.size() + ",");
                String content = uuc.getChatRecordEntity().getCommenttext();
                msg.append("\"content\":" + "\"" + content + "\",");
                String sender = uuc.getChatRecordEntity().getSender().getUsername();
                msg.append("\"sender\":" + "\"" + sender + "\",");
                String roomId = Integer.toString(uuc.getChatRecordEntity().getRoom().getId());
                msg.append("\"roomId\":" + "\"" + roomId + "\",");
                String time = DateFormat.getDateTimeInstance().format(uuc.getChatRecordEntity().getCommenttime());
                msg.append("\"src\":\"").append(userProfileImg.replace("_","")).append("\",");
                msg.append("\"time\":" + "\"" + time + "\"}");
                session.sendMessage(new TextMessage(msg.toString()));
            }

        }catch(Exception e){
            e.printStackTrace();
            try{
                session.sendMessage(new TextMessage("[]"));
            }catch(Exception e2){
                //ignore
            }

        }
    }

}
