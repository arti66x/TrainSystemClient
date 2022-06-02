package com.example.rzdwebapp.repository.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q4_2FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.Q5FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.QueryFormDto;
import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class Q5Repository extends QueryRepo{
    public Q5Repository(EntityManager em) {
        super(em);
    }

    @Override
    protected String getQuery(QueryFormDto f){
        return QueryLoader.loadQuery("query5.sql");
    }

    @Override
    protected void setParams(Query q, QueryFormDto f){
        Q5FormDto form = (Q5FormDto) f;
        q.setParameter("min_tech", new TypedParameterValue(StandardBasicTypes.DATE, form.getMinTechDate()));
        q.setParameter("max_tech", new TypedParameterValue(StandardBasicTypes.DATE, form.getMaxTechDate()));
        q.setParameter("min_rep", new TypedParameterValue(StandardBasicTypes.DATE, form.getMinRepairDate()));
        q.setParameter("max_rep", new TypedParameterValue(StandardBasicTypes.DATE, form.getMaxRepairDate()));
        q.setParameter("min_rep_count", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinRepairCount()));
        q.setParameter("max_rep_count", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxRepairCount()));
        q.setParameter("min_trips", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinTripsBeforeRepair()));
        q.setParameter("max_trips", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxTripsBeforeRepair()));
        q.setParameter("min_age", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinAge()));
        q.setParameter("max_age", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxAge()));

    }
}