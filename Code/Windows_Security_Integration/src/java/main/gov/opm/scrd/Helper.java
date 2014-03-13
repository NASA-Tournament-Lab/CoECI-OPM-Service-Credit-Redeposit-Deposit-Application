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

package gov.opm.scrd;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SubjectInfo;
import org.jboss.security.identity.Role;

/**
 * <p>
 * This is the helper class of this assembly.
 * </p>
 * <strong>Thread Safety: </strong>this class is immutable and thread-safe
 * 
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class Helper {
    
    /**
     * Represents the name of identifier.
     */
    public static final String IDENTIFIER_NAME = "identifier";

    /**
     * Represents the principal name for roles.
     */
    private static final String ROLES = "Roles";

    /**
     * Represents the entrance message.
     */
    private static final String MESSAGE_ENTRANCE = "Entering method %1$s.";

    /**
     * Represents the exit message.
     */
    private static final String MESSAGE_EXIT = "Exiting method %1$s.";

    /**
     * Represents the error message.
     */
    private static final String MESSAGE_ERROR = "Error in method %1$s. Details:";

    /**
     * Private empty constructor to prevent instantiating this class.
     */
    private Helper() {
        // empty
    }

    /**
     * Validates the value of a variable. The value can not be <code>null</code>
     * .
     * 
     * @param name
     *            the name of the variable to be validated.
     * @param value
     *            the value of the variable to be validated.
     * 
     * @throws IllegalArgumentException
     *             if the value of the variable is <code>null</code>.
     */
    public static void checkNull(String name, Object value) {
        if (value == null) {
            throw new IllegalArgumentException("'" + name
                    + "' should not be null.");
        }
    }

    /**
     * Validates the value of a variable. The value can not be <code>null</code>
     * or empty.
     * 
     * @param name
     *            the name of the variable to be validated.
     * @param value
     *            the value of the variable to be validated.
     * 
     * @throws IllegalArgumentException
     *             if the value of the variable is <code>null</code> or empty.
     */
    public static void checkNullOrEmpty(String name, String value) {
        checkNull(name, value);

        if (value.trim().length() == 0) {
            throw new IllegalArgumentException("'" + name
                    + "' should not be empty.");
        }
    }

    /**
     * Check the value of a variable is <code>null</code> or empty.
     * 
     * @param value
     *            the value of the variable to check
     * @return if string is null or empty return true.
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

    /**
     * Logs for entrance into public methods at <code>DEBUG</code> level.
     * 
     * @param logger
     *            the log service.
     * @param signature
     *            the signature.
     * @param paramNames
     *            the names of parameters to log (not Null).
     * @param params
     *            the values of parameters to log (not Null).
     */
    public static void logEntrance(Logger logger, String signature,
            String[] paramNames, Object[] params) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(MESSAGE_ENTRANCE, signature));
        // Parameters
        sb.append(toString(paramNames, params));
        // Do logging
        logger.log(Level.DEBUG, sb.toString());
    }

    /**
     * Logs for exit from public methods at <code>DEBUG</code> level.
     * 
     * @param logger
     *            the log service.
     * @param signature
     *            the signature of the method to be logged.
     * @param value
     *            the return value to log.
     */
    public static void logExit(Logger logger, String signature, Object[] value) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(MESSAGE_EXIT, signature));
        if (value != null) {
            // Return value
            sb.append(" Output parameter : " + toString(value[0]));
        }
        // Do logging
        logger.log(Level.DEBUG, sb.toString());
    }

    /**
     * Logs the given exception and message at <code>ERROR</code> level.
     * 
     * @param <T>
     *            the exception type.
     * @param logger
     *            the log service.
     * @param signature
     *            the signature of the method to log.
     * @param e
     *            the exception to log.
     * 
     * @return the passed in exception.
     */
    public static <T extends Throwable> T logException(Logger logger,
            String signature, T e) {
        logger.error(String.format(MESSAGE_ERROR, signature), e);
        return e;
    }

    /**
     * Get Roles from subject info.
     * 
     * @param info
     *            the subject info.
     * @return roles from subject info.
     */
    public static List<String> GetRoles(SubjectInfo info) {
        List<String> roles = new ArrayList<String>();
        if (info.getRoles() != null) {
            for (Role role : info.getRoles().getRoles()) {
                roles.add(role.getRoleName());
            }
        } else {
            if (info.getAuthenticatedSubject() != null
                    && info.getAuthenticatedSubject().getPrincipals() != null) {
                Iterator<Principal> it = info.getAuthenticatedSubject()
                        .getPrincipals().iterator();
                while (it.hasNext()) {
                    Principal principal = it.next();
                    if (ROLES.equals(principal.getName())) {
                        SimpleGroup simpleGroup = (SimpleGroup) principal;
                        Enumeration<Principal> members = simpleGroup.members();
                        while (members.hasMoreElements()) {
                            roles.add(members.nextElement().getName());
                        }
                        break;
                    }
                }
            }
        }
        return roles;
    }

    /**
     * Converts the parameters to string.
     * 
     * @param paramNames
     *            the names of parameters.
     * @param params
     *            the values of parameters.
     * 
     * @return the string
     */
    static String toString(String[] paramNames, Object[] params) {
        StringBuffer sb = new StringBuffer(" Input parameters: {");
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(paramNames[i]).append(":")
                        .append(toString(params[i]));
            }
        }
        sb.append("}.");

        return sb.toString();
    }

    /**
     * Converts the object to string.
     * 
     * @param obj
     *            the object
     * 
     * @return the string representation of the object.
     */
    static String toString(Object obj) {
        if (obj instanceof List<?>) {
            return toString((List<?>) obj);
        }
        return String.valueOf(obj);
    }

    /**
     * Converts the List to a string.
     * 
     * @param obj
     *            the List.
     * 
     * @return the string.
     */
    private static String toString(List<?> obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean first = true;
        for (Object element : obj) {
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }

            sb.append(toString(element));
        }
        sb.append("]");
        return sb.toString();
    }
}
