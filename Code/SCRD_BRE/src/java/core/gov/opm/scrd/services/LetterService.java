/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services;

import gov.opm.scrd.entities.application.Letter;

import java.util.List;

/**
 * <p>
 * This interface defines a contract for managing the letters in the application. It provides simple
 * create/update/delete/retrieval operations for the letters and exporting functionality.
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
public interface LetterService {
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
    public Letter create(Letter letter) throws OPMException;

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
    public void update(Letter letter) throws OPMException;

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
    public void delete(long letterId) throws OPMException;

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
    public Letter get(long letterId) throws OPMException;

    /**
     * Retrieves all letters.
     *
     * @return List of all letters, can not be null/contain null elements.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<Letter> getAll() throws OPMException;

    /**
     * Exports the letter for the specified id to specific export type.
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
    public byte[] export(long letterId, ExportType exportType) throws OPMException;
}
