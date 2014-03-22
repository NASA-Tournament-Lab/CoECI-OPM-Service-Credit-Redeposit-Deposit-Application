INSERT INTO opm.retirement_type (id, deleted, name) VALUES (1, false, 'Retirement Type 1');
INSERT INTO opm.retirement_type (id, deleted, name) VALUES (2, false, 'Retirement Type 2');
INSERT INTO opm.retirement_type (id, deleted, name) VALUES (3, false, 'Retirement Type 3');
INSERT INTO opm.retirement_type (id, deleted, name) VALUES (4, false, 'Retirement Type 4');
INSERT INTO opm.retirement_type (id, deleted, name) VALUES (5, false, 'Retirement Type 5');

INSERT INTO opm.form_type (id, deleted, name) VALUES (1, false, 'Form Type 1');
INSERT INTO opm.form_type (id, deleted, name) VALUES (2, false, 'Form Type 2');
INSERT INTO opm.form_type (id, deleted, name) VALUES (3, false, 'Form Type 3');
INSERT INTO opm.form_type (id, deleted, name) VALUES (4, false, 'Form Type 4');
INSERT INTO opm.form_type (id, deleted, name) VALUES (5, false, 'Form Type 5');

INSERT INTO opm.account_status (id, deleted, name) VALUES (1, false, 'Account Status 1');
INSERT INTO opm.account_status (id, deleted, name) VALUES (2, false, 'Account Status 2');
INSERT INTO opm.account_status (id, deleted, name) VALUES (3, false, 'Account Status 3');
INSERT INTO opm.account_status (id, deleted, name) VALUES (4, false, 'Account Status 4');
INSERT INTO opm.account_status (id, deleted, name) VALUES (5, false, 'Account Status 5');

INSERT INTO opm.suffix (id, deleted, name) VALUES (1, false, 'Suffix 1');
INSERT INTO opm.suffix (id, deleted, name) VALUES (2, false, 'Suffix 2');

INSERT INTO opm.state (id, deleted, name) VALUES (1, false, 'State 1');
INSERT INTO opm.state (id, deleted, name) VALUES (2, false, 'State 2');

INSERT INTO opm.country (id, deleted, name) VALUES (1, false, 'Country 1');
INSERT INTO opm.country (id, deleted, name) VALUES (2, false, 'Country 2');

INSERT INTO opm.role (id, deleted, name) VALUES (1, false, 'Role 1');
INSERT INTO opm.role (id, deleted, name) VALUES (2, false, 'Role 2');
INSERT INTO opm.role (id, deleted, name) VALUES (3, false, 'Role 3');

INSERT INTO opm.application_designation (id, deleted, name) VALUES (1, false, 'Application Designation 1');
INSERT INTO opm.application_designation (id, deleted, name) VALUES (2, false, 'Application Designation 2');
INSERT INTO opm.application_designation (id, deleted, name) VALUES (3, false, 'Application Designation 3');

INSERT INTO opm.address(id, deleted, street1, city, state_id, zip_code, country_id) VALUES(1, false, 'Street1', 'City 1', 1, 'Zip 1', 1);
INSERT INTO opm.address(id, deleted, street1, city, state_id, zip_code, country_id) VALUES(2, false, 'Street2', 'City 2', 2, 'Zip 2', 2);

INSERT INTO opm.account_holder(id, deleted, last_name, first_name, suffix_id, birth_date, ssn, email, city_of_employment, state_of_employment_id, address_id) VALUES(1, false, 'Hughes', 'Jack', 1, CURRENT_TIMESTAMP, 'SSN 1', 'Email 1', 'City 1', 1, 1);
INSERT INTO opm.account_holder(id, deleted, last_name, first_name, suffix_id, birth_date, ssn, email, city_of_employment, state_of_employment_id, address_id) VALUES(2, false, 'Zuckerberg', 'Mark', 2, CURRENT_TIMESTAMP, 'SSN 2', 'Email 2', 'City 2', 2, 2);

INSERT INTO opm.payment_status VALUES (10, false, 'Suspended');
INSERT INTO opm.payment_status VALUES (11, false, 'Posted - Pending Approval');
INSERT INTO opm.payment_status VALUES (59, false, 'Credit Balance Refund - Pending');
INSERT INTO opm.payment_status VALUES (25, false, 'Suspense Refund - Complete');
INSERT INTO opm.payment_status VALUES (26, false, 'Debit Voucher - Pending');
INSERT INTO opm.payment_status VALUES (34, false, 'Suspense Refund - Pending Approval');
INSERT INTO opm.payment_status VALUES (2, false, 'Unresolved');
INSERT INTO opm.payment_status VALUES (19, false, 'Annuity - Complete');
INSERT INTO opm.payment_status VALUES (27, false, 'Debit Voucher - Complete');
INSERT INTO opm.payment_status VALUES (23, false, 'Direct Pay - Complete');
INSERT INTO opm.payment_status VALUES (73, false, 'Suspense Debit Voucher - Complete');
INSERT INTO opm.payment_status VALUES (21, false, 'Voluntary Contributions - Complete');
INSERT INTO opm.payment_status VALUES (12, false, 'Posted - Complete');
INSERT INTO opm.payment_status VALUES (54, false, 'Adjustment - Complete');


INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date)
VALUES (1, false, '2014-01-01', '2014-01-01', '2014-01-01', '2014-01-01', 'Deposit', '2014-01-01');

INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen)
VALUES (1, false, 'BILLING 1', 55.00, 5, 20, 40, 1, 1, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen)
VALUES (2, false, 'BILLING 2', 40.00, 4, 69, 0, 2, 1, false);

INSERT INTO opm.account(id, deleted, claim_number, plan_type, form_type_id, account_holder_id, account_status_id, grace, frozen, returned_from_record_date, billing_summary_id, claimant_birthdate)
 VALUES (1, false, '1111111', 'FERS', 1, 1, 1, false, false, '2014-01-01', 1, '1970-01-01');
INSERT INTO opm.account(id, deleted, claim_number, plan_type, form_type_id, account_holder_id, account_status_id, grace, frozen, returned_from_record_date)
 VALUES (2, false, '2222222', 'CSRS', 2, 2, 1, false, false, '2014-01-01');
INSERT INTO opm.account(id, deleted, claim_number, plan_type, form_type_id, account_holder_id, account_status_id, grace, frozen, returned_from_record_date)
 VALUES (3, false, '3333333', 'FERS', 1, 1, 1, false, false, '2014-01-01');
 
INSERT INTO opm.payment(id, deleted, batch_number, block_number, sequence_number, 
claim_number, payment_status_id, deposit_date, amount, ssn, 
import_id, sequence, apply_designation_id, apply_to_gl, note, 
transaction_key, master_account_status_id, approval_user, approval_status, payment_type, 
ach, account_status_id, master_claimant_birthday, status_date, account_id, claimant)
VALUES(1, false, '000', '00', '01',
'1111111', 23, '2014-01-01', 20, '123456789',
0, 1, 1, false, 'payment note',
1, 1, 'approver1', 'APPROVED', 'ORDINARY',
true, 1, '1982-01-01', '2014-01-01', 1, 'claimant name1');

INSERT INTO opm.payment(id, deleted, batch_number, block_number, sequence_number, 
claim_number, payment_status_id, deposit_date, amount, ssn, 
import_id, sequence, apply_designation_id, apply_to_gl, note, 
transaction_key, master_account_status_id, approval_user, approval_status, payment_type, 
ach, account_status_id, master_claimant_birthday, status_date, account_id, claimant)
VALUES(2, false, '000', '00', '02',
'2222222', 23, '2014-01-01', 44, '123456789',
0, 2, 1, false, 'payment note',
1, 1, 'approver1', 'APPROVED', 'ORDINARY',
false, 1, '1982-01-01', '2014-01-01', 2, 'claimant name2');

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (1, false, '111', '11', '11', 
		'1111111', '1982-01-01', 101.01, '2014-01-01', 1, 
		'2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '1111111', 
		false, false, 10, false, false, 1);
		
INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (1, false, 1, '1111111', '1982-01-01',
		'claimant 1', '1', 'Active', 4567.89, 4321.76, 
		246.13, 4321.76, 'Suspended', 4321.76, 0,
		'2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (2, false, '111', '22', '11', 
		'1111111', '1982-01-02', 101.01, '2014-01-02', 2, 
		'2014-01-02', 2, 'DEFAULT_USER_ORDER', true, '2222222', 
		false, false, 2, false, false, 2);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (2, false, 2, '2222222', '1982-01-02',
		'claimant 2', '1', 'Active', 4522.89, 321.76, 
		26.13, 4321.76, 'Unresolved', 4311.76, 0,
		'2014-01-02', 2, 'Ret Type 2', false, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 30, 1);
		
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (3, false, '111', '22', '12', 
		'1111111', '1982-01-02', 101.01, '2014-01-02', 1, 
		'2014-01-02', 2, 'DEFAULT_USER_ORDER', true, '2222222', 
		false, false, 11, false, false, 3);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (3, false, 3, '2222222', '1982-01-02',
		'claimant 2', '1', 'Active', 4522.89, 321.76, 
		26.13, 4321.76, 'Posted - Pending Approval', 4311.76, 0,
		'2014-01-02', 2, 'Ret Type 2', false, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 100, 1);
		
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (4, false, '111', '22', '23', 
		'1111111', '1982-01-03', 101.01, '2014-01-02', 5, 
		'2014-01-02', 2, 'DEFAULT_USER_ORDER', true, '3333333', 
		false, false, 59, false, false, 4);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (4, false, 4, '3333333', '1982-01-03',
		'claimant 3', '2', 'Account Closed', 33.89, 3333.76, 
		26.13, -421.76, 'Credit Balance Refund - Pending Approval', -11.76, 0,
		'2014-01-02', 2, 'Ret Type 2', false, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 0, 1);
		
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (5, false, '111', '22', '24', 
		'1111111', '1982-01-03', 101.01, '2014-01-02', 3, 
		'2014-01-02', 2, 'DEFAULT_USER_ORDER', true, '3333333', 
		false, true, 34, false, false, 5);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (5, false, 5, '3333333', '1982-01-03',
		'claimant 3', '2', 'Account Closed', 33.89, 3333.76, 
		26.13, -421.76, 'Credit Balance Refund - Pending Approval', -11.76, 0,
		'2014-01-02', 2, 'Ret Type 2', true, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 0, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (6, false, '111', '11', '12', 
		'1111111', '1982-01-01', 101.01, '2014-01-01', 1, 
		'2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '1111111', 
		false, true, 10, false, false, 6);
		
INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (6, false, 6, '1111111', '1982-01-01',
		'claimant 1', '1', 'Active', 4567.89, 4321.76, 
		246.13, 4321.76, 'Suspended', 4321.76, 0,
		'2014-01-01', 1, 'Ret Type 1', true, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 0, 1);
		

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (7, false, '111', '07', '01', 
		'1111111', '1982-01-01', 101.01, '2014-01-01', 1, 
		'2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '1111111', 
		false, false, 19, true, false, 7);
		
INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (7, false, 7, '1111111', '1982-01-01',
		'claimant 1', '1', 'Active', 4567.89, 4321.76, 
		246.13, 4321.76, 'Suspended', 4321.76, 0,
		'2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 0, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (8, false, '111', '08', '01', 
		'1111111', '1982-01-02', 101.01, '2014-01-02', 2, 
		'2014-01-02', 2, 'DEFAULT_USER_ORDER', true, '2222222', 
		false, false, 27, true, false, 8);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (8, false, 8, '2222222', '1982-01-02',
		'claimant 2', '1', 'Active', 4522.89, 321.76, 
		26.13, 4321.76, 'Unresolved', 4311.76, 0,
		'2014-01-02', 2, 'Ret Type 2', false, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 0, 1);
		
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (9, false, '111', '09', '01', 
		'1111111', '1982-01-02', 101.01, '2014-01-02', 2, 
		'2014-01-02', 2, 'DEFAULT_USER_ORDER', true, '2222222', 
		false, false, 23, true, false, 9);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (9, false, 9, '2222222', '1982-01-02',
		'claimant 2', '1', 'Active', 4522.89, 321.76, 
		26.13, 4321.76, 'Posted - Pending Approval', 4311.76, 0,
		'2014-01-02', 2, 'Ret Type 2', false, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 0, 1);
		
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (10, false, '111', '10', '01', 
		'1111111', '1982-01-03', 101.01, '2014-01-02', 7, 
		'2014-01-02', 2, 'DEFAULT_USER_ORDER', true, '3333333', 
		false, false, 73, true, false, 10);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (10, false, 10, '3333333', '1982-01-03',
		'claimant 3', '2', 'Account Closed', 33.89, 3333.76, 
		26.13, -421.76, 'Credit Balance Refund - Pending Approval', -11.76, 0,
		'2014-01-02', 2, 'Ret Type 2', false, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 0, 1);
		
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (11, false, '111', '11', '01', 
		'1111111', '1982-01-03', 101.01, '2014-01-02', 2, 
		'2014-01-02', 2, 'DEFAULT_USER_ORDER', true, '3333333', 
		false, true, 21, true, false, 11);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (11, false, 11, '3333333', '1982-01-03',
		'claimant 3', '2', 'Account Closed', 33.89, 3333.76, 
		26.13, -421.76, 'Credit Balance Refund - Pending Approval', -11.76, 0,
		'2014-01-02', 2, 'Ret Type 2', true, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 0, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (12, false, '111', '12', '01', 
		'1111111', '1982-01-01', 101.01, '2014-01-01', 1, 
		'2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '1111111', 
		false, true, 19, true, false, 12);
		
INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (12, false, 12, '1111111', '1982-01-01',
		'claimant 1', '1', 'Active', 4567.89, 4321.76, 
		246.13, 4321.76, 'Suspended', 4321.76, 0,
		'2014-01-01', 1, 'Ret Type 1', true, 'DEFAULT_USER_ORDER', 
		'this is a long note that shows how the line breaks in the report there should be some limit here that breaks it', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 0, 1);
		
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (13, false, '999', '00', '00', 
		'1111111', '1982-01-01', 101.01, '2014-01-01', 1, 
		'2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '1111111', 
		true, false, 12, false, false, 13);
		
INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (13, false, 13, '1111111', '1982-01-01',
		'claimant 1', '1', 'Active', 4567.89, 4321.76, 
		246.13, 4321.76, 'Suspended', 4321.76, 0,
		'2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 0, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (14, false, '999', '01', '01', 
		'1111111', '1982-01-02', 101.01, '2014-01-02', 5, 
		'2014-01-02', 2, 'DEFAULT_USER_ORDER', true, '2222222', 
		true, false, 54, false, false, 14);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (14, false, 14, '2222222', '1982-01-02',
		'claimant 2', '1', 'Active', 4522.89, 321.76, 
		26.13, 4321.76, 'Unresolved', 4311.76, 0,
		'2014-01-02', 2, 'Ret Type 2', false, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 0, 1);
		
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (15, false, '999', '01', '02', 
		'1111111', '1982-01-02', 101.01, '2014-01-02', 5, 
		'2014-01-02', 2, 'DEFAULT_USER_ORDER', true, '2222222', 
		true, false, 54, false, false, 15);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (15, false, 15, '2222222', '1982-01-02',
		'claimant 2', '1', 'Active', 4522.89, 321.76, 
		26.13, 4321.76, 'Posted - Pending Approval', 4311.76, 0,
		'2014-01-02', 2, 'Ret Type 2', false, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 0, 1);
		
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (16, false, '999', '01', '03', 
		'1111111', '1982-01-03', 101.01, '2014-01-02', 1, 
		'2014-01-02', 2, 'DEFAULT_USER_ORDER', true, '3333333', 
		true, false, 12, false, false, 16);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
	scm_name, account_status, account_status_description, account_balance, account_payment_total, 
	account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
	pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
	note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
	fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
	print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (16, false, 16, '3333333', '1982-01-03',
		'claimant 3', '2', 'Account Closed', 33.89, 3333.76, 
		26.13, -421.76, 'Credit Balance Refund - Pending Approval', -11.76, 0,
		'2014-01-02', 2, 'Ret Type 2', false, 'DEFAULT_USER_ORDER', 
		'note 1', 100, 100, 100, 100,
		100, false, false, false, false,
		false, false, false, 0, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (17, false, '000', '00', '01', 
        '1111111', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '1111111', 
        false, false, 10, false, false, 1);
        
INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (17, false, 17, '1111111', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 100, 100, 100, 100,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (18, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 18);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (18, false, 18, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (19, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 19);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (19, false, 19, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (20, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 20);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (20, false, 20, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);
        
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (21, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 21);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (21, false, 21, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (22, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 22);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (22, false, 22, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (23, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 23);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (23, false, 23, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (24, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 24);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (24, false, 24, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (25, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 25);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (25, false, 25, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (26, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 26);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (26, false, 26, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (27, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 27);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (27, false, 27, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (28, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 28);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (28, false, 28, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (29, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 29);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (29, false, 29, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (30, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 30);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (30, false, 30, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (31, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 31);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (31, false, 31, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (32, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 32);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (32, false, 32, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (33, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 33);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (33, false, 33, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (34, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 34);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (34, false, 34, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (35, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 35);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (35, false, 35, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (36, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 36);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (36, false, 36, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (37, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 37);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (37, false, 37, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (38, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 38);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (38, false, 38, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (39, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 39);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (39, false, 39, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (40, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 40);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (40, false, 40, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (41, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 41);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (41, false, 41, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (42, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 42);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (42, false, 42, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (43, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 43);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (43, false, 43, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (44, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 44);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (44, false, 44, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (45, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 45);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (45, false, 45, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (46, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 46);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (46, false, 46, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (47, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 47);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (47, false, 47, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (48, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 48);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (48, false, 48, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (49, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 49);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (49, false, 49, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (50, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 50);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (50, false, 50, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (51, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 51);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (51, false, 51, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (52, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 52);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (52, false, 52, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (53, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 53);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (53, false, 53, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (54, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 54);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (54, false, 54, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
    scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
    pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
    user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (55, false, '000', '00', '02', 
        '2222222', '1982-01-01', 101.01, '2014-01-01', 1, 
        '2014-01-01', 1, 'DEFAULT_USER_ORDER', true, '2222222', 
        false, false, 10, false, false, 55);

INSERT INTO opm.invoice_data (id, deleted, pay_transaction_key, scm_claimnumber, scm_date_of_birth, 
    scm_name, account_status, account_status_description, account_balance, account_payment_total, 
    account_balance_new, todays_payment_total, pay_trans_status_description, pay_trans_payment_amount, over_payment_amount, 
    pay_trans_transaction_date, retirement_type_code, retirement_type_description, ach_payment, payment_application_order, 
    note, pre_1082_deposit_total_payment, pre_1082_redeposit_total_payment, post_1082_deposit_total_payment, post_1082_redeposit_total_payment,
    fers_total_payment, ach_stop_letter, print_initial_bill, update_completed, reversed_payment,
    print_invoice, refund_required, update_to_completed, over_the_payment_amount, number_payments_today)
VALUES (55, false, 55, '2222222', '1982-01-01',
        'claimant 1', '1', 'Active', 4567.89, 4321.76, 
        246.13, 4321.76, 'Suspended', 4321.76, 0,
        '2014-01-01', 1, 'Ret Type 1', false, 'DEFAULT_USER_ORDER', 
        'note 1', 200, 200, 200, 200,
        100, false, false, false, false,
        false, false, false, 26, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (56, false, '999', '01', '03', 
		'1111111', '1982-01-02', 101.01, '2014-01-02', 2, 
		'2014-01-02', 1, 'DEFAULT_USER_ORDER', true, '1111111', 
		true, false, 25, false, false, 15);
        
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (1, false, '1', 'Ret Type 1', 50.50, 45.45, 20.20, 'A1', '2014-03-03', 'gl filler 1', 'gl code 1', 'acct code 1', 'src code 1', 'agency 1', '2014-03-05', 64, '1111111'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (2, false, '2', 'Ret Type 2', 50.50, 45.45, 20.20, 'A2', '2014-03-03', 'gl filler 2', 'gl code 2', 'acct code 2', 'src code 2', 'agency 2', '2014-03-05', 0, '2222222'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (3, false, '3', 'Ret Type 1', 50.50, 45.45, 20.20, 'A1', '2014-03-03', 'gl filler 1', 'gl code 1', 'acct code 1', 'src code 1', 'agency 1', '2014-03-05', 0, '1111111'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (4, false, '4', 'Ret Type 1', 50.50, 45.45, 20.20, 'A4', '2014-03-03', 'gl filler 4', 'gl code 4', 'acct code 1', 'src code 4', 'agency 4', '2014-03-05', 0, '1111111'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (5, false, '5', 'Ret Type 2', 50.50, 45.45, 20.20, 'A5', '2014-03-04', 'gl filler 5', 'gl code 5', 'acct code 1', 'src code 5', 'agency 5', '2014-03-05', 0, '2222222'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (6, false, '6', 'Ret Type 3', 50.50, 45.45, 20.20, 'A6', '2014-03-04', 'gl filler 6', 'gl code 6', 'acct code 1', 'src code 6', 'agency 6', '2014-03-05', 0, '3333333'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (7, false, '7', 'Ret Type 1', 50.50, 45.45, 20.20, 'A7', '2014-03-04', 'gl filler 7', 'gl code 7', 'acct code 1', 'src code 7', 'agency 7', '2014-03-05', 0, '1111111'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (8, false, '8', 'Ret Type 1', 50.50, 45.45, 20.20, 'A8', '2014-03-04', 'gl filler 8', 'gl code 8', 'acct code 1', 'src code 8', 'agency 8', '2014-03-05', 0, '1111111'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (9, false, '9', 'Ret Type 3', 50.50, 45.45, 20.20, 'A9', '2014-03-05', 'gl filler 9', 'gl code 9', 'acct code 1', 'src code 9', 'agency 9', '2014-03-05', 0, '3333333'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (10, false, '10', 'Ret Type 1', 50.50, 45.45, 20.20, 'A10', '2014-03-05', 'gl filler 10', 'gl code 10', 'acct code 1', 'src code 10', 'agency 10', '2014-03-05', 0, '1111111'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (11, false, '11', 'Ret Type 2', 50.50, 45.45, 20.20, 'A11', '2014-03-05', 'gl filler 11', 'gl code 11', 'acct code 1', 'src code 11', 'agency 11', '2014-03-05', 0, '2222222'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (12, false, '12', 'Ret Type 2', 50.50, 45.45, 20.20, 'A12', '2014-03-05', 'gl filler 12', 'gl code 12', 'acct code 1', 'src code 12', 'agency 12', '2014-03-05', 0, '2222222'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (13, false, '13', 'Ret Type 1', 50.50, 45.45, 20.20, 'A12', '2014-03-06', 'gl filler 13', 'gl code 13', 'acct code 1', 'src code 13', 'agency 13', '2014-03-05', 0, '1111111'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (14, false, '14', 'Ret Type 1', 50.50, 45.45, 20.20, 'A13', '2014-03-06', 'gl filler 14', 'gl code 14', 'acct code 1', 'src code 14', 'agency 14', '2014-03-05', 0, '1111111'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (15, false, '15', 'Ret Type 2', 50.50, 45.45, 20.20, 'A14', '2014-03-06', 'gl filler 15', 'gl code 15', 'acct code 1', 'src code 15', 'agency 15', '2014-03-05', 0, '2222222'); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (16, false, '16', 'Ret Type 1', 50.50, 45.45, 20.20, 'A15', '2014-03-06', 'gl filler 16', 'gl code 16', 'acct code 1', 'src code 16', 'agency 16', '2014-03-05', 0, '1111111'); 

UPDATE opm.all_details SET claimant_name = 'test claimant';
UPDATE opm.all_details SET julian_date = 2456697;

INSERT INTO opm.audit_batch_log_id (id, audit_batch_log_id, batch_date, batch_number, deleted)
VALUES (1, '1', '2014-01-01', 1, false); 
INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (1, '1', '2014-01-01', false, false, false, 'F1', '1', 1, 1);

INSERT INTO opm.audit_batch_log_id (id, audit_batch_log_id, batch_date, batch_number, deleted)
VALUES (2, '1', '2014-01-01', 2, false); 
INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (2, '2', '2014-01-01', false, false, false, 'F1', '1', 2, 0);

INSERT INTO opm.audit_batch_log_id (id, audit_batch_log_id, batch_date, batch_number, deleted)
VALUES (3, '1', '2014-01-01', 1, false); 
INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (3, '3', '2014-01-01', false, false, false, 'F1', '1', 3, 2);

INSERT INTO opm.audit_batch_log_id (id, audit_batch_log_id, batch_date, batch_number, deleted)
VALUES (4, '2', '2014-01-01', 1, false); 
INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (4, '4', '2014-01-01', false, false, false, 'F1', '2', 4, 1); 

INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (5, '5', '2014-01-01', false, false, false, 'F1', '1', 5, 1); 

INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (6, '6', '2014-01-01', false, false, false, 'F1', '1', 6, 1); 

INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (7, '7', '2014-01-01', false, false, false, 'F1', '1', 7, 1); 

INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (8, '8', '2014-01-01', false, false, false, 'F1', '1', 8, 1); 

INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (9, '9', '2014-01-01', false, false, false, 'F1', '1', 9, 1); 

INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (10, '10', '2014-01-01', false, false, false, 'F1', '1', 10, 1); 

INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (11, '11', '2014-01-01', false, false, false, 'F1', '1', 11, 1); 

INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (12, '12', '2014-01-01', false, false, false, 'F1', '1', 12, 1); 

INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (13, '13', '2014-01-01', false, false, false, 'F1', '1', 13, 1); 

INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (14, '14', '2014-01-01', false, false, false, 'F1', '1', 14, 1); 

INSERT INTO opm.mainframe_import (id, record_string, import_date, processing_flag, error_flag, ach_flag, file_name, audit_batch_log_id, pay_transaction_key, payment_type)
VALUES (15, '15', '2014-01-01', false, false, false, 'F1', '1', 15, 1); 

INSERT INTO opm.audit_record VALUES (1, false, 'USER1', '127.0.0.1', 'some description', 'Lock Box Import Error', '2014-01-01');
INSERT INTO opm.audit_record VALUES (2, false, 'USER1', '127.0.0.1', 'some description', 'Lock Box Import Error', '2014-01-01');
INSERT INTO opm.audit_record VALUES (3, false, 'USER1', '127.0.0.1', 'some description', 'Lock Box Import Error', '2014-01-01');

INSERT INTO opm.audit_parameter_record VALUES (10001, false, 1111111, 'Lock Box Import', 'errorCode', ' ', 'R', 1);
INSERT INTO opm.audit_parameter_record VALUES (10002, false, 1111111, 'Lock Box Import', 'errorMessage', ' ', 'R60610201 915785010100 Bad Address or name', 1);
INSERT INTO opm.audit_parameter_record VALUES (10003, false, 1111111, 'Lock Box Import', 'description', ' ', 'ValidC_NoChangeOccurred: Change command did not update database for CSD #', 1);

INSERT INTO opm.audit_parameter_record VALUES (20001, false, 2222222, 'Lock Box Import', 'errorCode', ' ', 'R', 2);
INSERT INTO opm.audit_parameter_record VALUES (20002, false, 2222222, 'Lock Box Import', 'errorMessage', ' ', 'R60610201 915785010100 Bad Address or name', 2);
INSERT INTO opm.audit_parameter_record VALUES (20003, false, 2222222, 'Lock Box Import', 'description', ' ', 'ValidC_NoChangeOccurred: Change command did not update database for CSD #', 2);

INSERT INTO opm.audit_parameter_record VALUES (30001, false, 3333333, 'Lock Box Import', 'errorCode', ' ', 'R', 3);
INSERT INTO opm.audit_parameter_record VALUES (30002, false, 3333333, 'Lock Box Import', 'errorMessage', ' ', 'R60610201 915785010100 Bad Address or name', 3);
INSERT INTO opm.audit_parameter_record VALUES (30003, false, 3333333, 'Lock Box Import', 'description', ' ', 'ValidC_NoChangeOccurred: Change command did not update database for CSD #', 3);

INSERT INTO opm.audit_record VALUES (4, false, 'USER1', '127.0.0.1', 'some description', 'Master: Post 9/82 Redeposit Changed', '2014-01-01');
INSERT INTO opm.audit_record VALUES (5, false, 'USER1', '127.0.0.1', 'some description', 'Master: First Name Changed', '2014-01-01');
INSERT INTO opm.audit_record VALUES (6, false, 'USER2', '127.0.0.1', 'some description', 'Master: First Name Changed', '2014-01-01');
INSERT INTO opm.audit_record VALUES (7, false, 'USER2', '127.0.0.1', 'some description', 'Master: Last Name Changed', '2014-01-01');
INSERT INTO opm.audit_record VALUES (8, false, 'USER2', '127.0.0.1', 'some description', 'Master: Middle Name Changed', '2014-01-01');
INSERT INTO opm.audit_parameter_record VALUES (40001, false, 1111111, 'Account', 'post982Redeposit', 'old value 4', 'new value 4', 4);
INSERT INTO opm.audit_parameter_record VALUES (50001, false, 2222222, 'Account', 'firstName', 'old value 5', 'new value 5', 5);
INSERT INTO opm.audit_parameter_record VALUES (60001, false, 1111111, 'Account', 'firstName', 'old value 6', 'new value 6', 6);
INSERT INTO opm.audit_parameter_record VALUES (70001, false, 1111111, 'Account', 'lastName', 'old value 7', 'new value 7', 7);
INSERT INTO opm.audit_parameter_record VALUES (80001, false, 1111111, 'Account', 'middleInitial', 'old value 8', 'new value 8', 8);

INSERT INTO opm.audit_record VALUES (9, false, 'USER1', '127.0.0.1', 'some description', 'Master: Post 9/82 Redeposit Changed', '2014-01-02');
INSERT INTO opm.audit_parameter_record VALUES (90001, false, 1111111, 'Account', 'post982Redeposit', 'old value 4', 'new value 4', 9);
INSERT INTO opm.audit_record VALUES (10, false, 'USER1', '127.0.0.1', 'some description', 'Master: Post 9/82 Redeposit Changed', '2014-01-03');
INSERT INTO opm.audit_parameter_record VALUES (100001, false, 1111111, 'Account', 'post982Redeposit', 'old value 4', 'new value 4', 10);
INSERT INTO opm.audit_record VALUES (11, false, 'USER1', '127.0.0.1', 'some description', 'Master: Post 9/82 Redeposit Changed', '2014-01-04');
INSERT INTO opm.audit_parameter_record VALUES (110001, false, 1111111, 'Account', 'post982Redeposit', 'old value 4', 'new value 4', 11);
INSERT INTO opm.audit_record VALUES (12, false, 'USER1', '127.0.0.1', 'some description', 'Master: Post 9/82 Redeposit Changed', '2014-01-05');
INSERT INTO opm.audit_parameter_record VALUES (120001, false, 1111111, 'Account', 'post982Redeposit', 'old value 4', 'new value 4', 12);
INSERT INTO opm.audit_record VALUES (13, false, 'USER1', '127.0.0.1', 'some description', 'Master: Post 9/82 Redeposit Changed', '2014-01-06');
INSERT INTO opm.audit_parameter_record VALUES (130001, false, 1111111, 'Account', 'post982Redeposit', 'old value 4', 'new value 4', 13);
INSERT INTO opm.audit_record VALUES (14, false, 'USER1', '127.0.0.1', 'some description', 'Master: Post 9/82 Redeposit Changed', '2014-01-07');
INSERT INTO opm.audit_parameter_record VALUES (140001, false, 1111111, 'Account', 'post982Redeposit', 'old value 4', 'new value 4', 14);
INSERT INTO opm.audit_record VALUES (15, false, 'USER1', '127.0.0.1', 'some description', 'Master: Post 9/82 Redeposit Changed', '2014-01-08');
INSERT INTO opm.audit_parameter_record VALUES (150001, false, 1111111, 'Account', 'post982Redeposit', 'old value 4', 'new value 4', 15);
INSERT INTO opm.audit_record VALUES (16, false, 'USER1', '127.0.0.1', 'some description', 'Master: Post 9/82 Redeposit Changed', '2014-01-09');
INSERT INTO opm.audit_parameter_record VALUES (160001, false, 1111111, 'Account', 'post982Redeposit', 'old value 4', 'new value 4', 16);
INSERT INTO opm.audit_record VALUES (17, false, 'USER1', '127.0.0.1', 'some description', 'Master: Post 9/82 Redeposit Changed', '2014-01-10');
INSERT INTO opm.audit_parameter_record VALUES (170001, false, 1111111, 'Account', 'post982Redeposit', 'old value 4', 'new value 4', 17);
INSERT INTO opm.audit_record VALUES (18, false, 'USER1', '127.0.0.1', 'some description', 'Master: Post 9/82 Redeposit Changed', '2014-01-11');
INSERT INTO opm.audit_parameter_record VALUES (180001, false, 1111111, 'Account', 'post982Redeposit', 'old value 4', 'new value 4', 18);
INSERT INTO opm.audit_record VALUES (19, false, 'USER1', '127.0.0.1', 'some description', 'Master: Post 9/82 Redeposit Changed', '2014-01-12');
INSERT INTO opm.audit_parameter_record VALUES (190001, false, 1111111, 'Account', 'post982Redeposit', 'old value 4', 'new value 4', 19);
INSERT INTO opm.audit_record VALUES (20, false, 'USER1', '127.0.0.1', 'some description', 'Master: Post 9/82 Redeposit Changed', '2014-01-13');
INSERT INTO opm.audit_parameter_record VALUES (200001, false, 1111111, 'Account', 'post982Redeposit', 'old value 4', 'new value 4', 20);

UPDATE opm.invoice_data SET account_note_type = 'ACH_OVER_PAYMENT';

INSERT INTO opm.user_status VALUES (1, false, 'S1');
INSERT INTO opm.app_user VALUES (1, false, 'SUP1', null, '\\NET\USER1', 2, 'SUPERVISOR', '1', 'SUPERVISOR1@host.com', null, 1, null);
INSERT INTO opm.app_user VALUES (2, false, 'USER1', null, '\\NET\USER1', 1, 'USER', '1', 'USER1@host.com', null, 1, 1);
UPDATE opm.account SET claim_officer = 'USER1';
