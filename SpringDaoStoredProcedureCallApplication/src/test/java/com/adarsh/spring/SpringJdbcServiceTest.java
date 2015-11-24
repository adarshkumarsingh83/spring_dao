package com.adarsh.spring;


import com.adarsh.spring.dao.EmployeeDaoService;
import com.adarsh.spring.domain.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SpringJdbcServiceTest extends TestAnnotatedContext {

    @Qualifier(value = "employeeDaoServiceImpl")
    @Autowired(required = true)
    private EmployeeDaoService employeeDaoService;

    public SpringJdbcServiceTest() {
        super(SpringJdbcServiceTest.class);
    }

    @Test
    public void test() throws Exception {

        Integer insertStatus = employeeDaoService.insertEmployee(new Integer(101), "employeeName", "employee@Email");
        System.out.println("Employee Inserted status " + insertStatus);

        Employee employeeObject = employeeDaoService.getEmployeeData(new Integer(101));
        System.out.println("Employee Data " + employeeObject.display());

        Integer updatedStatus = employeeDaoService.updateEmployee(new Integer(101), "ADARSH KUMAR", "adarsh@sourcn.com");
        System.out.println("Employee Updated status " + updatedStatus);

        Integer deletionStatus = employeeDaoService.deleteEmployee(new Integer(3));
        System.out.println("Employee Deletion Status " + deletionStatus);
    }
}
