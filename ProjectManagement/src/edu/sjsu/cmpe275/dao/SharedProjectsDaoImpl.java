package edu.sjsu.cmpe275.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.entities.Person;
import edu.sjsu.cmpe275.entities.Project;
import edu.sjsu.cmpe275.entities.SharedProjects;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class SharedProjectsDaoImpl implements SharedProjectsDao {

	private SessionFactory sessionFactory;

	public SharedProjectsDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean shareProject(int projectId, int personId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			SharedProjects newInvite = new SharedProjects();

			newInvite.setSharedProject((Project) session.get(Project.class,
					projectId));
			newInvite.setSharedPerson((Person) session.get(Person.class,
					personId));
			newInvite.setAcceptedFlag('N');
			session.save(newInvite);
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
	public boolean acceptInvite(int projectId, int personId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			SharedProjects acceptInvite = new SharedProjects();
			acceptInvite.setSharedProject((Project) session.get(Project.class,
					projectId));
			acceptInvite.setSharedPerson((Person) session.get(Person.class,
					personId));
			acceptInvite.setAcceptedFlag('Y');
			session.update(acceptInvite);
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

	// Get all pending invites of a person
	@Override
	public List<SharedProjects> getInvites(int personId) {
		Session session = sessionFactory.getCurrentSession();
		List<SharedProjects> invites = new ArrayList<SharedProjects>();
		try {
			session.beginTransaction();
			invites.addAll(session
					.createQuery(
							"from SharedProjects where SHARED_PRSN_ID = :personId AND ACCEPTED_FLAG='N'")
					.setParameter("personId", personId).list());
			session.getTransaction().commit();
			System.out.println("Get invite: success " + invites.size());
		} catch (JDBCConnectionException e) {
			session.getTransaction().rollback();
			return null;
		} catch (HibernateException e) {
			// e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
		return invites;
	}
	

}