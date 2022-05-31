package com.example.rzdwebapp.data.dto.QueryFormDto;

import lombok.Data;

@Data
public class Q5FormDto extends QueryFormDto{
    Integer defaultStation;
    Integer minTrips;
    Integer maxTrips;
}
