package com.adarsh.spring.main;

import com.adarsh.spring.dao.EmployeeDaoService;

import java.util.*;

import com.adarsh.spring.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 1595 $, $Date:: 5/4/12 6:12 PM#$
 */
public class ApplicationMain {

    final private static Logger LOGGER= LoggerFactory.getLogger(ApplicationMain.class);

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/configuration/applicationContext.xml");
        EmployeeDaoService employee = context.getBean(EmployeeDaoService.class);

        Map employeeMap = employee.getEmployeeDataMap(new Integer(1));
        List employeeList = employee.getEmployeeDataList();
        for (int i = 0; i < employeeList.size(); i++) {
            Map<String, Employee> map = (Map<String, Employee>) employeeList.get(i);
            for (Map.Entry<String, Employee> emp : map.entrySet())
                LOGGER.info(":=> "+emp.getKey() + " " + emp.getValue());
        }

        Employee employeeObject = employee.getEmployeeData(new Integer(2));
        LOGGER.info(":=> "+employeeObject.display());

        Integer employeeCount = employee.getEmployeeCount();
        LOGGER.info(":=> Total Employee " + employeeCount);

        Integer insertStatus = employee.insertEmployee(new Integer(3), "employeeName", "employeeEmail");
        LOGGER.info(":=> Employee Inserted " + insertStatus);

        Integer updatedStatus = employee.updateEmployee(new Integer(3), "employeeName_U", "employeeEmail_u");
        LOGGER.info(":=> Employee Updated " + updatedStatus);
    }


}
