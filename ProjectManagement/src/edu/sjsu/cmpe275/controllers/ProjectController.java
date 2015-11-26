package edu.sjsu.cmpe275.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.sjsu.cmpe275.dao.PersonDao;
import edu.sjsu.cmpe275.dao.ProjectDao;
import edu.sjsu.cmpe275.entities.EntityDetail;
import edu.sjsu.cmpe275.entities.Person;
import edu.sjsu.cmpe275.entities.Project;

@Controller
public class ProjectController {

	// Initialized via bean
	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private PersonDao ownerDao;

	// 1> API to add project
	@RequestMapping(value = "/addproject", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody
	ResponseEntity<?> addProject(@RequestParam(value = "title") String title,
			@RequestParam(value = "emailid") String emailid,
			@RequestParam(value = "state") String state,
			@RequestParam(value = "description") String description) {
		System.out.println("Inside addProject API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		Project project = new Project();
		project.setOwner(ownerDao.getPerson(emailid));
		project.setProjectDetail(new EntityDetail(title,description, state));
		System.out.println("Project with owner is : "+ project.getOwner().getEmailid());
		if (!projectDao.addProject(project))
			return new ResponseEntity<String>("fail", responseHeaders,
					HttpStatus.OK);
		return new ResponseEntity<String>("success", responseHeaders,
				HttpStatus.OK);

	}
	
	
	// 2> API to get a project
	@RequestMapping(value = "/getproject", method = RequestMethod.POST, produces ={ "application/json" })
	public @ResponseBody
	ResponseEntity<?> getProject(@RequestParam(value ="projectId") int projectId) {
		System.out.println("Inside getProject API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		Project project=projectDao.getProject(projectId);
		if( project != null)
		return new ResponseEntity<Project>(project, responseHeaders,
				HttpStatus.OK);
		return new ResponseEntity<String>("NotFound", responseHeaders,
				HttpStatus.OK);
		
	}
}
