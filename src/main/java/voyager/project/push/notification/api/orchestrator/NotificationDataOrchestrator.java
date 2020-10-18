package voyager.project.push.notification.api.orchestrator;

import javax.servlet.http.HttpServletRequest;

import voyager.project.push.notification.api.model.AddIndividual;
import voyager.project.push.notification.api.model.BalanceUpdate;
import voyager.project.push.notification.api.model.ConfirmPaymentRequest;
import voyager.project.push.notification.api.model.PersonalDetails;

public interface NotificationDataOrchestrator {
	
	public void getPaymentsRequest(ConfirmPaymentRequest confirmPaymentRequest, HttpServletRequest httpServletRequest);

	public void addIndividual(AddIndividual addIndividual, HttpServletRequest httpServletRequest);
	
	public void updateBalance(BalanceUpdate balanceUpdate, HttpServletRequest httpServletRequest);
	
	public void updatePersonalDetails(PersonalDetails personalDetails, HttpServletRequest httpServletRequest);
}
