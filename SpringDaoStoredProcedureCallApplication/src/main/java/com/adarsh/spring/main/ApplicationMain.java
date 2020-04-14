package com.adarsh.spring.main;

import com.adarsh.spring.dao.EmployeeDaoService;
import com.adarsh.spring.domain.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationMain {
    
    private static final Logger LOGGER= LoggerFactory.getLogger(ApplicationMain.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/configuration/applicationContext.xml");
        EmployeeDaoService employee = (EmployeeDaoService) context.getBean(EmployeeDaoService.class);

        Integer insertStatus = employee.insertEmployee(new Integer(10), "employeeName", "employeeEmail");
        LOGGER.info("Employee Inserted status " + insertStatus);

        Employee employeeObject = employee.getEmployeeData(new Integer(1));
        LOGGER.info("Employee Data " + employeeObject.display());

        Integer updateStatus = employee.updateEmployee(new Integer(10), "ADARSH KUMAR", "adarsh@sourcn.com");
        LOGGER.info("Employee Updated status " + updateStatus);

        Integer deletionStatus = employee.deleteEmployee(new Integer(10));
        LOGGER.info("Employee Deletion Status " + deletionStatus);

    }

}
