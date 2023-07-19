package bg.softuni.mvcdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        modelAndView.addObject("firstName", "Pesho");

        return modelAndView;
    }

    @GetMapping("/home")
    public String home(
            @RequestParam(value = "show-ad", required = false) Integer adId
    ) {
        System.out.println(adId);

        return "home";
    }
}
