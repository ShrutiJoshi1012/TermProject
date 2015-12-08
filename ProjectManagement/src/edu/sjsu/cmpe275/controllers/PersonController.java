package edu.sjsu.cmpe275.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.cmpe275.dao.PersonDao;
import edu.sjsu.cmpe275.dao.SharedProjectsDao;
import edu.sjsu.cmpe275.entities.Person;

@Controller
@SessionAttributes("personSessionObj")
public class PersonController {

	// Initialized via bean
	@Autowired
	private PersonDao personDao;

	@Autowired
	private SharedProjectsDao sharedProjectsDao;

	// 1> Default handler
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "text/html" })
	public Object getHome(ModelAndView model, HttpServletRequest request) {
		model.setViewName("index");
		return model;
	}

	// 2> Dashboard handler
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET, produces = { "text/html" })
	public Object getDashboard(@ModelAttribute("personSessionObj") Person person,ModelAndView model, HttpServletRequest request,
			SessionStatus status) {
		if (status.isComplete() == true) {
			model.setViewName("index");
			return model;
		} else {
			model.setViewName("dashboard");
			model.addObject("invites",
					sharedProjectsDao.getInvites(person.getPersonId()));
			return model;
		}
	}

	// 2> SignIn handler
	@RequestMapping(value = "/signin", method = RequestMethod.GET, produces = { "text/html" })
	public Object getSignInPage(ModelAndView model, HttpServletRequest request) {
		model.setViewName("signin");
		return model;
	}

	// 3> SignIn handler
	@RequestMapping(value = "/signup", method = RequestMethod.GET, produces = { "text/html" })
	public Object getSignUpPage(ModelAndView model, HttpServletRequest request) {
		model.setViewName("signup");
		return model;
	}

	// Logout Functionality
	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = { "text/html" })
	public Object logout(ModelAndView model, HttpServletRequest request,
			SessionStatus status) {
		status.setComplete();
		model.setViewName("index");
		return model;
	}
    
	// 2> SignIn handler
		@RequestMapping(value = "/updateprofile", method = RequestMethod.GET, produces = { "text/html" })
		public Object getProfile(ModelAndView model, HttpServletRequest request) {
			model.setViewName("updateProfile");
			return model;
		}
	
	
	// 4> API to signup a user
	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = { "text/html" })
	public Object signUp(@RequestParam(value = "name") String name,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "c_password") String c_password,
			@RequestParam(value = "emailid") String emailid,
			@RequestParam(value = "description") String description,
			ModelAndView model, HttpServletRequest request) {
		System.out.println("Inside signup API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		
		Person person = new Person(name, password, emailid, description);
		if (!personDao.addPerson(person) && password != c_password) {
			Map<String, String> message = new HashMap<String, String>();
			message.put("message",
					"Either existing email address <br>Or<br> Passwords dont match ");
			model.setViewName("signup");
			
			model.addObject("message", message);
			return model;
		}else{
			model.setViewName("dashboard");
		person = personDao.getPerson(emailid);
		model.addObject("personSessionObj", person);
		model.addObject("invites",
			sharedProjectsDao.getInvites(person.getPersonId()));
		return model;
		}
	}

	// 4> API to signin a user
	@RequestMapping(value = "/signin", method = RequestMethod.POST, produces = { "text/html" })
	public Object signIn(@RequestParam(value = "emailid") String emailid,
			@RequestParam(value = "password") String password,
			ModelAndView model, HttpServletRequest request) {
		System.out.println("Inside signin API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		model.setViewName("dashboard");
		Person person = personDao.getPerson(emailid);
		if (person == null || !person.getPassword().equals(password)) {
			Map<String, String> message = new HashMap<String, String>();
			message.put("message", "Login Failed! <br> Please try again!");
			model.setViewName("signin");
			model.addObject("message", message);
			return model;
		}
		model.addObject("invites",
				sharedProjectsDao.getInvites(person.getPersonId()));
		System.out.println("Invites in API:"
				+ sharedProjectsDao.getInvites(person.getPersonId()));
		model.addObject("personSessionObj", person);
		return model;

	}



	// 5> API to update a user
	@RequestMapping(value = "/updateprofile", method = RequestMethod.POST, produces = { "text/html" })
	public Object updateProfile(@RequestParam(value = "personid") int personId,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "emailid") String emailid,
			@RequestParam(value = "description") String description,
			ModelAndView model, HttpServletRequest request) {
		System.out.println("Inside update API ");
		model.setViewName("updateProfile");
		HttpHeaders responseHeaders = new HttpHeaders();
		Person person = new Person(name, password, emailid, description);
		person.setPersonId(personId);
		boolean isUpdated = personDao.updatePerson(person);

		if (!isUpdated)
			return new ResponseEntity<String>("fail", responseHeaders,
					HttpStatus.BAD_REQUEST);
		else {
			Person updatedPerson = personDao.getPerson(person.getEmailid());
			model.addObject("personSessionObj", updatedPerson);
		}
		model.addObject("invites",
				sharedProjectsDao.getInvites(personId));
		return model;

	}

}
