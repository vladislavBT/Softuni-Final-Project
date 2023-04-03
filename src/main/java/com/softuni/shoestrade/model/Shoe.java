package com.softuni.shoestrade.model;

import com.softuni.shoestrade.model.enums.Fabric;
import com.softuni.shoestrade.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Fabric fabric;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Brand brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private Color color;

    @Min(value = 36)
    @Max(value = 47)
    private double size;
}
