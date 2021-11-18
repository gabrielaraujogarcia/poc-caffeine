package com.example.caffeine.service;

import com.example.caffeine.dto.MyPojo;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManualCacheService {

    @Autowired
    private Cache<Integer, MyPojo> manualCache;

    /**
     * Adiciona o MyPojo no cache. Ele substitui o valor da chave se ela ja existir no cache
     * @param id
     * @return
     */
    public String put(Integer id) {

        manualCache.put(id, new MyPojo(id, "Pojo " + id));
        return printCache();
    }

    /**
     * Retorna se existir no cache se nao retorna nulo
     * @param id
     * @return
     */
    public MyPojo getIfPresent(Integer id) {
        return manualCache.getIfPresent(id);
    }

    /**
     * Adiciona no cache se nao existir e entao retorna o MyPojo
     * @param id
     * @return
     */
    public MyPojo addAndGet(Integer id) {
        return manualCache.get(id, key -> new MyPojo(id, "Pojo " + id));
    }

    /**
     * Invalida um objeto do cache (remove)
     * @param id
     * @return
     */
    public String invalidate(Integer id) {
        manualCache.invalidate(id);
        return printCache();
    }

    public String printCache() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();

        manualCache.asMap().forEach((key, pojo) -> {
            builder.append("Key: ").append(key).append(" | Value: ").append(pojo).append(lineSeparator);
        });

        System.out.println(builder);
        return builder.toString();
    }

}
