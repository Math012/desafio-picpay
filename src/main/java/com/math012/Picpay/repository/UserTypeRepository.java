package com.math012.Picpay.repository;

import com.math012.Picpay.model.UserTypeModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Transactional
@Repository
public interface UserTypeRepository extends JpaRepository<UserTypeModel, Long> {


    @Query("SELECT r FROM UserTypeModel r WHERE r.userType =:userType")
    UserTypeModel findByNameRole(@Param("userType")String userType);
}
