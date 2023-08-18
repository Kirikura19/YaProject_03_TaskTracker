package ru.kirikura.service;

import ru.kirikura.data.Data;
import ru.kirikura.entity.SingleTask;
import ru.kirikura.interfaces.HistoryManager;

import java.util.List;

public class InMemoryHistoryService implements HistoryManager {
    public void addHistory(Integer taskId) {
        if(Data.historyGetInstance().contains(taskId))
            removeHistory(Data.historyGetInstance().indexOf(taskId));
        Data.historyGetInstance().add(taskId);
    }
    @Override
    public void removeHistory(int id) {
        Data.historyGetInstance().remove(id);
    }
    public List<Integer> getHistory() {
        return Data.historyGetInstance();
    }
}
