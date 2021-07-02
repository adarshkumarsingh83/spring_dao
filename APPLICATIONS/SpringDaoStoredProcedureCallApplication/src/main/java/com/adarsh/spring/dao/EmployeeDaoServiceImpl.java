package com.adarsh.spring.dao;

import com.adarsh.spring.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


import javax.sql.DataSource;

@Transactional
@Scope(value = "singleton")
@Service(value = "employeeDaoServiceImpl")
public class EmployeeDaoServiceImpl implements EmployeeDaoService {


    @Qualifier(value = "dataSource")
    @Autowired(required = true)
    private DataSource dataSource;

    public Employee getEmployeeData(Integer employeeNumber) {
        MyEmployeeSelect empProcedure = new MyEmployeeSelect(dataSource);
        Map<String, Object> map = empProcedure.execute(employeeNumber);
        Employee emp = new Employee();
        emp.setEmployeeNumber((Long) map.get("VAR_EMP_NO"));
        emp.setEmployeeName((String) map.get("VAR_EMP_NAME"));
        emp.setEmployeeEmail((String) map.get("VAR_EMP_EMAIL"));
        return emp;
    }

    public Integer updateEmployee(Integer employeeNumber, String employeeName, String employeeEmail) {
        MyEmployeeUpdate empProcedure = new MyEmployeeUpdate(dataSource);
        Map<String, Object> map = empProcedure.execute(employeeNumber, employeeName, employeeEmail);

        return (Integer) map.get("#update-count-1");
    }

    public Integer insertEmployee(Integer employeeNumber, String employeeName, String employeeEmail) {
        MyEmployeeInsert empProcedure = new MyEmployeeInsert(dataSource);
        Map<String, Object> map = empProcedure.execute(employeeNumber, employeeName, employeeEmail);
        return (Integer) map.get("#update-count-1");
    }

    public Integer deleteEmployee(Integer employeeNumber) {
        MyEmployeeDelete empProcedure = new MyEmployeeDelete(dataSource);
        Map<String, Object> map = empProcedure.execute(employeeNumber);
        return (Integer) map.get("#update-count-1");
    }

    public static class MyEmployeeSelect extends StoredProcedure {

        public MyEmployeeSelect(DataSource dataSource) {
            super(dataSource, "EMPLOYEE_SELECT");
            super.setFunction(false);

            SqlParameter param[] = {
                    new SqlParameter("VAR_EMP_ID", java.sql.Types.BIGINT)
                    , new SqlOutParameter("VAR_EMP_NO", java.sql.Types.BIGINT)
                    , new SqlOutParameter("VAR_EMP_NAME", java.sql.Types.VARCHAR)
                    , new SqlOutParameter("VAR_EMP_EMAIL", java.sql.Types.VARCHAR)
            };
            this.setParameters(param);
            compile();
        }

        public Map<String, Object> execute(Integer employeeNumber) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("VAR_EMP_ID", employeeNumber);
            return super.execute(map);
        }
    }

    public static class MyEmployeeInsert extends StoredProcedure {
        public MyEmployeeInsert(DataSource dataSource) {
            super(dataSource, "EMPLOYEE_INSERT");
            this.setFunction(false);

            SqlParameter param[] = {
                    new SqlParameter("EMP_NO", java.sql.Types.INTEGER)
                    , new SqlParameter("EMP_NAME", java.sql.Types.VARCHAR)
                    , new SqlParameter("EMP_EMAIL", java.sql.Types.VARCHAR)
            };
            this.setParameters(param);
            compile();
        }

        public Map<String, Object> execute(Integer employeeNumber, String employeeName, String employeeEmail) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("EMP_NO", employeeNumber);
            map.put("EMP_NAME", employeeName);
            map.put("EMP_EMAIL", employeeEmail);
            return super.execute(map);
        }
    }

    public static class MyEmployeeUpdate extends StoredProcedure {
        public MyEmployeeUpdate(DataSource dataSource) {
            super(dataSource, "EMPLOYEE_UPDATE");
            this.setFunction(false);

            SqlParameter param[] = {
                    new SqlParameter("EMP_ID", java.sql.Types.INTEGER)
                    , new SqlParameter("EMP_NAME", java.sql.Types.VARCHAR)
                    , new SqlParameter("EMP_EMAIL", java.sql.Types.VARCHAR)
            };
            this.setParameters(param);
            compile();
        }

        public Map<String, Object> execute(Integer employeeNumber, String employeeName, String employeeEmail) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("EMP_ID", employeeNumber);
            map.put("EMP_NAME", employeeName);
            map.put("EMP_EMAIL", employeeEmail);
            return super.execute(map);
        }
    }

    public static class MyEmployeeDelete extends StoredProcedure {

        public MyEmployeeDelete(DataSource dataSource) {
            super(dataSource, "EMPLOYEE_DELETE");
            this.setFunction(false);

            SqlParameter param[] = {
                    new SqlParameter("EMP_ID", java.sql.Types.INTEGER)
            };
            this.setParameters(param);
            compile();
        }

        public Map<String, Object> execute(Integer employeeNumber) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("EMP_ID", employeeNumber);
            return super.execute(map);
        }
    }
}
