----------------------------------------------------
--      retirement_type table
----------------------------------------------------
INSERT INTO opm.retirement_type(id, deleted, name) VALUES(1, false, 'FERS');
INSERT INTO opm.retirement_type(id, deleted, name) VALUES(2, false, 'CSRS');

ALTER SEQUENCE opm.retirement_type_id_seq RESTART WITH 1000;

----------------------------------------------------
-- 			form_type table
----------------------------------------------------
INSERT INTO opm.form_type(id, deleted, name) VALUES(1, false, '3108(FERS)');
INSERT INTO opm.form_type(id, deleted, name) VALUES(2, false, '2803(CSRS)');
ALTER SEQUENCE opm.form_type_id_seq RESTART WITH 1000;

----------------------------------------------------
--      gl_code table
----------------------------------------------------
INSERT INTO opm.gl_code(id, deleted, name, code, payment_type, post_office, retirement_type_id) VALUES(1, false, 'General-Ledger-Code', 'GLC','R6', true,1);
INSERT INTO opm.gl_code(id, deleted, name, code, payment_type, post_office, retirement_type_id) VALUES(2, false, 'Filler-Position', 'F','R6', true,1);
INSERT INTO opm.gl_code(id, deleted, name, code, payment_type, post_office, retirement_type_id) VALUES(3, false, 'Accounting-Code', '1234567890','R6', true,2);
INSERT INTO opm.gl_code(id, deleted, name, code, payment_type, post_office, retirement_type_id) VALUES(4, false, 'Revenue-Source-Code', 'Reve','R6', true,2);
INSERT INTO opm.gl_code(id, deleted, name, code, payment_type, post_office, retirement_type_id) VALUES(5, false, 'Feeder-System-ID', 'Feed','R6', true,1);

ALTER SEQUENCE opm.gl_code_id_seq RESTART WITH 1000;

----------------------------------------------------
--      pay_code table
----------------------------------------------------
INSERT INTO opm.pay_code(id, deleted, name) VALUES(1, false, '1');
INSERT INTO opm.pay_code(id, deleted, name) VALUES(2, false, '2');
INSERT INTO opm.pay_code(id, deleted, name) VALUES(3, false, '3');
INSERT INTO opm.pay_code(id, deleted, name) VALUES(4, false, '4');
INSERT INTO opm.pay_code(id, deleted, name) VALUES(5, false, '5');
INSERT INTO opm.pay_code(id, deleted, name) VALUES(6, false, '6');
INSERT INTO opm.pay_code(id, deleted, name) VALUES(7, false, 'A');
INSERT INTO opm.pay_code(id, deleted, name) VALUES(8, false, 'B');
INSERT INTO opm.pay_code(id, deleted, name) VALUES(9, false, 'C');
INSERT INTO opm.pay_code(id, deleted, name) VALUES(10, false, 'D');

ALTER SEQUENCE opm.pay_code_id_seq RESTART WITH 1000;

----------------------------------------------------
--      pay_trans_status_code table
----------------------------------------------------
INSERT INTO opm.pay_trans_status_code(id, deleted) VALUES(-1, false);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(0, false, 'Pending');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(1, false, 'Accepted');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(2, false, 'Unresolved');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(3, false, 'Suspended');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch, next_state_link) VALUES(10, false, 'PostedPending', true, 11);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(11, false, 'PostedComplete');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch, next_state_link) VALUES(12, false, 'ReversedPending', true, 13);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(13, false, 'ReversedComplete');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(15, false, 'PriorPaymentRecorded');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch) VALUES(18, false, 'AnnuityPending', true);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(19, false, 'AnnuityComplete');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch, next_state_link) VALUES(20, false, 'VoluntaryContributionsPending', true, 21);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(21, false, 'VoluntaryContributionsComplete');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch, next_state_link) VALUES(22, false, 'DirectPayLifePending', true, 23);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(23, false, 'DirectPayLifeComplete');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch, next_state_link) VALUES(24, false, 'RefundPending', true, 25);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(25, false, 'RefundComplete');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch) VALUES(26, false, 'DebitVoucherPending', true);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(27, false, 'DebitVoucherComplete');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(30, false, 'ReversedPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(31, false, 'PostedPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(32, false, 'VoluntaryContributionsPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(33, false, 'DirectPayLifePendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(34, false, 'RefundPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(35, false, 'AnnuityPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(36, false, 'DebitVoucherPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(37, false, 'HealthBenefitsPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(38, false, 'AdjustmentPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(39, false, 'WriteOffPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch) VALUES(40, false, 'HealthBenefitsPending', true);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(41, false, 'HealthBenefitsComplete');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(50, false, 'ResettoOriginalPending');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(51, false, 'RefundDueClaimant');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(52, false, 'TransferredAmount');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch) VALUES(53, false, 'WriteOffPending', true);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(54, false, 'AdjustmentComplete');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(55, false, 'RefundCanceled');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(58, false, 'WriteOffComplete');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(59, false, 'RefundToClaimantPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch) VALUES(60, false, 'RefundToClaimantPending', true);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(61, false, 'RefundToClaimantComplete');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(62, false, 'ManualAdjustmentCancelled');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(63, false, 'RefundCanceledPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch) VALUES(64, false, 'RefundCanceledPending', true);
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch) VALUES(65, false, 'ManualAdjustmentCancelledPending', true);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(66, false, 'ManualAdjustmentCancelledPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch) VALUES(67, false, 'AdjustmentPending', true);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(68, false, 'BatchAutoRefundPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch) VALUES(69, false, 'BatchAutoRefundPending', true);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(70, false, 'LockboxBankError');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(71, false, 'SuspenseDebitVoucherPendingApproval');
INSERT INTO opm.pay_trans_status_code(id, deleted, description, nightly_batch) VALUES(72, false, 'SuspenseDebitVoucherPending', true);
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(73, false, 'SuspenseDebitVoucherComplete');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(74, false, 'SendToHewittsDBTSPending');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(75, false, 'ProcessedByHewittsDBTS');
INSERT INTO opm.pay_trans_status_code(id, deleted, description) VALUES(99, false, 'UnknownState');

ALTER SEQUENCE opm.pay_trans_status_code_id_seq RESTART WITH 1000;

----------------------------------------------------
--      calculation_status table
----------------------------------------------------
INSERT INTO opm.calculation_status(id, deleted, name) VALUES(1, false, 'SUCCESS');
INSERT INTO opm.calculation_status(id, deleted, name) VALUES(2, false, 'FAILED');
INSERT INTO opm.calculation_status(id, deleted, name) VALUES(3, false, 'Status Calculation Saved');
INSERT INTO opm.calculation_status(id, deleted, name) VALUES(4, false, 'Status Calculation');
INSERT INTO opm.calculation_status(id, deleted, name) VALUES(5, false, 'Triggered Pending');

ALTER SEQUENCE opm.calculation_status_id_seq RESTART WITH 1000;

----------------------------------------------------
--      transfer_type table
----------------------------------------------------
INSERT INTO opm.transfer_type(id, deleted, name) VALUES(1, false, 'Refund Payment');
INSERT INTO opm.transfer_type(id, deleted, name) VALUES(2, false, 'Direct Pay Life Insurance');
INSERT INTO opm.transfer_type(id, deleted, name) VALUES(3, false, 'Voluntary Contributions');
INSERT INTO opm.transfer_type(id, deleted, name) VALUES(4, false, 'Debit Voucher');
INSERT INTO opm.transfer_type(id, deleted, name) VALUES(5, false, 'Annuity');

ALTER SEQUENCE opm.transfer_type_id_seq RESTART WITH 1000;

----------------------------------------------------
--      user_status table
----------------------------------------------------
INSERT INTO opm.user_status(id, deleted, name) VALUES(1, false, 'Active User');
INSERT INTO opm.user_status(id, deleted, name) VALUES(2, false, 'Suspended User');

ALTER SEQUENCE opm.user_status_id_seq RESTART WITH 1000;

----------------------------------------------------
--      payment_status table
----------------------------------------------------
INSERT INTO opm.payment_status(id, deleted, name) VALUES(1, false, 'Posted – Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(2, false, 'Reversed - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(3, false, 'Reversed – Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(4, false, 'Voluntary Contributions - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(5, false, 'Voluntary Contributions – Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(6, false, 'Direct Pay Life - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(7, false, 'Direct Pay Life – Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(8, false, 'Suspense Refund Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(9, false, 'Suspense Refund Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(10, false, 'Annuity - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(11, false, 'Annuity – Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(12, false, 'Debit Voucher - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(13, false, 'Debit Voucher – Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(14, false, 'Health Benefits - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(15, false, 'Health Benefits – Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(16, false, 'Adjustment - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(17, false, 'Adjustment – Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(18, false, 'Write-Off - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(19, false, 'Write-Off – Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(20, false, 'Credit Balance Refund - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(21, false, 'Credit Balance Refund – Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(22, false, 'Batch Auto Refund Canceled - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(23, false, 'Batch Auto Refund Canceled – Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(24, false, 'Manual Adjustment Cancelled - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(25, false, 'Manual Adjustment Cancelled – Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(26, false, 'Batch Auto Refund - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(27, false, 'Batch Auto Refund – Pending');
ALTER SEQUENCE opm.payment_status_id_seq RESTART WITH 1000;

----------------------------------------------------
--      application_designation table
----------------------------------------------------
INSERT INTO opm.application_designation(id, deleted, name) VALUES(1, false, 'Default User Order');
INSERT INTO opm.application_designation(id, deleted, name) VALUES(2, false, 'Pre Deposit');
INSERT INTO opm.application_designation(id, deleted, name) VALUES(3, false, 'Post Deposit');
INSERT INTO opm.application_designation(id, deleted, name) VALUES(4, false, 'Pre Redeposit');
INSERT INTO opm.application_designation(id, deleted, name) VALUES(5, false, 'Post Redeposit');
INSERT INTO opm.application_designation(id, deleted, name) VALUES(6, false, 'FERS');
ALTER SEQUENCE opm.application_designation_id_seq RESTART WITH 1000;

----------------------------------------------------
--      payment_reversal_reason table
----------------------------------------------------
INSERT INTO opm.payment_reversal_reason(id, deleted, name) VALUES(1, false, 'Applied to wrong account');
INSERT INTO opm.payment_reversal_reason(id, deleted, name) VALUES(2, false, 'Bank Account Closed');
INSERT INTO opm.payment_reversal_reason(id, deleted, name) VALUES(3, false, 'Data Entry Error');
INSERT INTO opm.payment_reversal_reason(id, deleted, name) VALUES(4, false, 'No Acct/Cannot Locate');
INSERT INTO opm.payment_reversal_reason(id, deleted, name) VALUES(5, false, 'Noon-Sufficient Funds');
INSERT INTO opm.payment_reversal_reason(id, deleted, name) VALUES(6, false, 'Stop Payment');
ALTER SEQUENCE opm.payment_reversal_reason_id_seq RESTART WITH 1000;

----------------------------------------------------
--      pay_type table
----------------------------------------------------
INSERT INTO opm.pay_type(id, deleted, name) VALUES (1, false, 'Unknown');
INSERT INTO opm.pay_type(id, deleted, name) VALUES (2, false, 'Annual Salary');
INSERT INTO opm.pay_type(id, deleted, name) VALUES (3, false, 'Hourly Rate');
INSERT INTO opm.pay_type(id, deleted, name) VALUES (4, false, 'Earnings Amount');
INSERT INTO opm.pay_type(id, deleted, name) VALUES (5, false, 'Refund/Lump Sum');
INSERT INTO opm.pay_type(id, deleted, name) VALUES (6, false, 'Daily Rate');
INSERT INTO opm.pay_type(id, deleted, name) VALUES (7, false, 'Monthly Rate');
INSERT INTO opm.pay_type(id, deleted, name) VALUES (8, false, 'Biweekly Rate');
INSERT INTO opm.pay_type(id, deleted, name) VALUES (9, false, 'Deduction Amount');
ALTER SEQUENCE opm.pay_type_id_seq RESTART WITH 1000;

----------------------------------------------------
--      appoinment_type table
----------------------------------------------------
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(1, false, 'Deposit (D)');
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(2, false, 'Career (C)');
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(3, false, 'Career XXX');
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(4, false, 'Temporary');
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(5, false, 'NTE');
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(6, false, 'TERM');
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(7, false, 'Excepted');
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(8, false, 'Intermittent');
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(9, false, 'Taper');
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(10, false, 'Indefinite');
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(11, false, 'Unknown-deduction');
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(12, false, 'Unknown-nondeduction');
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(13, false, 'Not applicable');
ALTER SEQUENCE opm.appointment_type_id_seq RESTART WITH 1000;

----------------------------------------------------
-- 			role table
----------------------------------------------------
INSERT INTO opm.role(id, deleted, name, description) VALUES(1, false, 'Batch', 'Batch Process');
INSERT INTO opm.role(id, deleted, name, description) VALUES(2, false, 'Reviewer', 'Service Credit Reviewer');
INSERT INTO opm.role(id, deleted, name, description) VALUES(3, false, 'RecSup', 'Receivables Supervisor');
INSERT INTO opm.role(id, deleted, name, description) VALUES(4, false, 'Specialist', 'Service Credit Specialist');
INSERT INTO opm.role(id, deleted, name, description) VALUES(5, false, 'RecTech', 'Receivables Technician');
INSERT INTO opm.role(id, deleted, name, description) VALUES(6, false, 'DataEntry', 'Service Credit Data Entry Technician');
INSERT INTO opm.role(id, deleted, name, description) VALUES(7, false, 'FinTech', 'Financial Technician');
INSERT INTO opm.role(id, deleted, name, description) VALUES(8, false, 'FinSup', 'Financial Supervisor');
INSERT INTO opm.role(id, deleted, name, description) VALUES(9, false, 'InfoTech', 'Information Technician');
INSERT INTO opm.role(id, deleted, name, description) VALUES(10, false, 'Admin', 'System Administrator');
INSERT INTO opm.role(id, deleted, name, description) VALUES(11, false, 'FACES', 'FACES User');
INSERT INTO opm.role(id, deleted, name, description) VALUES(12, false, 'RIO', 'RIO User');
INSERT INTO opm.role(id, deleted, name, description) VALUES(13, false, 'ViewOnly', 'View Only User');
ALTER SEQUENCE opm.role_id_seq RESTART WITH 1000;

----------------------------------------------------
--      service_type table
----------------------------------------------------
INSERT INTO opm.service_type(id, deleted, name) VALUES(1, false, 'GS (0)');
INSERT INTO opm.service_type(id, deleted, name) VALUES(2, false, 'Wage Grade');
INSERT INTO opm.service_type(id, deleted, name) VALUES(3, false, 'ES-Executive Schedule');
INSERT INTO opm.service_type(id, deleted, name) VALUES(4, false, 'SES-Senior Executive Schedule');
INSERT INTO opm.service_type(id, deleted, name) VALUES(5, false, 'Senior Official');
INSERT INTO opm.service_type(id, deleted, name) VALUES(6, false, 'Legislators');
INSERT INTO opm.service_type(id, deleted, name) VALUES(7, false, 'Legislators –SS Max');
INSERT INTO opm.service_type(id, deleted, name) VALUES(8, false, 'Postal Service – PTF');
INSERT INTO opm.service_type(id, deleted, name) VALUES(9, false, 'Postal Service – LT (and FT)');
INSERT INTO opm.service_type(id, deleted, name) VALUES(10, false, 'Legislative Employee');
INSERT INTO opm.service_type(id, deleted, name) VALUES(11, false, 'Senate Child Care');
INSERT INTO opm.service_type(id, deleted, name) VALUES(12, false, 'Library of Congress Child Care');
INSERT INTO opm.service_type(id, deleted, name) VALUES(13, false, 'Legislative Employee –SS Max');
INSERT INTO opm.service_type(id, deleted, name) VALUES(14, false, 'D.C. Government');
INSERT INTO opm.service_type(id, deleted, name) VALUES(15, false, 'Firefighter');
INSERT INTO opm.service_type(id, deleted, name) VALUES(16, false, 'Air Traffic Controller');
INSERT INTO opm.service_type(id, deleted, name) VALUES(17, false, 'Law Enforcement');
INSERT INTO opm.service_type(id, deleted, name) VALUES(18, false, 'Capitol Police');
INSERT INTO opm.service_type(id, deleted, name) VALUES(19, false, 'Customs Border Prot Officer');
INSERT INTO opm.service_type(id, deleted, name) VALUES(20, false, 'Supreme Ct Police');
INSERT INTO opm.service_type(id, deleted, name) VALUES(21, false, 'Bankruptcy Judge');
INSERT INTO opm.service_type(id, deleted, name) VALUES(22, false, 'Bankruptcy Judge – SS Max');
INSERT INTO opm.service_type(id, deleted, name) VALUES(23, false, 'Ct of Fed Claims');
INSERT INTO opm.service_type(id, deleted, name) VALUES(24, false, 'Ct of Fed Claims –SS Max');
INSERT INTO opm.service_type(id, deleted, name) VALUES(25, false, 'Military Appeals Judge');
INSERT INTO opm.service_type(id, deleted, name) VALUES(26, false, 'Military Appeals Judge- SS Max');
INSERT INTO opm.service_type(id, deleted, name) VALUES(27, false, 'US Magistrate');
INSERT INTO opm.service_type(id, deleted, name) VALUES(28, false, 'US Magistrate- SS Max');
INSERT INTO opm.service_type(id, deleted, name) VALUES(29, false, 'Reemployed Offset');
INSERT INTO opm.service_type(id, deleted, name) VALUES(30, false, 'Reemployed Offset- SS Max');
INSERT INTO opm.service_type(id, deleted, name) VALUES(31, false, 'Title 38 USC');
INSERT INTO opm.service_type(id, deleted, name) VALUES(32, false, 'Foreign Service Schedule');
INSERT INTO opm.service_type(id, deleted, name) VALUES(33, false, 'Federal Reserve Official');
INSERT INTO opm.service_type(id, deleted, name) VALUES(34, false, 'Nuclear Materials Courier');
INSERT INTO opm.service_type(id, deleted, name) VALUES(35, false, 'Peace Corps/VISTA');
INSERT INTO opm.service_type(id, deleted, name) VALUES(36, false, 'Unknown');
INSERT INTO opm.service_type(id, deleted, name) VALUES(37, false, 'Not Applicable');
ALTER SEQUENCE opm.service_type_id_seq RESTART WITH 1000;

----------------------------------------------------
-- 			account_status table
----------------------------------------------------
INSERT INTO opm.account_status(id, deleted, name) VALUES(1, false, 'Active');
INSERT INTO opm.account_status(id, deleted, name) VALUES(2, false, 'Suspended');
INSERT INTO opm.account_status(id, deleted, name) VALUES(10, false, 'First Bill Generated');
INSERT INTO opm.account_status(id, deleted, name) VALUES(12, false, 'History');
ALTER SEQUENCE opm.account_status_id_seq RESTART WITH 1000;

----------------------------------------------------
-- 			period_type table
----------------------------------------------------
INSERT INTO opm.period_type(id, deleted, name) VALUES (1, false, 'Deposit (D)');
INSERT INTO opm.period_type(id, deleted, name) VALUES (2, false, 'Redeposit (R)');
INSERT INTO opm.period_type(id, deleted, name) VALUES (3, false, 'Military');
INSERT INTO opm.period_type(id, deleted, name) VALUES (4, false, 'LWOP (Y)');
INSERT INTO opm.period_type(id, deleted, name) VALUES (5, false, 'No Earnings (X)');
INSERT INTO opm.period_type(id, deleted, name) VALUES (6, false, 'Deduction Service');
INSERT INTO opm.period_type(id, deleted, name) VALUES (7, false, 'Unpaid Redeposit');
INSERT INTO opm.period_type(id, deleted, name) VALUES (8, false, 'Pre 10-82 Redeposit');
INSERT INTO opm.period_type(id, deleted, name) VALUES (9, false, 'Post 10-82 Redeposit');
INSERT INTO opm.period_type(id, deleted, name) VALUES (10, false, 'Post 03-91 Redeposit');
INSERT INTO opm.period_type(id, deleted, name) VALUES (11, false, 'Pre 03-91 Redeposit');
ALTER SEQUENCE opm.period_type_id_seq RESTART WITH 1000;

----------------------------------------------------
-- 			suffix table
----------------------------------------------------
INSERT INTO opm.suffix(id, deleted, name) VALUES(1, false,'I');
INSERT INTO opm.suffix(id, deleted, name) VALUES(2, false,'II');
INSERT INTO opm.suffix(id, deleted, name) VALUES(3, false,'III');
INSERT INTO opm.suffix(id, deleted, name) VALUES(4, false,'JR');
INSERT INTO opm.suffix(id, deleted, name) VALUES(5, false,'SR');
ALTER SEQUENCE opm.suffix_id_seq RESTART WITH 1000;

INSERT INTO opm.state (id, deleted, name) VALUES (1, false, 'OH');
INSERT INTO opm.state (id, deleted, name) VALUES (2, false, 'IL');
ALTER SEQUENCE opm.state_id_seq RESTART WITH 1000;

INSERT INTO opm.country (id, deleted, name) VALUES (1, false, 'Country 1');
INSERT INTO opm.country (id, deleted, name) VALUES (2, false, 'Country 2');
ALTER SEQUENCE opm.country_id_seq RESTART WITH 1000;


INSERT INTO opm.role_permission(id, deleted, rolename, action) VALUES(1, false, 'Batch', 'batchProcessingJob');
INSERT INTO opm.app_user(id, deleted, first_name, last_name, email, telephone, username, default_tab, role_id, user_status_id, network_id) VALUES(1, false,'Jacqueline','Hill','jhill@quimba.com','046-44-6641','BatchUser',null, 1, 1,'AMAZONA-20SVSGC\\Batch');

--INSERT INTO opm.address(id, deleted, street1, city, state_id, zip_code, country_id) VALUES(1, false, 'Street1', 'City 1', 1, 'Zip 1', 1);
--INSERT INTO opm.address(id, deleted, street1, city, state_id, zip_code, country_id) VALUES(2, false, 'Street2', 'City 2', 2, 'Zip 2', 2);

--INSERT INTO opm.account_holder(id, deleted, last_name, first_name, suffix_id, birth_date, ssn, email, city_of_employment, state_of_employment_id, address_id) VALUES(1, false, 'Hughes', 'Jack', 1, CURRENT_TIMESTAMP, 'SSN 1', 'Email 1', 'City 1', 1, 1);
--INSERT INTO opm.account_holder(id, deleted, last_name, first_name, suffix_id, birth_date, ssn, email, city_of_employment, state_of_employment_id, address_id) VALUES(2, false, 'Zuckerberg', 'Mark', 2, CURRENT_TIMESTAMP, 'SSN 2', 'Email 2', 'City 2', 2, 2);
