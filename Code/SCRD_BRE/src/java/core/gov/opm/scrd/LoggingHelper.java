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

import gov.opm.scrd.entities.application.InterestCalculationRequest;
import gov.opm.scrd.entities.application.InterestCalculationResponse;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * This is the helper class of this assembly.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Services - Account and Payment Services Assembly 1.0):</em>
 * <ol>
 * <li>Made logger accept null.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - SCRD - Frontend Initial Module Assembly 1.0):</em>
 * <ol>
 * <li>Added handling for HttpServletRequest, HttpSession and ModelAndView in toString(Object).</li>
 * </ol>
 * </p>
 *
 * <strong>Thread Safety: </strong> this class is immutable and thread-safe
 *
 * @author j3_guile, sparemax
 * @version 1.2
 * @since OPM - Initial System Assembly
 */
public class LoggingHelper {

    /**
     * Maximum log details text length. (See SDS 7.7)
     */
    private static final int MAX_ARGUMENTS = 4066;

    /**
     * Maximum log exception text length. (See SDS 7.7)
     */
    private static final int MAX_EXCEPTION = 1024;

    /**
     * Maximum log method text length. (See SDS 7.7)
     */
    private static final int MAX_SIGNATURE = 100;

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
    private LoggingHelper() {
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
    public static void logEntrance(Logger logger, String signature, String[] paramNames, Object[] params) {
        if (logger == null) {
            // No logging
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(truncate(String.format(MESSAGE_ENTRANCE, signature), MAX_SIGNATURE));
        sb.append(truncate(toString(paramNames, params, MAX_ARGUMENTS), MAX_ARGUMENTS));
        logger.debug(sb.toString());
    }

    /**
     * Trims the message to a more manageable length.
     *
     * @param message
     *            the message to trim
     * @param max
     *            the threshold for the message length
     * @return the trimmed message only if it exceeds the threshold, otherwise, the same as the input
     */
    private static String truncate(String message, int max) {
        if (message.length() > max) {
            return message.substring(0, max);
        }
        return message;
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
        if (logger == null) {
            // No logging
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(truncate(String.format(MESSAGE_EXIT, signature), MAX_SIGNATURE));
        if (value != null) {
            sb.append(truncate(" Output parameter : " + toString(value[0]), MAX_ARGUMENTS));
        }
        logger.debug(sb.toString());
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
    public static <T extends Throwable> T logException(Logger logger, String signature, T e) {
        if (logger != null) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.error(truncate(String.format(MESSAGE_ERROR, signature + " " + sw.toString()), MAX_EXCEPTION));
        }
        return e;
    }

    /**
     * Converts the parameters to string.
     *
     * @param paramNames
     *            the names of parameters.
     * @param params
     *            the values of parameters.
     * @param max
     *            the maximum length of parameter logging allowed
     *
     * @return the string
     */
    static String toString(String[] paramNames, Object[] params, int max) {
        StringBuffer sb = new StringBuffer(" Input parameters: {");
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (sb.length() >= max) {
                    break;
                }
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(paramNames[i]).append(":").append(toString(params[i]));
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
    @SuppressWarnings("unchecked")
    static String toString(Object obj) {
        if (obj instanceof InterestCalculationResponse) {
            InterestCalculationResponse response = (InterestCalculationResponse) obj;
            StringBuffer str = new StringBuffer("{");
            str.append("extendedServicePeriods:" + toString(response.getExtendedServicePeriods()));
            str.append("}");
            return str.toString();
        } else if (obj instanceof InterestCalculationRequest) {
            InterestCalculationRequest request = (InterestCalculationRequest) obj;
            StringBuffer str = new StringBuffer("{");
            str.append("extendedServicePeriods:" + toString(request.getExtendedServicePeriods()) + ",");
            str.append("interestCalculatedToDate:" + toString(request.getInterestCalculatedToDate()));
            str.append("}");
            return str.toString();
        } else if (obj instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) obj;
            StringBuffer str = new StringBuffer("{request parameters: {");
            Enumeration<String> parameterNames = request.getParameterNames();
            boolean first = true;
            while (parameterNames.hasMoreElements()) {
                if (!first) {
                    str.append(", ");
                }
                first = false;
                String parameterName = parameterNames.nextElement();
                str.append(parameterName).append("=").append(Arrays.asList(request.getParameterValues(parameterName)));
            }
            str.append("}}");
            return str.toString();
        } else if (obj instanceof HttpSession) {
            HttpSession session = (HttpSession) obj;
            StringBuffer str = new StringBuffer("{session attributes: {");
            Enumeration<String> attributeNames = session.getAttributeNames();
            boolean first = true;
            while (attributeNames.hasMoreElements()) {
                if (!first) {
                    str.append(", ");
                }
                first = false;
                String parameterName = attributeNames.nextElement();
                str.append(parameterName).append("=").append(session.getAttribute(parameterName));
            }
            str.append("}}");
            return str.toString();
        } else if (obj instanceof ModelAndView) {
            ModelAndView modelAndView = (ModelAndView) obj;
            return new StringBuffer("{view name:").append(modelAndView.getViewName()).append("}").toString();
        } else if (obj instanceof List<?>) {
            StringBuffer str = new StringBuffer("[");
            for (Object item : (List<?>) obj) {
                if (!str.toString().equals("[")) {
                    str.append(",");
                }
                str.append(toString(item));
            }
            str.append("]");
            return str.toString();
        } else {
            return String.valueOf(obj);
        }
    }

}
