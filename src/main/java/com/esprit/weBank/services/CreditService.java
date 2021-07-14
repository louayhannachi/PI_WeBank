package com.esprit.weBank.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.Credit;
import com.esprit.weBank.entities.User;
import com.esprit.weBank.repository.ICreditRepository;

@Service
public class CreditService {
	@Autowired
	private ICreditRepository creditRepository;

	public List<Credit> findAllCredit() {
		return (List<Credit>) creditRepository.findAll();
	}

	public double addCredit(Credit credit) {
		double limite = creditRepository.calculeSommeMontant(credit.getUserID());
		double montantManquant = credit.getMontant() / 0.4 - limite;
		if (limite * 0.4 >= credit.getMontant()) {
			creditRepository.save(credit);
			return 1;

		} else {
			return montantManquant;

		}

	}

	public Credit findCreditById(int id) {
		return creditRepository.findById(id).orElse(null);
	}

	public Credit updateCredit(Credit credit, int id) {
		Credit existingCredit = findCreditById(id);
		if (existingCredit != null) {
			// existingCredit.setNomC(credit.getNomC());
			// existingCredit.setRibR(credit.getRibR());
			existingCredit.setMontant(credit.getMontant());
			existingCredit.setDevice(credit.getDevice());
			existingCredit.setDuree(credit.getDuree());
			// existingCredit.setUser(credit.getUser());
			return creditRepository.save(existingCredit);
		}
		return null;
	}

	public void deleteCreditById(int id) {
		creditRepository.deleteById(id);
	}

	public int calculeSommeMontant(int id) {
		return creditRepository.calculeSommeMontant(id);
	}
}
