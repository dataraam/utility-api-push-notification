package voyager.project.push.notification.api.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import voyager.project.push.notification.api.constants.ReceiverConstant;
import voyager.project.push.notification.api.model.TestModel;

@Service
public class SQSPullService {
	
	@Autowired
	PushNotificationService pushNotificationService;

	public void pullFromQueue() throws Exception {
		AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
		while(true)
		{
			ReceiveMessageRequest pushmessMessageRequest = new ReceiveMessageRequest(ReceiverConstant.PUSH_QUEUE_URL)
					.withMaxNumberOfMessages(1).withWaitTimeSeconds(1);
			List<Message> pushMessages = sqs.receiveMessage(pushmessMessageRequest).getMessages();
			Message pushMessage = pushMessages.isEmpty() ? null : pushMessages.get(0);
			
			if(pushMessage != null) {
				JSONObject messageObject = new JSONObject(pushMessage.getBody());
				ObjectMapper mapper = new ObjectMapper();
				TestModel testModel = mapper.readValue(messageObject.getString("Message"), TestModel.class);
				String templateMessage = testModel.getNotificationText();
				if(templateMessage != null) {
					pushNotificationService.pushNotification(templateMessage,testModel.getRecipientId());
					
					sqs.deleteMessage(ReceiverConstant.PUSH_QUEUE_URL,pushMessage.getReceiptHandle());
				}
			}
			Thread.currentThread();
			Thread.sleep(1000);
		}
	}
	
}
