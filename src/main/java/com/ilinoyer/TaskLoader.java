package com.ilinoyer;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by sojer on 27.10.2017.
 */
public class TaskLoader {

    private ArrayList<Task> toDoTask;
    private ArrayList<Task> inProgress;
    private ArrayList<Task> doneTask;

    public TaskLoader(ArrayList<Task> doneTask, ArrayList<Task> inProgress, ArrayList<Task> toDoTask) {
        this.toDoTask = toDoTask;
        this.doneTask = doneTask;
        this.inProgress = inProgress;
    }

    public void saveData()
    {
        String userName = System.getProperty("user.name");
        String savePath = "C:\\Users" + "\\" + userName + "\\ToDoAppData\\";

        File directory = new File(savePath);
        if (! directory.exists()){
            directory.mkdir();
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(savePath + "tasksToDo.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(toDoTask);
            out.close();
            fileOut.close();

            fileOut = new FileOutputStream(savePath + "tasksDone.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(doneTask);
            out.close();
            fileOut.close();

            fileOut = new FileOutputStream(savePath + "tasksInProgress.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(inProgress);
            out.close();
            fileOut.close();

            System.out.printf("Serialized data is saved in " + savePath);
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    public void loadData()
    {
        String userName = System.getProperty("user.name");
        String savePath = "C:\\Users" + "\\" + userName + "\\ToDoAppData\\";

        try {
            FileInputStream fileIn = new FileInputStream(savePath + "tasksToDo.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.toDoTask = (ArrayList<Task>) in.readObject();
            in.close();
            fileIn.close();

            fileIn = new FileInputStream(savePath + "tasksDone.ser");
            in = new ObjectInputStream(fileIn);
            this.doneTask = (ArrayList<Task>) in.readObject();
            in.close();
            fileIn.close();

            fileIn = new FileInputStream(savePath + "taskInProgress.ser");
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

}
