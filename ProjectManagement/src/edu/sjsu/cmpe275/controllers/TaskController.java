package edu.sjsu.cmpe275.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.sjsu.cmpe275.dao.TaskDao;


@Controller
public class TaskController {
	@Autowired
	private TaskDao taskDao;
	
	
	//Write your APIs here
}
