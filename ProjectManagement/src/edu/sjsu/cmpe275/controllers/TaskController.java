package edu.sjsu.cmpe275.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.cmpe275.dao.PersonDao;
import edu.sjsu.cmpe275.dao.ProjectDao;
import edu.sjsu.cmpe275.dao.TaskDao;
import edu.sjsu.cmpe275.entities.EntityDetail;
import edu.sjsu.cmpe275.entities.Person;
import edu.sjsu.cmpe275.entities.Project;
import edu.sjsu.cmpe275.entities.Task;


@Controller
@SessionAttributes({"personSessionObj","currProj"})
public class TaskController {
	
	//Initialized via bean
	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private ProjectDao currProjectDao;
	
	@Autowired
	private PersonDao projOwnerDao;
	
	//create task page
	@RequestMapping(value = "/createtask/{projectid}", method = RequestMethod.GET, produces = { "text/html" })
	public Object createTask(@PathVariable("projectid") int projectid,
			@ModelAttribute("personSessionObj") Person personSessionObj,
			ModelAndView model, HttpServletRequest request) {
		model.setViewName("addTask");
		Project currProject=currProjectDao.getProject(projectid);
		model.addObject("currProject", currProject);
		List<Person> sharedPersons = projOwnerDao.getProjectTeam(projectid);
		model.addObject("sharedPersons",sharedPersons);
		model.addObject("projOwner", currProject.getOwner());
		return model;
	}
	
	@RequestMapping(value = "/listtask/{projectid}", method = RequestMethod.GET, produces = { "text/html" })
	public Object listTask(@PathVariable("projectid") int projectid,
			ModelAndView model, HttpServletRequest request) {
		model.setViewName("listTask");
		model.addObject("tasks", taskDao.getAllTasks(projectid));
		return model;
	}
	
	@RequestMapping(value = "/updatetask", method = RequestMethod.GET, produces = { "text/html" })
	public Object updateTask(ModelAndView model, HttpServletRequest request) {
		model.setViewName("updateTask");
		return model;
	}
	
		// 1> API to create task for a project
	@RequestMapping(value = "/createtask", method = RequestMethod.POST, produces = { "text/html" })
	public Object createTask(@RequestParam(value ="projectid") int projectid,
			@RequestParam(value ="title") String title,
			@RequestParam(value ="description") String description,
			@RequestParam(value ="assigneeid") String assigneeid,
			@RequestParam(value ="state") String state,
			@RequestParam(value ="estimatedwork") int estimatedwork,
			@RequestParam(value ="actualwork") int actualwork,ModelAndView model, HttpServletRequest request){
		System.out.println("Inside create  Task API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		model.setViewName("listTask");
		Task task = new Task();
		Project currProject=currProjectDao.getProject(projectid);
		task.setActualWork(actualwork);
		task.setEstimatedWork(estimatedwork);
		task.setTaskDetail(new EntityDetail(title,description,state));
		task.setAssignee(projOwnerDao.getPerson(assigneeid));
		task.setProject(currProject);
		taskDao.addTask(task);
		model.addObject("tasks", taskDao.getAllTasks(projectid));
		model.addObject("currProject", currProject);
		return model;
	}
	
}

