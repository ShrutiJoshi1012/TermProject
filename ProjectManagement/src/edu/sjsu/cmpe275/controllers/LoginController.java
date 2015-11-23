package edu.sjsu.cmpe275.controllers;

import javax.servlet.http.HttpServletRequest;

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
public class LoginController {

	// Initialized via Bean
		@Autowired
		private PersonDao personDao;
		
		
		// 1> API to signup a user
		@RequestMapping(value = "/signup",  method = RequestMethod.POST, produces = { "text/html" })
		public Object getPersonHTML(@RequestParam Person person,
				ModelAndView model, HttpServletRequest request) {
			model.setViewName("person");
			personDao.addPerson(person);
			model.addObject("message", "Person Created");
			return model;
		}

}
