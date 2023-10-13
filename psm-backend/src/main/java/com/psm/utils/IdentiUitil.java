package com.psm.utils;

import com.netflix.graphql.dgs.context.DgsContext;
import com.psm.custom.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

public class IdentiUitil {
    public static Object IdentiToken(DataFetchingEnvironment dfe){
        Object object = DgsContext.getCustomContext(dfe);
        if (object instanceof GraphQLException){
            throw (GraphQLException)object;
        }
        return object;
    }
}
