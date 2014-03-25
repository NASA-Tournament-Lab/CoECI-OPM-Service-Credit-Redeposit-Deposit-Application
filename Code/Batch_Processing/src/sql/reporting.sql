INSERT INTO opm.account_status (id, deleted, name) VALUES (8, false, 'Closed');
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, last_action, last_action_time) VALUES(800001, false, 'FERS', 1, 1, 354312.44, 1, 8, true, true, 'Tina Sullivan', '2013-11-29', '2013-02-18', '1926-10-21', 1, '9092191', 'Provide final fix', '2013-11-15');
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, last_action, last_action_time) VALUES(800002, false, 'FERS', 1, 1, 354312.44, 1, 8, true, true, 'Tina Sullivan', '2013-11-29', '2013-02-18', '1926-10-21', 1, '9092191', 'Provide final fix', '2013-11-15');

INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number) 
VALUES (3001,  false,  16, '1978-04-30', '2013-10-30',  10553.1,  'user1', '1970-10-27',  971, '2013-07-14', '2013-07-20',  5,  false,  'ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec',  '8d3ad280-3831-43fb-b46d-672e10b730a6',  false,  17386.72,  1, '1969-08-13',  1,  35091.56,  1611,  16576.52,  17120.34,  13325.97,  18449.53,  'bhill',  'PENDING',  'SUSPENDED_PAYMENT',  800001,  '235',  '442',  '1',  '900302',  '046-44-6641',  '622',  '919259');
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number) 
VALUES (3002,  false,  16, '1978-04-30', '2013-10-30',  10553.1,  'user1', '1970-10-27',  971, '2013-07-14', '2013-07-20',  5,  false,  'ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec',  '8d3ad280-3831-43fb-b46d-672e10b730a6',  false,  17386.72,  1, '1969-08-13',  1,  35091.56,  1611,  16576.52,  17120.34,  13325.97,  18449.53,  'bhill',  'PENDING',  'SUSPENDED_PAYMENT',  800001,  '235',  '442',  '2',  '900302',  '046-44-6641',  '622',  '919259');
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number) 
VALUES (3003,  false,  16, '1978-04-30', '2013-10-30',  10553.1,  'user1', '1970-10-27',  971, '2013-07-14', '2013-07-20',  5,  false,  'ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec',  '8d3ad280-3831-43fb-b46d-672e10b730a6',  false,  17386.72,  1, '1969-08-13',  1,  35091.56,  1611,  16576.52,  17120.34,  13325.97,  18449.53,  'bhill',  'PENDING',  'SUSPENDED_PAYMENT',  800002,  '235',  '442',  '3',  '900302',  '046-44-6641',  '622',  '919259');
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number) 
VALUES (3004,  false,  16, '1978-04-30', '2013-10-30',  10553.1,  'user1', '1970-10-27',  971, '2013-07-14', '2013-07-20',  5,  false,  'ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec',  '8d3ad280-3831-43fb-b46d-672e10b730a6',  false,  17386.72,  1, '1969-08-13',  1,  35091.56,  1611,  16576.52,  17120.34,  13325.97,  18449.53,  'bhill',  'PENDING',  'SUSPENDED_PAYMENT',  800002,  '235',  '442',  '4',  '900302',  '046-44-6641',  '622',  '919259');
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number) 
VALUES (3005,  false,  16, '1978-04-30', '2013-10-30',  10553.1,  'user1', '1970-10-27',  971, '2013-07-14', '2013-07-20',  5,  false,  'ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec',  '8d3ad280-3831-43fb-b46d-672e10b730a6',  false,  17386.72,  1, '1969-08-13',  1,  35091.56,  1611,  16576.52,  17120.34,  13325.97,  18449.53,  'bhill',  'PENDING',  'SUSPENDED_PAYMENT',  800002,  '235',  '442',  '5',  '900302',  '046-44-6641',  '622',  '919259');


---------------------------------------------------
-----   refund_transaction table
---------------------------------------------------
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (1, false, '79efe83e-6a3a-4cec-9512-b4ab0a55cc9c', 20.00, '919259', '01/01/2014', 'user1', 1);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (2, false, 'b74210a7-1157-4d14-a5d3-452345951f18', 20.00, '919259', '01/02/2014', 'user1', 3);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (3, false, '55954f24-2512-4507-a667-fab718cc34db', 37952.91, '919259', '01/03/2014', 'user2', 4);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (4, false, '78bb2c8d-5eb4-40b1-a5f4-fa5372a7e2f1', 22321.38, '919259', '01/04/2014', 'user2', 3);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (5, false, '3e7113ed-7dd5-4722-b48d-14937b06e226', 18680.04, '919259', '01/05/2014', 'user3', 1);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (6, false, 'dad3358a-fcb8-4c82-9ea9-27ced4e88018', 35564.68, '919259', '01/06/2014', 'user3', 2);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (7, false, '21fc6719-798c-4865-a6b2-ff1dafc396cb', 30258.12, '919259', '01/07/2014', 'user4', 2);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (8, false, '25e1f10d-dbab-465e-9f21-43396775ad39', 28329.64, '919259', '01/08/2014', 'user4', 3);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (9, false, '04fde94b-80b3-44c4-ba5b-3255f0b0303c', 37769.76, '919259', '01/09/2014', 'user5', 1);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (10, false, '601b7e07-780b-4aef-8634-0a098c34fd84', 17528.01, '919259', '01/10/2014', 'user5', 3);


INSERT INTO opm.audit_batch_log_id (id, audit_batch_log_id, batch_date, batch_number, deleted)
VALUES (101, '1', '2015-01-01', 101, false); 
INSERT INTO opm.audit_batch_log_id (id, audit_batch_log_id, batch_date, batch_number, deleted)
VALUES (102, '1', '2015-01-02', 102, false); 

INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (9001, 101, 1, 0, 'file name', false, 'VALID_C_TRANSACTION', false);
INSERT INTO opm.all_details (id, deleted, pay_transaction_key, scm_retirement_type_description, total_non_postal_fers, total_postal_fers, total_csrs, payment_type, payment_date, gl_filler, gl_code, gl_accounting_code, revenue_source_code, agency, print_date, recipient_amount, scm_claim_number)
VALUES (1, false, '1', 'Ret Type 1', 50.50, 45.45, 20.20, 'A1', '2014-03-03', 'gl filler 1', 'gl code 1', 'acct code 1', 'src code 1', 'agency 1', '2014-03-05', 64, '919259'); 

INSERT INTO opm.audit_record VALUES (101, false, 'USER1', '127.0.0.1', 'some description', 'Lock Box Import Error', '2015-01-01');
INSERT INTO opm.audit_record VALUES (102, false, 'USER1', '127.0.0.1', 'some description', 'Lock Box Import Error', '2015-01-01');
INSERT INTO opm.audit_record VALUES (103, false, 'USER1', '127.0.0.1', 'some description', 'Lock Box Import Error', '2015-01-01');
INSERT INTO opm.audit_record VALUES (104, false, 'USER1', '127.0.0.1', 'some description', 'Lock Box Import Error', '2015-01-02');
INSERT INTO opm.audit_record VALUES (105, false, 'USER1', '127.0.0.1', 'some description', 'Lock Box Import Error', '2015-01-02');
INSERT INTO opm.audit_record VALUES (106, false, 'USER1', '127.0.0.1', 'some description', 'Lock Box Import Error', '2015-01-02');
INSERT INTO opm.audit_record VALUES (107, false, 'USER1', '127.0.0.1', 'some description', 'Lock Box Import Error', '2015-01-02');

INSERT INTO opm.audit_record VALUES (207, false, 'USER1', '127.0.0.1', 'some description', 'test', '2014-01-02');
INSERT INTO opm.audit_record VALUES (208, false, 'USER2', '127.0.0.1', 'some description', 'test', '2014-02-03');

INSERT INTO opm.audit_parameter_record VALUES (30001, false, 919259, 'Account', 'errorCode', 'a', 'b', 207);
INSERT INTO opm.audit_parameter_record VALUES (30002, false, 919259, 'Account', 'errorMessage', 'c', 'd', 207);
INSERT INTO opm.audit_parameter_record VALUES (30003, false, 919259, 'Account', 'description', 'e', 'f', 207);
INSERT INTO opm.audit_parameter_record VALUES (30004, false, 919259, 'Account', 'errorCode', 'g', 'h', 208);
INSERT INTO opm.audit_parameter_record VALUES (30005, false, 919259, 'Account', 'errorMessage', 'i', 'j', 208);
INSERT INTO opm.audit_parameter_record VALUES (30006, false, 919259, 'Account', 'description', 'k', 'l', 208);


INSERT INTO opm.audit_parameter_record VALUES (10001, false, 1111111, 'Lock Box Import Error', 'errorCode', ' ', 'R', 101);
INSERT INTO opm.audit_parameter_record VALUES (10002, false, 1111111, 'Lock Box Import Error', 'errorMessage', ' ', 'R60610201 915785010100 Bad Address or name', 101);
INSERT INTO opm.audit_parameter_record VALUES (10003, false, 1111111, 'Lock Box Import Error', 'description', ' ', 'ValidC_NoChangeOccurred: Change command did not update database for CSD #', 101);

INSERT INTO opm.audit_parameter_record VALUES (10004, false, 1111111, 'Lock Box Import Error', 'errorCode', ' ', 'R', 102);
INSERT INTO opm.audit_parameter_record VALUES (10005, false, 1111111, 'Lock Box Import Error', 'errorMessage', ' ', 'R60610201 915785010100 Bad Address or name', 102);
INSERT INTO opm.audit_parameter_record VALUES (10006, false, 1111111, 'Lock Box Import Error', 'description', ' ', 'ValidC_NoChangeOccurred: Change command did not update database for CSD #', 102);

INSERT INTO opm.audit_parameter_record VALUES (10007, false, 1111111, 'Lock Box Import Error', 'errorCode', ' ', 'R', 103);
INSERT INTO opm.audit_parameter_record VALUES (10008, false, 1111111, 'Lock Box Import Error', 'errorMessage', ' ', 'R60610201 915785010100 Bad Address or name', 103);
INSERT INTO opm.audit_parameter_record VALUES (10009, false, 1111111, 'Lock Box Import Error', 'description', ' ', 'ValidC_NoChangeOccurred: Change command did not update database for CSD #', 103);

INSERT INTO opm.audit_parameter_record VALUES (10010, false, 1111111, 'Lock Box Import Error', 'errorCode', ' ', 'R', 104);
INSERT INTO opm.audit_parameter_record VALUES (10011, false, 1111111, 'Lock Box Import Error', 'errorMessage', ' ', 'R60610201 915785010100 Bad Address or name', 104);
INSERT INTO opm.audit_parameter_record VALUES (10012, false, 1111111, 'Lock Box Import Error', 'description', ' ', 'ValidC_NoChangeOccurred: Change command did not update database for CSD #', 104);

INSERT INTO opm.audit_parameter_record VALUES (10013, false, 1111111, 'Lock Box Import Error', 'errorCode', ' ', 'R', 105);
INSERT INTO opm.audit_parameter_record VALUES (10014, false, 1111111, 'Lock Box Import Error', 'errorMessage', ' ', 'R60610201 915785010100 Bad Address or name', 105);
INSERT INTO opm.audit_parameter_record VALUES (10015, false, 1111111, 'Lock Box Import Error', 'description', ' ', 'ValidC_NoChangeOccurred: Change command did not update database for CSD #', 105);

INSERT INTO opm.audit_parameter_record VALUES (10016, false, 1111111, 'Lock Box Import Error', 'errorCode', ' ', 'R', 106);
INSERT INTO opm.audit_parameter_record VALUES (10017, false, 1111111, 'Lock Box Import Error', 'errorMessage', ' ', 'R60610201 915785010100 Bad Address or name', 106);
INSERT INTO opm.audit_parameter_record VALUES (10018, false, 1111111, 'Lock Box Import Error', 'description', ' ', 'ValidC_NoChangeOccurred: Change command did not update database for CSD #', 106);

INSERT INTO opm.audit_parameter_record VALUES (10019, false, 2222222, 'Lock Box Import Error', 'errorCode', ' ', 'R', 107);
INSERT INTO opm.audit_parameter_record VALUES (10020, false, 2222222, 'Lock Box Import Error', 'errorMessage', ' ', 'R60610201 915785010100 Bad Address or name', 107);
INSERT INTO opm.audit_parameter_record VALUES (10021, false, 2222222, 'Lock Box Import Error', 'description', ' ', 'ValidC_NoChangeOccurred: Change command did not update database for CSD #', 107);

INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (8001,  false, '2013-03-14', '2003-05-21', '1999-09-10', '2013-10-26',  'D', '2012-12-24',  true);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(301, false, 'FERS_DEPOSIT', 13571.33, 15298.61, 8449.61, 20420.33, 1, 8001, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(302, false, 'FERS_REDEPOSIT', 13548.24, 18136.03, 7149.38, 24534.88, 2, 8001, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(303, false, 'CSRS_POST_3_91_REDEPOSIT', 17656.0, 16621.32, 5798.56, 28478.76, 3, 8001, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(304, false, 'CSRS_POST_82_PRE_91_REDEPOSIT', 16278.99, 16237.45, 6514.87, 26001.57, 4, 8001, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(305, false, 'CSRS_PRE_10_82_REDEPOSIT', 14064.74, 19096.39, 6811.78, 26349.35, 5, 8001, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(306, false, 'CSRS_POST_10_82_DEPOSIT', 18754.47, 18341.08, 7658.91, 29436.64, 6, 8001, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(307, false, 'CSRS_PRE_10_82_DEPOSIT', 18830.3, 9295.31, 5398.97, 22726.64, 7, 8001, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(308, false, 'FERS_PEACE_CORPS', 10383.14, 17012.48, 7644.43, 19751.19, 8, 8001, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(309, false, 'CSRS_PEACE_CORPS', 10199.26, 9459.89, 6778.98, 12880.17, 9, 8001, false);

INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number) VALUES (8001,  false,  16, '1978-04-30', '2013-10-30',  10553.1,  'user1', '1970-10-27',  971, '2013-07-14', '2013-07-20',  5,  false,  'ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec',  '8d3ad280-3831-43fb-b46d-672e10b730a6',  false,  17386.72,  1, '1969-08-13',  1,  35091.56,  1611,  16576.52,  17120.34,  13325.97,  18449.53,  'bhill',  'PENDING',  'SUSPENDED_PAYMENT',  1027,  '235',  '442',  '1',  '900302',  '046-44-6641',  '622',  '919259');
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number) VALUES (8002,  false,  16, '1978-04-30', '2013-10-30',  10553.1,  'user1', '1970-10-27',  971, '2013-07-14', '2013-07-20',  5,  false,  'ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec',  '8d3ad280-3831-43fb-b46d-672e10b730a6',  false,  17386.72,  1, '1969-08-13',  1,  35091.56,  1611,  16576.52,  17120.34,  13325.97,  18449.53,  'bhill',  'PENDING',  'SUSPENDED_PAYMENT',  1027,  '235',  '442',  '2',  '900302',  '046-44-6641',  '622',  '919259');
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number) VALUES (8003,  false,  16, '1978-04-30', '2013-10-30',  10553.1,  'user1', '1970-10-27',  971, '2013-07-14', '2013-07-20',  5,  false,  'ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec',  '8d3ad280-3831-43fb-b46d-672e10b730a6',  false,  17386.72,  1, '1969-08-13',  1,  35091.56,  1611,  16576.52,  17120.34,  13325.97,  18449.53,  'bhill',  'PENDING',  'SUSPENDED_PAYMENT',  1027,  '235',  '442',  '3',  '900302',  '046-44-6641',  '622',  '919259');
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number) VALUES (8004,  false,  16, '1978-04-30', '2013-10-30',  10553.1,  'user1', '1970-10-27',  971, '2013-07-14', '2013-07-20',  5,  false,  'ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec',  '8d3ad280-3831-43fb-b46d-672e10b730a6',  false,  17386.72,  1, '1969-08-13',  1,  35091.56,  1611,  16576.52,  17120.34,  13325.97,  18449.53,  'bhill',  'PENDING',  'SUSPENDED_PAYMENT',  1027,  '235',  '442',  '4',  '900302',  '046-44-6641',  '622',  '919259');
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number) VALUES (8005,  false,  16, '1978-04-30', '2013-10-30',  10553.1,  'user1', '1970-10-27',  971, '2013-07-14', '2013-07-20',  5,  false,  'ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec',  '8d3ad280-3831-43fb-b46d-672e10b730a6',  false,  17386.72,  1, '1969-08-13',  1,  35091.56,  1611,  16576.52,  17120.34,  13325.97,  18449.53,  'bhill',  'PENDING',  'SUSPENDED_PAYMENT',  1027,  '235',  '442',  '5',  '900302',  '046-44-6641',  '622',  '919259');

UPDATE opm.account set billing_summary_id=8001 where claim_number ='919259';

---------------------------------------------------
-----   deduction_rate table
---------------------------------------------------
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (10001, false, 'service type 1', 1, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (10002, false, 'service type 1', 1, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (10003, false, 'service type 1', 1, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (10004, false, 'service type 1', 1, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (10005, false, 'service type 2', 2, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (10006, false, 'service type 2', 2, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (10007, false, 'service type 3', 1, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (10008, false, 'service type 3', 1, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);

-------------------------------------------
-----   letter table
-------------------------------------------
INSERT INTO opm.letter(id, deleted, name, content) VALUES (1, false, 'Letter1', 'Content of Letter1');
INSERT INTO opm.letter(id, deleted, name, content) VALUES (2, false, 'Letter2', 'Content of Letter2');
INSERT INTO opm.letter(id, deleted, name, content) VALUES (3, false, 'Letter3', 'Content of Letter3');
INSERT INTO opm.letter(id, deleted, name, content) VALUES (4, false, 'Letter4', 'Content of Letter4');
INSERT INTO opm.letter(id, deleted, name, content) VALUES (5, false, 'Letter5', 'Content of Letter5');

-------------------------------------------
-----   reference table
-------------------------------------------
INSERT INTO opm.reference(id, deleted, name, content) VALUES (1, false, 'Reference1', 'Content of Reference1');
INSERT INTO opm.reference(id, deleted, name, content) VALUES (2, false, 'Reference2', 'Content of Reference2');
INSERT INTO opm.reference(id, deleted, name, content) VALUES (3, false, 'Reference3', 'Content of Reference3');
INSERT INTO opm.reference(id, deleted, name, content) VALUES (4, false, 'Reference4', 'Content of Reference4');
INSERT INTO opm.reference(id, deleted, name, content) VALUES (5, false, 'Reference5', 'Content of Reference5');
-------------------------------------------
-----   audit_record table (from daily)
-------------------------------------------
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (1, false, 'user1', 'ip', 'Closed Account', '12/01/2013', 'description');
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (2, false, 'user2', 'ip', 'Closed Account', '12/01/2013 01:00:00', 'description');
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (3, false, 'user2', 'ip', 'Closed Account', '12/01/2013 02:00:00', 'description');
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (4, false, 'user4', 'ip', 'New Account', '12/02/2013 01:00:00', 'description');
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (5, false, 'user5', 'ip', 'New Account', '12/03/2013 03:00:00', 'description');
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (6, false, 'user1', 'ip', 'New Account', '12/01/2013', 'description');
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (7, false, 'user2', 'ip', 'Initial Bill Triggered', '12/01/2013', 'description');
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (8, false, 'user3', 'ip', 'Initial Bill Triggered', '12/01/2013', 'description');
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (9, false, 'user4', 'ip', 'Initial Bill Triggered', '12/01/2013', 'description');

-------------------------------------------
-----   audit_parameter_record table
-------------------------------------------
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (1, false, 1, 'item', 'databaseTable', 'previous', 'table', 1);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (2, false, 1, 'item', 'property', 'previous', 'new', 1);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (3, false, 2, 'item', 'property', 'previous', 'new', 2);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (4, false, 2, 'item', 'property', 'previous', 'new', 2);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (5, false, 3, 'item', 'property', 'previous', 'new', 3);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (6, false, 3, 'item', 'property', 'previous', 'new', 3);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (7, false, 4, 'item', 'property', 'previous', 'new', 4);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (8, false, 4, 'item', 'property', 'previous', 'new', 4);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (9, false, 5, 'item', 'property', 'previous', 'new', 5);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (10, false, 5, 'item', 'property', 'previous', 'new', 5);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (11, false, 2, 'item', 'payCode', 'previous', 'payCode', 2);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (12, false, 2, 'item', 'accountStatus', 'previous', 'accountStatus', 2);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (13, false, 2, 'item', 'lastPay', 'previous', '01/01/2014', 2);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (14, false, 2, 'item', 'deposit', 'previous', '30.00', 2);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (15, false, 2, 'item', 'additionalInterest', 'previous', '20.00', 2);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (16, false, 2, 'item', 'payment', 'previous', '10.00', 2);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (17, false, 2, 'item', 'subTotals', 'previous', '40.00', 2);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (18, false, 2, 'item', 'grandTotal', 'previous', '40.00', 2);

INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (19, false, 2, 'item', 'payCode', 'previous', 'payCode', 3);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (20, false, 2, 'item', 'accountStatus', 'previous', 'accountStatus', 3);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (21, false, 2, 'item', 'lastPay', 'previous', '01/01/2014', 3);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (22, false, 2, 'item', 'deposit', 'previous', '30.00', 3);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (23, false, 2, 'item', 'additionalInterest', 'previous', '20.00', 3);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (24, false, 2, 'item', 'payment', 'previous', '10.00', 3);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (25, false, 2, 'item', 'subTotals', 'previous', '40.00', 3);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (26, false, 2, 'item', 'grandTotal', 'previous', '40.00', 3);



INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (27, false, 2, 'item', 'payCode', 'previous', 'payCode', 4);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (28, false, 2, 'item', 'accountStatus', 'previous', 'accountStatus', 4);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (29, false, 2, 'item', 'lastPay', 'previous', '01/01/2014', 4);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (30, false, 2, 'item', 'deposit', 'previous', '30.00', 4);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (31, false, 2, 'item', 'additionalInterest', 'previous', '20.00', 4);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (32, false, 2, 'item', 'payment', 'previous', '10.00', 4);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (33, false, 2, 'item', 'subTotals', 'previous', '40.00', 4);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (34, false, 2, 'item', 'grandTotal', 'previous', '40.00', 4);

INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (35, false, 2, 'item', 'payCode', 'previous', 'payCode', 5);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (36, false, 2, 'item', 'accountStatus', 'previous', 'accountStatus', 5);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (37, false, 2, 'item', 'lastPay', 'previous', '01/01/2014', 5);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (38, false, 2, 'item', 'deposit', 'previous', '30.00', 5);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (39, false, 2, 'item', 'additionalInterest', 'previous', '20.00', 5);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (40, false, 2, 'item', 'payment', 'previous', '10.00', 5);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (41, false, 2, 'item', 'subTotals', 'previous', '40.00', 5);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (42, false, 2, 'item', 'grandTotal', 'previous', '40.00', 5);

-------------------------------------------
-----   audit_parameter_record table
-------------------------------------------
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (819, false, 1, 'item', 'claim number', '977602', '977602', 1);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (820, false, 1, 'item', 'claim number', '977602', '977602', 2);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (821, false, 10, 'item', 'claim number', '977602', '977602', 3);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (822, false, 10, 'item', 'claim number', '977602', '977602', 4);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (823, false, 3, 'item', 'claim number', '977602', '977602', 5);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (824, false, 3, 'item', 'claim number', '977602', '977602', 6);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (825, false, 4, 'item', 'claim number', '977602', '977602', 7);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (826, false, 4, 'item', 'claim number', '977602', '977602', 8);
INSERT INTO opm.audit_parameter_record (id, deleted, item_id, item_type, property_name, previous_value, new_value, audit_record_id) VALUES (827, false, 5, 'item', 'claim number', '977602', '977602', 9);


INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(1, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 1, '1.11', '1.11', '1.11', '1.11', 1, 1, 1, 'INSERTED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(2, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 1, '2.22', '2.22', '2.22', '2.22', 1, 1, 1, 'INSERTED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(3, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 2, '3.33', '3.33', '3.33', '3.33', 1, 1, 1, 'DELETED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(4, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 2, '4.44', '4.44', '4.44', '4.44', 1, 1, 1, 'UPDATED', 'CSRS_POST_10_82_DEPOSIT', 1);

INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(5, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 1, '1.11', '1.11', '1.11', '1.11', 2, 1, 1, 'INSERTED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(6, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 1, '2.22', '2.22', '2.22', '2.22', 2, 1, 1, 'INSERTED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(7, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 2, '3.33', '3.33', '3.33', '3.33', 2, 1, 1, 'DELETED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(8, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 2, '4.44', '4.44', '4.44', '4.44', 2, 1, 1, 'UPDATED', 'CSRS_POST_10_82_DEPOSIT', 1);


INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(9, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 1, '1.11', '1.11', '1.11', '1.11', 3, 1, 1, 'INSERTED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(10, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 1, '2.22', '2.22', '2.22', '2.22', 3, 1, 1, 'INSERTED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(11, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 2, '3.33', '3.33', '3.33', '3.33', 3, 1, 1, 'DELETED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(12, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 2, '4.44', '4.44', '4.44', '4.44', 3, 1, 1, 'UPDATED', 'CSRS_POST_10_82_DEPOSIT', 1);


INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(13, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 1, '1.11', '1.11', '1.11', '1.11', 4, 1, 1, 'INSERTED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(14, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 1, '2.22', '2.22', '2.22', '2.22', 4, 1, 1, 'INSERTED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(15, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 2, '3.33', '3.33', '3.33', '3.33', 4, 1, 1, 'DELETED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(16, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 2, '4.44', '4.44', '4.44', '4.44', 4, 1, 1, 'UPDATED', 'CSRS_POST_10_82_DEPOSIT', 1);


---------------------------------------------------
-----   payment_transaction table
---------------------------------------------------
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1001, false, 1, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 1, '01/01/2014', 1, '123', true, '1234567', false, true, 1, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1002, false, 2, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 2, '01/01/2014', 1, '123', true, '1234567', false, true, 2, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1003, false, 3, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 3, '01/01/2014', 1, '123', true, '1234567', false, true, 10, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1004, false, 4, '123', '123', '123', '123', '01/01/2014', '100.000', '01/01/2014', 4, '01/01/2014', 1, '123', true, '1234567', true, true, 11, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1005, false, 5, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 5, '01/01/2014', 1, '123', true, '1234567', true, true, 14, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1006, false, 6, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 1, '01/01/2014', 1, '123', true, '1234567', false, true, 1, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1007, false, 7, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 2, '01/01/2014', 1, '123', true, '1234567', false, true, 2, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1008, false, 8, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 3, '01/01/2014', 1, '123', true, '1234567', false, true, 10, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1009, false, 9, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 4, '01/01/2014', 1, '123', true, '1234567', true, true, 11, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1010, false, 10, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 5, '01/01/2014', 1, '123', true, '1234567', true, true, 14, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1011, false, 11, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 1, '01/01/2014', 1, '123', true, '1234567', false, true, 1, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1012, false, 12, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 2, '01/01/2014', 1, '123', true, '1234567', false, true, 2, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1013, false, 13, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 3, '01/01/2014', 1, '123', true, '1234567', false, true, 10, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1014, false, 14, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 4, '01/01/2014', 1, '123', true, '1234567', true, true, 11, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1015, false, 15, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 5, '01/01/2014', 1, '123', true, '1234567', true, true, 14, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1016, false, 16, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 6, '01/01/2014', 1, '123', true, '1234567', false, true, 22, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1017, false, 17, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 7, '01/01/2014', 1, '123', true, '1234567', false, true, 21, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1018, false, 18, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 8, '01/01/2014', 1, '123', true, '1234567', false, true, 25, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1019, false, 19, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 9, '01/01/2014', 1, '123', true, '1234567', true, true, 61, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1020, false, 20, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 10, '01/01/2014', 1, '123', true, '1234567', true, true, 51, true, true);

UPDATE opm.app_user set supervisor_id=2 where id=1;

-------------------------------------------
-----   audit_batch_log_id table
-------------------------------------------
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (1, false, 1, '01/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (2, false, 2, '01/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (3, false, 3, '01/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (4, false, 4, '01/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (5, false, 5, '01/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (6, false, 6, '02/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (7, false, 7, '02/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (8, false, 8, '02/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (9, false, 9, '02/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (10, false, 10, '02/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (11, false, 11, '03/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (12, false, 12, '03/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (13, false, 13, '03/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (14, false, 14, '03/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (15, false, 15, '03/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (16, false, 16, '04/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (17, false, 17, '04/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (18, false, 18, '04/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (19, false, 19, '04/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (20, false, 20, '04/01/2014');

-------------------------------------------
-----   mainframe_import table
-------------------------------------------
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (1, 1, 1, 0, 'file name', false, 'VALID_C_TRANSACTION', false);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (2, 2, 2, 0, 'file name', false, 'VALID_C_TRANSACTION', false);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (3, 3, 3, 0, 'file name', false, 'VALID_C_TRANSACTION', false);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (4, 4, 4, 0, 'file name', false, 'VALID_C_TRANSACTION', false);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (5, 5, 5, 0, 'file name', false, 'VALID_C_TRANSACTION', false);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (6, 6, 6, 1, 'file name', false, 'VALID_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (7, 7, 7, 1, 'file name', false, 'VALID_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (8, 8, 8, 1, 'file name', false, 'VALID_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (9, 9, 9, 1, 'file name', false, 'VALID_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (10, 10, 10, 1, 'file name', false, 'VALID_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (11, 11, 11, 2, 'file name', false, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (12, 12, 12, 2, 'file name', false, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (13, 13, 13, 2, 'file name', false, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (14, 14, 14, 2, 'file name', false, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (15, 15, 15, 2, 'file name', false, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (16, 16, 16, 2, 'file name', true, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (17, 17, 17, 2, 'file name', true, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (18, 18, 18, 2, 'file name', true, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (19, 19, 19, 2, 'file name', true, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (20, 20, 20, 2, 'file name', true, 'BAD_C_TRANSACTION', true);

INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (101, 801, 801, 0, 'file name', false, 'VALID_C_TRANSACTION', false);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (102, 802, 802, 0, 'file name', false, 'VALID_C_TRANSACTION', false);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (103, 803, 803, 0, 'file name', false, 'VALID_C_TRANSACTION', false);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (104, 804, 804, 0, 'file name', false, 'VALID_C_TRANSACTION', false);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (105, 805, 805, 0, 'file name', false, 'VALID_C_TRANSACTION', false);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (106, 806, 806, 1, 'file name', false, 'VALID_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (107, 807, 807, 1, 'file name', false, 'VALID_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (108, 808, 808, 1, 'file name', false, 'VALID_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (109, 809, 809, 1, 'file name', false, 'VALID_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (110, 810, 810, 1, 'file name', false, 'VALID_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (111, 811, 811, 2, 'file name', false, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (112, 812, 812, 2, 'file name', false, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (113, 813, 813, 2, 'file name', false, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (114, 814, 814, 2, 'file name', false, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (115, 815, 815, 2, 'file name', false, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (116, 816, 816, 2, 'file name', true, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (117, 817, 817, 2, 'file name', true, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (118, 818, 818, 2, 'file name', true, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (119, 819, 819, 2, 'file name', true, 'BAD_C_TRANSACTION', true);
INSERT INTO opm.mainframe_import (id, audit_batch_log_id, pay_transaction_key, payment_type, file_name, error_flag, record_string, ach_flag) VALUES (120, 820, 820, 2, 'file name', true, 'BAD_C_TRANSACTION', true);


INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (301, false, 801, '01/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (302, false, 802, '01/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (303, false, 803, '01/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (304, false, 804, '01/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (305, false, 805, '01/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (306, false, 806, '02/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (307, false, 807, '02/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (308, false, 808, '02/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (309, false, 809, '02/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (310, false, 810, '02/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (311, false, 811, '03/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (312, false, 812, '03/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (313, false, 813, '03/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (314, false, 814, '03/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (315, false, 815, '03/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (316, false, 816, '04/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (317, false, 817, '04/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (318, false, 818, '04/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (319, false, 819, '04/01/2014');
INSERT INTO opm.audit_batch_log_id (id, deleted, audit_batch_log_id, batch_date) VALUES (320, false, 820, '04/01/2014');

INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8001, false, 801, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 1, '01/01/2014', 1, '123', true, '1', false, true, 1, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8002, false, 802, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 2, '01/01/2014', 1, '123', true, '2', false, true, 2, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8003, false, 803, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 3, '01/01/2014', 1, '123', true, '3', false, true, 10, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8004, false, 804, '123', '123', '123', '123', '01/01/2014', '100.000', '01/01/2014', 4, '01/01/2014', 1, '123', true, '4', true, true, 11, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8005, false, 805, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 5, '01/01/2014', 1, '123', true, '5', true, true, 14, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8006, false, 806, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 1, '01/01/2014', 1, '123', true, '6', false, true, 1, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8007, false, 807, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 2, '01/01/2014', 1, '123', true, '7', false, true, 2, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8008, false, 808, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 3, '01/01/2014', 1, '123', true, '8', false, true, 10, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8009, false, 809, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 4, '01/01/2014', 1, '123', true, '9', true, true, 11, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8010, false, 810, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 5, '01/01/2014', 1, '123', true, '10', true, true, 14, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8011, false, 811, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 1, '01/01/2014', 1, '123', true, '11', false, true, 1, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8012, false, 812, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 2, '01/01/2014', 1, '123', true, '12', false, true, 2, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8013, false, 813, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 3, '01/01/2014', 1, '123', true, '13', false, true, 10, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8014, false, 814, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 4, '01/01/2014', 1, '123', true, '14', true, true, 11, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8015, false, 815, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 5, '01/01/2014', 1, '123', true, '15', true, true, 14, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8016, false, 816, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 6, '01/01/2014', 1, '123', true, '16', false, true, 22, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8017, false, 817, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 7, '01/01/2014', 1, '123', true, '17', false, true, 21, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8018, false, 818, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 8, '01/01/2014', 1, '123', true, '18', false, true, 25, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8019, false, 819, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 9, '01/01/2014', 1, '123', true, '19', true, true, 61, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_transaction_key, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (8020, false, 820, '123', '123', '123', '123', '01/01/2014', '100.00', '01/01/2014', 10, '01/01/2014', 1, '123', true, '20', true, true, 51, true, true);



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
		false, false, false, 30, 1);
		
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
		false, false, false, 100, 1);
		
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
		
UPDATE opm.invoice_data SET account_note_type = 'ACH_OVER_PAYMENT';

ALTER SEQUENCE opm.audit_record_id_seq RESTART WITH 209;
ALTER SEQUENCE opm.account_id_seq RESTART WITH 800003;
ALTER SEQUENCE opm.account_status_id_seq RESTART WITH 9;
ALTER SEQUENCE opm.refund_transaction_id_seq RESTART WITH 11;
ALTER SEQUENCE opm.payment_transaction_id_seq RESTART WITH 8021;
ALTER SEQUENCE opm.billing_summary_id_seq RESTART WITH 8002;
ALTER SEQUENCE opm.all_details_id_seq RESTART WITH 2;
ALTER SEQUENCE opm.audit_batch_log_id_id_seq RESTART WITH 321;
ALTER SEQUENCE opm.reference_id_seq RESTART WITH 6;
ALTER SEQUENCE opm.deduction_rate_id_seq RESTART WITH 10009;
ALTER SEQUENCE opm.billing_id_seq RESTART WITH 310;
ALTER SEQUENCE opm.invoice_data_id_seq RESTART WITH 56;
ALTER SEQUENCE opm.mainframe_import_id_seq RESTART WITH 9002;
ALTER SEQUENCE opm.audit_parameter_record_id_seq RESTART WITH 30007;
ALTER SEQUENCE opm.calculation_result_item_id_seq RESTART WITH 17;
ALTER SEQUENCE opm.letter_id_seq RESTART WITH 6;
ALTER SEQUENCE opm.payment_id_seq RESTART WITH 8006;



