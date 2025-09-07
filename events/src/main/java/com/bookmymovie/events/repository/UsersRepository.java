package com.bookmymovie.events.repository;

import com.bookmymovie.events.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {

    @Query("select u from Users u join FETCH u.userTypeEntity where u.username = ?1")
    Optional<Users> findByUsername(String username);

}
