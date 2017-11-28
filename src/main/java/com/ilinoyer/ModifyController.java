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
 * Created by sojer on 19.11.2017.
 */
public class ModifyController implements Initializable{

    @FXML
    private TextField subjectField;

    @FXML
    private TextArea contentField;

    @FXML
    private DatePicker deadlineDatePicker;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    private BooleanProperty isChanged;
    private Task taskToModify;


    public ModifyController(Task taskToModify, SimpleBooleanProperty isChanged)
    {
        this.taskToModify = taskToModify;
        this.isChanged = isChanged;
    }

    private void loadFields()
    {
        subjectField.setText(taskToModify.getTaskSubject());
        contentField.setText(taskToModify.getTaskContent());
        deadlineDatePicker.setValue(taskToModify.getDeadline());
    }

    public void initialize(URL location, ResourceBundle resources) {
        loadFields();

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(!subjectField.getText().isEmpty() && contentField.getText() != null && deadlineDatePicker.getValue() != null)
                {
                    taskToModify.setTaskSubject(subjectField.getText());
                    taskToModify.setTaskContent(contentField.getText());
                    taskToModify.setDeadline(deadlineDatePicker.getValue());


                    Alert alert = new Alert(Alert.AlertType.NONE, "Task Modified.", ButtonType.OK);
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }

                    Stage stage = (Stage) okButton.getScene().getWindow();
                    stage.close();
                    isChanged.setValue(true);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Fill all fields to modify task or press Cancel.", ButtonType.OK);
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
