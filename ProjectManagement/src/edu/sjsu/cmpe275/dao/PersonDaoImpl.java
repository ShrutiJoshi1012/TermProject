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
import org.hibernate.Query;
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
		int total_planned_tasks;
		int total_cancelled_tasks;
		int finsished_tasks;
		int unfinsished_tasks;
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
					for (int i = 0; i < ownedProjects.size(); i++){
						ownedProjects.get(i).setOwner(null);

						String select_tot_proj_Query= "select COUNT(*) as total_projects FROM Task T WHERE Project_id ="+ownedProjects.get(i).getProjectId(); 
						total_planned_tasks= ((Long)session.createQuery(select_tot_proj_Query).uniqueResult()).intValue();
						System.out.println("total_proj:"+total_planned_tasks);
						ownedProjects.get(i).setTotal_planned_tasks(total_planned_tasks);

						String select_cancel_proj_Query= "select COUNT(*) as cancelled_projects from Task T WHERE STATE='Cancelled' and Project_id ="+ownedProjects.get(i).getProjectId(); 
						total_cancelled_tasks= ((Long)session.createQuery(select_cancel_proj_Query).uniqueResult()).intValue();
						System.out.println("cancel_proj:"+total_cancelled_tasks);
						ownedProjects.get(i).setTotal_cancelled_tasks(total_cancelled_tasks);

						String select_finish_proj_Query= "select COUNT(*) as finished_projects from Task T WHERE STATE='Finished' and Project_id ="+ownedProjects.get(i).getProjectId(); 
						finsished_tasks= ((Long)session.createQuery(select_finish_proj_Query).uniqueResult()).intValue();
						System.out.println("finish_proj:"+finsished_tasks);
						ownedProjects.get(i).setFinsished_tasks(finsished_tasks);

						String select_unfinish_proj_Query= "select COUNT(*) as unfinished_projects from Task T WHERE STATE not in ('Finished','Cancelled') and Project_id ="+ownedProjects.get(i).getProjectId(); 
						unfinsished_tasks= ((Long)session.createQuery(select_unfinish_proj_Query).uniqueResult()).intValue();
						System.out.println("unfinish_proj:"+unfinsished_tasks);
						ownedProjects.get(i).setUnfinsished_tasks(unfinsished_tasks);


						String selectQuery= "SELECT NAME,COUNT(TASK_ID) as TaskCount from task  ";
						selectQuery=selectQuery+ "  inner join person where ASSIGNEE_ID=PERSON_ID ";
						selectQuery=selectQuery+" and PROJECT_ID="+ownedProjects.get(i).getProjectId()+" and STATE='Finished' ";
						selectQuery=selectQuery+" group by NAME ORDER BY 2 DESC";
						Query query = session.createSQLQuery(selectQuery);
						List<Person> PersonList=new ArrayList<Person>();
						
				        List list = query.list();
				        for (Object object : list) {
				        	Person memberpersons=new Person();
				            Object[] objArray = (Object[])object;
				            memberpersons.setName(objArray[0].toString());
				            memberpersons.setTaskCount(Integer.parseInt(objArray[1].toString()));
				            PersonList.add(memberpersons);
				        }
				        ownedProjects.get(i).setMembers(PersonList);

					}
					person.setOwnedProjects(ownedProjects);
				}
				List<Project> sharedProjects = new ArrayList<Project>();

				List<SharedProjects> sharedProjectEntries = session
						.createQuery(
								"from SharedProjects where SHARED_PRSN_ID = :id and accepted_flag='Y'")
								.setParameter("id", person.getPersonId()).list();
				if (sharedProjectEntries.size() > 0) {
					for (int i = 0; i < sharedProjectEntries.size(); i++) {

						sharedProjects.add(sharedProjectEntries.get(i)
								.getSharedProject());
						sharedProjects.get(i).setOwner(null);

						String select_tot_proj_Query= "select COUNT(*) as total_projects FROM Task T WHERE Project_id ="+sharedProjects.get(i).getProjectId(); 
						total_planned_tasks= ((Long)session.createQuery(select_tot_proj_Query).uniqueResult()).intValue();
						System.out.println("total_proj:"+total_planned_tasks);
						sharedProjects.get(i).setTotal_planned_tasks(total_planned_tasks);

						String select_cancel_proj_Query= "select COUNT(*) as cancelled_projects from Task T WHERE STATE='Cancelled' and Project_id ="+sharedProjects.get(i).getProjectId(); 
						total_cancelled_tasks= ((Long)session.createQuery(select_cancel_proj_Query).uniqueResult()).intValue();
						System.out.println("cancel_proj:"+total_cancelled_tasks);
						sharedProjects.get(i).setTotal_cancelled_tasks(total_cancelled_tasks);

						String select_finish_proj_Query= "select COUNT(*) as finished_projects from Task T WHERE STATE='Finished' and Project_id ="+sharedProjects.get(i).getProjectId(); 
						finsished_tasks= ((Long)session.createQuery(select_finish_proj_Query).uniqueResult()).intValue();
						System.out.println("finish_proj:"+finsished_tasks);
						sharedProjects.get(i).setFinsished_tasks(finsished_tasks);

						String select_unfinish_proj_Query= "select COUNT(*) as unfinished_projects from Task T WHERE STATE not in ('Finished','Cancelled') and Project_id ="+sharedProjects.get(i).getProjectId(); 
						unfinsished_tasks= ((Long)session.createQuery(select_unfinish_proj_Query).uniqueResult()).intValue();
						System.out.println("unfinish_proj:"+unfinsished_tasks);
						sharedProjects.get(i).setUnfinsished_tasks(unfinsished_tasks);


						String selectQuery= "SELECT NAME,COUNT(TASK_ID) as TaskCount from task  ";
						selectQuery=selectQuery+ "  inner join person where ASSIGNEE_ID=PERSON_ID ";
						selectQuery=selectQuery+" and PROJECT_ID="+sharedProjects.get(i).getProjectId()+" and STATE='Finished' ";
						selectQuery=selectQuery+" group by NAME ORDER BY 2 DESC";
						Query query = session.createSQLQuery(selectQuery);
						List<Person> PersonList=new ArrayList<Person>();
						
				        List list = query.list();
				        for (Object object : list) {
				        	Person memberpersons=new Person();
				            Object[] objArray = (Object[])object;
				            memberpersons.setName(objArray[0].toString());
				            memberpersons.setTaskCount(Integer.parseInt(objArray[1].toString()));
				            PersonList.add(memberpersons);
				        }
				        sharedProjects.get(i).setMembers(PersonList);
						
						
						

					}

					person.setSharedProjects(sharedProjects);
				}
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			// e.printStackTrace();
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
			System.out.println("Hibernate exception occured"+e);
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

				team.add(sharedProjectsList.get(i).getSharedPerson());

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