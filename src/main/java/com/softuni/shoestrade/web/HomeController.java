package com.softuni.shoestrade.web;

import com.softuni.shoestrade.model.Brand;
import com.softuni.shoestrade.model.Offer;
import com.softuni.shoestrade.model.Shoe;
import com.softuni.shoestrade.model.UserEntity;
import com.softuni.shoestrade.model.dto.UserRegistrationDTO;
import com.softuni.shoestrade.model.view.BrandView;
import com.softuni.shoestrade.model.view.ProfileLoggedUser;
import com.softuni.shoestrade.model.view.ShoeView;
import com.softuni.shoestrade.model.view.UserDetailsView;
import com.softuni.shoestrade.service.BrandService;
import com.softuni.shoestrade.service.ShoeService;
import com.softuni.shoestrade.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController{

    private UserService userService;

    private BrandService brandService;

    private ShoeService shoeService;

    @Autowired
    public HomeController(UserService userService, BrandService brandService, ShoeService shoeService) {
        this.userService = userService;
        this.brandService = brandService;
        this.shoeService = shoeService;
    }

    @GetMapping
    public ModelAndView getIndex(ModelAndView modelAndView) {
        List<Brand> brands = brandService.getAllBrands();
        List<Shoe> shoes = shoeService.getAllShoes();

        ShoeView shoe1 =new ShoeView(shoes.get(0).getId(),shoes.get(0).getName(),shoes.get(0).getImageUrl());
        ShoeView shoe2 = new ShoeView(shoes.get(1).getId(),shoes.get(1).getName(),shoes.get(1).getImageUrl());

        modelAndView.addObject("shoe1",shoe1);
        modelAndView.addObject("shoe2",shoe2);

        BrandView brand1 = new BrandView(brands.get(0).getId(),brands.get(0).getName(),brands.get(0).getDescription(),brands.get(0).getImageUrl());
        BrandView brand2 = new BrandView(brands.get(1).getId(),brands.get(1).getName(),brands.get(1).getDescription(),brands.get(1).getImageUrl());
        BrandView brand3 = new BrandView(brands.get(2).getId(),brands.get(2).getName(),brands.get(2).getDescription(),brands.get(2).getImageUrl());

        modelAndView.addObject("brand1",brand1);
        modelAndView.addObject("brand2",brand2);
        modelAndView.addObject("brand3",brand3);

        modelAndView.setViewName("index.html");

        return modelAndView;
    }

    @GetMapping("users/login")
    public ModelAndView getLogin() {
        return super.view("log-in.html");
    }

    @GetMapping("users/signup")
    public ModelAndView getSignup() {
        return super.view("sign-up.html");
    }

    @PostMapping("users/signup")
    public String doSignUp(@Valid UserRegistrationDTO userRegistrationDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        System.out.println(userRegistrationDTO.toString());

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);

            return "redirect:/users/signup";
        }

        this.userService.signUp(userRegistrationDTO);

        return "redirect:/users/login";
    }

    @GetMapping("users/profile")
    @Transactional
    public ModelAndView getProfile(Principal principal, ModelAndView modelAndView) {
        Optional<UserEntity> user = userService.getUserByName(principal.getName());

        ProfileLoggedUser profileLoggedUser = new ProfileLoggedUser(user.get().getFullName(),user.get().getDescription());
        UserDetailsView userDetailsView = new UserDetailsView(user.get().getUsername(), user.get().getEmail(),user.get().getFullName(), user.get().getMoney(), user.get().getDescription(),user.get().getAge(),user.get().getOffers(),user.get().getGender());

        userDetailsView.setGithub(user.get().getUsername());
        userDetailsView.setFacebook(user.get().getUsername());
        userDetailsView.setTwitter(user.get().getUsername());

        userDetailsView.setInstagram("@"+user.get().getUsername());
        userDetailsView.setWebsite("https://"+user.get().getUsername()+".com");

        List<Offer> myOffers = new ArrayList<>();
        myOffers = user.get().getOffers();

        modelAndView.addObject("loggedUser",profileLoggedUser);
        modelAndView.addObject("userProperties",userDetailsView);
        modelAndView.addObject("offers", myOffers);

        modelAndView.setViewName("profile.html");
       return modelAndView;
    }



    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initForm() {
        return new UserRegistrationDTO();
    }


    @PostMapping("users/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY) String password,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/users/login";
    }

    @ModelAttribute("loggedUser")
    public ProfileLoggedUser initFormLogged() {
        return new ProfileLoggedUser();
    }
}
