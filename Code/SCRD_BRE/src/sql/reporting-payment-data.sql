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

INSERT INTO opm.payment(id, deleted, batch_number, block_number, sequence_number, 
claim_number, payment_status_id, deposit_date, amount, ssn, 
import_id, sequence, apply_designation_id, apply_to_gl, note, 
transaction_key, master_account_status_id, approval_user, approval_status, payment_type, 
ach, account_status_id, master_claimant_birthday, status_date)
VALUES(1, false, '000', '00', '01',
'1111111', 23, '2014-01-01', 20, '123456789',
0, 1, 1, false, 'payment note',
1, 1, 'approver1', 'APPROVED', 'ORDINARY',
true, 1, '1982-01-01', '2014-01-01');

INSERT INTO opm.payment(id, deleted, batch_number, block_number, sequence_number, 
claim_number, payment_status_id, deposit_date, amount, ssn, 
import_id, sequence, apply_designation_id, apply_to_gl, note, 
transaction_key, master_account_status_id, approval_user, approval_status, payment_type, 
ach, account_status_id, master_claimant_birthday, status_date)
VALUES(2, false, '000', '00', '01',
'1111111', 23, '2014-01-01', 44, '123456789',
0, 2, 1, false, 'payment note',
1, 1, 'approver1', 'APPROVED', 'ORDINARY',
false, 1, '1982-01-01', '2014-01-01');

INSERT INTO opm.account(id, deleted, claim_number, plan_type, form_type_id, account_holder_id, account_status_id, grace, frozen, returned_from_record_date, billing_summary_id)
 VALUES (1, false, '1111111', 1, 1, 1, 1, false, false, '2014-01-01', 1);
INSERT INTO opm.account(id, deleted, claim_number, plan_type, form_type_id, account_holder_id, account_status_id, grace, frozen, returned_from_record_date)
 VALUES (2, false, '2222222', 1, 2, 1, 1, false, false, '2014-01-01');
INSERT INTO opm.account(id, deleted, claim_number, plan_type, form_type_id, account_holder_id, account_status_id, grace, frozen, returned_from_record_date)
 VALUES (3, false, '3333333', 1, 1, 1, 1, false, false, '2014-01-01');
    
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (1, false, '111', '11', '11', 
		'1111111', '1982-01-01', 101.01, '2014-01-01', '10', 
		'2014-01-01', 'userkey1', 'DEFAULT_USER_ORDER', true, '1111111', 
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
		false, false, false, 0, 1);

INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (2, false, '111', '22', '11', 
		'1111111', '1982-01-02', 101.01, '2014-01-02', '2', 
		'2014-01-02', 'userkey2', 'DEFAULT_USER_ORDER', true, '2222222', 
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
		false, false, false, 0, 1);
		
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (3, false, '111', '22', '12', 
		'1111111', '1982-01-02', 101.01, '2014-01-02', '11', 
		'2014-01-02', 'userkey2', 'DEFAULT_USER_ORDER', true, '2222222', 
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
		false, false, false, 0, 1);
		
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
	scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
	pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, 
	user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed, pay_transaction_key)
VALUES (4, false, '111', '22', '23', 
		'1111111', '1982-01-03', 101.01, '2014-01-02', '59', 
		'2014-01-02', 'userkey2', 'DEFAULT_USER_ORDER', true, '3333333', 
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
		'1111111', '1982-01-03', 101.01, '2014-01-02', '34', 
		'2014-01-02', 'userkey2', 'DEFAULT_USER_ORDER', true, '3333333', 
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
		'1111111', '1982-01-01', 101.01, '2014-01-01', '10', 
		'2014-01-01', 'userkey1', 'DEFAULT_USER_ORDER', true, '1111111', 
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
		'1111111', '1982-01-01', 101.01, '2014-01-01', '19', 
		'2014-01-01', 'userkey1', 'DEFAULT_USER_ORDER', true, '1111111', 
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
		'1111111', '1982-01-02', 101.01, '2014-01-02', '27', 
		'2014-01-02', 'userkey2', 'DEFAULT_USER_ORDER', true, '2222222', 
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
		'1111111', '1982-01-02', 101.01, '2014-01-02', '23', 
		'2014-01-02', 'userkey2', 'DEFAULT_USER_ORDER', true, '2222222', 
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
		'1111111', '1982-01-03', 101.01, '2014-01-02', '73', 
		'2014-01-02', 'userkey2', 'DEFAULT_USER_ORDER', true, '3333333', 
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
		'1111111', '1982-01-03', 101.01, '2014-01-02', '21', 
		'2014-01-02', 'userkey2', 'DEFAULT_USER_ORDER', true, '3333333', 
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
		'1111111', '1982-01-01', 101.01, '2014-01-01', '19', 
		'2014-01-01', 'userkey1', 'DEFAULT_USER_ORDER', true, '1111111', 
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
		'1111111', '1982-01-01', 101.01, '2014-01-01', '12', 
		'2014-01-01', 'userkey1', 'DEFAULT_USER_ORDER', true, '1111111', 
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
		'1111111', '1982-01-02', 101.01, '2014-01-02', '54', 
		'2014-01-02', 'userkey2', 'DEFAULT_USER_ORDER', true, '2222222', 
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
		'1111111', '1982-01-02', 101.01, '2014-01-02', '54', 
		'2014-01-02', 'userkey2', 'DEFAULT_USER_ORDER', true, '2222222', 
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
		'1111111', '1982-01-03', 101.01, '2014-01-02', '12', 
		'2014-01-02', 'userkey2', 'DEFAULT_USER_ORDER', true, '3333333', 
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

INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (1, false, '1', 'Ret Type 1', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (2, false, '2', 'Ret Type 2', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (3, false, '3', 'Ret Type 3', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (4, false, '4', 'Ret Type 1', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (5, false, '5', 'Ret Type 2', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (6, false, '6', 'Ret Type 3', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (7, false, '7', 'Ret Type 1', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (8, false, '8', 'Ret Type 1', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (9, false, '9', 'Ret Type 3', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (10, false, '10', 'Ret Type 1', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (11, false, '11', 'Ret Type 2', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (12, false, '12', 'Ret Type 2', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (13, false, '13', 'Ret Type 1', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (14, false, '14', 'Ret Type 1', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (15, false, '15', 'Ret Type 2', 50.50, 45.45, 20.20); 
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs)
VALUES (16, false, '16', 'Ret Type 1', 50.50, 45.45, 20.20); 
