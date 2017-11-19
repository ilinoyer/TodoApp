package com.ilinoyer;

import java.util.ArrayList;

/**
 * Created by sojer on 19.11.2017.
 */
public class TaskContainer {
    private static TaskContainer ourInstance = new TaskContainer();

    private ArrayList<Task> toDoTask;
    private ArrayList<Task> inProgress;
    private ArrayList<Task> doneTask;

    public static TaskContainer getInstance() {
        return ourInstance;
    }

    private TaskContainer() {
    }

    public ArrayList<Task> getToDoTask() {
        return toDoTask;
    }

    public void setToDoTask(ArrayList<Task> toDoTask) {
        this.toDoTask = toDoTask;
    }

    public ArrayList<Task> getInProgress() {
        return inProgress;
    }

    public void setInProgress(ArrayList<Task> inProgress) {
        this.inProgress = inProgress;
    }

    public ArrayList<Task> getDoneTask() {
        return doneTask;
    }

    public void setDoneTask(ArrayList<Task> doneTask) {
        this.doneTask = doneTask;
    }

    public Task getToDoTaskByIndex(int index)
    {
        return this.toDoTask.get(index);
    }

    public Task getDoneTaskByIndex(int index)
    {
        return this.doneTask.get(index);
    }

    public Task getInProgressTaskByIndex(int index)
    {
        return this.inProgress.get(index);
    }

    public void addToDoTask(Task newTask)
    {
        this.toDoTask.add(newTask);
    }
}
