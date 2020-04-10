package com.ms.bugzilla.service;

import java.util.List;

import com.ms.bugzilla.entity.Application;
import com.ms.bugzilla.entity.AuthenticateBean;
import com.ms.bugzilla.entity.User;
import com.ms.bugzilla.exeption.ApplicationNotFoundException;

public interface ApplicationService {
	List<Application> listApplication();

	Application findApplicationbyId(long id) throws ApplicationNotFoundException;

	double areaOfCircle(int radious);

	void evictAllCaches();

	Application addApplication(Application application) throws ApplicationNotFoundException;

	User getUsername(String name);

	AuthenticateBean getUserAuthorized();
}
