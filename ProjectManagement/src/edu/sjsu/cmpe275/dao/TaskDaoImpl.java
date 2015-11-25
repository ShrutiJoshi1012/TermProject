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

import edu.sjsu.cmpe275.entities.Task;



@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class TaskDaoImpl implements TaskDao{

	private SessionFactory sessionFactory;

	public TaskDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public boolean addTask(Task task) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Task getTask(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateTask(Task task) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean deleteTask(Task task) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<Task> getAllTasks(int projectId) {
		// TODO Auto-generated method stub
		return null;
	}

}
