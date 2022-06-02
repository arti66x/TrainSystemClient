package com.example.rzdwebapp.repository.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q4_3FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.QueryFormDto;
import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class Q4_3Repository extends QueryRepo{
    public Q4_3Repository(EntityManager em) {
        super(em);
    }

    @Override
    protected String getQuery(QueryFormDto f){
        return QueryLoader.loadQuery("query4_3.sql");
    }

    @Override
    protected void setParams(Query q, QueryFormDto f){
        Q4_3FormDto form = (Q4_3FormDto) f;
        q.setParameter("station", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getDefaultStation()));
        q.setParameter("min_trips", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinTrips()));
        q.setParameter("max_trips", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxTrips()));
    }
}