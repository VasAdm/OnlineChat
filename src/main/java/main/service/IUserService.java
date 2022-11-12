package main.service;

import main.model.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
    Map<String, Boolean> auth(String name);
    Map<String, Boolean> isAuthorised();
    List<User> getUsers();
}
