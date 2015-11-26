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
	
	
	// 1> Create a Project in the database
	@Override
	public boolean addProject(Project project) {
		System.out.println("IN CreateProject");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			System.out.println("Project with owner inside DAO is : "+ project.getOwner().getEmailid());		
			session.save(project);
			session.flush();
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

	// 2> Get a Project from the database
	@Override
	public Project getProject(int id) {
		System.out.println("IN GetProject");
		Project project = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			project= (Project) session.get(
					Project.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			//e.printStackTrace();
			System.out.println("Hibernate exception occured");
			session.getTransaction().rollback();
		}
		return project;
	}

	// 3> Update a Project in the database
	@Override
	public void updateProject(Project project) {
		
	}
	
	
	// 4> Delete a Project in the database
	@Override
	public void deleteProject(Project project) {
		
	}

	// 5> Get all projects from the database
	@Override
	public List<Project> getAllProjects(int ownerId) {
		return null;
	}


	// 6> Change the state of a project based on input
	@Override
	public boolean changeProjectState(int projectId, String newState) {
		// TODO Auto-generated method stub
		return false;
	}

}
