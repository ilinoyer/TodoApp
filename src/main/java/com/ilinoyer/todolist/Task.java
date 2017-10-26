package com.ilinoyer.todolist;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sojer on 26.10.2017.
 */
public class Task implements Serializable {
    private Date doUntilDate;
    private String taskContent;
    private boolean isDone;

    public Task(Date doUntilDate, String taskContent) {
        this.doUntilDate = doUntilDate;
        this.taskContent = taskContent;
        this.isDone = false;
    }

    public Date getDoUntilDate() {
        return doUntilDate;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "doUntilDate=" + doUntilDate +
                ", taskContent='" + taskContent + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}
