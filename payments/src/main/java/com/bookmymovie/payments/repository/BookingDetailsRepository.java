package com.bookmymovie.payments.repository;

import com.bookmymovie.payments.entity.BookingDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, UUID> , Pageable {



}