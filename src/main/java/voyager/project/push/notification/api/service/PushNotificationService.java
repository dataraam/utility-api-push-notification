package voyager.project.push.notification.api.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.budgets.model.InvalidParameterException;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import voyager.project.push.notification.api.constants.ReceiverConstant;

@Service
public class PushNotificationService {
	
	
	
	
	private AmazonSNS client;
	
	public PushNotificationService(BasicSessionCredentials sessionCredentials) {
		this.client = AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(sessionCredentials)).build();
	}
	
	private String createEndpoint(String deviceToken) {
		String endpointARN = null;
		try {
			CreatePlatformEndpointRequest endpointRequest = new CreatePlatformEndpointRequest()
					.withPlatformApplicationArn(ReceiverConstant.APP_ENDPOINT_ARN).withToken(deviceToken);
			CreatePlatformEndpointResult endPointResult = client.createPlatformEndpoint(endpointRequest);
			endpointARN = endPointResult.getEndpointArn();
		} catch (InvalidParameterException ipe) {
			String message = ipe.getErrorMessage();
			Pattern p = Pattern.compile(".*Endpoint (arn:aws:sns[^ ]+) already exists " + "with the same [Tt]oken.*");
			Matcher m = p.matcher(message);
			if (m.matches()) {
				endpointARN = m.group(1);
			}
			else {
				throw ipe;
			}
		}
		return endpointARN;
	}
	
	
	public void publish (String subject, String targetArn) throws Exception {
		JSONObject payload = new JSONObject();
		PublishRequest request = new PublishRequest();
//		request.setMessageStructure(ReceiverConstant.GCM_MSG_STRUCTURE);
//		Map<String, Map<String, String>> androidMsg = new HashMap<>();
//		Map<String, String> messageMap = new HashMap<String, String>();
//		messageMap.put(ReceiverConstant.TEXT, subject);
//		androidMsg.put(ReceiverConstant.NOTIFICATION, messageMap);
//		JSONObject json = new JSONObject(androidMsg);
//		payload.put(ReceiverConstant.NOTIFICATION, androidMsg);
//		payload.put(ReceiverConstant.GCM, "GCM");
//		payload.put("default", "defaultmsg");
//		String payload1 = payload.toString();
//		String message = json.toString();
//		Map<String, String> msgMap = new HashMap<String, String>();
//		msgMap.put(ReceiverConstant.GCM, message);
//		msgMap.put("default", "defaultmsg");
//		JSONObject msgMapObject = new JSONObject(msgMap);
//		String sendMSg = msgMapObject.toString();
//		request.setTargetArn(targetArn);
//		request.setMessage(payload1);
//		AmazonSNSClient snsClient = new AmazonSNSClient();
//		final PublishResult result = snsClient.publish(request);
		client.publish(new PublishRequest().withTargetArn(targetArn).withMessage(subject));
		//System.out.println("MessageId: " + client.);
		
		
	}
	
	public void pushNotification(String message, String deviceId) throws Exception {
		// registerWithSNS();
				String targetArn = createEndpoint(deviceId);
				publish(message, targetArn);
			}
	
	

}
