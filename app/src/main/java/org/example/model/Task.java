package org.example.model;

import java.io.Serializable;
//Defining the main task class
public class Task implements Serializable{
    // private String title;
    private String description;
    private Priority priority;
    private boolean isCompleted;

    //add title later 
    //Constructor
    public Task( String desc, Priority prio){
        //this.title = titleString; maybe add title back later after ui is figured out
        this.description = desc;
        this.priority = prio;
        this.isCompleted = false;
    }
    //Setters
    // public void setTitle(String titleString){
    //     this.title = titleString;
    // }
    public void setDescription(String desc){
        this.description = desc;
    }
    public void setPriority(Priority prio){
        this.priority = prio;
    }
    public void markCompleted(){
        this.isCompleted = true;
    }

    //getters
    // public String getTitle(){ return title;}
    public String getDescription(){ return description; }
    public Priority getPriority(){ return priority; }
    public boolean isCompleted(){ return isCompleted; }
    //overriding to string 
    @Override
    public String toString() {
        String status =  isCompleted ? "Completed" : "Pending";
        return "[" + status +"] " +  " " + description;
    }

}
