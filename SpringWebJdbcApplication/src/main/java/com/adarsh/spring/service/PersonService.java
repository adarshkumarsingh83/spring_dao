package com.adarsh.spring.service;


import com.adarsh.spring.domain.Person;
import java.util.List;




public interface PersonService {


    /**
     * Retrieves all persons
     *
     * @return a list of persons
     */
    public List<Person> getAll();

    /**
     * Adds a new person
     *
     * @param firstName the first name of the person
     * @param lastName  the last name of the person
     * @param money     the money of the person
     */
    public void add(String firstName, String lastName, Double money) ;

    /**
     * Deletes an existing person
     *
     * @param id the id of the existing person
     */
    public void delete(Integer id);

    /**
     * Edits an existing person
     *
     * @param id        the id of the existing person
     * @param firstName the first name of the existing person
     * @param lastName  the last name of the existing person
     * @param money     the money of the existing person
     */
    public void edit(Integer id, String firstName, String lastName, Double money);
}
