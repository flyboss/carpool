package com.carpool.website.service;

import com.carpool.domain.ChatRecordEntity;
import com.carpool.domain.UserUnreceivedChatRecord;
import com.carpool.website.dao.ChatRecordRepository;
import com.carpool.website.dao.UserUnreceivedRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by deado on 2016/12/20.
 */

@Service
public class WebSocketService {

    @Autowired
    private UserUnreceivedRecordRepository userUnreceivedRecordRepository;

    @Autowired
    private ChatRecordRepository chatRecordRepository;

    public List<ChatRecordEntity> getUnreadMessage(String userid){
        List<ChatRecordEntity> ret = new ArrayList<ChatRecordEntity>();
        List<UserUnreceivedChatRecord> unreadRecord = this.userUnreceivedRecordRepository.findByUserId(userid);
        Iterator<UserUnreceivedChatRecord>  itr = unreadRecord.iterator();
        while(itr.hasNext()){
            ChatRecordEntity chatRecord = itr.next().getChatRecordEntity();
            ret.add(chatRecord);
        }

        return ret;
    }

    public void deleteUnreadMessage(String userid){
        this.userUnreceivedRecordRepository.deleteByUserId(userid);
    }
}
