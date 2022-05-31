package com.example.rzdwebapp.data.dto.QueryFormDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Q4FormDto extends QueryFormDto{
    @NotNull
    private Integer station;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date time;

}
