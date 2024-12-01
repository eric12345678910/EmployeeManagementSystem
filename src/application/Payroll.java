package application;

import application.Employee;

public class Payroll {
    private Employee employee;
    private double salary;
    private double hoursWorked;
    private double hourlyRate;
    private double taxRate = 0.15;
    private double overtimeRate = 1.5; //
    private double bonus;
    private double deductions;


    // Constructor:
    public Payroll(Employee employee, double salary) {
        this.employee = employee;
       // this.salary = salary;

    }

    // Getters & Setters for: Employee
    public Employee getEmployee(){
        return employee;
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    // Getters & Setters for: Salary
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Getters & Setters for: Hours Worked
    public double getHoursWorked() {
        return hoursWorked;
    }
    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
    public void setHourlyRate(double hoursWorked) {
        this.hourlyRate = hoursWorked;
    }

    // Getters & Setters for: Bonus
    public double getBonus() {
        return bonus;
    }
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    // Getters & Setters for: Deductions
    public double getDeductions() {
        return deductions;
    }
    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }


    public double generatePayroll(){
        double payBeforeDeductions;
        double payAfterDeductions;

        // Verify based on type of employee
        if (employee instanceof FullTimeEmployee){
            payBeforeDeductions = (salary/26) + bonus; // 2 week period
        }
        else if(employee instanceof PartTimeEmployee){
            payBeforeDeductions = hoursWorked * hourlyRate;
        }
        else{
            return 0;
        }

        // After deductions:
        double incomeTax = payBeforeDeductions  * taxRate;

        payAfterDeductions = payBeforeDeductions - incomeTax;

        return payAfterDeductions;

    }
}
