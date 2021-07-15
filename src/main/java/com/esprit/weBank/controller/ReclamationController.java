package com.esprit.weBank.controller;
import java.util.List;

import com.esprit.weBank.entities.Reclamation;
import com.esprit.weBank.repository.ReclamationRepository;
import com.esprit.weBank.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.sipios.springsearch.anotation.SearchSpec;



@RestController
@RequestMapping(value = "/reclamation", method = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT })
public class ReclamationController {

	@Autowired
	ReclamationService reclamationService;
	@Autowired
	ReclamationRepository reclamationRepository;
	
	@PostMapping(value = "/addreclamation")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation reclamation) {

		return new ResponseEntity<>(reclamationService.saveReclamation(reclamation), HttpStatus.OK);
	}

	@RequestMapping(value = "/listallreclamations", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Reclamation> findAllReclamation() {
		return reclamationService.findAllReclamation();
	}

	
	@RequestMapping(value = "/statInprog", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String statInprog() {
		Integer stat = reclamationRepository.statInprogress();
		return stat.toString() + '%';
	}

	@RequestMapping(value = "/statClosed", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String statClosed() {
		Integer stat = reclamationRepository.statClosed();
		return stat.toString() + '%';
	}
	
	@RequestMapping(value = "/statOpen", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String statOpen() {
		Integer stat = reclamationRepository.statOpen();
		return stat.toString() + '%';
	}

	
	@PutMapping(value = "updatereclamation/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Reclamation> updateReclamation(@PathVariable(value = "id") Long id,
			@RequestBody Reclamation reclamation) {
		return new ResponseEntity<>(reclamationService.updateReclamation(id, reclamation), HttpStatus.OK);
	}

	@DeleteMapping(value = "deletereclamation/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteReclamation(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<>(reclamationService.deleteReclamation(id), HttpStatus.OK);
	}

	@GetMapping("/findbycriteria")
	public ResponseEntity<List<Reclamation>> findbycriteria(@SearchSpec Specification<Reclamation> specs) {
	return new ResponseEntity<>(reclamationRepository.findAll(Specification.where(specs)), HttpStatus.OK);
	}

}

