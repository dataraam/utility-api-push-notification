package voyager.project.push.notification.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import voyager.project.push.notification.api.service.SQSPullService;


@SpringBootApplication
@EnableAutoConfiguration
public class UtilityApiApp implements CommandLineRunner {

	@Autowired
	private SQSPullService sqspullservices;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(UtilityApiApp.class, args);
	}
	
	@Override
	  public final void run(final String... args) throws Exception {
		sqspullservices.pullFromQueue();
	  }

}
