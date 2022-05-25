package com.example.rzdwebapp.controller.query;

import com.example.rzdwebapp.data.dto.QueryPagedResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public interface QueryController<FormDto> {
    @GetMapping("/fields")
    List<String> getFields();

    @GetMapping("/select-list")
    Map<String,List> getSelectList();


    @PutMapping
    QueryPagedResponse selectQuery (@RequestBody FormDto formDto);
}