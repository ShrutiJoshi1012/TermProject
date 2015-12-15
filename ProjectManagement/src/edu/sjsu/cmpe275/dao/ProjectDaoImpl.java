package edu.sjsu.cmpe275.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.entities.Project;
import edu.sjsu.cmpe275.entities.SharedProjects;
import edu.sjsu.cmpe275.entities.Person;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class ProjectDaoImpl implements ProjectDao{

	private SessionFactory sessionFactory;

	public ProjectDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	// 1> Create a Project in the database
	@Override
	public boolean addProject(Project project) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(project);
			session.flush();
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			
			session.getTransaction().rollback();
			return false;
		} catch (JDBCConnectionException e) {
			session.getTransaction().rollback();
			return false;
		} catch (HibernateException e) {
			//e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
		return true;
		
	}

	// 2> Get a Project from the database
	@Override
	public Project getProject(int id) {
		Project project = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			project= (Project) session.get(
					Project.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			//e.printStackTrace();
			session.getTransaction().rollback();
		}
		return project;
	}

	// 3> Update a Project in the database
	@Override
	public boolean updateProject(Project project) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(project);	
			session.getTransaction().commit();
		} catch (JDBCConnectionException e) {
			session.getTransaction().rollback();
			return false;
		} catch (HibernateException e) {
			// e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
		return true;
	}
	
	
	// 4> Delete a Project in the database
	@Override
	public void deleteProject(Project project) {
		
	}

	// 5> Get all projects from the database
	@Override
	public List<Project> getAllProjects(int Id) {
		return null;
	}


	// 6> Change the state of a project based on input
	@Override
	public boolean changeProjectState(int projectId, String newState) {
		// TODO Auto-generated method stub
		return false;
	}


}
