package edu.sjsu.cmpe275.dao;

import java.util.List;

import edu.sjsu.cmpe275.entities.Person;
import edu.sjsu.cmpe275.entities.Project;

public interface ProjectDao {
	boolean addProject(Project project);
	Project getProject(int id);
	String updateProject(Project project);	
	void deleteProject(Project project);
	
	
}
