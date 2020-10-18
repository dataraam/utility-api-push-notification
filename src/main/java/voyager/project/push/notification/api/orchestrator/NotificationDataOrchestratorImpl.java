package voyager.project.push.notification.api.orchestrator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import voyager.project.push.notification.api.model.AddIndividual;
import voyager.project.push.notification.api.model.BalanceUpdate;
import voyager.project.push.notification.api.model.ConfirmPaymentRequest;
import voyager.project.push.notification.api.model.PersonalDetails;
import voyager.project.push.notification.api.service.PushService;

@Service
public class NotificationDataOrchestratorImpl implements NotificationDataOrchestrator {
	
	private PushService pushService;
	
	
	@Autowired	
	public void setPushService(PushService pushService) {
		this.pushService = pushService;
	}


	@Override
	public void getPaymentsRequest(ConfirmPaymentRequest confirmPaymentRequest, HttpServletRequest httpServletRequest) {
		this.pushService.pushPaymentNotification(confirmPaymentRequest, httpServletRequest);
	}



	@Override
	public void addIndividual(AddIndividual addIndividual, HttpServletRequest httpServletRequest) {
		this.pushService.pushBenfAddNotification(addIndividual, httpServletRequest);
		
	}



	@Override
	public void updateBalance(BalanceUpdate balanceUpdate, HttpServletRequest httpServletRequest) {
		this.pushService.pushBalanceUpdateNotification(balanceUpdate, httpServletRequest);		
	}


	@Override
	public void updatePersonalDetails(PersonalDetails personalDetails, HttpServletRequest httpServletRequest) {
		this.pushService.pushPersonalDetailsUpdateNotification(personalDetails, httpServletRequest);
		
	}

}
