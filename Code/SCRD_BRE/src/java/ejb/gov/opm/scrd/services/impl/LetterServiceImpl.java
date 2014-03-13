/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Letter;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.EntityNotFoundException;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.LetterService;
import gov.opm.scrd.services.OPMException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;

/**
 * <p>
 * This class is the implementation of the LetterService. It utilizes JPA EntityManager from BaseService for necessary
 * operations. It also reuses logger from base class.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - SCRD - Reporting Initial Module Assembly 1.0
 */
@Stateless
@Local(LetterService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LetterServiceImpl extends BaseService implements LetterService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = LetterServiceImpl.class.getName();

    /**
     * <p>
     * Represents the JPQL to query Letter id and name.
     * </p>
     */
    private static final String JPQL_QUERY_LETTER_ID_NAME = "SELECT e.id, e.name FROM Letter e"
        + " WHERE e.deleted = false";

    /**
     * Creates an instance of LetterServiceImpl.
     */
    public LetterServiceImpl() {
        // Empty
    }

    /**
     * Creates the letter.
     *
     * @param letter
     *            the letter to create.
     *
     * @return The created letter instance.
     *
     * @throws IllegalArgumentException
     *             if letter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Letter create(Letter letter) throws OPMException {
        String signature = CLASS_NAME + "#create(Letter letter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"letter"},
            new Object[] {letter});

        Helper.checkNull(logger, signature, letter, "letter");

        try {
            getEntityManager().persist(letter);

            LoggingHelper.logExit(logger, signature, new Object[] {letter});
            return letter;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * Updates the letter.
     *
     * @param letter
     *            the letter to update.
     *
     * @throws IllegalArgumentException
     *             if letter is null.
     * @throws EntityNotFoundException
     *             if there is no such letter to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Letter letter) throws OPMException {
        String signature = CLASS_NAME + "#update(Letter letter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"letter"},
            new Object[] {letter});

        Helper.checkNull(logger, signature, letter, "letter");

        EntityManager entityManager = getEntityManager();
        try {
            // Check the Letter
            Helper.getEntityById(entityManager, logger, signature, Letter.class, letter.getId(), true);

            entityManager.merge(letter);

            LoggingHelper.logExit(logger, signature, null);
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * Deletes the letter by id.
     *
     * @param letterId
     *            the id of letter to delete.
     *
     * @throws IllegalArgumentException
     *             if letterId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such letter to delete.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(long letterId) throws OPMException {
        String signature = CLASS_NAME + "#delete(long letterId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"letterId"},
            new Object[] {letterId});

        Helper.checkPositive(logger, signature, letterId, "letterId");

        EntityManager entityManager = getEntityManager();
        try {
            // Get the Letter
            Letter letter = Helper.getEntityById(entityManager, logger, signature, Letter.class, letterId, true);
            letter.setDeleted(true);

            entityManager.merge(letter);

            LoggingHelper.logExit(logger, signature, null);
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * Gets the letter by id.
     *
     * @param letterId
     *            the id of letter to get.
     *
     * @return The letter for the id or null if it can not be found.
     *
     * @throws IllegalArgumentException
     *             if letterId is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Letter get(long letterId) throws OPMException {
        String signature = CLASS_NAME + "#get(long letterId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"letterId"},
            new Object[] {letterId});

        Helper.checkPositive(logger, signature, letterId, "letterId");

        try {
            // Get letter
            Letter result = Helper.getEntityById(getEntityManager(), logger, signature, Letter.class, letterId, false);

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * Retrieves all letters.
     *
     * @return List of all letters, can not be null/contain null elements.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @SuppressWarnings("unchecked")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Letter> getAll() throws OPMException {
        String signature = CLASS_NAME + "#getAll()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        try {
            // Get letters
            List<Object[]> data = getEntityManager().createQuery(JPQL_QUERY_LETTER_ID_NAME)
                .getResultList();

            List<Letter> result = new ArrayList<Letter>();
            for (Object[] row : data) {
                Letter ltter = new Letter();

                ltter.setId((Long) row[0]);
                ltter.setName((String) row[1]);

                result.add(ltter);
            }

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * <p>
     * Exports the letter for the specified id to specific export type.
     * </p>
     *
     * <p>
     * <em>NOTE:</em> this method is not implemented.
     * </p>
     *
     * @param letterId
     *            the id of letter to export
     * @param exportType
     *            the type of the report data to generate.
     *
     * @return The byte array of contents exported letter, can not be null.
     *
     * @throws IllegalArgumentException
     *             if letterId is not positive or exportType is null
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public byte[] export(long letterId, ExportType exportType) throws OPMException {
        return new byte[0];
    }
}
