package com.yourbookmark.api.handler;

import graphql.ErrorClassification;
import graphql.ExecutionResult;
import graphql.ExecutionResultImpl;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExecutionResult> methodNotAllowedHandler(HttpRequestMethodNotSupportedException ex) {
        GraphQLError graphQLError = generateError(ex.getMessage(), CustomErrorType.MethodNotAllowed);
        ExecutionResult result = new ExecutionResultImpl(graphQLError);
        return new ResponseEntity<>(result, HttpStatus.METHOD_NOT_ALLOWED);
    }

    private GraphQLError generateError(String message, ErrorClassification classification) {
        return GraphqlErrorBuilder
                .newError()
                .errorType(classification)
                .message(message).build();
    }
}
