package voyager.project.push.notification.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.connect.model.Credentials;
import com.amazonaws.services.lexruntime.model.GetSessionResult;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;
import com.fasterxml.jackson.databind.ser.BasicSerializerFactory;

@Configuration
public class NotificationConfguration {

	private static final Integer TEMPORARY_CREDENTIALS_DURATION_DEFAULT = 7200;
	
	@Bean(name = "sessionCredentials")
	public BasicSessionCredentials sessionCredentials( ) {
		AWSSecurityTokenServiceClient stsClient = (AWSSecurityTokenServiceClient) AWSSecurityTokenServiceClientBuilder
				.defaultClient();
		
		GetSessionTokenRequest sessionTokenRequest = new GetSessionTokenRequest();
		sessionTokenRequest.setDurationSeconds(TEMPORARY_CREDENTIALS_DURATION_DEFAULT);
		GetSessionTokenResult getSessionTokenResult = stsClient.getSessionToken(sessionTokenRequest);
		com.amazonaws.services.securitytoken.model.Credentials session_creds = getSessionTokenResult.getCredentials();
		BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(session_creds.getAccessKeyId(), session_creds
				.getSecretAccessKey(), session_creds.getSessionToken());
		return sessionCredentials;
		
	}

}
