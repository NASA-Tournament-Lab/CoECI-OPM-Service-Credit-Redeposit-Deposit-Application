/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Reference;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.EntityNotFoundException;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReferenceService;
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
 * This class is the implementation of the ReferenceService. It utilizes JPA EntityManager from BaseService for necessary
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
@Local(ReferenceService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ReferenceServiceImpl extends BaseService implements ReferenceService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = ReferenceServiceImpl.class.getName();

    /**
     * <p>
     * Represents the JPQL to query Reference id and name.
     * </p>
     */
    private static final String JPQL_QUERY_REFERENCE_ID_NAME = "SELECT e.id, e.name FROM Reference e"
        + " WHERE e.deleted = false";

    /**
     * Creates an instance of ReferenceServiceImpl.
     */
    public ReferenceServiceImpl() {
        // Empty
    }

    /**
     * Creates the reference.
     *
     * @param reference
     *            the reference to create.
     *
     * @return The created reference instance.
     *
     * @throws IllegalArgumentException
     *             if reference is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Reference create(Reference reference) throws OPMException {
        String signature = CLASS_NAME + "#create(Reference reference)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"reference"},
            new Object[] {reference});

        Helper.checkNull(logger, signature, reference, "reference");

        try {
            getEntityManager().persist(reference);

            LoggingHelper.logExit(logger, signature, new Object[] {reference});
            return reference;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * Updates the reference.
     *
     * @param reference
     *            the reference to update.
     *
     * @throws IllegalArgumentException
     *             if reference is null.
     * @throws EntityNotFoundException
     *             if there is no such reference to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Reference reference) throws OPMException {
        String signature = CLASS_NAME + "#update(Reference reference)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"reference"},
            new Object[] {reference});

        Helper.checkNull(logger, signature, reference, "reference");

        EntityManager entityManager = getEntityManager();
        try {
            // Check the Reference
            Helper.getEntityById(entityManager, logger, signature, Reference.class, reference.getId(), true);

            entityManager.merge(reference);

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
     * Deletes the reference by id.
     *
     * @param referenceId
     *            the id of reference to delete.
     *
     * @throws IllegalArgumentException
     *             if referenceId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such reference to delete.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(long referenceId) throws OPMException {
        String signature = CLASS_NAME + "#delete(long referenceId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"referenceId"},
            new Object[] {referenceId});

        Helper.checkPositive(logger, signature, referenceId, "referenceId");

        EntityManager entityManager = getEntityManager();
        try {
            // Get the Reference
            Reference reference = Helper.getEntityById(entityManager, logger, signature, Reference.class, referenceId, true);
            reference.setDeleted(true);

            entityManager.merge(reference);

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
     * Gets the reference by id.
     *
     * @param referenceId
     *            the id of reference to get.
     *
     * @return The reference for the id or null if it can not be found.
     *
     * @throws IllegalArgumentException
     *             if referenceId is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Reference get(long referenceId) throws OPMException {
        String signature = CLASS_NAME + "#get(long referenceId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"referenceId"},
            new Object[] {referenceId});

        Helper.checkPositive(logger, signature, referenceId, "referenceId");

        try {
            // Get reference
            Reference result = Helper.getEntityById(getEntityManager(), logger, signature, Reference.class, referenceId, false);

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
     * Retrieves all references.
     *
     * @return List of all references, can not be null/contain null elements.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @SuppressWarnings("unchecked")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Reference> getAll() throws OPMException {
        String signature = CLASS_NAME + "#getAll()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        try {
            // Get references
            List<Object[]> data = getEntityManager().createQuery(JPQL_QUERY_REFERENCE_ID_NAME)
                .getResultList();

            List<Reference> result = new ArrayList<Reference>();
            for (Object[] row : data) {
                Reference ltter = new Reference();

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
     * Exports the reference for the specified id to specific export type.
     * </p>
     *
     * <p>
     * <em>NOTE:</em> this method is not implemented.
     * </p>
     *
     * @param referenceId
     *            the id of reference to export
     * @param exportType
     *            the type of the report data to generate.
     *
     * @return The byte array of contents exported reference, can not be null.
     *
     * @throws IllegalArgumentException
     *             if referenceId is not positive or exportType is null
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public byte[] export(long referenceId, ExportType exportType) throws OPMException {
        return new byte[0];
    }
}
