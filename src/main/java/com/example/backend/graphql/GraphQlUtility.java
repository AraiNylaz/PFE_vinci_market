package com.example.backend.graphql;

import com.example.backend.dataFetcher.AllUserDataFetcher;
import com.example.backend.dataFetcher.UserDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Component
public class GraphQlUtility {

    @Value("classpath:models.graphqls")
    private Resource schemaResource;
    private GraphQL graphQL;
    private AllUserDataFetcher allUserDataFetcher;
    private UserDataFetcher userDataFetcher;

    @Autowired
    GraphQlUtility(AllUserDataFetcher allUsersDataFetcher, UserDataFetcher userDataFetcher) throws IOException {
        this.allUserDataFetcher = allUsersDataFetcher;
        this.userDataFetcher = userDataFetcher;
    }

    @PostConstruct
    public GraphQL createGraphQlObject() throws IOException {
        System.out.println("here in class GrapQIutil in method createGraphQlObject");
        File schemas = schemaResource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemas);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        return  newGraphQL(schema).build();
    }

    public RuntimeWiring buildRuntimeWiring(){
        System.out.println("here in class GrapQIutil in method buildRuntimeWiring");
        return newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("users", allUserDataFetcher)
                        .dataFetcher("user", userDataFetcher))
                .type("User", typeWiring -> typeWiring)
                .build();
    }
}
