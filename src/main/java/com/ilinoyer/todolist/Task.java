package com.ilinoyer.todolist;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sojer on 26.10.2017.
 */

public class Task implements Serializable {
    private Date doUntilDate;
    private String taskTopic;
    private String taskContent;
    private TaskType taskType;

    public Task(Date doUntilDate, String taskContent, String taskTopic) {
        this.doUntilDate = doUntilDate;
        this.taskContent = taskContent;
        this.taskTopic = taskTopic;
        this.taskType = TaskType.TODO;
    }

    public Date getDoUntilDate() {
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
