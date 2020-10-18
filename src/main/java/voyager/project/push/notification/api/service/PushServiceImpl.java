package voyager.project.push.notification.api.service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.rx.RxClient;
import org.glassfish.jersey.client.rx.RxWebTarget;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import voyager.project.push.notification.api.model.AddIndividual;
import voyager.project.push.notification.api.model.BalanceUpdate;
import voyager.project.push.notification.api.model.ConfirmPaymentRequest;
import voyager.project.push.notification.api.model.EventDetails;
import voyager.project.push.notification.api.model.PersonalDetails;
import voyager.project.push.notification.api.model.PushNotificationModel;

@Service
public class PushServiceImpl implements PushService {
	
	private RxClient<RxObservableInvoker> httpClient;	
	
	@Autowired
	public void setHttpClient(RxClient<RxObservableInvoker> httpClient) {
		this.httpClient = httpClient;
	}

	private final String url = "http://localhost:8082/notification/push";
	
	@Override
	public void pushPaymentNotification(ConfirmPaymentRequest request,HttpServletRequest httpServletRequest) {
		System.out.println("in confirm payment push service");
		
		PushNotificationModel pushNotificationRequest = new PushNotificationModel();
		EventDetails eventDetails = new EventDetails();
		eventDetails.setDeviceId(request.getDeviceId());
		eventDetails.setEventCode("TEB");
		eventDetails.setCustId(request.getCustId());
		
		pushNotificationRequest.setEventDetails(eventDetails);
		pushNotificationRequest.setEventAttributes(this.buildPushNotificationRequest(request));

		System.out.println("Request for Notification " + pushNotificationRequest.toString());
		RxWebTarget<RxObservableInvoker> webTarget = this.httpClient.target(url);
		
		MultivaluedHashMap<String, Object> headersMap = this.getHeaders(httpServletRequest);
		headersMap.add("X-Device-Token",request.getDeviceId());
		
		Response response = webTarget.request().headers(headersMap).
				post(Entity.json(pushNotificationRequest));
		System.out.println("Push Notification Successfully Triggred with data " + request.toString() + "with response " +
				response.getStatus());	
			
	}
	
	public MultivaluedHashMap<String, Object> getHeaders(HttpServletRequest httpServletRequest){
		MultivaluedHashMap<String, Object> headersMap = new MultivaluedHashMap<>();
		
		Enumeration<?> headersNames = httpServletRequest.getHeaderNames();
		
		while(headersNames.hasMoreElements()) {
			String headerName = (String)headersNames.nextElement();
			String headerValue = httpServletRequest.getHeader(headerName);
			headersMap.add(headerName, headerValue);
		}		
		
		return headersMap;
	}

	@Override
	public void pushBenfAddNotification(AddIndividual request, HttpServletRequest httpServletRequest) {
			System.out.println("in pushBenf push service");
		
		PushNotificationModel pushNotificationRequest = new PushNotificationModel();
		EventDetails eventDetails = new EventDetails();
		eventDetails.setDeviceId(request.getDeviceId());
		eventDetails.setEventCode("ABEN");
		eventDetails.setCustId(request.getCustId());
		
		pushNotificationRequest.setEventDetails(eventDetails);
		pushNotificationRequest.setEventAttributes(this.buildPushNotificationRequest(request));

		RxWebTarget<RxObservableInvoker> webTarget = this.httpClient.target(url);
		
		MultivaluedHashMap<String, Object> headersMap = this.getHeaders(httpServletRequest);
		headersMap.add("X-Device-Token",request.getDeviceId());
		
		Response response = webTarget.request().headers(headersMap).
				post(Entity.json(pushNotificationRequest));
		System.out.println("Push Notification Successfully Triggred with data " + request.toString() + "with response " +
				response.getStatus());	
		
		
	}

	@Override
	public void pushBalanceUpdateNotification(BalanceUpdate request, HttpServletRequest httpServletRequest) {
			System.out.println("in confirm payment push service");
		
		PushNotificationModel pushNotificationRequest = new PushNotificationModel();
		EventDetails eventDetails = new EventDetails();
		eventDetails.setDeviceId(request.getDeviceId());
		eventDetails.setEventCode("UBAL");
		eventDetails.setCustId(request.getCustId());
		
		pushNotificationRequest.setEventDetails(eventDetails);
		pushNotificationRequest.setEventAttributes(this.buildPushNotificationRequest(request));

		RxWebTarget<RxObservableInvoker> webTarget = this.httpClient.target(url);
		
		MultivaluedHashMap<String, Object> headersMap = this.getHeaders(httpServletRequest);
		headersMap.add("X-Device-Token",request.getDeviceId());
		
		Response response = webTarget.request().headers(headersMap).
				post(Entity.json(pushNotificationRequest));
		System.out.println("Push Notification Successfully Triggred with data " + request.toString() + "with response " +
				response.getStatus());	
				
	}
	
	private Map<String,Object> buildPushNotificationRequest(Object request){
		Map<String, Object> pushNotificationRequest = new HashMap<>();
		if(Optional.ofNullable(request).isPresent()){
				
			if(request instanceof ConfirmPaymentRequest) {
				ConfirmPaymentRequest confirmPaymentRequest = (ConfirmPaymentRequest) request;
				pushNotificationRequest.put("fromAccountNumber",confirmPaymentRequest.getFromAccountNumber());
				pushNotificationRequest.put("toAccountNumber",confirmPaymentRequest.getToAccountNumber());
				pushNotificationRequest.put("amount",confirmPaymentRequest.getAmount());
				pushNotificationRequest.put("date",confirmPaymentRequest.getDate());
				pushNotificationRequest.put("benfName",confirmPaymentRequest.getBenfName());
				pushNotificationRequest.put("currency",confirmPaymentRequest.getCurrency());
				pushNotificationRequest.put("name",confirmPaymentRequest.getName());
				
			}
			
			if(request instanceof AddIndividual) {
				AddIndividual addIndividualRequest = (AddIndividual) request;
				pushNotificationRequest.put("benfAccountNumber",addIndividualRequest.getBenfAccountNumber());
				pushNotificationRequest.put("date", addIndividualRequest.getDate());
				pushNotificationRequest.put("benfName",addIndividualRequest.getBenfName());
				pushNotificationRequest.put("currency",addIndividualRequest.getCurrency());
				pushNotificationRequest.put("benfType",addIndividualRequest.getBenfType());
				
			}
			
			if(request instanceof BalanceUpdate) {
				BalanceUpdate balanceUpdateRequest = (BalanceUpdate) request;
				pushNotificationRequest.put("benfAccountNumber",balanceUpdateRequest.getBenfAccountNumber());
				pushNotificationRequest.put("benfType",balanceUpdateRequest.getBenfType());
				pushNotificationRequest.put("previousBalance",balanceUpdateRequest.getPreviousBalance());
				pushNotificationRequest.put("date",balanceUpdateRequest.getDate());
				pushNotificationRequest.put("benfName",balanceUpdateRequest.getBenfName());
				pushNotificationRequest.put("currency",balanceUpdateRequest.getCurrency());
				pushNotificationRequest.put("updatedBalance",balanceUpdateRequest.getUpdatedBalance());
				
			}
			
			if(request instanceof PersonalDetails) {
				PersonalDetails personalDetailsRequest = (PersonalDetails) request;
				pushNotificationRequest.put("emailId",personalDetailsRequest.getEmailId());
				pushNotificationRequest.put("phoneNo",personalDetailsRequest.getPhoneNo());
				pushNotificationRequest.put("country",personalDetailsRequest.getCountry());
				pushNotificationRequest.put("city",personalDetailsRequest.getCity());
				pushNotificationRequest.put("address",personalDetailsRequest.getAddress());
				pushNotificationRequest.put("pinCode",personalDetailsRequest.getPinCode());
				
			}
			
		}
		
		
		
		return pushNotificationRequest;
	}

	@Override
	public void pushPersonalDetailsUpdateNotification(PersonalDetails request, HttpServletRequest httpServletRequest) {
		System.out.println("in Personal Update push service");
		
	PushNotificationModel pushNotificationRequest = new PushNotificationModel();
	EventDetails eventDetails = new EventDetails();
	eventDetails.setDeviceId(request.getDeviceId());
	eventDetails.setEventCode("UPER");
	eventDetails.setCustId(request.getCustId());
	
	pushNotificationRequest.setEventDetails(eventDetails);
	pushNotificationRequest.setEventAttributes(this.buildPushNotificationRequest(request));

	RxWebTarget<RxObservableInvoker> webTarget = this.httpClient.target(url);
	MultivaluedHashMap<String, Object> headersMap = this.getHeaders(httpServletRequest);
	headersMap.add("X-Device-Token",request.getDeviceId());
	
	Response response = webTarget.request().headers(headersMap).
			post(Entity.json(pushNotificationRequest));
	System.out.println("Push Notification Successfully Triggred with data " + request.toString() + "with response " +
			response.getStatus());
	}
	

}
