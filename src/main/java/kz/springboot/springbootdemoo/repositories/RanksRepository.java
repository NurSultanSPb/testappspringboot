package kz.springboot.springbootdemoo.repositories;

import kz.springboot.springbootdemoo.entities.Ranks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RanksRepository extends JpaRepository<Ranks, Long> {

}
