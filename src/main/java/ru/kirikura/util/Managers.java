package ru.kirikura.util;

import ru.kirikura.interfaces.HistoryManager;
import ru.kirikura.interfaces.TaskManager;
import ru.kirikura.service.HistoryService;
import ru.kirikura.service.InMemoryTaskManager;

public class Managers {
    public static TaskManager getDefaultTaskManager() {
        return new InMemoryTaskManager();
    }
    public static HistoryManager getDefaultHistoryManager() {
        return new HistoryService();
    }
}
