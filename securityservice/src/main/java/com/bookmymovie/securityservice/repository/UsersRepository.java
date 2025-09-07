package com.bookmymovie.securityservice.repository;

import com.bookmymovie.securityservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {

    @Query("select u from Users u join FETCH u.userTypeEntity where u.email = ?1")
    Optional<Users> findByEmail(String email);

}
