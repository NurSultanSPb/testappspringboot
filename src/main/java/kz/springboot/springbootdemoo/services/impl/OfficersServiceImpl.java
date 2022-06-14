package kz.springboot.springbootdemoo.services.impl;

import kz.springboot.springbootdemoo.entities.Departments;
import kz.springboot.springbootdemoo.entities.Officers;
import kz.springboot.springbootdemoo.entities.Positions;
import kz.springboot.springbootdemoo.entities.Ranks;
import kz.springboot.springbootdemoo.repositories.DepartmentsRepository;
import kz.springboot.springbootdemoo.repositories.OfficersRepository;
import kz.springboot.springbootdemoo.repositories.PositionsRepository;
import kz.springboot.springbootdemoo.repositories.RanksRepository;
import kz.springboot.springbootdemoo.services.OfficersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficersServiceImpl implements OfficersService {

    @Autowired
    private OfficersRepository officersRepository;

    @Autowired
    private DepartmentsRepository departmentsRepository;

    @Autowired
    private PositionsRepository positionsRepository;

    @Autowired
    private RanksRepository ranksRepository;

    @Override
    public Officers addOfficer(Officers officer) {
        return officersRepository.save(officer);
    }

    @Override
    public List<Officers> getAllOfficers() {
        return officersRepository.findAll();
    }

    @Override
    public Officers getOfficer(Long id) {
        return officersRepository.getById(id);
    }

    @Override
    public void deleteOfficer(Officers officer) {
        officersRepository.delete(officer);
    }

    @Override
    public Officers updateOfficer(Officers officer) {
        return officersRepository.save(officer);
    }

    @Override
    public List<Officers> getAllSearchedOfficers(String keyword) {
        return officersRepository.findAllOfficersByKeyword(keyword);
    }

    @Override
    public List<Positions> getAllPositions() {
        return positionsRepository.findAll();
    }

    @Override
    public Positions addPosition(Positions position) {
        return positionsRepository.save(position);
    }

    @Override
    public Positions updatePosition(Positions position) {
        return positionsRepository.save(position);
    }

    @Override
    public Positions getPosition(Long id) {
        return positionsRepository.getById(id);
    }

    @Override
    public void deletePosition(Positions position) {
        positionsRepository.delete(position);
    }

    @Override
    public List<Ranks> getAllRanks() {
        return ranksRepository.findAll();
    }

    @Override
    public Ranks addRank(Ranks rank) {
        return ranksRepository.save(rank);
    }

    @Override
    public Ranks updateRank(Ranks rank) {
        return ranksRepository.save(rank);
    }

    @Override
    public Ranks getRank(Long id) {
        return ranksRepository.getById(id);
    }

    @Override
    public void deleteRank(Ranks rank) {
        ranksRepository.delete(rank);
    }

    @Override
    public List<Departments> getAllDepartments() {
        return departmentsRepository.findAll();
    }

    @Override
    public Departments addDepartment(Departments departments) {
        return departmentsRepository.save(departments);
    }

    @Override
    public Departments updateDepartment(Departments departments) {
        return departmentsRepository.save(departments);
    }

    @Override
    public Departments getDepartment(Long id) {
        return departmentsRepository.getById(id);
    }

    @Override
    public void deleteDepartment(Departments departments) {
        departmentsRepository.delete(departments);
    }
}
