package com.adarsh.spring.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;


import com.adarsh.spring.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("personServiceImpl")
public class PersonServiceImpl implements PersonService {

    protected static Logger logger = LoggerFactory.getLogger("PersonServiceImpl");
    private SimpleJdbcTemplate jdbcTemplate;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    /**
     * Retrieves all persons
     *
     * @return a list of persons
     */
    @Override
    public List<Person> getAll() {
        logger.debug("Retrieving all persons");

        // Prepare our SQL statement
        final String sql = "select id, first_name, last_name, money from person";

        return this.jdbcTemplate.query(sql, new RowMapper<Person>() {
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                Person person = new Person(rs.getInt("id"),
                        rs.getString("first_name")
                        , rs.getString("last_name")
                        , rs.getDouble("money"));
                return person;
            }
        });
    }

    /**
     * Adds a new person
     *
     * @param firstName the first name of the person
     * @param lastName  the last name of the person
     * @param money     the money of the person
     */
    @Override
    public void add(String firstName, String lastName, Double money) {
        logger.debug("Adding new person");

        // Prepare our SQL statement using Named Parameters style
        final String sql = "insert into person(first_name, last_name, money) values " +
                "(:firstName, :lastName, :money)";

        // Assign values to parameters
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstName", firstName);
        parameters.put("lastName", lastName);
        parameters.put("money", money);
        // Save
        this.jdbcTemplate.update(sql, parameters);
    }

    /**
     * Deletes an existing person
     *
     * @param id the id of the existing person
     */
    @Override
    public void delete(Integer id) {
        logger.debug("Deleting existing person");

        // Prepare our SQL statement using Unnamed Parameters style
        final String sql = "delete from person where id = ?";

        // Delete
        this.jdbcTemplate.update(sql, new Object[]{id});
    }

    /**
     * Edits an existing person
     *
     * @param id        the id of the existing person
     * @param firstName the first name of the existing person
     * @param lastName  the last name of the existing person
     * @param money     the money of the existing person
     */
    @Override
    public void edit(Integer id, String firstName, String lastName, Double money) {
        logger.debug("Editing existing person");

        // Prepare our SQL statement
        final String sql = "update person set first_name = :firstName, " +
                "last_name = :lastName, money = :money where id = :id";

        // Assign values to parameters
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("firstName", firstName);
        parameters.put("lastName", lastName);
        parameters.put("money", money);

        // Edit
        this.jdbcTemplate.update(sql, parameters);

    }
}
