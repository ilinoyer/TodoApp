package com.ilinoyer;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by sojer on 26.10.2017.
 */

public class Task implements Serializable {
    private LocalDate deadline;
    private String taskSubject;
    private String taskContent;
    private TaskType taskType;
    private static final long serialVersionUID = 4721708364015056087L;

    public Task(LocalDate deadline, String taskContent, String taskSubject) {
        this.deadline = deadline;
        this.taskContent = taskContent;
        this.taskSubject = taskSubject;
        this.taskType = TaskType.TODO;
    }

    public Task() {
        this.taskType = TaskType.TODO;
        this.deadline = LocalDate.now();
    }

    public void setTaskType(TaskType type)
    {
        this.taskType = type;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setTaskSubject(String taskSubject) {
        this.taskSubject = taskSubject;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public boolean isValidate()
    {
        if ( this.taskSubject == null )
            return false;
        return true;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getTaskSubject() {
        return taskSubject;
    }

    public String getTaskContent() {
        return taskContent;
    }

    @Override
    public String toString() {
        if(taskSubject == null)
            return "";
        return taskSubject + "\nUntil: " + deadline.toString();
    }
}
