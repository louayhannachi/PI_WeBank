package com.esprit.weBank.services;

import java.util.List;

import com.esprit.weBank.entities.Transaction;
import com.esprit.weBank.util.AccountType;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.Account;
import com.esprit.weBank.entities.Alert;
import com.esprit.weBank.repository.IAlertRepository;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class AlertService  {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private IAlertRepository alertRepository;

	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionService transactionService;

	public static final String ACCOUNT_SID = "AC283f95af89ec7b3d7cf4aff73dbf730b";
	public static final String AUTH_TOKEN = "fea38182aa817462a09bdb6596b6978e";

	public Alert saveAlert(Alert alert , int rib) {

		Account ac = accountService.findAccountByRib(rib);

		if(ac.getBudget().getPlafond()>ac.getBalance())
			{

                sendEmail();

		 		return alertRepository.save(alert);
			}else
		 		return null;
	}

	public void sendEmail() {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("chaima.abdelaali@esprit.tn");

		msg.setSubject("Alert");
		msg.setText(" Bonjour, \n\r\r Vous avez dépassé le montant autorisé. \n\r\r Cordialement \n\r WeBank App");

		javaMailSender.send(msg);

	}

	public List<Alert> findAllAlert() {
		return (List<Alert>) alertRepository.findAll();
	}



	public Alert findAlertById(int id) {
		return alertRepository.findById(id).get();
	}



	public Alert updateAlert(Alert alert, int id) {
		Alert existingAlert = findAlertById(id);
		if (existingAlert != null) {
			existingAlert.setAlertDate(alert.getAlertDate());
			existingAlert.setAlertMsg(alert.getAlertMsg());
			return alertRepository.save(existingAlert);
		}		
		return null;
	}


	public void deleteAlertById(int id) {
		alertRepository.deleteById(id);
	}

}
