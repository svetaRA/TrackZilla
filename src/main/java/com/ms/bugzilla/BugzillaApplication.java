package com.ms.bugzilla;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.bugzilla.loaneligibility.Acknowledgement;
import com.ms.bugzilla.loaneligibility.CustomerRequest;
import com.ms.bugzilla.soapclient.SoapClinet;

@SpringBootApplication
@EnableCaching
@RestController
public class BugzillaApplication {
	private static final Logger log = LoggerFactory.getLogger(BugzillaApplication.class);
	@Autowired
	private SoapClinet clinet;

	// =========================================== Method to get Loan status from
	// soap web service ==========================================
	@PostMapping("/getLoanStatus")
	public Acknowledgement invokeSoapClientToGetLoanStatus(@RequestBody CustomerRequest request) {
		log.debug("invoking soap client to get loan status***");

		return clinet.getLoanStatus(request);

	}

	public static void main(String[] args) {
		SpringApplication.run(BugzillaApplication.class, args);

	}

	/*
	 * @Bean public CommandLineRunner demo(ApplicationRepository
	 * applicationrespository) { return (args) ->{
	 * 
	 * applicationrespository.save(new
	 * Application("Trackzilla","shweta.rautela","Application for tracking bugs."));
	 * applicationrespository.save(new Application("Expenses",
	 * "meethi.heaven","Application to track expense reports."));
	 * applicationrespository.save(new Application("Notifications",
	 * "m_heaven","Application to send alerts and notifications to users."));
	 * for(Application application : applicationrespository.findAll()) {
	 * log.info("The application is: " + application.toString());
	 * 
	 * } };
	 * 
	 * }
	 */

}
