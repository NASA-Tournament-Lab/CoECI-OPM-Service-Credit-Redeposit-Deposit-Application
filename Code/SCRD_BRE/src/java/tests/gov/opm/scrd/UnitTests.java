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

import gov.opm.scrd.entities.application.A01PrintSuppressionCasesUnitTests;
import gov.opm.scrd.entities.application.AccountConfirmationValidationEntryUnitTests;
import gov.opm.scrd.entities.application.AccountConfirmationValidationUnitTests;
import gov.opm.scrd.entities.application.AccountHolderUnitTests;
import gov.opm.scrd.entities.application.AccountNoteUnitTests;
import gov.opm.scrd.entities.application.AccountSearchFilterUnitTests;
import gov.opm.scrd.entities.application.AccountUnitTests;
import gov.opm.scrd.entities.application.AddressUnitTests;
import gov.opm.scrd.entities.application.AdjustmentTransactionUnitTests;
import gov.opm.scrd.entities.application.AnnuitantListUnitTests;
import gov.opm.scrd.entities.application.ApprovalItemSummaryUnitTests;
import gov.opm.scrd.entities.application.AuditBatchUnitTests;
import gov.opm.scrd.entities.application.AuditParameterRecordUnitTests;
import gov.opm.scrd.entities.application.AuditRecordUnitTests;
import gov.opm.scrd.entities.application.BatchDailyPaymentsUnitTests;
import gov.opm.scrd.entities.application.BillingSummaryUnitTests;
import gov.opm.scrd.entities.application.BillingUnitTests;
import gov.opm.scrd.entities.application.CalculationResultItemUnitTests;
import gov.opm.scrd.entities.application.CalculationResultUnitTests;
import gov.opm.scrd.entities.application.CalculationUnitTests;
import gov.opm.scrd.entities.application.CalculationVersionUnitTests;
import gov.opm.scrd.entities.application.ClaimWithoutServiceUnitTests;
import gov.opm.scrd.entities.application.ContactInfoUnitTests;
import gov.opm.scrd.entities.application.DedepositUnitTests;
import gov.opm.scrd.entities.application.DeductionRateUnitTests;
import gov.opm.scrd.entities.application.ErrorUnitTests;
import gov.opm.scrd.entities.application.GLCodeUnitTests;
import gov.opm.scrd.entities.application.GLPaymentTypeUnitTests;
import gov.opm.scrd.entities.application.HelpItemUnitTests;
import gov.opm.scrd.entities.application.HolidayUnitTests;
import gov.opm.scrd.entities.application.InfoUnitTests;
import gov.opm.scrd.entities.application.InterestAdjustmentUnitTests;
import gov.opm.scrd.entities.application.InterestGracePeriodUnitTests;
import gov.opm.scrd.entities.application.InterestRateUnitTests;
import gov.opm.scrd.entities.application.InterestSuppressionUnitTests;
import gov.opm.scrd.entities.application.InvoiceUnitTests;
import gov.opm.scrd.entities.application.NewClaimNumberUnitTests;
import gov.opm.scrd.entities.application.NotificationSearchFilterUnitTests;
import gov.opm.scrd.entities.application.NotificationUnitTests;
import gov.opm.scrd.entities.application.PayTransStatusCodeUnitTests;
import gov.opm.scrd.entities.application.PaymentInterestDetailUnitTests;
import gov.opm.scrd.entities.application.PaymentMoveTransactionUnitTests;
import gov.opm.scrd.entities.application.PaymentMoveUnitTests;
import gov.opm.scrd.entities.application.PaymentRefundLinkUnitTests;
import gov.opm.scrd.entities.application.PaymentReverseUnitTests;
import gov.opm.scrd.entities.application.PaymentSearchFilterUnitTests;
import gov.opm.scrd.entities.application.PaymentTransactionNoteUnitTests;
import gov.opm.scrd.entities.application.PaymentUnitTests;
import gov.opm.scrd.entities.application.PaymentsAppliedOrderCodeUnitTests;
import gov.opm.scrd.entities.application.PendingPaymentUnitTests;
import gov.opm.scrd.entities.application.PrintoutUnitTests;
import gov.opm.scrd.entities.application.RedepositUnitTests;
import gov.opm.scrd.entities.application.RefundTransactionUnitTests;
import gov.opm.scrd.entities.application.RolePermissionUnitTests;
import gov.opm.scrd.entities.application.SCMFirstInsertUnitTests;
import gov.opm.scrd.entities.application.ServiceAnnouncementUnitTests;
import gov.opm.scrd.entities.application.ServiceCreditPreferenceUnitTests;
import gov.opm.scrd.entities.application.SummaryDataUnitTests;
import gov.opm.scrd.entities.application.SuspendedPaymentUnitTests;
import gov.opm.scrd.entities.application.TimeFactorUnitTests;
import gov.opm.scrd.entities.application.UserAccountAssignmentUnitTests;
import gov.opm.scrd.entities.application.UserPermissionUnitTests;
import gov.opm.scrd.entities.application.UserUnitTests;
import gov.opm.scrd.entities.common.BasePagedSearchParametersUnitTests;
import gov.opm.scrd.entities.common.BaseSearchParametersUnitTests;
import gov.opm.scrd.entities.common.BasicPagedSearchFilterUnitTests;
import gov.opm.scrd.entities.common.BasicSearchFilterUnitTests;
import gov.opm.scrd.entities.common.DescriptiveNamedEntityUnitTests;
import gov.opm.scrd.entities.common.IdentifiableEntityUnitTests;
import gov.opm.scrd.entities.common.NamedEntityUnitTests;
import gov.opm.scrd.entities.common.OrderedNamedEntityUnitTests;
import gov.opm.scrd.entities.common.SearchResultUnitTests;
import gov.opm.scrd.entities.lookup.AccountStatusUnitTests;
import gov.opm.scrd.entities.lookup.AgencyCodeUnitTests;
import gov.opm.scrd.entities.lookup.ApplicationDesignationUnitTests;
import gov.opm.scrd.entities.lookup.AppointmentTypeUnitTests;
import gov.opm.scrd.entities.lookup.CalculationStatusUnitTests;
import gov.opm.scrd.entities.lookup.ChangeTransFieldNumberCodeUnitTests;
import gov.opm.scrd.entities.lookup.ClaimOfficerUnitTests;
import gov.opm.scrd.entities.lookup.CountryUnitTests;
import gov.opm.scrd.entities.lookup.FormTypeUnitTests;
import gov.opm.scrd.entities.lookup.PayCodeUnitTests;
import gov.opm.scrd.entities.lookup.PayTypeUnitTests;
import gov.opm.scrd.entities.lookup.PaymentAppliedOrderUnitTests;
import gov.opm.scrd.entities.lookup.PaymentReversalReasonUnitTests;
import gov.opm.scrd.entities.lookup.PaymentStatusUnitTests;
import gov.opm.scrd.entities.lookup.PeriodTypeUnitTests;
import gov.opm.scrd.entities.lookup.RetirementTypeUnitTests;
import gov.opm.scrd.entities.lookup.RoleUnitTests;
import gov.opm.scrd.entities.lookup.ServiceTypeUnitTests;
import gov.opm.scrd.entities.lookup.StateUnitTests;
import gov.opm.scrd.entities.lookup.SuffixUnitTests;
import gov.opm.scrd.entities.lookup.TransferTypeUnitTests;
import gov.opm.scrd.entities.lookup.UserStatusUnitTests;
import gov.opm.scrd.services.AuthorizationExceptionUnitTests;
import gov.opm.scrd.services.EntityNotFoundExceptionUnitTests;
import gov.opm.scrd.services.OPMConfigurationExceptionUnitTests;
import gov.opm.scrd.services.OPMExceptionUnitTests;
import gov.opm.scrd.services.impl.AccountServiceImplUnitTests;
import gov.opm.scrd.services.impl.ApprovalServiceImplUnitTests;
import gov.opm.scrd.services.impl.AuditServiceImplUnitTests;
import gov.opm.scrd.services.impl.BatchPrintoutArchiveServiceImplUnitTests;
import gov.opm.scrd.services.impl.HelpServiceImplUnitTests;
import gov.opm.scrd.services.impl.LookupServiceImplUnitTests;
import gov.opm.scrd.services.impl.PaymentServiceImplUnitTests;
import gov.opm.scrd.services.impl.SecurityServiceImplUnitTests;
import gov.opm.scrd.services.impl.ServiceAnnouncementServiceImplUnitTests;
import gov.opm.scrd.services.impl.ServiceCreditPreferenceServiceImplUnitTests;
import gov.opm.scrd.services.impl.SuspensionServiceImplUnitTests;
import gov.opm.scrd.services.impl.UserServiceImplUnitTests;
import gov.opm.scrd.services.impl.reporting.AccountBalanceReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.AccountStatisticsReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.AccountSummaryReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.ActiveCreditBalancesReportServiceTests;
import gov.opm.scrd.services.impl.reporting.BalancedLockboxReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.BalancedScorecardAccountReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.BalancedScorecardPaymentReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.CalculationAuditTrailReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.ChangeHistoryReportServiceTests;
import gov.opm.scrd.services.impl.reporting.ClosedAccountReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.CurrentSuspenseReportServiceTests;
import gov.opm.scrd.services.impl.reporting.DailyBatchProcessingReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.DailyCashflowReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.DailyReconciliationReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.DailySuspenseReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.DeductionRatesReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.GeneralLedgerReportServiceTests;
import gov.opm.scrd.services.impl.reporting.LockboxFileImportReportServiceTests;
import gov.opm.scrd.services.impl.reporting.LockboxImportErrorsReportServiceTests;
import gov.opm.scrd.services.impl.reporting.ManualPaymentReportServiceTests;
import gov.opm.scrd.services.impl.reporting.MonthlyAdjustmentReportServiceTests;
import gov.opm.scrd.services.impl.reporting.MonthlySuspenseListReportServiceTests;
import gov.opm.scrd.services.impl.reporting.PaymentByTypeRangeReportServiceTests;
import gov.opm.scrd.services.impl.reporting.PaymentHistoryReportServiceTests;
import gov.opm.scrd.services.impl.reporting.PaymentPendingApprovalReportServiceTests;
import gov.opm.scrd.services.impl.reporting.RefundSectionAccountAssignmentsReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.ResolvedSuspenseHistoryReportServiceTests;
import gov.opm.scrd.services.impl.reporting.SuspenseResolutionReportServiceTests;
import gov.opm.scrd.services.impl.reporting.TotalPaymentSummaryReportServiceTests;
import gov.opm.scrd.services.impl.reporting.TransactionRegisterNewReceiptsReportServiceTests;
import gov.opm.scrd.services.impl.reporting.UserAuditTrailReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.UserPermissionsRolesReportServiceUnitTests;
import gov.opm.scrd.services.impl.reporting.UserRolePermissionsReportServiceUnitTests;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * <p>
 * This test case aggregates all Unit test cases.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Services - Account and Payment Services Assembly 1.0):</em>
 * <ol>
 * <li>Added new tests.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ol>
 * <li>Added new tests.</li>
 * <li>Removed service tests.</li>
 * </ol>
 * </p>
 *
 * @author sparemax, TCSASSEMBLER
 * @version 1.2
 */
public class UnitTests extends TestCase {
    /**
     * <p>
     * All unit test cases.
     * </p>
     *
     * @return The test suite.
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite();

//        // Add service tests
//        addServiceTests(suite);
//
//        suite.addTest(PersistenceTests.suite());
//
//        // Added in OPM - Data Migration - Entities Update Module Assembly 1.0
//        suite.addTest(DescriptiveNamedEntityUnitTests.suite());
//        suite.addTest(OrderedNamedEntityUnitTests.suite());
//
//        suite.addTest(IdentifiableEntityUnitTests.suite());
//        suite.addTest(NamedEntityUnitTests.suite());
//        suite.addTest(BaseSearchParametersUnitTests.suite());
//        suite.addTest(BasicSearchFilterUnitTests.suite());
//        suite.addTest(BasePagedSearchParametersUnitTests.suite());
//        suite.addTest(BasicPagedSearchFilterUnitTests.suite());
//        suite.addTest(SearchResultUnitTests.suite());
//
//        // Add application tests
//        addApplicationTests(suite);
//
//        // Add lookup tests
//        addLookupTests(suite);
        
        // add reporting tests
        addReportTests(suite);
        // Exceptions
        
//        suite.addTest(OPMExceptionUnitTests.suite());
//        suite.addTest(AuthorizationExceptionUnitTests.suite());
//        suite.addTest(EntityNotFoundExceptionUnitTests.suite());
//        suite.addTest(OPMConfigurationExceptionUnitTests.suite());
        
        return suite;
    }

    /**
     * Adds the service tests to test suite.
     *
     * @param suite
     *            the test suite.
     */
    private static void addServiceTests(TestSuite suite) {
        suite.addTest(PaymentServiceImplUnitTests.suite());
        suite.addTest(ServiceAnnouncementServiceImplUnitTests.suite());
        suite.addTest(SuspensionServiceImplUnitTests.suite());
        suite.addTest(ApprovalServiceImplUnitTests.suite());
        suite.addTest(AccountServiceImplUnitTests.suite());
        //suite.addTest(CalculationExecutionServiceImplUnitTests.suite());

        suite.addTest(AuditServiceImplUnitTests.suite());
        suite.addTest(LookupServiceImplUnitTests.suite());
        suite.addTest(BatchPrintoutArchiveServiceImplUnitTests.suite());
        suite.addTest(ServiceCreditPreferenceServiceImplUnitTests.suite());
        suite.addTest(HelpServiceImplUnitTests.suite());
        suite.addTest(UserServiceImplUnitTests.suite());
        suite.addTest(SecurityServiceImplUnitTests.suite());
    }

    /**
     * Adds the application tests to test suite.
     *
     * @param suite
     *            the test suite.
     */
    private static void addApplicationTests(TestSuite suite) {
        // Added in OPM - Data Migration - Entities Update Module Assembly 1.0
        suite.addTest(PaymentsAppliedOrderCodeUnitTests.suite());
        suite.addTest(A01PrintSuppressionCasesUnitTests.suite());
        suite.addTest(AdjustmentTransactionUnitTests.suite());
        suite.addTest(AnnuitantListUnitTests.suite());
        suite.addTest(AuditBatchUnitTests.suite());
        suite.addTest(BatchDailyPaymentsUnitTests.suite());
        suite.addTest(ClaimWithoutServiceUnitTests.suite());
        suite.addTest(ContactInfoUnitTests.suite());
        suite.addTest(DeductionRateUnitTests.suite());
        suite.addTest(GLCodeUnitTests.suite());
        suite.addTest(GLPaymentTypeUnitTests.suite());
        suite.addTest(HolidayUnitTests.suite());
        suite.addTest(InterestGracePeriodUnitTests.suite());
        suite.addTest(InterestRateUnitTests.suite());
        suite.addTest(InterestSuppressionUnitTests.suite());
        suite.addTest(InvoiceUnitTests.suite());
        suite.addTest(NewClaimNumberUnitTests.suite());
        suite.addTest(PaymentInterestDetailUnitTests.suite());
        suite.addTest(PaymentMoveTransactionUnitTests.suite());
        suite.addTest(PaymentRefundLinkUnitTests.suite());
        suite.addTest(PaymentTransactionNoteUnitTests.suite());
        suite.addTest(PayTransStatusCodeUnitTests.suite());
        suite.addTest(SCMFirstInsertUnitTests.suite());
        suite.addTest(TimeFactorUnitTests.suite());
        suite.addTest(UserAccountAssignmentUnitTests.suite());

        suite.addTest(AccountConfirmationValidationEntryUnitTests.suite());
        suite.addTest(AccountConfirmationValidationUnitTests.suite());
        suite.addTest(AccountHolderUnitTests.suite());
        suite.addTest(AccountNoteUnitTests.suite());
        suite.addTest(AccountUnitTests.suite());
        suite.addTest(AddressUnitTests.suite());
        suite.addTest(AuditParameterRecordUnitTests.suite());
        suite.addTest(AuditRecordUnitTests.suite());
        suite.addTest(BillingSummaryUnitTests.suite());
        suite.addTest(BillingUnitTests.suite());
        suite.addTest(CalculationResultItemUnitTests.suite());
        suite.addTest(CalculationResultUnitTests.suite());
        suite.addTest(CalculationUnitTests.suite());
        suite.addTest(CalculationVersionUnitTests.suite());
        suite.addTest(DedepositUnitTests.suite());
        suite.addTest(ErrorUnitTests.suite());
        suite.addTest(HelpItemUnitTests.suite());
        suite.addTest(InfoUnitTests.suite());
        suite.addTest(NotificationUnitTests.suite());
        suite.addTest(PaymentReverseUnitTests.suite());
        suite.addTest(PaymentUnitTests.suite());
        suite.addTest(PrintoutUnitTests.suite());
        suite.addTest(RedepositUnitTests.suite());
        suite.addTest(RefundTransactionUnitTests.suite());
        suite.addTest(RolePermissionUnitTests.suite());
        suite.addTest(ServiceAnnouncementUnitTests.suite());
        suite.addTest(ServiceCreditPreferenceUnitTests.suite());
        suite.addTest(SummaryDataUnitTests.suite());
        suite.addTest(UserPermissionUnitTests.suite());
        suite.addTest(UserUnitTests.suite());

        suite.addTest(AccountSearchFilterUnitTests.suite());
        suite.addTest(PaymentSearchFilterUnitTests.suite());
        suite.addTest(NotificationSearchFilterUnitTests.suite());

        suite.addTest(ApprovalItemSummaryUnitTests.suite());

        suite.addTest(InterestAdjustmentUnitTests.suite());
        suite.addTest(PaymentMoveUnitTests.suite());
        suite.addTest(PendingPaymentUnitTests.suite());
        suite.addTest(SuspendedPaymentUnitTests.suite());
    }

    /**
     * Adds the lookup tests to test suite.
     *
     * @param suite
     *            the test suite.
     */
    private static void addLookupTests(TestSuite suite) {
        // Added in OPM - Data Migration - Entities Update Module Assembly 1.0
        suite.addTest(AgencyCodeUnitTests.suite());
        suite.addTest(ChangeTransFieldNumberCodeUnitTests.suite());
        suite.addTest(PayCodeUnitTests.suite());
        suite.addTest(PaymentAppliedOrderUnitTests.suite());

        suite.addTest(AccountStatusUnitTests.suite());
        suite.addTest(ApplicationDesignationUnitTests.suite());
        suite.addTest(AppointmentTypeUnitTests.suite());
        suite.addTest(CalculationStatusUnitTests.suite());
        suite.addTest(ClaimOfficerUnitTests.suite());
        suite.addTest(CountryUnitTests.suite());
        suite.addTest(FormTypeUnitTests.suite());
        suite.addTest(PaymentReversalReasonUnitTests.suite());
        suite.addTest(PaymentStatusUnitTests.suite());
        suite.addTest(PayTypeUnitTests.suite());
        suite.addTest(PeriodTypeUnitTests.suite());
        suite.addTest(RetirementTypeUnitTests.suite());
        suite.addTest(RoleUnitTests.suite());
        suite.addTest(ServiceTypeUnitTests.suite());
        suite.addTest(StateUnitTests.suite());
        suite.addTest(SuffixUnitTests.suite());
        suite.addTest(TransferTypeUnitTests.suite());
        suite.addTest(UserStatusUnitTests.suite());
    }

    
    /**
     * Adds the reporting unit tess.
     * @param suite the test suite.
     */
    private static void addReportTests(TestSuite suite) {
//        // lock box reporting
//        suite.addTest(ActiveCreditBalancesReportServiceTests.suite());
//        suite.addTest(GeneralLedgerReportServiceTests.suite());
//        suite.addTest(TransactionRegisterNewReceiptsReportServiceTests.suite());
//        suite.addTest(LockboxImportErrorsReportServiceTests.suite());
//        suite.addTest(LockboxFileImportReportServiceTests.suite());
//        suite.addTest(MonthlyAdjustmentReportServiceTests.suite());
//        suite.addTest(ChangeHistoryReportServiceTests.suite());
//        suite.addTest(SuspenseResolutionReportServiceTests.suite());
//
//        // payment reporing
//        suite.addTest(CurrentSuspenseReportServiceTests.suite());
//        suite.addTest(TotalPaymentSummaryReportServiceTests.suite());
//        suite.addTest(ResolvedSuspenseHistoryReportServiceTests.suite());
//        suite.addTest(ManualPaymentReportServiceTests.suite());
//        suite.addTest(PaymentHistoryReportServiceTests.suite());
//        suite.addTest(PaymentByTypeRangeReportServiceTests.suite());
//        suite.addTest(PaymentPendingApprovalReportServiceTests.suite());
//        suite.addTest(MonthlySuspenseListReportServiceTests.suite());
//        
//        // daily reporting
//        suite.addTest(BalancedLockboxReportServiceUnitTests.suite());
//        suite.addTest(BalancedScorecardAccountReportServiceUnitTests.suite());
//        suite.addTest(BalancedScorecardPaymentReportServiceUnitTests.suite());
//        suite.addTest(DailyBatchProcessingReportServiceUnitTests.suite());
//        suite.addTest(DailyCashflowReportServiceUnitTests.suite());
//        suite.addTest(DailyReconciliationReportServiceUnitTests.suite());
//        suite.addTest(DailySuspenseReportServiceUnitTests.suite());
//        
//        // account reporting
//        suite.addTest(AccountBalanceReportServiceUnitTests.suite());
//        suite.addTest(AccountStatisticsReportServiceUnitTests.suite());
        suite.addTest(AccountSummaryReportServiceUnitTests.suite());
//        suite.addTest(CalculationAuditTrailReportServiceUnitTests.suite());
//        suite.addTest(ClosedAccountReportServiceUnitTests.suite());
//        suite.addTest(RefundSectionAccountAssignmentsReportServiceUnitTests.suite());
//        suite.addTest(UserAuditTrailReportServiceUnitTests.suite());
//        suite.addTest(UserPermissionsRolesReportServiceUnitTests.suite());
//        suite.addTest(UserRolePermissionsReportServiceUnitTests.suite());
//        suite.addTest(DeductionRatesReportServiceUnitTests.suite());
//        suite.addTest(RefundSectionAccountAssignmentsReportServiceUnitTests.suite());
    }
}
