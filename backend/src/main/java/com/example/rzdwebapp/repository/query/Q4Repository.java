package com.example.rzdwebapp.repository.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q4FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.QueryFormDto;
import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;


@Repository
public class Q4Repository extends QueryRepo{
    public Q4Repository(EntityManager em) {
        super(em);
    }

    @Override
    protected String getQuery(QueryFormDto f){
        return QueryLoader.loadQuery("query4.sql");
    }

    @Override
    protected void setParams(Query q, QueryFormDto f){
        Q4FormDto form = (Q4FormDto) f;
        q.setParameter("station_id", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getStation()));
        q.setParameter("time", new TypedParameterValue(StandardBasicTypes.DATE, form.getTime()));
    }
}