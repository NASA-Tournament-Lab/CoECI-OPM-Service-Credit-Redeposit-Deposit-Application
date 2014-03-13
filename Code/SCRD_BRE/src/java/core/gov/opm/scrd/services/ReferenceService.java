/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services;

import gov.opm.scrd.entities.application.Reference;

import java.util.List;

/**
 * <p>
 * This interface defines a contract for managing the references in the application. It provides simple
 * create/update/delete/retrieval operations for the references and exporting functionality.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> The implementations are required to be effectively thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - SCRD - Reporting Initial Module Assembly 1.0
 */
public interface ReferenceService {
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
    public Reference create(Reference reference) throws OPMException;

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
    public void update(Reference reference) throws OPMException;

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
    public void delete(long referenceId) throws OPMException;

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
    public Reference get(long referenceId) throws OPMException;

    /**
     * Retrieves all references.
     *
     * @return List of all references, can not be null/contain null elements.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<Reference> getAll() throws OPMException;

    /**
     * Exports the reference for the specified id to specific export type.
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
    public byte[] export(long referenceId, ExportType exportType) throws OPMException;
}
