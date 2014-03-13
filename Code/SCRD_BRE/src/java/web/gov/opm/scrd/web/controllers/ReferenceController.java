/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.web.controllers;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Reference;
import gov.opm.scrd.services.ReferenceService;
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
 * This class represents the controller used to manage references. There will be
 * ability for user to list references and retrieve single reference.
 * 
 * 
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
@Controller
public class ReferenceController extends BaseController {
    /**
     * Represents the report controller class name for logging.
     */
    private static final String CLASS_NAME = ReferenceController.class.getName();
    
    /**
     * Represents the ReferenceService instance used to manage references..
     * It is modified by setter.
     * It is injected by Spring.
     * It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.REFERENCE_SERVICE_JNDI)
    private ReferenceService referenceService;

    /**
     * This is the constructor of this class.
     */
    public ReferenceController() {
        super();
    }
    

    /**
     * Returns the reference from id.
     * @param referenceId the reference id.
     * @return the reference.
     * @throws OPMException if there are any exceptions
     */
    @RequestMapping(value="reference/{referenceId}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Reference get(@PathVariable Integer referenceId) throws OPMException {
        String signature = CLASS_NAME + "#get(Integer referenceId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"referenceId"},
            new Object[] {referenceId});
        
        Reference reference =  referenceService.get(referenceId);
        LoggingHelper.logExit(logger, signature, new Object[] {reference});
        return reference;
    }

    /**
     * Gets all available references.
     * @return the references.
     * @throws OPMException if there are any error.
     */
    @RequestMapping(value="reference/all", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Reference> getAll() throws OPMException {
        String signature = CLASS_NAME + "#get(Integer referenceId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {},
            new Object[] {});
        List<Reference> result = referenceService.getAll();
        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Creates a reference.
     * @param reference the reference to create.
     * @throws OPMException if there are any error.
     */
    @RequestMapping(value = "reference", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Reference create(@RequestBody Reference reference) throws OPMException {
        String signature = CLASS_NAME + "#create(Reference reference)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"reference"},
            new Object[] {reference});
        Reference result = referenceService.create(reference);
        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }
    
    /**
     * Deletes the reference.
     * @param referenceId the reference id.
     * @throws OPMException if there are any erros.
     */
    @RequestMapping(value = "reference/{referenceId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(@PathVariable long referenceId) throws OPMException {
        String signature = CLASS_NAME + "#delete(long referenceId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"referenceId"},
            new Object[] {referenceId});
        referenceService.delete(referenceId);
        LoggingHelper.logExit(logger, signature, null);
    }
    
    /**
     * Updates the reference.
     * @param reference the reference to update.
     * @throws OPMException if there are any error.
     */
    @RequestMapping(value = "reference", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Reference update(@RequestBody Reference reference) throws OPMException {
        String signature = CLASS_NAME + "#update(Reference reference)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"reference"},
            new Object[] {reference});
        referenceService.update(reference);
        LoggingHelper.logExit(logger, signature, new Object[] {reference});
        return reference;
        
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

