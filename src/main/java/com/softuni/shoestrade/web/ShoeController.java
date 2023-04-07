package com.softuni.shoestrade.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shoe")
public class ShoeController extends BaseController{

    @GetMapping("/add")
    public ModelAndView getAdd() {
        return super.view("add-shoe.html");
    }

    @GetMapping("/all")
    public ModelAndView getShoes() {
        return super.view("all-original-shoes.html");
    }

    @GetMapping("/details")
    public ModelAndView getDetails() {
        return super.view("brand-details.html");
    }
}
