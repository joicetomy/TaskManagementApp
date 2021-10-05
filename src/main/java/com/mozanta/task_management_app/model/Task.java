package com.mozanta.task_management_app.model;

        import org.springframework.data.annotation.Id;
        import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String task;
    private boolean completed;

    public Task() {

    }

    public Task(String task, boolean completed) {
        this.task = task;
        this.completed =completed;
    }

    public String getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean isCompleted) {
        this.completed = isCompleted;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", task=" + task + ", completed=" + completed + "]";
    }
}