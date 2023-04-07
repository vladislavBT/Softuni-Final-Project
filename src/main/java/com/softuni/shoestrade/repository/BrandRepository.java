package com.softuni.shoestrade.repository;

import com.softuni.shoestrade.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
    Brand findBrandById(Long id);
}
