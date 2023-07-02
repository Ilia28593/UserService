package com.example.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "persons")
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "password")
    private String password;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    private LocalDate birthday;

    public Person(String firstName, String password, String lastName, String email, LocalDate birthday, Role role) {
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.role = role;
    }

    @Enumerated(EnumType.STRING)

    private Role role;
}
