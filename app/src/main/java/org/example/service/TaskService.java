package org.example.service;

import java.util.List;

import org.example.Repo.FileTaskRepo;
import org.example.model.Task;

public class TaskService {
    //List of all tasks for the class
    private List<Task> taskList;
    private FileTaskRepo repository;

    public TaskService(FileTaskRepo repo) {
        
        this.repository = repo;
        //constructor loads from file initially but if the file isnt there it receives an empty list
        this.taskList = repository.load();
    }
    //returns all tasks for the gui class
    public List<Task> getAllTasks(){
        return taskList;
    }
    //my add task takes the full object instead of a description and priority
    public void addTask(Task newTask ){
        taskList.add(newTask);
        //list is saved to the file everytime a new task is added
        repository.save(taskList);
    }

    public void markCompleted(int index){
        if (index < 0 || index > taskList.size()-1){
            throw new IllegalArgumentException("Invalid index");
        }

        Task task = taskList.get(index);
        task.markCompleted();

        repository.save(taskList);
    }

}
