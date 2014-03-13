/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.web.controllers;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Letter;
import gov.opm.scrd.services.LetterService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * This class represents the controller used to manage letters. There will be
 * ability for user to list letters and retrieve single letter.
 * 
 * 
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
@Controller
public class LetterController extends BaseController {
    /**
     * Represents the report controller class name for logging.
     */
    private static final String CLASS_NAME = LetterController.class.getName();
    
    /**
     * Represents the LetterService instance used to manage letters..
     * It is modified by setter.
     * It is injected by Spring.
     * It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.LETTER_SERVICE_JNDI)
    private LetterService letterService;

    /**
     * This is the constructor of this class.
     */
    public LetterController() {
        super();
    }
    

    /**
     * Returns the letter from id.
     * @param letterId the letter id.
     * @return the letter.
     * @throws OPMException if there are any exceptions
     */
    @RequestMapping(value="letter/{letterId}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Letter get(@PathVariable Integer letterId) throws OPMException {
        String signature = CLASS_NAME + "#get(Integer letterId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"letterId"},
            new Object[] {letterId});
        
        Letter letter =  letterService.get(letterId);
        LoggingHelper.logExit(logger, signature, new Object[] {letter});
        return letter;
    }

    /**
     * Gets all available letters.
     * @return the letters.
     * @throws OPMException if there are any error.
     */
    @RequestMapping(value="letter/all", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Letter> getAll() throws OPMException {
        String signature = CLASS_NAME + "#get(Integer letterId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {},
            new Object[] {});
        List<Letter> result = letterService.getAll();
        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Creates a letter.
     * @param letter the letter to create.
     * @throws OPMException if there are any error.
     */
    @RequestMapping(value = "letter", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Letter create(@RequestBody Letter letter) throws OPMException {
        String signature = CLASS_NAME + "#create(Letter letter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"letter"},
            new Object[] {letter});
        Letter result = letterService.create(letter);
        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }
    
    /**
     * Deletes the letter.
     * @param letterId the letter id.
     * @throws OPMException if there are any erros.
     */
    @RequestMapping(value = "letter/{letterId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(@PathVariable long letterId) throws OPMException {
        String signature = CLASS_NAME + "#delete(long letterId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"letterId"},
            new Object[] {letterId});
        letterService.delete(letterId);
        LoggingHelper.logExit(logger, signature, null);
    }
    
    /**
     * Updates the letter.
     * @param letter the letter to update.
     * @throws OPMException if there are any error.
     */
    @RequestMapping(value = "letter", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Letter update(@RequestBody Letter letter) throws OPMException {
        String signature = CLASS_NAME + "#update(Letter letter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"letter"},
            new Object[] {letter});
        letterService.update(letter);
        LoggingHelper.logExit(logger, signature, new Object[] {letter});
        return letter;
        
    }
    

    /**
     * Checks if the bean is successfully injected.
     * 
     * @throws OPMConfigurationException if the injection is not correct.
     */
    @PostConstruct
    @Override
    protected void checkInit() {
        super.checkInit();
    }
}

