package edu.sjsu.cmpe275.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class EntityDetail {

	private String title;
	private String description;
	private String state;
	
	@Column(name = "TITLE", nullable = false, length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Column(name = "STATE", length = 45)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
