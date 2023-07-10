package com.example.jsonxmlexercise.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
//id, first name (optional) and last name (at least 3 characters) and age (optional).
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private Integer age;

    @OneToMany(targetEntity = Product.class, mappedBy = "seller", fetch = FetchType.EAGER)
    private Set<Product> sellingProducts;

    @OneToMany(targetEntity = Product.class, mappedBy = "buyer", fetch = FetchType.EAGER)
    private Set<Product> boughtProducts;

    @ManyToMany
    private Set<User> friends;
}
