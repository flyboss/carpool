package com.carpool.repository;

import com.carpool.domain.JourneyEntity;
import com.carpool.domain.RoomEntity;
import com.carpool.domain.UserEntity;
import com.carpool.website.dao.JourneyEntityRepository;
import com.carpool.website.dao.RoomEntityRepository;
import com.carpool.website.dao.UserEntityRepository;
import com.carpool.website.model.MyTrack;
import com.carpool.website.service.JourneyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by qi on 2016/11/26.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class JourneyRepositoryTest {

    @Autowired
    JourneyEntityRepository journeyEntityRepository;
    @Autowired
    RoomEntityRepository roomEntityRepository;
    @Autowired
    UserEntityRepository userEntityRepository;
    @Autowired
    JourneyService journeyService;

    @Test
    public void testSave()
    {

    }

    //测试保存Journey
    @Test
    public void testSaveJourney()
    {
        //通过房间即可保存一个行程
        RoomEntity roomEntity = roomEntityRepository.findOne(10);
        Assert.assertTrue(roomEntity.getHost().getId().equals("1452779"));
        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setRoom(roomEntity);
        journeyEntity.setStartPoint(roomEntity.getStartPoint());
        journeyEntity.setEndPoint(roomEntity.getEndPoint());
        journeyEntity.setPeerNums(roomEntity.getCurrentNums());
        journeyEntity.setStartTime(roomEntity.getStartTime());
        journeyEntityRepository.saveAndFlush(journeyEntity);
/*
        JourneyEntity journeyEntity1 = journeyEntityRepository.findByRoomId(roomEntity.getId());
        Assert.assertTrue(journeyEntity1.getEndPoint().equals("虹桥火车站"));

        JourneyEntity journeyEntity1 ;
        journeyEntity1 = journeyEntityRepository.findOne(2);
        Assert.assertNull(journeyEntity1);

        RoomEntity roomEntity = roomEntityRepository.findOne(2);
        Assert.assertTrue(roomEntity.getHost().getId().equals("1452779"));
        Assert.assertTrue(roomEntity.getHost().getId().equals("1452779"));
        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setRoom(roomEntity);
        journeyEntity.setStartPoint(roomEntity.getStartPoint());
        journeyEntity.setEndPoint(roomEntity.getEndPoint());
        journeyEntity.setPeerNums(roomEntity.getCurrentNums());
        journeyEntity.setStartTime(roomEntity.getStartTime());
        journeyEntityRepository.saveAndFlush(journeyEntity);

        JourneyEntity journeyEntity2 = journeyEntityRepository.findOne(2);
        Assert.assertTrue(journeyEntity2.getPeerNums()==2);
    */
    }

    @Test()
    public  void testQuery()
    {
        //测试 查询自己是房主的行程  这个函数
        Assert.assertTrue(journeyEntityRepository.countIdByRoomHostId("1452779")==2);
        Assert.assertTrue(journeyEntityRepository.countIdByRoomHostId("145277")==0);

        //测试 查询自己不是房主而参与的行程 这函数
        Assert.assertTrue(journeyEntityRepository.getTotalParticipateNumsByUserId("1452779")==0);
        Assert.assertTrue(journeyEntityRepository.getTotalParticipateNumsByUserId("1452778")==1);
    }

    //测试分页查询
    @Test
    public void testPageQuery()
    {
        Sort sort = new Sort(Sort.Direction.DESC, "startTime");
        int page = 0; int size = 10;
        Pageable pageable = new PageRequest(page, size, sort);
        Page<JourneyEntity> pages = journeyEntityRepository.findByRoomHostId("1452779",pageable);
        Assert.assertTrue(pages.getTotalElements()==2);
        Assert.assertEquals(pages.getContent().get(0).getStartTime().toString(),"123");
        pageable = new PageRequest(page,size,sort);
        pages = journeyEntityRepository.findByRoomHostId("1452779",pageable);
        Assert.assertEquals(pages.getContent().get(1).getStartTime().toString(),"123");
    }

    //测试 找到某个用户的参与的出行 函数
    @Test
    public  void testQuery2()
    {
        Sort sort = new Sort(Sort.Direction.DESC, "startTime");
        int page = 0; int size = 10;
        Pageable pageable = new PageRequest(page, size, sort);
        Page<JourneyEntity> pages = journeyEntityRepository.findByParticipateId("1452779",pageable);
        Assert.assertTrue(pages.getTotalElements()==0);
        pages = journeyEntityRepository.findByParticipateId("1452778",pageable);
        Assert.assertEquals(pages.getTotalElements(),1);
    }

    //测试返回某个用户出行数的查询
    @Test
    public  void queryAllJounery()
    {
        Sort sort = new Sort(Sort.Direction.DESC, "startTime");
        int page = 0; int size = 10;
        Pageable pageable = new PageRequest(page, size, sort);
        Page<JourneyEntity> pages = journeyEntityRepository.getAllJourneyByByUSerId("1452779",pageable);
        Assert.assertEquals(pages.getTotalElements(),2);
        Assert.assertEquals(journeyEntityRepository.getJourneyNums("1452778"),1);
    }

    @Test
    public void testJourneyByCommnets()
    {
        JourneyEntity journeyEntity
                = journeyEntityRepository.findByCommentId(4);
        Assert.assertEquals("同济大学",journeyEntity.getEndPoint());

    }

    @Test
    public void test()
    {
        UserEntity userEntity = userEntityRepository.findOne("1452779");
        Assert.assertEquals(userEntity.getOwnRoom().size(),2);
    }

    @Test
    public void test2()
    {
        Integer nums = journeyEntityRepository.getJourneyNums("1324");
    }


    @Test
    public void test_myPreferDestination()
    {
        List<Object[]>myprefers = journeyEntityRepository.getMypreferDestination("1452779");
        for (Object[] o:myprefers) {
            String des = (String) o[0];
            Long times = (Long)o[1];
            System.out.println(des + "   :" + times+"\n");
        }
    }

    @Test
    public void test3()
    {
        List<MyTrack> myTracks = journeyService.getMyTrack("1452779");
        Assert.assertEquals(2,myTracks.size());
    }

    @Test
    public void  test_getTimesWithOthers()
    {
  //              journeyEntityRepository.getTimeOfJourneyWithothers("1452779");
     //   journeyEntityRepository.test();
    }
}

