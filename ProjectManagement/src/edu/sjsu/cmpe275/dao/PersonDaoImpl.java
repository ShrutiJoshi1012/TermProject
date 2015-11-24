package edu.sjsu.cmpe275.dao;

/**
 * Class: PersonDAOImpl
 * Implements: PersonDAO
 * isTransactional: Yes
 * Isolation level :Default
 * Transaction Propagation: REQUIRED
 * Dependencies : Uses Hibernate to access DB
 * 
 * 
 * Methods:
 * 1> addPerson
 * 2> updatePerson
 * 3> getPerson
 * 4> deletePerson
 * 5> getFriends
 * 
 * 
 */

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.transaction.annotation.*;

import edu.sjsu.cmpe275.entities.Person;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class PersonDaoImpl implements PersonDao {

	private SessionFactory sessionFactory;

	public PersonDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// 1> Create a Person in the database
	@Override
	public boolean addPerson(Person person) {
		System.out.println("IN CreatePerson");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(person);
			session.getTransaction().commit();
			System.out.println("create person result: success");
		} catch (ConstraintViolationException e) {
			System.out
					.println("EmailID has to be a unique value. This value already exists.");
			session.getTransaction().rollback();
			return false;
		} catch (JDBCConnectionException e) {
			System.out.println("Connection lost");
			session.getTransaction().rollback();
			return false;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
		return true;
	}

	@Override
	public Person getPerson(String emailid) {
		System.out.println("IN GetPerson");
		Person person = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			List<Person> persons= session
					.createQuery("from Person where EMAILID = :emailid")
					.setParameter("emailid", emailid).list();
			if(persons.size()>0)
				person=persons.get(0);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return person;
	}

	@Override
	public void updatePerson(Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePerson(Person person) {
		// TODO Auto-generated method stub

	}

}