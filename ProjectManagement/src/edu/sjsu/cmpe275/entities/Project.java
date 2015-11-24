package edu.sjsu.cmpe275.entities;

// Generated Nov 22, 2015 8:22:29 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Project generated by hbm2java
 */
@Entity
@Table(name = "PROJECT", catalog = "CMPE275_PROJECT_MGMT_DB")
public class Project implements java.io.Serializable {

	private int projectId;
	
	@ManyToOne
	private Person owner;

	@Transient
	@Embedded
	private EntityDetail projectDetail;
	
	public Project() {
	}

	public Project(Person owner, EntityDetail projectDetail) {
		this.owner = owner;
		this.projectDetail=projectDetail;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PROJECT_ID", unique = true, nullable = false)
	public int getProjectId() {
		return this.projectId;
	}

	private void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public Person getOwner() {
		return this.owner;
	}

	public void setOwner(Person owner) {
		this.owner= owner;
	}

	
	public EntityDetail getProjectDetail() {
		return this.projectDetail;
	}

	public void setProjectDetail(EntityDetail projectDetail) {
		this.projectDetail = projectDetail;
	}


}
