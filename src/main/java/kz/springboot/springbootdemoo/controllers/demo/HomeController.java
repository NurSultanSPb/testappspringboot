package kz.springboot.springbootdemoo.controllers.demo;

import kz.springboot.springbootdemoo.entities.demo.Country;
import kz.springboot.springbootdemoo.entities.demo.ShopItems;
import kz.springboot.springbootdemoo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }
    
    @GetMapping(value = "/")
    public String index(Model model) {
        List<ShopItems> items = itemService.getAllItems();
        model.addAttribute("tovary", items);

        List<Country> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        return "index";
    }

    @PostMapping(value = "/additem")
    public String addItem(@RequestParam(name = "item_name", defaultValue = "No Item") String name,
                          @RequestParam(name = "item_price", defaultValue = "0") int price,
                          @RequestParam(name = "item_amount", defaultValue = "0") int amount,
                          @RequestParam(name = "country_id", defaultValue = "0") Long country_id) {

        Country country = itemService.getCountry(country_id);
        if (country != null) {
            ShopItems item = new ShopItems();

            item.setName(name);
            item.setPrice(price);
            item.setAmount(amount);
            item.setCountry(country);

            itemService.addItem(item);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/details/{idshka}")
    public String details(Model model, @PathVariable(name = "idshka") Long id) {
        ShopItems item = itemService.getItem(id);
        model.addAttribute("item", item);
        return "details";
    }

    @PostMapping(value = "/saveitem")
    public String saveItem(@RequestParam(name = "id", defaultValue = "0") Long id,
                          @RequestParam(name = "item_name", defaultValue = "No Item") String name,
                          @RequestParam(name = "item_price", defaultValue = "0") int price,
                          @RequestParam(name = "item_amount", defaultValue = "0") int amount) {
        ShopItems item = itemService.getItem(id);
        if(item!=null) {
            item.setName(name);
            item.setPrice(price);
            item.setAmount(amount);
            itemService.saveItem(item);
        }

        return "redirect:/";
    }

    @PostMapping(value = "/deleteitem")
    public String deleteItem(@RequestParam(name = "id", defaultValue = "0") Long id) {
        ShopItems item = itemService.getItem(id);
        if(item!=null) {
            itemService.deleteItem(item);
        }

        return "redirect:/";
    }
}
