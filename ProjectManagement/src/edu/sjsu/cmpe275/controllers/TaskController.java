package edu.sjsu.cmpe275.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.sjsu.cmpe275.dao.TaskDao;


@Controller
public class TaskController {
	
	//Initialized via bean
	@Autowired
	private TaskDao taskDao;
	
	
	//Write your APIs here
	// 1> API to create task for a project
	@RequestMapping(value = "/createtask", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody
	ResponseEntity<?> createTask(@RequestParam(value ="projectName") String projectName,
			@RequestParam(value ="title") String title,
			@RequestParam(value ="description") String description,
			@RequestParam(value ="assigneeName") String assigneeName,
			@RequestParam(value ="state") String state,
			@RequestParam(value ="estimatedWork") String estimatedWork,
			@RequestParam(value ="actualWork") String actualWork){
		System.out.println("Inside create  API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		//yet to write
		return null;
	}
	
}

