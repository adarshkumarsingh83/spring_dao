package com.adarsh.spring.domain;

public class Employee {


    private Long employeeNumber;
    private String employeeName;
    private String employeeEmail;

    public Employee() {
        super();
    }

    public Employee(Long employeeNumber, String employeeName,
                    String employeeEmail) {
        super();
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
    }

    public Long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String display() {
        return this.employeeNumber + " " + this.employeeName + " " + this.employeeEmail;
    }

}
