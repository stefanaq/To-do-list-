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
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){

             outputStream.writeObject(tasks);
                
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> load(){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){

            return (List<Task>) inputStream.readObject();
        }
        //exception to handle the file not being here initially gracefully
        catch(FileNotFoundException e){

            return new ArrayList<>();
        }

        catch(IOException | ClassNotFoundException e){

            e.printStackTrace();
            return new ArrayList<>();
        }

    }
}
