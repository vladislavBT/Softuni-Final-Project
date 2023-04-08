package com.softuni.shoestrade.web;

import com.softuni.shoestrade.model.Brand;
import com.softuni.shoestrade.model.dto.BrandCreateDTO;
import com.softuni.shoestrade.model.dto.UserRegistrationDTO;
import com.softuni.shoestrade.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
    public ModelAndView getAllBrands(ModelAndView modelAndView, @PageableDefault(
            sort = "id",
            size = 2
    ) Pageable pageable) {

        var brands = this.brandService.getAllBrandsForPages(pageable);

        modelAndView.addObject("brands", brands);

        List<Integer> pagesNumber = new ArrayList<>();
        for (int i = 0; i < brands.getTotalPages(); i++) {
            pagesNumber.add(i);
        }

        modelAndView.addObject("pagesNumber", pagesNumber);

        modelAndView.setViewName("all-brands.html");

        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView getDetails(@PathVariable(name = "id") long brandId, ModelAndView modelAndView) {
        Brand brand = brandService.getBrandById(brandId);

        modelAndView.addObject("brand", brand);
        modelAndView.setViewName("brand-details.html");

        return modelAndView;
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
