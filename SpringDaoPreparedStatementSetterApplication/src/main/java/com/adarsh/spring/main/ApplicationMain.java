package com.adarsh.spring.main;

import java.util.*;

import com.adarsh.spring.dao.EmployeeDaoService;
import com.adarsh.spring.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationMain {
    
    private static final Logger LOGGER= LoggerFactory.getLogger(ApplicationMain.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/configuration/applicationContext.xml");
		EmployeeDaoService employeeDaoService = context.getBean(EmployeeDaoService.class);
		
		 List <Employee>employeeListObject =employeeDaoService.getEmployeeDataList(new Integer(101));
		 for(int i=0;i<employeeListObject.size();i++){
			 Employee emp=(Employee)employeeListObject.get(i);
			 LOGGER.info(emp.display());
		 }
		 
		 List<Map<String,Object>> employeeList = employeeDaoService.getEmployeeDataList();
		 for(int i=0;i<employeeList.size();i++){
		        Map <String,Object>empMap=employeeList.get(i);
		        for(Map.Entry<String,Object> empMapObj : empMap.entrySet()){
		        	LOGGER.info(empMapObj.getKey()+" "+empMapObj.getValue());
		        }
		 }
		 
		 List<Employee> employeeObject=employeeDaoService.getEmployeeData(new Integer(101));
		 for(int i=0;i<employeeObject.size();i++){
			 Employee emp=employeeObject.get(i);			 
				 LOGGER.info(emp.display());
			
		 }
		 
		 Integer employeeCount=employeeDaoService.getEmployeeCount();
		 LOGGER.info("Employee count "+employeeCount);
		 Integer insertStatus=employeeDaoService.insertEmployee(new Integer(10), "employeeName", "employeeEmail");
		 LOGGER.info("Employee Inserted status "+insertStatus);
		 Integer updataStatus=employeeDaoService.updateEmployee(new Integer(10), "employeeName", "employeeEmail");
		 LOGGER.info("Employee Updated Status "+updataStatus);
	}
	
}
