package ru.kirikura.entity;

public class SingleTask {
    protected String name;
    protected String description;
    protected TaskStatus currentStatus;
    public SingleTask(String name, String description, TaskStatus currentStatus) {
        this.name = name;
        this.description = description;
        this.currentStatus = currentStatus;
    }
    public TaskStatus getCurrentStatus() {
        return currentStatus;
    }
    public void setCurrentStatus(TaskStatus currentStatus) {
        this.currentStatus = currentStatus;
    }
    @Override
    public String toString() {
        String border = "═".repeat(20);
        return "\n" +
                "║ Type: " + "SingleTask" + "\n" +
                "║ Name: " + name + "\n" +
                "║ Description: " + description + "\n" +
                "║ Status: " + currentStatus + "\n" +
                "╚" + border + "╝";
    }
}
