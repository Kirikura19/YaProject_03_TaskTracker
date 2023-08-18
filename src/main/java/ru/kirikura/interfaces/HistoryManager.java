package ru.kirikura.interfaces;

import ru.kirikura.entity.SingleTask;

import java.util.List;

public interface HistoryManager {
    void addHistory(Integer task);
    void removeHistory(int id);
    List<Integer> getHistory();
}
