package com.ilinoyer;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    private TaskContainer taskContainer;
    private TaskLoader taskLoader;

    private SimpleBooleanProperty isChanged = new SimpleBooleanProperty(false);

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
    private Button addTaskButton;

    @FXML
    private Button toDoDeleteButton;

    @FXML
    private Button inProgressDeleteButton;

    @FXML
    private Button doneDeleteButton;

    @FXML
    private Button inProgressButton;

    @FXML
    private Button doneButton;


    public MainController()
    {
        taskContainer = TaskContainer.getInstance();
        taskLoader = new TaskLoader();
        taskLoader.loadData();
    }

    private void initToDoObservable(){

        for(int i = 0; i < taskContainer.getToDoTasks().size(); i++){
            toDoListObservable.add(taskContainer.getToDoTaskByIndex(i));
        }
        toDoListView.setItems(toDoListObservable);
    }

    private void initInProgressObservable(){

        for(int i = 0; i < taskContainer.getInProgress().size(); i++){
            inProgressListObservable.add(taskContainer.getInProgressTaskByIndex(i));
        }
        inProgressListView.setItems(inProgressListObservable);
    }

    private void initDoneObservable(){

        for(int i = 0; i < taskContainer.getDoneTasks().size(); i++){
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
        for(int i = 0; i < taskContainer.getToDoTasks().size(); ++i)
        {
            if(!taskContainer.getToDoTaskByIndex(i).isValidate())
            {
                taskToRemove = taskContainer.getToDoTaskByIndex(i);
                taskContainer.deleteToDoTask(taskToRemove);
                toDoListObservable.remove(taskToRemove);
            }

        }
    }

    private void openModifyWindow(Task taskToModify)
    {
        try {

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

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        initToDoObservable();
        initDoneObservable();
        initInProgressObservable();

        isChanged.addListener(new ChangeListener<Boolean>() {

            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                checkToDoList();
                toDoListView.refresh();
                doneListView.refresh();
                inProgressListView.refresh();
                isChanged.set(false);

            }
        });

        addTaskButton.setOnAction(new EventHandler<ActionEvent>() {
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
                    e.printStackTrace();
                }
            }
        });

        toDoDeleteButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                taskContainer.deleteToDoTask(toDoListView.getSelectionModel().getSelectedItem());
                toDoListObservable.remove(toDoListView.getSelectionModel().getSelectedItem());
            }
        });

        inProgressDeleteButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                taskContainer.deleteInProgressTask(inProgressListView.getSelectionModel().getSelectedItem());
                inProgressListObservable.remove(inProgressListView.getSelectionModel().getSelectedItem());
            }
        });

        doneDeleteButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                taskContainer.deleteDoneTask(doneListView.getSelectionModel().getSelectedItem());
                doneListObservable.remove(doneListView.getSelectionModel().getSelectedItem());
            }
        });


        inProgressButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Task elementToMove = toDoListView.getSelectionModel().getSelectedItem();
                if(elementToMove != null)
                {
                    taskContainer.addInProgressTask(elementToMove);
                    inProgressListObservable.add(elementToMove);
                    toDoListObservable.remove(elementToMove);
                    taskContainer.deleteToDoTask(elementToMove);
                }
            }
        });

        doneButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Task elementToMove = inProgressListView.getSelectionModel().getSelectedItem();
                if (elementToMove != null) {
                    taskContainer.addDoneTask(elementToMove);
                    doneListObservable.add(elementToMove);
                    inProgressListObservable.remove(elementToMove);
                    taskContainer.deleteInProgressTask(elementToMove);
                }
            }
        });


        inProgressListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                            Task taskToModify = inProgressListView.getSelectionModel().getSelectedItem();
                            openModifyWindow(taskToModify);
                    }
            }}});

        toDoListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        Task taskToModify = toDoListView.getSelectionModel().getSelectedItem();
                        openModifyWindow(taskToModify);
                    }
                }}});


        doneListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                            Task taskToModify = doneListView.getSelectionModel().getSelectedItem();
                            openModifyWindow(taskToModify);
                    }
                }}});
    }


}
