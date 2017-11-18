package com.ilinoyer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    private ArrayList<Task> toDoTask;
    private ArrayList<Task> inProgress;
    private ArrayList<Task> doneTask;
    private TaskContainer taskContainer;

    @FXML
    private ListView<Task> toDoListView;
    private ObservableList<Task> toDoListObservable = FXCollections.observableArrayList();

    @FXML
    Button addTask;


    public MainController()
    {
        toDoTask = new ArrayList<Task>();
        inProgress = new ArrayList<Task>();
        doneTask = new ArrayList<Task>();
        taskContainer = new TaskContainer(doneTask, inProgress, toDoTask);
        taskContainer.loadData();
    }

    private void initToDoObservable(){

        for(int i = 0; i < toDoTask.size(); i++){
            toDoListObservable.add(toDoTask.get(i));
        }
        toDoListView.setItems(toDoListObservable);
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        initToDoObservable();

        addTask.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    Task newTask = new Task();
                    toDoTask.add(newTask);
                    toDoListObservable.add(newTask);

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/AddTaskWindow.fxml"));
                    fxmlLoader.setController(new AddTaskController(newTask));
                    Scene scene = new Scene((Parent) fxmlLoader.load(), 400, 400);
                    Stage stage = new Stage();
                    stage.setTitle("Add Task");
                    stage.setScene(scene);

                    stage.show();
                } catch (IOException e) {
                }
            }
        });

    }


}
