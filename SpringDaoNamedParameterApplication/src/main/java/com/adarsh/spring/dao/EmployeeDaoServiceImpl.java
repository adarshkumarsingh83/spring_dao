package com.adarsh.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import com.adarsh.spring.domain.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Scope(value = "singleton")
@Service(value = "employeeDaoServiceImpl")
public class EmployeeDaoServiceImpl implements EmployeeDaoService {


    @Qualifier(value = "namedParameterJdbcTemplate")
    @Autowired(required = true)
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Map<String, Object>> getEmployeeDataList() {
        final String sqlQuery = "SELECT * FROM EMPLOYEE";
        return this.namedParameterJdbcTemplate.queryForList(sqlQuery, new HashMap<String, Object>());
    }

    @Override
    public Map<String, Object> getEmployeeDataMap(Integer employeeNumber) {
        final String sqlQuery = "SELECT * FROM EMPLOYEE WHERE EMP_NO = :empNo";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("empNo", employeeNumber);
        return this.namedParameterJdbcTemplate.queryForMap(sqlQuery, namedParameters);
    }

    @Override
    public Integer updateEmployee(Integer employeeNumber, String employeeName, String employeeEmail) {
        final String sqlQuery = "UPDATE EMPLOYEE SET EMP_NAME=:empName,EMP_EMAIL=:empEmail WHERE EMP_NO=:empNo";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("empNo", employeeNumber);
        namedParameters.addValue("empName", employeeName);
        namedParameters.addValue("empEmail", employeeEmail);
        return this.namedParameterJdbcTemplate.update(sqlQuery, namedParameters);
    }

    @Override
    public Employee getEmployeeData(Integer employeeNumber) {
        final String sqlQuery = "SELECT * FROM EMPLOYEE WHERE EMP_NO = :EMP_NO";
        SqlParameterSource namedParameters = new MapSqlParameterSource("EMP_NO", employeeNumber);
        return (Employee) namedParameterJdbcTemplate.queryForObject(sqlQuery, namedParameters
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
        final String sqlQuery = "INSERT INTO EMPLOYEE(EMP_NO,EMP_NAME,EMP_EMAIL)VALUES(:empNo,:empName,:empEmail)";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("empNo", employeeNumber);
        namedParameters.addValue("empName", employeeName);
        namedParameters.addValue("empEmail", emplyeeEmail);
        return namedParameterJdbcTemplate.update(sqlQuery, namedParameters);
    }

    @Override
    public Integer getEmployeeCount() {
        final String sqlQuery = "SELECT COUNT(*) FROM EMPLOYEE";
        return namedParameterJdbcTemplate.queryForInt(sqlQuery, new HashMap());
    }
}


	