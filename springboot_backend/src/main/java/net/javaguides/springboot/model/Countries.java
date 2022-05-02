package net.javaguides.springboot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class Countries {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="Country_Id")
    private Integer countryId;

    @Column(name="Country_Code")
    private String countryCode;
    @Column(name="Region")
    private String region;
    @Column(name="IncomeGroup")
    private String incomeGroup;
    @Column(name="TableName")
    private String tableName;
}
