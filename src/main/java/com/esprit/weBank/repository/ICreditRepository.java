package com.esprit.weBank.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.esprit.weBank.entities.Credit;

public interface ICreditRepository extends CrudRepository<Credit, Integer> {
/*
	Optional<List<Credit>> findByRibE(int ribE);
	
	@Query("SELECT u.firstname, t.id FROM USER u JOIN u.transactions t")
	public List<Transaction> getAllTransactionByIdUser( );

*/
}
