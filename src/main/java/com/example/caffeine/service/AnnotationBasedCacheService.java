package com.example.caffeine.service;

import com.example.caffeine.dto.MyPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AnnotationBasedCacheService {

    private final Integer myFiexedKey = -1;

    @Autowired
    private CacheManager cacheManager;

    /**
     *  o value e usado para falar qual cache queremos usar e
     *  A key e usado para dizer para o caffeine qual campo queremos usar como chave do cache e, se nao informado ele gera uma automaticamente
     *  Quando o objeto expira no cache ele remove automaticamente do cache
     *  Quando o tamanho maximo do cache e atingido ele remove uma entrada do cach. Qual entrada? Não sei :D
     */
    @Cacheable(value = "tokens", key = "#id")
    public MyPojo getPojo(Integer id) {
        MyPojo pojo = new MyPojo(id, "Pojo " + id);
        System.out.println("Pojo: " + pojo);
        return pojo;
    }

    @Cacheable(value = "another")
    public MyPojo anotherCache(Integer id) {
        MyPojo pojo = new MyPojo(id, "Pojo " + id);
        System.out.println("Pojo: " + pojo);
        return pojo;
    }

    @Cacheable(value = "tokens", key = "#root.target.myFiexedKey")
    public MyPojo fixedKey() {
        MyPojo pojo = new MyPojo(myFiexedKey, "Pojo " + myFiexedKey);
        System.out.println("Pojo: " + pojo);
        return pojo;
    }

    @CachePut(value="tokens", key="#id")
    public MyPojo update(Integer id){
        return new MyPojo(id, "Pojo "+ id);
    }

    @CacheEvict(value="tokens", key="#id")
    public void remove(Integer id){
        System.out.println(id + " removed from cache");
    }

    /**
     * Tem que ter o get pra associar a chave na classe
     * @return
     */
    public Integer getMyFiexedKey() {
        return myFiexedKey;
    }

}
