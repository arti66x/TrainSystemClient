package com.example.rzdwebapp.repository.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q1FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.QueryFormDto;
import com.example.rzdwebapp.data.dto.QueryPagedResponse;
import lombok.AllArgsConstructor;
import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.ShortType;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class Q1Repository extends QueryRepo{
    public Q1Repository(EntityManager em) {
        super(em);
    }

    @Override
    protected String getQuery(QueryFormDto f){
        Q1FormDto form = (Q1FormDto) f;
        String queryStr;
        if(form.getAdminOfSector() == null)
            queryStr = QueryLoader.loadQuery("query1.sql");
        else
            queryStr = QueryLoader.loadQuery("query1_2.sql");
        return queryStr;
    }
    @Override
    protected void setParams(Query q, QueryFormDto f){
        Q1FormDto form = (Q1FormDto) f;
        if(form.getAdminOfSector() == null) {
            q.setParameter("spec_id", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getSpecialization()));
            q.setParameter("sector_id", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getSector()));
            q.setParameter("years_experience", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getYearsExperience()));
            q.setParameter("is_male", new TypedParameterValue(StandardBasicTypes.BOOLEAN, form.getIsMale()));
            q.setParameter("min_age", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinAge()));
            q.setParameter("max_age", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxAge()));
            q.setParameter("min_salary", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinSalary()));
            q.setParameter("max_salary", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxSalary()));
        } else
            q.setParameter("sect_admin", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getAdminOfSector()));

    }
}

