package com.yourbookmark.api.service.datafetcher;

import com.yourbookmark.api.entity.User;
import com.yourbookmark.api.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataFetchers {
    private final UserRepository userRepository;

    public UserDataFetchers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DataFetcher<User> getUser() {
        return environment -> {
            int id = Integer.parseInt(environment.getArgument("id"));
            return userRepository.findByUserId(id);
        };
    }

    public DataFetcher<User> getUserByName() {
        return environment -> {
            String name = environment.getArgument("name");
            return userRepository.findByName(name);
        };
    }

    public DataFetcher<List<User>> getAllUsers() {
        return environment -> userRepository.findAll();
    }

    public DataFetcher<User> addUser() {
        return environment -> {
            String name = environment.getArgument("name");
            Integer age = environment.getArgument("age");

            User user = new User(name, age);
            return userRepository.save(user);
        };
    }
}
