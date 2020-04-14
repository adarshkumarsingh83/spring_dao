package com.adarsh.spring.dao;

import com.adarsh.spring.domain.Employee;


public interface EmployeeDaoService {


    public Integer insertEmployee(Integer employeeNumber, String employeeName, String employeeEmail);

    public Integer updateEmployee(Integer employeeNumber, String employeeName, String employeeEmail);

    public Employee getEmployeeData(Integer employeeNumber);

    public Integer deleteEmployee(Integer empNumber);


}
