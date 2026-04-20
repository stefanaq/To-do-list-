package org.example.service;

import java.util.List;

import org.example.Repo.FileTaskRepo;
import org.example.model.Task;

public class TaskService {
    private List<Task> taskList;
    private FileTaskRepo repository;

    public TaskService(FileTaskRepo repo) {
        
        this.repository = repo;
        this.taskList = repository.load();
    }

    public List<Task> getAllTasks(){
        return taskList;
    }

    public void addTask(Task newTask ){
        taskList.add(newTask);
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
