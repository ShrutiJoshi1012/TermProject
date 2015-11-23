package edu.sjsu.cmpe275.dao;

import edu.sjsu.cmpe275.entities.Person;

public interface PersonDao {
	void addPerson(Person person);
	Person getPerson(int id);
	void updatePerson(Person person);
	void deletePerson(Person person);	
}
