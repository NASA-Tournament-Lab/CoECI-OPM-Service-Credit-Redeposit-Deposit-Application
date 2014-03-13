/*
    Copyright 2014 OPM.gov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package gov.opm.scrd.entities.common;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.services.EntityNotFoundException;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * Helper class for the component. It provides useful common methods for all the classes in this component.
 * </p>
 *
 * <p>
 * <em>Changes in OPM - Data Services - Account and Payment Services Assembly 1.0:</em>
 * <ol>
 * <li>Added new methods (except toString).</li>
 * </ol>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class has no state, and thus it is thread safe.
 * </p>
 *
 * @author sparemax, TCSASSEMBLER
 * @version 1.0
 */
public final class Helper {
    /**
     * <p>
     * Represents the string ' AND '.
     * </p>
     */
    public static final String AND = " AND ";

    /**
     * <p>
     * Represents the object mapper.
     * </p>
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * <p>
     * Represents the JPQL to query entity by id.
     * </p>
     */
    private static final String JPQL_QUERY_ENTITY_BY_ID = "SELECT e FROM %1$s e"
        + " WHERE e.deleted = false AND e.id = :id";

    /**
     * <p>
     * Represents the string '%'.
     * </p>
     */
    private static final String PERCENT_SIGN = "%";

    /**
     * <p>
     * Represents the string ' LIKE '.
     * </p>
     */
    private static final String LIKE = " LIKE ";

    /**
     * <p>
     * Prevents to create a new instance.
     * </p>
     */
    private Helper() {
        // empty
    }

    /**
     * Builds the "order by" string.
     *
     * @param filter
     *            the filter
     *
     * @return the "order by" string.
     */
    public static String buildOrderBy(BaseSearchParameters filter) {
        String sortColumn = filter.getSortColumn();
        SortOrder sortOrder = filter.getSortOrder();

        if (sortColumn == null) {
            sortColumn = "id";
        }
        if (sortOrder == null) {
            sortOrder = SortOrder.DESC;
        }

        return new StringBuilder().append(" ORDER BY e.").append(sortColumn).append(" ").append(sortOrder).toString();
    }

    /**
     * Sets the parameters.
     *
     * @param query
     *            the query
     * @param paramNames
     *            the parameter names
     * @param paramValues
     *            the parameter values
     *
     * @throws IllegalStateException
     *             if the entity manager has been closed.
     * @throws PersistenceException
     *             if any other error occurs.
     */
    public static void setParameters(Query query, List<String> paramNames, List<Object> paramValues) {
        int paramSize = paramNames.size();
        for (int i = 0; i < paramSize; i++) {
            query.setParameter(paramNames.get(i), paramValues.get(i));
        }
    }

    /**
     * Appends the condition string.
     *
     * @param sb
     *            the string builder
     * @param str
     *            the condition string.
     * @param value
     *            the value
     * @param name
     *            the name
     * @param paramNames
     *            the parameter name
     * @param paramValues
     *            the parameter values
     */
    public static void appendCondition(StringBuilder sb, String str, Object value, String name,
        List<String> paramNames, List<Object> paramValues) {
        if (value == null) {
            return;
        }

        sb.append(AND);

        sb.append(str);

        paramNames.add(name);

        paramValues.add(str.contains(LIKE) ? (PERCENT_SIGN + value + PERCENT_SIGN) : value);
    }

    /**
     * Retrieves entity by id.
     *
     * @param <T>
     *            the entity type.
     * @param entityManager
     *            the entity manager.
     * @param logger
     *            the logger object.
     * @param signature
     *            the signature of the method to log.
     * @param entityType
     *            the entity type.
     * @param id
     *            the id of entity to retrieve.
     * @param required
     *            true if the entity should exist.
     *
     * @return entity for the id or null if there is no such entity when required is false.
     *
     * @throws IllegalStateException
     *             if the entity manager has been closed.
     * @throws PersistenceException
     *             if there is any problem when executing the method.
     * @throws EntityNotFoundException
     *             if there is no such entity when required is true.
     */
    public static <T> T getEntityById(EntityManager entityManager, Logger logger, String signature,
        Class<T> entityType, long id, boolean required) throws EntityNotFoundException {
        String entityTypeName = entityType.getSimpleName();

        return getValue(entityManager, logger, signature, entityType,
            String.format(JPQL_QUERY_ENTITY_BY_ID, entityTypeName), new String[] {"id"}, new Object[] {id}, required);
    }

    /**
     * Retrieves value.
     *
     * @param <T>
     *            the value type.
     * @param entityManager
     *            the entity manager.
     * @param logger
     *            the logger object.
     * @param signature
     *            the signature of the method to log.
     * @param resultClass
     *            the result class.
     * @param qlString
     *            the query string.
     * @param paramNames
     *            the parameter names.
     * @param paramValues
     *            the parameter values.
     * @param required
     *            true if the value is required.
     *
     * @return the value or null if there is no result.
     *
     * @throws IllegalStateException
     *             if the entity manager has been closed.
     * @throws PersistenceException
     *             if there is any problem when executing the method.
     * @throws EntityNotFoundException
     *             if the value can't be found
     */
    public static <T> T getValue(EntityManager entityManager, Logger logger, String signature, Class<T> resultClass,
        String qlString, String[] paramNames, Object[] paramValues, boolean required) throws EntityNotFoundException {
        List<T> list = getValues(entityManager, logger, signature, resultClass, qlString, paramNames, paramValues);

        if (list.isEmpty()) {
            if (required) {
                throw LoggingHelper.logException(logger, signature, new EntityNotFoundException("There is no such "
                    + resultClass.getSimpleName() + "."));
            }

            return null;
        }

        return list.get(0);
    }

    /**
     * Retrieves values.
     *
     * @param <T>
     *            the value type.
     * @param entityManager
     *            the entity manager.
     * @param logger
     *            the logger object.
     * @param signature
     *            the signature of the method to log.
     * @param resultClass
     *            the result class.
     * @param qlString
     *            the query string.
     * @param paramNames
     *            the parameter names.
     * @param paramValues
     *            the parameter values.
     *
     * @return the values
     *
     * @throws IllegalStateException
     *             if the entity manager has been closed.
     * @throws PersistenceException
     *             if there is any problem when executing the method.
     */
    public static <T> List<T> getValues(EntityManager entityManager, Logger logger, String signature,
        Class<T> resultClass, String qlString, String[] paramNames, Object[] paramValues) {
        TypedQuery<T> query = entityManager.createQuery(qlString, resultClass);

        if (paramNames != null) {
            for (int i = 0; i < paramNames.length; i++) {
                query.setParameter(paramNames[i], paramValues[i]);
            }
        }

        return query.getResultList();
    }

    /**
     * <p>
     * Validates the value of a field.
     * </p>
     *
     * @param logger
     *            the logger object.
     * @param signature
     *            the signature of the method to be logged.
     * @param value
     *            the value
     * @param name
     *            the name
     *
     * @throws OPMException
     *             if value is <code>null</code>.
     */
    public static void checkFieldNull(Logger logger, String signature, Object value, String name) throws OPMException {
        if (value == null) {
            // Log exception
            throw LoggingHelper.logException(logger, signature, new OPMException("'" + name + "' should not be null."));
        }
    }

    /**
     * <p>
     * Validates the value of a variable. The value can not be <code>null</code>.
     * </p>
     *
     * @param logger
     *            the logger object.
     * @param signature
     *            the signature of the method to be logged.
     * @param value
     *            the value of the variable to be validated.
     * @param name
     *            the name of the variable to be validated.
     *
     * @throws IllegalArgumentException
     *             if the value of the variable is <code>null</code>.
     */
    public static void checkNull(Logger logger, String signature, Object value, String name) {
        if (value == null) {
            // Log exception
            throw LoggingHelper.logException(logger, signature, new IllegalArgumentException("'" + name
                + "' should not be null."));
        }
    }

    /**
     * <p>
     * Validates the value of a list.
     * </p>
     *
     * @param logger
     *            the logger object.
     * @param signature
     *            the signature of the method to be logged.
     * @param list
     *            the value of the variable to be validated.
     * @param name
     *            the name of the variable to be validated.
     *
     * @throws IllegalArgumentException
     *             if the value of the variable is <code>null</code> or contains null.
     */
    public static void checkList(Logger logger, String signature, List<?> list, String name) {
        checkNull(logger, signature, list, name);

        for (Object element : list) {
            if (element == null) {
                // Log exception
                throw LoggingHelper.logException(logger, signature, new IllegalArgumentException("'" + name
                    + "' should not contain null."));
            }
        }
    }

    /**
     * <p>
     * Validates the value of a string. The value can not be <code>null</code> or an empty string.
     * </p>
     *
     * @param logger
     *            the logger object.
     * @param signature
     *            the signature of the method to be logged.
     * @param value
     *            the value of the variable to be validated.
     * @param name
     *            the name of the variable to be validated.
     *
     * @throws IllegalArgumentException
     *             if the given string is <code>null</code> or an empty string.
     */
    public static void checkNullOrEmpty(Logger logger, String signature, String value, String name) {
        checkNull(logger, signature, value, name);

        if (value.trim().length() == 0) {
            // Log exception
            throw LoggingHelper.logException(logger, signature, new IllegalArgumentException("'" + name
                + "' should not be an empty string."));
        }
    }

    /**
     * <p>
     * Validates the value of a long.
     * </p>
     *
     * @param logger
     *            the logger object.
     * @param signature
     *            the signature of the method to be logged.
     * @param value
     *            the value of the variable to be validated.
     * @param name
     *            the name of the variable to be validated.
     *
     * @throws IllegalArgumentException
     *             if the given long value is not positive.
     */
    public static void checkPositive(Logger logger, String signature, long value, String name) {
        if (value <= 0) {
            // Log the exception
            throw LoggingHelper.logException(logger, signature, new IllegalArgumentException("'" + name
                + "' should be positive."));
        }
    }

    /**
     * Converts the object to string.
     *
     * @param obj
     *            the object
     *
     * @return the string representation of the object.
     */
    public static String toString(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    /**
     * <p>
     * Checks state of object.
     * </p>
     *
     * @param isInvalid
     *            the state of object.
     * @param message
     *            the error message.
     *
     * @throws OPMConfigurationException
     *             if isInvalid is <code>true</code>
     */
    public static void checkState(boolean isInvalid, String message) {
        if (isInvalid) {
            throw new OPMConfigurationException(message);
        }
    }
}
