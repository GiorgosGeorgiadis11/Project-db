package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Indicators;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicatorsRepository extends JpaRepository<Indicators,Integer> {
}
