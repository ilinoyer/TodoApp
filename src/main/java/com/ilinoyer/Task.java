package com.ilinoyer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sojer on 26.10.2017.
 */

public class Task implements Serializable {
    private LocalDate doUntilDate;
    private String taskTopic;
    private String taskContent;
    private TaskType taskType;

    public Task(LocalDate doUntilDate, String taskContent, String taskTopic) {
        this.doUntilDate = doUntilDate;
        this.taskContent = taskContent;
        this.taskTopic = taskTopic;
        this.taskType = TaskType.TODO;
    }

    public Task() {
        this.taskType = TaskType.TODO;
        this.doUntilDate = LocalDate.now();
    }


    public void setDoUntilDate(LocalDate doUntilDate) {
        this.doUntilDate = doUntilDate;
    }

    public void setTaskTopic(String taskTopic) {
        this.taskTopic = taskTopic;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    @Override
    public String toString() {
        if(taskTopic == null)
            return "";
        return taskTopic + "\nUntil: " + doUntilDate.toString();
    }
}
