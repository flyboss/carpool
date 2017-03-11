package com.carpool.website.service;

import com.carpool.domain.CommentEntity;
import com.carpool.exception.CommentException;
import com.carpool.exception.UserNullException;
import com.carpool.website.dao.CommentEntityRepository;
import com.carpool.website.dao.UserEntityRepository;
import com.carpool.website.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by qi on 2016/11/30.
 */

@Service
@ComponentScan
public class CommentService extends PageSevice{
    @Autowired
    private CommentEntityRepository commentEntityRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    public Page<CommentEntity> getRecievedComment(String userid, int currentPage)
    {
        if(userEntityRepository.findOne(userid)==null)
            throw  new UserNullException("getCommentError","不存在的用户");
        setCurrentPage(currentPage);
        Pageable pageable = buidPage(Sort.Direction.DESC,"commentTime");
        return  commentEntityRepository.findByTargetUserId(userid,pageable);
    }

    public Page<CommentEntity> getSendedComment(String userid, int currentPage)
    {
        if(userEntityRepository.findOne(userid)==null)
            throw  new UserNullException("getCommentError","不存在的用户");
        setCurrentPage(currentPage);
        Pageable pageable = buidPage(Sort.Direction.DESC,"commentTime");
        return  commentEntityRepository.findBySourceUserId(userid,pageable);
    }


    public boolean addComment(Comment comment)
    {
        checkCommentUser(comment.getSourceUser().getId(),comment.getTargetUser().getId());
        if(commentEntityRepository.getNumsOfCommentOneToAnotherInJour(comment.getSourceUser().getId(),
                comment.getTargetUser().getId(),comment.getJourney().getId())>0)
            throw new CommentException("makeComment","用户评论重复");
        CommentEntity commentEntity = createCommentEntityFromComment(comment);
        commentEntityRepository.saveAndFlush(commentEntity);
        return  true;
    }

    public void checkCommentUser(String sourceuserid,String targetid)
    {
        if(sourceuserid==null||userEntityRepository.findById(sourceuserid)==null)
            throw new CommentException("check comment User","评论的源用户无效");
        if(targetid==null||userEntityRepository.findById(targetid)==null)
            throw new CommentException("check comment User","评论的目标用户无效");
    }

    private Comment createCommentFromEntity(CommentEntity commentEntity)
    {
        Comment comment = new Comment();
        comment.setCommentTime(new Date());
        comment.setTargetUser(commentEntity.getTargetUser());
        comment.setSourceUser(commentEntity.getSourceUser());
        comment.setCommentText(commentEntity.getCommentText()==null?"":commentEntity.getCommentText());
        comment.setCredit(commentEntity.getCredit());
        comment.setJourney(commentEntity.getJourney());
        return  comment;
    }

    private CommentEntity createCommentEntityFromComment(Comment comment)
    {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentTime(new Date());
        commentEntity.setTargetUser(comment.getTargetUser());
        commentEntity.setSourceUser(comment.getSourceUser());
        commentEntity.setCommentText(comment.getCommentText()==null?"":comment.getCommentText());
        commentEntity.setCredit(comment.getCredit());
        commentEntity.setJourney(comment.getJourney());
        return  commentEntity;
    }


  //  public Double getAvgCredits(CommentEntity )




}
