package com.esprit.weBank.repository;
import com.esprit.weBank.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;



@RepositoryRestResource
public interface ReclamationRepository extends JpaRepository<Reclamation, Long>, JpaSpecificationExecutor<Reclamation> {

	@Query(
			"SELECT COUNT(*)/(SELECT COUNT(*) FROM Reclamation) * 100 FROM Reclamation r WHERE r.status = 'INPROGRESS'")
			Integer statInprogress();


	@Query(
			"SELECT COUNT(*)/(SELECT COUNT(*) FROM Reclamation) * 100 FROM Reclamation r WHERE r.status = 'OPEN'")
			Integer statOpen();
	
	@Query(
			"SELECT COUNT(*)/(SELECT COUNT(*) FROM Reclamation) * 100 FROM Reclamation r WHERE r.status = 'CLOSED'")
			Integer statClosed();

}