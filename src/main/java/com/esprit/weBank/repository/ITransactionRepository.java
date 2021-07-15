package com.esprit.weBank.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.esprit.weBank.entities.Transaction;

@Repository
public interface ITransactionRepository extends CrudRepository<Transaction, Integer> {

	Optional<List<Transaction>> findByRibE(int ribE);

	@Query(value = "select t.id ,t.dateT,t.devise,t.montant,t.nomc,t.ribe,t.ribr,t.type,t.user_fk FROM transaction t where t.user_fk= :id ", nativeQuery = true)
	public List<Transaction> getAllTransactionByIdUser(@Param("id") int id);

	@Modifying
	@Query(value = "UPDATE account " 
			+ "INNER JOIN transaction " 
			+ "ON account.client_id = transaction.user_fk "
			+ "set account.balance=:account.balance - transaction.montant "
			+ "where account.client_id=:idU ", nativeQuery = true)
	public void updateBalanceCredit(@Param("idU") int idU);
	
	@Modifying
	@Query(value = "UPDATE account " 
			+ "INNER JOIN transaction " 
			+ "ON account.client_id = transaction.user_fk "
			+ "set account.balance=account.balance * transaction.montant "
			+ "where account.client_id=:idU ", nativeQuery = true)
	public void updateBalanceSaving(@Param("idU") int idU);
	
	


@Modifying
@Query(value = "UPDATE account " 
		+ "INNER JOIN transaction " 
		+ "ON account.rib = transaction.ribr "
		+ "set account.balance=account.balance + transaction.montant "
		+ "where account.rib=:ribr ", nativeQuery = true)
public void updateBalanceR(@Param("ribr") int ribr);



@Query(value = "select max(transaction.datet) FROM transaction where transaction.user_fk=:id ", nativeQuery = true)
public Date getLastTransaction(@Param("id") int id);


@Query(value = "SELECT COUNT(*) FROM transaction where transaction.type='SAVINGS' and transaction.user_fk=:id ", nativeQuery = true)
public int getNbrTransactionsS(@Param("id") int id);

@Query(value = "SELECT COUNT(*) FROM transaction where transaction.type='CURRENT' and transaction.user_fk=:id ", nativeQuery = true)
public int getNbrTransactionsC(@Param("id") int id);

@Query(value = "SELECT COUNT(*) FROM transaction where transaction.user_fk=:id ", nativeQuery = true)
public int getNbrTransactionsAll(@Param("id") int id);
}