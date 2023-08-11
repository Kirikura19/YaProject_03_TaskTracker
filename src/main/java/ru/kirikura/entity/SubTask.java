package ru.kirikura.entity;

public class SubTask extends SingleTask {
    //MultipleTask multipleTask;
    int multipleTaskId;
    public SubTask(String name, String description, TaskStatus currentStatus, int multipleTaskId) {
        super(name, description, currentStatus);
        this.multipleTaskId = multipleTaskId;
        //multipleTask = new MultipleTask();
    }
    public int getMultipleTaskId() {
        return multipleTaskId;
    }
    @Override
    public String toString() {
        return "\n" +
                "   ║ Type: " + "SubTask" + "\n" +
                "   ║ Name: " + name + "\n" +
                "   ║ Description: " + description + "\n" +
                "   ║ Status: " + currentStatus + "\n";
    }
}
