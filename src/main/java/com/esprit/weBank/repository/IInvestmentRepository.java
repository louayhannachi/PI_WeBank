package com.esprit.weBank.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.esprit.weBank.entities.Investment;

public interface IInvestmentRepository extends CrudRepository<Investment, Integer> {

	Optional<Investment> findByInvestName(String investName);

}
