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
    TextField subjectField;

    @FXML
    TextArea contentField;

    @FXML
    DatePicker untilDate;

    @FXML
    Button okButton;

    @FXML
    Button cancleButton;

    BooleanProperty isChanged;
    Task taskToModify;


    private void loadFields()
    {
        subjectField.setText(taskToModify.getTaskTopic());
        contentField.setText(taskToModify.getTaskContent());
        untilDate.setValue(taskToModify.getDoUntilDate());
    }

    public ModifyController(Task taskToModify, SimpleBooleanProperty isChanged)
    {
        this.taskToModify = taskToModify;
        this.isChanged = isChanged;
    }

    public void initialize(URL location, ResourceBundle resources) {
        loadFields();

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(!subjectField.getText().isEmpty() && contentField.getText() != null && untilDate.getValue() != null)
                {
                    taskToModify.setTaskTopic(subjectField.getText());
                    taskToModify.setTaskContent(contentField.getText());
                    taskToModify.setDoUntilDate(untilDate.getValue());


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
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Fill all fields to modify task or press Cancle.", ButtonType.OK);
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                }
            }
        });

        cancleButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Stage stage = (Stage) cancleButton.getScene().getWindow();
                stage.close();
                isChanged.setValue(true);
            }
        });
    }
}
