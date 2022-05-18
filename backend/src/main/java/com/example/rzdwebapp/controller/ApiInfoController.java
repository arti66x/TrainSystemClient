package com.example.rzdwebapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api-info")
public class ApiInfoController {
    @GetMapping("/table-list")
    public List<String> getTableList() {
        return List.of(
                "specialization","staff","station","route","sector",
                "brigade","train","repair","techinspection","trainschedule",
                "routestation","ticket","canceledrun","delay");
    }
    @GetMapping("/query-list")
    public List<String> getQueryList() {
        return List.of(
                "query-1","query-2");
    }
}
