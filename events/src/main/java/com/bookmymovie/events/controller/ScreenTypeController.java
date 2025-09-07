package com.bookmymovie.events.controller;

import com.bookmymovie.events.repository.ScreenTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("screen/type")
public class ScreenTypeController {

    @Autowired
    ScreenTypeRepo screenTypeRepo;

    @GetMapping
    public ResponseEntity<?> getList(){
        return ResponseEntity.ok(screenTypeRepo.findAll());
    }

}
