package kz.springboot.springbootdemoo.services;

import kz.springboot.springbootdemoo.entities.demo.Country;
import kz.springboot.springbootdemoo.entities.demo.ShopItems;

import java.util.List;

public interface ItemService {

    ShopItems addItem(ShopItems item);
    List<ShopItems> getAllItems();
    ShopItems getItem(Long id);
    void deleteItem(ShopItems item);
    ShopItems saveItem(ShopItems item);

    List<Country> getAllCountries();
    Country addCountry(Country country);
    Country saveCountry(Country country);
    Country getCountry(Long id);
}
