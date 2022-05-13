package com.db.project.repository;

import com.db.project.dto.graphDataDTO;
import com.db.project.model.AllData;
import com.db.project.model.AllDataPKID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllDataRepository extends JpaRepository<AllData, AllDataPKID> {

    @Query(value = "SELECT c.Table_Name,i.Indicator_Name,a.Year,a.Value\n"+
            " FROM AllData as a\n" +
            "INNER JOIN countries as c ON a.Country_Id=c.Country_Id\n" +
            "INNER JOIN Indicators as i ON a.Indicator_Id=i.Indicator_Id\n" +
            "WHERE a.Country_Id=:Country_Id AND a.Indicator_Id = :Indicator_Id\n" +
            "AND a.Year=:Year",nativeQuery = true)
    public List<graphDataDTO> getGraphData(Integer Country_Id,Integer Indicator_Id,Integer Year);

    @Query(value = "SELECT Year FROM AllData WHERE Country_Id=1 AND Indicator_Id=1",nativeQuery = true)
    public List<Integer> getAllYears();

}
