package com.demo.graphql.springbootgraphqldemo.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.demo.graphql.springbootgraphqldemo.model.Book;
import com.demo.graphql.springbootgraphqldemo.repository.BookRepository;
import com.demo.graphql.springbootgraphqldemo.service.datafetcher.AllBooksDataFetcher;
import com.demo.graphql.springbootgraphqldemo.service.datafetcher.BookDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

    @Autowired
    BookRepository bookRepository;

    @Value("classpath:books.graphql")
    Resource resource;

    private GraphQL graphQL;
    @Autowired
    private AllBooksDataFetcher allBooksDataFetcher;
    @Autowired
    private BookDataFetcher bookDataFetcher;


    @PostConstruct
    private void loadSchema() throws IOException {

        loadDataIntoHSQL();

        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoHSQL() {

        Stream.of(
                new Book(new Long(001), "Effective Java, 3rd Edition", "Addison-Wesley Professional",
                        new String[] {
                        "Joshua Bloch"
                        }, "Dec 2017"),
                
                new Book(new Long(002), "Mastering Java", "Independently published",
                        new String[] {
                                "Michael B. White"
                        }, "Dec 2018"),
                
                new Book(new Long(003), "Learning Spring Boot 2.0 - Second Edition", "Orielly",
                        new String[] {
                                "Packt Publishing"
                        }, "Nov 2017")
        ).forEach(book -> {
            bookRepository.save(book);
        });
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allBooks", allBooksDataFetcher)
                        .dataFetcher("book", bookDataFetcher))
                .build();
    }


    public GraphQL getGraphQL() {
        return graphQL;
    }
    
}