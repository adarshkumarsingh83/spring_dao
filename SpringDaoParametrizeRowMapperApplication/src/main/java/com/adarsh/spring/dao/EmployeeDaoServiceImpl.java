package com.adarsh.spring.dao;


import com.adarsh.spring.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Scope(value = "singleton")
@Service(value = "employeeDaoServiceImpl")
public class EmployeeDaoServiceImpl implements EmployeeDaoService {


    @Qualifier(value = "jdbcTemplate")
    @Autowired(required = true)
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getEmployeeDataList() {
        String sqlQuery = "SELECT * FROM EMPLOYEE";
        return this.jdbcTemplate.queryForList(sqlQuery);
    }

    @Override
    public Map<String, Object> getEmployeeDataMap(Integer employeeNumber) {
        String sqlQuery = "SELECT * FROM EMPLOYEE WHERE EMP_NO = ?";
        return this.jdbcTemplate.queryForMap(sqlQuery, new Object[]{employeeNumber});
    }

    @Override
    public Integer updateEmployee(Integer employeeNumber, String employeeName, String employeeEmail) {
        String sqlQuery = "UPDATE EMPLOYEE SET EMP_NAME=?,EMP_EMAIL=? WHERE EMP_NO=?";
        return this.jdbcTemplate.update(sqlQuery, new Object[]{employeeName, employeeEmail, employeeNumber});
    }

    @Override
    public Employee getEmployeeData(Integer employeeNumber) {
        String sqlQuery = "SELECT * FROM EMPLOYEE WHERE EMP_NO = ?";
        return (Employee) jdbcTemplate.queryForObject(sqlQuery, new Object[]{employeeNumber},
                new ParameterizedRowMapper<Employee>() {
                    public Employee mapRow(ResultSet resultSet, int rowNum)
                            throws SQLException {
                        return new Employee(resultSet.getInt("EMP_NO")
                                , resultSet.getString("EMP_NAME")
                                , resultSet.getString("EMP_EMAIL")
                        );
                    }
                });

    }

    @Override
    public Integer insertEmployee(Integer employeeNumber, String employeeName, String emplyeeEmail) {
        final String sqlQuery = "INSERT INTO EMPLOYEE(EMP_NO,EMP_NAME,EMP_EMAIL)VALUES(?,?,?)";
        return jdbcTemplate.update(sqlQuery, new Object[]{employeeNumber, employeeName, emplyeeEmail});
    }

    @Override
    public Integer getEmployeeCount() {
        final String sqlQuery = "SELECT COUNT(*) FROM EMPLOYEE";
        return jdbcTemplate.queryForInt(sqlQuery);
    }
}


	