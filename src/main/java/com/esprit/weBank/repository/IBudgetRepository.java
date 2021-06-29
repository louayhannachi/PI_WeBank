package com.esprit.weBank.repository;

import org.springframework.data.repository.CrudRepository;

import com.esprit.weBank.entities.Budget;

public interface IBudgetRepository extends CrudRepository<Budget, Integer> {

}
