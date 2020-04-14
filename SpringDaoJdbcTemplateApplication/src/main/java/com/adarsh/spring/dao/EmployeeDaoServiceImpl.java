package com.adarsh.spring.dao;


import com.adarsh.spring.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 1595 $, $Date:: 5/4/12 6:12 PM#$
 */
@Transactional
@Scope(value = "singleton")
@Service(value = "employeeDaoServiceImpl")
public class EmployeeDaoServiceImpl implements EmployeeDaoService {

    final private static Logger LOGGER= LoggerFactory.getLogger(EmployeeDaoServiceImpl.class);

    @Qualifier(value = "jdbcTemplate")
    @Autowired(required = true)
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getEmployeeDataList() {
        final String sqlQuery = "SELECT * FROM EMPLOYEE";
        return this.jdbcTemplate.queryForList(sqlQuery);
    }

    @Override
    public Map<String, Object> getEmployeeDataMap(Integer employeeNumber) {
        final String sqlQuery = "SELECT Emp_no,Emp_Name,Emp_Email FROM EMPLOYEE WHERE EMP_NO = ?";
        return this.jdbcTemplate.queryForMap(sqlQuery, new Object[]{employeeNumber});
    }

    @Override
    public Integer getEmployeeCount() {
        final String sqlQuery = "SELECT COUNT(*) FROM EMPLOYEE";
        return this.jdbcTemplate.queryForInt(sqlQuery);
    }

    @Override
    public Integer updateEmployee(Integer employeeNumber, String employeeName, String employeeEmail) {
        final String sqlQuery = "UPDATE EMPLOYEE SET EMP_NAME=?,EMP_EMAIL=? WHERE EMP_NO=?";
        return this.jdbcTemplate.update(sqlQuery, new Object[]{employeeName, employeeEmail, employeeNumber});
    }

    @Override
    public Employee getEmployeeData(Integer employeeNumber) {
        final String sqlQuery = "SELECT * FROM EMPLOYEE WHERE EMP_NO = ?";

        return (Employee) jdbcTemplate.queryForObject(sqlQuery, new Object[]{employeeNumber},
                new RowMapper() {
                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return new Employee(resultSet.getInt("EMP_NO")
                                , resultSet.getString("EMP_NAME")
                                , resultSet.getString("EMP_EMAIL")
                        );
                    }
                }
        );
    }

    @Override
    public Integer insertEmployee(Integer employeeNumber, String employeeName, String emplyeeEmail) {
        final String sqlQuery = "INSERT INTO EMPLOYEE(EMP_NO,EMP_NAME,EMP_EMAIL)VALUES(?,?,?)";
        return this.jdbcTemplate.update(sqlQuery, new Object[]{employeeNumber, employeeName, emplyeeEmail});
    }

}
