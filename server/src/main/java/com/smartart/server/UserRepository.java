package com.smartart.server;

import org.springframework.data.repository.CrudRepository;
/**
 * @author rwendell
 */
public interface UserRepository extends CrudRepository<User, Long>{
    public User findByUsername(String username);

}
