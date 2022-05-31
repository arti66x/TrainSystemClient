package com.example.rzdwebapp.data.dto.QueryFormDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Q1FormDto extends QueryFormDto{
    private Integer specialization;
    private Integer sector;
    private Integer adminOfSector;
    private Integer yearsExperience;
    private Boolean isMale;
    private Integer minAge;
    private Integer maxAge;
    private Integer minSalary;
    private Integer maxSalary;

}
