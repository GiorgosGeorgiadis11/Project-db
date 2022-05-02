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

public class AllData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="Country_Id")
    private Integer countryId;

    @Column(name="Indicator_Id")
    private String indicatorId;

    @Column(name="Year")
    private String year;

    @Column(name="value")
    private String value;
}
