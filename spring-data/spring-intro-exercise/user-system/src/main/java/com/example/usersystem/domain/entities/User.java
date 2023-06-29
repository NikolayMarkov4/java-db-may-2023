package com.example.usersystem.domain.entities;

import com.example.usersystem.annotations.email.Email;
import com.example.usersystem.annotations.password.Password;
import com.example.usersystem.constants.Constants;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.Date;
import java.util.Set;

import static com.example.usersystem.constants.Constants.FULL_NAME_AGE_FORMAT;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Length(min = 4, max = 30, message = Constants.USERNAME_INCORRECT_LENGTH)
    private String userName;

    @Column(nullable = false)
    @Password(minLength = 6,
            maxLength = 50,
            containsDigit = true,
            containsLowerCase = true,
            containsUpperCase = true,
            containsSpecialSymbols = true)
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Lob
    @Size(max = 1024 * 1024)
    private byte[] profilePicture;

    @Column
    private Date registeredOn;

    @Column
    private Date lastTimeLoggedIn;

    @Min(value = 1, message = Constants.AGE_TOO_LOW)
    @Max(value = 120, message = Constants.AGE_TOO_HIGH)
    private Integer age;

    @Column
    private Boolean isDeleted;

    @ManyToOne
    private Town bornTown;

    @ManyToOne
    private Town currentlyLiving;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "friends",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id")}
    )
    private Set<User> friends;

    @OneToMany
    private Set<Album> albums;


    public String getFullNameAndAge() {
        return String.format(FULL_NAME_AGE_FORMAT, this.firstName, this.lastName, this.age);
    }
}