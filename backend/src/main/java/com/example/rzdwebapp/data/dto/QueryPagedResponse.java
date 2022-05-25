package com.example.rzdwebapp.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class QueryPagedResponse {
    private List<Map<String,Object>>  entities;
    private Integer countPages;
}
