package voyager.project.push.notification.api.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import voyager.project.push.notification.api.model.ConfirmPaymentRequest;
import voyager.project.push.notification.api.orchestrator.NotificationDataOrchestrator;

@RestController
@RequestMapping("/banking")
public class UtilityAppController {
	
	private NotificationDataOrchestrator notificationDataOrchestrator;

	
	@RequestMapping("/")
	public String getAppStatus() {
		return "App is Up and running";
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/confirm-payments")
	public String getConfirmPayments(@RequestHeader("X-Device-Token") String deviceToken,
			@Valid @RequestBody ConfirmPaymentRequest confirmPaymentRequest, HttpServletRequest request) throws Exception {
		String message ="";
		if(Optional.ofNullable(deviceToken).isPresent()) {
			confirmPaymentRequest.setDeviceId(deviceToken);
		this.notificationDataOrchestrator.getPaymentsRequest(confirmPaymentRequest, request);
			message = "Payment Success";
		}
		else
		{
			message = "Token Not found";
			throw new Exception("Required Token Not found");
		}
		return message;
	}

	@Autowired
	public void setNotificationDataOrchestrator(NotificationDataOrchestrator notificationDataOrchestrator) {
		this.notificationDataOrchestrator = notificationDataOrchestrator;
	}

	
}