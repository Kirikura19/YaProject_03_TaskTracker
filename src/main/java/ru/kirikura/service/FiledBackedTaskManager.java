package ru.kirikura.service;

import ru.kirikura.data.Data;
import ru.kirikura.entity.*;
import ru.kirikura.interfaces.FileTaskManager;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

public class FiledBackedTaskManager implements FileTaskManager {
    Path path;
    public FiledBackedTaskManager(String path) {
        this.path = Paths.get(path);
    }
    public void loadFromFile() {
        HashMap<Integer, SingleTask> tasks = new HashMap<>();
        LinkedList<Integer> history = new LinkedList<>();
        Data.taskGetInstance().clear();
        Data.historyGetInstance().clear();
        try(BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String tempLine;
            String[] curTask;
            while ((tempLine = br.readLine()) != "") {
                curTask = tempLine.split(",");
                switch (TaskType.valueOf(curTask[1])) {
                    case SINGLE -> Data.taskGetInstance().put(Integer.parseInt(curTask[0]),
                            new SingleTask(curTask[2], curTask[3], TaskStatus.valueOf(curTask[4])));
                    case MULTI -> Data.taskGetInstance().put(Integer.parseInt(curTask[0]),
                            new MultipleTask(curTask[2], curTask[3], TaskStatus.valueOf(curTask[4])));
                    case SUB -> Data.taskGetInstance().put(Integer.parseInt(curTask[0]),
                            new SubTask(curTask[2], curTask[3], TaskStatus.valueOf(curTask[4]), Integer.parseInt(curTask[5])));
                }
            }
            tempLine = br.readLine();
            for(var temp : tempLine.split(",")) {
                Data.historyGetInstance().add(Integer.parseInt(temp));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveToFile() {
        StringBuilder sb = new StringBuilder("id,type,name,status,description,epic" + "\n");
        for(var temp : Data.taskGetInstance().entrySet()) {
            SingleTask curTask = temp.getValue();
            TaskType type;
            String subTasks = "";
            if(curTask instanceof MultipleTask) {
                type = TaskType.MULTI;
            }
            else if(curTask instanceof SubTask) {
                type = TaskType.SUB;
                subTasks += ((SubTask) curTask).getMultipleTaskId();
            }
            else {
                type =  TaskType.SINGLE;
            }
            sb.append((temp.getKey() + 1)).append(",")
                    .append(type.toString()).append(",")
                    .append(curTask.getName()).append(",")
                    .append(curTask.getCurrentStatus()).append(",")
                    .append(curTask.getDescription()).append(",")
                    .append(subTasks);
        }
        sb.append("\n");
        for(var temp : Data.historyGetInstance()) {
            sb.append(Data.taskGetInstance()).append(",");
        }
        sb.delete(sb.length() - 1, sb.length() - 1);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
