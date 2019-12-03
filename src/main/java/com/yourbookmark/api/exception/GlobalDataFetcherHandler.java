package com.yourbookmark.api.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;

public class GlobalDataFetcherHandler implements DataFetcherExceptionHandler {

    @Override
    public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handlerParameters) {
        Throwable exception = handlerParameters.getException();
        GraphQLError graphQLError = GraphqlErrorBuilder
                .newError()
                .message(exception.getMessage())
                .build();

        return DataFetcherExceptionHandlerResult
                .newResult()
                .error(graphQLError)
                .build();
    }
}
