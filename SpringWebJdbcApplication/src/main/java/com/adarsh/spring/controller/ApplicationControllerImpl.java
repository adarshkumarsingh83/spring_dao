package com.adarsh.spring.controller;

import com.adarsh.spring.domain.Person;
import com.adarsh.spring.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Handles and retrieves person request
 */

@RequestMapping("/main")
@Scope(value = "singleton")
@Controller(value = "applicationControllerImpl")
public class ApplicationControllerImpl implements ApplicationController {

    protected static Logger logger = LoggerFactory.getLogger("controller");
    @Qualifier(value = "personServiceImpl")
    @Autowired(required = true)
    private PersonService personService;

    /**
     * Handles and retrieves all persons and show it in a JSP page
     *
     * @return the name of the JSP page
     */
    @Override
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String getPersons(Model model) {

        logger.debug("Received request to show all persons");

        // Retrieve all persons by delegating the call to PersonServiceImpl
        final List<Person> persons = this.personService.getAll();

        // Attach persons to the Model
        model.addAttribute("persons", persons);

        // This will resolve to /WEB-INF/jsp/personsPage.jsp
        return "personsPage";
    }

    /**
     * Adds a new person by delegating the processing to PersonServiceImpl.
     * Displays a confirmation JSP page
     *
     * @return the name of the JSP page
     */
    @Override
    @RequestMapping(value = "/persons/add", method = RequestMethod.GET)
    public String add(
            @RequestParam(value = "firstname", required = true) String firstName,
            @RequestParam(value = "lastname", required = true) String lastName,
            @RequestParam(value = "money", required = true) Double money) {

        logger.debug("Received request to add new person");

        // Call PersonServiceImpl to do the actual adding
        this.personService.add(firstName, lastName, money);

        // This will resolve to /WEB-INF/jsp/addedPage.jsp
        return "addedPage";
    }

    /**
     * Deletes an existing person by delegating the processing to PersonServiceImpl.
     * Displays a confirmation JSP page
     *
     * @return the name of the JSP page
     */
    @Override
    @RequestMapping(value = "/persons/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", required = true) Integer id,
                         Model model) {

        logger.debug("Received request to delete existing person");

        // Call PersonServiceImpl to do the actual deleting
        this.personService.delete(id);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/deletedPage.jsp
        return "deletedPage";
    }

    /**
     * Edits an existing person by delegating the processing to PersonServiceImpl.
     * Displays a confirmation JSP page
     *
     * @return the name of the JSP page
     */
    @Override
    @RequestMapping(value = "/persons/edit", method = RequestMethod.GET)
    public String edit(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "firstname", required = true) String firstName,
            @RequestParam(value = "lastname", required = true) String lastName,
            @RequestParam(value = "money", required = true) Double money,
            Model model) {

        logger.debug("Received request to edit existing person");

        // Call PersonServiceImpl to do the actual editing
        this.personService.edit(id, firstName, lastName, money);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/editedPage.jsp
        return "editedPage";
    }
}
