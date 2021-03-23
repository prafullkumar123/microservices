package com.javafuns.epizy.repo;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.javafuns.epizy.model.User;

public interface UserRepo extends MongoRepository<User, Integer> {
	User findByuserName(String userName);
}
