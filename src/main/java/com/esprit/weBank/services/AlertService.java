package com.esprit.weBank.services;
import java.util.List;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.esprit.weBank.entities.Account;
import com.esprit.weBank.entities.Alert;
import com.esprit.weBank.repository.IAlertRepository;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.mail.SimpleMailMessage;
import com.esprit.weBank.entities.Transaction;
import com.esprit.weBank.util.AccountType;

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

 //   public static final String ACCOUNT_SID = "ACcb1ccfb30743cc3439bfe79b46968ccb";
  //  public static final String AUTH_TOKEN = "7528fe2b5dbfd0ce4f31765298809126";

    public Alert saveAlert(Alert alert, int rib) {

        Account ac = accountService.findAccountByRib(rib);

        if (ac.getBudget().getPlafond() > ac.getBalance()) {

//			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//			Message message = Message.creator(
//					new com.twilio.type.PhoneNumber("+21626555558"),
//					"MGf2d184943b5df94a0366475f11c1afed",
//					"\n \n \n Vous avez choisi de garder en compte : "
//							+ ac.getBudget().getPlafond() + "dt pour ce mois." +
//							"\nVotre solde est : " + ac.getBalance() + "dt " +
//							"\n Vous avez fait un depassement").create();
//			System.out.println(message.getSid());
            return alertRepository.save(alert);
        } else
            return null;
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
