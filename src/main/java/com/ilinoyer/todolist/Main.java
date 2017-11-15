package com.ilinoyer.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Date;
import java.util.GregorianCalendar;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


        TaskContainer container = new TaskContainer();
        container.addTask(new Task(new GregorianCalendar(), "fsdasda", "dasdadss"));
        container.saveData();
    }


    public static void main(String[] args) {
        launch(args);
    }
}