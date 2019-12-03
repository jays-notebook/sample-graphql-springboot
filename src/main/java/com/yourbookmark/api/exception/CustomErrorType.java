package com.yourbookmark.api.exception;

import graphql.ErrorClassification;

public enum CustomErrorType implements ErrorClassification {
    InvalidSyntax,
    ValidationError,
    DataFetchingException,
    OperationNotSupported,
    ExecutionAborted,
    BadRequest,
    MethodNotAllowed
}
