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
		System.out.println("IN CreateProject");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
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
	public String updateProject(Project project) {
		System.out.println("IN UpdateProject");
		Session session = sessionFactory.getCurrentSession();
		if(project.getProjectDetail().getState().matches("Planning"))
		{
			int newstate=0;
			int assigneeexist=0;
			try{
				session.beginTransaction();
				String select_chk_newQuery= "SELECT COUNT(*) as NewProjects FROM Task T WHERE state='New' and Project_id ="+project.getProjectId(); 
				newstate= ((Long)session.createQuery(select_chk_newQuery).uniqueResult()).intValue();
				System.out.println("New values:"+newstate);

				if(newstate>0)
				{
					session.getTransaction().rollback();
					return "incompletetask";
				}
				else {

					String select_chk_assignee_Query= "SELECT COUNT(*) as AssigneeNullexists FROM Task T WHERE state='Assigned' and (ASSIGNEE_ID IS null or ESTIMATED_WORK IS null) and Project_id ="+project.getProjectId(); 
					assigneeexist= ((Long)session.createQuery(select_chk_assignee_Query).uniqueResult()).intValue();
					System.out.println("New values:"+assigneeexist);
					if(assigneeexist>0)
					{
						session.getTransaction().rollback();
						return "incompletetask";
					}
					else {
						session.update(project);	
						session.getTransaction().commit();
						System.out.println("update project result: success");
					}
				}
			}
			catch (JDBCConnectionException e) {
				System.out.println("Connection lost");
				session.getTransaction().rollback();
				return "incompletetask";
			} catch (HibernateException e) {
				// e.printStackTrace();
				System.out.println("Hibernate exception occured");
				session.getTransaction().rollback();
				return "incompletetask";
			}
			catch(Exception ex)
			{
				System.out.println("Exception:"+ex);
				return "incompletetask";
			}
			return "assignedtask";
		}
		else {
			int New_Assigned_started;
			int finishedcount;
			try{
				session.beginTransaction();
				String select_chk_new_assn_start_Query= "SELECT COUNT(*) as New_Assigned_started FROM Task T WHERE  state in ('New') and Project_id ="+project.getProjectId(); 
				New_Assigned_started= ((Long)session.createQuery(select_chk_new_assn_start_Query).uniqueResult()).intValue();
				System.out.println("New_Assigned_started:"+New_Assigned_started);
				if(New_Assigned_started>0)
				{
					session.getTransaction().rollback();
					return "unfinishedtask";
				}
				else {

					String select_chk_finishd_Query= "SELECT COUNT(*) as New_Assigned_started FROM Task T WHERE state in ('Finished') and Project_id ="+project.getProjectId(); 
					finishedcount= ((Long)session.createQuery(select_chk_finishd_Query).uniqueResult()).intValue();
					System.out.println("New values:"+finishedcount);
					if(finishedcount>0)
					{
						session.update(project);	
						session.getTransaction().commit();
						System.out.println("update project result: success");
						
					}
					else {
						session.getTransaction().rollback();
						return "unfinishedtask";
					}
					
				}
			}
			catch (JDBCConnectionException e) {
				System.out.println("Connection lost");
				session.getTransaction().rollback();
				return "unfinishedtask";
			} catch (HibernateException e) {
				// e.printStackTrace();
				System.out.println("Hibernate exception occured");
				session.getTransaction().rollback();
				return "unfinishedtask";
			}
			catch(Exception ex)
			{
				System.out.println("Exception:"+ex);
				return "unfinishedtask";
			}

			return "finishedtask";
		}

	}


	// 4> Delete a Project in the database
	@Override
	public void deleteProject(Project project) {

	}




}
