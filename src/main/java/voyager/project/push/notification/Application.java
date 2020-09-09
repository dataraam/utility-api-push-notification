package voyager.project.push.notification;

import java.util.concurrent.Executor;

import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.rx.RxClient;
import org.glassfish.jersey.client.rx.rxjava.RxObservable;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
@ComponentScan("voyager.project.push.notification.api.*")
public class Application {

//	@Autowired
	//private SQSPullService sqspullservices;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public RxClient<RxObservableInvoker> httpClient()
	{
		return RxObservable.from(ClientBuilder.newClient(new ClientConfig()))
				.property(ClientProperties.READ_TIMEOUT, 25000)
				.property(ClientProperties.ASYNC_THREADPOOL_SIZE, 100)
				.property(ClientProperties.CONNECT_TIMEOUT, 25000);
	}

	@Bean(name = "pushNotificationPoolExecutor")
	public Executor pushNotificationPoolExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setQueueCapacity(1000);
		threadPoolTaskExecutor.setCorePoolSize(10);
		threadPoolTaskExecutor.setMaxPoolSize(25);
		return threadPoolTaskExecutor;	
		
	}
}
