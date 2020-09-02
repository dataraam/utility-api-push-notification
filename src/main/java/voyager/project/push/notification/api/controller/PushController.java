package voyager.project.push.notification.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PushController {

	
	@RequestMapping("/balances")
	public String getBalance() {
		return "get balance";
	}
	
}
