package com.ms.bugzilla.web;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ms.bugzilla.entity.Application;
import com.ms.bugzilla.entity.User;
import com.ms.bugzilla.exeption.ApplicationNotFoundException;
import com.ms.bugzilla.service.ApplicationService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/tza")
public class TzaController {
	 
	Logger log= LoggerFactory.getLogger(TzaController.class);
	@Autowired 
	ApplicationService applicationService;
	
	
	
	
	// =========================================== Welcome user message ==========================================
		
		@GetMapping("/welcome-user/{name}")
		public ResponseEntity <User> getWelcomeMessage(@PathVariable("name") String name) {
			log.info("get user name***"+name);
			User username= applicationService.getUsername(name);
			return new ResponseEntity<User>(username,HttpStatus.OK);

		} 

	 // =========================================== Get All Applications ==========================================
	
	@GetMapping("/applications")
	public ResponseEntity <List<Application>> getAllApplications() {
		List<Application> list = applicationService.listApplication();
		return new ResponseEntity<List<Application>>(list,HttpStatus.OK);

	}

	// =========================================== Get Application by Id==========================================
	@GetMapping("/applications/{id}")
		public ResponseEntity<Application> getApplicationbyId(@PathVariable("id") long id) {
		log.debug("in controller***");
		try {
			
			return new ResponseEntity<Application>(applicationService.findApplicationbyId(id), HttpStatus.OK);

		} catch (ApplicationNotFoundException ex) {
			System.out.println("in controller1111***");
			//return (ResponseEntity<Application>
			//return new ResponseEntity<Application>(applicationService.findApplicationbyId(id), HttpStatus.);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}
	
	// =========================================== Add Application==========================================

		@PostMapping("/applications")
			public ResponseEntity<Application> addApplication(@RequestBody Application application) {
			log.debug("in controller*** add application**");
			try {
				
				return new ResponseEntity<Application>(applicationService.addApplication(application), HttpStatus.OK);

			} catch (ApplicationNotFoundException ex) {
				System.out.println("in controller1111***");
				//return (ResponseEntity<Application>
				//return new ResponseEntity<Application>(applicationService.findApplicationbyId(id), HttpStatus.);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}

		}
	 
	@GetMapping(path = "/areaOfCircle")
	  public ResponseEntity<Double> areaOfCircle(@RequestParam int radius) {
	    double result = applicationService.areaOfCircle(radius);
	    return ResponseEntity.ok(result);
	}
	
	@GetMapping("clearAllCaches")
    public void clearAllCaches() {
		applicationService.evictAllCaches();
    }}
