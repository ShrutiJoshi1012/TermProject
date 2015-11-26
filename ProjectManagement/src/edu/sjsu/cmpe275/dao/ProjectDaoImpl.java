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

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class ProjectDaoImpl implements ProjectDao{

	private SessionFactory sessionFactory;

	public ProjectDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public boolean addProject(Project project) {
		System.out.println("IN CreateProject");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(project);
			session.getTransaction().commit();
			System.out.println("create project result: success");
		} catch (ConstraintViolationException e) {
			System.out
					.println("Invalid Owner_Id.. add project failed");
			session.getTransaction().rollback();
			return false;
		} catch (JDBCConnectionException e) {
			System.out.println("Connection lost");
			session.getTransaction().rollback();
			return false;
		} catch (HibernateException e) {
			//e.printStackTrace();
			System.out.println("Hibernate exception occured");
			session.getTransaction().rollback();
			return false;
		}
		return true;
		
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
