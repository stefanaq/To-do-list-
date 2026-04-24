package org.example.Repo;

import java.util.List;

import org.example.model.Task;
//interface to define main functions save and load
public interface TaskRepo {

    void save(List<Task>tasks);
    List<Task> load();

}
