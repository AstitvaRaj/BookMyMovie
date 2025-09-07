package com.bookmymovie.events.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class ScreenController {

    @PostMapping("admin/screen/add")
    public ResponseEntity<?> createScreen(@RequestBody Map<String, String> map){
        return null;
    }

    @PostMapping("admin/screen/add-layout")
    public ResponseEntity<?> createSeatLayout(@RequestBody Map<String, String> map){
        return null;
    }




}
