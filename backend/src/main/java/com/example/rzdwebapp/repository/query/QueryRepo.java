package com.example.rzdwebapp.repository.query;

import com.example.rzdwebapp.data.dto.QueryFormDto.QueryFormDto;
import com.example.rzdwebapp.data.dto.QueryPagedResponse;
import lombok.AllArgsConstructor;
import org.hibernate.query.internal.NativeQueryImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public abstract class QueryRepo {
    @PersistenceContext
    private final EntityManager em;

    @Transactional
    public QueryPagedResponse select(QueryFormDto form){
        String queryStr = getQuery(form);

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

        return new QueryPagedResponse(result,((count-1)/ form.getSize()+1));
    }

    abstract String getQuery(QueryFormDto form);
    abstract void setParams(Query q,  QueryFormDto form);
}
