package com.example.rzdwebapp.repository.query;

import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.hibernate.transform.ResultTransformer;


public class AliasToEntityMapResultTransformer extends AliasedTupleSubsetResultTransformer {

    public static final AliasToEntityMapResultTransformer INSTANCE = new AliasToEntityMapResultTransformer();
    private AliasToEntityMapResultTransformer() {
    }

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Map result = new LinkedHashMap<>(tuple.length);
        for (int i = 0; i < tuple.length; i++) {
            String alias = aliases[i];
            if (alias != null) {
                result.put(alias, tuple[i]);
            }
        }
        return result;
    }

    @Override
    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }

    private Object readResolve() {
        return INSTANCE;
    }
}