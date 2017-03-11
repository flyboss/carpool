package com.carpool.website.dao;

import com.carpool.domain.ChatRecordEntity;

import com.carpool.domain.RoomEntity;
import com.carpool.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by deado on 2016/11/30.
 */

public interface ChatRecordRepository extends JpaRepository<ChatRecordEntity,Integer>{

    ChatRecordEntity findById(int id);
    Page<ChatRecordEntity> findByCommenttime(Date commentime, Pageable pageable);

    @Query("select comment.commenttext from ChatRecordEntity comment where comment.id=?1")
    String getChatTextById(int id);

    //通过评论内容找到评论
    //@Query("select comment from ChatRecordEntity  comment where comment.commenttext like \"%\" + ?1 + \"%\" ")
    //Page<ChatRecordEntity> getChatByText(String text, Pageable pageable);


    @Query("select comment.room.id from ChatRecordEntity comment where comment.id=?1")
    int getRoomById(int id);

    @Query("select comment.sender.id from ChatRecordEntity comment where comment.id=?1")
    String getSenderById(int id);

    @Query("select comment from ChatRecordEntity comment where  comment.room.id=?1")
    List<ChatRecordEntity> getAllChatOfRoom(int id);

    @Query("select comment from ChatRecordEntity  comment where comment.sender.id=?1")
    Page<ChatRecordEntity> getAllChatOfUser(String id, Pageable pageable);

    @Modifying
    @Query("delete from ChatRecordEntity comment where comment.id=?1")
    int deleteChatRecordById(int id);

    @Modifying
    @Query("delete from ChatRecordEntity comment where comment.room.id=?1")
    int deleteChatRecordByRoom(int id);

    @Modifying
    @Query("delete from ChatRecordEntity comment where comment.sender.id=?1")
    int deleteChatRecordBySenderId(String id);

}
