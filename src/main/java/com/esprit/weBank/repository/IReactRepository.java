package com.esprit.weBank.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.esprit.weBank.entities.React;

public interface IReactRepository extends CrudRepository<React, Integer> {
	Optional<React> findById(int id);
}
