package com.smartart.model;

import org.springframework.data.repository.CrudRepository;

/**
 * @author rwendell
 */
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
    public User findByUserId(Long UserID);
    public User removeByUserId(Long UserID);

}
