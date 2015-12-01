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

import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.transaction.annotation.*;

import edu.sjsu.cmpe275.entities.Person;
import edu.sjsu.cmpe275.entities.Project;
import edu.sjsu.cmpe275.entities.SharedProjects;

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
			// e.printStackTrace();
			System.out.println("Hibernate exception occured");
			session.getTransaction().rollback();
			return false;
		}
		return true;
	}

	// 2> Get a Person from the database
	@Override
	public Person getPerson(String emailid) {
		System.out.println("IN GetPerson");
		Person person = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			List<Person> persons = session
					.createQuery("from Person where EMAILID = :emailid")
					.setParameter("emailid", emailid).list();
			if (persons.size() > 0) {
				person = persons.get(0);
				List<Project> ownedProjects = session
						.createQuery("from Project where OWNER_ID = :id")
						.setParameter("id", person.getPersonId()).list();
				if (ownedProjects.size() > 0) {
					for (int i = 0; i < ownedProjects.size(); i++)
						ownedProjects.get(i).setOwner(null);
					person.setOwnedProjects(ownedProjects);
				}
				List<Project> shProj = new ArrayList<Project>();

				List<SharedProjects> sharedProjects = session
						.createQuery(
								"from SharedProjects where SHARED_PRSN_ID = :id")
						.setParameter("id", person.getPersonId()).list();
				System.out.println("shared project list "
						+ sharedProjects.size());
				for (int i = 0; i < sharedProjects.size(); i++) {

					shProj.add((Project) session.get(Project.class,
							sharedProjects.get(i).getSharedPrjctId()));
					shProj.get(i).setOwner(null);

				}

				person.setSharedProjects(shProj);

			}

			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Hibernate exception occured");
			session.getTransaction().rollback();
		}

		return person;
	}

	// 3> Update a Person in the database
	@Override
	public boolean updatePerson(Person person) {
		System.out.println("IN UpdatePerson");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(person);
			;
			session.getTransaction().commit();
			System.out.println("update person result: success");
		} catch (JDBCConnectionException e) {
			System.out.println("Connection lost");
			session.getTransaction().rollback();
			return false;
		} catch (HibernateException e) {
			// e.printStackTrace();
			System.out.println("Hibernate exception occured");
			session.getTransaction().rollback();
			return false;
		}
		return true;
	}

	// 4> Delete a Person in the database
	@Override
	public void deletePerson(Person person) {

	}

	public List<Person> getProjectTeam(int projectId) {
		System.out.println("IN UpdatePerson");
		Session session = sessionFactory.getCurrentSession();
		List<Person> team = new ArrayList<Person>();
		try {
			session.beginTransaction();
			List<SharedProjects> sharedProjectsList = session
					.createQuery(
							"from SharedProjects where SHARED_PRJCT_ID = :id")
					.setParameter("id", projectId).list();
			for (int i = 0; i < sharedProjectsList.size(); i++) {

				team.add((Person) session.get(Person.class, sharedProjectsList
						.get(i).getSharedPrsnId()));

			}
			

	
			session.getTransaction().commit();
		} catch (HibernateException e) {
			// e.printStackTrace();
			System.out.println("Hibernate exception occured");
			session.getTransaction().rollback();
			return null;
		}
		return team;
	}

}