package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Prodcut {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private Integer price;

    @ManyToOne
    private Warehouse warehouse;
}
