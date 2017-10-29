package com.ilinoyer.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Date;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        TaskContainer container = new TaskContainer();
        container.loadData();
        container.showTasks();
        System.out.println("/////");
        container.addTask(new Task(new Date(2017,12,12), "Zadanie 1"));
        container.addTask(new Task(new Date(2017,10,12), "Zadanie 2"));
        container.showTasks();
        container.saveData();

    }


    public static void main(String[] args) {
        launch(args);
    }
}