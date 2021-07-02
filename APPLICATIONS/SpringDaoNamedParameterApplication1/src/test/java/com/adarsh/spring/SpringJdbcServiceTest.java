package com.adarsh.spring;


import com.adarsh.spring.dao.EmployeeDaoService;
import com.adarsh.spring.domain.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

public class SpringJdbcServiceTest extends TestAnnotatedContext {

    @Qualifier(value = "employeeDaoServiceImpl")
    @Autowired(required = true)
    private EmployeeDaoService employeeDaoService;

    public SpringJdbcServiceTest() {
        super(SpringJdbcServiceTest.class);
    }

    @Test
    public void getEmployeeById() {
        Employee employeeObject = employeeDaoService.getEmployeeData(new Integer(1));
        super.logger.info("Employee Date " + employeeObject.display());
    }

    @Test
    public void getEmployeeMap() {
        final Map<String, Object> employeeMap = (Map<String, Object>) employeeDaoService.getEmployeeDataMap(new Integer(1));
        for (Map.Entry<String, Object> m : employeeMap.entrySet()) {
            super.logger.info(m.getKey() + " " + m.getValue());
        }
    }

    @Test
    public void getAllEmployee() {
        List employeeList = employeeDaoService.getEmployeeDataList();
        for (int i = 0; i < employeeList.size(); i++) {
            final Map<String, Object> employeeMaps = (Map<String, Object>) employeeList.get(i);
            for (Map.Entry<String, Object> m : employeeMaps.entrySet()) {
                super.logger.info(m.getKey() + " " + m.getValue());
            }
        }
    }

    @Test
    public void getEmployeeCount() {
        Integer employeeCount = employeeDaoService.getEmployeeCount();
        super.logger.info("Employee count" + employeeCount);
    }

    @Test
    public void getEmployeeInsertAndUpdate() {

        Integer insertStatus = employeeDaoService.insertEmployee(new Integer(4), "employeeName", "employee@Email4");
        super.logger.info("Employee inserted " + insertStatus);

        Integer updateStatus = employeeDaoService.updateEmployee(new Integer(1), "employeeName", "employee@Email1");
        super.logger.info("Updated Employee " + updateStatus);
    }
}
