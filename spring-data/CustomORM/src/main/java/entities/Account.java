package entities;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "accounts")
public class Account {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "countOfFollowers")
    private Integer countOfFollowers;

    @Column(name = "createdOn")
    private LocalDate createdOn;

    @Column(name = "lastLogged")
    private LocalDate lastLogged;

    @Column(name = "nickname")
    private String nickname;

    public Account(String name, LocalDate createdOn, Integer age) {
        this.name = name;
        this.createdOn = createdOn;
        this.age = age;
    }
}
