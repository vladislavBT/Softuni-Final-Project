package com.softuni.shoestrade.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/offer")
public class OfferController extends BaseController{

    @GetMapping("/add")
    public ModelAndView getAdd() {
        return super.view("add-offer.html");
    }

    @GetMapping("/store")
    public ModelAndView getStore() {
        return super.view("store.html");
    }

    @GetMapping("/details")
    public ModelAndView getDetails() {
        return super.view("offer-details.html");
    }

}
