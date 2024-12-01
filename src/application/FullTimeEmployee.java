package application;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class FullTimeEmployee extends Employee {
    private double salary;
    private double bonus;

    public FullTimeEmployee(String f_name, String l_name, String position, String department, double baseSalary ) {
        super(f_name, l_name, position, department);
        this.salary = baseSalary;
    }

    // Salary
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Bonus
    public double getBonus() {
        return bonus;
    }
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    // Handle deductions tbd
    public double deductIncomeTax(){
        return (salary + bonus) * 0.15; // assume 15% / not marginal tax rate
    }

    public double deductEIContribution(){
        double eIContribution = (salary + bonus) * 0.163;
        if(eIContribution > 1002){
            eIContribution = 1002;
        }
        return eIContribution;
    }


    /*
        - the Canada Pension Plan contribution.
        - the Employment Insurance premium.
        - federal income tax.  base on salary or just set standard rate?
        - provincial and territorial income tax. (lets assume province is ontario)
     */


    @Override
    public double calculatePayroll(){
        double incomeTaxDeduction = deductIncomeTax();
        double eIContribution = deductEIContribution();

        return (salary + bonus) - (incomeTaxDeduction + eIContribution); // - deductions tbd
    }


    @Override
    public String toString() {
        return String.format(
                "\nFull Time Employee: " +
                        "\nName: " + getName() +
                        "\nPosition: " + position +
                        "\nDepartment: " + department +
                        "\n" +
                        "\nSalary: " + salary +

                        "\nDeductions: " +
                        "\nIncome Tax: " + deductIncomeTax() +
                        "\nEmployment Insurance Contribution: " + deductEIContribution() +
                        "\n- - - - - - - - - - - - - - - - - - -" +
                        "\nTotal pay after deductions: " + calculatePayroll());
    }
}
