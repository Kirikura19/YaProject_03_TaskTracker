package ru.kirikura.data;
import ru.kirikura.entity.SingleTask;
import java.util.HashMap;

public class Data {
    private static HashMap<Integer, SingleTask> tasks;
    private Data() {}
    // Singleton
    public static HashMap<Integer, SingleTask> taskGetInstance(){
        if(tasks == null){
            tasks = new HashMap<>();
        }
        return tasks;
    }
}
