package edu.sjsu.cmpe275.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.transaction.annotation.*;

import edu.sjsu.cmpe275.entities.Project;
import edu.sjsu.cmpe275.entities.Task;



@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class TaskDaoImpl implements TaskDao{

	private SessionFactory sessionFactory;

	public TaskDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public boolean addTask(Task task) {
		System.out.println("IN CreateTask");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(task);
			session.flush();
			session.getTransaction().commit();
			System.out.println("create task result: success");
		} catch (ConstraintViolationException e) {
			System.out
					.println("Invalid Owner_Id or Project Id.. add task failed");
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
	public Task getTask(int id) {
		// TODO Auto-generated method stub
		System.out.println("IN UpdateTask");
		Session session = sessionFactory.getCurrentSession();
		Task task=null;
		try {
			session.beginTransaction();
			task=(Task)session.get(Task.class,id);
			session.getTransaction().commit();
			System.out.println("update task : success");
		} catch (JDBCConnectionException e) {
			System.out.println("Connection lost");
			session.getTransaction().rollback();
			return null;
		} catch (HibernateException e) {
			//e.printStackTrace();
			System.out.println("Hibernate exception occured");
			session.getTransaction().rollback();
			return null;
		}
		return task;
	}

	// Update Task
		@Override
		public boolean updateTask(Task task) {
			System.out.println("IN UpdateTask");
			Session session = sessionFactory.getCurrentSession();
			try {
				session.beginTransaction();
				session.saveOrUpdate(task);
				session.getTransaction().commit();
				System.out.println("update task : success");
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
	public boolean deleteTask(Task task) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<Task> getAllTasks(int projectId) {
		System.out.println("IN GetProject");
		List<Task> tasks=null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			tasks=session
					.createQuery("from Task where PROJECT_ID = :projectId")
					.setParameter("projectId", projectId).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			//e.printStackTrace();
			System.out.println("Hibernate exception occured");
			session.getTransaction().rollback();
		}
		return tasks;
	}

}
