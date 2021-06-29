package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.weBank.entities.Depense;
import com.esprit.weBank.repository.IDepenseRepository;

@Service
public class DepenseService {
	@Autowired
	private IDepenseRepository depenseRepository;
	
	public Depense saveDepense(Depense depense) {
		return depenseRepository.save(depense);
	}
	
	public List<Depense> findAllDepense() {
		return (List<Depense>) depenseRepository.findAll();
	}
	
	public Depense findDepenseById(int id) {
		return depenseRepository.findById(id).get();
	}
	
	public Depense updateDepense(Depense depense, int id) {
		Depense existingDepense = findDepenseById(id);
		if (existingDepense != null) {
			existingDepense.setMontant(depense.getMontant());
			existingDepense.setNote(depense.getNote());
			existingDepense.setPaiement(depense.getPaiement());
			existingDepense.setType(depense.getType());
			return depenseRepository.save(existingDepense);
		}		
		return null;
	}
	
	public void deleteDepenseById(int id) {
		depenseRepository.deleteById(id);
	}
}
