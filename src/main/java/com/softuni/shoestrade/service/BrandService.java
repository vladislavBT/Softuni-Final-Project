package com.softuni.shoestrade.service;

import com.softuni.shoestrade.model.Brand;
import com.softuni.shoestrade.model.dto.BrandCreateDTO;
import com.softuni.shoestrade.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
}
