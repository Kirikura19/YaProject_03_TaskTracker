package ru.kirikura.service;
import ru.kirikura.data.Data;
import ru.kirikura.entity.MultipleTask;
import ru.kirikura.entity.SingleTask;
import ru.kirikura.entity.SubTask;
import ru.kirikura.entity.TaskStatus;
import ru.kirikura.exception.NonExistingTask;
import java.util.HashMap;

public class Manager {
    public void getAllTasks() {
        for (HashMap.Entry<Integer, SingleTask> entry : Data.taskGetInstance().entrySet()) {
            if(!(entry.getValue() instanceof SubTask)) {
                System.out.println(entry.getValue().toString());
            }
        }
    }
    public void deleteAllTasks() {
        Data.taskGetInstance().clear();
    }
    public SingleTask getTask(int id) throws NonExistingTask {
        HashMap<Integer, SingleTask> tasks = Data.taskGetInstance();
        if(!tasks.containsKey(id))
            throw new NonExistingTask();
        return Data.taskGetInstance().get(id);
    }
    public void updateTask(int id, SingleTask task) throws NonExistingTask {
        HashMap<Integer, SingleTask> tasks = Data.taskGetInstance();
        if(!tasks.containsKey(id))
            throw new NonExistingTask();
        Data.taskGetInstance().put(id, task);
    }
    public void deleteTask(int id) {
        if(Data.taskGetInstance().get(id) instanceof MultipleTask) {
            for (SubTask st : ((MultipleTask) Data.taskGetInstance().get(id)).getAllSubTasks())
                Data.taskGetInstance().remove(st);
        }
        Data.taskGetInstance().remove(id);
    }
    public void createTask(SingleTask task) throws NonExistingTask {
        if(task instanceof SubTask subTask) {
            int multipleTaskId = (subTask).getMultipleTaskId();
            SingleTask taskFromCurTask = Data.taskGetInstance().get(multipleTaskId);
            if(!Data.taskGetInstance().containsKey(multipleTaskId) || taskFromCurTask instanceof MultipleTask) {
                MultipleTask multipleTask = (MultipleTask) taskFromCurTask;
                multipleTask.getAllSubTasks().add(subTask);
                checkMultipleStatus(multipleTask, subTask);
            }
        else
            throw new NonExistingTask();
        }
        saveTask(task);
    }
    public void saveTask(SingleTask task) {
        Data.taskGetInstance().put(Data.taskGetInstance().size(), task);
    }
    public void checkMultipleStatus(MultipleTask multipleTask, SubTask subTask) {
        if(subTask.getCurrentStatus().equals(TaskStatus.DONE)) {
            multipleTask.setCurrentStatus(TaskStatus.DONE);
        }
        else {
            multipleTask.setCurrentStatus(TaskStatus.IN_PROGRESS);
        }
    }
}
