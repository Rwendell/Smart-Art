package com.smartart.server;

import org.springframework.data.repository.CrudRepository;
import com.smartart.server.Users;

public interface UsersRepository extends CrudRepository<Users, Long>{
}
