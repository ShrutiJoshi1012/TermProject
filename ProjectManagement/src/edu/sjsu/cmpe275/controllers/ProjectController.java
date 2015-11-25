package edu.sjsu.cmpe275.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.sjsu.cmpe275.dao.ProjectDao;


@Controller
public class ProjectController {

	//Initialized via bean
	@Autowired
	private ProjectDao projectDao;
	
	
	//Write your APIs here
}
