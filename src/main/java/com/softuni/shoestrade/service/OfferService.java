package com.softuni.shoestrade.service;

import com.softuni.shoestrade.model.Offer;
import com.softuni.shoestrade.model.Shoe;
import com.softuni.shoestrade.model.UserEntity;
import com.softuni.shoestrade.model.dto.OfferCreateDTO;
import com.softuni.shoestrade.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Optional;

@Service
public class OfferService {
    private ShoeService shoeService;

    private ImageCloudService imageCloudService;

    private OfferRepository offerRepository;

    private UserService userService;

    @Autowired
    public OfferService(ShoeService shoeService, ImageCloudService imageCloudService, OfferRepository offerRepository, UserService userService) {
        this.shoeService=shoeService;
        this.imageCloudService = imageCloudService;
        this.offerRepository = offerRepository;
        this.userService = userService;
    }

    public void createOrder(OfferCreateDTO offerCreateDTO, Principal principal) {

        Optional<Shoe> shoe = this.shoeService.getShoeById(offerCreateDTO.getShoeId());

        Offer offer = new Offer(offerCreateDTO.getTitle(),offerCreateDTO.getGender(), offerCreateDTO.getSize(), shoe.get(),offerCreateDTO.getPrice(),offerCreateDTO.getDescription());

        MultipartFile image = offerCreateDTO.getImage();

        Optional<UserEntity> user = userService.getUserByName(principal.getName());
        offer.setSeller(user.get());

        String imageUrl = imageCloudService.saveImage(image);
        offer.setImageUrl(imageUrl);

        this.offerRepository.save(offer);
    }
}
