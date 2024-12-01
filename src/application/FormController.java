package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
// import javafx.scene.control.ComboBox;
// import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import javafx.stage.Stage;

public class FormController {

    @FXML
    private TextField tf_firstName;

    @FXML
    private TextField tf_lastName;

    @FXML
    private TextField tf_position;

    @FXML
    private TextField tf_department;

    @FXML
    private TextField tf_salary;

    @FXML
    private TextField tf_errorMessage;

    @FXML
    public void submitForm(){
        String firstName = tf_firstName.getText();
        String lastName = tf_lastName.getText();
        String position = tf_position.getText();
        String department = tf_department.getText();
        String salary = tf_salary.getText();
        String errorMessage = tf_errorMessage.getText();


        // Check if form is complete
        if(firstName.isEmpty() || lastName.isEmpty() || position.isEmpty() || department.isEmpty() || salary.isEmpty()){
            errorMessage = "Please Complete All Fields";
            return;
        }
    }
}
