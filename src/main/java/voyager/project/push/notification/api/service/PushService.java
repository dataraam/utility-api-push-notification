package voyager.project.push.notification.api.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.annotation.Async;

import voyager.project.push.notification.api.model.AddIndividual;
import voyager.project.push.notification.api.model.BalanceUpdate;
import voyager.project.push.notification.api.model.ConfirmPaymentRequest;
import voyager.project.push.notification.api.model.PersonalDetails;

public interface PushService {
	
	@Async("pushNotificationPoolExecutor")
	public void pushPaymentNotification(ConfirmPaymentRequest request, HttpServletRequest httpServletRequest);
	
	@Async("pushNotificationPoolExecutor")
	public void pushBenfAddNotification(AddIndividual request, HttpServletRequest httpServletRequest);
	
	@Async("pushNotificationPoolExecutor")
	public void pushBalanceUpdateNotification(BalanceUpdate request, HttpServletRequest httpServletRequest);

	@Async("pushNotificationPoolExecutor")
	public void pushPersonalDetailsUpdateNotification(PersonalDetails request, HttpServletRequest httpServletRequest);

}
