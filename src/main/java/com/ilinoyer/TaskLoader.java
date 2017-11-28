package com.ilinoyer;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by sojer on 27.10.2017.
 */
public class TaskLoader {

    private TaskContainer taskContainer;

    public TaskLoader() {
        taskContainer = TaskContainer.getInstance();
    }

    public void saveData()
    {
        String homePath = System.getProperty("user.home");
        String savePath = homePath + "\\ToDoAppData\\";
        File directory = new File(savePath);

        if (! directory.exists()){
            directory.mkdir();
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(savePath + "tasksToDo.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskContainer.getToDoTasks());
            out.close();
            fileOut.close();

            fileOut = new FileOutputStream(savePath + "tasksDone.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(taskContainer.getDoneTasks());
            out.close();
            fileOut.close();

            fileOut = new FileOutputStream(savePath + "tasksInProgress.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(taskContainer.getInProgress());
            out.close();
            fileOut.close();

        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    public void loadData()
    {
        String homePath = System.getProperty("user.home");
        String savePath = homePath + "\\ToDoAppData\\";

        try {
            FileInputStream fileIn = new FileInputStream(savePath + "tasksToDo.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            taskContainer.setToDoTasks((ArrayList<Task>) in.readObject());
            in.close();
            fileIn.close();

            fileIn = new FileInputStream(savePath + "tasksDone.ser");
            in = new ObjectInputStream(fileIn);
            taskContainer.setDoneTasks((ArrayList<Task>) in.readObject());
            in.close();
            fileIn.close();

            fileIn = new FileInputStream(savePath + "tasksInProgress.ser");
            in = new ObjectInputStream(fileIn);
            taskContainer.setInProgress((ArrayList<Task>) in.readObject());
            in.close();
            fileIn.close();
        }
        catch(FileNotFoundException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nie znaleziono poprzednich zapisów.", ButtonType.OK);
            alert.showAndWait();
            e.printStackTrace();
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
