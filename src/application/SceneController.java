package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// --------------------------------------------------------------------

public class SceneController {

    // Handle Forms
    @FXML
    private TextField tf_employeeId;

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
    private TextField tf_hourlyRate;

    @FXML
    private TextField tf_errorMessage;

    // List to hold employees
    private List<Employee> listEmployee = new ArrayList<>();

    // -------------------------------------------------------------------- Submit Form Methods

    @FXML
    public void submitFullTime(){
        // Clear errors before method is run
        tf_errorMessage.clear();

        // Assign properties to test fields
        String firstName = tf_firstName.getText();
        String lastName = tf_lastName.getText();
        String position = tf_position.getText();
        String department = tf_department.getText();
        String salaryString= tf_salary.getText(); // convert to double later
        double salary = 0;

        // Check if form is complete
        if(firstName.isEmpty() || lastName.isEmpty() || position.isEmpty() || department.isEmpty() || salaryString.isEmpty()){
            tf_errorMessage.setText("Error: Please complete all fields.");
            return;
        }

        try{
            salary = Double.parseDouble(salaryString); // Convert string to double
        }
        catch (NumberFormatException e){
            tf_errorMessage.setText("Error: Please enter a valid format.");
            return;
        }

        Employee employee = new FullTimeEmployee(firstName, lastName, position, department, salary);

        // Add employee to list
        listEmployee.add(employee);

        // Clear Text Fields
        tf_firstName.clear();
        tf_lastName.clear();
        tf_position.clear();
        tf_department.clear();
        tf_salary.clear();


    }

    @FXML
    public void submitPartTime(){
        String firstName = tf_firstName.getText();
        String lastName = tf_lastName.getText();
        String position = tf_position.getText();
        String department = tf_department.getText();
        String hourlyRateString = tf_hourlyRate.getText();
        double hourlyRate = 0;
        String errorMessage = tf_errorMessage.getText();

        // Check if form is complete
        if(firstName.isEmpty() || lastName.isEmpty() || position.isEmpty() || department.isEmpty() || hourlyRateString.isEmpty()){
            tf_errorMessage.setText("Error: Please complete all fields.");
            return;
        }

        try{
            hourlyRate = Double.parseDouble(hourlyRateString); // Convert string to double
        }
        catch (NumberFormatException e){
            tf_errorMessage.setText("Error: Please enter a valid format.");
            return;
        }

        Employee employee = new FullTimeEmployee(firstName, lastName, position, department, hourlyRate);

        // Add employee to list
        listEmployee.add(employee);



        // Clear fields once form is submitted
        tf_firstName.clear();
        tf_lastName.clear();
        tf_position.clear();
        tf_department.clear();
        tf_salary.clear();
        tf_errorMessage.clear();
    }

    @FXML
    public void submitRemoveEmployee(){
        String employeeIdString = tf_employeeId.getText();
        int employeeId = 0;
        String errorMessage = tf_errorMessage.getText();

        // Check if form is complete
        if(employeeIdString.isEmpty()){
            errorMessage = "Error: Please complete all fields.";
            return;
        }

        try {
            employeeId = Integer.parseInt(employeeIdString); // Convert string to double

            Employee employeeRemove = null;
            for (Employee employee : listEmployee) {
                if (employee.getEmployeeId() == employeeId) {
                    employeeRemove = employee;
                    break;
                }
            }
            try {
                listEmployee.remove(employeeRemove);
            } catch(IndexOutOfBoundsException e){
                tf_errorMessage.setText("Error: Employee could not be removed from system.");
            }
        }
        catch(NumberFormatException e) {
            tf_errorMessage.setText("Error: Please enter a valid format.");

        };
        // Clear fields once form is submitted
        tf_employeeId.clear();
        tf_errorMessage.clear();
    }

    // --------------------------------------------------------------------

    private Stage stage;
    private Scene scene;
    private Parent root;



    // ---------------------------------------------------------------------    Scene Changes:

    // Main Menu
    public void switchToMainMenu(ActionEvent actionEvent)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // 1. Manage Info
    public void switchToManageInfo(ActionEvent actionEvent)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManageInfo.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

            // 1. a) Add New Full Time Employee
            public void switchToAddFullTime(ActionEvent actionEvent)throws IOException {
                    Parent root = FXMLLoader.load(getClass().getResource("addFullTime.fxml"));
                    stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }

            // 1. b) Add New Full Time Employee
            public void switchToAddPartTime(ActionEvent actionEvent)throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("addPartTime.fxml"));
                stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }

    // 2. View Info
    public void switchToViewInfo(ActionEvent actionEvent)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ViewInfo.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    // 3. Generate Payroll
    public void switchToPayroll(ActionEvent actionEvent)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Payroll.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    // ---------------------------------------------------------------------    File Handling:
    // Save Employees to File: employees.ser
    private void addEmployeeToFile(List<Employee> employees){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employees.ser", true))){
            oos.writeObject(employees);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // Load Employees from File: employees.ser
    public List<Employee> getEmployeesFromFile(){
        List<Employee> employees = new ArrayList<>();
        File file = new File("employees.ser"); // create file if it doesn't exist
/*
        if(file.exists()){
            try(ObjectInputStream ois = new ObjectInputStream( new FileInputStream(file))){
                Object obj = ois.readObject();
                if(obj instanceof List<?>){

                }
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }

        }*/
        return employees;
    }
}