package com.ms.bugzilla.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller=new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.ms.bugzilla.loaneligibility");
		//marshaller.setPackagesToScan("com.javatechie.spring.soap.api.loaneligibility");
		
		return marshaller;
	}

}
