package edu.sjsu.cmpe275.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import edu.sjsu.cmpe275.dao.PersonDao;
import edu.sjsu.cmpe275.dao.ProjectDao;
import edu.sjsu.cmpe275.dao.TaskDao;
import edu.sjsu.cmpe275.entities.EntityDetail;
import edu.sjsu.cmpe275.entities.Person;
import edu.sjsu.cmpe275.entities.Project;
import edu.sjsu.cmpe275.entities.Task;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTest {

	ApplicationContext context = new ClassPathXmlApplicationContext(
			"spring-dispatcher-servlet.xml");
	PersonDao personDao = (PersonDao) context.getBean("personDao");
	ProjectDao projectDao = (ProjectDao) context.getBean("projectDao");
	TaskDao taskDao = (TaskDao) context.getBean("taskDao");

	@Test
	public void createPerson() {
		Person person = new Person("Admin", "pwd123", "admin@gmail.com");
		boolean createPerson = personDao.addPerson(person);
		assert (createPerson);
	}

	@Test
	public void checkExistingPerson() {
		Person person = new Person("Admin", "pwd123", "admin@gmail.com");
		boolean createPerson = personDao.addPerson(person);
		assert (!createPerson);
	}

	@Test
	public void updatePerson() {
		Person person = personDao.getPerson("admin@gmail.com");
		// boolean beforeUpdatePerson=personDao.addPerson(person);
		String oldName = person.getName();
		String newName = "Administartor";
		person.setName(newName);
		boolean updatePerson = personDao.updatePerson(person);
		assert (updatePerson);
		Person newPerson = personDao.getPerson("admin@gmail.com");
		assertEquals(person.getPersonId(), newPerson.getPersonId());
	}

	@Test
	public void createProject() {
		Person person = personDao.getPerson("admin@gmail.com");
		Project project1 = new Project(null, new EntityDetail("Cloud Project",
				"It is a new description", ""));
		Project project2 = new Project(person, new EntityDetail(
				"Cloud Project", "It is a new description", ""));

		assert (project1 == null);
		assert (project2 != null);
	}

	@Test
	public void updateProject() {
		Person person = personDao.getPerson("admin@gmail.com");
		Project project = projectDao.getProject(1);
		int projectId = project.getProjectId();
		String oldTitle = project.getProjectDetail().getTitle();
		String newTitle = "Virtualization Project";
		project.getProjectDetail().setTitle(newTitle);
		String updateProject = projectDao.updateProject(project);
		assert (updateProject != null);

	}

	@Test
	public void addTask() {
		Person assignee = personDao.getPerson("admin@gmail.com");
		Project project = projectDao.getProject(1);
		Task task = new Task(project, assignee, 10, 0, new EntityDetail(
				"Task1", "Desc", "New"));
		boolean createTask = taskDao.addTask(task);
		assert (createTask);

	}

	@Test
	public void updateTask() {
		Person assignee = personDao.getPerson("admin@gmail.com");

		Task oldTask = taskDao.getTask(1);
		Task task = oldTask;
		task.setEstimatedWork(oldTask.getEstimatedWork() + 100);
		boolean updateTask = taskDao.updateTask(task);
		Task newTask = taskDao.getTask(1);

		assert (updateTask);
		assertEquals(oldTask.getEstimatedWork(), newTask.getEstimatedWork());

	}

}