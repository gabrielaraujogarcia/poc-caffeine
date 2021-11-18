package com.example.caffeine.service;

import com.example.caffeine.dto.MyPojo;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AsyncLoadingCacheService {

    @Autowired
    private AsyncLoadingCache<Integer, MyPojo> asyncLoadingCache;

    public CompletableFuture<MyPojo> getPojo(Integer id) {
        return asyncLoadingCache.get(id);
    }

    public void invalidate(Integer id) {
        asyncLoadingCache.synchronous().invalidate(id);
    }

    public String printCache() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();

        asyncLoadingCache.asMap().forEach((key, pojo) -> {
            try {
                MyPojo myPojo = pojo.get();
                builder.append("Key: ").append(key).append(" | Value: ").append(myPojo).append(lineSeparator);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        });

        System.out.println(builder);
        return builder.toString();
    }

}
