package com.ilinoyer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sojer on 18.11.2017.
 */
public class AddTaskController implements Initializable {

    @FXML
    TextField subjectField;

    @FXML
    TextArea contentField;

    @FXML
    DatePicker untilDate;

    @FXML
    Button addButton;

    @FXML
    Button cancleButton;

    Task newTask;

    public AddTaskController(Task newTask)
    {
        this.newTask = newTask;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        addButton.setOnAction(new EventHandler<ActionEvent>()  {
            public void handle(ActionEvent event){
                newTask.setTaskTopic(subjectField.getText());
                System.out.println(subjectField.getText());
                newTask.setTaskContent(contentField.getText());
                System.out.println(contentField.getText());
                newTask.setDoUntilDate(untilDate.getValue());


                Alert alert = new Alert(Alert.AlertType.NONE, "Task Added.", ButtonType.OK);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                }

                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.close();

            }
        });

        cancleButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Stage stage = (Stage) cancleButton.getScene().getWindow();
                stage.close();
            }
        });
    }
}