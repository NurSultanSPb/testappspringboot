package kz.springboot.springbootdemoo.controllers;

import kz.springboot.springbootdemoo.entities.Departments;
import kz.springboot.springbootdemoo.entities.Officers;
import kz.springboot.springbootdemoo.entities.Positions;
import kz.springboot.springbootdemoo.entities.Ranks;
import kz.springboot.springbootdemoo.services.OfficersService;
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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class OfficersController {

    @Autowired
    private OfficersService officersService;

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/img";

    @GetMapping(value = "")
    public String getAllOfficers(Model model) {

        List<Officers> officers = officersService.getAllOfficers();
        model.addAttribute("officers", officers);

        List<Positions> positions = officersService.getAllPositions();
        model.addAttribute("positions", positions);

        List<Ranks> ranks = officersService.getAllRanks();
        model.addAttribute("ranks", ranks);

        List<Departments> departments = officersService.getAllDepartments();
        model.addAttribute("departments", departments);

        return "officers";

    }

    @GetMapping(value = "/about")
    public String aboutUs() {
        return "officers_about";
    }

    @GetMapping(value = "/add")
    public String getForm(Model model) {

        List<Positions> positions = officersService.getAllPositions();
        model.addAttribute("positions", positions);

        List<Ranks> ranks = officersService.getAllRanks();
        model.addAttribute("ranks", ranks);

        List<Departments> departments = officersService.getAllDepartments();
        model.addAttribute("departments", departments);

        return "officers_add";

    }

    @PostMapping(value = "/add")
    public String addOfficer(@RequestParam(name = "personal_number", defaultValue = "0") int personalNumber,
                          @RequestParam(name = "surname", defaultValue = " ") String surname,
                          @RequestParam(name = "name", defaultValue = " ") String name,
                          @RequestParam(name = "middle_name", defaultValue = " ") String middleName,
                          @RequestParam(name = "date_of_birth")
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                          @RequestParam(name = "position", defaultValue = "0") Long position_id,
                          @RequestParam(name = "rank", defaultValue = "0") Long rank_id,
                          @RequestParam(name = "department", defaultValue = "0") Long department_id,
                          @RequestParam(name = "date_of_sign")
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfSign,
                          @RequestParam(name = "contract_period") int contractPeriod,
                             @RequestParam(name = "fileImage") MultipartFile file) {

            Positions position = officersService.getPosition(position_id);
            Ranks rank = officersService.getRank(rank_id);
            Departments department = officersService.getDepartment(department_id);

            if (position != null && rank != null && department != null) {
                Officers officer = new Officers();

                officer.setPersonalNumber(personalNumber);
                officer.setSurname(surname);
                officer.setName(name);
                officer.setMiddleName(middleName);
                officer.setDateOfBirth(dateOfBirth);

                officer.setPosition(position);
                officer.setRank(rank);
                officer.setDepartment(department);

                officer.setDateOfSign(dateOfSign);
                officer.setContractPeriod(contractPeriod);

                //dealing with photo
                Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
                officer.setFileName(file.getOriginalFilename());
                try {
                    Files.write(fileNameAndPath, file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                officersService.addOfficer(officer);
            }

        return "redirect:/";
    }

    @GetMapping(value = "/details/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {

        Officers officer = officersService.getOfficer(id);
        model.addAttribute("officer", officer);

        List<Positions> positions = officersService.getAllPositions();
        model.addAttribute("positions", positions);

        List<Ranks> ranks = officersService.getAllRanks();
        model.addAttribute("ranks", ranks);

        List<Departments> departments = officersService.getAllDepartments();
        model.addAttribute("departments", departments);

        return "officers_details";

    }

    @PostMapping(value = "/details")
    public String updateOfficer(@RequestParam(name = "id", defaultValue = "0") Long id,
                                @RequestParam(name = "personal_number", defaultValue = "0") int personalNumber,
                                @RequestParam(name = "surname", defaultValue = " ") String surname,
                                @RequestParam(name = "name", defaultValue = " ") String name,
                                @RequestParam(name = "middle_name", defaultValue = " ") String middleName,
                                @RequestParam(name = "date_of_birth")
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                                @RequestParam(name = "position", defaultValue = "0") Long position_id,
                                @RequestParam(name = "rank", defaultValue = "0") Long rank_id,
                                @RequestParam(name = "department", defaultValue = "0") Long department_id,
                                @RequestParam(name = "date_of_sign")
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfSign,
                                @RequestParam(name = "contract_period") int contractPeriod) {

        Officers officer = officersService.getOfficer(id);

        Positions position = officersService.getPosition(position_id);
        Ranks rank = officersService.getRank(rank_id);
        Departments department = officersService.getDepartment(department_id);

        if(officer!=null && position != null && rank != null && department != null) {
            officer.setPersonalNumber(personalNumber);
            officer.setSurname(surname);
            officer.setName(name);
            officer.setMiddleName(middleName);
            officer.setDateOfBirth(dateOfBirth);

            officer.setPosition(position);
            officer.setRank(rank);
            officer.setDepartment(department);

            officer.setDateOfSign(dateOfSign);
            officer.setContractPeriod(contractPeriod);

            officersService.updateOfficer(officer);
        }

        return "redirect:/";
    }

    @PostMapping(value = "/deleteofficer")
    public String deleteOfficer(@RequestParam(name = "id", defaultValue = "0") Long id) {

        Officers officer = officersService.getOfficer(id);
        if(officer != null) {
            officersService.deleteOfficer(officer);
        }

        return "redirect:/";
    }

}
