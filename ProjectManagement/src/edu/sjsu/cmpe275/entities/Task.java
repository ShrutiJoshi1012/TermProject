package edu.sjsu.cmpe275.entities;

// Generated Nov 22, 2015 8:22:29 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Task generated by hbm2java
 */
@Entity
@Table(name = "TASK", catalog = "CMPE275_PROJECT_MGMT_DB")
public class Task implements java.io.Serializable {

	private int taskId;
	
	private Project project;

	private Person assignee;
	
	private int estimatedWork;
	private int actualWork;
	
	
	@Transient
	@Embedded
	private EntityDetail taskDetail;

	public Task() {
	}


	public Task(Project project, 
			Person assignee,  int estimatedWork,
			int actualWork, EntityDetail taskDetail) {
		this.project = project;
		this.taskDetail = taskDetail;
		this.assignee = assignee;
		this.estimatedWork = estimatedWork;
		this.actualWork = actualWork;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TASK_ID", unique = true, nullable = false)
	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}


	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	
	


	public Person getAssignee() {
		return this.assignee;
	}

	public void setAssignee(Person assignee) {
		this.assignee = assignee;
	}

	

	@Column(name = "ESTIMATED_WORK")
	public Integer getEstimatedWork() {
		return this.estimatedWork;
	}

	public void setEstimatedWork(Integer estimatedWork) {
		this.estimatedWork = estimatedWork;
	}

	@Column(name = "ACTUAL_WORK")
	public Integer getActualWork() {
		return this.actualWork;
	}

	public void setActualWork(Integer actualWork) {
		this.actualWork = actualWork;
	}
	
	
	public EntityDetail getTaskDetail() {
		return this.taskDetail;
	}

	public void setTitle(EntityDetail taskDetail) {
		this.taskDetail = taskDetail;
	}


}
