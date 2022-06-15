package kz.springboot.springbootdemoo.controllers;

import kz.springboot.springbootdemoo.entities.*;
import kz.springboot.springbootdemoo.services.MeridianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MeridianController {

    @Autowired
    private MeridianService meridianService;

    @GetMapping("/allresults")
    public String getAllResults(Model model) {
        List<MeridianOptions> optionsList = meridianService.getAllOptions();
        model.addAttribute("options", optionsList);
        return "meridian_results";
    }

    @GetMapping("/")
    public String getForm(Model model) {
        return "meridian_add";
    }

    @GetMapping("/about")
    public String about() {
        return "meridian_about";
    }


    @PostMapping("/add")
    public String addOptionAndCheck() {
        return "redirect:/";
    }
}
