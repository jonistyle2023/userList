package com.springmvc.app.springmvc.app.services;

import com.springmvc.app.springmvc.app.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void deleteBy(Long id);

}
