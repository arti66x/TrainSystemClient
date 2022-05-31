package com.example.rzdwebapp.data.dto.QueryFormDto;

import lombok.Data;

@Data
public class Q2FormDto extends QueryFormDto{
    private Integer sector;
    private Integer train;
    private Integer minAge;
    private Integer maxAge;
    private Integer minSalary;
    private Integer maxSalary;
    private Integer minAvgSalary;
    private Integer maxAvgSalary;
    private Integer minSumSalary;
    private Integer maxSumSalary;

}
