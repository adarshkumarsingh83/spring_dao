package com.adarsh.spring.dao;


import com.adarsh.spring.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Scope(value = "singleton")
@Service(value = "employeeDaoServiceImpl")
public class EmployeeDaoServiceImpl implements EmployeeDaoService {


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
        final String sqlQuery = "SELECT * FROM EMPLOYEE WHERE EMP_NO = ?";
        return this.jdbcTemplate.queryForMap(sqlQuery, new Object[]{employeeNumber});
    }

    @Override
    public Integer updateEmployee(Integer employeeNumber, String employeeName, String employeeEmail) {
        final String sqlQuery = "UPDATE EMPLOYEE SET EMP_NAME=?,EMP_EMAIL=? WHERE EMP_NO=?";
        return this.jdbcTemplate.update(sqlQuery, new Object[]{employeeName, employeeEmail, employeeNumber});
    }

    @Override
    public Employee getEmployeeData(Integer employeeNumber) {
        final String sqlQuery = "SELECT * FROM EMPLOYEE WHERE EMP_NO =?";
        return (Employee) jdbcTemplate.queryForObject(sqlQuery, new Object[]{employeeNumber}
                , new RowMapper() {
            public Object mapRow(ResultSet resultSet, int rowNum)
                    throws SQLException {
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
        return jdbcTemplate.update(sqlQuery, new Object[]{employeeNumber, employeeName, emplyeeEmail});
    }

    @Override
    public Integer getEmployeeCount() {
        final String sqlQuery = "SELECT COUNT(*) FROM EMPLOYEE";
        return jdbcTemplate.queryForInt(sqlQuery);
    }
}


	