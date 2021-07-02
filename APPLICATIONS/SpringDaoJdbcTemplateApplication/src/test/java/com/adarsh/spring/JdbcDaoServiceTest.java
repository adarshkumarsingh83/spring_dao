package com.adarsh.spring;


import com.adarsh.spring.dao.EmployeeDaoService;
import com.adarsh.spring.domain.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;
/**
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 1595 $, $Date:: 5/4/12 6:12 PM#$
 */
public class JdbcDaoServiceTest extends TestAnnotatedContext {

    @Qualifier(value = "employeeDaoServiceImpl")
    @Autowired(required = true)
    private EmployeeDaoService employeeDaoService;

    public JdbcDaoServiceTest() {
        super(JdbcDaoServiceTest.class);
    }

    @Test
    public void test() {
        Map employeeMap = employeeDaoService.getEmployeeDataMap(new Integer(1));
        List employeeList = employeeDaoService.getEmployeeDataList();
        for (int i = 0; i < employeeList.size(); i++) {
            Map<String, Employee> map = (Map<String, Employee>) employeeList.get(i);
            for (Map.Entry<String, Employee> emp : map.entrySet())
                super.logger.info(":=> " + emp.getKey() + " " + emp.getValue());
        }

        Employee employeeObject = employeeDaoService.getEmployeeData(new Integer(2));
        super.logger.info(":=> "+employeeObject.display());

        Integer employeeCount = employeeDaoService.getEmployeeCount();
        super.logger.info(":=> Total Employee " + employeeCount);

        Integer insertStatus = employeeDaoService.insertEmployee(new Integer(3), "employeeName", "employeeEmail");
        super.logger.info(":=> Employee Inserted " + insertStatus);

        Integer updatedStatus = employeeDaoService.updateEmployee(new Integer(3), "employeeName_U", "employeeEmail_u");
        super.logger.info(":=> Employee Updated " + updatedStatus);
    }
}
