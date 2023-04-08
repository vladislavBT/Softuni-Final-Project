package com.softuni.shoestrade.model;

import com.softuni.shoestrade.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Min(value = 36)
    @Max(value = 47)
    private double size;

    @OneToOne
    private Shoe shoe;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String description;



    @ManyToOne
    private UserEntity seller;
    @OneToMany(targetEntity = Comment.class, mappedBy = "offer",fetch = FetchType.EAGER)
    private List<Comment> comments;

    public Offer(String title, Gender gender, double size, Shoe shoe, double price, String description) {
        this.title = title;
        this.gender = gender;
        this.size = size;
        this.shoe = shoe;
        this.price = price;
        this.description = description;
    }
}
