package kz.springboot.springbootdemoo.repositories;

import kz.springboot.springbootdemoo.entities.MeridianOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MeridianRepository extends JpaRepository<MeridianOptions, Long> {

}
