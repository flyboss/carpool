package com.carpool.website.service;

import com.carpool.domain.RoomEntity;
import com.carpool.domain.UserEntity;
import com.carpool.website.dao.UserEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * Project: Carpool
 * Package: com.carpool.website.service
 * Author:  Novemser
 * 2016/11/29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@WebAppConfiguration
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Test
    public void createUser2() throws Exception {
        UserEntity user = new UserEntity();
        user.setId("1452683");
        user.setUsername("WSM");
        user.setPassword("123");
        user.setCoins(123);
        user.setAlipay("444");
        user.setCredit(412);
        user.setGender((byte) 1);
        user.setQqAccount("1343124");
        user.setWechatAccount("555");
        userEntityRepository.save(user);
    }

    @Test
    public void createUser1() throws Exception {
        UserEntity user = new UserEntity();
        user.setId("1452681");
        user.setUsername("HGS");
        user.setPassword("123");
        user.setCoins(100);
        user.setAlipay("14564564897489");
        user.setCredit(100.0);
        user.setGender((byte) 0);
        user.setQqAccount("504589731");
        user.setWechatAccount("2072806652");
        userEntityRepository.save(user);
    }

    @Test
    public void createRoom() throws Exception {

        roomService.createRoom(
                "宝宝要回家",
                "嘉定校区",
                "虹桥机场",
                3,
                new Date(),
                "1452681",
                "来吧来吧"
        );
    }

    @Test
    public void listRooms() throws Exception {

        Page<RoomEntity> roomEntities =
                roomService.listRoomsInDays(
                        "嘉定校区",
                        "虹桥机场",
                        new Date(),
                        1,
                        0,
                        12
                );
        int cnt = roomEntities.getNumberOfElements();
        System.out.println("roomEntities num:" + cnt);
    }

    @Test
    public void addUserToRoom() throws Exception {
        roomService.addUserToRoom(3, "1452683");
    }

    @Test
    public void listUserRooms() throws Exception {

    }

    @Test
    public void listAllRooms() throws Exception {

    }

    @Test
    public void addUser() throws Exception {

    }

    @Test
    public void lockRoomById() throws Exception {

    }

}