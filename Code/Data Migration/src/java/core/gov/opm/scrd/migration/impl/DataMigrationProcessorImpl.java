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

package gov.opm.scrd.migration.impl;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.A01PrintSuppressionCases;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.AccountHolder;
import gov.opm.scrd.entities.application.AccountNote;
import gov.opm.scrd.entities.application.Address;
import gov.opm.scrd.entities.application.AdjustmentTransaction;
import gov.opm.scrd.entities.application.AnnuitantList;
import gov.opm.scrd.entities.application.AuditBatch;
import gov.opm.scrd.entities.application.BatchDailyPayments;
import gov.opm.scrd.entities.application.Calculation;
import gov.opm.scrd.entities.application.CalculationResult;
import gov.opm.scrd.entities.application.CalculationResultItem;
import gov.opm.scrd.entities.application.CalculationVersion;
import gov.opm.scrd.entities.application.ClaimWithoutService;
import gov.opm.scrd.entities.application.ContactInfo;
import gov.opm.scrd.entities.application.DeductionRate;
import gov.opm.scrd.entities.application.GLCode;
import gov.opm.scrd.entities.application.GLPaymentType;
import gov.opm.scrd.entities.application.Holiday;
import gov.opm.scrd.entities.application.InterestGracePeriod;
import gov.opm.scrd.entities.application.InterestRate;
import gov.opm.scrd.entities.application.InterestSuppression;
import gov.opm.scrd.entities.application.Invoice;
import gov.opm.scrd.entities.application.NewClaimNumber;
import gov.opm.scrd.entities.application.PayTransStatusCode;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.application.PaymentInterestDetail;
import gov.opm.scrd.entities.application.PaymentMoveTransaction;
import gov.opm.scrd.entities.application.PaymentRefundLink;
import gov.opm.scrd.entities.application.PaymentTransactionNote;
import gov.opm.scrd.entities.application.PaymentsAppliedOrderCode;
import gov.opm.scrd.entities.application.RefundTransaction;
import gov.opm.scrd.entities.application.RolePermission;
import gov.opm.scrd.entities.application.SCMFirstInsert;
import gov.opm.scrd.entities.application.TimeFactor;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.application.UserAccountAssignment;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.lookup.AccountStatus;
import gov.opm.scrd.entities.lookup.AgencyCode;
import gov.opm.scrd.entities.lookup.AppointmentType;
import gov.opm.scrd.entities.lookup.ChangeTransFieldNumberCode;
import gov.opm.scrd.entities.lookup.Country;
import gov.opm.scrd.entities.lookup.PayCode;
import gov.opm.scrd.entities.lookup.PayType;
import gov.opm.scrd.entities.lookup.PaymentAppliedOrder;
import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.entities.lookup.RetirementType;
import gov.opm.scrd.entities.lookup.Role;
import gov.opm.scrd.entities.lookup.ServiceType;
import gov.opm.scrd.entities.lookup.State;
import gov.opm.scrd.entities.lookup.Suffix;
import gov.opm.scrd.entities.lookup.UserStatus;
import gov.opm.scrd.migration.DataMigrationProcessor;
import gov.opm.scrd.migration.OPMMigrationException;
import gov.opm.scrd.services.OPMConfigurationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;

/**
 * <p>
 * This class is the default implementation of DataMigrationProcessor. It reads the data rows one after other in source
 * database and for each such row commits the transaction to destination database.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> The class state is not changed after configuration, the configuration is done in a
 * thread - safe manner by Spring IoC. It is also expected that only a single instance of the class will be instantiated
 * per data migration run so the database resources are not shared.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - Data Migration - Processor Module Assembly 1.0
 */
public class DataMigrationProcessorImpl implements DataMigrationProcessor {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = DataMigrationProcessorImpl.class.getName();

    /**
     * <p>
     * Represents the SQL to query record count.
     * </p>
     */
    private static final String SQL_QUERY_RECORD_COUNT = "SELECT COUNT(*) FROM ";

    /**
     * The tables.
     */
    private static final String[] TABLES = {"LookUpGLPaymentType", "LookupRetirementTypeCode", "LookUpGLCodes",
        "LookupInterestRates", "LookupInterestSuppression", "LookUpDeductionRates", "LookupSCMPayCode",
        "LookUpPaymentAppliedOrder", "LookupAgencyCode", "LookupInterestGracePeriod", "LookupPayTransStatusCode",
        "LookupChangeTransFieldNumberCode", "LookupUserStatusCode", "LookupAccountStatus", "LookupServiceTypeCode",
        "LookupPayTypeCode", "LookupAppointmentTypeCode", "LookupPeriodTypeCode", "AnnuitantList", "USStates",
        "NewClaimNumbers", "NameSuffix", "Holidays", "ClaimsWithoutService", "SCMFirstInsert",
        "A01_PrintSuppressionCases", "TimeFactor", "ContactInfo", "LookupUserRoleCode", "Users", "ServiceCreditMaster",
        "ServicePeriods", "CalculatedServicePeriods", "AccountNote", "UserAccountAssignments", "PaymentTransaction",
        "PaymentTranactionNotes", "RefundTransaction", "PaymentRefundLinks", "Invoices", "PaymentInterestDetails",
        "AdjustmentTransaction", "PaymentMoveTransaction", "AuditBatch", "BatchDailyPayments"};

    /**
     * The permission names.
     */
    private static final String[] PERMISSION_NAMES = {"AddAccount", "AddNotes", "AddOverTheCounterPayments",
        "AddPaymentTransactions", "AddServiceHistory", "AdjustAccount", "AdjustInterest", "ApproveBillingTransactions",
        "ApprovePaymentTransactions", "AssignUserRoles", "CalculateserviceHistory", "ChangeAccount",
        "ChangeAccountStatus", "DeleteNotes", "DeleteServiceHistory", "EditNotes", "EditServiceHistory",
        "GenerateStatement", "MovePayments", "PostPaymentTransaction", "PostPriorPayments",
        "RecalculateInitialBilling", "RerunGL", "ReversePaymentTransaction", "SearchAccount",
        "UnpostPaymentTransaction", "UpdateInterest", "ViewAccount", "ViewAuditTrail", "ViewPaymentTransaction"};

    /**
     * Represents the EntityManager instance for managing data in the destination PostgreSQL persistence. It is modified
     * by setter. It is injected by Spring. It can not be null after injected.
     */
    private EntityManager destinationEntityManager;

    /**
     * Represents the country name. It is injected by Spring. It can not be null/empty after injected.
     */
    private String countryName;

    /**
     * Represents the redeposit period type ("ReDeposit (R)") for calculated items that are marked as redeposits. It
     * should be set to "R". It is modified by setter. It is injected by Spring. It can not be null/empty after
     * injected.
     */
    private String redepositPeriodTypeName;

    /**
     * Represents the deposit period type ("Deposit (D)") for calculated items that are marked as deposits. It
     * should be set to "D". It is modified by setter. It is injected by Spring. It can not be null/empty after
     * injected.
     */
    private String depositPeriodTypeName;

    /**
     * Represents the driver name of the source database. It should be set to
     * "com.microsoft.sqlserver.jdbc.SQLServerDriver". It is modified by setter. It is injected by Spring. It can not be
     * null/empty after injected.
     */
    private String sourceDatabaseDriverName;

    /**
     * Represents the url of the source database. It is modified by setter. It is injected by Spring. It can not be
     * null/empty after injected.
     */
    private String sourceDatabaseUrl;

    /**
     * Represents the username of the source database. It is modified by setter. It is injected by Spring. It can not be
     * null/empty after injected.
     */
    private String sourceDatabaseUsername;

    /**
     * Represents the password of the source database. It is modified by setter. It is injected by Spring. It can not be
     * null/empty after injected.
     */
    private String sourceDatabasePassword;

    /**
     * Represents the logger used for logging. It is modified by setter. It is injected by Spring. It can be null if
     * logging is turned off.
     */
    private Logger logger;

    /**
     * The id mapping.
     */
    private Map<Class<?>, Map<Long, Long>> idMapping = new HashMap<Class<?>, Map<Long, Long>>();

    /**
     * The mapping from table name to record count.
     */
    private Map<String, Integer> countMapping = new HashMap<String, Integer>();

    /**
     * Creates an instance of DataMigrationProcessorImpl.
     */
    public DataMigrationProcessorImpl() {
        // Empty
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly (destinationEntityManager is null; countryName,
     *             redepositPeriodTypeName, depositPeriodTypeName, sourceDatabaseDriverName, sourceDatabaseUrl,
     *             sourceDatabaseUsername or sourceDatabasePassword is null/empty).
     */
    @PostConstruct
    protected void checkInit() {
        Helper.checkState(destinationEntityManager == null, "'destinationEntityManager' can't be null.");

        Helper.checkState(isNullOrEmpty(countryName), "'countryName' can't be null/empty.");
        Helper.checkState(isNullOrEmpty(redepositPeriodTypeName), "'redepositPeriodTypeName' can't be null/empty.");
        Helper.checkState(isNullOrEmpty(depositPeriodTypeName), "'depositPeriodTypeName' can't be null/empty.");
        Helper.checkState(isNullOrEmpty(sourceDatabaseDriverName), "'sourceDatabaseDriverName' can't be null/empty.");
        Helper.checkState(isNullOrEmpty(sourceDatabaseUrl), "'sourceDatabaseUrl' can't be null/empty.");
        Helper.checkState(isNullOrEmpty(sourceDatabaseUsername), "'sourceDatabaseUsername' can't be null/empty.");
        Helper.checkState(isNullOrEmpty(sourceDatabasePassword), "'sourceDatabasePassword' can't be null/empty.");
    }

    /**
     * Performs the data migration. The method should read the existing data and store it in the new database.
     *
     * @throws OPMMigrationException
     *             if there is any problem performing migration.
     */
    @Override
    public void migrate() throws OPMMigrationException {
        final String signature = CLASS_NAME + "#migrate()";

        LoggingHelper.logEntrance(logger, signature, null, null);

        idMapping.clear();
        countMapping.clear();

        try {
            Connection connection = obtainConnection();

            int totalCount = 0;
            for (String table : TABLES) {
                int count = getCount(signature, connection, table);
                countMapping.put(table, count);

                totalCount += count;
            }
            countMapping.put("totalCount", totalCount);
            countMapping.put("processedCount", 0);

            try {
                migrateLookupData(signature, connection);
                migrateMiscellaneousData(signature, connection);
                migrateUserData(signature, connection);
                migrateAccountData(signature, connection);
                migratePaymentData(signature, connection);
                migrateBatchData(signature, connection);
            } finally {
                try {
                    // Close the connection
                    connection.close();
                } catch (SQLException e) {
                    // Ignore the exception
                    LoggingHelper.logException(logger, signature, e);
                }
            }

            LoggingHelper.logExit(logger, signature, null);
        } catch (ClassNotFoundException e) {
            throw LoggingHelper.logException(logger, signature, new OPMMigrationException(
                "The driver cannot be located: " + sourceDatabaseDriverName, e));
        } catch (SQLException e) {
            throw LoggingHelper.logException(logger, signature, new OPMMigrationException(
                "A database access error has occurred when accessing source database.", e));
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMMigrationException(
                "The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMMigrationException(
                "An error has occurred when accessing destination persistence.", e));
        }
    }

    /**
     * Sets the EntityManager instance for managing data in the destination PostgreSQL persistence.
     *
     * @param destinationEntityManager
     *            the EntityManager instance for managing data in the destination PostgreSQL persistence.
     */
    public void setDestinationEntityManager(EntityManager destinationEntityManager) {
        this.destinationEntityManager = destinationEntityManager;
    }

    /**
     * Sets the country name.
     *
     * @param countryName
     *            the country name.
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Sets the redeposit period type ("ReDeposit (R)") for calculated items that are marked as redeposits.
     *
     * @param redepositPeriodTypeName
     *            the redeposit period type ("ReDeposit (R)") for calculated items that are marked as redeposits.
     */
    public void setRedepositPeriodTypeName(String redepositPeriodTypeName) {
        this.redepositPeriodTypeName = redepositPeriodTypeName;
    }

    /**
     * Sets the deposit period type ("Deposit (D)") for calculated items that are marked as deposits.
     *
     * @param depositPeriodTypeName
     *            the deposit period type ("Deposit (D)") for calculated items that are marked as deposits.
     */
    public void setDepositPeriodTypeName(String depositPeriodTypeName) {
        this.depositPeriodTypeName = depositPeriodTypeName;
    }

    /**
     * Sets the driver name of the source database.
     *
     * @param sourceDatabaseDriverName
     *            the driver name of the source database.
     */
    public void setSourceDatabaseDriverName(String sourceDatabaseDriverName) {
        this.sourceDatabaseDriverName = sourceDatabaseDriverName;
    }

    /**
     * Sets the url of the source database.
     *
     * @param sourceDatabaseUrl
     *            the url of the source database.
     */
    public void setSourceDatabaseUrl(String sourceDatabaseUrl) {
        this.sourceDatabaseUrl = sourceDatabaseUrl;
    }

    /**
     * Sets the username of the source database.
     *
     * @param sourceDatabaseUsername
     *            the username of the source database.
     */
    public void setSourceDatabaseUsername(String sourceDatabaseUsername) {
        this.sourceDatabaseUsername = sourceDatabaseUsername;
    }

    /**
     * Sets the password of the source database.
     *
     * @param sourceDatabasePassword
     *            the password of the source database.
     */
    public void setSourceDatabasePassword(String sourceDatabasePassword) {
        this.sourceDatabasePassword = sourceDatabasePassword;
    }

    /**
     * Sets the logger used for logging.
     *
     * @param logger
     *            the logger used for logging.
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Performs the batch related data migration. The method should read the existing data and store it in the new
     * database.
     *
     * @param signature
     *            the signature
     * @param connection
     *            the connection
     *
     * @throws SQLException
     *             if there is any problem accessing source database.
     * @throws IllegalStateException
     *             if entity manager has been closed
     * @throws PersistenceException
     *             if any error occurs when accessing destination persistence
     */
    private void migrateBatchData(String signature, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection
            .prepareStatement("SELECT EventYear, EventMonth, EventDay, FileReceived,"
                + " DailyAction, ManualBatch, ErrorImporting, ErrorProcessing, LatestBatch,"
                + " AmountImported, AmountProcessed, NumberACHAccepted, NumberACHUnresolved, NumberACHSuspended,"
                + " NumberAccepted, NumberUnresolved, NumberSuspended, NumberChangeRequests,"
                + " PaymentsProcessed, InitialBillsProcessed, ReversedProcessed, ACHStopLetters, RefundMemos,"
                + " ErrorCountProcessing, UserKey, BatchTime FROM AuditBatch");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AuditBatch batch = new AuditBatch();
                batch.setEventYear(resultSet.getInt("EventYear"));
                batch.setEventMonth(resultSet.getInt("EventMonth"));
                batch.setEventDay(resultSet.getInt("EventDay"));
                batch.setFileReceived(resultSet.getBoolean("FileReceived"));
                batch.setDailyAction(resultSet.getBoolean("DailyAction"));
                batch.setManualBatch(resultSet.getBoolean("ManualBatch"));
                batch.setErrorImporting(resultSet.getBoolean("ErrorImporting"));
                batch.setErrorProcessing(resultSet.getBoolean("ErrorProcessing"));
                batch.setLatestBatch(resultSet.getBoolean("LatestBatch"));
                batch.setAmountImported(resultSet.getBigDecimal("AmountImported"));
                batch.setAmountProcessed(resultSet.getBigDecimal("AmountProcessed"));
                batch.setNumberAchAccepted(resultSet.getInt("NumberACHAccepted"));
                batch.setNumberAchUnresolved(resultSet.getInt("NumberACHUnresolved"));
                batch.setNumberAchSuspended(resultSet.getInt("NumberACHSuspended"));
                batch.setNumberAccepted(resultSet.getInt("NumberAccepted"));
                batch.setNumberUnresolved(resultSet.getInt("NumberUnresolved"));
                batch.setNumberSuspended(resultSet.getInt("NumberSuspended"));
                batch.setNumberChangeRequests(resultSet.getInt("NumberChangeRequests"));
                batch.setPaymentsProcessed(resultSet.getInt("PaymentsProcessed"));
                batch.setInitialBillsProcessed(resultSet.getInt("InitialBillsProcessed"));
                batch.setReversedProcessed(resultSet.getInt("ReversedProcessed"));
                batch.setAchStopLetters(resultSet.getInt("ACHStopLetters"));
                batch.setRefundMemos(resultSet.getInt("RefundMemos"));
                batch.setErrorCountProcessing(resultSet.getInt("ErrorCountProcessing"));
                batch.setUserKey(resultSet.getLong("UserKey"));
                batch.setBatchTime(resultSet.getTimestamp("BatchTime"));

                persist(batch);
            }
            printProgressMessage("AuditBatch", "AuditBatch");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT AuditBatchIDLog, PayTransactionKey, NumberPaymentsToday,"
                + " BatchDate, AccountStatus, PayTransStatusCode, SCMClaimNumber, AccountBalance,"
                + " OverPaymentAmount, ACHPayment, ACHStopLetter, PrintInvoice, RefundRequired,"
                + " ReversedPayment, UpdateToCompleted, PrintInitialBill, LatestBatch, ErrorProcessing"
                + " FROM BatchDailyPayments");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BatchDailyPayments payments = new BatchDailyPayments();
                payments.setAuditBatchLogId(resultSet.getLong("AuditBatchIDLog"));
                payments.setPayTransactionKey(resultSet.getInt("PayTransactionKey"));
                payments.setNumberPaymentToday(resultSet.getInt("NumberPaymentsToday"));
                payments.setBatchTime(resultSet.getTimestamp("BatchDate"));

                payments.setAccountStatus(getEntity(AccountStatus.class,
                    getNewId(AccountStatus.class, resultSet.getLong("AccountStatus"))));

                payments.setPayTransStatusCode(resultSet.getInt("PayTransStatusCode"));
                payments.setClaimNumber(resultSet.getString("SCMClaimNumber"));
                payments.setAccountBalance(resultSet.getBigDecimal("AccountBalance"));
                payments.setOverPaymentAmount(resultSet.getBigDecimal("OverPaymentAmount"));
                payments.setAchPayment(resultSet.getBoolean("ACHPayment"));
                payments.setAchStopLetter(resultSet.getBoolean("ACHStopLetter"));
                payments.setPrintInvoice(resultSet.getBoolean("PrintInvoice"));
                payments.setRefundRequired(resultSet.getBoolean("RefundRequired"));
                payments.setReversedPayment(resultSet.getBoolean("ReversedPayment"));
                payments.setUpdateToCompleted(resultSet.getBoolean("UpdateToCompleted"));
                payments.setPrintInitialBill(resultSet.getBoolean("PrintInitialBill"));
                payments.setLatestBatch(resultSet.getBoolean("LatestBatch"));
                payments.setErrorProcessing(resultSet.getBoolean("ErrorProcessing"));

                persist(payments);
            }
            printProgressMessage("BatchDailyPayments", "BatchDailyPayments");
        } finally {
            closeStatement(signature, preparedStatement);
        }
    }

    /**
     * Performs the user related data migration. The method should read the existing data and store it in the new
     * database.
     *
     * @param signature
     *            the signature
     * @param connection
     *            the connection
     *
     * @throws SQLException
     *             if there is any problem accessing source database.
     * @throws IllegalStateException
     *             if entity manager has been closed
     * @throws PersistenceException
     *             if any error occurs when accessing destination persistence
     */
    private void migrateUserData(String signature, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection
            .prepareStatement("SELECT UserRoleCode, UserRoleName, UserRoleDescription,"
                + " AddAccount, AddNotes, AddOverTheCounterPayments, AddPaymentTransactions,"
                + " AddServiceHistory, AdjustAccount, AdjustInterest, ApproveBillingTransactions,"
                + " ApprovePaymentTransactions, AssignUserRoles, CalculateserviceHistory, ChangeAccount,"
                + " ChangeAccountStatus, DeleteNotes, DeleteServiceHistory, EditNotes,"
                + " EditServiceHistory, GenerateStatement, MovePayments, PostPaymentTransaction,"
                + " PostPriorPayments, RecalculateInitialBilling, RerunGL, ReversePaymentTransaction,"
                + " SearchAccount, UnpostPaymentTransaction, UpdateInterest, ViewAccount, ViewAuditTrail,"
                + " ViewPaymentTransaction FROM LookupUserRoleCode");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Role role = new Role();

                long oldId = resultSet.getLong("UserRoleCode");
                String roleName = resultSet.getString("UserRoleName");
                role.setName(roleName);
                role.setDescription(resultSet.getString("UserRoleDescription"));

                persist(role);

                saveNewId(Role.class, oldId, role.getId());

                for (String permissionName : PERMISSION_NAMES) {
                    if (resultSet.getBoolean(permissionName)) {
                        RolePermission rolePermission = new RolePermission();
                        rolePermission.setRole(roleName);
                        rolePermission.setAction(permissionName);

                        persist(rolePermission);
                    }
                }
            }
            printProgressMessage("Role", "LookupUserRoleCode");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT UserKey, UserNetworkID, UserID, UserStatusCode, UserFirstName,"
                + " UserLastName, UserPhoneNumber, UserEmail FROM Users");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                long oldId = resultSet.getLong("UserKey");

                user.setNetworkId(resultSet.getString("UserNetworkID"));
                user.setUsername(resultSet.getString("UserID"));

                user.setStatus(getEntity(UserStatus.class,
                    getNewId(UserStatus.class, resultSet.getLong("UserStatusCode"))));

                user.setFirstName(resultSet.getString("UserFirstName"));
                user.setLastName(resultSet.getString("UserLastName"));
                user.setTelephone(resultSet.getString("UserPhoneNumber"));
                user.setEmail(resultSet.getString("UserEmail"));

                // Get role
                PreparedStatement preparedStatementUserRole = connection
                    .prepareStatement("SELECT UserRoleCode FROM UserRoleAssignment WHERE UserKey =?");
                try {
                    preparedStatementUserRole.setObject(1, oldId);

                    ResultSet resultSetUserRole = preparedStatementUserRole.executeQuery();

                    if (resultSetUserRole.next()) {
                        Role role = getEntity(Role.class,
                            getNewId(Role.class, resultSetUserRole.getLong("UserRoleCode")));

                        user.setRole(role);
                    }
                } finally {
                    closeStatement(signature, preparedStatementUserRole);
                }

                persist(user);

                saveNewId(User.class, oldId, user.getId());
            }
            printProgressMessage("User", "Users");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection.prepareStatement("SELECT UserKey, UserSupervisorKey FROM Users");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = getEntity(User.class, getNewId(User.class, resultSet.getLong("UserKey")));
                user.setSupervisor(getEntity(User.class, getNewId(User.class, resultSet.getLong("UserSupervisorKey"))));

                merge(user);
            }
        } finally {
            closeStatement(signature, preparedStatement);
        }
    }

    /**
     * Performs the lookup related data migration. The method should read the existing data and store it in the new
     * database.
     *
     * @param signature
     *            the signature
     * @param connection
     *            the connection
     *
     * @throws SQLException
     *             if there is any problem accessing source database.
     * @throws IllegalStateException
     *             if entity manager has been closed
     * @throws PersistenceException
     *             if any error occurs when accessing destination persistence
     */
    private void migrateLookupData(String signature, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection
            .prepareStatement("SELECT PaymentCode, CodeDescription FROM LookUpGLPaymentType");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GLPaymentType type = new GLPaymentType();
                type.setPaymentCode(resultSet.getString("PaymentCode"));
                type.setCodeDescription(resultSet.getString("CodeDescription"));

                persist(type);
            }
            printProgressMessage("GLPaymentType", "LookUpGLPaymentType");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection.prepareStatement("SELECT SCMRetirementTypeCode, SCMRetirementTypeDescription,"
            + " RetireMentTypeDisplayOrder FROM LookupRetirementTypeCode");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RetirementType type = new RetirementType();
                long oldId = resultSet.getLong("SCMRetirementTypeCode");
                type.setName("" + oldId);
                type.setDescription(resultSet.getString("SCMRetirementTypeDescription"));
                type.setDisplayOrder(resultSet.getInt("RetireMentTypeDisplayOrder"));

                persist(type);

                saveNewId(RetirementType.class, oldId, type.getId());
            }
            printProgressMessage("RetirementType", "LookupRetirementTypeCode");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT GLName, GLCode, PaymentType, SCMRetirementTypeCode, PostOffice"
                + " FROM LookUpGLCodes");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                GLCode code = new GLCode();
                code.setName(resultSet.getString("GLName"));
                code.setCode(resultSet.getString("GLCode"));
                code.setPaymentType(resultSet.getString("PaymentType"));

                code.setRetirementType(getEntity(RetirementType.class,
                    getNewId(RetirementType.class, resultSet.getLong("SCMRetirementTypeCode"))));

                code.setPostOffice(resultSet.getBoolean("PostOffice"));

                persist(code);
            }
            printProgressMessage("GLCode", "LookUpGLCodes");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection.prepareStatement("SELECT InterestYear, InterestRate FROM LookupInterestRates");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                InterestRate rate = new InterestRate();
                rate.setInterestYear(resultSet.getInt("InterestYear"));
                rate.setInterestRate(resultSet.getBigDecimal("InterestRate"));

                persist(rate);
            }
            printProgressMessage("InterestRate", "LookupInterestRates");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT SCMClaimNumber, Post982Redeposit, Pre1082Redeposit, Post982Deposit,"
                + " Pre1082Deposit, FersDeposit FROM LookupInterestSuppression");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                InterestSuppression suppression = new InterestSuppression();
                suppression.setClaimNumber(resultSet.getString("SCMClaimNumber"));
                suppression.setPost982Redeposit(resultSet.getBoolean("Post982Redeposit"));
                suppression.setPre1082Redeposit(resultSet.getBoolean("Pre1082Redeposit"));
                suppression.setPost982Deposit(resultSet.getBoolean("Post982Deposit"));
                suppression.setPre1082Deposit(resultSet.getBoolean("Pre1082Deposit"));
                suppression.setFersDeposit(resultSet.getBoolean("FersDeposit"));

                persist(suppression);
            }
            printProgressMessage("InterestSuppression", "LookupInterestSuppression");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT SCMServiceTypeCode, SCMRetirementTypeCode, StartDate, EndDate,"
                + " DaysInPeriod, Rate, ServiceTypeDescription, DeductionConversionFactor FROM LookUpDeductionRates");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DeductionRate rate = new DeductionRate();
                rate.setServiceType(resultSet.getString("SCMServiceTypeCode"));
                rate.setRetirementType(getEntity(RetirementType.class,
                    getNewId(RetirementType.class, resultSet.getLong("SCMRetirementTypeCode"))));
                rate.setStartDate(resultSet.getTimestamp("StartDate"));
                rate.setEndDate(resultSet.getTimestamp("EndDate"));
                rate.setDaysInPeriod(resultSet.getInt("DaysInPeriod"));
                rate.setRate(resultSet.getBigDecimal("Rate"));
                rate.setServiceTypeDescription(resultSet.getString("ServiceTypeDescription"));
                rate.setDeductionConversionFactor(resultSet.getBigDecimal("DeductionConversionFactor"));

                persist(rate);
            }
            printProgressMessage("DeductionRate", "LookUpDeductionRates");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT SCMPayCode, SCMPayCodeDescription FROM LookupSCMPayCode");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PayCode code = new PayCode();
                code.setName(resultSet.getString("SCMPayCode"));
                code.setDescription(resultSet.getString("SCMPayCodeDescription"));

                persist(code);
            }
            printProgressMessage("PayCode", "LookupSCMPayCode");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT PaymentAccount, PaymentAppliedDisplayOrder FROM LookUpPaymentAppliedOrder");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PaymentAppliedOrder order = new PaymentAppliedOrder();
                order.setName(resultSet.getString("PaymentAccount"));
                order.setDisplayOrder(resultSet.getInt("PaymentAppliedDisplayOrder"));

                persist(order);
            }
            printProgressMessage("PaymentAppliedOrder", "LookUpPaymentAppliedOrder");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT SCMAgencyCode, SCMAgencyDescription, AgencyCodeDisplayOrder"
                + " FROM LookupAgencyCode");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AgencyCode code = new AgencyCode();

                long oldId = resultSet.getLong("SCMAgencyCode");

                code.setName(resultSet.getString("SCMAgencyDescription"));
                code.setDisplayOrder(resultSet.getInt("AgencyCodeDisplayOrder"));

                persist(code);

                saveNewId(AgencyCode.class, oldId, code.getId());
            }
            printProgressMessage("AgencyCode", "LookupAgencyCode");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT SCMClaimNumber, Post982Redeposit, Pre1082Redeposit, Post982Deposit,"
                + " Pre1082Deposit, FersDeposit FROM LookupInterestGracePeriod");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                InterestGracePeriod period = new InterestGracePeriod();
                period.setClaimNumber(resultSet.getString("SCMClaimNumber"));
                period.setPost982Redeposit(resultSet.getBoolean("Post982Redeposit"));
                period.setPre1082Redeposit(resultSet.getBoolean("Pre1082Redeposit"));
                period.setPost982Deposit(resultSet.getBoolean("Post982Deposit"));
                period.setPre1082Deposit(resultSet.getBoolean("Pre1082Deposit"));
                period.setFersDeposit(resultSet.getBoolean("FersDeposit"));

                persist(period);
            }
            printProgressMessage("InterestGracePeriod", "LookupInterestGracePeriod");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT PayTransStatusCode, PayTransStatusDescription, PayTransDisplayCategory,"
                + " PayTransDisplayOrder, NextStateLink, BatchProcessingOrder, FinalState, NeedsApproval,"
                + " ShowOnSuspense, IncludeInBalance, NightlyBatch, Deletable, Reversable, ManualEntered,"
                + " SuspenseAction, CanHitGL, ReversingType, BalancedScorecard, SendToDBTS"
                + " FROM LookupPayTransStatusCode");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PayTransStatusCode code = new PayTransStatusCode();

                long oldId = resultSet.getLong("PayTransStatusCode");

                code.setDescription(resultSet.getString("PayTransStatusDescription"));
                code.setCategory(resultSet.getString("PayTransDisplayCategory"));
                code.setDisplayOrder(resultSet.getInt("PayTransDisplayOrder"));
                code.setNextStateLink(resultSet.getInt("NextStateLink"));
                code.setBatchProcessingOrder(resultSet.getInt("BatchProcessingOrder"));
                code.setFinalState(resultSet.getBoolean("FinalState"));
                code.setNeedsApproval(resultSet.getBoolean("NeedsApproval"));
                code.setShowOnSuspense(resultSet.getBoolean("ShowOnSuspense"));
                code.setIncludeInBalance(resultSet.getBoolean("IncludeInBalance"));
                code.setNightlyBatch(resultSet.getBoolean("NightlyBatch"));
                code.setDeletable(resultSet.getBoolean("Deletable"));
                code.setReversable(resultSet.getBoolean("Reversable"));
                code.setManualEntered(resultSet.getBoolean("ManualEntered"));
                code.setSuspenseAction(resultSet.getBoolean("SuspenseAction"));
                code.setCanHitGl(resultSet.getBoolean("CanHitGL"));
                code.setReversingType(resultSet.getBoolean("ReversingType"));
                code.setBalancedScorecard(resultSet.getBoolean("BalancedScorecard"));
                code.setSendToDbts(resultSet.getBoolean("SendToDBTS"));

                persist(code);

                saveNewId(PayTransStatusCode.class, oldId, code.getId());
            }
            printProgressMessage("PayTransStatusCode", "LookupPayTransStatusCode");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT ChangeTransFieldNumberCode, ChangeTransFieldNumberDescription"
                + " FROM LookupChangeTransFieldNumberCode");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ChangeTransFieldNumberCode code = new ChangeTransFieldNumberCode();
                code.setName(resultSet.getString("ChangeTransFieldNumberCode"));
                code.setDescription(resultSet.getString("ChangeTransFieldNumberDescription"));

                persist(code);
            }
            printProgressMessage("ChangeTransFieldNumberCode", "LookupChangeTransFieldNumberCode");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT UserStatusCode, UserStatusDescription FROM LookupUserStatusCode");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserStatus status = new UserStatus();

                long oldId = resultSet.getLong("UserStatusCode");

                status.setName(resultSet.getString("UserStatusDescription"));

                persist(status);

                saveNewId(UserStatus.class, oldId, status.getId());
            }
            printProgressMessage("UserStatus", "LookupUserStatusCode");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT AccountStatus, AccountStatusDescription, AcountStatusCategory,"
                + " AccountStatusDisplayOrder FROM LookupAccountStatus");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AccountStatus status = new AccountStatus();

                long oldId = resultSet.getLong("AccountStatus");

                status.setName(resultSet.getString("AccountStatusDescription"));
                status.setCategory(resultSet.getString("AcountStatusCategory"));
                status.setDisplayOrder(resultSet.getInt("AccountStatusDisplayOrder"));

                persist(status);

                saveNewId(AccountStatus.class, oldId, status.getId());
            }
            printProgressMessage("AccountStatus", "LookupAccountStatus");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT SCMServiceTypeCode, SCMServiceTypeDescription, FERSDepositAllowedAfter88,"
                + " ServiceTypeDisplayOrder FROM LookupServiceTypeCode");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ServiceType type = new ServiceType();

                long oldId = resultSet.getLong("SCMServiceTypeCode");

                type.setName(resultSet.getString("SCMServiceTypeDescription"));
                type.setFersDepositAllowedAfter88(resultSet.getInt("FERSDepositAllowedAfter88"));
                type.setDisplayOrder(resultSet.getInt("ServiceTypeDisplayOrder"));

                persist(type);

                saveNewId(ServiceType.class, oldId, type.getId());
            }
            printProgressMessage("ServiceType", "LookupServiceTypeCode");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT PayTypeCode, PayTypeDescription, PayTypeDisplayOrder FROM LookupPayTypeCode");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PayType type = new PayType();
                type.setName(resultSet.getString("PayTypeCode"));
                type.setDescription(resultSet.getString("PayTypeDescription"));
                type.setDisplayOrder(resultSet.getInt("PayTypeDisplayOrder"));

                persist(type);
            }
            printProgressMessage("PayType", "LookupPayTypeCode");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection.prepareStatement("SELECT SCMAppointmentTypeCode, SCMAppointmentTypeDescription,"
            + " AppoinmentTypeDisplayOrder, AppointmentTypeCategory FROM LookupAppointmentTypeCode");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AppointmentType type = new AppointmentType();
                type.setName(resultSet.getString("SCMAppointmentTypeCode"));
                type.setDescription(resultSet.getString("SCMAppointmentTypeDescription"));
                type.setDisplayOrder(resultSet.getInt("AppoinmentTypeDisplayOrder"));
                type.setCategory(resultSet.getString("AppointmentTypeCategory"));

                persist(type);
            }
            printProgressMessage("AppointmentType", "LookupAppointmentTypeCode");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT SCMPeriodTypeCode, SCMPeriodTypeDescription, PeriodTypeDisplayOrder"
                + " FROM LookupPeriodTypeCode");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PeriodType type = new PeriodType();
                type.setName(resultSet.getString("SCMPeriodTypeCode"));
                type.setDescription(resultSet.getString("SCMPeriodTypeDescription"));
                type.setDisplayOrder(resultSet.getInt("PeriodTypeDisplayOrder"));

                persist(type);
            }
            printProgressMessage("PeriodType", "LookupPeriodTypeCode");
        } finally {
            closeStatement(signature, preparedStatement);
        }
    }

    /**
     * Performs the miscellaneous data migration. The method should read the existing data and store it in the new
     * database.
     *
     * @param signature
     *            the signature
     * @param connection
     *            the connection
     *
     * @throws SQLException
     *             if there is any problem accessing source database.
     * @throws IllegalStateException
     *             if entity manager has been closed
     * @throws PersistenceException
     *             if any error occurs when accessing destination persistence
     */
    private void migrateMiscellaneousData(String signature, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT SCMClaimNumber FROM AnnuitantList");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AnnuitantList list = new AnnuitantList();
                list.setClaimNumber(resultSet.getString("SCMClaimNumber"));

                persist(list);
            }
            printProgressMessage("AnnuitantList", "AnnuitantList");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection.prepareStatement("SELECT State, Abbreviation FROM USStates");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                State state = new State();
                state.setName(resultSet.getString("State"));
                state.setAbbreviation(resultSet.getString("Abbreviation"));

                persist(state);
            }
            printProgressMessage("State", "USStates");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection.prepareStatement("SELECT SCMClaimNumber FROM NewClaimNumbers");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                NewClaimNumber newClaimNumber = new NewClaimNumber();
                newClaimNumber.setClaimNumber(resultSet.getString("SCMClaimNumber"));

                persist(newClaimNumber);
            }
            printProgressMessage("NewClaimNumber", "NewClaimNumbers");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection.prepareStatement("SELECT Suffix FROM NameSuffix");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Suffix suffix = new Suffix();
                suffix.setName(resultSet.getString("Suffix"));

                persist(suffix);
            }
            printProgressMessage("Suffix", "NameSuffix");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT Holiday, ExactDate, WeekDay, MonthNumber, DayOfMonth, WeekOfMonth, HolidayId"
                + " FROM Holidays");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Holiday holidays = new Holiday();
                holidays.setHoliday(resultSet.getString("Holiday"));
                holidays.setExactDate(resultSet.getBoolean("ExactDate"));
                holidays.setWeekDay(resultSet.getInt("WeekDay"));
                holidays.setMonthNumber(resultSet.getInt("MonthNumber"));
                holidays.setDayOfMonth(resultSet.getInt("DayOfMonth"));
                holidays.setWeekOfMonth(resultSet.getInt("WeekOfMonth"));
                holidays.setHolidayId(resultSet.getInt("HolidayId"));

                persist(holidays);
            }
            printProgressMessage("Holiday", "Holidays");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT SCMClaimNumber, SCMDateOfBirth FROM ClaimsWithoutService");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ClaimWithoutService claims = new ClaimWithoutService();
                claims.setClaimNumber(resultSet.getString("SCMClaimNumber"));
                claims.setDateOfBirth(resultSet.getTimestamp("SCMDateOfBirth"));

                persist(claims);
            }
            printProgressMessage("ClaimWithoutService", "ClaimsWithoutService");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection.prepareStatement("SELECT SCMClaimNumber, scmlastact FROM SCMFirstInsert");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SCMFirstInsert insert = new SCMFirstInsert();
                insert.setClaimNumber(resultSet.getString("SCMClaimNumber"));
                insert.setLastAction(resultSet.getTimestamp("scmlastact"));

                persist(insert);
            }
            printProgressMessage("SCMFirstInsert", "SCMFirstInsert");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT SCMClaimNumber, ReasonForPrintSuppression FROM A01_PrintSuppressionCases");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                A01PrintSuppressionCases cases = new A01PrintSuppressionCases();
                cases.setClaimNumber(resultSet.getString("SCMClaimNumber"));
                cases.setReasonForPrintSuppression(resultSet.getInt("ReasonForPrintSuppression"));

                persist(cases);
            }
            printProgressMessage("A01PrintSuppressionCases", "A01_PrintSuppressionCases");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection.prepareStatement("SELECT NumDays, NumMonths, TimeFactor FROM TimeFactor");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TimeFactor factor = new TimeFactor();
                factor.setNumDays(resultSet.getInt("NumDays"));
                factor.setNumMonths(resultSet.getInt("NumMonths"));
                factor.setTimeFactor(resultSet.getBigDecimal("TimeFactor"));

                persist(factor);
            }
            printProgressMessage("TimeFactor", "TimeFactor");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection.prepareStatement("SELECT ContactName, ContactText FROM ContactInfo");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ContactInfo info = new ContactInfo();
                info.setName(resultSet.getString("ContactName"));
                info.setText(resultSet.getString("ContactText"));

                persist(info);
            }
            printProgressMessage("ContactInfo", "ContactInfo");
        } finally {
            closeStatement(signature, preparedStatement);
        }
    }

    /**
     * Performs the account related data migration. The method should read the existing data and store it in the new
     * database.
     *
     * @param signature
     *            the signature
     * @param connection
     *            the connection
     *
     * @throws SQLException
     *             if there is any problem accessing source database.
     * @throws IllegalStateException
     *             if entity manager has been closed
     * @throws PersistenceException
     *             if any error occurs when accessing destination persistence
     */
    private void migrateAccountData(String signature, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection
            .prepareStatement("SELECT SCMClaimNumber, SCMSSN, SCMDateOfBirth, SCMFName, SCMMInitial, SCMLastName,"
                + " SCMSuffix, SCMName, SCMAddress1, SCMAddress2, SCMAddress3, SCMAddress4, SCMAddress5, SCMCity,"
                + " SCMState, SCMZipcode, SCMTelephone, SCMPosition, SCMAgencyCode, SCMJobLocationCity,"
                + " SCMJobLocationState, SCMDeposit, SCMRedeposit, SCMTotVarRedeposit, SCMNonDed, SCMFersW,"
                + " SCMAccIntDep, SCMAccIntRdep, SCMAccIntNonDed, SCMAccIntVarRdep, SCMAccIntFers, SCMTotPayd,"
                + " SCMTotPayr, SCMTotPayn, SCMTotPayvr, SCMTotPayfers, SCMCompDate, SCMVarIntCompDate, SCMLastAct,"
                + " SCMLastPay, SCMPayCode, SCMTimePer, SCMAddServ, SCMNoInterest, SCMCode20Date, SCMFlagPreRedep,"
                + " SCMFlagPostRedep, SCMPriorClaimNumber, AccountStatus, PaymentOrder, NewClaimNumber,"
                + " StopACHPayment, DBTSAccount FROM ServiceCreditMaster");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Account account = new Account();
                account.setClaimNumber(resultSet.getString("SCMClaimNumber"));

                AccountHolder accountHolder = new AccountHolder();
                accountHolder.setSsn(resultSet.getString("SCMSSN"));
                accountHolder.setBirthDate(resultSet.getTimestamp("SCMDateOfBirth"));
                accountHolder.setFirstName(resultSet.getString("SCMFName"));
                accountHolder.setMiddleInitial(resultSet.getString("SCMMInitial"));
                accountHolder.setLastName(resultSet.getString("SCMLastName"));

                accountHolder.setSuffix(getEntityByName(Suffix.class, resultSet.getString("SCMSuffix")));

                accountHolder.setTitle(resultSet.getString("SCMName"));

                Address address = new Address();
                address.setStreet1(resultSet.getString("SCMAddress1"));
                address.setStreet2(resultSet.getString("SCMAddress2"));
                address.setStreet3(resultSet.getString("SCMAddress3"));
                address.setStreet4(resultSet.getString("SCMAddress4"));
                address.setStreet5(resultSet.getString("SCMAddress5"));
                address.setCity(resultSet.getString("SCMCity"));
                address.setCountry(getCountry());

                address.setState(getState(resultSet.getString("SCMState")));

                address.setZipCode(resultSet.getString("SCMZipcode"));
                accountHolder.setAddress(address);

                accountHolder.setTelephone(resultSet.getString("SCMTelephone"));
                accountHolder.setPosition(resultSet.getString("SCMPosition"));
                accountHolder.setAgencyCode(resultSet.getString("SCMAgencyCode"));
                accountHolder.setCityOfEmployment(resultSet.getString("SCMJobLocationCity"));

                accountHolder.setStateOfEmployment(getState(resultSet.getString("SCMJobLocationState")));

                persist(accountHolder);

                account.setHolder(accountHolder);

                account.setTotalDeposit(resultSet.getBigDecimal("SCMDeposit"));
                account.setTotalRedeposit(resultSet.getBigDecimal("SCMRedeposit"));
                account.setTotalVarRedeposit(resultSet.getBigDecimal("SCMTotVarRedeposit"));
                account.setTotalNonDeposit(resultSet.getBigDecimal("SCMNonDed"));
                account.setTotalFersW(resultSet.getBigDecimal("SCMFersW"));
                account.setAccIntDep(resultSet.getBigDecimal("SCMAccIntDep"));
                account.setAccIntRdep(resultSet.getBigDecimal("SCMAccIntRdep"));
                account.setAccIntNonDep(resultSet.getBigDecimal("SCMAccIntNonDed"));
                account.setAccIntVarRdep(resultSet.getBigDecimal("SCMAccIntVarRdep"));
                account.setAccIntFers(resultSet.getBigDecimal("SCMAccIntFers"));
                account.setTotPayd(resultSet.getBigDecimal("SCMTotPayd"));
                account.setTotPayr(resultSet.getBigDecimal("SCMTotPayr"));
                account.setTotPayn(resultSet.getBigDecimal("SCMTotPayn"));
                account.setTotPayvr(resultSet.getBigDecimal("SCMTotPayvr"));
                account.setTotPayfers(resultSet.getBigDecimal("SCMTotPayfers"));
                account.setComputationDate(resultSet.getTimestamp("SCMCompDate"));
                account.setVarIntComputationDate(resultSet.getTimestamp("SCMVarIntCompDate"));
                account.setLastAction(resultSet.getTimestamp("SCMLastAct"));
                account.setLastPay(resultSet.getTimestamp("SCMLastPay"));

                account.setPayCode(getEntityByName(PayCode.class, resultSet.getString("SCMPayCode")));

                account.setTimePeriod(resultSet.getString("SCMTimePer"));
                account.setAdditionalService(resultSet.getString("SCMAddServ"));
                account.setNoInterest(resultSet.getBoolean("SCMNoInterest"));
                account.setCode20Date(resultSet.getTimestamp("SCMCode20Date"));
                account.setFlagPreredeposit(resultSet.getBoolean("SCMFlagPreRedep"));
                account.setFlagPostredeposit(resultSet.getBoolean("SCMFlagPostRedep"));
                account.setPriorClaimNumber(resultSet.getString("SCMPriorClaimNumber"));

                account.setStatus(getEntity(AccountStatus.class,
                    getNewId(AccountStatus.class, resultSet.getLong("AccountStatus"))));

                account.setPaymentOrder(resultSet.getString("PaymentOrder"));
                account.setNewClaimNumber(resultSet.getString("NewClaimNumber"));
                account.setStopAchPayment(resultSet.getBoolean("StopACHPayment"));
                account.setDbtsAccount(resultSet.getBoolean("DBTSAccount"));

                persist(account);
            }
            printProgressMessage("Account", "ServiceCreditMaster");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT SCMClaimNumber, SCMVersion, SCMLineNumber, SCMBeginDate, SCMEndDate,"
                + " SCMRetirementTypeCode, SCMAgencyCode, SCMServiceTypeCode, SCMAppointmentTypeCode,"
                + " SCMEnteredAmount, SCMHoursInYear, SCMAnnualizedAmount, SCMPeriodType, SCMDateEntered,"
                + " SCMEnteredBy, SCMPayTypeCode FROM ServicePeriods");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Account parentAccoount = getAccount(resultSet.getString("SCMClaimNumber"));

                CalculationVersion version = new CalculationVersion();
                version.setVersion(resultSet.getInt("SCMVersion"));
                version.setLineNumber(resultSet.getInt("SCMLineNumber"));

                Calculation calculation = new Calculation();
                calculation.setBeginDate(resultSet.getTimestamp("SCMBeginDate"));
                calculation.setEndDate(resultSet.getTimestamp("SCMEndDate"));

                calculation.setRetirementType(getEntity(RetirementType.class,
                    getNewId(RetirementType.class, resultSet.getLong("SCMRetirementTypeCode"))));
                calculation.setAgencyCode(getEntity(AgencyCode.class,
                    getNewId(AgencyCode.class, resultSet.getLong("SCMAgencyCode"))));
                calculation.setServiceType(getEntity(ServiceType.class,
                    getNewId(ServiceType.class, resultSet.getLong("SCMServiceTypeCode"))));

                calculation.setAppointmentType(getEntityByName(AppointmentType.class,
                    resultSet.getString("SCMAppointmentTypeCode")));

                calculation.setAmount(resultSet.getBigDecimal("SCMEnteredAmount"));
                calculation.setHoursInYear(resultSet.getInt("SCMHoursInYear"));
                calculation.setAnnualizedAmount(resultSet.getBigDecimal("SCMAnnualizedAmount"));

                String periodTypeName = resultSet.getString("SCMPeriodType");
                calculation.setPeriodType(getEntityByName(PeriodType.class, periodTypeName));

                calculation.setDateEntered(resultSet.getTimestamp("SCMDateEntered"));
                calculation.setEnteredBy(resultSet.getLong("SCMEnteredBy"));

                calculation.setPayType(getEntityByName(PayType.class, resultSet.getString("SCMPayTypeCode")));

                version.setCalculations(new ArrayList<Calculation>());
                version.getCalculations().add(calculation);

                if (redepositPeriodTypeName.equals(periodTypeName)) {
                    parentAccoount.getFersRedepositCalculationVersions().add(version);
                } else if (depositPeriodTypeName.equals(periodTypeName)) {
                    parentAccoount.getFersDepositCalculationVersions().add(version);
                }

                merge(parentAccoount);
            }
            printProgressMessage("CalculationVersion (ServicePeriods)", "ServicePeriods");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT SCMClaimNumber, Version, LineNumber, SCMRetirementTypeCode,"
                + " SCMPeriodTypeCode, SCMEnteredBy, LastUpdate, ServicePeriodStartDate,"
                + " ServicePeriodEndDate, ServicePeriodMidpoint, ServicePeriodEffectiveDate,"
                + " DeductionAmount, InterestAmount, PaymentsApplied, TotalBalance, IsOfficial, PaymentOrder,"
                + " InterestAccrualDate FROM CalculatedServicePeriods");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Account parentAccoount = getAccount(resultSet.getString("SCMClaimNumber"));

                CalculationVersion version = new CalculationVersion();
                version.setVersion(resultSet.getInt("Version"));
                version.setLineNumber(resultSet.getInt("LineNumber"));

                Calculation calculation = new Calculation();
                calculation.setBeginDate(resultSet.getTimestamp("ServicePeriodStartDate"));
                calculation.setEndDate(resultSet.getTimestamp("ServicePeriodEndDate"));

                calculation.setRetirementType(getEntity(RetirementType.class,
                    getNewId(RetirementType.class, resultSet.getLong("SCMRetirementTypeCode"))));

                String periodTypeName = resultSet.getString("SCMPeriodTypeCode");
                PeriodType periodType = getEntityByName(PeriodType.class, periodTypeName);
                calculation.setPeriodType(periodType);

                calculation.setEnteredBy(resultSet.getLong("SCMEnteredBy"));

                version.setCalculationDate(resultSet.getTimestamp("LastUpdate"));
                version.setCalculations(new ArrayList<Calculation>());
                version.getCalculations().add(calculation);

                CalculationResultItem calculationResultItem = new CalculationResultItem();
                calculationResultItem.setStartDate(resultSet.getTimestamp("ServicePeriodStartDate"));
                calculationResultItem.setEndDate(resultSet.getTimestamp("ServicePeriodEndDate"));
                calculationResultItem.setMidDate(resultSet.getTimestamp("ServicePeriodMidpoint"));
                calculationResultItem.setEffectiveDate(resultSet.getTimestamp("ServicePeriodEffectiveDate"));
                calculationResultItem.setDeductionAmount(resultSet.getBigDecimal("DeductionAmount"));
                calculationResultItem.setTotalInterest(resultSet.getBigDecimal("InterestAmount"));
                calculationResultItem.setPaymentsApplied(resultSet.getBigDecimal("PaymentsApplied"));
                calculationResultItem.setBalance(resultSet.getBigDecimal("TotalBalance"));
                calculationResultItem.setPeriodType(periodType);

                CalculationResult calculationResult = new CalculationResult();
                calculationResult.setItems(new ArrayList<CalculationResultItem>());
                calculationResult.getItems().add(calculationResultItem);
                calculationResult.setOfficial(resultSet.getBoolean("IsOfficial"));
                calculationResult.setPaymentOrder(resultSet.getInt("PaymentOrder"));
                calculationResult.setInterestAccrualDate(resultSet.getTimestamp("InterestAccrualDate"));
                version.setCalculationResult(calculationResult);

                if (redepositPeriodTypeName.equals(periodTypeName)) {
                    parentAccoount.getFersRedepositCalculationVersions().add(version);
                } else if (depositPeriodTypeName.equals(periodTypeName)) {
                    parentAccoount.getFersDepositCalculationVersions().add(version);
                }

                merge(parentAccoount);
            }
            printProgressMessage("CalculationVersion (CalculatedServicePeriods)", "CalculatedServicePeriods");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT SCMClaimNumber, AccountNoteText, AccountNoteDate, AccountNotePriority,"
                + " AccountNoteAuthorKey FROM AccountNote");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AccountNote note = new AccountNote();
                note.setAccountId(getAccount(resultSet.getString("SCMClaimNumber")).getId());
                note.setText(resultSet.getString("AccountNoteText"));
                note.setDate(resultSet.getTimestamp("AccountNoteDate"));
                note.setPriority(resultSet.getInt("AccountNotePriority"));

                note.setWriter(getEntity(User.class, getNewId(User.class, resultSet.getLong("AccountNoteAuthorKey")))
                    .getUsername());

                persist(note);
            }
            printProgressMessage("AccountNote", "AccountNote");
        } finally {
            closeStatement(signature, preparedStatement);
        }
        preparedStatement = connection
            .prepareStatement("SELECT SCMClaimNumber, UserKey, AssignmentDate FROM UserAccountAssignments");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserAccountAssignment assignment = new UserAccountAssignment();
                assignment.setAccount(getAccount(resultSet.getString("SCMClaimNumber")));
                assignment.setUser(getEntity(User.class, getNewId(User.class, resultSet.getLong("UserKey"))));
                assignment.setAssignmentDate(resultSet.getTimestamp("AssignmentDate"));

                persist(assignment);
            }
            printProgressMessage("UserAccountAssignment", "UserAccountAssignments");
        } finally {
            closeStatement(signature, preparedStatement);
        }
    }

    /**
     * Performs the payment related data migration. The method should read the existing data and store it in the new
     * database.
     *
     * @param signature
     *            the signature
     * @param connection
     *            the connection
     *
     * @throws SQLException
     *             if there is any problem accessing source database.
     * @throws IllegalStateException
     *             if entity manager has been closed
     * @throws PersistenceException
     *             if any error occurs when accessing destination persistence
     */
    private void migratePaymentData(String signature, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection
            .prepareStatement("SELECT PayTransactionKey, PayTransBatchNumber, PayTransBlockNumber,"
                + " PayTransSequenceNumber, SCMClaimnumber, SCMDateOfBirth, PayTransPaymentAmount,"
                + " PayTransTransactionDate, PayTransStatusCode, PayTransStatusDate, TechnicianUserKey,"
                + " PaymentAppliedOrderCode, PostFlag, UserInserted, ACHPayment, ResolvedSuspense,"
                + " HistoryPayment, Disapprove, GovRefund, Apply2GL FROM PaymentTransaction");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setTransactionKey(resultSet.getString("PayTransactionKey"));
                payment.setBatchNumber(resultSet.getString("PayTransBatchNumber"));
                payment.setBlockNumber(resultSet.getString("PayTransBlockNumber"));
                payment.setSequenceNumber(resultSet.getString("PayTransSequenceNumber"));
                payment.setClaimNumber(resultSet.getString("SCMClaimnumber"));
                payment.setClaimantBirthdate(resultSet.getTimestamp("SCMDateOfBirth"));
                payment.setAmount(resultSet.getBigDecimal("PayTransPaymentAmount"));
                payment.setTransactionDate(resultSet.getTimestamp("PayTransTransactionDate"));

                payment.setStatusCode(getEntity(PayTransStatusCode.class,
                    getNewId(PayTransStatusCode.class, resultSet.getLong("PayTransStatusCode"))));

                payment.setStatusDate(resultSet.getTimestamp("PayTransStatusDate"));

                payment.setApprovalUser(getEntity(User.class,
                    getNewId(User.class, resultSet.getLong("TechnicianUserKey"))).getUsername());

                long paymentsAppliedOrderCodeOldId = resultSet.getLong("PaymentAppliedOrderCode");
                long paymentsAppliedOrderCodeNewId = getNewId(PaymentsAppliedOrderCode.class,
                    paymentsAppliedOrderCodeOldId);
                PaymentsAppliedOrderCode paymentsAppliedOrderCode;
                if (paymentsAppliedOrderCodeNewId == 0) {
                    paymentsAppliedOrderCode = new PaymentsAppliedOrderCode();

                    persist(paymentsAppliedOrderCode);

                    saveNewId(PaymentsAppliedOrderCode.class, paymentsAppliedOrderCodeOldId,
                        paymentsAppliedOrderCodeNewId);
                } else {
                    paymentsAppliedOrderCode = getEntity(PaymentsAppliedOrderCode.class, paymentsAppliedOrderCodeNewId);
                }
                payment.setOrderCode(paymentsAppliedOrderCode);

                payment.setPostFlag(resultSet.getBoolean("PostFlag"));
                payment.setUserInserted(resultSet.getBoolean("UserInserted"));
                payment.setAch(resultSet.getBoolean("ACHPayment"));
                payment.setResolvedSuspense(resultSet.getBoolean("ResolvedSuspense"));
                payment.setHistoryPayment(resultSet.getBoolean("HistoryPayment"));
                payment.setDisapprove(resultSet.getBoolean("Disapprove"));
                payment.setGovRefund(resultSet.getBoolean("GovRefund"));
                payment.setApplyToGL(resultSet.getBoolean("Apply2GL"));

                persist(payment);

                Account account = getAccount(resultSet.getString("SCMClaimNumber"));
                if (account.getPaymentHistory() == null) {
                    account.setPaymentHistory(new ArrayList<Payment>());
                }
                account.getPaymentHistory().add(payment);

                merge(account);
            }
            printProgressMessage("Payment", "PaymentTransaction");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection.prepareStatement("SELECT PayTransactionKey, Note FROM PaymentTranactionNotes");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PaymentTransactionNote note = new PaymentTransactionNote();
                note.setPayTransactionKey(resultSet.getLong("PayTransactionKey"));
                note.setNote(resultSet.getString("Note"));

                persist(note);
            }
            printProgressMessage("ResultSet", "PaymentTranactionNotes");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT RefundTransactionKey, RefundAmount, SCMClaimnumber, RefundTransactionDate,"
                + " TechnicianUserKey FROM RefundTransaction");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RefundTransaction transaction = new RefundTransaction();

                transaction.setTransactionKey(resultSet.getString("RefundTransactionKey"));
                transaction.setAmount(resultSet.getBigDecimal("RefundAmount"));
                transaction.setClaimNumber(resultSet.getString("SCMClaimnumber"));
                transaction.setRefundDate(resultSet.getTimestamp("RefundTransactionDate"));
                transaction.setRefundUsername(getEntity(User.class,
                    getNewId(User.class, resultSet.getLong("TechnicianUserKey"))).getUsername());

                persist(transaction);
            }
            printProgressMessage("RefundTransaction", "RefundTransaction");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT PaymentNeedingRefund, RefundForPayment FROM PaymentRefundLinks");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PaymentRefundLink link = new PaymentRefundLink();
                link.setPaymentNeedingRefund(resultSet.getLong("PaymentNeedingRefund"));
                link.setRefundForPayment(resultSet.getLong("RefundForPayment"));

                persist(link);
            }
            printProgressMessage("PaymentRefundLink", "PaymentRefundLinks");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT InvoiceID, PayTransactionKey, SCMDeposit, SCMRedeposit, SCMTotVarRedeposit,"
                + " SCMNonDed, SCMFersW, SCMAccIntDep, SCMAccIntRdep, SCMAccIntNonDed, SCMAccIntVarRdep,"
                + " SCMAccIntFers, SCMTotPayd, SCMTotPayr, SCMTotPayn, SCMTotPayvr, SCMTotPayfers, LastPay,"
                + " CalcDate, NextInvoiceID FROM Invoices");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setPayTransactionKey(resultSet.getLong("PayTransactionKey"));
                invoice.setDeposit(resultSet.getBigDecimal("SCMDeposit"));
                invoice.setRedeposit(resultSet.getBigDecimal("SCMRedeposit"));
                invoice.setTotVarRedeposit(resultSet.getBigDecimal("SCMTotVarRedeposit"));
                invoice.setNonDed(resultSet.getBigDecimal("SCMNonDed"));
                invoice.setFersW(resultSet.getBigDecimal("SCMFersW"));
                invoice.setAccIntDep(resultSet.getBigDecimal("SCMAccIntDep"));
                invoice.setAccIntRdep(resultSet.getBigDecimal("SCMAccIntRdep"));
                invoice.setAccIntNonDep(resultSet.getBigDecimal("SCMAccIntNonDed"));
                invoice.setAccIntVarRdep(resultSet.getBigDecimal("SCMAccIntVarRdep"));
                invoice.setAccIntFers(resultSet.getBigDecimal("SCMAccIntFers"));
                invoice.setTotPayd(resultSet.getBigDecimal("SCMTotPayd"));
                invoice.setTotPayr(resultSet.getBigDecimal("SCMTotPayr"));
                invoice.setTotPayn(resultSet.getBigDecimal("SCMTotPayn"));
                invoice.setTotPayvr(resultSet.getBigDecimal("SCMTotPayvr"));
                invoice.setTotPayfers(resultSet.getBigDecimal("SCMTotPayfers"));
                invoice.setLastPay(resultSet.getTimestamp("LastPay"));
                invoice.setCalcDate(resultSet.getTimestamp("CalcDate"));
                invoice.setLastInvoiceId(resultSet.getLong("NextInvoiceID"));

                persist(invoice);
            }
            printProgressMessage("Invoice", "Invoices");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT PayTransactionKey, AccountType, NumWholeYears, CalculatedInterest,"
                + " LastPayToEOYFactor, PartialToThisPayFactor, ThisInterestRate, LastPaymentDate,"
                + " TransactionDate, ComputedDate, Post, GUI, LastPaymentWasThisYear FROM PaymentInterestDetails");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PaymentInterestDetail detail = new PaymentInterestDetail();
                detail.setPayTransactionKey(resultSet.getLong("PayTransactionKey"));
                detail.setAccountType(resultSet.getLong("AccountType"));
                detail.setNumWholeYears(resultSet.getInt("NumWholeYears"));
                detail.setCalculatedInterest(resultSet.getBigDecimal("CalculatedInterest"));
                detail.setLastPayToEOYFactor(resultSet.getBigDecimal("LastPayToEOYFactor"));
                detail.setPartialToThisPayFactor(resultSet.getBigDecimal("PartialToThisPayFactor"));
                detail.setThisInterestRate(resultSet.getBigDecimal("ThisInterestRate"));
                detail.setLastPaymentDate(resultSet.getTimestamp("LastPaymentDate"));
                detail.setTransactionDate(resultSet.getTimestamp("TransactionDate"));
                detail.setComputedDate(resultSet.getTimestamp("ComputedDate"));
                detail.setPost(resultSet.getBoolean("Post"));
                detail.setGui(resultSet.getBoolean("GUI"));
                detail.setLastPaymentWasThisYear(resultSet.getBoolean("LastPaymentWasThisYear"));

                persist(detail);
            }
            printProgressMessage("PaymentInterestDetail", "PaymentInterestDetails");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT TransactionKey, SCMClaimnumber, SCMAccIntDep, SCMAccIntRdep,"
                + " SCMAccIntNonDed, SCMAccIntVarRdep, SCMAccIntFers, ModificationDate, ApprovalDate, "
                + "ProcessedDate, TechnicianUserKey, ManagerUserKey, Approved, Disapproved, Modified, Note"
                + " FROM AdjustmentTransaction");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AdjustmentTransaction transaction = new AdjustmentTransaction();
                transaction.setPayTransactionKey(resultSet.getLong("TransactionKey"));
                transaction.setClaimNumber(resultSet.getString("SCMClaimnumber"));
                transaction.setAccIntDep(resultSet.getBigDecimal("SCMAccIntDep"));
                transaction.setAccIntRdep(resultSet.getBigDecimal("SCMAccIntRdep"));
                transaction.setAccIntNonDep(resultSet.getBigDecimal("SCMAccIntNonDed"));
                transaction.setAccIntVarRdep(resultSet.getBigDecimal("SCMAccIntVarRdep"));
                transaction.setAccIntDepFers(resultSet.getBigDecimal("SCMAccIntFers"));
                transaction.setModificationDate(resultSet.getTimestamp("ModificationDate"));
                transaction.setApprovalDate(resultSet.getTimestamp("ApprovalDate"));
                transaction.setProcessedDate(resultSet.getTimestamp("ProcessedDate"));
                transaction.setTechnicianUserKey(resultSet.getLong("TechnicianUserKey"));
                transaction.setManagerUserKey(resultSet.getLong("ManagerUserKey"));
                transaction.setApproved(resultSet.getBoolean("Approved"));
                transaction.setDisapproved(resultSet.getBoolean("Disapproved"));
                transaction.setModified(resultSet.getBoolean("Modified"));
                transaction.setNote(resultSet.getString("Note"));

                persist(transaction);
            }
            printProgressMessage("AdjustmentTransaction", "AdjustmentTransaction");
        } finally {
            closeStatement(signature, preparedStatement);
        }

        preparedStatement = connection
            .prepareStatement("SELECT TransactionKey, SCMClaimnumber, SCMTotPayd, SCMTotPayr, SCMTotPayn,"
                + " SCMTotPayvr, SCMTotPayfers, ModificationDate, ApprovalDate, ProcessedDate,"
                + " TechnicianUserKey, ManagerUserKey, Approved, Disapproved, Modified, Note"
                + " FROM PaymentMoveTransaction");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PaymentMoveTransaction transaction = new PaymentMoveTransaction();
                transaction.setPayTransactionKey(resultSet.getLong("TransactionKey"));
                transaction.setClaimNumber(resultSet.getString("SCMClaimnumber"));
                transaction.setTotPayd(resultSet.getBigDecimal("SCMTotPayd"));
                transaction.setTotPayr(resultSet.getBigDecimal("SCMTotPayr"));
                transaction.setTotPayn(resultSet.getBigDecimal("SCMTotPayn"));
                transaction.setTotPayvr(resultSet.getBigDecimal("SCMTotPayvr"));
                transaction.setTotPayfers(resultSet.getBigDecimal("SCMTotPayfers"));
                transaction.setModificationDate(resultSet.getTimestamp("ModificationDate"));
                transaction.setApprovalDate(resultSet.getTimestamp("ApprovalDate"));
                transaction.setProcessedDate(resultSet.getTimestamp("ProcessedDate"));
                transaction.setTechnicianUserKey(resultSet.getLong("TechnicianUserKey"));
                transaction.setManagerUserKey(resultSet.getLong("ManagerUserKey"));
                transaction.setApproved(resultSet.getBoolean("Approved"));
                transaction.setDisapproved(resultSet.getBoolean("Disapproved"));
                transaction.setModified(resultSet.getBoolean("Modified"));
                transaction.setNote(resultSet.getString("Note"));

                persist(transaction);
            }
            printProgressMessage("PaymentMoveTransaction", "PaymentMoveTransaction");
        } finally {
            closeStatement(signature, preparedStatement);
        }
    }

    /**
     * Print the progress message.
     *
     * @param entity
     *            the entity name
     * @param table
     *            the table
     */
    private void printProgressMessage(String entity, String table) {
        int count = countMapping.get(table);
        System.out.println("Currently processed entity: " + entity + ", records count: " + count);

        int processedCount = countMapping.get("processedCount");
        processedCount += count;
        countMapping.put("processedCount", processedCount);

        System.out.println("Progress (processed records/total records): " + processedCount + "/"
            + countMapping.get("totalCount"));
    }

    /**
     * Gets record count of the table.
     *
     * @param signature
     *            the signature
     * @param connection
     *            the connection
     * @param table
     *            the table
     *
     * @return the record count of the table.
     *
     * @throws SQLException
     *             if any error occurs
     */
    private int getCount(String signature, Connection connection, String table) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_RECORD_COUNT + table);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return (int) resultSet.getLong(1);
        } finally {
            closeStatement(signature, preparedStatement);
        }
    }

    /**
     * Establishes the connection to the source database.
     *
     * @return Connection to the source database, can not be null.
     *
     * @throws ClassNotFoundException
     *             if the driver cannot be located
     * @throws SQLException
     *             if a database access error occurs
     */
    private Connection obtainConnection() throws ClassNotFoundException, SQLException {
        Class.forName(sourceDatabaseDriverName);
        return DriverManager.getConnection(sourceDatabaseUrl, sourceDatabaseUsername, sourceDatabasePassword);
    }

    /**
     * Merges the object.
     *
     * @param obj
     *            the object
     *
     * @throws IllegalStateException
     *             if entity manager has been closed
     * @throws PersistenceException
     *             if any error occurs when accessing persistence
     */
    private void merge(Object obj) {
        destinationEntityManager.getTransaction().begin();
        destinationEntityManager.merge(obj);
        destinationEntityManager.getTransaction().commit();
    }

    /**
     * Persists the object.
     *
     * @param obj
     *            the object
     *
     * @throws IllegalStateException
     *             if entity manager has been closed
     * @throws PersistenceException
     *             if any error occurs when accessing persistence
     */
    private void persist(Object obj) {
        destinationEntityManager.getTransaction().begin();
        destinationEntityManager.persist(obj);
        destinationEntityManager.getTransaction().commit();
        destinationEntityManager.refresh(obj);
    }

    /**
     * Gets the country.
     *
     * @return the account
     *
     * @throws IllegalStateException
     *             if entity manager has been closed
     * @throws PersistenceException
     *             if any error occurs when accessing persistence
     */
    private Country getCountry() {
        List<Country> list = destinationEntityManager
            .createQuery("SELECT e FROM Country e WHERE e.name=:name", Country.class)
            .setParameter("name", "US")
            .getResultList();
        Country country;
        if (list.isEmpty()) {
            country = new Country();
            country.setName(countryName);

            persist(country);
        } else {
            country = list.get(0);
        }

        return country;
    }

    /**
     * Gets the account by claim number.
     *
     * @param claimNumber
     *            the claim number
     *
     * @return the account
     *
     * @throws IllegalStateException
     *             if entity manager has been closed
     * @throws PersistenceException
     *             if any error occurs when accessing persistence
     */
    private Account getAccount(String claimNumber) {
        return destinationEntityManager
            .createQuery("SELECT a FROM Account a WHERE a.claimNumber=:claimNumber", Account.class)
            .setParameter("claimNumber", claimNumber).getSingleResult();
    }

    /**
     * Gets the state by abbreviation.
     *
     * @param abbreviation
     *            the abbreviation
     *
     * @return the state
     *
     * @throws IllegalStateException
     *             if entity manager has been closed
     * @throws PersistenceException
     *             if any error occurs when accessing persistence
     */
    private State getState(String abbreviation) {
        return destinationEntityManager
            .createQuery("SELECT e FROM State e WHERE e.abbreviation=:abbreviation", State.class)
            .setParameter("abbreviation", abbreviation).getSingleResult();
    }

    /**
     * Gets the entity by name.
     *
     * @param <T>
     *            the entity type
     * @param type
     *            the entity type class
     * @param name
     *            the name
     *
     * @return the entity
     *
     * @throws IllegalStateException
     *             if entity manager has been closed
     * @throws PersistenceException
     *             if any error occurs when accessing persistence
     */
    private <T> T getEntityByName(Class<T> type, String name) {
        return destinationEntityManager
            .createQuery("SELECT e FROM " + type.getSimpleName() + " e WHERE e.name=:name", type)
            .setParameter("name", name).getSingleResult();
    }

    /**
     * Gets the entity.
     *
     * @param <T>
     *            the entity type
     * @param type
     *            the entity type class
     * @param id
     *            the id
     *
     * @return the entity
     *
     * @throws IllegalStateException
     *             if entity manager has been closed
     * @throws PersistenceException
     *             if any error occurs when accessing persistence
     */
    private <T> T getEntity(Class<T> type, long id) {
        return destinationEntityManager.find(type, id);
    }

    /**
     * Closes the statement.
     *
     * @param signature
     *            the signature
     * @param statement
     *            the statement
     */
    private void closeStatement(String signature, Statement statement) {
        try {
            // Close the statement
            statement.close();
        } catch (SQLException e) {
            // Ignore the exception
            LoggingHelper.logException(logger, signature, e);
        }
    }

    /**
     * Saves new id.
     *
     * @param type
     *            the type
     * @param oldId
     *            the old id
     * @param newId
     *            the new id.
     */
    private void saveNewId(Class<?> type, long oldId, long newId) {
        Map<Long, Long> map = idMapping.get(type);
        if (map == null) {
            map = new HashMap<Long, Long>();
            idMapping.put(type, map);
        }
        map.put(oldId, newId);
    }

    /**
     * Gets new id.
     *
     * @param type
     *            the type
     * @param oldId
     *            the old id
     *
     * @return the new id.
     */
    private long getNewId(Class<?> type, long oldId) {
        Map<Long, Long> map = idMapping.get(type);
        if (map != null) {
            Long newId = map.get(oldId);
            if (newId != null) {
                return newId;
            }
        }

        return 0;
    }

    /**
     * <p>
     * Validates the value of a string.
     * </p>
     *
     * @param value
     *            the value of the variable to be validated.
     *
     * @return <code>true</code> if value is <code>null</code> or empty
     */
    private static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }
}
