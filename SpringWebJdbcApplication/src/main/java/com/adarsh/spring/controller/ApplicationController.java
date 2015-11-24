package com.adarsh.spring.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;



public interface ApplicationController {


    /**
     * Handles and retrieves all persons and show it in a JSP page
     *
     * @return the name of the JSP page
     */
    public String getPersons(Model model);
    /**
     * Adds a new person by delegating the processing to PersonServiceImpl.
     * Displays a confirmation JSP page
     *
     * @return the name of the JSP page
     */

    public String add(
            @RequestParam(value = "firstname", required = true) String firstName,
            @RequestParam(value = "lastname", required = true) String lastName,
            @RequestParam(value = "money", required = true) Double money);
    /**
     * Deletes an existing person by delegating the processing to PersonServiceImpl.
     * Displays a confirmation JSP page
     *
     * @return the name of the JSP page
     */

    public String delete(@RequestParam(value = "id", required = true) Integer id,
                         Model model);

    /**
     * Edits an existing person by delegating the processing to PersonServiceImpl.
     * Displays a confirmation JSP page
     *
     * @return the name of the JSP page
     */
    public String edit(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "firstname", required = true) String firstName,
            @RequestParam(value = "lastname", required = true) String lastName,
            @RequestParam(value = "money", required = true) Double money,
            Model model);
}
