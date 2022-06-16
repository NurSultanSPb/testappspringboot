package kz.springboot.springbootdemoo.controllers;

import kz.springboot.springbootdemoo.entities.*;
import kz.springboot.springbootdemoo.services.MeridianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
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


    @PostMapping("/")
    public String addOptionAndCheck(@RequestParam(name = "gun_number", defaultValue = "0") int gunNumber,
                                    @RequestParam(name = "x", defaultValue = "0") double x,
                                    @RequestParam(name = "y", defaultValue = "0")  double y,
                                    @RequestParam(name = "target_one", defaultValue = "1") int targetOne,
                                    @RequestParam(name = "azimuth_one", defaultValue = " ") String azimuthOne,
                                    @RequestParam(name = "distance_one", defaultValue = "0") double distanceOne,
                                    @RequestParam(name = "target_two", defaultValue = "1") int targetTwo,
                                    @RequestParam(name = "azimuth_two", defaultValue = " ") String azimuthTwo,
                                    @RequestParam(name = "distance_two", defaultValue = "0") double distanceTwo) {
        MeridianOptions option = new MeridianOptions();

        option.setNumberOfGun(gunNumber);
        option.setX(x);
        option.setY(y);

        option.setNumberOfTargetOne(targetOne);
        option.setAzimuthOne(azimuthOne);
        option.setDistanceOne(distanceOne);

        option.setNumberOfTargetTwo(targetTwo);
        option.setAzimuthTwo(azimuthTwo);
        option.setDistanceTwo(distanceTwo);

        meridianService.addOption(option);
        return "redirect:/allresults";
    }

    @GetMapping("/delete")
    public String deleteOption() {

        return "redirect:/allresults";
    }

}
