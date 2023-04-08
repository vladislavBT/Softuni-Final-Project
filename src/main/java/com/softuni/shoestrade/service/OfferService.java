package com.softuni.shoestrade.service;

import com.softuni.shoestrade.model.Offer;
import com.softuni.shoestrade.model.Shoe;
import com.softuni.shoestrade.model.UserEntity;
import com.softuni.shoestrade.model.dto.OfferCreateDTO;
import com.softuni.shoestrade.model.view.OfferView;
import com.softuni.shoestrade.model.view.ShoeView;
import com.softuni.shoestrade.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Offer getOfferById(long offerId) {
        return this.offerRepository.findOfferById(offerId);
    }

    public Page<OfferView> getAllOffersForPages(Pageable pageable) {
        return offerRepository.
                findAll(pageable).
                map(this::map);
    }

    private OfferView map(Offer offer) {
        return new OfferView(offer.getId(), offer.getTitle(), offer.getPrice(), offer.getImageUrl());

    }
}
