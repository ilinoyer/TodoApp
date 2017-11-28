package com.ilinoyer;

import java.util.ArrayList;

/**
 * Created by sojer on 19.11.2017.
 */
public class TaskContainer {
    private static TaskContainer ourInstance = new TaskContainer();

    private ArrayList<Task> toDoTasks;
    private ArrayList<Task> inProgress;
    private ArrayList<Task> doneTasks;

    public static TaskContainer getInstance() {
        return ourInstance;
    }

    private TaskContainer() {
    }

    public ArrayList<Task> getToDoTasks() {
        return toDoTasks;
    }

    public void setToDoTasks(ArrayList<Task> toDoTasks) {
        this.toDoTasks = toDoTasks;
    }

    public ArrayList<Task> getInProgress() {
        return inProgress;
    }

    public void setInProgress(ArrayList<Task> inProgress) {
        this.inProgress = inProgress;
    }

    public ArrayList<Task> getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(ArrayList<Task> doneTasks) {
        this.doneTasks = doneTasks;
    }

    public Task getToDoTaskByIndex(int index)
    {
        return this.toDoTasks.get(index);
    }

    public Task getDoneTaskByIndex(int index)
    {
        return this.doneTasks.get(index);
    }

    public Task getInProgressTaskByIndex(int index)
    {
        return this.inProgress.get(index);
    }

    public void addToDoTask(Task newTask)
    {
        this.toDoTasks.add(newTask);
    }

    public void deleteToDoTask(Task taskToDelete)
    {
        this.toDoTasks.remove(taskToDelete);
    }

    public void addInProgressTask(Task newTask)
    {
        newTask.setTaskType(TaskType.INPROGRESS);
        this.inProgress.add(newTask);
    }

    public void deleteInProgressTask(Task taskToDelete)
    {
        this.inProgress.remove(taskToDelete);
    }

    public void addDoneTask(Task newTask)
    {
        newTask.setTaskType(TaskType.DONE);
        this.doneTasks.add(newTask);
    }

    public void deleteDoneTask(Task taskToDelete)
    {
        this.doneTasks.remove(taskToDelete);
    }
}
