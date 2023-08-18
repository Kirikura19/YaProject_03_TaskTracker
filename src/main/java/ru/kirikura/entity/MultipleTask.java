package ru.kirikura.entity;
import ru.kirikura.service.MultipleService;
import java.util.ArrayList;

public class MultipleTask extends SingleTask {
    private ArrayList<SubTask> subTasks;
    public MultipleTask(String name, String description) {
        super(name, description, TaskStatus.NEW);
        subTasks = new ArrayList<>();
    }
    public MultipleTask(String name, String description, TaskStatus status) {
        super(name, description, status);
        subTasks = new ArrayList<>();
    }
    public ArrayList<SubTask> getAllSubTasks() {
        return subTasks;
    }
    @Override
    public String toString() {
        return new MultipleService().toOutout(name, description, currentStatus, subTasks);
    }
}
