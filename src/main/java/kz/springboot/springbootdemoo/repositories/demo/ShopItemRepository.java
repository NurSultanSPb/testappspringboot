package kz.springboot.springbootdemoo.repositories.demo;

import kz.springboot.springbootdemoo.entities.demo.ShopItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ShopItemRepository extends JpaRepository<ShopItems, Long> {

    List<ShopItems> findAllByAmountGreaterThanOrderByPriceDesc(int amount);
    ShopItems findByIdAndAmountGreaterThan(Long id, int amount);
}
