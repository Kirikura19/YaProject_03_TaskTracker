package ru.kirikura.interfaces;

import ru.kirikura.entity.SingleTask;
import ru.kirikura.exception.NonExistingTask;

public interface TaskManager {
    void getAllTasks();
    void deleteAllTasks() throws NonExistingTask;
    SingleTask getTask(int id) throws NonExistingTask;
    void updateTask(int id, SingleTask task) throws NonExistingTask;
    void deleteTask(int id) throws NonExistingTask;
    void createTask(SingleTask task) throws NonExistingTask;
    void saveTask(SingleTask task);
}
