package kz.springboot.springbootdemoo.services;

import kz.springboot.springbootdemoo.entities.MeridianOptions;
import kz.springboot.springbootdemoo.entities.Officers;

import java.util.List;

public interface MeridianService {
    MeridianOptions addOption(MeridianOptions option);
    List<MeridianOptions> getAllOptions();
    MeridianOptions getOption(Long id);
    void deleteOption(MeridianOptions option);
    MeridianOptions updateOption(MeridianOptions option);
}
