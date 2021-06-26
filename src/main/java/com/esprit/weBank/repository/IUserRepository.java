package com.esprit.weBank.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esprit.weBank.entities.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {
	
	Optional<User> findByFirstName(String name);
	Optional<User> findByCin(String cin);
	void deleteByCin(String cin);
}
