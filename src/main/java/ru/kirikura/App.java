package ru.kirikura;

import ru.kirikura.entity.MultipleTask;
import ru.kirikura.entity.SingleTask;
import ru.kirikura.entity.SubTask;
import ru.kirikura.entity.TaskStatus;
import ru.kirikura.exception.NonExistingTask;
import ru.kirikura.service.HistoryService;
import ru.kirikura.service.InMemoryTaskManager;
import ru.kirikura.util.Managers;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NonExistingTask {
        InMemoryTaskManager manager = (InMemoryTaskManager) Managers.getDefaultTaskManager();
        HistoryService history = (HistoryService) Managers.getDefaultHistoryManager();

        manager.createTask(new MultipleTask("HW", "I need to do my HW"));
        manager.createTask(new MultipleTask("HW 2", "I need to do my HW"));
        manager.createTask(new SubTask("HW Step 1", "I need to do my HW, Step 1", TaskStatus.NEW, 0));
        manager.createTask(new SubTask("HW Step 2", "I need to do my HW, Step 2", TaskStatus.NEW, 0));
        manager.createTask(new SubTask("HW Step 3", "I need to do my HW, Step 3", TaskStatus.NEW, 0));
        manager.createTask(new SubTask("HW Step 4", "I need to do my HW, Step 4", TaskStatus.NEW, 0));
        manager.updateTask(2, new SubTask("HW Step 1", "I need to do my HW, Step 1", TaskStatus.DONE, 0));
        manager.createTask(new SingleTask("HW", "I need to do my HW", TaskStatus.NEW));
        manager.createTask(new SingleTask("HW", "I need to do my HW", TaskStatus.NEW));
        manager.createTask(new SingleTask("HW", "I need to do my HW", TaskStatus.NEW));
        manager.createTask(new SingleTask("HW", "I need to do my HW", TaskStatus.NEW));
        manager.createTask(new SingleTask("HW", "I need to do my HW", TaskStatus.NEW));
        manager.getTask(0);
        manager.getTask(1);
        manager.getTask(2);
        manager.getTask(3);
        System.out.println("----------------------------------------------------------------");
        history.getHistory();

    }
}
