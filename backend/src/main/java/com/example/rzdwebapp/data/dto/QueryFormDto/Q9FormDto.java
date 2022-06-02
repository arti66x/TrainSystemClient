package com.example.rzdwebapp.data.dto.QueryFormDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class Q9FormDto extends QueryFormDto{
    Integer route;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date minTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date maxTime;

    Integer minDurationMin;
    Integer maxDurationMin;

    Integer minCost;
    Integer maxCost;
}