package com.softuni.shoestrade.web;

import com.softuni.shoestrade.model.Brand;
import com.softuni.shoestrade.model.Offer;
import com.softuni.shoestrade.model.Shoe;
import com.softuni.shoestrade.model.UserEntity;
import com.softuni.shoestrade.model.dto.OfferCreateDTO;
import com.softuni.shoestrade.service.OfferService;
import com.softuni.shoestrade.service.ShoeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/offer")
public class OfferController extends BaseController{

    private ShoeService shoeService;

    private OfferService offerService;

    @Autowired
    public OfferController(ShoeService shoeService, OfferService offerService) {
        this.shoeService = shoeService;
        this.offerService = offerService;
    }


    @GetMapping("/add")
    public ModelAndView getAdd(ModelAndView modelAndView) {
        List<Shoe> allShoes = this.shoeService.getAllShoes();
        modelAndView.addObject("shoes", allShoes);
        modelAndView.setViewName("add-offer.html");
        return modelAndView;
    }

    @GetMapping("/store")
    public ModelAndView getAllOffers(ModelAndView modelAndView, @PageableDefault(
            sort = "id",
            size = 3
    ) Pageable pageable) {

        var offers = this.offerService.getAllOffersForPages(pageable);

        modelAndView.addObject("offers", offers);

        List<Integer> pagesNumber = new ArrayList<>();

        for (int i = 0; i < offers.getTotalPages(); i++) {
            pagesNumber.add(i);
        }

        modelAndView.addObject("pagesNumber", pagesNumber);

        modelAndView.setViewName("store.html");

        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView getDetails(@PathVariable(name = "id") long offerId, ModelAndView modelAndView,Principal principal) {



        Offer offer = offerService.getOfferById(offerId);
        UserEntity seller = offer.getSeller();
        Shoe shoe = offer.getShoe();
        Brand brand = shoe.getBrand();

        boolean sameUser =false;
        if(principal.getName().equals(seller.getUsername())){
            sameUser=true;
        }

        modelAndView.addObject("offer", offer);
        modelAndView.addObject("seller", seller);
        modelAndView.addObject("shoe", shoe);
        modelAndView.addObject("brand", brand);

        modelAndView.addObject("sameUser", sameUser);

        modelAndView.setViewName("offer-details.html");

        return modelAndView;
    }

    @PostMapping("/add")
    public String doSignUp(@Valid OfferCreateDTO offerCreateDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes, Principal principal) {
        System.out.println(offerCreateDTO.toString());

        if(offerCreateDTO.getShoeId()==-1){
            return "redirect:/offer/add";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerCreateDTO", offerCreateDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerCreateDTO", bindingResult);

            return "redirect:/offer/add";
        }

        this.offerService.createOrder(offerCreateDTO, principal);

        return "redirect:/";
    }




    @ModelAttribute("offerCreateDTO")
    public OfferCreateDTO initForm() {
        return new OfferCreateDTO();
    }

}
