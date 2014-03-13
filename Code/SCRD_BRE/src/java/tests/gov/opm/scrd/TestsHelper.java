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

import gov.opm.scrd.entities.application.ExtendedServicePeriod;
import gov.opm.scrd.entities.application.ReportRequest;
import gov.opm.scrd.entities.application.ReportResponse;
import gov.opm.scrd.entities.application.ServicePeriod;
import gov.opm.scrd.entities.lookup.AppointmentType;
import gov.opm.scrd.entities.lookup.PayType;
import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.entities.lookup.RetirementType;
import gov.opm.scrd.entities.lookup.ServiceType;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportResponse;
import gov.opm.scrd.services.impl.BaseReportService;
import gov.opm.scrd.services.impl.BaseService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;

import junit.framework.Assert;

/**
 * <p>
 * The base class for unit tests.
 * </p>
 *
 * <p>
 * <em>Changes in OPM - Data Services - Account and Payment Services Assembly 1.0:</em>
 * <ol>
 * <li>Added EMPTY_STRING, DATE_FORMAT constants and setField method.</li>
 * </ol>
 * </p>
 * 
 * <p>
 * <em>Changes in OPM -  Rules Engine Integrate with Web APP</em>
 * <ol>
 *   <li> Added methods for deduction scenarios testing 
 *   from (Version 1.1 - OPM Rules Engine - Scenarios Conversion 2 - Deduction Update Assembly)</li>
 * </ol>
 * </p>
 *
 * @author sparemax, TCSASSEMBLER
 * @version 1.0
 */
public class TestsHelper {
    /**
     * <p>
     * Represents the empty string.
     * </p>
     */
    public static final String EMPTY_STRING = " \t ";

    /**
     * The date format 'MM/dd/yyyy'.
     */
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Represents the status name for active account.
     */
    public static final String ACTIVE_ACCOUNT_STATUS_NAME = "Active";

    /**
     * Represents the status name for history account.
     */
    public static final String HISTORY_ACCOUNT_STATUS_NAME = "History";

    /**
     * Represents the status name for closed account.
     */
    public static final String CLOSED_ACCOUNT_STATUS_NAME = "Closed";

    /**
     * Represents the name for CSRS retirement type.
     */
    public static final String CSRS_RETIREMENT_TYPE_NAME = "CSRS";

    /**
     * Represents the name for FERS retirement type.
     */
    public static final String FERS_RETIREMENT_TYPE_NAME = "FERS";

    /**
     * Represents the status name for new account.
     */
    public static final String NEW_ACCOUNT_STATUS_NAME = "New";


    /**
     * Creates an instance of TestsHelper.
     */
    private TestsHelper() {
        // Empty
    }

    /**
     * Gets the total interests of a extended service period after the calculation.
     * 
     * @param extendedServicePeriod the extended service period.
     * 
     * @return the total interests.
     */
    public static double getTotalInterest(ExtendedServicePeriod extendedServicePeriod) {
        return extendedServicePeriod.getBalanceWithInterest().subtract(
                extendedServicePeriod.getTotalDeduction()).setScale(
                        2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * Converts the BigDecimal to double, round up to 0.01.
     * @param decimal the big decimal.
     * @return the double value.
     */
    public static double toDouble(BigDecimal decimal) {
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * Parses the service period from this format:
     * beginDate, endDate, retirementType, periodType, appointmentType, serviceType, amount, payType
     * @param periodString the period string to parse.
     * @return the parsed service period instance.
     * 
     * @throws Exception if there are any error
     */
    public static ServicePeriod parseServicePeriod(String periodString) throws Exception {
        ServicePeriod period = new ServicePeriod();
        String[] items = periodString.split(",");
        int index = 0;
        // create the date formatter
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date beginDate = df.parse(items[index++]);
        Date endDate = df.parse(items[index++]);
        RetirementType retirementType = new RetirementType();
        retirementType.setName(items[index++]);
        PeriodType periodType = new PeriodType();
        periodType.setName(items[index++]);
        AppointmentType appointmentType = new AppointmentType();
        appointmentType.setName(items[index++]);
        ServiceType serviceType = new ServiceType();
        serviceType.setName(items[index++]);
        BigDecimal amount = new BigDecimal(items[index++]);
        PayType payType = new PayType();
        payType.setName(items[index++]);
        Date interestAccrualDate = null;
        if (!"null".equals(items[index])) {
            interestAccrualDate = df.parse(items[index]);
        }
        boolean connerCase = Boolean.parseBoolean(items[++index]);

        period.setAmount(amount);
        period.setAppointmentType(appointmentType);
        period.setBeginDate(beginDate);
        period.setEndDate(endDate);
        period.setPayType(payType);
        period.setPeriodType(periodType);
        period.setRetirementType(retirementType);
        period.setServiceType(serviceType);
        period.setInterestAccrualDate(interestAccrualDate);
        period.setConnerCase(connerCase);
        period.setValidationErrors(new HashMap<String,String>());

        return period;
    }

    /**
     * Parses the extended service period from this format:
     * beginDate(dd/mm/yyyy),endDate(dd/mm/yyyy),retirementType,periodType,totalDeduciton,totalEarnings,
     * interestAccuralDate(dd/mm/yyyy),connerCase
     * @param periodString the string to parse
     * @return the extended service period instance.
     * @throws Exception if any error occurs
     */
    public static ExtendedServicePeriod parseExtendedServicePeriod(String periodString) throws Exception {
        ExtendedServicePeriod esp = new ExtendedServicePeriod();
        String[] items = periodString.split(",");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        int index = 0;
        Date beginDate = df.parse(items[index++]);
        Date endDate = df.parse(items[index++]);
        RetirementType retirementType = new RetirementType();
        retirementType.setName(items[index++]);
        PeriodType periodType = new PeriodType();
        periodType.setName(items[index++]);
        BigDecimal totalDeduction = new BigDecimal(items[index++]);
        BigDecimal totalEarnings = new BigDecimal(items[index++]);
        Date interestAccrualDate = null;
        if (!"null".equals(items[index])) {
            interestAccrualDate = df.parse(items[index]);
        }
        index++;
        boolean connerCase = Boolean.parseBoolean(items[index++]);
        esp.setBeginDate(beginDate);
        esp.setEndDate(endDate);
        esp.setRetirementType(retirementType);
        esp.setPeriodType(periodType);
        esp.setTotalDeduction(totalDeduction);
        esp.setTotalEarnings(totalEarnings);
        esp.setInterestAccrualDate(interestAccrualDate);
        esp.setConnerCase(connerCase);
        esp.setValidationErrors(new ArrayList<String>());
        return esp;
    }

    /**
     * Asserts two lists of ExtendedServicePeriods are the same.
     * @param expected the expected ExtendedServicePeriods
     * @param actual the actual ExtendedServicePeriods
     */
    public static void assertEqualsExtendedServicePeriodList(List<ExtendedServicePeriod> expected,
            List<ExtendedServicePeriod> actual) {
        Assert.assertEquals("The number of Extended Service Periods is not correct.", expected.size(), actual.size());
        // Sort two lists first
        Comparator<ExtendedServicePeriod> comparator = new ExtendedServicePeriodComparator();
        Collections.sort(expected, comparator);
        Collections.sort(actual, comparator);
        // Assert for each ExtendedServicePeriod
        for (int i = 0; i < expected.size(); i++) {
            assertEqualsExtendedServicePeriod(expected.get(i), actual.get(i));
        }
    }

    /**
     * Asserts if two extended service periods are the same.
     * Check for beginDate, endDate, retirementType, periodType, totalDeduction,
     * totalEarnings and interestAccrualDate.
     * 
     * @param esp1
     *            the first extended service period.
     * @param esp2
     *            the second extended service period.
     */
    public static void assertEqualsExtendedServicePeriod(ExtendedServicePeriod esp1, ExtendedServicePeriod esp2) {
        Assert.assertEquals("Begin Dates are not equal.", esp1.getBeginDate(), esp2.getBeginDate());
        Assert.assertEquals("End Dates are not equal.", esp1.getEndDate(), esp2.getEndDate());
        Assert.assertEquals("Retirement types are not equal.", esp1.getRetirementType().getName(), esp2
                .getRetirementType().getName());
        Assert.assertEquals("Period types are not equal.", esp1.getPeriodType().getName(), esp2
                .getPeriodType().getName());
        Assert.assertEquals("Total Deductions are not equal.", toDouble(esp1.getTotalDeduction()),
                toDouble(esp2.getTotalDeduction()));
        Assert.assertEquals("Total Earnings are not equal.", toDouble(esp1.getTotalEarnings()),
                toDouble(esp2.getTotalEarnings()));
        Assert.assertEquals("Number of validation errors is not correct.", 0, esp2.getValidationErrors().size());
        if (esp1.getInterestAccrualDate() == null) {
            Assert.assertNull("Interest Accural Dates are not equal.", esp2.getInterestAccrualDate());
        } else {
            Assert.assertEquals("Interest Accural Dates are not equal.",
                    esp1.getInterestAccrualDate(), esp2.getInterestAccrualDate());
        }
        Assert.assertEquals("Conner cases are not equal.", esp1.isConnerCase(), esp2.isConnerCase());
        // Check service type only if the esp1's service type starts with "PEACE CORPS"
        if (esp1.getServiceType() != null && esp1.getServiceType().getName().startsWith("PEACE CORPS")) {
            Assert.assertEquals("Service Types are not equal.",
                    esp1.getServiceType().getName(), esp2.getServiceType().getName());
        }
    }

    /**
     * Reads the service periods from file.
     * @param fileName the name of the file.
     * @return the list of the service periods.
     * 
     * @throws Exception if any error occurs.
     */
    public static List<ServicePeriod> readServicePeriodsFromFile(String fileName) throws Exception {
        InputStream is = null;
        Properties properties = null;
        try {
            is = new FileInputStream(new File(fileName));
            properties = new Properties();
            properties.load(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }

        List<ServicePeriod> periods = new ArrayList<ServicePeriod>();
        for (int i = 1;; i++) {
            String str = properties.getProperty("ServicePeriod" + i);
            if (str == null) {
                break;
            }
            periods.add(parseServicePeriod(str));
        }
        return periods;
    }

    /**
     * Helper method to create service type.
     * @param name the name of the service type
     * @return the service type
     */
    public static ServiceType createServiceType(String name) {
        ServiceType sp = new ServiceType();
        sp.setName(name);
        return sp;
    }
    
    /**
     * Exports the report to a physical file.
     *
     * @param service
     *         the ReportService instance.
     * @param response
     *         the ReportResponse instance.
     * @param exportType
     *         the export type.
     */
    public static <S extends ReportRequest, T extends ReportResponse> void exportReport(
            ReportService<S, T> service, T response, ExportType exportType)
            throws ReportGenerationException, IOException {
        byte[] result = service.exportReport(response, exportType);
        File reportFile = new File("log//"
                + ((BaseReportResponse) response).getReportName() + "."
                + exportType.toString().toLowerCase());
        reportFile.createNewFile();
        FileOutputStream fileStream = new FileOutputStream(reportFile);
        BufferedOutputStream bos = new BufferedOutputStream(fileStream);
        bos.write(result);
        bos.flush();
        bos.close();
    }

   /**
     * Initializes the ReportService instance.
     *
     * @param service
     *         the service to initialize.
     */
    public static void initService(BaseReportService service, EntityManager em)
            throws IllegalArgumentException, SecurityException,
            IllegalAccessException, NoSuchFieldException {
        Field entityManagerField = BaseService.class
                .getDeclaredField("entityManager");
        entityManagerField.setAccessible(true);
        entityManagerField.set(service, em);

        Field reportNameField = BaseReportService.class
                .getDeclaredField("reportName");
        reportNameField.setAccessible(true);
        reportNameField.set(service, service.getClass().getSimpleName());
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
     * <p>
     * Sets value for field of given object.
     * </p>
     *
     * @param obj
     *            the given object.
     * @param field
     *            the field name.
     * @param value
     *            the field value.
     */
    public static void setField(Object obj, String field, Object value) {
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
                declaredField.set(obj, value);
            } finally {
                declaredField.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            // Ignore
        } catch (NoSuchFieldException e) {
            // Ignore
        }
    }
}
