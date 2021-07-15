package com.esprit.weBank.services;
import java.util.List;

import com.esprit.weBank.entities.Reclamation;
import com.esprit.weBank.repository.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service
public class ReclamationService {
	public static final String STATUSOpen = "OPEN";
	public static final String STATUSINPROGRESS = "INPROGRESS";
	public static final String StatusClosed = "CLOSED";
	@Autowired
	ReclamationRepository reclamationRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	public Reclamation saveReclamation(Reclamation reclamation) {
		Reclamation reclamationAlerted = new Reclamation(/*reclamation.getProduct,*/ reclamation.getTitle(),
				reclamation.getDescription(), reclamation.getType(), reclamation.getPriorityEnum(),
				reclamation.getStatus());
		reclamationAlerted.setStatus(STATUSOpen);
		reclamationRepository.save(reclamationAlerted);
		sendEmail();
		return reclamationAlerted;
	}

	public List<Reclamation> findAllReclamation() {
		return reclamationRepository.findAll();
	}

	public Reclamation updateReclamation(Long id, Reclamation Newreclamation) {
		if (reclamationRepository.findById(id).isPresent()) {
			Reclamation existingReclamation = reclamationRepository.findById(id).get();
			existingReclamation.setTitle(Newreclamation.getTitle());
			existingReclamation.setDescription(Newreclamation.getDescription());
			existingReclamation.setPriorityEnum(Newreclamation.getPriorityEnum());
			existingReclamation.setType(Newreclamation.getType());
			existingReclamation.setStatus(Newreclamation.getStatus());

			return reclamationRepository.save(existingReclamation);
		} else
			return null;
	}

	public String deleteReclamation(Long id) {
		if (reclamationRepository.findById(id).isPresent()) {
			reclamationRepository.deleteById(id);
			return "Reclamation supprimé";
		} else
			return "Reclamation non supprimé";
	}


	public void sendEmail() {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("chaima.abdelaali@esprit.tn");
		msg.setSubject("Reclamation");
		msg.setText(" Bonjour, \n\r\r nous avons bien reçu votre reclamation et allons vous répondre. \n\r\r Cordialement \n\r WeBank App");
		javaMailSender.send(msg);
	}
	

}
