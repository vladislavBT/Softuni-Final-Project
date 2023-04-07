package com.softuni.shoestrade.web;

import com.softuni.shoestrade.model.dto.BrandCreateDTO;
import com.softuni.shoestrade.model.dto.UserRegistrationDTO;
import com.softuni.shoestrade.service.BrandService;
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

@Controller
@RequestMapping("/brand")
public class BrandController extends BaseController{

    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/add")
    public ModelAndView getAdd() {
        return super.view("add-brand.html");
    }

    @GetMapping("/all")
    public ModelAndView getStore() {
        return super.view("all-brands.html");
    }

    @GetMapping("/details")
    public ModelAndView getDetails() {
        return super.view("brand-details.html");
    }

    @PostMapping("/add")
    public String doSignUp(@Valid BrandCreateDTO brandCreateDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        System.out.println(brandCreateDTO.toString());

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandCreateDTO", brandCreateDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandCreateDTO", bindingResult);

            return "redirect:/brand/add";
        }

        this.brandService.createBrand(brandCreateDTO);

        return "redirect:/";
    }

    @ModelAttribute("brandCreateDTO")
    public BrandCreateDTO initForm() {
        return new BrandCreateDTO();
    }

}
