package com.springmvc.app.springmvc.app.repositories;

import com.springmvc.app.springmvc.app.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    Long id(Long id);
}
