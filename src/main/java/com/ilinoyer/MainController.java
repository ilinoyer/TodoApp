package com.ilinoyer;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
    private TaskLoader taskLoader;

    SimpleBooleanProperty isChanged = new SimpleBooleanProperty(false);

    @FXML
    private ListView<Task> toDoListView;
    private ObservableList<Task> toDoListObservable = FXCollections.observableArrayList();

    @FXML
    private ListView<Task> inProgressListView;
    private ObservableList<Task> inProgressListObservable = FXCollections.observableArrayList();

    @FXML
    private ListView<Task> doneListView;
    private ObservableList<Task> doneListObservable = FXCollections.observableArrayList();

    @FXML
    Button addTask;


    public MainController()
    {
        toDoTask = new ArrayList<Task>();
        inProgress = new ArrayList<Task>();
        doneTask = new ArrayList<Task>();
        taskLoader = new TaskLoader(doneTask, inProgress, toDoTask);
        taskLoader.loadData();
    }

    private void initToDoObservable(){

        for(int i = 0; i < toDoTask.size(); i++){
            toDoListObservable.add(toDoTask.get(i));
        }
        toDoListView.setItems(toDoListObservable);
    }

    private void initInPorgressObservable(){

        for(int i = 0; i < inProgress.size(); i++){
            inProgressListObservable.add(inProgress.get(i));
        }
        inProgressListView.setItems(inProgressListObservable);
    }

    private void initDoneObservable(){

        for(int i = 0; i < doneTask.size(); i++){
            doneListObservable.add(doneTask.get(i));
        }
        doneListView.setItems(doneListObservable);
    }

    public void saveData()
    {
        taskLoader.saveData();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        initToDoObservable();
        initDoneObservable();
        initInPorgressObservable();

        isChanged.addListener(new ChangeListener<Boolean>() {

            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                toDoListView.refresh();
                //doneListView.refresh();
                //inProgressListView.refresh();
                isChanged.set(false);

            }
        });

        addTask.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    Task newTask = new Task();
                    toDoTask.add(newTask);
                    toDoListObservable.add(newTask);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/AddTaskWindow.fxml"));
                    fxmlLoader.setController(new AddTaskController(newTask, isChanged));
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
