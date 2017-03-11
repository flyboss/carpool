package com.carpool.repository;


import com.carpool.domain.CommentEntity;
import com.carpool.domain.JourneyEntity;
import com.carpool.domain.UserEntity;
import com.carpool.website.dao.CommentEntityRepository;
import com.carpool.website.dao.JourneyEntityRepository;
import com.carpool.website.dao.UserEntityRepository;
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
public class CommentRepositoryTest {
    @Autowired
    private JourneyEntityRepository journeyEntityRepository;

    @Autowired
    private CommentEntityRepository commentEntityRepository;
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Test
    public void testSaveComment()
    {
        UserEntity sourceUser  = userEntityRepository.findOne("1452779");
        JourneyEntity journeyEntity =  journeyEntityRepository.findOne(3);
        UserEntity targetEntity = userEntityRepository.findOne("1452778");
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCredit(5);
        commentEntity.setJourney(journeyEntity);
        commentEntity.setSourceUser(sourceUser);
        commentEntity.setTargetUser(targetEntity);
        commentEntity.setCommentText("dwe");
        commentEntityRepository.save(commentEntity);
        Assert.assertNotNull(commentEntityRepository.findOne(1));
    }

    @Test
    public void testCreaditsQuery()
    {
        Assert.assertEquals(0,commentEntityRepository.countIdBySourceUserId("1452779"));

        Assert.assertEquals(2,commentEntityRepository.countIdByTargetUserId("1452779"));

        Assert.assertEquals(new Double(10),commentEntityRepository.sumOfCreditByUserId("1452779"));

        Assert.assertTrue(commentEntityRepository.sumOfCreditByUserId("1452778")==null);
    }


    @Test
    public void testPageComment()
    {
        Sort sort = new Sort(Sort.Direction.DESC, "commentTime");
        int page = 0; int size = 1;
        Pageable pageable = new PageRequest(page, size, sort);
        Page<CommentEntity> commentEntities = commentEntityRepository.findBySourceUserId("1452778",pageable);
        Assert.assertEquals(1,commentEntities.getContent().size());

        commentEntities = commentEntityRepository.findByTargetUserId("1452779",pageable);
        Assert.assertEquals(1,commentEntities.getContent().size());
    }

    @Test
    public void testCommentsByJouery()
    {
        List<CommentEntity>commentEntities
                = commentEntityRepository.findByJourneyId(2);
        Assert.assertEquals(2,commentEntities.size());
    }

    @Test
    public  void  testCountNumsOfCommentToOne()
    {
        int nums = commentEntityRepository.getNumsOfCommentOneToAnotherInJour("" +
                "1452778","1452779",1);
        Assert.assertEquals(1,nums);
    }


}
