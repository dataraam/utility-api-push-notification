package voyager.project.push.notification.api.constants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class ReceiverConstant  {
	
	public static final String PUSH_QUEUE_URL = "https://sqs.us-east-1.amazonaws.com/221807171438/test_sqs";
	public static final String MSG_SUBJECT = "Subject";
	public static final String MSG_BODY = "Body";
	public static final String APP_ENDPOINT_ARN = "arn:aws:sns:us-east-1:221807171438:app/GCM/Push-Notification";
	public static final String TOKEN = "Token";
	public static final String GCM_MSG_STRUCTURE = "json";
	public static final String TEXT = "text";
	public static final String NOTIFICATION = "notification";
	public static final String GCM = "GCM";

}
