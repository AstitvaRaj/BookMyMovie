package com.bookmymovie.events.repository;

import com.bookmymovie.events.entities.Screens;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface ScreensRepository extends Repository<Screens, UUID> {
}