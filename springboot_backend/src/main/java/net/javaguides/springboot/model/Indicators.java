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

public class Indicators {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="Indicator_Id")
    private Integer indicatorId;

    @Column(name="INDICATOR_CODE")
    private String indicatorCode;

    @Column(name="INDICATOR_NAME")
    private String indicatorName;

    @Column(name="SOURCE_NOTE")
    private String sourceNote;

    @Column(name="SOURCE_ORGANIZATION")
    private String sourceOrganization;

}
