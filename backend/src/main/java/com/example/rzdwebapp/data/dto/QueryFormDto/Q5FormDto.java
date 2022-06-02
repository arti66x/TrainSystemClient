package com.example.rzdwebapp.data.dto.QueryFormDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Q5FormDto extends QueryFormDto{
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date minTechDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date maxTechDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date minRepairDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date maxRepairDate;
    private Integer minRepairCount;
    private Integer maxRepairCount;
    private Integer minTripsBeforeRepair;
    private Integer maxTripsBeforeRepair;
    private Integer minAge;
    private Integer maxAge;
}