package dorn.core;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dorn.tasks.Task;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles persistent storage of tasks by serializing and deserializing
 * the task list to and from a JSON file on disk.
 */
public class StorageSystem {
    private static final String FILE_PATH = "Data/tasks.json";

    /**
     * Serializes the given list of tasks to a JSON file at {@value FILE_PATH}.
     * Creates the {@code Data/} directory if it does not already exist.    
     * Uses a custom {@link LocalDateAdapter} to handle {@link LocalDate} serialization.
     *
     * @param tasks the list of tasks to save
     * @throws IOException if an I/O error occurs while writing the file
     */
    public static void saveList(List<Task> tasks) throws IOException {
        File dataDir = new File("Data");
        if(!dataDir.exists()){
            dataDir.mkdirs();
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();


        try(FileWriter writer = new FileWriter(FILE_PATH)){
            gson.toJson(tasks, writer);
        } catch (IOException e){
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    /**
     * Deserializes the task list from the JSON file at {@value FILE_PATH}.
     * Returns an empty list if the file does not exist or cannot be read,
     * or if the file contains a null/empty JSON array.
     *
     * @return the loaded list of tasks, or an empty list if unavailable
     */
    public static ArrayList<Task> loadList(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
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

