package com.ilinoyer.todolist;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sojer on 26.10.2017.
 */

public class Task implements Serializable {
    private Calendar doUntilDate;
    private String taskTopic;
    private String taskContent;
    private TaskType taskType;

    public Task(Calendar doUntilDate, String taskContent, String taskTopic) {
        this.doUntilDate = doUntilDate;
        this.taskContent = taskContent;
        this.taskTopic = taskTopic;
        this.taskType = TaskType.TODO;
    }

    public Calendar getDoUntilDate() {
        return doUntilDate;
    }

    public String getTaskContent() {
        return taskContent;
    }

    @Override
    public String toString() {
        return "Task{" +
                "doUntilDate=" + doUntilDate +
                ", taskContent='" + taskContent + '\'' +
                '}';
    }
}
