package com.carpool.website.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Project: Carpool
 * Package: com.carpool.website.dao
 * Author:  Novemser
 * 2016/12/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@WebAppConfiguration
public class RoomEntityRepositoryTest {

    @Autowired
    private RoomEntityRepository roomEntityRepository;

    @Test
    public void findRoomStartEndPointLike() throws Exception {
//        Page<RoomEntity> entities = roomEntityRepository.findRoomStartEndPointLikeInDays("嘉定", "虹桥", new PageRequest(0, 10));
//        System.out.println(entities.getSize());
//        List<RoomEntity> entityList = entities.getContent();
//        for (RoomEntity entity : entities) {
//            System.out.println(entity.getStartPoint() + "->" + entity.getEndPoint());
//        }
    }

}