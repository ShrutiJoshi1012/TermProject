package edu.sjsu.cmpe275.dao;

import java.util.List;

import edu.sjsu.cmpe275.entities.Task;

public interface TaskDao {

	boolean addTask(Task task);
	Task getTask(int id);
	boolean updateTask(Task task);
	boolean deleteTask(Task task);
	List<Task> getAllTasks(int projectId);
}
