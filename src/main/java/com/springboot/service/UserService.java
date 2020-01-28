package com.springboot.service;

import java.util.List;

import com.springboot.model.User;

public interface UserService {

    List<User> getAll();

    User findById(int id);
    
    List<User> findByIds(int id);
    
    

    User findByName(String name);

    void create(User user);

    void update(User user);

    void delete(int id);

    boolean exists(User user);
}
