package com.bookmymovie.events.repository;

import com.bookmymovie.events.entities.LocationMaster;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface LocationMasterRepository extends Repository<LocationMaster, UUID> {
}