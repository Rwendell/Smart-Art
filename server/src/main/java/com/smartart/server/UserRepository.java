package com.smartart.server;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    public User findByUsername(String username);

}
