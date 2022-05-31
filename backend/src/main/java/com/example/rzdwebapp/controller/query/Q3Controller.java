package com.example.rzdwebapp.controller.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q2FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.Q3FormDto;
import com.example.rzdwebapp.data.dto.QueryPagedResponse;
import com.example.rzdwebapp.repository.crud.SpecializationRepo;
import com.example.rzdwebapp.repository.crud.StaffRepo;
import com.example.rzdwebapp.repository.query.Q2Repository;
import com.example.rzdwebapp.repository.query.Q3Repository;
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
@RequestMapping("/query/3")
public class Q3Controller implements QueryController<Q3FormDto> {
    @Autowired
    private Q3Repository repo;
    @Override
    public List<String> getFields() {
        Field[] fields = Q3FormDto.class.getDeclaredFields();
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
        myMap.put("isPassed",List.of(true,false));
        myMap.put("isMale",List.of(true,false));
        return myMap;
    }

    @Override
    public QueryPagedResponse selectQuery(Q3FormDto form) {
        return repo.select(form);
    }
}