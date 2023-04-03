package com.softuni.shoestrade.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String title;

    @OneToOne
    private Shoe shoe;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String description;

    @OneToMany
    private List<Comment> comments;

}
