package com.ilinoyer.todolist;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by sojer on 27.10.2017.
 */
public class TaskContainer {

    private ArrayList<Task> toDoTask;
    private ArrayList<Task> inProgress;
    private ArrayList<Task> doneTask;

    public TaskContainer()
    {
        toDoTask = new ArrayList<Task>();
        doneTask = new ArrayList<Task>();
        inProgress = new ArrayList<Task>();
    }

    public void addTask(Task newTask)
    {
        toDoTask.add(newTask);
    }

    public void aktualizeTasks()
    {
        for(int i = 0; i < toDoTask.size(); ++i)
        {
            if(toDoTask.get(i).isDone())
            {
                doneTask.add(toDoTask.get(i));
                toDoTask.remove(i);
            }
        }
    }

    public void saveData()
    {
        try {
            FileOutputStream fileOut = new FileOutputStream("tasksToDo.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(toDoTask);
            out.close();
            fileOut.close();

            fileOut = new FileOutputStream("tasksDone.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(doneTask);
            out.close();
            fileOut.close();

            fileOut = new FileOutputStream("tasksInProgress.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(inProgress);
            out.close();
            fileOut.close();

            System.out.printf("Serialized data is saved in /tmp/");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    public void loadData()
    {
        try {
            FileInputStream fileIn = new FileInputStream("tasksToDo.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.toDoTask = (ArrayList<Task>) in.readObject();
            in.close();
            fileIn.close();

            fileIn = new FileInputStream("tasksDone.ser");
            in = new ObjectInputStream(fileIn);
            this.doneTask = (ArrayList<Task>) in.readObject();
            in.close();
            fileIn.close();

            fileIn = new FileInputStream("taskInProgress.ser");
            in = new ObjectInputStream(fileIn);
            this.doneTask = (ArrayList<Task>) in.readObject();
            in.close();
            fileIn.close();
        }
        catch(FileNotFoundException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nie znaleziono poprzednich zapisów.", ButtonType.OK);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        }
        catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("Task class not found.");
            c.printStackTrace();
            return;
        }
    }

    public void showTasks()
    {
        for(Task task : this.toDoTask)
            System.out.println(task.toString());
    }
}
