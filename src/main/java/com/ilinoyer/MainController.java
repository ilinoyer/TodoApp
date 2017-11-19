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
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    private TaskContainer taskContainer;
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

    @FXML
    Button toDoDelete;

    @FXML
    Button inProgressDelete;

    @FXML
    Button doneDelete;

    @FXML
    Button inProgress;

    @FXML
    Button done;

    @FXML
    Button modify;



    public MainController()
    {
        taskContainer = TaskContainer.getInstance();
        taskLoader = new TaskLoader();
        taskLoader.loadData();
    }

    private void initToDoObservable(){

        for(int i = 0; i < taskContainer.getToDoTask().size(); i++){
            toDoListObservable.add(taskContainer.getToDoTaskByIndex(i));
        }
        toDoListView.setItems(toDoListObservable);
    }

    private void initInPorgressObservable(){

        for(int i = 0; i < taskContainer.getInProgress().size(); i++){
            inProgressListObservable.add(taskContainer.getInProgressTaskByIndex(i));
        }
        inProgressListView.setItems(inProgressListObservable);
    }

    private void initDoneObservable(){

        for(int i = 0; i < taskContainer.getDoneTask().size(); i++){
            doneListObservable.add(taskContainer.getDoneTaskByIndex(i));
        }
        doneListView.setItems(doneListObservable);
    }

    public void saveData()
    {
        taskLoader.saveData();
    }

    private void checkToDoList()
    {
        Task taskToRemove;
        for(int i = 0; i < taskContainer.getToDoTask().size(); ++i)
        {
            if(!taskContainer.getToDoTaskByIndex(i).isValidate())
            {
                taskToRemove = taskContainer.getToDoTaskByIndex(i);
                taskContainer.deleteToDoTask(taskToRemove);
                toDoListObservable.remove(taskToRemove);
            }

        }
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        initToDoObservable();
        initDoneObservable();
        initInPorgressObservable();

        isChanged.addListener(new ChangeListener<Boolean>() {

            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                checkToDoList();
                toDoListView.refresh();
                doneListView.refresh();
                inProgressListView.refresh();
                isChanged.set(false);

            }
        });

        addTask.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    Task newTask = new Task();
                    taskContainer.addToDoTask(newTask);
                    toDoListObservable.add(newTask);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/AddTaskWindow.fxml"));
                    fxmlLoader.setController(new AddTaskController(newTask, isChanged));
                    Scene scene = new Scene((Parent) fxmlLoader.load(), 400, 400);
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle("Add Task");
                    stage.setScene(scene);
                    stage.show();

                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent event) {
                            isChanged.set(true);
                        }
                    });

                } catch (IOException e) {
                }
            }
        });

        toDoDelete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                taskContainer.deleteToDoTask(toDoListView.getSelectionModel().getSelectedItem());
                toDoListObservable.remove(toDoListView.getSelectionModel().getSelectedItem());
            }
        });

        inProgressDelete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                taskContainer.deleteInProgressTask(inProgressListView.getSelectionModel().getSelectedItem());
                inProgressListObservable.remove(inProgressListView.getSelectionModel().getSelectedItem());
            }
        });

        doneDelete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                taskContainer.deleteDoneTask(doneListView.getSelectionModel().getSelectedItem());
                doneListObservable.remove(doneListView.getSelectionModel().getSelectedItem());
            }
        });


        inProgress.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Task elementToMove = toDoListView.getSelectionModel().getSelectedItem();
                taskContainer.addInPogressTask(elementToMove);
                inProgressListObservable.add(elementToMove);
                toDoListObservable.remove(elementToMove);
                taskContainer.deleteToDoTask(elementToMove);
            }
        });

        done.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Task elementToMove = inProgressListView.getSelectionModel().getSelectedItem();
                taskContainer.addDoneTask(elementToMove);
                doneListObservable.add(elementToMove);
                inProgressListObservable.remove(elementToMove);
                taskContainer.deleteInProgressTask(elementToMove);
            }
        });

        modify.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    Task taskToModify = inProgressListView.getSelectionModel().getSelectedItem();

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/ModifyWindow.fxml"));
                    fxmlLoader.setController(new ModifyController(taskToModify, isChanged));
                    Scene scene = new Scene((Parent) fxmlLoader.load(), 400, 400);
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle("Modify Task");
                    stage.setScene(scene);
                    stage.show();

                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent event) {
                            isChanged.set(true);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
