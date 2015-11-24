package com.adarsh.spring.main;

import com.adarsh.spring.dao.EmployeeDaoService;

import java.util.*;

import com.adarsh.spring.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationMain {
    
    private static final Logger LOGGER= LoggerFactory.getLogger(ApplicationMain.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/configuration/applicationContext.xml");
        EmployeeDaoService employee = context.getBean(EmployeeDaoService.class);

        Map<String, Object> employeeMap = employee.getEmployeeDataMap(new Integer(1));
        for (Map.Entry<String, Object> map : employeeMap.entrySet()) {
            LOGGER.info(map.getKey() + " " + map.getValue());
        }

        List<Map<String, Object>> employeeList = employee.getEmployeeDataList();
        for (int i = 0; i < employeeList.size(); i++) {
            Map<String, Object> mapEmp = employeeList.get(i);
            for (Map.Entry<String, Object> map : mapEmp.entrySet()) {
                LOGGER.info(map.getKey() + " " + map.getValue());
            }
        }

        Employee employeeObject = employee.getEmployeeData(new Integer(1));
        LOGGER.info("Employee Details " + employeeObject.display());

        Integer employeeCount = employee.getEmployeeCount();
        LOGGER.info("Employee Count " + employeeCount);

        Integer insertStatus = employee.insertEmployee(new Integer(101), "employeeName", "employeeEmail");
        LOGGER.info("Employee Inserted status " + insertStatus);


        Integer updataStatus = employee.updateEmployee(new Integer(101), "employeeName", "employee@Email");
        LOGGER.info("Employee Updated status " + updataStatus);
    }

}
