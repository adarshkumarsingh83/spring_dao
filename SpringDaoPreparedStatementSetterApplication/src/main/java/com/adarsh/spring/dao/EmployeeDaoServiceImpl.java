package com.adarsh.spring.dao;


import com.adarsh.spring.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<Employee> getEmployeeDataList(final Integer employeeNumber) {
        final String sqlQuery = "SELECT * FROM EMPLOYEE WHERE EMP_NO = ?";
        return (List<Employee>) this.jdbcTemplate.query(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection)
                            throws SQLException {
                        PreparedStatement preparedStatementObject = connection.prepareStatement(sqlQuery);
                        preparedStatementObject.setInt(1, employeeNumber);
                        return preparedStatementObject;
                    }
                }
                , new ResultSetExtractor() {
                    public Object extractData(ResultSet resultSet) throws SQLException {
                        List<Employee> employeeList = new ArrayList<Employee>();
                        while (resultSet.next()) {
                            Employee employeeObject = new Employee();
                            employeeObject.setEmployeeNumber(resultSet.getInt(1));
                            employeeObject.setEmployeeName(resultSet.getString(2));
                            employeeObject.setEmployeeEmail(resultSet.getString(3));
                            employeeList.add(employeeObject);
                        }
                        return employeeList;
                    }
                }
        );
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
    public List getEmployeeData(final Integer employeeNumber) {
        final String sqlQuery = "SELECT * FROM EMPLOYEE WHERE EMP_NO = ?";
        return (List) jdbcTemplate.query(sqlQuery
                , new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, employeeNumber);
                    }
                }
                , new ResultSetExtractor() {
                    final List employeeList = new ArrayList();

                    public Object extractData(ResultSet resultSet) throws SQLException {
                        Employee employee = null;
                        while (resultSet.next()) {
                            employee = new Employee(
                                    resultSet.getInt("EMP_NO")
                                    , resultSet.getString("EMP_NAME")
                                    , resultSet.getString("EMP_EMAIL")
                            );
                            employeeList.add(employee);
                        }
                        return employeeList;
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
