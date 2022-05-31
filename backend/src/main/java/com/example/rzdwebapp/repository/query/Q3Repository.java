package com.example.rzdwebapp.repository.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q1FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.Q2FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.Q3FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.QueryFormDto;
import com.example.rzdwebapp.data.dto.QueryPagedResponse;
import lombok.AllArgsConstructor;
import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository

public class Q3Repository extends QueryRepo{

    public Q3Repository(EntityManager em) {
        super(em);
    }

    @Override
    protected String getQuery(QueryFormDto f){
        Q3FormDto form = (Q3FormDto) f;
        String queryStr;
        if(form.getMedicalExamYear() != null)
            queryStr = QueryLoader.loadQuery("query3.sql");
        else
            queryStr = QueryLoader.loadQuery("query3_2.sql");
        return queryStr;
    }
    @Override
    protected void setParams(Query q, QueryFormDto f){
        Q3FormDto form = (Q3FormDto) f;
        if(form.getMedicalExamYear() != null) {
            q.setParameter("year", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMedicalExamYear()));
            q.setParameter("is_passed", new TypedParameterValue(StandardBasicTypes.BOOLEAN, form.getIsPassed()));
        }
        q.setParameter("is_male", new TypedParameterValue(StandardBasicTypes.BOOLEAN, form.getIsMale()));
        q.setParameter("min_age", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinAge()));
        q.setParameter("max_age", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxAge()));
        q.setParameter("min_salary", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinSalary()));
        q.setParameter("max_salary", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxSalary()));

    }
}

