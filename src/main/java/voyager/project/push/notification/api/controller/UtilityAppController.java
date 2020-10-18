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

import voyager.project.push.notification.api.model.AddIndividual;
import voyager.project.push.notification.api.model.BalanceUpdate;
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
	
	@RequestMapping(method = RequestMethod.POST, value ="/add-individual")
	public String addIndividual(@RequestHeader("X-Device-Token") String deviceToken,
			@Valid @RequestBody AddIndividual addIndividualRequest, HttpServletRequest request) throws Exception {
		String message ="";
		if(Optional.ofNullable(deviceToken).isPresent()) {
			addIndividualRequest.setDeviceId(deviceToken);
		this.notificationDataOrchestrator.addIndividual(addIndividualRequest, request);
			message = "Payee has been successfully added";
		}
		else
		{
			message = "Token Not found";
			throw new Exception("Required Token Not found");
		}
		return message;
	}

	@RequestMapping(method = RequestMethod.POST, value ="/balance-update")
	public String balanceUpdated(@RequestHeader("X-Device-Token") String deviceToken,
			@Valid @RequestBody BalanceUpdate balanceUpdateRequest, HttpServletRequest request) throws Exception {
		String message ="";
		if(Optional.ofNullable(deviceToken).isPresent()) {
			balanceUpdateRequest.setDeviceId(deviceToken);
		this.notificationDataOrchestrator.updateBalance(balanceUpdateRequest, request);
			message = "Balance has been successfully updated";
		}
		else
		{
			message = "Token Not found";
			throw new Exception("Required Token Not found");
		}
		return message;
	}

	@RequestMapping(method = RequestMethod.POST, value ="/personal-details-update")
	public String personalDetailssUpdated(@RequestHeader("X-Device-Token") String deviceToken,
			@Valid @RequestBody BalanceUpdate balanceUpdateRequest, HttpServletRequest request) throws Exception {
		String message ="";
		if(Optional.ofNullable(deviceToken).isPresent()) {
			balanceUpdateRequest.setDeviceId(deviceToken);
		this.notificationDataOrchestrator.updateBalance(balanceUpdateRequest, request);
			message = "Details has been successfully updated";
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