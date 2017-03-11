package com.carpool.website.dao;

import com.carpool.domain.UserUnreceivedChatRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by deado on 2016/12/20.
 */

@Repository
public interface UserUnreceivedRecordRepository extends JpaRepository<UserUnreceivedChatRecord, Long> {

    List<UserUnreceivedChatRecord> findByUserId(String userid);

    @Transactional
    @Modifying
    @Query("delete from UserUnreceivedChatRecord  uuc where uuc.userId = ?1")
    void deleteByUserId(String userid);
}
