package kz.springboot.springbootdemoo.services;

import kz.springboot.springbootdemoo.entities.Departments;
import kz.springboot.springbootdemoo.entities.Officers;
import kz.springboot.springbootdemoo.entities.Positions;
import kz.springboot.springbootdemoo.entities.Ranks;

import java.util.List;

public interface OfficersService {

    Officers addOfficer(Officers officer);
    List<Officers> getAllOfficers();
    Officers getOfficer(Long id);
    void deleteOfficer(Officers officer);
    Officers updateOfficer(Officers officer);

    List<Positions> getAllPositions();
    Positions addPosition(Positions position);
    Positions updatePosition(Positions position);
    Positions getPosition(Long id);
    void deletePosition(Positions position);

    List<Ranks> getAllRanks();
    Ranks addRank(Ranks rank);
    Ranks updateRank(Ranks rank);
    Ranks getRank(Long id);
    void deleteRank(Ranks rank);

    List<Departments> getAllDepartments();
    Departments addDepartment(Departments departments);
    Departments updateDepartment(Departments departments);
    Departments getDepartment(Long id);
    void deleteDepartment(Departments departments);
}
