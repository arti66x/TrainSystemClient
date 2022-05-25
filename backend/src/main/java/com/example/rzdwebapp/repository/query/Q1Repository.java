package com.example.rzdwebapp.repository.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.Q1FormDto;
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
@AllArgsConstructor
public class Q1Repository {
    @PersistenceContext
    private final EntityManager em;

    public QueryPagedResponse select(Q1FormDto form){
        String queryStr;
        if(form.getAdminOfSector() == null)
            queryStr = QueryLoader.loadQuery("query1.sql");
        else
            queryStr = QueryLoader.loadQuery("query1_2.sql");
        String countQueryStr = "select count(*) from (" + queryStr + ") as res";

        queryStr = queryStr + " limit " + form.getSize() + " offset "+ (form.getPage()*form.getSize());


        Query query = em.createNativeQuery(queryStr);
        setParams(query,form);

        NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
        nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        List<Map<String,Object>> result = nativeQuery.getResultList();

        Query countQuery = em.createNativeQuery(countQueryStr);
        setParams(countQuery,form);
        Integer count = ((Number) countQuery.getSingleResult()).intValue();
        return new QueryPagedResponse(result,(count/ form.getSize()+1));

    }

    private void setParams(Query q, Q1FormDto form){
        if(form.getAdminOfSector() == null) {
            q.setParameter("spec_id", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getSpecialization()));
            q.setParameter("sector_id", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getSector()));
            q.setParameter("years_experience", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getYearsExperience()));
            q.setParameter("is_male", new TypedParameterValue(StandardBasicTypes.BOOLEAN, form.getIsMale()));
            q.setParameter("age", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getAge()));
            q.setParameter("min_salary", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMinSalary()));
            q.setParameter("max_salary", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getMaxSalary()));
        } else
            q.setParameter("sect_admin", new TypedParameterValue(StandardBasicTypes.INTEGER, form.getAdminOfSector()));

    }


}

