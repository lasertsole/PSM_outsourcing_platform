package com.psm.custom;

import com.netflix.graphql.types.errors.TypedGraphQLError;
import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/*DGS ERROR上下文*/
@Component
public class ExceptionHandler implements DataFetcherExceptionHandler {
    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
        if (handlerParameters.getException() instanceof GraphQLException) {
            Map<String, Object> debugInfo = new HashMap<>();
            debugInfo.put("state", ((GraphQLException) handlerParameters.getException()).getState());

            GraphQLError graphqlError = TypedGraphQLError.newInternalErrorBuilder()
                    .message(handlerParameters.getException().getMessage())//获取异常信息
                    .debugInfo(debugInfo)
                    .path(handlerParameters.getPath()).build();

            DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError)
                    .build();
            return CompletableFuture.completedFuture(result);
        }else {
            return DataFetcherExceptionHandler.super.handleException(handlerParameters);
        }
    }
}
