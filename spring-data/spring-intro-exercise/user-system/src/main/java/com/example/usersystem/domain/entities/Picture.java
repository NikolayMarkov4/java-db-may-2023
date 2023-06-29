package com.example.usersystem.domain.entities;

import lombok.*;

import jakarta.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    @Column
    private String title;

    @Column
    private String caption;

    @Column
    private String pathOnFileSystem;

    @ManyToMany
    private Set<Album> albums;

}