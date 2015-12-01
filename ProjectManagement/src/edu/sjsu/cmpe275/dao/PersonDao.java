package edu.sjsu.cmpe275.dao;

import java.util.List;

import edu.sjsu.cmpe275.entities.Person;

public interface PersonDao {
	boolean addPerson(Person person);
	Person getPerson(String emailid);
	boolean updatePerson(Person person);
	void deletePerson(Person person);	
	
	public List<Person> getProjectTeam(int projectId);
}
