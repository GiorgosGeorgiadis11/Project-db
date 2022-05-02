package net.javaguides.springboot.service;

import net.javaguides.springboot.repository.AllDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AllDataServiceImpl implements AllDataService{

    @Autowired
    private AllDataRepository allDataRepository;


}
