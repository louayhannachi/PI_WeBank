package com.esprit.weBank.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.esprit.weBank.entities.Transaction;

public interface ITransactionRepository extends CrudRepository<Transaction, Integer> {

	Optional<List<Transaction>> findByRibE(int ribE);
	
	/*@Query("select t from Transaction t where t.user.id = :x")
	public Page<Transaction> getAllTransactionByIdUser( @Param("x")String id, Pageable pageable );*/
}
