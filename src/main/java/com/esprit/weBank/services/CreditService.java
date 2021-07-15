package com.esprit.weBank.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.Credit;
import com.esprit.weBank.entities.User;
import com.esprit.weBank.repository.ICreditRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class CreditService {
	@Autowired
	private ICreditRepository creditRepository;
	public static final String ACCOUNT_SID = "AC95fd43aba538a6464a06fe21a575787c";
	public static final String AUTH_TOKEN = "0dac66ce9e639fc6231569a9bca57e08";

	public List<Credit> findAllCredit() {
		return (List<Credit>) creditRepository.findAll();
	}

	public double addCredit(Credit credit) {
		double limite = creditRepository.calculeSommeMontant(credit.getUserID());
		double montantManquant = credit.getMontant() / 0.4 - limite;
		if (limite * 0.4 >= credit.getMontant()) {
			creditRepository.save(credit);

			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			Message message = Message.creator(new com.twilio.type.PhoneNumber("+21628037468"),
					"MG44fe7a88eaa1f2b559a32328ab2699c2", "felicitation \n votre credit de " +credit.getMontant()+" a été approuvé").create();
	        System.out.println(message.getSid()); 

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

	public Credit updateCreditNull(Credit credit, int id) {
		Credit existingCredit = findCreditById(id);
		if (existingCredit != null) {
			existingCredit.setUser(null);
			return creditRepository.save(existingCredit);
		}
		return null;
	}

	public void deleteCreditById(int id) {
		Credit credit = findCreditById(id);
		;
		updateCreditNull(credit, id);
		creditRepository.deleteById(id);
	}

	public int calculeSommeMontant(int id) {
		return creditRepository.calculeSommeMontant(id);
	}

	public List<Credit> getCreditByDuree(int date) {
		return (List<Credit>) creditRepository.getCreditByDuree(date);
	}

}
