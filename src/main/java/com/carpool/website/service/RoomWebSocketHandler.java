package com.carpool.website.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.carpool.domain.ChatRecordEntity;
import com.carpool.domain.RoomEntity;
import com.carpool.domain.UserEntity;
import com.carpool.domain.UserUnreceivedChatRecord;
import com.carpool.website.dao.ChatRecordRepository;
import com.carpool.website.dao.RoomEntityRepository;
import com.carpool.website.dao.UserEntityRepository;
import com.carpool.website.dao.UserUnreceivedRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

/**
 * Created by deado on 2016/12/20.
 */

@Service
public class RoomWebSocketHandler extends TextWebSocketHandler {

    private String[] month = {"January", "February", "March", "April",  "May", "June", "July", "August", "September", "October" ,"November", "December"};

    @Autowired
    private RoomEntityRepository roomEntityRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private UserUnreceivedRecordRepository userUnreceivedRecordRepository;

    @Autowired
    private ChatRecordRepository chatRecordRepository;

    @Autowired
    private UserService userService;


    // {roomid: [userid1, userid2, ......]}
    private static Map<String, ArrayList<WebSocketSession>>  roomDomain = new HashMap<String, ArrayList<WebSocketSession>>();


    @Autowired
    private WebSocketService webSocketService;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message){
        String messageContent = message.getPayload().toString();
        JSONObject messageJson = JSON.parseObject(messageContent);

        Integer type = messageJson.getInteger("type");
        switch(type){
            case(1): this.handleMessageTypeOne(messageJson);break;
            case(2): this.handleMessageTypeTwo(session, messageJson);break;
            case(3): this.handleMessageTypeThree(session,messageJson);break;
        }

    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        for(Map.Entry<String, ArrayList<WebSocketSession>> entry: roomDomain.entrySet()){
            if(entry.getValue().contains(session)){
                roomDomain.get(entry.getKey()).remove(session);
                break;
            }
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }



    public void handleMessageTypeOne(JSONObject messageJson){
        //retrieve info from message
        String userId = messageJson.getString("userid");
        String room = messageJson.getString("room");
        String content = messageJson.getString("chatContent");
        messageJson.put("src", userService.getUserProfileImgSrc(userId));

        ArrayList<WebSocketSession> roomSpace = roomDomain.get(room);

        //save message
        ChatRecordEntity cre = new ChatRecordEntity(new Date(),content,
                this.userEntityRepository.findById(userId),
                this.roomEntityRepository.findOne(Integer.valueOf(room)));
        this.chatRecordRepository.save(cre);

        //send
        this.sendMessageToUsers(cre, roomSpace, messageJson);


    }

    @Transactional
    private void sendMessageToUsers(ChatRecordEntity cre, ArrayList<WebSocketSession> roomSpace, JSONObject msg) {
        Integer monthIndex = Integer.valueOf(msg.getString("month"));

        msg.replace("month", msg.get("month"), this.month[monthIndex-1]);

        //generate message
        String messageStr = msg.toJSONString();
        TextMessage message = new TextMessage(messageStr);
        Collection<UserEntity> roomPatis = this.roomEntityRepository.findById(msg.getInteger("room")).getUserParticipate();

        if(null != roomSpace){
            //send to users
            for(WebSocketSession session: roomSpace){
                UserEntity target = this.userEntityRepository.findById(session.getPrincipal().getName());
                try{
                    if(session.isOpen()){
                        session.sendMessage(message);
                        if(roomPatis.contains(target)){
                            roomPatis.remove(target);
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

            }



        }

        //save the unsent message in database
        for(UserEntity remainUsers: roomPatis){
            UserUnreceivedChatRecord uUC = new UserUnreceivedChatRecord();
            uUC.setChatRecordEntity(cre);
            uUC.setUserId(remainUsers.getId());
            this.userUnreceivedRecordRepository.save(uUC);
        }

    }

    private void handleMessageTypeTwo(WebSocketSession session, JSONObject messageJson){
    }

    @SuppressWarnings("deprecation")
    private void handleMessageTypeThree(WebSocketSession session, JSONObject messageJson){
        Integer room = messageJson.getInteger("roomId");
        List<ChatRecordEntity> chatList = this.chatRecordRepository.getAllChatOfRoom(room);

        //add this room to roomDomain
        if(!roomDomain.containsKey(room.toString())){
            ArrayList<WebSocketSession> value = new ArrayList<WebSocketSession>();
            roomDomain.put(room.toString(), value);

            RoomEntity roomEntity = this.roomEntityRepository.findById(room);

//            Collection<UserEntity> roomMemberColl = roomEntity.getUserParticipate();
//            ArrayList<String> roomMemberList = new ArrayList<String>();
//            for(UserEntity userEntity: roomMemberColl){
//                roomMemberList.add(userEntity.getId());
//            }
//            roomMembers.put(room.toString(), roomMemberList);
        }
        //add this session to roomSpace
        ArrayList<WebSocketSession> roomSpace = roomDomain.get(room.toString());
        if(!roomSpace.contains(session)){
            roomSpace.add(session);
        }

        //send back the chat history
        for(ChatRecordEntity cre : chatList){
            if(0 == cre.getSender().getId().compareTo("0000001")){
                continue;
            }
            String year = Integer.toString(cre.getCommenttime().getYear());
            String month = Integer.toString(cre.getCommenttime().getMonth());
            String day = Integer.toString(cre.getCommenttime().getDay());
            String hour = Integer.toString(cre.getCommenttime().getHours());
            String minute = Integer.toString(cre.getCommenttime().getMinutes());
            String userProfileImg = userService.getUserProfileImgSrc(cre.getSender().getId());

            String msg = "{ \"type\":1, \"userid\":\""+cre.getSender().getId() + "\", \"username\":\"" + cre.getSender().getUsername()
                    + "\", \"room\":" + room + ", \"year\":"+year + ", \"month\":"+ month + ",\"day\":" +day+ ",\"hour\":"
                    + hour + ",\"minute\":" + minute + ",\"chatContent\":\"" + cre.getCommenttext() + "\"," +
                    "\"src\":\"" + userProfileImg.replace("_","") + "\"}";
            try{
                session.sendMessage(new TextMessage(msg));
            }catch(Exception e){

            }
        }
    }


}
