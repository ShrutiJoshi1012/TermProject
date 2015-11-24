package edu.sjsu.cmpe275.dao;

import edu.sjsu.cmpe275.entities.Person;

public interface PersonDao {
	boolean addPerson(Person person);
	Person getPerson(String emailid);
	void updatePerson(Person person);
	void deletePerson(Person person);	
}
