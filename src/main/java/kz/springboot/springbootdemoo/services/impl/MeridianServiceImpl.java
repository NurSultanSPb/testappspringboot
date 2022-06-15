package kz.springboot.springbootdemoo.services.impl;

import kz.springboot.springbootdemoo.entities.MeridianOptions;
import kz.springboot.springbootdemoo.repositories.MeridianRepository;
import kz.springboot.springbootdemoo.services.MeridianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeridianServiceImpl implements MeridianService {

    @Autowired
    private MeridianRepository meridianRepository;

    @Override
    public MeridianOptions addOption(MeridianOptions option) {
        return meridianRepository.save(option);
    }

    @Override
    public List<MeridianOptions> getAllOptions() {
        return meridianRepository.findAll();
    }

    @Override
    public MeridianOptions getOption(Long id) {
        return meridianRepository.getById(id);
    }

    @Override
    public void deleteOption(MeridianOptions option) {
        meridianRepository.delete(option);
    }

    @Override
    public MeridianOptions updateOption(MeridianOptions option) {
        return meridianRepository.save(option);
    }
}
