package com.example.rzdwebapp.controller.query;


import com.example.rzdwebapp.data.dto.QueryFormDto.Q1FormDto;
import com.example.rzdwebapp.data.dto.QueryPagedResponse;
import com.example.rzdwebapp.data.entity.Staff;
import com.example.rzdwebapp.repository.crud.SpecializationRepo;
import com.example.rzdwebapp.repository.crud.StaffRepo;
import com.example.rzdwebapp.repository.query.Q1Repository;
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
@RequestMapping("/query/1")
public class Q1Controller implements QueryController<Q1FormDto> {
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private SpecializationRepo specializationRepo;
    @Autowired
    private Q1Repository repo;
//    private StaffRepo repo;
    @Override
    public List<String> getFields() {
        Field[] fields = Q1FormDto.class.getDeclaredFields();
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
        myMap.put("specialization", specializationRepo.findAll());
        myMap.put("sector", staffRepo.getSectorList());
        myMap.put("adminOfSector", staffRepo.getSectorList());
        myMap.put("isMale",List.of(true,false));
        return myMap;
    }

    @Override
    public QueryPagedResponse selectQuery(Q1FormDto form) {
        return repo.select(form);
    }
}
