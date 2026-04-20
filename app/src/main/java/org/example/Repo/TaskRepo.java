package org.example.Repo;

import java.util.List;

import org.example.model.Task;
//interface to define main functions that need to be present in our repository
public interface TaskRepo {

    void save(List<Task>tasks);
    List<Task> load();

}
