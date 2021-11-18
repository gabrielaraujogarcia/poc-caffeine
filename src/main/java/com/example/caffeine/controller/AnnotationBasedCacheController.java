package com.example.caffeine.controller;

import com.example.caffeine.dto.MyPojo;
import com.example.caffeine.service.AnnotationBasedCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/annotation")
public class AnnotationBasedCacheController {

    @Autowired
    private AnnotationBasedCacheService annotationBasedCacheService;

    @GetMapping("/fixed")
    public ResponseEntity fixed() {

        try {
            MyPojo response = annotationBasedCacheService.fixedKey();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getPojo(@PathVariable Integer id) {

        try {
            MyPojo response = annotationBasedCacheService.getPojo(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/{id}/another")
    public ResponseEntity another(@PathVariable Integer id) {

        try {
            MyPojo response = annotationBasedCacheService.anotherCache(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/{id}/update")
    public ResponseEntity update(@PathVariable Integer id) {

        try {
            MyPojo response = annotationBasedCacheService.update(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/{id}/remove")
    public ResponseEntity remove(@PathVariable Integer id) {

        try {
            annotationBasedCacheService.remove(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

}
