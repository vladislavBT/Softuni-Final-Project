package com.softuni.shoestrade.web;

import com.softuni.shoestrade.model.Brand;
import com.softuni.shoestrade.model.dto.ShoeCreateDTO;
import com.softuni.shoestrade.service.BrandService;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shoe")
public class ShoeController extends BaseController{
    private ShoeService shoeService;

    private BrandService brandService;

    @Autowired
    public ShoeController(ShoeService shoeService, BrandService brandService) {
        this.shoeService = shoeService;
        this.brandService = brandService;
    }


    @GetMapping("/add")
    public ModelAndView getAdd(ModelAndView modelAndView) {

        List<Brand> allBrands = this.brandService.getAllBrands();
        modelAndView.addObject("brands", allBrands);
        modelAndView.setViewName("add-shoe.html");
        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView getShoes() {
        return super.view("all-original-shoes.html");
    }

    @GetMapping("/details")
    public ModelAndView getDetails() {
        return super.view("brand-details.html");
    }

    @PostMapping("/add")
    public String doSignUp(@Valid ShoeCreateDTO shoeCreateDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        System.out.println(shoeCreateDTO.toString());

        if(shoeCreateDTO.getBrandId()==-1){
            return "redirect:/shoe/add";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shoeCreateDTO", shoeCreateDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shoeCreateDTO", bindingResult);

            return "redirect:/shoe/add";
        }

        this.shoeService.createShoe(shoeCreateDTO);

        return "redirect:/";
    }

    @ModelAttribute("shoeCreateDTO")
    public ShoeCreateDTO initForm() {
        return new ShoeCreateDTO();
    }
}
