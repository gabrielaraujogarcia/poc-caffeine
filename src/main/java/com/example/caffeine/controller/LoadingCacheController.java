package com.example.caffeine.controller;

import com.example.caffeine.dto.MyPojo;
import com.example.caffeine.service.LoadingCacheService;
import com.example.caffeine.service.ManualCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loading")
public class LoadingCacheController {

    @Autowired
    private LoadingCacheService loadingCacheService;

    @GetMapping
    public ResponseEntity print() {
        try {
            String print = loadingCacheService.printCache();
            return ResponseEntity.ok().body(print);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getPojo(@PathVariable Integer id) {
        try {
            MyPojo pojo = loadingCacheService.getPojo(id);
            return ResponseEntity.ok().body(pojo);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }



}
