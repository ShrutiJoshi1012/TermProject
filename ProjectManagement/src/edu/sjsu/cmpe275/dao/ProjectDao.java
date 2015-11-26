package edu.sjsu.cmpe275.dao;

import java.util.List;

import edu.sjsu.cmpe275.entities.Project;

public interface ProjectDao {
	boolean addProject(Project project);
	Project getProject(int id);
	void updateProject(Project project);	
	void deleteProject(Project project);
	List<Project> getAllProjects(int ownerId);
	boolean changeProjectState(int projectId,String newState);
}
