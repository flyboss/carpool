package com.carpool.website.dao;

import com.carpool.domain.PaymentRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 91574 on 2016/11/30.
 */
public interface PaymentRecordEntityRepository extends JpaRepository<PaymentRecordEntity,Integer>{
//    @Query("select record from PaymentRecordEntity as record where record.sourceUser="+
//           "(select sourceUser from UserEntity as sourceUser where sourceUser.id=?1)")
//    Page<PaymentRecordEntity> findBySourceUser(String id);
}
