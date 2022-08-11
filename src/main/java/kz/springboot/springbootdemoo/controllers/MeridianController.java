package kz.springboot.springbootdemoo.controllers;

import kz.springboot.springbootdemoo.entities.*;
import kz.springboot.springbootdemoo.services.MeridianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        //option = вводимый вариант

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
        //сохранение вводимого варианта

        Long id = option.getId();


        //допуск расстояния х и у
        double deltaDistance = 0.200001;

        //допуск разности координат
        double deltaXY = 0.050001;

        if (id != 1 && id != 2) {
            //option1 =  данные 1 варианта
            MeridianOptions option1 = meridianService.getOption(1L);

            //option2 =  данные 2 варианта
            MeridianOptions option2 = meridianService.getOption(2L);

            String degree = option.getAzimuthOne();
            String degree1 = option1.getAzimuthOne();
            String degree2 = option2.getAzimuthOne();

            String degreeTwo = option.getAzimuthTwo();
            String degreeTwo1 = option1.getAzimuthTwo();
            String degreeTwo2 = option2.getAzimuthTwo();

            //для номера орудия = 1
            if (option.getNumberOfGun() == option1.getNumberOfGun()) {
                if ((Math.abs(option.getX() - option1.getX()) > deltaXY) || (Math.abs(option.getY() - option1.getY()) > deltaXY)) {
                    return "meridian_add_miss_all";
                } else {
                    if ((Math.abs(option.getDistanceOne() - option1.getDistanceOne()) > deltaDistance) && (Math.abs(option.getDistanceTwo() - option1.getDistanceTwo()) > deltaDistance)) {
                        return "meridian_add_miss_all";
                    }
                    if ((Math.abs(option.getDistanceOne() - option1.getDistanceOne()) <= deltaDistance) && (Math.abs(option.getDistanceTwo() - option1.getDistanceTwo()) > deltaDistance)) {
                        if (compareDegrees(degree, degree1)) {
                            return "meridian_add_success_first";
                        } else {
                            return "meridian_add_miss_all";
                        }
                    } else if ((Math.abs(option.getDistanceOne() - option1.getDistanceOne()) > deltaDistance) && (Math.abs(option.getDistanceTwo() - option1.getDistanceTwo()) <= deltaDistance)) {
                        if (compareDegrees(degreeTwo, degreeTwo1)) {
                            return "meridian_add_success_second";
                        } else {
                            return "meridian_add_miss_all";
                        }
                    }
                    if ((Math.abs(option.getDistanceOne() - option1.getDistanceOne()) <= deltaDistance) && (Math.abs(option.getDistanceTwo() - option1.getDistanceTwo()) <= deltaDistance)) {
                        if (compareDegrees(degree, degree1) && compareDegrees(degreeTwo, degreeTwo1)) {
                            return "meridian_add_success_all";
                        } else if (compareDegrees(degree, degree1) == true && compareDegrees(degreeTwo, degreeTwo1) == false) {
                            return "meridian_add_success_first";
                        } else if (compareDegrees(degree, degree1) == false && compareDegrees(degreeTwo, degreeTwo1) == true) {
                            return "meridian_add_success_second";
                        } else {
                            return "meridian_add_miss_all";
                        }
                    }
                }
            }

            //для номера орудия = 2
            if (option.getNumberOfGun() == option2.getNumberOfGun()) {
                if ((Math.abs(option.getX() - option2.getX()) > deltaXY) || (Math.abs(option.getY() - option2.getY()) > deltaXY)) {
                    return "meridian_add_miss_all";
                } else {
                    if ((Math.abs(option.getDistanceOne() - option2.getDistanceOne()) > deltaDistance) && (Math.abs(option.getDistanceTwo() - option2.getDistanceTwo()) > deltaDistance)) {
                        return "meridian_add_miss_all";
                    }
                    if ((Math.abs(option.getDistanceOne() - option2.getDistanceOne()) <= deltaDistance) && (Math.abs(option.getDistanceTwo() - option2.getDistanceTwo()) > deltaDistance)) {
                        if (compareDegrees(degree, degree2)) {
                            return "meridian_add_success_first";
                        } else {
                            return "meridian_add_miss_all";
                        }
                    } else if ((Math.abs(option.getDistanceOne() - option2.getDistanceOne()) > deltaDistance) && (Math.abs(option.getDistanceTwo() - option2.getDistanceTwo()) <= deltaDistance)) {
                        if (compareDegrees(degreeTwo, degreeTwo2)) {
                            return "meridian_add_success_second";
                        } else {
                            return "meridian_add_miss_all";
                        }
                    }
                    if ((Math.abs(option.getDistanceOne() - option2.getDistanceOne()) <= deltaDistance) && (Math.abs(option.getDistanceTwo() - option2.getDistanceTwo()) <= deltaDistance)) {
                        if (compareDegrees(degree, degree2) && compareDegrees(degreeTwo, degreeTwo2)) {
                            return "meridian_add_success_all";
                        } else if (compareDegrees(degree, degree2) == true && compareDegrees(degreeTwo, degreeTwo2) == false) {
                            return "meridian_add_success_first";
                        } else if (compareDegrees(degree, degree2) == false && compareDegrees(degreeTwo, degreeTwo2) == true) {
                            return "meridian_add_success_second";
                        } else {
                            return "meridian_add_miss_all";
                        }
                    }
                }
            }
            return "meridian_add_success_all";
        }

        return "redirect:/allresults";

    }

    @GetMapping("/details/{id}")
    public String detailsPage(@PathVariable("id") Long id, Model model) {
        MeridianOptions options = meridianService.getOption(id);
        if (options != null) {
            model.addAttribute("option", options);
            return "meridian_details";
        }
        return "meridian_results";
    }

    @PostMapping("/details")
    public String updateOption(@RequestParam(name = "id") Long id,
                               @RequestParam(name = "gun_number") int gunNumber,
                               @RequestParam(name = "x") double x,
                               @RequestParam(name = "y")  double y,
                               @RequestParam(name = "target_one") int targetOne,
                               @RequestParam(name = "azimuth_one") String azimuthOne,
                               @RequestParam(name = "distance_one") double distanceOne,
                               @RequestParam(name = "target_two") int targetTwo,
                               @RequestParam(name = "azimuth_two") String azimuthTwo,
                               @RequestParam(name = "distance_two") double distanceTwo) {
        MeridianOptions option = meridianService.getOption(id);

        option.setNumberOfGun(gunNumber);
        option.setX(x);
        option.setY(y);

        option.setNumberOfTargetOne(targetOne);
        option.setAzimuthOne(azimuthOne);
        option.setDistanceOne(distanceOne);

        option.setNumberOfTargetTwo(targetTwo);
        option.setAzimuthTwo(azimuthTwo);
        option.setDistanceTwo(distanceTwo);

        meridianService.updateOption(option);
        return "redirect:/allresults";

    }


    @PostMapping("/delete")
    public String deleteOption(@RequestParam(name = "id", defaultValue = "0") Long id) {
        MeridianOptions options = meridianService.getOption(id);
        if (options != null) {
            meridianService.deleteOption(options);
        }
        return "redirect:/allresults";
    }

    private boolean compareDegrees(String degree, String degree1) {
        String newDegree = degree.replace("°", " ").replace("'", " ").replace("\"", " ");
        String newDegree1 = degree1.replace("°", " ").replace("'", " ").replace("\"", " ");
        String[] array = newDegree.split(" ");
        String[] arrayTwo = newDegree1.split(" ");
        int degrees = Integer.parseInt(array[0]);
        int minutes = Integer.parseInt(array[1]);
        int seconds = Integer.parseInt(array[2]);
        int degrees1 = Integer.parseInt(arrayTwo[0]);
        int minutes1 = Integer.parseInt(arrayTwo[1]);
        int seconds1 = Integer.parseInt(arrayTwo[2]);
        int allMinutes = (degrees * 60) + minutes;
        int allMinutes1 = (degrees1 * 60) + minutes1;
        int allSeconds = (degrees * 3600) + (minutes * 60) + (seconds);
        int allSeonds1 = (degrees1 * 3600) + (minutes1 * 60) + (seconds1);
        if (Math.abs(degrees - degrees1) <= 1) {
            if (Math.abs(allMinutes - allMinutes1) <= 1) {
                if (Math.abs(allSeconds - allSeonds1) <= 60) {
                    return true;
                }
            }
        }
        return false;
    }

}
