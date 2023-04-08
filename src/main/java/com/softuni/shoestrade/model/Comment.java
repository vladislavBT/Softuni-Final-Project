package com.softuni.shoestrade.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean approved;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private Offer offer;

}
