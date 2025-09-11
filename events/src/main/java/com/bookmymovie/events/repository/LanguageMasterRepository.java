package com.bookmymovie.events.repository;

import com.bookmymovie.events.entities.LanguageMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageMasterRepository extends JpaRepository<LanguageMaster, Long> {
}