package com.carpool.website.service;

import com.carpool.website.dao.ChatRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by deado on 2016/12/1.
 */

@Service
@Transactional
public class ChatRecordService {
    @Autowired
    private ChatRecordRepository chatRecordRepository;

    public boolean deleteChatRecordById(int id) throws Exception{
        try{

            int res =this.chatRecordRepository.deleteChatRecordById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
