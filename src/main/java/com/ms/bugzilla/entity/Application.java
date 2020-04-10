package com.ms.bugzilla.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Application implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "application_id")
	private Long id;
	

	@Column(name = "app_name", nullable = false)
	private String name;
	@Column(name = "description", length = 2000)
	private String description;
	@Column(name = "owner")
	private String owner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public Application() {}

	public Application(String name, String description, String owner) {

		this.name = name;
		this.owner = owner;
		this.description = description;
	}
	public Application(long id,String name, String description, String owner) {
		this.id=id;
		this.name = name;
		this.owner = owner;
		this.description = description;
	}
	@Override
	public String toString() {
		return "Application [id=" + id + ", name=" + name + ", description=" + description + ", owner=" + owner + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}


}
