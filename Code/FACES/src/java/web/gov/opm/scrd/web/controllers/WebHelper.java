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

package gov.opm.scrd.web.controllers;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.AuditParameterRecord;
import gov.opm.scrd.entities.application.AuditRecord;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.common.IdentifiableEntity;
import gov.opm.scrd.entities.common.NamedEntity;
import gov.opm.scrd.services.AuditService;
import gov.opm.scrd.services.OPMException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;

/**
 * <p>
 * Helper class for the web components.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Add the JNDI binding for several services EJB.</li>
 * </ul>
 * </p>
 * <p>
 * <em>Changes in 1.2 (OPM - SCRD - Frontend Account Module Assembly 1.0):</em>
 * <ul>
 * <li>Add the JNDI binding for CalculationExecutionService EJB.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class has no state, and thus it is thread safe.
 * </p>
 *
 * @author sparemax, liuliquan, TCSASSEMBLER
 * @version 1.2
 * @since OPM - SCRD - Frontend Initial Module Assembly 1.0
 */
public final class WebHelper {

    /**
     * JNDI binding for the security service.
     */
    public static final String SECURITY_SERVICE_JNDI = "java:app/opm-faces-ejb/SecurityServiceImpl!"
            + "gov.opm.scrd.services.impl.SecurityServiceImpl";
    /**
     * JNDI binding for the user service.
     */
    public static final String USER_SERVICE_JNDI = "java:app/opm-faces-ejb/UserServiceImpl!"
            + "gov.opm.scrd.services.impl.UserServiceImpl";
    /**
     * JNDI binding for the lookup service.
     */
    static final String LOOKUP_SERVICE_JNDI = "java:app/opm-faces-ejb/LookupServiceImpl!"
            + "gov.opm.scrd.services.impl.LookupServiceImpl";
    /**
     * JNDI binding for the account service.
     */
    static final String ACCOUNT_SERVICE_JNDI = "java:app/opm-faces-ejb/AccountServiceImpl!"
            + "gov.opm.scrd.services.impl.AccountServiceImpl";
    /**
     * JNDI binding for the suspension service.
     */
    static final String SUSPENSION_SERVICE_JNDI = "java:app/opm-faces-ejb/SuspensionServiceImpl!"
            + "gov.opm.scrd.services.impl.SuspensionServiceImpl";
    /**
     * JNDI binding for the approval service.
     */
    static final String APPROVAL_SERVICE_JNDI = "java:app/opm-faces-ejb/ApprovalServiceImpl!"
            + "gov.opm.scrd.services.impl.ApprovalServiceImpl";
    /**
     * JNDI binding for the service announcement service.
     */
    static final String SERVICE_ANNOUNCEMENT_SERVICE_JNDI = "java:app/opm-faces-ejb/ServiceAnnouncementServiceImpl!"
            + "gov.opm.scrd.services.impl.ServiceAnnouncementServiceImpl";
    /**
     * JNDI binding for the audit service.
     */
    static final String AUDIT_SERVICE_JNDI = "java:app/opm-faces-ejb/AuditServiceImpl!"
            + "gov.opm.scrd.services.impl.AuditServiceImpl";
    /**
     * JNDI binding for the calculation execution service.
     * @since OPM - Frontend - Account Module Assembly
     */
    static final String CALCULATION_EXECUTION_SERVICE_JNDI = "java:app/opm-faces-ejb/CalculationExecutionServiceImpl!"
            + "gov.opm.scrd.services.impl.CalculationExecutionServiceImpl";
    /**
     * JNDI binding for the payment service.
     *
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    static final String PAYMENT_SERVICE_JNDI = "java:app/opm-faces-ejb/PaymentServiceImpl!"
            + "gov.opm.scrd.services.impl.PaymentServiceImpl";
    /**
     * JNDI binding for the batch print out archive service.
     *
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    static final String BATCH_PRINT_OUT_ARCHIVE_SERVICE_JNDI = "java:app/opm-faces-ejb/BatchPrintoutArchiveServiceImpl!"
            + "gov.opm.scrd.services.impl.BatchPrintoutArchiveServiceImpl";
    /**
     * JNDI binding for the service credit preference service.
     *
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    static final String SERVICE_CREDIT_PREFERENCE_SERVICE_JNDI = "java:app/opm-faces-ejb/ServiceCreditPreferenceServiceImpl!"
            + "gov.opm.scrd.services.impl.ServiceCreditPreferenceServiceImpl";
    /**
     * JNDI binding for the report generation data service.
     *
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    static final String REPORT_GENERATION_DATA_SERVICE_JNDI = "java:app/opm-faces-ejb/ReportGenerationDataServiceImpl!"
            + "gov.opm.scrd.services.impl.ReportGenerationDataServiceImpl";
    /**
     * JNDI binding for the help service.
     *
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    static final String HELP_SERVICE_JNDI = "java:app/opm-faces-ejb/HelpServiceImpl!"
            + "gov.opm.scrd.services.impl.HelpServiceImpl";

    /**
     * <p>
     * Prevents to create a new instance.
     * </p>
     */
    private WebHelper() {
        // empty
    }

    /**
     * Checks the user request.
     *
     * @param logger the logger object.
     * @param signature the signature of the method to be logged.
     * @param invalidStates the states of user request.
     * @param messages the messages
     * @param errorMessage the error message
     *
     * @throws ValidationException if any error occurs
     */
    public static void checkRequest(Logger logger, String signature, boolean[] invalidStates, String[] messages,
            String errorMessage) throws ValidationException {
        List<String> details = new ArrayList<String>();
        for (int i = 0; i < invalidStates.length; i++) {
            if (invalidStates[i]) {
                details.add(messages[i]);
            }
        }
        if (!details.isEmpty()) {
            throw LoggingHelper.logException(logger, signature, new ValidationException(errorMessage, details));
        }
    }

    /**
     * Stores the audit record.
     *
     * @param request the http servlet request
     * @param auditService the audit service
     * @param currentUser the current user
     * @param actionName the action name
     * @param values the values of audit parameter records
     *
     * @throws OPMException if any error occurs
     */
    public static void audit(HttpServletRequest request, AuditService auditService, User currentUser,
            String actionName, Object[]... values) throws OPMException {
        AuditRecord auditRecord = new AuditRecord();
        auditRecord.setUsername(currentUser.getUsername());
        auditRecord.setIpAddress(request.getRemoteAddr());
        auditRecord.setActionName(actionName);
        auditRecord.setDate(new Date());

        List<AuditParameterRecord> parameters = new ArrayList<AuditParameterRecord>();
        auditRecord.setParameters(parameters);

        for (int i = 0; i < values.length; i++) {
            Object[] recordValues = values[i];

            int index = 0;
            AuditParameterRecord parameterRecord = new AuditParameterRecord();
            parameterRecord.setItemId((Long) recordValues[index++]);
            parameterRecord.setItemType((String) recordValues[index++]);
            parameterRecord.setPropertyName((String) recordValues[index++]);
            parameterRecord.setPreviousValue(getString(recordValues[index++]));
            parameterRecord.setNewValue(getString(recordValues[index]));

            parameters.add(parameterRecord);
        }

        auditService.audit(auditRecord);
    }
    
    /**
     * Audit the entity
     * 
     * @param request
     *            the http servlet request
     * @param auditService
     *            the audit service
     * @param user
     *            the current user
     * @param action
     *            the action name
     * @param newEntity
     *            the new entity to audit
     * @param oldEntity
     *            the old entity to audit
     * 
     * @throws OPMException
     *             if any error occurs
     */
    public static void auditEntity(HttpServletRequest request, AuditService auditService, User user, String action,
            IdentifiableEntity oldEntity, IdentifiableEntity newEntity) throws OPMException {
        List<Object[]> objs = new ArrayList<Object[]>();
        List<String> fields = getFields(newEntity);
        for (String field : fields) {
            Object oldValue = getField(oldEntity, field);
            if (oldValue == null) {
                oldValue = "null";
            }
            Object newValue = getField(newEntity, field);
            if (newValue == null) {
                newValue = "null";
            }
            boolean equal = oldValue.equals(newValue);
            if (oldValue.toString().length() > 128) {
                oldValue = oldValue.toString().substring(0, 128);
            }
            if (newValue.toString().length() > 128) {
                newValue = newValue.toString().substring(0, 128);
            }
            if (!equal) {
                Object[] ab =
                        new Object[] { newEntity.getId(), newEntity.getClass().getName(), field, oldValue, newValue };
                objs.add(ab);
            }
        }

        WebHelper.audit(request, auditService, user, action, objs);
    }
    
    
    /**
     * Stores the audit record.
     * 
     * @param request
     *            the http servlet request
     * @param auditService
     *            the audit service
     * @param currentUser
     *            the current user
     * @param actionName
     *            the action name
     * @param values
     *            the values of audit parameter records
     * 
     * @throws OPMException
     *             if any error occurs
     */
    public static void audit(HttpServletRequest request, AuditService auditService, User currentUser,
            String actionName, List<Object[]> values) throws OPMException {
        AuditRecord auditRecord = new AuditRecord();
        auditRecord.setUsername(currentUser.getUsername());
        auditRecord.setIpAddress(request.getRemoteAddr());
        auditRecord.setActionName(actionName);
        auditRecord.setDate(new Date());

        List<AuditParameterRecord> parameters = new ArrayList<AuditParameterRecord>();
        auditRecord.setParameters(parameters);

        for (int i = 0; i < values.size(); i++) {
            Object[] recordValues = values.get(i);

            int index = 0;
            AuditParameterRecord parameterRecord = new AuditParameterRecord();
            parameterRecord.setItemId(Long.parseLong(recordValues[index++].toString()));
            parameterRecord.setItemType((String) recordValues[index++]);
            parameterRecord.setPropertyName((String) recordValues[index++]);
            parameterRecord.setPreviousValue(getString(recordValues[index++]));
            parameterRecord.setNewValue(getString(recordValues[index]));

            parameters.add(parameterRecord);
        }

        auditService.audit(auditRecord);
    }

    /**
     * Get all the private fields of the obj.
     * 
     * @param obj
     *            to get the fields.
     * @return a list of fields.
     */
    public static List<String> getFields(Object obj) {
        List<String> fields = new ArrayList<String>();
        Field[] fs = obj.getClass().getDeclaredFields();
        for (Field f : fs) {
            String className = f.getClass().getName();
            if (className.startsWith("java.lang") || className.startsWith("java.util.Date")) {
                fields.add(f.getName());
            }

        }
        return fields;
    }

    /**
     * <p>
     * Gets value for field of given object.
     * </p>
     * 
     * @param obj
     *            the given object.
     * @param field
     *            the field name.
     * 
     * @return the field value.
     */
    public static Object getField(Object obj, String field) {
        Object value = null;
        try {
            Field declaredField = null;
            try {
                declaredField = obj.getClass().getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                // Ignore
            }

            try {
                if (declaredField == null) {
                    declaredField = obj.getClass().getSuperclass().getDeclaredField(field);
                }
            } catch (NoSuchFieldException e) {
                // Ignore
            }

            if (declaredField == null) {
                declaredField = obj.getClass().getSuperclass().getSuperclass().getDeclaredField(field);
            }

            declaredField.setAccessible(true);

            try {
                value = declaredField.get(obj);
            } finally {
                declaredField.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            // Ignore
        } catch (NoSuchFieldException e) {
            // Ignore
        }

        return value;
    }
    
    

    /**
     * Converts the value to string.
     *
     * @param value the value
     *
     * @return null or string
     */
    private static String getString(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof NamedEntity) {
            return ((NamedEntity) value).getName();
        }
        if (value instanceof IdentifiableEntity) {
            return ((IdentifiableEntity) value).getId() + "";
        }
        return value.toString();
    }

    /**
     * <p>
     * Validates the value of a string.
     * </p>
     *
     * @param value the value of the variable to be validated.
     *
     * @return <code>true</code> if value is <code>null</code> or empty
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }
}
