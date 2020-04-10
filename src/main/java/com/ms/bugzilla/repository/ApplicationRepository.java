package com.ms.bugzilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.bugzilla.entity.Application;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
