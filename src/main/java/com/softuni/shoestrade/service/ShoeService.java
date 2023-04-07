package com.softuni.shoestrade.service;

import com.softuni.shoestrade.model.Brand;
import com.softuni.shoestrade.model.Shoe;
import com.softuni.shoestrade.model.dto.ShoeCreateDTO;
import com.softuni.shoestrade.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoeService {
    private BrandService brandService;

    private ImageCloudService imageCloudService;

    private ShoeRepository shoeRepository;

    @Autowired
    public ShoeService(BrandService brandService, ImageCloudService imageCloudService, ShoeRepository shoeRepository) {
        this.brandService = brandService;
        this.imageCloudService = imageCloudService;
        this.shoeRepository = shoeRepository;
    }

    public void createShoe(ShoeCreateDTO shoeCreateDTO) {
        Brand brand = brandService.getBrandById(shoeCreateDTO.getBrandId());
        Shoe shoe = new Shoe(shoeCreateDTO.getName(),shoeCreateDTO.getFabric(),shoeCreateDTO.getCategory(),brand,shoeCreateDTO.getColor());

        List<Shoe> shoes;
        try {
            shoes = shoeRepository.findAll();
        }catch (NullPointerException ex){
            shoes = new ArrayList<>();
        }


        shoe.setId(shoes.size()+1);
        shoe.setCategory(shoeCreateDTO.getCategory());
        shoe.setColor(shoeCreateDTO.getColor());
        shoe.setFabric(shoeCreateDTO.getFabric());
        shoe.setName(shoeCreateDTO.getName());

        MultipartFile image = shoeCreateDTO.getImage();

        String imageUrl = imageCloudService.saveImage(image);
        shoe.setImageUrl(imageUrl);

        this.shoeRepository.save(shoe);
    }

    public List<Shoe> getAllShoes() {
        return this.shoeRepository.findAll();
    }

    public Optional<Shoe> getShoeById(long shoeId) {
        return this.shoeRepository.findById(shoeId);
    }
}
