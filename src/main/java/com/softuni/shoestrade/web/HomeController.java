package com.softuni.shoestrade.web;

import com.softuni.shoestrade.model.Offer;
import com.softuni.shoestrade.model.UserEntity;
import com.softuni.shoestrade.model.dto.UserRegistrationDTO;
import com.softuni.shoestrade.model.view.ProfileLoggedUser;
import com.softuni.shoestrade.model.view.UserDetailsView;
import com.softuni.shoestrade.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getIndex() {

        return super.view("index.html");
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
