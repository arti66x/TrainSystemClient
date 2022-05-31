package com.example.rzdwebapp.repository.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q1FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.Q2FormDto;
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
public class Q2Repository extends QueryRepo{
    public Q2Repository(EntityManager em) {
        super(em);
    }
    @Override
    protected String getQuery(QueryFormDto f){
        return QueryLoader.loadQuery("query2.sql");
    }
    @Override
    protected void setParams(Query q, QueryFormDto f){
        Q2FormDto form = (Q2FormDto) f;

        q.setParameter("sector_id", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getSector()));
        q.setParameter("train_id", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getTrain()));
        q.setParameter("min_age", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinAge()));
        q.setParameter("max_age", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxAge()));
        q.setParameter("min_salary", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinSalary()));
        q.setParameter("max_salary", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxSalary()));
        q.setParameter("min_avg_salary", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinAvgSalary()));
        q.setParameter("max_avg_salary", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxAvgSalary()));
        q.setParameter("min_sum_salary", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinSumSalary()));
        q.setParameter("max_sum_salary", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxSumSalary()));

    }
}
