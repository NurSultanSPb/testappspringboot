package kz.springboot.springbootdemoo.controllers;

import kz.springboot.springbootdemoo.entities.Departments;
import kz.springboot.springbootdemoo.entities.Officers;
import kz.springboot.springbootdemoo.entities.Positions;
import kz.springboot.springbootdemoo.services.OfficersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentsController {

    @Autowired
    private OfficersService officersService;

    @GetMapping("")
    public String getAllDepartments(Model model) {
        List<Departments> departments = officersService.getAllDepartments();
        model.addAttribute("departments", departments);

        return "departments";
    }

    @GetMapping("/add")
    public String getForm() {
        return "departments_add";
    }

    @PostMapping("/add")
    public String addDepartment(@RequestParam(name = "department_name") String departmentName) {
        if(departmentName != null) {
            Departments department = new Departments();
            department.setDepartmentName(departmentName);
            officersService.addDepartment(department);
        }
        return "redirect:/departments";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        Departments department = officersService.getDepartment(id);
        model.addAttribute("department", department);
        return "departments_details";
    }

    @PostMapping("/details")
    public String updateDepartment(@RequestParam(name = "id", defaultValue = "0") Long id,
                                 @RequestParam(name = "department") String departmentName) {
        Departments department = officersService.getDepartment(id);
        if (department != null && departmentName != null) {
            department.setDepartmentName(departmentName);
            officersService.updateDepartment(department);
        }
        return "redirect:/departments";
    }

    @PostMapping(value = "/deletedep")
    public String deleteDepartment(@RequestParam(name = "id", defaultValue = "0") Long id) {

        Departments department = officersService.getDepartment(id);

        //Как избежать белого листа с ошибкой
        List<Officers> officers = officersService.getAllOfficers();
        for (Officers off: officers) {
            if (off.getDepartment().getId() == id) {
                return "redirect:/departments";
            }
        }

        if(department != null) {
            officersService.deleteDepartment(department);
        }

        return "redirect:/departments";
    }
}
