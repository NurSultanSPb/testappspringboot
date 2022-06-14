package kz.springboot.springbootdemoo.controllers;

import kz.springboot.springbootdemoo.entities.Officers;
import kz.springboot.springbootdemoo.entities.Positions;
import kz.springboot.springbootdemoo.entities.Ranks;
import kz.springboot.springbootdemoo.services.OfficersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ranks")
public class RanksController {

    @Autowired
    private OfficersService officersService;

    @GetMapping("")
    public String getAllRanks(Model model) {
        List<Ranks> ranks = officersService.getAllRanks();
        model.addAttribute("ranks", ranks);

        return "ranks";
    }

    @GetMapping("/add")
    public String getForm() {
        return "ranks_add";
    }

    @PostMapping("/add")
    public String addRank(@RequestParam(name = "rank_name") String rankName) {
        if(rankName != null) {
            Ranks rank = new Ranks();
            rank.setRankName(rankName);
            officersService.addRank(rank);
        }
        return "redirect:/ranks";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        Ranks rank = officersService.getRank(id);
        model.addAttribute("rank", rank);
        return "ranks_details";
    }

    @PostMapping("/details")
    public String updateRank(@RequestParam(name = "id", defaultValue = "0") Long id,
                                 @RequestParam(name = "rank") String rankName) {
        Ranks rank = officersService.getRank(id);
        if (rank != null && rankName != null) {
            rank.setRankName(rankName);
            officersService.updateRank(rank);
        }
        return "redirect:/ranks";
    }

    @PostMapping(value = "/deleterank")
    public String deleteRank(@RequestParam(name = "id", defaultValue = "0") Long id) {

        Ranks rank = officersService.getRank(id);

        //Как избежать белого листа с ошибкой
        List<Officers> officers = officersService.getAllOfficers();
        for (Officers off: officers) {
            if (off.getRank().getId() == id) {
                return "redirect:/ranks";
            }
        }

        if (rank != null) {
            officersService.deleteRank(rank);
        }

        return "redirect:/ranks";
    }
}
