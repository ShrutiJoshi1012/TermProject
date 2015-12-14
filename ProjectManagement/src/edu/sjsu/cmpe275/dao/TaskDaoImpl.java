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
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(task);
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

	@Override
	public Task getTask(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Task task=null;
		try {
			session.beginTransaction();
			task=(Task)session.get(Task.class,id);
			session.getTransaction().commit();
		} catch (JDBCConnectionException e) {
			session.getTransaction().rollback();
			return null;
		} catch (HibernateException e) {
			//e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
		return task;
	}

	// Update Task
		@Override
		public boolean updateTask(Task task) {
			Session session = sessionFactory.getCurrentSession();
			try {
				session.beginTransaction();
				session.saveOrUpdate(task);
				session.getTransaction().commit();
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

	@Override
	public boolean deleteTask(Task task) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<Task> getAllTasks(int projectId) {
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
			session.getTransaction().rollback();
		}
		return tasks;
	}

}
