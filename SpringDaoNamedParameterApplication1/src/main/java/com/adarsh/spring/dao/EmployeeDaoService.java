package com.adarsh.spring.dao;

import java.util.*;

import com.adarsh.spring.domain.Employee;

public interface EmployeeDaoService {

    public Integer getEmployeeCount();

    public Integer insertEmployee(Integer employeeNumber, String employeeName, String employeeEmail);

    public List<Map<String, Object>> getEmployeeDataList();

    public Employee getEmployeeData(Integer employeeNumber);

    public Map<String, Object> getEmployeeDataMap(Integer employeeNumber);

    public Integer updateEmployee(Integer employeeNumber, String employeeName, String employeeEmail);

}
