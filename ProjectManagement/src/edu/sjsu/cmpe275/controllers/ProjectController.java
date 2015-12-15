package edu.sjsu.cmpe275.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.cmpe275.dao.PersonDao;
import edu.sjsu.cmpe275.dao.ProjectDao;
import edu.sjsu.cmpe275.entities.EntityDetail;
import edu.sjsu.cmpe275.entities.Person;
import edu.sjsu.cmpe275.entities.Project;

@Controller
@SessionAttributes("personSessionObj")
public class ProjectController {

	// Initialized via bean
	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private PersonDao ownerDao;
	

	// 1> Get Project handler
			@RequestMapping(value = "/addproject", method = RequestMethod.GET, produces = { "text/html" })
			public Object getDashboard(ModelAndView model, HttpServletRequest request) {
				model.setViewName("createProject");
				return model;
			}
	

	// 2> Get Project handler
			@RequestMapping(value = "/getallprojects", method = RequestMethod.GET, produces = { "text/html" })
			public Object getAllProjects(ModelAndView model, HttpServletRequest request) {
					model.setViewName("listProject");
					
					return model;
			}
    //3> Update Project handler
			@RequestMapping(value = "/updateproject/{projectid}", method = RequestMethod.GET, produces = { "text/html" })
			public Object updateProject(@PathVariable("projectid")int projectid,ModelAndView model, HttpServletRequest request) {
					model.setViewName("updateProject");
					model.addObject("project",projectDao.getProject(projectid));
					return model;
			}
			
	// 1> API to add project
	@RequestMapping(value = "/addproject", method = RequestMethod.POST, produces = { "text/html" })
	public Object addProject(@RequestParam(value = "title") String title,
			@RequestParam(value = "emailid") String emailid,
			@RequestParam(value = "state") String state,
			@RequestParam(value = "description") String description,ModelAndView model, HttpServletRequest request) {
		System.out.println("Inside addProject API ");
		System.out.println("Email:"+emailid);
		HttpHeaders responseHeaders = new HttpHeaders();
		model.setViewName("listProject");
		Project project = new Project();
		project.setOwner(ownerDao.getPerson(emailid));
		project.setProjectDetail(new EntityDetail(title, description, state));
		
		
		if (!projectDao.addProject(project))
			return new ResponseEntity<String>("fail", responseHeaders,
					HttpStatus.BAD_REQUEST);
		Person person=ownerDao.getPerson(emailid);
	    model.addObject("personSessionObj",person);
	    System.out.println("No of owned projects: "+person.getOwnedProjects().size());
		System.out.println("No of shared projects: "+person.getSharedProjects().size());

		return model;

	}

	// 2> API to get a project
	@RequestMapping(value = "/getproject", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody
	ResponseEntity<?> getProject(
			@RequestParam(value = "projectId") int projectId) {
		System.out.println("Inside getProject API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		Project project = projectDao.getProject(projectId);
		if (project != null)
			return new ResponseEntity<Project>(project, responseHeaders,
					HttpStatus.OK);
		return new ResponseEntity<String>("NotFound", responseHeaders,
				HttpStatus.OK);

	}
	
	// 3> API to get both projects and shared projects
	@RequestMapping(value = "/getbothproject", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody
	ResponseEntity<?> getBothProject(
			@RequestParam(value = "projectId") int projectId) {
		HttpHeaders responseHeaders = new HttpHeaders();		
		return null;
	}
	
	@RequestMapping(value = "/updateproject", method = RequestMethod.POST, produces = {"text/html"})
	public Object updateProjectPost(@RequestParam(value="projectid") int projectid,
			@RequestParam(value="title") String title,
			@RequestParam(value="description") String description,
			@RequestParam(value="prev_state",required = false) String prev_state,
			@RequestParam(value="new_state",required = false) String new_state,
			@RequestParam(value="ownerid") String ownerid,
			ModelAndView model, HttpServletRequest request){
		model.setViewName("listProject");
		Project project = new Project();
		project.setProjectId(projectid);
		project.setOwner(ownerDao.getPerson(ownerid));
		String state=prev_state;
		System.out.println("Pre v state" +prev_state);
		System.out.println("New state" +new_state);
		if(new_state != null)
			state=new_state;
		
		System.out.println("Current state" +state);
		project.setProjectDetail(new EntityDetail(title,description,state));
		projectDao.updateProject(project);
		model.addObject("personSessionObj", ownerDao.getPerson(ownerid));
		return model;
	}
	
}
