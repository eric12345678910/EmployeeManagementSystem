package application;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class PartTimeEmployee extends Employee {
    private double regularHoursWorked;
    private double overtimeHoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(
            String f_name, String l_name, String position, String department,
            double regularHoursWorked, double overtimeHoursWorked, double hourlyRate) {
        super(f_name, l_name, position, department);
        this.regularHoursWorked = regularHoursWorked;
        this.overtimeHoursWorked = overtimeHoursWorked;

        this.hourlyRate = hourlyRate;
    }

    // Regular Hours Worked
    public double getRegularHoursWorked() {
        return regularHoursWorked;
    }

    // Overtime Hours Worked
    public double getOvertimeHoursWorked() {
        return overtimeHoursWorked;
    }

    // Hourly Pay Rate
    public double getHourlyRate() {
        return hourlyRate;
    }



    @Override
    public double calculatePayroll(){

        double regularPay = regularHoursWorked * hourlyRate;
        double overtimePay = overtimeHoursWorked * hourlyRate;

        return regularPay + overtimePay; // Total pay earned
    }

    @Override
    public String toString() {
        return String.format(
                "\nPart-Time Employee: " +
                        "\n Name: " + getName() +
                        "\n Position: " + position +
                        "\n Department: " + department +
                        "\n Regular Hours Worked: " + regularHoursWorked +
                        "\n Overtime Hours Worked: " + overtimeHoursWorked +
                        "\n Hourly Rate: " + hourlyRate +
                        "\n Pay: " + calculatePayroll() +
                        "\n- - - - - - - - - - - - - - - - - - -");
    }
}
