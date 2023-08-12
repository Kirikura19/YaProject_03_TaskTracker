package ru.kirikura.service;
import ru.kirikura.data.Data;
import ru.kirikura.entity.MultipleTask;
import ru.kirikura.entity.SingleTask;
import ru.kirikura.entity.SubTask;
import ru.kirikura.exception.NonExistingTask;
import ru.kirikura.interfaces.HistoryManager;
import ru.kirikura.interfaces.TaskManager;

import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    HistoryManager history = new HistoryService();
    @Override
    public void getAllTasks() {
        for (HashMap.Entry<Integer, SingleTask> entry : Data.taskGetInstance().entrySet()) {
            if(!(entry.getValue() instanceof SubTask)) {
                System.out.println(entry.getValue().toString());
            }
        }
    }
    @Override
    public void deleteAllTasks() {
        Data.taskGetInstance().clear();
    }
    @Override
    public SingleTask getTask(int id) throws NonExistingTask {
        HashMap<Integer, SingleTask> tasks = Data.taskGetInstance();
        if(!tasks.containsKey(id))
            throw new NonExistingTask();
        history.addHistory(id);
        return Data.taskGetInstance().get(id);
    }
    @Override
    public void updateTask(int id, SingleTask task) throws NonExistingTask {
        HashMap<Integer, SingleTask> tasks = Data.taskGetInstance();
        if(!tasks.containsKey(id))
            throw new NonExistingTask();
        if(task instanceof MultipleTask)
            throw new NonExistingTask();
        if(task instanceof SubTask && !(Data.taskGetInstance().get(id).getCurrentStatus().equals(task.getCurrentStatus()))) {
            MultipleTask multipleTask = (MultipleTask) Data.taskGetInstance().get(((SubTask) task).getMultipleTaskId());
            multipleTask.getAllSubTasks().remove(Data.taskGetInstance().get(id));
            multipleTask.getAllSubTasks().add((SubTask) task);
            new MultipleService().checkMultipleStatus(multipleTask);
        }
        Data.taskGetInstance().put(id, task);
    }
    @Override
    public void deleteTask(int id) {
        if(Data.taskGetInstance().get(id) instanceof MultipleTask) {
            for (SubTask st : ((MultipleTask) Data.taskGetInstance().get(id)).getAllSubTasks())
                Data.taskGetInstance().remove(st);
        }
        Data.taskGetInstance().remove(id);
    }
    @Override
    public void createTask(SingleTask task) throws NonExistingTask {
        if(task instanceof SubTask subTask) {
            int multipleTaskId = (subTask).getMultipleTaskId();
            SingleTask taskFromCurTask = Data.taskGetInstance().get(multipleTaskId);
            if(!Data.taskGetInstance().containsKey(multipleTaskId) || taskFromCurTask instanceof MultipleTask) {
                MultipleTask multipleTask = (MultipleTask) taskFromCurTask;
                multipleTask.getAllSubTasks().add(subTask);
                new MultipleService().checkMultipleStatus(multipleTask);
            }
        else throw new NonExistingTask();
        }
        saveTask(task);
    }
    @Override
    public void saveTask(SingleTask task) {
        Data.taskGetInstance().put(Data.taskGetInstance().size(), task);
    }
}
