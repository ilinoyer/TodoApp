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
        String userName = System.getProperty("user.name");
        String savePath = "C:\\Users" + "\\" + userName + "\\ToDoAppData\\";

        File directory = new File(savePath);
        if (! directory.exists()){
            directory.mkdir();
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(savePath + "tasksToDo.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskContainer.getToDoTask());
            out.close();
            fileOut.close();

            fileOut = new FileOutputStream(savePath + "tasksDone.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(taskContainer.getDoneTask());
            out.close();
            fileOut.close();

            fileOut = new FileOutputStream(savePath + "tasksInProgress.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(taskContainer.getInProgress());
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
            taskContainer.setToDoTask((ArrayList<Task>) in.readObject());
            in.close();
            fileIn.close();

            fileIn = new FileInputStream(savePath + "tasksDone.ser");
            in = new ObjectInputStream(fileIn);
            taskContainer.setDoneTask((ArrayList<Task>) in.readObject());
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
