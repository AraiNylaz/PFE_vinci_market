package com.example.backend.dataFetcher;
import com.example.backend.model.User;
import com.example.backend.services.UserService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
 public class AllUserDataFetcher implements DataFetcher<List<User>> {


    private UserService userService;

    @Autowired
    AllUserDataFetcher(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> get(DataFetchingEnvironment env) {
        User user =  env.getSource();
        List<User> friends = new ArrayList<>();
        friends = userService.findAllUsers();
        return friends;
    }
}
