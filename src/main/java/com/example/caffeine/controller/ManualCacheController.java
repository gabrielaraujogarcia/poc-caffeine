package com.example.caffeine.controller;

import com.example.caffeine.dto.MyPojo;
import com.example.caffeine.service.ManualCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manual")
public class ManualCacheController {

    @Autowired
    private ManualCacheService manualCacheService;

    @GetMapping
    public ResponseEntity print() {
        try {
            String print = manualCacheService.printCache();
            return ResponseEntity.ok().body(print);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/put")
    public ResponseEntity put(@PathVariable Integer id) {
        try {
            String print = manualCacheService.put(id);
            return ResponseEntity.ok().body(print);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/get")
    public ResponseEntity getIfPresent(@PathVariable Integer id) {
        try {
            MyPojo pojo = manualCacheService.getIfPresent(id);
            return ResponseEntity.ok().body(pojo);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity addAndGet(@PathVariable Integer id) {
        try {
            MyPojo pojo = manualCacheService.addAndGet(id);
            return ResponseEntity.ok().body(pojo);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/invalidate")
    public ResponseEntity invalidate(@PathVariable Integer id) {
        try {
            manualCacheService.invalidate(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
