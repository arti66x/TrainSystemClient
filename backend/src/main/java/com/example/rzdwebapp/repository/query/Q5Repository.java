package com.example.rzdwebapp.repository.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q4FormDto;
import com.example.rzdwebapp.data.dto.QueryFormDto.Q5FormDto;
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
        q.setParameter("station", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getDefaultStation()));
        q.setParameter("min_trips", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinTrips()));
        q.setParameter("max_trips", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxTrips()));
    }
}