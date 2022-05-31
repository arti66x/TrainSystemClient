package com.example.rzdwebapp.controller.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q4FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.Q5FormDto;
import com.example.rzdwebapp.data.dto.QueryPagedResponse;
import com.example.rzdwebapp.repository.crud.TrainRepo;
import com.example.rzdwebapp.repository.query.Q4Repository;
import com.example.rzdwebapp.repository.query.Q5Repository;
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
@RequestMapping("/query/5")
public class Q5Controller implements QueryController<Q5FormDto> {
    @Autowired
    private Q5Repository repo;
    @Autowired
    private TrainRepo trainRepo;
    @Override
    public List<String> getFields() {
        Field[] fields = Q5FormDto.class.getDeclaredFields();
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
    public QueryPagedResponse selectQuery(Q5FormDto form) {
        return repo.select(form);
    }
}