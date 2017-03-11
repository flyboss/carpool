package com.carpool.website.dao;

import com.carpool.domain.JourneyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by qi on 2016/11/26.
 */
public interface JourneyEntityRepository extends JpaRepository<JourneyEntity, Integer> {

    //得到自己发起的(是房主)的出行数
    Integer countIdByRoomHostId(String id);

    //得到自己参与的行程（非房主）数

 //   @Query("select  count (j) from JourneyEntity j where exists (select userParticipate.id from j.room.userParticipate userParticipate)" +
  //          "and :id in (select userParticipate.participateUser.id from j.room.userParticipate userParticipate)")
    @Query("select  count (j) from JourneyEntity j where exists (select userParticipate.id from j.room.userParticipate userParticipate)" +
                      "and :id in (select userParticipate.id from j.room.userParticipate userParticipate)")

    Integer getTotalParticipateNumsByUserId(@Param("id") String id);

 //   int getTotalParticipateNumsByUserId(@Param("id") String id);

    //得到一次出行对应的房间编号
    @Query("select j.room.id from JourneyEntity as j where j.id=:id")
    Integer findRoomById(@Param("id") Integer id);

    //分页得到自己是房主的出行

    @Query("from JourneyEntity as j where j.room.host.id=:id")
    Page<JourneyEntity> findByRoomHostId(@Param("id") String id, Pageable pageable);

    //分页找到得到某位用户参与的出行
 //   @Query("select  j from JourneyEntity j where exists (select userParticipate.id from j.room.userParticipate userParticipate)" +
  //          "and :id in (select userParticipate.participateUser.id from j.room.userParticipate userParticipate)")
    @Query("select  j from JourneyEntity j where exists (select userParticipate.id from j.room.userParticipate userParticipate)" +
                      "and :id in (select userParticipate.id from j.room.userParticipate userParticipate)")

    Page<JourneyEntity> findByParticipateId(@Param("id") String id, Pageable pageable);


    //分页得到某位用户的全部出行

//    @Query("select  j from JourneyEntity j where exists (select userParticipate.id from j.room.userParticipate userParticipate)" +
  //          "and :id in (select userParticipate.participateUser.id from j.room.userParticipate userParticipate) or j.room.host.id=:id")
 //   @Query("select  j from JourneyEntity j where exists (select userParticipate.id from j.room.userParticipate userParticipate)" +
  //                    "and :id in (select userParticipate.id from j.room.userParticipate userParticipate) or j.room.host.id=:id")

    @Query("select  j from JourneyEntity j where " +
                                " :id in (select userParticipate.id from j.room.userParticipate userParticipate)")
    Page<JourneyEntity> getAllJourneyByByUSerId(@Param("id") String id, Pageable pageable);

    @Query("select count(*) from JourneyEntity j where " +
            ":id in (select userParticipate.id from j.room.userParticipate userParticipate)")
    Integer getAllJourneyCountByUserId(@Param("id") String id);

    //得到用户的出行数，不管是不是房主
  //  @Query("select  count (j.id) from JourneyEntity j where exists (select userParticipate.id from j.room.userParticipate userParticipate)" +
 //           "and :id in (select userParticipate.participateUser.id from j.room.userParticipate userParticipate)")
    @Query("select  count (j.id) from JourneyEntity j where exists (select userParticipate.id from j.room.userParticipate userParticipate)" +
                       "and :id in (select userParticipate.id from j.room.userParticipate userParticipate)")

    int getJourneyNums(@Param("id") String id);

    //得到某条评论对应的出行
    @Query("from JourneyEntity j where :commentId in (select comments.id from j.comments comments)")
    JourneyEntity findByCommentId(@Param("commentId") Integer id);

    //根据房间ID得到该次出行（可能不存在任何出现）
    JourneyEntity findByRoomId(Integer id);


   //得到某个用户的出现的目的地，次数的统计
     @Query("select j.endPoint ,count(j.id)from JourneyEntity  j where :id in (select userParticipate.id from j.room.userParticipate userParticipate) group by j.endPoint  order by count(j.id) desc ")
     List<Object[]> getMypreferDestination(@Param("id") String id);


   //得到某个用户与其他用户的一起出行次数
//   @Query("select r from RoomEntity as r where :id in (select users.id from r.userParticipate users )")
//   List<RoomEntity> test(@Param("id")String id);




 //  List<Object> getTimeOfJourneyWithothers(@Param("id")String id);

}
