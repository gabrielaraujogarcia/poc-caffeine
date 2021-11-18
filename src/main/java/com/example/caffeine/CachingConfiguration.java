package com.example.caffeine;

import com.example.caffeine.dto.MyPojo;
import com.github.benmanes.caffeine.cache.*;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CachingConfiguration {

    @Bean
    public Cache<Integer, MyPojo> manualCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(3)
                .build();
    }

    @Bean
    public LoadingCache<Integer, MyPojo> loadingCache() {
        return Caffeine.newBuilder()
                .maximumSize(3)
                .expireAfterWrite(10, TimeUnit.SECONDS)
//                .expireAfterAccess(10, TimeUnit.SECONDS) //RESETA O CACHE APOS X TEMPOS PASSADOS DESDE O ULTIMO ACESSO
                .build(key -> new MyPojo(key, "Pojo "+ key));
    }

    @Bean
    public AsyncCache<Integer, MyPojo> manualAsyncCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10)
                .buildAsync();
    }

    @Bean
    public AsyncLoadingCache<Integer, MyPojo> asyncLoadingCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10)
                .buildAsync(key -> new MyPojo(key, "Pojo" + key));
    }

    @Bean
    public Caffeine caffeine() {
        return Caffeine.newBuilder()
                .maximumSize(5)
                .expireAfterWrite(10, TimeUnit.MINUTES);
    }

    /**
     * Se quiser criar configuracoes especificas de cache para o caffeine, poed usar isso aqui
     */
//    @Bean
//    public CacheManager cacheManager(Caffeine caffeine) {
//        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
//
//        List<String> cachesNames = new ArrayList<>();
//        cachesNames.add("tokens");
//        cachesNames.add("nok");
//
//        caffeineCacheManager.setCacheNames(cachesNames);
//        caffeineCacheManager.setCaffeine(caffeine);
//
//        return caffeineCacheManager;
//    }

}

