package com.carpool.website.dao;

import com.carpool.domain.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Created by deado on 2016/12/18.
 */

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, String> {

    SessionEntity findBySeriesId(String id);

    SessionEntity findByUserId(String id);

    @Modifying
    @Transactional
    @Query("delete from SessionEntity session where session.userId=?1")
    void deleteSessionBySeriseId(String userid);

    @Modifying
    @Transactional
    @Query("update SessionEntity  session set session.token=?1 , session.last_used=?2 where session.seriesId=?3")
    void updateTokenBySeriseId(String newToken, Timestamp newTimestamp, String Id);

}
