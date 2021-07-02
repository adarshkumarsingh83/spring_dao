package com.adarsh.spring.main;

import com.adarsh.spring.dao.EmployeeDaoService;
import java.util.*;

import com.adarsh.spring.dao.EmployeeDaoServiceImpl;
import com.adarsh.spring.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationMain {
    
    private static final Logger LOGGER= LoggerFactory.getLogger(ApplicationMain.class);

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/configuration/applicationContext.xml");
		EmployeeDaoService employee =  context.getBean(EmployeeDaoService.class);
		
		 Map<String,Object> employeeMap = (Map<String,Object>)employee.getEmployeeDataMap(new Integer(1));
		 for(Map.Entry<String,Object> m:employeeMap.entrySet()){
			 LOGGER.info(m.getKey()+" "+m.getValue());
		 }
		 
		 List employeeList = employee.getEmployeeDataList();
		 for(int i=0;i<employeeList.size();i++){
			 Map<String,Object> employeeMaps=(Map<String,Object>)employeeList.get(i);
			 for(Map.Entry<String,Object> m:employeeMaps.entrySet()){
				 LOGGER.info(m.getKey()+" "+m.getValue());
			 }
		 }
		 
		 Employee employeeObject=employee.getEmployeeData(new Integer(1));
		 LOGGER.info("Employee Date "+employeeObject.display());
		 
		 Integer employeeCount=employee.getEmployeeCount();
		 LOGGER.info("Employee count"+employeeCount);
		 
		 Integer insertStatus=employee.insertEmployee(new Integer(10), "employeeName", "employeeEmail");
		 LOGGER.info("Employee inserted "+insertStatus);
		 
		 Integer updateStatus=employee.updateEmployee(new Integer(10), "employeeName", "employeeEmail");
		 LOGGER.info("Updated Employee "+updateStatus);
	}
	
}
