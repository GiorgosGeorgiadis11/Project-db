package net.javaguides.springboot.service;

import net.javaguides.springboot.repository.IndicatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class IndicatorsServiceImpl implements IndicatorsService{

    @Autowired
    private IndicatorsRepository indicatorsRepository;

}
