package com.softuni.shoestrade.model;

import com.softuni.shoestrade.model.enums.Category;
import com.softuni.shoestrade.model.enums.Color;
import com.softuni.shoestrade.model.enums.Fabric;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shoes")
public class Shoe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Fabric fabric;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @ManyToOne
    private Brand brand;

    @Column(nullable = false)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Color color;

    public Shoe(String name, Fabric fabric, Category category, Brand brand, Color color) {
        this.name = name;
        this.fabric = fabric;
        this.category = category;
        this.brand = brand;
        this.color = color;
    }



}
