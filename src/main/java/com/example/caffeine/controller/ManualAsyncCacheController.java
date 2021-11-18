package com.example.caffeine.controller;

import com.example.caffeine.dto.MyPojo;
import com.example.caffeine.service.ManualAsyncCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/async")
public class ManualAsyncCacheController {

    @Autowired
    private ManualAsyncCacheService manualAsyncCacheService;

    @GetMapping
    public ResponseEntity print() {
        try {
            String print = manualAsyncCacheService.printCache();
            return ResponseEntity.ok().body(print);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getPojo(@PathVariable Integer id) {
        try {
            CompletableFuture<MyPojo> pojo = manualAsyncCacheService.getPojo(id);
            return ResponseEntity.ok().body(pojo.get());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/invalidate")
    public ResponseEntity invalidate(@PathVariable Integer id) {
        try {
            manualAsyncCacheService.invalidate(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
