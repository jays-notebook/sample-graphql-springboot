package com.yourbookmark.api.handler;

import graphql.ErrorClassification;

public enum CustomErrorType implements ErrorClassification {
    InvalidSyntax,
    ValidationError,
    DataFetchingException,
    OperationNotSupported,
    ExecutionAborted,
    MethodNotAllowed
}
