package com.yourbookmark.api.service;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.yourbookmark.api.handler.GlobalDataFetcherHandler;
import com.yourbookmark.api.service.datafetcher.BookMarkDataFetchers;
import com.yourbookmark.api.service.datafetcher.UserDataFetchers;
import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.GraphQL.newGraphQL;

@Service
public class BookMarkServiceImpl implements BookMarkService {

    private final UserDataFetchers userDataFetchers;
    private final BookMarkDataFetchers bookMarkDataFetchers;

    private GraphQL graphQL;

    public BookMarkServiceImpl(UserDataFetchers userDataFetchers,
                               BookMarkDataFetchers bookMarkDataFetchers) {
        this.userDataFetchers = userDataFetchers;
        this.bookMarkDataFetchers = bookMarkDataFetchers;
    }

    @Bean
    public GraphQL graphQL() {
        return this.graphQL;
    }

    @PostConstruct
    private void init() throws IOException {
        URL url = Resources.getResource("BookMark.graphql");
        String schema = Resources.toString(url, Charsets.UTF_8);

        TypeDefinitionRegistry registry = new SchemaParser().parse(schema);
        RuntimeWiring wiring = this.buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
        this.graphQL = newGraphQL(graphQLSchema)
                .queryExecutionStrategy(new AsyncExecutionStrategy(new GlobalDataFetcherHandler()))
                .mutationExecutionStrategy(new AsyncExecutionStrategy(new GlobalDataFetcherHandler()))
                .build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("user", userDataFetchers.getUser())
                        .dataFetcher("userbyname", userDataFetchers.getUserByName())
                        .dataFetcher("users", userDataFetchers.getAllUsers())
                        .dataFetcher("bookmark", bookMarkDataFetchers.getBookMark())
                        .dataFetcher("bookmarks", bookMarkDataFetchers.getAllBookMarks())
                )
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("addUser", userDataFetchers.addUser())
                )
                .build();
    }
}
