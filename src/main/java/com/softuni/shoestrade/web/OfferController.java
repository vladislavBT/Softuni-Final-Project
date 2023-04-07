package com.softuni.shoestrade.web;

import com.softuni.shoestrade.model.Shoe;
import com.softuni.shoestrade.model.dto.OfferCreateDTO;
import com.softuni.shoestrade.service.OfferService;
import com.softuni.shoestrade.service.ShoeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
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
    public ModelAndView getStore() {
        return super.view("store.html");
    }

    @GetMapping("/details")
    public ModelAndView getDetails() {
        return super.view("offer-details.html");
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
