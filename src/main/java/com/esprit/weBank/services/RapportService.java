package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.Rapport;
import com.esprit.weBank.repository.IRapportRepository;

@Service
public class RapportService {
	@Autowired
	private IRapportRepository rapportRepository;
	

	public Rapport saveRapport(Rapport rapport) {
		return rapportRepository.save(rapport);
	}
	
	public List<Rapport> findAllRapport() {
		return (List<Rapport>) rapportRepository.findAll();
	}

	public Rapport findRapportById(int id) {
		return rapportRepository.findById(id).get();
	}

	public Rapport updateRapport(Rapport rapport, int id) {
		Rapport existingRapport = findRapportById(id);
		if (existingRapport != null) {
			existingRapport.setBeginDate(rapport.getBeginDate());
			existingRapport.setEndDate(rapport.getEndDate());
			existingRapport.setCritere(rapport.getCritere());
			return rapportRepository.save(existingRapport);
		}		
		return null;
	}

	public void deleteRapportById(int id) {
		rapportRepository.deleteById(id);
	}
}
