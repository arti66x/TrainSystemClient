package com.example.rzdwebapp.controller.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q8FormDto;
import com.example.rzdwebapp.data.dto.QueryPagedResponse;
import com.example.rzdwebapp.repository.crud.TrainScheduleRepo;
import com.example.rzdwebapp.repository.query.Q8Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/query/8")
public class Q8Controller implements QueryController<Q8FormDto> {
    @Autowired
    private Q8Repository repo;
    @Autowired
    private TrainScheduleRepo trainScheduleRepo;
    @Override
    public List<String> getFields() {
        Field[] fields = Q8FormDto.class.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields){
            if(!field.getName().equals("page") && !field.getName().equals("size"))
                fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    @Override
    public Map<String, List> getSelectList() {
        Map<String,List> myMap = new HashMap<>();
        myMap.put("route",trainScheduleRepo.getRouteList());
        return myMap;
    }

    @Override
    public QueryPagedResponse selectQuery(Q8FormDto form) {
        return repo.select(form);
    }
}