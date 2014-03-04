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

INSERT INTO opm.payment_status (id, deleted, name) VALUES (1, false, 'Payment Status 1');
INSERT INTO opm.payment_status (id, deleted, name) VALUES (2, false, 'Payment Status 2');
INSERT INTO opm.payment_status (id, deleted, name) VALUES (3, false, 'Payment Status 3');

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
