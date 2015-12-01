package edu.sjsu.cmpe275.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.cmpe275.dao.TaskDao;
import edu.sjsu.cmpe275.entities.Task;


@Controller
@SessionAttributes("personSessionObj")
public class TaskController {
	
	//Initialized via bean
	@Autowired
	private TaskDao taskDao;
	//create task page
	@RequestMapping(value = "/createtask", method = RequestMethod.GET, produces = { "text/html" })
	public Object createTask(ModelAndView model, HttpServletRequest request) {
		model.setViewName("addTask");
		return model;
	}
	
	@RequestMapping(value = "/listtask", method = RequestMethod.GET, produces = { "text/html" })
	public Object listTask(ModelAndView model, HttpServletRequest request) {
		model.setViewName("listTask");
		return model;
	}
	
	@RequestMapping(value = "/updatetask", method = RequestMethod.GET, produces = { "text/html" })
	public Object updateTask(ModelAndView model, HttpServletRequest request) {
		model.setViewName("updateTask");
		return model;
	}
	
	//Write your APIs here
	// 1> API to create task for a project
	@RequestMapping(value = "/createtask", method = RequestMethod.POST, produces = { "application/json" })
	public Object createTask(@RequestParam(value ="projectName") String projectName,
			@RequestParam(value ="title") String title,
			@RequestParam(value ="description") String description,
			@RequestParam(value ="assigneeName") String assigneeName,
			@RequestParam(value ="state") String state,
			@RequestParam(value ="estimatedWork") String estimatedWork,
			@RequestParam(value ="actualWork") String actualWork,ModelAndView model, HttpServletRequest request){
		System.out.println("Inside create  API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		model.setViewName("listProject");
		Task task = new Task();
		
		//yet to write
		return null;
	}
	
}

