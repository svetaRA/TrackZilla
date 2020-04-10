package com.ms.bugzilla.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ms.bugzilla.entity.Application;
import com.ms.bugzilla.entity.AuthenticateBean;
import com.ms.bugzilla.entity.User;
import com.ms.bugzilla.exeption.ApplicationNotFoundException;
import com.ms.bugzilla.repository.ApplicationRepository;

@Service
@CacheConfig(cacheNames="applicationCache")
public class ApplicationServiceImpl implements ApplicationService{
	
	private final Logger LOG = LoggerFactory.getLogger(ApplicationServiceImpl.class);

	@Autowired
	ApplicationRepository applicationRepository;
	/*
	 * @Autowired CacheManager cachemgr;
	 */

	

	@Override
		public List<Application> listApplication() {
		// TODO Auto-generated method stub
		LOG.info("returing applications from list applications");
		return (List<Application>)applicationRepository.findAll();
	}

	@Override
	@CachePut(key = "#result.id")
		public Application findApplicationbyId(long id) throws ApplicationNotFoundException  {
		// TODO Auto-generated method stub
		Optional<Application>  oppApplication = applicationRepository.findById(id);
	//LOG.info("isPresetnt"+oppApplication.isPresent() +"" +oppApplication.get().getName());
		if (oppApplication.isPresent()){
			return oppApplication.get();
		} else {
            throw new ApplicationNotFoundException("Application Not Found");
		}
	 
	}

	@Override
	@Cacheable(value = "areaOfCircleCache", key = "#radius", condition = "#radius > 5")
	public double areaOfCircle(int radius) {
	    LOG.info("calculate the area of a circle with a radius of {}", radius);
	    return Math.PI * Math.pow(radius, 2);
	  }
	
	


	@Override
	public void evictAllCaches() {
		// TODO Auto-generated method stub
		 LOG.info("evicted cache");
		// cachemgr.getCache("areaOfCircleCache", Integer.class, Double.class);
	}
 
	@Override
	public Application addApplication(Application application) {
		// TODO Auto-generated method stub
		return applicationRepository.save(application);
	}

	@Override
	public User getUsername(String name) {
		// TODO Auto-generated method stub
		User user= new User();
		user.setUsername("shweta");
		return user;
	}

	@Override
	public AuthenticateBean getUserAuthorized() {
		// TODO Auto-generated method stub
		AuthenticateBean authBean = new AuthenticateBean("You are Authenticated");
		//authBean.setUsername("You are auth");
		return authBean;
	}



}
