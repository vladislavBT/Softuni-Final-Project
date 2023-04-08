package com.softuni.shoestrade.service;

import com.softuni.shoestrade.model.Brand;
import com.softuni.shoestrade.model.dto.BrandCreateDTO;
import com.softuni.shoestrade.model.view.BrandView;
import com.softuni.shoestrade.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private BrandRepository brandRepository;
    private ImageCloudService imageCloudService;

    @Autowired
    public BrandService(BrandRepository brandRepository, ImageCloudService imageCloudService) {
        this.brandRepository = brandRepository;
        this.imageCloudService = imageCloudService;
    }

    public void createBrand(BrandCreateDTO brandCreateDTO) {
        MultipartFile image = brandCreateDTO.getImage();

        String imageUrl = imageCloudService.saveImage(image);

        Brand brand = new Brand(brandCreateDTO.getName(),brandCreateDTO.getDescription(),imageUrl);

        this.brandRepository.save(brand);
    }

    public List<Brand> getAllBrands() {
        return this.brandRepository.findAll();
    }

    public Brand getBrandById(long brandId) {
        return this.brandRepository.findBrandById(brandId);
    }

    public Page<BrandView> getAllBrandsForPages(Pageable pageable) {
        return brandRepository.
                findAll(pageable).
                map(this::map);
    }

    private BrandView map(Brand brand) {
        return new BrandView(brand.getId(), brand.getName(), brand.getDescription(), brand.getImageUrl());

    }
}
