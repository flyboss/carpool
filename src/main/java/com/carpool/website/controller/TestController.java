package com.carpool.website.controller;

import com.carpool.domain.UserEntity;
import com.carpool.website.service.ChatRecordService;
import com.carpool.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qi on 2016/11/26.
 */
@RestController
@RequestMapping("/Test")
public class TestController {

//    @Autowired
//    UserEntityRepository repository;
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public String home()
//    {
//        if(repository.findAll().size()==1)
//            return  "index";
//        else
//            throw  new RuntimeException();
//    }
    @Autowired
    ChatRecordService chatRecordService;
    @Autowired
    UserService userService;


    @RequestMapping("/deleteChatRecord")
    String deleteChatRecordTest(){
        try{
            this.chatRecordService.deleteChatRecordById(10);
            return "True";
        }catch(Exception e){
            return "False";
        }
    }

    @RequestMapping("/insert")
    boolean insertData(){
        UserEntity userEntity = new UserEntity("1452716","张尹嘉","852147", (byte)2014,5.0,"13301856183",12,"123456","123456","");
        userEntity.setReceivedComments(0);
        try{
            this.userService.saveUser(userEntity);
        }catch(Exception e){

        }

        return true;
    }

}
