package com.softuni.shoestrade.model;


import com.softuni.shoestrade.model.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "money")
    private double money;
    @Basic
    private int age;

    @Column(nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @OneToMany(targetEntity = Comment.class, mappedBy = "author",fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(targetEntity = Offer.class, mappedBy = "seller",fetch = FetchType.EAGER)
    private List<Offer> offers = new ArrayList<>();

    public UserEntity(String username, String password, String email, String fullName, int age, String description, Gender gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.age = age;
        this.description = description;
        this.gender = gender;
    }
}
