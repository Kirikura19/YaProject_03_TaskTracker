package ru.kirikura.data;
import ru.kirikura.entity.SingleTask;
import java.util.HashMap;
import java.util.LinkedList;

public class Data {
    private static HashMap<Integer, SingleTask> tasks;
    private static LinkedList<Integer> history;
    private Data() {}
    // Singleton
    public static HashMap<Integer, SingleTask> taskGetInstance(){
        if(tasks == null){
            tasks = new HashMap<>();
        }
        return tasks;
    }
    public static LinkedList<Integer> historyGetInstance(){
        if(history == null){
            history = new LinkedList<>();
        }
        return history;
    }
}
