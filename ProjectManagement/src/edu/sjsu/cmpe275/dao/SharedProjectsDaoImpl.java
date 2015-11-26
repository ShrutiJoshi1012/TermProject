package edu.sjsu.cmpe275.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.entities.SharedProjects;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class SharedProjectsDaoImpl implements SharedProjectsDao{

	private SessionFactory sessionFactory;

	public SharedProjectsDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public boolean shareProject(int projectId, int personId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acceptInvite(int projectId, int personId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SharedProjects> getInvites(int personId) {
		// TODO Auto-generated method stub
		return null;
	}

}
