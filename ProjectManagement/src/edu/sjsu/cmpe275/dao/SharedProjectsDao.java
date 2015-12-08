package edu.sjsu.cmpe275.dao;

import java.util.List;

import edu.sjsu.cmpe275.entities.SharedProjects;

public interface SharedProjectsDao {
	boolean shareProject(int projectId,int personId);
	boolean acceptInvite(int projectId,int personId);
	List<SharedProjects> getInvites(int personId);	
}
