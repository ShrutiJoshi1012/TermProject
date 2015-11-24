package edu.sjsu.cmpe275.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.entities.Project;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class ProjectDaoImpl implements ProjectDao{

	private SessionFactory sessionFactory;

	public ProjectDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addProject(Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Project getProject(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProject(Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProject(Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Project> getAllProjects(int ownerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
