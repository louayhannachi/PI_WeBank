package com.esprit.weBank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.esprit.weBank.entities.Credit;
import com.esprit.weBank.entities.User;

public interface ICreditRepository extends CrudRepository<Credit, Integer> {

	Optional<List<Credit>> findByRibR(int ribR);
	
	
	@Query(value = "select SUM(balance) "
			+"from account " 
			+"where account.client_id=:id ", nativeQuery = true)
	public int calculeSommeMontant(@Param("id") int id);


}
