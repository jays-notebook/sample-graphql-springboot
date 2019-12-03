package com.yourbookmark.api.exception;

import com.google.gson.JsonSyntaxException;
import graphql.ErrorClassification;
import graphql.ExecutionResult;
import graphql.ExecutionResultImpl;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExecutionResult> methodNotAllowedHandler(HttpRequestMethodNotSupportedException ex) {
        GraphQLError graphQLError = generateError(ex.getMessage(), CustomErrorType.MethodNotAllowed);
        return new ResponseEntity<>(new ExecutionResultImpl(graphQLError), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ExecutionResult> badParameterHandler(HttpMessageNotReadableException ex) {
        GraphQLError graphQLError = generateError(ex.getMessage(), CustomErrorType.BadRequest);
        return new ResponseEntity<>(new ExecutionResultImpl(graphQLError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = JsonSyntaxException.class)
    public ResponseEntity<ExecutionResult> jsonConvertErrorHandler(JsonSyntaxException ex) {
        GraphQLError graphQLError = generateError(ex.getMessage(), CustomErrorType.BadRequest);
        return new ResponseEntity<>(new ExecutionResultImpl(graphQLError), HttpStatus.BAD_REQUEST);
    }

    private GraphQLError generateError(String message, ErrorClassification classification) {
        return GraphqlErrorBuilder
                .newError()
                .errorType(classification)
                .message(message).build();
    }
}
