package org.example.Repo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.example.model.Task;
//my file repository uses serialization and deserialization for saving and loading to a file
public class FileTaskRepo implements TaskRepo {
    //final as i dont want finalname to change
    private final String fileName = "ToDo.dat";

    @Override
    public void save(List<Task> tasks){
        //try catch block to serialize data into the .dat file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){

             outputStream.writeObject(tasks);
                
        } 
        //exception to catch input/output errors
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> load(){
        //try block to deserialize file into list for the taskService class
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){

            return (List<Task>) inputStream.readObject();
        }
        //exception to handle the file not being here initially by returning an empty list
        catch(FileNotFoundException e){

            return new ArrayList<>();
        }
        //another exception for input/output errors
        catch(IOException | ClassNotFoundException e){

            e.printStackTrace();
            return new ArrayList<>();
        }

    }
}
