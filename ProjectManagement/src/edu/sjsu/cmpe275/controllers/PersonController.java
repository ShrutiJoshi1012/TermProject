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
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.cmpe275.dao.PersonDao;
import edu.sjsu.cmpe275.entities.Person;

@Controller
public class PersonController {

	//Initialized via bean
	@Autowired
	private PersonDao personDao;

	// 1> API to signup a user
	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody
	ResponseEntity<?> signUp(@RequestParam(value ="name") String name,
			@RequestParam(value ="password") String password,
			@RequestParam(value ="emailid") String emailid,
			@RequestParam(value ="description") String description) {
		System.out.println("Inside signup API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		Person person=new Person(name,password,emailid,description);
		if(!personDao.addPerson(person))
		return new ResponseEntity<String>("fail", responseHeaders,
				HttpStatus.OK);
		return new ResponseEntity<Person>(person, responseHeaders,
				HttpStatus.OK);
		
	}

	
	// 2> API to signin a user
	@RequestMapping(value = "/signin", method = RequestMethod.POST, produces ={ "application/json" })
	public @ResponseBody
	ResponseEntity<?> signIn(@RequestParam(value ="emailid") String emailid,
			@RequestParam(value ="password") String password
			) {
		System.out.println("Inside signin API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		Person person=personDao.getPerson(emailid);
		if( person != null)
		return new ResponseEntity<Person>(person, responseHeaders,
				HttpStatus.OK);
		return new ResponseEntity<String>("NotFound", responseHeaders,
				HttpStatus.OK);
		
	}


	// 3> API to update a user
	
	@RequestMapping(value = "/updateprofile", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody
	ResponseEntity<?> updateProfile(@RequestParam(value ="personId") int personId,
			@RequestParam(value ="name") String name,
			@RequestParam(value ="password") String password,
			@RequestParam(value ="emailid") String emailid,
			@RequestParam(value ="description") String description) {
		System.out.println("Inside signup API ");
		HttpHeaders responseHeaders = new HttpHeaders();
		Person person=new Person(name,password,emailid,description);
		person.setPersonId(personId);
		boolean isUpdated=personDao.updatePerson(person);
		if(!isUpdated)
		return new ResponseEntity<String>("fail", responseHeaders,
				HttpStatus.OK);
		return new ResponseEntity<String>("success", responseHeaders,
				HttpStatus.OK);
		
	}


}
