package kz.springboot.springbootdemoo.repositories;

import kz.springboot.springbootdemoo.entities.Officers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OfficersRepository extends JpaRepository<Officers, Long> {

}
