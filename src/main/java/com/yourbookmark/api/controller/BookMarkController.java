package com.yourbookmark.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/bookMark")
public class BookMarkController {

    private final GraphQL graphQL;
    private static final String PREFIX_QUERY = "query";

    public BookMarkController(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @PostMapping
    public ExecutionResult graphQLResult(@RequestBody String query) throws IOException {

        String finalQuery = query;
        if (query.contains(PREFIX_QUERY)) {
            Map jsonMap = this.jsonToMap(finalQuery);
            query = (String) jsonMap.get("query");
        }

        return graphQL.execute(query);
    }

    private Map jsonToMap(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Map.class);
    }
}
