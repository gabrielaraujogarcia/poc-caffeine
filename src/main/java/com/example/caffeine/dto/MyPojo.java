package com.example.caffeine.dto;

import java.io.Serializable;
import java.util.UUID;

public class MyPojo implements Serializable {

    private Integer id;
    private String name;
    private String token;

    public MyPojo(Integer id, String name) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.id = id;
        this.name = name;
        this.token = UUID.randomUUID().toString();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "MyPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
