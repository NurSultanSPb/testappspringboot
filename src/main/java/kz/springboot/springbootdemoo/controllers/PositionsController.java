package kz.springboot.springbootdemoo.controllers;

import kz.springboot.springbootdemoo.entities.Officers;
import kz.springboot.springbootdemoo.entities.Positions;
import kz.springboot.springbootdemoo.services.OfficersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/officers/positions")
public class PositionsController {

    @Autowired
    private OfficersService officersService;

    @GetMapping("")
    public String getAllPositions(Model model) {
        List<Positions> positions = officersService.getAllPositions();
        model.addAttribute("positions", positions);

        return "positions";
    }

    @GetMapping("/add")
    public String getForm() {
        return "positions_add";
    }

    @PostMapping("/add")
    public String addPosition(@RequestParam(name = "position_name") String positionName) {
        if(positionName != null) {
            Positions position = new Positions();
            position.setPositionName(positionName);
            officersService.addPosition(position);
        }
        return "redirect:/officers/positions";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        Positions position = officersService.getPosition(id);
        model.addAttribute("position", position);
        return "positions_details";
    }

    @PostMapping("/details")
    public String updatePosition(@RequestParam(name = "id", defaultValue = "0") Long id,
                                 @RequestParam(name = "position") String positionName) {
        Positions position = officersService.getPosition(id);
        if (position != null && positionName != null) {
            position.setPositionName(positionName);
            officersService.updatePosition(position);
        }
        return "redirect:/officers/positions";
    }

    @PostMapping(value = "/deleteposition")
    public String deleteOfficer(@RequestParam(name = "id", defaultValue = "0") Long id) {

        Positions position = officersService.getPosition(id);

        if(position != null) {
            officersService.deletePosition(position);
        }

        return "redirect:/officers/positions";
    }
}
