package com.example.entity;

import lombok.Data;

import javax.persistence.Column;

@Data
public class Goods {
    @Column(name = "id")
    private Long id;

    private String name;

    private String brand;

    //private short inventory;

    private String pic_url;
}
