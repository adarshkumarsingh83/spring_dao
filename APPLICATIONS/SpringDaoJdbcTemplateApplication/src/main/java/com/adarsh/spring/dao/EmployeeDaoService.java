package com.adarsh.spring.dao;

import java.util.*;

import com.adarsh.spring.domain.Employee;
/**
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 1595 $, $Date:: 5/4/12 6:12 PM#$
 */
public interface EmployeeDaoService {

    public Integer getEmployeeCount();

    public Integer insertEmployee(Integer employeeNumber, String employeeName, String employeeEmail);

    public List<Map<String, Object>> getEmployeeDataList();

    public Employee getEmployeeData(Integer employeeNumber);

    public Map<String, Object> getEmployeeDataMap(Integer employeeNumber);

    public Integer updateEmployee(Integer employeeNumber, String employeeName, String employeeEmail);

}
