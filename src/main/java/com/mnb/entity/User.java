package com.mnb.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "USER")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USER_NAME")
    private String name;
    @Column(name = "USER_SURNAME")
    private String surname;
    @Column(name = "USER_EMAIL")
    private String email;
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "USER_GENDER")
    private String gender;
    @Column(name = "USER_DATE_OF_BIRTH")
    private LocalDateTime dateOfBirth;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<LibraryCart> carts;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
