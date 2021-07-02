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
		EmployeeDaoService employeeDaoService = context.getBean(EmployeeDaoService.class);
		
		 Map<String,Object> employeeMap = (Map<String,Object>)employeeDaoService.getEmployeeDataMap(new Integer(1));
		 for(Map.Entry<String,Object> m:employeeMap.entrySet()){
			 LOGGER.info(m.getKey()+" "+m.getValue());
		 }
		 
		 List employeeList = employeeDaoService.getEmployeeDataList();
		 for(int i=0;i<employeeList.size();i++){
			 Map<String,Object> employeeMaps=(Map<String,Object>)employeeList.get(i);
			 for(Map.Entry<String,Object> m:employeeMaps.entrySet()){
				 LOGGER.info(m.getKey()+" "+m.getValue());
			 }
		 }
		 
		 Employee employeeObject=employeeDaoService.getEmployeeData(new Integer(1));
		 LOGGER.info("Employee Date "+employeeObject.display());
		 
		 Integer employeeCount=employeeDaoService.getEmployeeCount();
		 LOGGER.info("Employee count"+employeeCount);
		 
		 Integer insertStatus=employeeDaoService.insertEmployee(new Integer(10), "employeeName", "employeeEmail");
		 LOGGER.info("Employee inserted " + insertStatus);
		 
		 Integer updataStatus=employeeDaoService.updateEmployee(new Integer(10), "employeeName", "employeeEmail");
		 LOGGER.info("Updated Employee "+updataStatus);
	}
	
}
