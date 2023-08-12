package ru.kirikura.service;

import ru.kirikura.data.Data;
import ru.kirikura.interfaces.HistoryManager;

public class HistoryService implements HistoryManager {
    public void addHistory(int id) {
        Data.historyGetInstance().add(id);
    }
    public void getHistory() {
        System.out.println("History: ");
        for(int id : Data.historyGetInstance()) {
            System.out.println(id + ") " + "\n"
                    + Data.taskGetInstance().get(id));
        }
    }
}
