package com.example.rzdwebapp.repository.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q9FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.QueryFormDto;
import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;


@Repository
public class Q9Repository extends QueryRepo{
    public Q9Repository(EntityManager em) {
        super(em);
    }

    @Override
    protected String getQuery(QueryFormDto f){
        return QueryLoader.loadQuery("query9.sql");
    }

    @Override
    protected void setParams(Query q, QueryFormDto f){
        Q9FormDto form = (Q9FormDto) f;
        q.setParameter("route", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getRoute()));
        q.setParameter("min_time", new TypedParameterValue(StandardBasicTypes.DATE, form.getMinTime()));
        q.setParameter("max_time", new TypedParameterValue(StandardBasicTypes.DATE, form.getMaxTime()));
        q.setParameter("min_duration", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinDurationMin()));
        q.setParameter("max_duration", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxDurationMin()));
        q.setParameter("min_cost", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinCost()));
        q.setParameter("max_cost", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxCost()));

    }
}