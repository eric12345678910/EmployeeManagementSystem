package application;

import java.io.Serializable;

public abstract class Employee implements Serializable {
    private static int generator = 0; // used to auto increment employee id
    protected int employeeId;
    protected String f_name;
    protected String l_name;
    protected String position;
    protected String department;


    // Constructor for Base Employee (salary/pay tbd)
    public Employee(String f_name, String l_name, String position, String department) {
        this.employeeId = ++generator; // auto increment employee id number
        this.f_name = f_name;
        this.l_name = l_name;
        this.position = position;
        this.department = department;
    }

    // Getters
    public int getEmployeeId() { return employeeId; }
    public String getName() { return f_name + " " + l_name; }
    public String getPosition() { return position; }
    public String getDepartment() { return department; }


    // Calculate employee pay (tbd)
    public abstract double calculatePayroll();

    @Override
    public String toString() {
        return String.format(
                "Employee ID: " + employeeId +
                        "\nName: " + f_name + " " + l_name +
                        "\nPosition: " + position +
                        "\nDepartment: " + department +
                        "\n- - - - - - - - - - - - - - - - - - -");
    }

}