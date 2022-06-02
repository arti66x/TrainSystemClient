package com.example.rzdwebapp.repository.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q4FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.Q4_2FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.QueryFormDto;
import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class Q4_2Repository extends QueryRepo{
    public Q4_2Repository(EntityManager em) {
        super(em);
    }

    @Override
    protected String getQuery(QueryFormDto f){
        return QueryLoader.loadQuery("query4_2.sql");
    }

    @Override
    protected void setParams(Query q, QueryFormDto f){
        Q4_2FormDto form = (Q4_2FormDto) f;
        q.setParameter("station_id", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getStation()));
        q.setParameter("min_time", new TypedParameterValue(StandardBasicTypes.DATE, form.getMinTimeArrive()));
        q.setParameter("max_time", new TypedParameterValue(StandardBasicTypes.DATE, form.getMaxTimeArrive()));

    }
}