package com.softuni.shoestrade.web;

import com.softuni.shoestrade.model.Brand;
import com.softuni.shoestrade.model.Shoe;
import com.softuni.shoestrade.model.dto.ShoeCreateDTO;
import com.softuni.shoestrade.service.BrandService;
import com.softuni.shoestrade.service.ShoeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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
    public ModelAndView getAllShoes(ModelAndView modelAndView, @PageableDefault(
            sort = "id",
            size = 3
    ) Pageable pageable) {

        var shoes = this.shoeService.getAllShoesForPages(pageable);

        modelAndView.addObject("shoes", shoes);

        List<Integer> pagesNumber = new ArrayList<>();

        for (int i = 0; i < shoes.getTotalPages(); i++) {
            pagesNumber.add(i);
        }

        modelAndView.addObject("pagesNumber", pagesNumber);

        modelAndView.setViewName("all-original-shoes.html");

        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView getDetails(@PathVariable(name = "id") long shoeId, ModelAndView modelAndView) {
        Optional<Shoe> shoeOptional = this.shoeService.getShoeById(shoeId);

        Shoe shoe = shoeOptional.get();
        Brand brand = shoe.getBrand();

        modelAndView.addObject("brand", brand);
        modelAndView.addObject("shoe",shoe);

        modelAndView.setViewName("shoe-details.html");
        return modelAndView;
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
