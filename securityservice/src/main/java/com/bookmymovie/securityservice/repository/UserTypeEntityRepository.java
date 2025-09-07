package com.bookmymovie.securityservice.repository;

import com.bookmymovie.securityservice.entity.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeEntityRepository extends JpaRepository<UserTypeEntity, Long> {
}
