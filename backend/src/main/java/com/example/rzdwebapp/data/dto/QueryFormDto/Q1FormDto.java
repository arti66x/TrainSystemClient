package com.example.rzdwebapp.data.dto.QueryFormDto;

import lombok.Data;

@Data
public class Q1FormDto {
    private Integer specialization;
    private Integer sector;
    private Integer adminOfSector;
    private Integer yearsExperience;
    private Boolean isMale;
    private Integer age;
    private Integer minSalary;
    private Integer maxSalary;

    private Integer page;
    private Integer size;
}
