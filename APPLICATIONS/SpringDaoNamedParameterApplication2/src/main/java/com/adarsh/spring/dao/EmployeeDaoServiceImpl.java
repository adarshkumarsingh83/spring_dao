package com.adarsh.spring.dao;

import com.adarsh.spring.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
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
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public List<Map<String, Object>> getEmployeeDataList() {
        final String sqlQuery = "SELECT * FROM EMPLOYEE";
        return this.namedJdbcTemplate.queryForList(sqlQuery, new HashMap());
    }

    @Override
    public Map<String, Object> getEmployeeDataMap(Integer employeeNumber) {
        final String sqlQuery = "SELECT * FROM EMPLOYEE WHERE EMP_NO = :empNo";
        return this.namedJdbcTemplate.queryForMap(sqlQuery, new MapSqlParameterSource("empNo", employeeNumber));
    }

    @Override
    public Integer updateEmployee(Integer employeeNumber, String employeeName, String employeeEmail) {
        final String sqlQuery = "UPDATE EMPLOYEE SET EMP_NAME=:EMP_NAME,EMP_EMAIL=:EMP_EMAIL WHERE EMP_NO=:EMP_NO";
        final Map namedParameters = new HashMap();
        namedParameters.put("EMP_NO", employeeNumber);
        namedParameters.put("EMP_NAME", employeeName);
        namedParameters.put("EMP_EMAIL", employeeEmail);
        return this.namedJdbcTemplate.update(sqlQuery, namedParameters);
    }

    @Override
    public Employee getEmployeeData(Integer employeeNumber) {
        final String sqlQuery = "SELECT * FROM EMPLOYEE WHERE EMP_NO = :empNo";
        return (Employee) namedJdbcTemplate.queryForObject(sqlQuery
                , new MapSqlParameterSource("empNo", employeeNumber)
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
        final String sqlQuery = "INSERT INTO EMPLOYEE(EMP_NO,EMP_NAME,EMP_EMAIL)VALUES(:EMP_NO,:EMP_NAME,:EMP_EMAIL)";
        final Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("EMP_NO", employeeNumber);
        namedParameters.put("EMP_NAME", employeeName);
        namedParameters.put("EMP_EMAIL", emplyeeEmail);
        return namedJdbcTemplate.update(sqlQuery, namedParameters);
    }

    @Override
    public Integer getEmployeeCount() {
        final String sqlQuery = "SELECT COUNT(*) FROM EMPLOYEE";
        return namedJdbcTemplate.queryForInt(sqlQuery, new HashMap());
    }
}


	