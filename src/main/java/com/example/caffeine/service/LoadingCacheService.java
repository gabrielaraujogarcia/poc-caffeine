package com.example.caffeine.service;

import com.example.caffeine.dto.MyPojo;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoadingCacheService {

    @Autowired
    private LoadingCache<Integer, MyPojo> loadingCache;

    public MyPojo getPojo(Integer id) {
        return loadingCache.get(id);
    }

    public LoadingCache<Integer, MyPojo> getLoadingCache() {
        return loadingCache;
    }

    public String printCache() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();

        loadingCache.asMap().forEach((key, pojo) -> {
            builder.append("Key: ").append(key).append(" | Value: ").append(pojo).append(lineSeparator);
        });

        System.out.println(builder);
        return builder.toString();
    }

}
