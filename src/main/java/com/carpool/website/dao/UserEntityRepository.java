package com.carpool.website.dao;

import com.carpool.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by qi on 2016/11/26.
 */

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, String> {
    public UserEntity findById(String id);
    public Page<UserEntity> findByUsername(String username, Pageable pageable);
    public Page<UserEntity> findByQqAccount(String acount, Pageable pageable);
    public Page<UserEntity> findByWechatAccount(String acount, Pageable pageable);

    public Integer          countIdByGender(byte gender);

    @Query("select user.coins from UserEntity user where user.id=?1")
    public Integer getCoinsById(String id);

    @Query("select user.alipay from UserEntity user where user.id=?1")
    public String getAlipayById(String id);

    @Query("select user.qqAccount from UserEntity user where user.id=?1")
    public String getQQAccountById(String id);

    @Query("select user.wechatAccount from UserEntity user where user.id=?1")
    public String getWechatByid(String id);

    @Query("select user.password from UserEntity user where user.id=?1")
    public String getPwById(String id);

    @Query("select user.gender from UserEntity user where user.id=?1")
    public Byte getGenderById(String id);

    @Query("select user.credit from UserEntity user where user.id=?1")
    public Double getCreditById(String id);

    @Modifying
    @Query("delete from UserEntity user where user.id=?1")
    public Integer deleteUserById(String id);

    @Modifying
    @Query("update UserEntity user set user.username=?1 where user.id=?2")
    public Integer setUsername(String newName, String id);

    @Modifying
    @Query("update UserEntity user set user.alipay =?1 where user.id=?2")
    public Integer setUserAlipay(String newAlipay, String id);

    @Modifying
    @Query("update UserEntity user set user.password=?1 where user.id=?2")
    public Integer setUserPw(String newPw, String id);

}
