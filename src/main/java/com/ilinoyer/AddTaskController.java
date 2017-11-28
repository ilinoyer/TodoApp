package com.ilinoyer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sojer on 18.11.2017.
 */
public class AddTaskController implements Initializable {

    @FXML
    private TextField subjectField;

    @FXML
    private TextArea contentField;

    @FXML
    private DatePicker deadlineDatePicker;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    private BooleanProperty isChanged;


    Task newTask;

    public AddTaskController(Task newTask, SimpleBooleanProperty isChanged)
    {
        this.newTask = newTask;
        this.isChanged = isChanged;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {


        addButton.setOnAction(new EventHandler<ActionEvent>()  {
            public void handle(ActionEvent event){
                if(subjectField.getText() != null && contentField.getText() != null && deadlineDatePicker.getValue() != null)
                {
                    newTask.setTaskSubject(subjectField.getText());
                    newTask.setTaskContent(contentField.getText());
                    newTask.setDeadline(deadlineDatePicker.getValue());

                    Alert alert = new Alert(Alert.AlertType.NONE, "Task Added.", ButtonType.OK);
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }

                    Stage stage = (Stage) addButton.getScene().getWindow();
                    stage.close();
                    isChanged.setValue(true);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Fill all fields to add task or press Cancel.", ButtonType.OK);
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                }

            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
                isChanged.setValue(true);
            }
        });
    }
}
