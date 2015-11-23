package edu.sjsu.cmpe275.dao;

import java.util.List;

import edu.sjsu.cmpe275.entities.Task;

public interface TaskDao {

	void addTask(Task task);
	Task getTask(int id);
	void updateTask(Task task);
	void deleteTask(Task task);
	List<Task> getAllTasks(int projectId);
}
