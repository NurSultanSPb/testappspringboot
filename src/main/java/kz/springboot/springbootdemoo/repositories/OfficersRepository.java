package kz.springboot.springbootdemoo.repositories;

import kz.springboot.springbootdemoo.entities.Officers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OfficersRepository extends JpaRepository<Officers, Long> {
    @Query("SELECT o FROM Officers o WHERE o.name LIKE %?1%")
    List<Officers> findAllOfficersByKeyword(String keyword);
}
