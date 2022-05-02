package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.AllData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllDataRepository extends JpaRepository<AllData,Integer> {
}
