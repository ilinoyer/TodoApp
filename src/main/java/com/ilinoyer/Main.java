package com.ilinoyer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.GregorianCalendar;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Remember");
        final MainController maincontroller = loader.getController();

        primaryStage.setScene(new Scene(root, 800, 550));
        primaryStage.setMinHeight(550);
        primaryStage.setMinWidth(800);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                maincontroller.saveData();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}

