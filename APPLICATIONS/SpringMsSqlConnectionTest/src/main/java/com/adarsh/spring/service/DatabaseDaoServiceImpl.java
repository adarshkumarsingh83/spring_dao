package com.adarsh.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Scope(value = "singleton")
@Service(value = "databaseDaoService")
public class DatabaseDaoServiceImpl implements DatabaseDaoService {

    @Autowired(required = true)
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getDatabaseTables() throws Exception {
        final String sqlQuery = "SELECT * FROM SYS.TABLES";
        return this.jdbcTemplate.queryForList(sqlQuery);
    }
}
