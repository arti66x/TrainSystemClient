package com.example.rzdwebapp.data.dto.QueryFormDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
public class Q4_2FormDto extends QueryFormDto{
    @NotNull
    private Integer station;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date minTimeArrive;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date maxTimeArrive;
}
