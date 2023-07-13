package com.example.jsonxmlexercise.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
//id, name (at least 3 characters), price, buyerId (optional) and sellerId as IDs of users
public class Product extends BaseEntity {

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.JOIN)
    private User buyer;

    @ManyToOne(fetch = FetchType.EAGER)
    private User seller;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

}
