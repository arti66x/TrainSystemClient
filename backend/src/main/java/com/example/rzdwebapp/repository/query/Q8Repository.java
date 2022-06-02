package com.example.rzdwebapp.repository.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q8FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.QueryFormDto;
import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class Q8Repository extends QueryRepo{
    public Q8Repository(EntityManager em) {
        super(em);
    }

    @Override
    protected String getQuery(QueryFormDto f){
        return QueryLoader.loadQuery("query8.sql");
    }

    @Override
    protected void setParams(Query q, QueryFormDto f){
        Q8FormDto form = (Q8FormDto) f;
        q.setParameter("route", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getRoute()));
    }
}