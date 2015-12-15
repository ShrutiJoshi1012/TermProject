package edu.sjsu.cmpe275.controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;

import edu.sjsu.cmpe275.dao.PersonDao;
import edu.sjsu.cmpe275.dao.SharedProjectsDao;
import edu.sjsu.cmpe275.entities.Person;

@Controller
@SessionAttributes("personSessionObj")
public class ProjectSharingController {

	// Initialized via bean
	@Autowired
	private SharedProjectsDao sharedProjectsDao;

	@Autowired
	private PersonDao invitePersonDao;

	// 1> Invitation page handler
	@RequestMapping(value = "/invitation", method = RequestMethod.GET, produces = { "text/html" })
	public Object getInvitationPage(ModelAndView model,
			HttpServletRequest request) {
		model.setViewName("invitation");
		return model;
	}

	// 2> Send Invite
	@RequestMapping(value = "/invitation", method = RequestMethod.POST, produces = { "text/html" })
	public Object sendInvite(@RequestParam(value = "projectID") int projectid,
			@RequestParam(value = "personid") int personid,
			@RequestParam(value = "emailid") String emailid,
			ModelAndView model, HttpServletRequest request) {
		model.setViewName("invitation");

		System.out.println("POST details: " + projectid + " " + personid + " "
				+ emailid);
		Person person = invitePersonDao.getPerson(emailid);
		if (person != null) {
			sharedProjectsDao.shareProject(projectid, person.getPersonId());
		} else {
			System.out.println("Person does not exist");
			// Sending email
			final String username = "projsharecmpe275@gmail.com";
			final String password = "pink@123";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("from-email@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(emailid));
				message.setSubject("Invitation to Join ProjShare!");
				message.setText("Hi,\n"
						+ "Please join ProjShare with following credentials to collaborate projects \n"
						+ "Email id: "
						+ emailid
						+ "\n"
						+ "Password: abcd@123"
						+ "\n"
						+ "click here http://10.189.148.227:8080/ProjectManagement/signin");

				Transport.send(message);

				Person person2 = new Person("User", "abc@123", emailid, "");
				invitePersonDao.addPerson(person2);
				person2 = invitePersonDao.getPerson(emailid);// get the id of a
																// newly added
																// person
				sharedProjectsDao
						.shareProject(projectid, person2.getPersonId());

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			// Sending email ends

		}
		model.addObject("invites",
				sharedProjectsDao.getInvites(personid));
		return model;
	}

	// 3> Accept Invitation handler
	@RequestMapping(value = "/acceptinvite/{projectId}/{personId}", method = RequestMethod.GET, produces = { "text/html" })
	public Object acceptInvitation(
			@ModelAttribute("personSessionObj") Person personSessionObj,
			@PathVariable("projectId") int projectId,
			@PathVariable("personId") int personId, ModelAndView model,
			HttpServletRequest request) {
		model.setViewName("dashboard");

		sharedProjectsDao.acceptInvite(projectId, personId);
		model.addObject("invites",
				sharedProjectsDao.getInvites(personId));
		model.addObject("personSessionObj", invitePersonDao.getPerson(personSessionObj.getEmailid()));
		return model;
	}
}