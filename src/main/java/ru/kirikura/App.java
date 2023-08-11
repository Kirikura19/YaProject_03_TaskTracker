package ru.kirikura;

import ru.kirikura.entity.MultipleTask;
import ru.kirikura.entity.SubTask;
import ru.kirikura.entity.TaskStatus;
import ru.kirikura.exception.NonExistingTask;
import ru.kirikura.service.Manager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NonExistingTask {
        Manager manager = new Manager();
        manager.createTask(new MultipleTask("HW", "I need to do my HW"));
        manager.createTask(new SubTask("HW Step 1", "I need to do my HW, Step 1", TaskStatus.NEW, 0));
        manager.createTask(new SubTask("HW Step 2", "I need to do my HW, Step 2", TaskStatus.NEW, 0));
        manager.createTask(new SubTask("HW Step 3", "I need to do my HW, Step 3", TaskStatus.NEW, 0));
        manager.createTask(new SubTask("HW Step 4", "I need to do my HW, Step 4", TaskStatus.NEW, 0));
        System.out.println(manager.getTask(0).toString());
    }
}
