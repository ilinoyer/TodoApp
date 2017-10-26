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

    }


    public static void main(String[] args) {
        launch(args);
    }
}