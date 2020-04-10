package com.ms.bugzilla.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ms.bugzilla.entity.AuthenticateBean;
import com.ms.bugzilla.entity.User;
import com.ms.bugzilla.service.ApplicationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BasicAuthController {
	@Autowired 
	ApplicationService applicationService;
	Logger log= LoggerFactory.getLogger(BasicAuthController.class);
	// =========================================== Basic Auth handler ==========================================
			
			@GetMapping("/basicauth")
			public AuthenticateBean getWelcomeMessage() {
				log.info("in AuthenticateBean***");
				AuthenticateBean message= applicationService.getUserAuthorized();
				return message;

			} 
}
