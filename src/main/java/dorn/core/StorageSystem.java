package dorn.core;
import com.google.gson.reflect.TypeToken;
import dorn.tasks.Task;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StorageSystem {
    private static final String FILE_PATH = "Data/tasks.json";

    public static void saveList(List<Task> tasks) throws IOException {
        File dataDir = new File("Data");
        if(!dataDir.exists()){
            dataDir.mkdirs();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter(FILE_PATH)){  // ✅ Use the constant!
            gson.toJson(tasks, writer);
        } catch (IOException e){
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadList(){
        Gson gson = new Gson();
        File file = new File(FILE_PATH);

        if(!file.exists()){
            return new ArrayList<>();
        }

        try(FileReader reader = new FileReader(FILE_PATH)){
            Type listType = new TypeToken<ArrayList<Task>>(){}.getType();
            ArrayList<Task> tasks = gson.fromJson(reader, listType);
            return tasks != null ? tasks : new ArrayList<>();
        } catch(IOException e){
            return new ArrayList<>();
        }
    }
}
