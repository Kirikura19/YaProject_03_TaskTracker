package ru.kirikura.entity;
import ru.kirikura.service.MultipleToString;
import java.util.ArrayList;

public class MultipleTask extends SingleTask {
    private ArrayList<SubTask> subTasks;
    public MultipleTask(String name, String description) {
        super(name, description, TaskStatus.NEW);
        subTasks = new ArrayList<>();
    }
    public ArrayList<SubTask> getAllSubTasks() {
        return subTasks;
    }
    @Override
    public String toString() {
        return new MultipleToString().toOutout(name, description, currentStatus, subTasks);
    }
}
