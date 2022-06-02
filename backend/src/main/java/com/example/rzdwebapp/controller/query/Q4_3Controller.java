package com.example.rzdwebapp.controller.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q4_3FormDto;
import com.example.rzdwebapp.data.dto.QueryPagedResponse;
import com.example.rzdwebapp.repository.crud.TrainRepo;
import com.example.rzdwebapp.repository.query.Q4_3Repository;
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
@RequestMapping("/query/4_3")
public class Q4_3Controller implements QueryController<Q4_3FormDto> {
    @Autowired
    private Q4_3Repository repo;
    @Autowired
    private TrainRepo trainRepo;
    @Override
    public List<String> getFields() {
        Field[] fields = Q4_3FormDto.class.getDeclaredFields();
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
        myMap.put("defaultStation",trainRepo.getStationList());
        return myMap;
    }

    @Override
    public QueryPagedResponse selectQuery(Q4_3FormDto form) {
        return repo.select(form);
    }
}