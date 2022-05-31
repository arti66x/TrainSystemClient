package com.example.rzdwebapp.data.dto.QueryFormDto;

import lombok.Data;

@Data
public class Q3FormDto extends QueryFormDto{
    private Integer medicalExamYear;
    private Boolean isPassed;
    private Boolean isMale;
    private Integer minAge;
    private Integer maxAge;
    private Integer minSalary;
    private Integer maxSalary;

}
