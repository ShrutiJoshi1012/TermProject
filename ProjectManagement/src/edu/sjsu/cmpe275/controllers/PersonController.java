package edu.sjsu.cmpe275.controllers;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.cmpe275.dao.PersonDao;
import edu.sjsu.cmpe275.entities.Person;

@Controller
@SessionAttributes("personSessionObj")
public class PersonController {

	// Initialized via bean
	@Autowired
	private PersonDao personDao;

	// 1> Default handler
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "text/html" })
	public Object getHome(ModelAndView model, HttpServletRequest request) {
		model.setViewName("index");
		return model;
	}
	
	// 2> Dashboard handler
		@RequestMapping(value = "/dashboard", method = RequestMethod.GET, produces = { "text/html" })
		public Object getDashboard(ModelAndView model, HttpServletRequest request) {
			//if(model.)
			model.setViewName("dashboard");
			return model;
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

	// 4> API to signup a user
	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = { "text/html" })
	public Object signUp(@RequestParam(value = "name") String name,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "emailid") String emailid,
			@RequestParam(value = "description") String description,ModelAndView model, HttpServletRequest request) {
		System.out.println("Inside signup API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		model.setViewName("dashboard");
		Person person = new Person(name, password, emailid, description);
		if (!personDao.addPerson(person))
			return new ResponseEntity<String>("fail", responseHeaders,
					HttpStatus.OK);
		person=personDao.getPerson(emailid);
		model.addObject("personSessionObj",person);
		return model;
		
		


	}

	// 4> API to signin a user
	@RequestMapping(value = "/signin", method = RequestMethod.POST, produces = { "text/html" })
	public Object signIn(@RequestParam(value = "emailid") String emailid,
			@RequestParam(value = "password") String password,ModelAndView model, HttpServletRequest request) {
		System.out.println("Inside signin API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		model.setViewName("dashboard");
		Person person = personDao.getPerson(emailid);		
		if (person == null || !person.getPassword().equals(password))
			return new ResponseEntity<String>("NotFound", responseHeaders,
					HttpStatus.BAD_REQUEST);
		model.addObject("personSessionObj",person);
		return model;
		

	}

	// 4> API to update a user

	@RequestMapping(value = "/updateprofile", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody
	ResponseEntity<?> updateProfile(
			@RequestParam(value = "personid") int personId,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "emailid") String emailid,
			@RequestParam(value = "description") String description) {
		System.out.println("Inside signup API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		Person person = new Person(name, password, emailid, description);
		person.setPersonId(personId);
		boolean isUpdated = personDao.updatePerson(person);
		Person updatedPerson = null;
		if (!isUpdated)
			return new ResponseEntity<String>("fail", responseHeaders,
					HttpStatus.OK);
		else {
			updatedPerson = personDao.getPerson(person.getEmailid());
		}
		return new ResponseEntity<Person>(updatedPerson, responseHeaders,
				HttpStatus.OK);

	}

}
