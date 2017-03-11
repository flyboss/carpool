package com.carpool.website.dao;

import com.carpool.domain.RoomEntity;
import com.carpool.domain.RoomState;
import com.carpool.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Novemser on 2016/11/27.
 */

public interface RoomEntityRepository extends JpaRepository<RoomEntity, Integer> {


    RoomEntity  findById(int id);

    Page<RoomEntity> findByStartPointAndEndPointAndStartTimeBetween(String startPoint, String endPoint,
                                                             Date from, Date to, Pageable pageable);

    @Query("select r from RoomEntity r where " +
            "r.startPoint like %:qStartPoint% " +
            "and r.endPoint like %:qEndPoint% " +
            "and r.startTime between :qStartTime and :qEndTime")
    Page<RoomEntity> findRoomStartEndPointLikeInDays(@Param("qStartPoint") String startPoint,
                                                     @Param("qEndPoint") String endPoint,
                                                     @Param("qStartTime") Date from,
                                                     @Param("qEndTime") Date to,
                                                     Pageable pageable);

    @Query("select r from RoomEntity r where " +
            "r.startPoint like %:qStartPoint% " +
            "and r.endPoint like %:qEndPoint% ")
    Page<RoomEntity> findRoomStartEndPointLike(@Param("qStartPoint") String startPoint,
                                                     @Param("qEndPoint") String endPoint,
                                                     Pageable pageable);

    RoomEntity findByRoomname(String name);

    // 根据User找到何其在同一房间的用户
    @Query(value = "select r from RoomEntity r " +
            "where r.state <> 'END'")
    Collection<RoomEntity> findUserNotEndRooms();

    RoomEntity findByHost(UserEntity host);

    @Modifying
    @Query("update RoomEntity room set " +
            "room.state=:qState " +
            "where room.id=:qId")
    void updateState(@Param("qState") RoomState state,
                     @Param("qId") int id);

    @Query("select count(*) from RoomEntity")
    Integer getRoomCount();
}
