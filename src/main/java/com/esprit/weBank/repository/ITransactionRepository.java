package com.esprit.weBank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.esprit.weBank.entities.Transaction;

public interface ITransactionRepository extends CrudRepository<Transaction, Integer> {

	Optional<List<Transaction>> findByRibE(int ribE);
	
	/*@Query("SELECT t FROM Transaction t where t.user.id:=id ")
	public List<Transaction> getAllTransactionByIdUser(@Param("id") int id );*/
}
