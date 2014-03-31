CREATE SCHEMA opm;


-- -----------------------------------------------------
-- Table payment_appliance
-- -----------------------------------------------------
CREATE TABLE opm.payment_appliance  (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) );


-- -----------------------------------------------------
-- Table form_type
-- -----------------------------------------------------
CREATE TABLE opm.form_type (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table suffix
-- -----------------------------------------------------
CREATE TABLE opm.suffix (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table period_type
-- -----------------------------------------------------
CREATE TABLE opm.period_type (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  description VARCHAR(128) NULL,
  display_order INTEGER NULL,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table state
-- -----------------------------------------------------
CREATE TABLE opm.state (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  abbreviation VARCHAR(2) NULL,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table country
-- -----------------------------------------------------
CREATE TABLE opm.country (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table account_status
-- -----------------------------------------------------
CREATE TABLE opm.account_status (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  display_order INTEGER NULL,
  status_category VARCHAR(128),
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table retirement_type
-- -----------------------------------------------------
CREATE TABLE opm.retirement_type (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  description VARCHAR(128) NULL,
  display_order INTEGER NULL,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table service_type
-- -----------------------------------------------------
CREATE TABLE opm.service_type (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  display_order INTEGER NULL,
  fers_deposit_allowed_after_88 INTEGER NULL,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table role
-- -----------------------------------------------------
CREATE TABLE opm.role (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  description VARCHAR(128) NULL,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table appointment_type
-- -----------------------------------------------------
CREATE TABLE opm.appointment_type (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  description VARCHAR(128) NULL,
  display_order INTEGER NULL,
  type_category VARCHAR(128) NULL,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table pay_type
-- -----------------------------------------------------
CREATE TABLE opm.pay_type (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  description VARCHAR(128) NULL,
  display_order INTEGER NULL,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table payment_reversal_reason
-- -----------------------------------------------------
CREATE TABLE opm.payment_reversal_reason (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table application_designation
-- -----------------------------------------------------
CREATE TABLE opm.application_designation (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table payment_status
-- -----------------------------------------------------
CREATE TABLE opm.payment_status (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table claim_officer
-- -----------------------------------------------------
CREATE TABLE opm.claim_officer (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table user_status
-- -----------------------------------------------------
CREATE TABLE opm.user_status (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table transfer_type
-- -----------------------------------------------------
CREATE TABLE opm.transfer_type (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table calculation_status
-- -----------------------------------------------------
CREATE TABLE opm.calculation_status (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table agency_code
-- -----------------------------------------------------
CREATE TABLE opm.agency_code (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NULL ,
  display_order INTEGER NULL,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table payment_applied_order
-- -----------------------------------------------------
CREATE TABLE opm.payment_applied_order (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NULL ,
  display_order INTEGER NULL,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table pay_code
-- -----------------------------------------------------
CREATE TABLE opm.pay_code (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NULL ,
  description VARCHAR(128),
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table role
-- -----------------------------------------------------
CREATE TABLE opm.change_trans_field_number_code (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NULL ,
  description VARCHAR(128),
  PRIMARY KEY (id) );
  
-- -----------------------------------------------------
-- Table app_user
-- -----------------------------------------------------
CREATE TABLE opm.app_user (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  username VARCHAR(120) NOT NULL ,
  default_tab VARCHAR(32) NULL ,
  network_id VARCHAR(128) NOT NULL ,
  role_id BIGINT NOT NULL,
  first_name VARCHAR(25) NOT NULL ,
  last_name VARCHAR(50) NOT NULL ,
  email VARCHAR(128) NOT NULL ,
  telephone VARCHAR(20) NULL ,
  user_status_id BIGINT NOT NULL,
  supervisor_id BIGINT,
  default_tab_account_id BIGINT NULL,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_app_user_user
    FOREIGN KEY (supervisor_id )
    REFERENCES opm.app_user (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_app_user_role
    FOREIGN KEY (role_id )
    REFERENCES opm.role (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_app_user_user_status
    FOREIGN KEY (user_status_id )
    REFERENCES opm.user_status (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table notification
-- -----------------------------------------------------
CREATE TABLE opm.notification (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  date TIMESTAMP NOT NULL ,
  details TEXT NOT NULL ,
  sender VARCHAR(128) NULL ,
  read BOOLEAN NOT NULL,
  recipient VARCHAR(128) NULL ,
  recipient_role_id BIGINT NULL ,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_notification_role
    FOREIGN KEY (recipient_role_id )
    REFERENCES opm.role (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table info
-- -----------------------------------------------------
CREATE TABLE opm.info (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  date TIMESTAMP NOT NULL ,
  details TEXT NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table error
-- -----------------------------------------------------
CREATE TABLE opm.error (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  date TIMESTAMP NOT NULL ,
  details TEXT NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table address
-- -----------------------------------------------------
CREATE TABLE opm.address (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  street1 VARCHAR(128) NOT NULL ,
  street2 VARCHAR(128) NULL ,
  street3 VARCHAR(128) NULL ,
  street4 VARCHAR(128) NULL ,
  street5 VARCHAR(128) NULL ,
  city VARCHAR(128) NOT NULL ,
  state_id BIGINT NULL,
  zip_code VARCHAR(128) NULL ,
  country_id BIGINT NULL,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_address_state
    FOREIGN KEY (state_id )
    REFERENCES opm.state (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_address_country
    FOREIGN KEY (country_id )
    REFERENCES opm.country (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table account_holder
-- -----------------------------------------------------
CREATE TABLE opm.account_holder (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  last_name VARCHAR(128) NOT NULL ,
  first_name VARCHAR(128) NOT NULL ,
  middle_initial VARCHAR(128) NULL ,
  suffix_id BIGINT NULL,
  birth_date TIMESTAMP NOT NULL ,
  ssn VARCHAR(128) UNIQUE NOT NULL ,
  telephone VARCHAR(128) NULL ,
  email VARCHAR(128) NULL ,
  title VARCHAR(128) NULL ,
  department_code VARCHAR(128) NULL ,
  geo_code VARCHAR(128) NULL ,
  city_of_employment VARCHAR(128) NULL ,
  state_of_employment_id BIGINT NULL,
  address_id BIGINT NOT NULL,
  holder_position VARCHAR(128),
  agency_code VARCHAR(128),
  PRIMARY KEY (id) ,
  CONSTRAINT fk_account_holder_suffix
    FOREIGN KEY (suffix_id )
    REFERENCES opm.suffix (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_holder_state
    FOREIGN KEY (state_of_employment_id )
    REFERENCES opm.state (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_holder_address
    FOREIGN KEY (address_id )
    REFERENCES opm.address (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
    
-- -----------------------------------------------------
-- Table billing_summary
-- -----------------------------------------------------
CREATE TABLE opm.billing_summary (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  computed_date TIMESTAMP NOT NULL ,
  last_deposit_date TIMESTAMP NULL ,
  first_billing_date TIMESTAMP NOT NULL ,
  last_interest_calculation TIMESTAMP NOT NULL ,
  transaction_type VARCHAR(128) NOT NULL ,
  last_transaction_date TIMESTAMP NOT NULL ,
  stop_ach_payments BOOLEAN NULL ,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table account_confirmation_validation
-- -----------------------------------------------------
CREATE TABLE opm.account_confirmation_validation (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  account_id BIGINT NULL,
  data_check_status VARCHAR(128) NOT NULL,
  data_check_status_validator VARCHAR(128) NOT NULL ,
  data_check_status_reason VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table account
-- -----------------------------------------------------
CREATE TABLE opm.account (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NOT NULL ,
  plan_type VARCHAR(128) NULL ,
  form_type_id BIGINT NULL,
  account_holder_id BIGINT NOT NULL,
  account_status_id BIGINT NOT NULL,
  grace BOOLEAN NOT NULL ,
  frozen BOOLEAN NOT NULL ,
  claim_officer VARCHAR(128) NULL ,
  claim_officer_assignment_date TIMESTAMP NULL ,
  returned_from_record_date TIMESTAMP NULL ,
  claimant_birthdate TIMESTAMP NULL ,
  balance DECIMAL(10,2) NULL ,
  billing_summary_id BIGINT NULL,
  account_confirmation_validation_id BIGINT NULL,
  total_deposit DECIMAL(10,2) NULL,
  total_redeposit DECIMAL(10,2) NULL,
  total_var_redeposit DECIMAL(10,2) NULL,
  total_non_deposit DECIMAL(10,2) NULL,
  total_fers_w DECIMAL(10,2) NULL,
  acc_int_dep DECIMAL(10,2) NULL,
  acc_int_rdep DECIMAL(10,2) NULL,
  acc_int_non_dep DECIMAL(10,2) NULL,
  acc_int_var_rdep DECIMAL(10,2) NULL,
  acc_int_fers DECIMAL(10,2) NULL,
  tot_pay_d DECIMAL(10,2) NULL,
  tot_pay_r DECIMAL(10,2) NULL,
  tot_pay_n DECIMAL(10,2) NULL,
  tot_pay_vr DECIMAL(10,2) NULL,
  tot_pay_fers DECIMAL(10,2) NULL,
  computation_date TIMESTAMP NULL,
  var_int_computation_date TIMESTAMP NULL,  
  last_action VARCHAR(128) NULL,
  last_action_time TIMESTAMP NULL,
  last_pay TIMESTAMP NULL,
  pay_code_id BIGINT NULL,
  time_period VARCHAR(128) NULL,
  additional_service VARCHAR(1024) NULL,
  no_interest BOOLEAN NULL,
  code_20_date TIMESTAMP NULL,
  flag_preredeposit BOOLEAN NULL,
  flag_postredeposit BOOLEAN NULL,
  prior_claim_number VARCHAR(128) NULL,
  payment_order VARCHAR(128) NULL,
  new_claim_number VARCHAR(128) NULL,
  stop_ach_payment BOOLEAN NULL,
  dbts_account BOOLEAN NULL,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_account_form_type
    FOREIGN KEY (form_type_id )
    REFERENCES opm.form_type (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_account_holder
    FOREIGN KEY (account_holder_id )
    REFERENCES opm.account_holder (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_account_status
    FOREIGN KEY (account_status_id )
    REFERENCES opm.account_status (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_billing_summary
    FOREIGN KEY (billing_summary_id )
    REFERENCES opm.billing_summary (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION ,
  CONSTRAINT fk_account_account_confirmation_validation
    FOREIGN KEY (account_confirmation_validation_id )
    REFERENCES opm.account_confirmation_validation (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_pay_code
    FOREIGN KEY (pay_code_id)
    REFERENCES opm.pay_code(id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table account_note
-- -----------------------------------------------------
CREATE TABLE opm.account_note (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  date TIMESTAMP NOT NULL ,
  writer VARCHAR(120) NOT NULL ,
  text TEXT NOT NULL ,
  account_id BIGINT NULL,
  priority INTEGER NULL,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_account_note_account
    FOREIGN KEY (account_id )
    REFERENCES opm.account (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table billing
-- -----------------------------------------------------
CREATE TABLE opm.billing (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  initial_billing DECIMAL(10,2) NOT NULL ,
  additional_interest DECIMAL(10,2) NOT NULL ,
  total_payments DECIMAL(10,2) NOT NULL ,
  balance DECIMAL(10,2) NOT NULL ,
  payment_order INT  NOT NULL ,
  billing_summary_id BIGINT NULL,
  frozen BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_billing_billing_summary
    FOREIGN KEY (billing_summary_id )
    REFERENCES opm.billing_summary (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table summary_data
-- -----------------------------------------------------
CREATE TABLE opm.summary_data (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  total_payments_required DECIMAL(10,2) NOT NULL ,
  total_initial_interest DECIMAL(10,2) NOT NULL ,
  total_payments_applied DECIMAL(10,2) NOT NULL ,
  total_balance DECIMAL(10,2) NOT NULL ,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table calculation_result
-- -----------------------------------------------------
CREATE TABLE opm.calculation_result (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  calculation_status_id BIGINT NULL,
  official BOOLEAN NULL ,
  apply_to_real_payment BOOLEAN NULL ,
  summary_data_id BIGINT NULL,
  payment_order INTEGER NULL,
  interest_accrual_date TIMESTAMP NULL,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_calculation_result_calculation_status
    FOREIGN KEY (calculation_status_id )
    REFERENCES opm.calculation_status (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION ,
  CONSTRAINT fk_calculation_result_summary_data
    FOREIGN KEY (summary_data_id )
    REFERENCES opm.summary_data (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table calculation_version
-- -----------------------------------------------------
CREATE TABLE opm.calculation_version (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NULL ,
  calculation_date TIMESTAMP NULL ,
  calculation_result_id BIGINT NULL,
  account_id BIGINT NOT NULL,
  version INTEGER NULL,
  line_number INTEGER NULL,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_calculation_version_calculation_result
    FOREIGN KEY (calculation_result_id )
    REFERENCES opm.calculation_result (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_calculation_version_account
    FOREIGN KEY (account_id )
    REFERENCES opm.account (id )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION );

-- -----------------------------------------------------
-- Table calculation
-- -----------------------------------------------------
CREATE TABLE opm.calculation (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  begin_date TIMESTAMP NOT NULL ,
  end_date TIMESTAMP NOT NULL ,
  retirement_type_id BIGINT NULL,
  period_type_id BIGINT NOT NULL,
  appointment_type_id BIGINT NULL,
  service_type_id BIGINT NULL,
  amount DECIMAL(10,2) NOT NULL ,
  pay_type_id BIGINT NULL,
  agency_code_id BIGINT NULL,
  hours_in_year INTEGER NULL,
  annualized_amount DECIMAL(10,2) NULL,
  date_entered TIMESTAMP NULL,
  entered_by BIGINT NULL,
  calculation_version_id BIGINT NULL,
  interest_rate DECIMAL(10,2) NULL,
  conner_case BOOLEAN NULL,
  interest_accrual_date TIMESTAMP NULL,
  frozen BOOLEAN NULL DEFAULT FALSE,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_calculation_retirement_type
    FOREIGN KEY (retirement_type_id )
    REFERENCES opm.retirement_type (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_calculation_period_type
    FOREIGN KEY (period_type_id )
    REFERENCES opm.period_type (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_calculation_appointment_type
    FOREIGN KEY (appointment_type_id )
    REFERENCES opm.appointment_type (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_calculation_service_type
    FOREIGN KEY (service_type_id )
    REFERENCES opm.service_type (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_calculation_pay_type
    FOREIGN KEY (pay_type_id )
    REFERENCES opm.pay_type (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_calculation_agency_code
    FOREIGN KEY (agency_code_id )
    REFERENCES opm.agency_code (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_calculation_calculation_version
    FOREIGN KEY (calculation_version_id )
    REFERENCES opm.calculation_version (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table calculation_result_item
-- -----------------------------------------------------
CREATE TABLE opm.calculation_result_item (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  start_date TIMESTAMP NOT NULL ,
  end_date TIMESTAMP NOT NULL ,
  mid_date TIMESTAMP NULL ,
  effective_date TIMESTAMP NULL ,
  period_type_id BIGINT NOT NULL,
  deduction_amount DECIMAL(10,2) NOT NULL ,
  total_interest DECIMAL(10,2) NOT NULL ,
  payment_applied DECIMAL(10,2) NOT NULL ,
  balance DECIMAL(10,2) NOT NULL ,
  calculation_result_id BIGINT NULL,
  service_category VARCHAR(128) NULL,
  retirement_type_id BIGINT NULL,
  version INT NULL,
  line INT NULL,
  status VARCHAR(120) NULL,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_calculation_result_item_period_type
    FOREIGN KEY (period_type_id )
    REFERENCES opm.period_type (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_calculation_result_item_calculation_result
    FOREIGN KEY (calculation_result_id )
    REFERENCES opm.calculation_result (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_calculation_result_item_retirement_type
    FOREIGN KEY (retirement_type_id )
    REFERENCES opm.retirement_type (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table redeposit
-- -----------------------------------------------------
CREATE TABLE opm.redeposit (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  label VARCHAR(128) NOT NULL,
  deposit DECIMAL(10,2) NOT NULL ,
  interest DECIMAL(10,2) NOT NULL ,
  total DECIMAL(10,2) NOT NULL ,
  calculation_result_id BIGINT NULL,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_redeposit_calculation_result
    FOREIGN KEY (calculation_result_id )
    REFERENCES opm.calculation_result (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table dedeposit
-- -----------------------------------------------------
CREATE TABLE opm.dedeposit (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  label VARCHAR(128) NOT NULL,
  deposit DECIMAL(10,2) NOT NULL ,
  interest DECIMAL(10,2) NOT NULL ,
  total DECIMAL(10,2) NOT NULL ,
  calculation_result_id BIGINT NULL,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_redeposit_calculation_result
    FOREIGN KEY (calculation_result_id )
    REFERENCES opm.calculation_result (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table audit_record
-- -----------------------------------------------------
CREATE TABLE opm.audit_record (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  username VARCHAR(120) NOT NULL ,
  ip_address VARCHAR(120) NOT NULL ,
  description VARCHAR(120) NULL,
  action VARCHAR(120) NOT NULL ,
  date TIMESTAMP NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table audit_parameter_record
-- -----------------------------------------------------
CREATE TABLE opm.audit_parameter_record (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  item_id BIGINT NOT NULL ,
  item_type VARCHAR(128) NOT NULL ,
  property_name VARCHAR(128) NOT NULL ,
  previous_value VARCHAR(20000) NULL ,
  new_value VARCHAR(20000) NULL ,
  audit_record_id BIGINT NOT NULL,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_audit_parameter_record_audit_record
    FOREIGN KEY (audit_record_id )
    REFERENCES opm.audit_record (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table refund_transaction
-- -----------------------------------------------------
CREATE TABLE opm.refund_transaction (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  transaction_key VARCHAR(128) NULL ,
  amount DECIMAL(10,2) NOT NULL ,
  claim_number VARCHAR(128) NOT NULL ,
  refund_date TIMESTAMP NOT NULL ,
  refund_username VARCHAR(128) NULL ,
  transfer_type_id BIGINT NULL,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_refund_transaction_transfer_type
    FOREIGN KEY (transfer_type_id )
    REFERENCES opm.transfer_type (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table date_calculation_data
-- -----------------------------------------------------
CREATE TABLE opm.date_calculation_data (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  calculation_type VARCHAR(128) NOT NULL ,
  day_offset INT NOT NULL ,
  month_offset INT NOT NULL ,
  value INT NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table notification_READBY
-- -----------------------------------------------------
CREATE TABLE opm.notification_READBY (
  id BIGSERIAL NOT NULL,
  notification_id BIGINT NOT NULL,
  value VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_notification_read_by_notification
    FOREIGN KEY (notification_id )
    REFERENCES opm.notification (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table help_item
-- -----------------------------------------------------
CREATE TABLE opm.help_item (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  title VARCHAR(120) NOT NULL ,
  summary TEXT NOT NULL ,
  content TEXT NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table related_help_items
-- -----------------------------------------------------
CREATE TABLE opm.related_help_items (
  id BIGSERIAL NOT NULL,
  help_item_id1 BIGINT NOT NULL ,
  help_item_id2 BIGINT NOT NULL ,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_related_help_items_help_item1
    FOREIGN KEY (help_item_id1 )
    REFERENCES opm.help_item (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_related_help_items_help_item2
    FOREIGN KEY (help_item_id2 )
    REFERENCES opm.help_item (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table printout
-- -----------------------------------------------------
CREATE TABLE opm.printout (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  print_date TIMESTAMP NOT NULL ,
  content BYTEA NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table pay_trans_status_code
-- -----------------------------------------------------
CREATE TABLE opm.pay_trans_status_code (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  description VARCHAR(128) NULL ,
  category VARCHAR(128) NULL ,
  display_order INTEGER NULL ,
  next_state_link BIGINT NULL ,
  batch_processing_order INTEGER NULL ,
  final_state BOOLEAN NULL ,
  needs_approval BOOLEAN NULL ,
  show_on_suspense BOOLEAN NULL ,
  include_in_balance BOOLEAN NULL ,
  nightly_batch BOOLEAN NULL ,
  deletable BOOLEAN NULL ,
  reversable BOOLEAN NULL ,
  manual_entered BOOLEAN NULL ,
  suspense_action BOOLEAN NULL ,
  can_hit_gl BOOLEAN NULL ,
  reversing_type BOOLEAN NULL ,
  balanced_scorecard BOOLEAN NULL ,
  send_to_dbts BOOLEAN NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table payments_applied_order_code
-- -----------------------------------------------------
CREATE TABLE opm.payments_applied_order_code (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  payment_account VARCHAR(128) NULL ,
  display_order INTEGER NULL,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table payment
-- -----------------------------------------------------
CREATE TABLE opm.payment (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  batch_number VARCHAR(128) NOT NULL ,
  block_number VARCHAR(128) NOT NULL ,
  sequence_number VARCHAR(128) NOT NULL ,
  claim_number VARCHAR(128) NULL ,
  payment_status_id BIGINT NULL,
  account_holder_birthdate TIMESTAMP NULL ,
  deposit_date TIMESTAMP NULL ,
  amount DECIMAL(10,2) NOT NULL ,
  ssn VARCHAR(128) NULL ,
  claimant VARCHAR(128) NULL ,
  claimant_birthday TIMESTAMP NULL ,
  import_id VARCHAR(128) NULL ,
  sequence INT NULL ,
  transaction_date TIMESTAMP NULL ,
  status_date TIMESTAMP NULL ,
  apply_designation_id BIGINT NULL,
  apply_to_gl BOOLEAN NOT NULL ,
  note VARCHAR(2048) NULL ,
  transaction_key VARCHAR(128) NULL ,
  ach BOOLEAN NULL ,
  account_balance DECIMAL(10,2) NULL ,
  account_status_id BIGINT NULL,
  master_claim_number VARCHAR(128) NULL ,
  master_claimant_birthday TIMESTAMP NULL ,
  master_account_status_id BIGINT NULL ,
  master_account_balance DECIMAL(10,2) NULL ,
  master_account_id BIGINT NULL,
  pre_deposit_amount DECIMAL(10,2) NULL ,
  pre_redeposit_amount DECIMAL(10,2) NULL ,
  post_deposit_amount DECIMAL(10,2) NULL ,
  post_redeposit_amount DECIMAL(10,2) NULL ,
  approval_user VARCHAR(128) NULL ,
  approval_status VARCHAR(128) NULL,
  approval_reason VARCHAR(255) NULL,
  payment_type VARCHAR(128) NULL ,
  account_id BIGINT NULL,
  gov_refund BOOLEAN NULL,
  disapprove BOOLEAN NULL,
  history_payment BOOLEAN NULL,
  resolve_suspense BOOLEAN NULL,
  user_inserted BOOLEAN NULL,
  post_flag BOOLEAN NULL,
  order_code_id BIGINT NULL,
  pay_trans_status_code_id BIGINT NULL,
  payment_appliance_id BIGINT,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_payment_payment_appliance
    FOREIGN KEY (payment_appliance_id )
    REFERENCES opm.payment_appliance (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    
  CONSTRAINT fk_payment_payment_status
    FOREIGN KEY (payment_status_id )
    REFERENCES opm.payment_status (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_payment_apply_designation
    FOREIGN KEY (apply_designation_id )
    REFERENCES opm.application_designation (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_payment_account_status1
    FOREIGN KEY (account_status_id )
    REFERENCES opm.account_status (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_payment_account_status2
    FOREIGN KEY (master_account_status_id )
    REFERENCES opm.account_status (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_payment_account
    FOREIGN KEY (account_id )
    REFERENCES opm.account (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_payment_order_code
    FOREIGN KEY (order_code_id)
    REFERENCES opm.payments_applied_order_code (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_payment_status_code
    FOREIGN KEY (pay_trans_status_code_id )
    REFERENCES opm.pay_trans_status_code (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table payment_reverse
-- -----------------------------------------------------
CREATE TABLE opm.payment_reverse (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  payment_id BIGINT NOT NULL,
  payment_reversal_reason_id BIGINT NOT NULL,
  apply_to_gl BOOLEAN NOT NULL ,
  reverser VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_payment_reverse_payment
    FOREIGN KEY (payment_id )
    REFERENCES opm.payment (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_payment_reverse_payment_reversal_reason
    FOREIGN KEY (payment_reversal_reason_id )
    REFERENCES opm.payment_reversal_reason (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table account_confirmation_validation_entry
-- -----------------------------------------------------
CREATE TABLE opm.account_confirmation_validation_entry (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  field_name VARCHAR(128) NOT NULL ,
  valid BOOLEAN NOT NULL ,
  account_confirmation_validation_id BIGINT NULL,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_account_confirmation_validation_entry_account_confirmation
    FOREIGN KEY (account_confirmation_validation_id )
    REFERENCES opm.account_confirmation_validation (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table service_credit_preference
-- -----------------------------------------------------
CREATE TABLE opm.service_credit_preference (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  use_agents BOOLEAN NOT NULL ,
  use_status_bar BOOLEAN NOT NULL ,
  use_message_box BOOLEAN NOT NULL ,
  other VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table user_permission
-- -----------------------------------------------------
CREATE TABLE opm.user_permission (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  username VARCHAR(120) NULL ,
  action VARCHAR(120) NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table role_permission
-- -----------------------------------------------------
CREATE TABLE opm.role_permission (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  rolename VARCHAR(128) NULL ,
  action VARCHAR(128) NULL ,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table payment_refund_link
-- -----------------------------------------------------
CREATE TABLE opm.payment_refund_link (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  payment_needing_refund BIGINT NULL,
  refund_for_payment BIGINT NULL,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table user_account_assignment
-- -----------------------------------------------------
CREATE TABLE opm.user_account_assignment (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  user_id BIGINT NULL,
  account_id BIGINT NULL ,
  assignment_date TIMESTAMP NULL ,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_user_account_assignment_user
    FOREIGN KEY (user_id )
    REFERENCES opm.app_user (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_user_account_assignment_account
    FOREIGN KEY (account_id )
    REFERENCES opm.account (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table a01_print_suppression_case
-- -----------------------------------------------------
CREATE TABLE opm.a01_print_suppression_case (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL ,
  reason_for_print_suppression INTEGER NULL,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table invoice
-- -----------------------------------------------------
CREATE TABLE opm.invoice (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  pay_transaction_key BIGINT NULL,
  deposit DECIMAL(10,2) NULL,
  redeposit DECIMAL(10,2) NULL,
  tot_var_redeposit DECIMAL(10,2) NULL,
  non_ded DECIMAL(10,2) NULL,
  fers_w DECIMAL(10,2) NULL,
  acc_int_dep DECIMAL(10,2) NULL,
  acc_int_rdep DECIMAL(10,2) NULL,
  acc_int_non_dep DECIMAL(10,2) NULL,
  acc_int_var_rdep DECIMAL(10,2) NULL,
  acc_int_fers DECIMAL(10,2) NULL,
  tot_pay_d DECIMAL(10,2) NULL,
  tot_pay_r DECIMAL(10,2) NULL,
  tot_pay_n DECIMAL(10,2) NULL,
  tot_pay_vr DECIMAL(10,2) NULL,
  tot_pay_fers DECIMAL(10,2) NULL,
  last_pay TIMESTAMP NULL,
  calc_date TIMESTAMP NULL,
  last_invoice_id BIGINT NULL,
  PRIMARY KEY (id) );
  
-- -----------------------------------------------------
-- Table adjustment_transaction
-- -----------------------------------------------------
CREATE TABLE opm.adjustment_transaction (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  pay_transaction_key BIGINT NULL,
  claim_number VARCHAR(128) NULL,
  acc_int_dep DECIMAL(10,2) NULL,
  acc_int_rdep DECIMAL(10,2) NULL,
  acc_int_non_dep DECIMAL(10,2) NULL,
  acc_int_var_rdep DECIMAL(10,2) NULL,
  acc_int_fers DECIMAL(10,2) NULL,
  modification_date TIMESTAMP NULL,
  approved_date TIMESTAMP NULL,
  processed_date TIMESTAMP NULL,
  technician_user_key BIGINT NULL,
  manager_user_key BIGINT NULL,
  approved BOOLEAN NULL,
  disapproved BOOLEAN NULL,  
  modified BOOLEAN NULL,
  note VARCHAR(1024) NULL,
  PRIMARY KEY (id) );
  
-- -----------------------------------------------------
-- Table payment_interest_detail
-- -----------------------------------------------------
CREATE TABLE opm.payment_interest_detail (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  pay_transaction_key BIGINT NULL,
  account_type BIGINT NULL,
  num_whole_years INTEGER NULL,
  calculated_interest DECIMAL(10,2) NULL,
  last_pay_to_eoy_factor DECIMAL(10,2) NULL,
  partial_to_this_factor DECIMAL(10,2) NULL,
  this_interest_rate DECIMAL(10,2) NULL,
  last_payment_date TIMESTAMP NULL,
  transaction_date TIMESTAMP NULL,
  computed_date TIMESTAMP NULL,
  post BOOLEAN NULL,
  gui BOOLEAN NULL,
  last_payment_was_this_year BOOLEAN NULL,
  PRIMARY KEY (id));
    
-- -----------------------------------------------------
-- Table interest_rate
-- -----------------------------------------------------
CREATE TABLE opm.interest_rate (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  interest_year INTEGER NULL,
  interest_rate DECIMAL(10,6) NULL,
  PRIMARY KEY (id) );

-- -----------------------------------------------------
-- Table payment_move_transaction
-- -----------------------------------------------------
CREATE TABLE opm.payment_move_transaction (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  pay_transaction_key BIGINT NULL,
  claim_number VARCHAR(128) NULL,
  tot_pay_d DECIMAL(10,2) NULL,
  tot_pay_r DECIMAL(10,2) NULL,
  tot_pay_n DECIMAL(10,2) NULL,
  tot_pay_vr DECIMAL(10,2) NULL,
  tot_pay_fers DECIMAL(10,2) NULL,
  modification_date TIMESTAMP NULL,
  approved_date TIMESTAMP NULL,
  processed_date TIMESTAMP NULL,
  technician_user_key BIGINT NULL,
  manager_user_key BIGINT NULL,
  approved BOOLEAN NULL,
  disapproved BOOLEAN NULL,  
  modified BOOLEAN NULL,
  note VARCHAR(1024) NULL,
  PRIMARY KEY (id) );
  
-- -----------------------------------------------------
-- Table payment_transaction_note
-- -----------------------------------------------------
CREATE TABLE opm.payment_transaction_note (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  pay_transaction_key BIGINT NULL,
  note VARCHAR(1024) NULL,
  PRIMARY KEY (id));
  
-- -----------------------------------------------------
-- Table claim_without_service
-- -----------------------------------------------------
CREATE TABLE opm.claim_without_service (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL,
  date_of_birth TIMESTAMP NULL,
  PRIMARY KEY (id));
  
-- -----------------------------------------------------
-- Table gl_code
-- -----------------------------------------------------
CREATE TABLE opm.gl_code (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NULL,
  code VARCHAR(128) NULL,
  payment_type VARCHAR(128) NULL,
  retirement_type_id BIGINT NULL,
  post_office BOOLEAN NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_gl_code_retirement_type
    FOREIGN KEY (retirement_type_id)
    REFERENCES opm.retirement_type (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table interest_grace_period
-- -----------------------------------------------------
CREATE TABLE opm.interest_grace_period (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL,
  post_982_redeposit BOOLEAN NULL,
  pre_1082_redeposit BOOLEAN NULL,
  post_982_deposit BOOLEAN NULL,
  pre_1082_deposit BOOLEAN NULL,
  fers_deposit BOOLEAN NULL,
  PRIMARY KEY (id));
    
-- -----------------------------------------------------
-- Table deduction_rate
-- -----------------------------------------------------
CREATE TABLE opm.deduction_rate (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  service_type VARCHAR(128) NULL,
  retirement_type_id BIGINT NULL,
  start_date TIMESTAMP NULL,
  end_date TIMESTAMP NULL,
  days_in_period INTEGER NULL,
  rate DECIMAL(10,6) NULL,
  service_type_description VARCHAR(1024) NULL,
  deduction_conversion_factor DECIMAL(10,6) NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_deduction_rate_retirement_type
    FOREIGN KEY (retirement_type_id )
    REFERENCES opm.retirement_type (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
    
-- -----------------------------------------------------
-- Table holiday
-- -----------------------------------------------------
CREATE TABLE opm.holiday (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  holiday VARCHAR(128) NULL,
  exact_date BOOLEAN NULL,
  week_day INTEGER NULL,
  month_number INTEGER NULL,
  day_of_month INTEGER NULL,
  week_of_month INTEGER NULL,
  holiday_id INTEGER NULL,
  PRIMARY KEY (id));
  
-- -----------------------------------------------------
-- Table scm_first_insert
-- -----------------------------------------------------
CREATE TABLE opm.scm_first_insert (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL,
  last_action TIMESTAMP NULL,
  PRIMARY KEY (id));
  
-- -----------------------------------------------------
-- Table gl_payment_type
-- -----------------------------------------------------
CREATE TABLE opm.gl_payment_type (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  payment_code VARCHAR(128) NULL,
  code_description VARCHAR(1024) NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table interest_suppression
-- -----------------------------------------------------
CREATE TABLE opm.interest_suppression (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL,
  post_982_redeposit BOOLEAN  NULL,
  pre_1082_redeposit BOOLEAN NULL,
  post_982_deposit BOOLEAN NULL,
  pre_1082_deposit BOOLEAN NULL,
  fers_deposit BOOLEAN NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table contact_info
-- -----------------------------------------------------
CREATE TABLE opm.contact_info (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NULL,
  text VARCHAR(1024) NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table time_factor
-- -----------------------------------------------------
CREATE TABLE opm.time_factor (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  num_days INTEGER NULL,
  num_months INTEGER NULL,
  time_factor DECIMAL(10,6) NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table annuitant_list
-- -----------------------------------------------------
CREATE TABLE opm.annuitant_list (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table new_claim_number
-- -----------------------------------------------------
CREATE TABLE opm.new_claim_number (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table audit_batch
-- -----------------------------------------------------
CREATE TABLE opm.audit_batch (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  event_year INTEGER NULL,
  event_month INTEGER NULL,
  event_day INTEGER NULL,
  file_received BOOLEAN NULL,
  daily_action BOOLEAN NULL,
  manual_batch BOOLEAN NULL,
  error_importing BOOLEAN NULL,
  error_processing BOOLEAN NULL,
  latest_batch BOOLEAN NULL,
  amount_imported DECIMAL(10,6) NULL,
  amount_processed DECIMAL(10,6) NULL,
  number_accepted INTEGER NULL,
  number_unresolved INTEGER NULL,
  number_suspended INTEGER NULL,
  number_ach_accepted INTEGER NULL,
  number_ach_unresolved INTEGER NULL,
  number_ach_suspended INTEGER NULL,
  number_change_requests INTEGER NULL,
  payments_processed INTEGER NULL,
  initial_bills_processed INTEGER NULL,
  reversed_processed INTEGER NULL,
  ach_stop_letters INTEGER NULL,
  refund_memos INTEGER NULL,
  error_count_processing INTEGER NULL,
  error_count_importing INTEGER NULL,
  user_key BIGINT NULL,
  batch_time TIMESTAMP NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table batch_daily_payments
-- -----------------------------------------------------
CREATE TABLE opm.batch_daily_payments (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  audit_batch_id BIGINT NULL,
  pay_transaction_key BIGINT NULL,
  number_payment_today INTEGER NULL,
  batch_time TIMESTAMP NULL,
  account_status_id BIGINT NULL,
  pay_trans_status_code BIGINT NULL,
  claim_number VARCHAR(128) NULL ,
  account_balance DECIMAL(10,6) NULL ,
  over_payment_amount DECIMAL(10,6) NULL ,
  ach_payment BOOLEAN NULL,
  ach_stop_letter BOOLEAN NULL,
  print_invoice BOOLEAN NULL,
  refund_required BOOLEAN NULL,
  reversed_payment BOOLEAN NULL,
  update_to_completed BOOLEAN NULL,
  print_initial_bill BOOLEAN NULL,
  latest_batch BOOLEAN NULL,
  error_processing BOOLEAN NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_batch_daily_payments_account_status
    FOREIGN KEY (account_status_id )
    REFERENCES opm.account_status (id )
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table app_user_history
-- -----------------------------------------------------
CREATE TABLE opm.app_user_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  username VARCHAR(120) NOT NULL ,
  default_tab VARCHAR(32) NULL ,
  network_id VARCHAR(128) NOT NULL ,
  role_id BIGINT NOT NULL,
  first_name VARCHAR(25) NOT NULL ,
  last_name VARCHAR(50) NOT NULL ,
  email VARCHAR(128) NOT NULL ,
  telephone VARCHAR(20) NULL ,
  user_status_id BIGINT NOT NULL,
  supervisor_id BIGINT ,
  default_tab_account_id BIGINT NULL,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')));

-- -----------------------------------------------------
-- Table notification_history
-- -----------------------------------------------------
CREATE TABLE opm.notification_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  date TIMESTAMP NOT NULL ,
  details TEXT NOT NULL ,
  sender VARCHAR(128) NULL ,
  read BOOLEAN NOT NULL,
  recipient VARCHAR(128) NULL ,
  recipient_role_id BIGINT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')));

-- -----------------------------------------------------
-- Table info_history
-- -----------------------------------------------------
CREATE TABLE opm.info_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  date TIMESTAMP NOT NULL ,
  details TEXT NOT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table error_history
-- -----------------------------------------------------
CREATE TABLE opm.error_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  date TIMESTAMP NOT NULL ,
  details TEXT NOT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table account_holder_history
-- -----------------------------------------------------
CREATE TABLE opm.account_holder_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  last_name VARCHAR(128) NOT NULL ,
  first_name VARCHAR(128) NOT NULL ,
  middle_initial VARCHAR(128) NULL ,
  suffix_id BIGINT NULL,
  birth_date TIMESTAMP NOT NULL ,
  ssn VARCHAR(128) NOT NULL ,
  telephone VARCHAR(128) NULL ,
  email VARCHAR(128) NULL ,
  title VARCHAR(128) NULL ,
  department_code VARCHAR(128) NULL ,
  geo_code VARCHAR(128) NULL ,
  city_of_employment VARCHAR(128) NULL ,
  state_of_employment_id BIGINT NULL ,
  address_id BIGINT NULL,
  holder_position VARCHAR(128),
  agency_code VARCHAR(128),
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')));

-- -----------------------------------------------------
-- Table account_history
-- -----------------------------------------------------
CREATE TABLE opm.account_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NOT NULL ,
  plan_type VARCHAR(128) NULL ,
  form_type_id BIGINT NULL,
  account_holder_id BIGINT NOT NULL,
  account_status_id BIGINT NOT NULL,
  grace BOOLEAN NOT NULL ,
  frozen BOOLEAN NOT NULL ,
  claim_officer VARCHAR(128) NULL ,
  claim_officer_assignment_date TIMESTAMP NULL ,
  returned_from_record_date TIMESTAMP NULL ,
  claimant_birthdate TIMESTAMP NULL ,
  balance DECIMAL(10,2) NULL ,
  billing_summary_id BIGINT NULL,
  account_confirmation_validation_id BIGINT NULL,
  total_deposit DECIMAL(10,2) NULL,
  total_redeposit DECIMAL(10,2) NULL,
  total_var_redeposit DECIMAL(10,2) NULL,
  total_non_deposit DECIMAL(10,2) NULL,
  total_fers_w DECIMAL(10,2) NULL,
  acc_int_dep DECIMAL(10,2) NULL,
  acc_int_rdep DECIMAL(10,2) NULL,
  acc_int_non_dep DECIMAL(10,2) NULL,
  acc_int_var_rdep DECIMAL(10,2) NULL,
  acc_int_fers DECIMAL(10,2) NULL,
  tot_pay_d DECIMAL(10,2) NULL,
  tot_pay_r DECIMAL(10,2) NULL,
  tot_pay_n DECIMAL(10,2) NULL,
  tot_pay_vr DECIMAL(10,2) NULL,
  tot_pay_fers DECIMAL(10,2) NULL,
  computation_date TIMESTAMP NULL,
  var_int_computation_date TIMESTAMP NULL,  
  last_action VARCHAR(128) NULL,
  last_action_time TIMESTAMP NULL,
  last_pay TIMESTAMP NULL,
  pay_code_id BIGINT NULL,
  time_period VARCHAR(128) NULL,
  additional_service VARCHAR(1024) NULL,
  no_interest BOOLEAN NULL,
  code_20_date TIMESTAMP NULL,
  flag_preredeposit BOOLEAN NULL,
  flag_postredeposit BOOLEAN NULL,
  prior_claim_number VARCHAR(128) NULL,
  payment_order VARCHAR(128) NULL,
  new_claim_number VARCHAR(128) NULL,
  stop_ach_payment BOOLEAN NULL,
  dbts_account BOOLEAN NULL,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')));

-- -----------------------------------------------------
-- Table address_history
-- -----------------------------------------------------
CREATE TABLE opm.address_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  street1 VARCHAR(128) NOT NULL ,
  street2 VARCHAR(128) NULL ,
  street3 VARCHAR(128) NULL ,
  street4 VARCHAR(128) NULL ,
  street5 VARCHAR(128) NULL ,
  city VARCHAR(128) NOT NULL ,
  state_id BIGINT NULL,
  zip_code VARCHAR(128) NULL ,
  country_id BIGINT NULL,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table account_note_history
-- -----------------------------------------------------
CREATE TABLE opm.account_note_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  date TIMESTAMP NOT NULL ,
  writer VARCHAR(120) NOT NULL ,
  text TEXT NOT NULL ,
  account_id BIGINT NULL ,
  priority INTEGER NULL,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );
    
-- -----------------------------------------------------
-- Table billing_summary_history
-- -----------------------------------------------------
CREATE TABLE opm.billing_summary_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  computed_date TIMESTAMP NOT NULL ,
  last_deposit_date TIMESTAMP NULL ,
  first_billing_date TIMESTAMP NOT NULL ,
  last_interest_calculation TIMESTAMP NOT NULL ,
  transaction_type VARCHAR(128) NOT NULL ,
  last_transaction_date TIMESTAMP NOT NULL ,
  stop_ach_payments BOOLEAN NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table billing_history
-- -----------------------------------------------------
CREATE TABLE opm.billing_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  initial_billing DECIMAL(10,2) NOT NULL ,
  additional_interest DECIMAL(10,2) NOT NULL ,
  total_payments DECIMAL(10,2) NOT NULL ,
  balance DECIMAL(10,2) NOT NULL ,
  payment_order INT  NOT NULL ,
  billing_summary_id BIGINT NULL ,
  frozen BOOLEAN NULL,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table calculation_version_history
-- -----------------------------------------------------
CREATE TABLE opm.calculation_version_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NULL ,
  calculation_date TIMESTAMP NULL ,
  calculation_result_id BIGINT NULL,
  version INTEGER NULL,
  line_number INTEGER NULL,
  account_id BIGINT NULL,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );


-- -----------------------------------------------------
-- Table calculation_history
-- -----------------------------------------------------
CREATE TABLE opm.calculation_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  begin_date TIMESTAMP NOT NULL ,
  end_date TIMESTAMP NOT NULL ,
  retirement_type_id BIGINT NULL,
  period_type_id BIGINT NOT NULL,
  appointment_type_id BIGINT NULL,
  service_type_id BIGINT NULL,
  amount DECIMAL(10,2) NOT NULL ,
  pay_type_id BIGINT NULL,
  agency_code_id BIGINT NULL,
  hours_in_year INTEGER NULL,
  annualized_amount DECIMAL(10,2) NULL,
  date_entered TIMESTAMP NULL,
  entered_by BIGINT NULL,
  calculation_version_id BIGINT NULL ,
  frozen BOOLEAN NULL,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table calculation_result_history
-- -----------------------------------------------------
CREATE TABLE opm.calculation_result_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  calculation_status_id BIGINT NULL,
  official BOOLEAN NULL ,
  apply_to_real_payment BOOLEAN NULL ,
  summary_data_id BIGINT NULL,
  payment_order INTEGER NULL,
  interest_accrual_date TIMESTAMP NULL,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table calculation_result_item_history
-- -----------------------------------------------------
CREATE TABLE opm.calculation_result_item_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  start_date TIMESTAMP NOT NULL ,
  end_date TIMESTAMP NOT NULL ,
  mid_date TIMESTAMP NULL ,
  effective_date TIMESTAMP NULL ,
  period_type_id BIGINT NOT NULL,
  deduction_amount DECIMAL(10,2) NOT NULL ,
  total_interest DECIMAL(10,2) NOT NULL ,
  payment_applied DECIMAL(10,2) NOT NULL ,
  balance DECIMAL(10,2) NOT NULL ,
  calculation_result_id BIGINT NULL ,
  service_category VARCHAR(128) NULL,
  retirement_type_id BIGINT NULL,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table redeposit_history
-- -----------------------------------------------------
CREATE TABLE opm.redeposit_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  label VARCHAR(128) NOT NULL,
  deposit DECIMAL(10,2) NOT NULL ,
  interest DECIMAL(10,2) NOT NULL ,
  total DECIMAL(10,2) NOT NULL ,
  calculation_result_id BIGINT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table dedeposit_history
-- -----------------------------------------------------
CREATE TABLE opm.dedeposit_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  label VARCHAR(128) NOT NULL,
  deposit DECIMAL(10,2) NOT NULL ,
  interest DECIMAL(10,2) NOT NULL ,
  total DECIMAL(10,2) NOT NULL ,
  calculation_result_id BIGINT NULL,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table summary_data_history
-- -----------------------------------------------------
CREATE TABLE opm.summary_data_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  total_payments_required DECIMAL(10,2) NOT NULL ,
  total_initial_interest DECIMAL(10,2) NOT NULL ,
  total_payments_applied DECIMAL(10,2) NOT NULL ,
  total_balance DECIMAL(10,2) NOT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table refund_transaction_history
-- -----------------------------------------------------
CREATE TABLE opm.refund_transaction_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  transaction_key VARCHAR(128) NULL ,
  amount DECIMAL(10,2) NOT NULL ,
  claim_number VARCHAR(128) NOT NULL ,
  refund_date TIMESTAMP NOT NULL ,
  refund_username VARCHAR(128) NULL ,
  transfer_type_id BIGINT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table notification_READBY_history
-- -----------------------------------------------------
CREATE TABLE opm.notification_READBY_history (
  id BIGINT NOT NULL,
  notification_id BIGINT NOT NULL,
  value VARCHAR(128) NOT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table pay_trans_status_code_history
-- -----------------------------------------------------
CREATE TABLE opm.pay_trans_status_code_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  description VARCHAR(128) NULL ,
  category VARCHAR(128) NULL ,
  display_order INTEGER NULL ,
  next_state_link INTEGER NULL ,
  batch_processing_order INTEGER NULL ,
  final_state BOOLEAN NULL ,
  needs_approval BOOLEAN NULL ,
  show_on_suspense BOOLEAN NULL ,
  include_in_balance BOOLEAN NULL ,
  nightly_batch BOOLEAN NULL ,
  deletable BOOLEAN NULL ,
  reversable BOOLEAN NULL ,
  manual_entered BOOLEAN NULL ,
  suspense_action BOOLEAN NULL ,
  can_hit_gl BOOLEAN NULL ,
  reversing_type BOOLEAN NULL ,
  balanced_scorecard BOOLEAN NULL ,
  send_to_dbts BOOLEAN NULL,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table payment_history
-- -----------------------------------------------------
CREATE TABLE opm.payment_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  batch_number VARCHAR(128) NOT NULL ,
  block_number VARCHAR(128) NOT NULL ,
  sequence_number VARCHAR(128) NOT NULL ,
  claim_number VARCHAR(128) NULL ,
  payment_status_id BIGINT NULL,
  account_holder_birthdate TIMESTAMP NULL ,
  deposit_date TIMESTAMP NULL ,
  amount DECIMAL(10,2) NOT NULL ,
  ssn VARCHAR(128) NULL ,
  claimant VARCHAR(128) NULL ,
  claimant_birthday TIMESTAMP NULL ,
  import_id VARCHAR(128) NULL ,
  sequence INT NULL ,
  transaction_date TIMESTAMP NULL ,
  status_date TIMESTAMP NULL ,
  apply_designation_id BIGINT NULL,
  apply_to_gl BOOLEAN NOT NULL ,
  note VARCHAR(2048) NULL ,
  transaction_key VARCHAR(128) NULL ,
  ach BOOLEAN NULL ,
  account_balance DECIMAL(10,2) NULL ,
  account_status_id BIGINT NULL,
  master_claim_number VARCHAR(128) NULL ,
  master_claimant_birthday TIMESTAMP NULL ,
  master_account_status_id BIGINT NULL ,
  master_account_balance DECIMAL(10,2) NULL ,
  master_account_id BIGINT NULL,
  pre_deposit_amount DECIMAL(10,2) NULL ,
  pre_redeposit_amount DECIMAL(10,2) NULL ,
  post_deposit_amount DECIMAL(10,2) NULL ,
  post_redeposit_amount DECIMAL(10,2) NULL ,
  approval_user VARCHAR(128) NULL ,
  approval_status VARCHAR(128) NULL,
  approval_reason VARCHAR(255) NULL,
  payment_type VARCHAR(128) NULL ,
  account_id BIGINT NULL,
  gov_refund BOOLEAN NULL,
  disapprove BOOLEAN NULL,
  history_payment BOOLEAN NULL,
  resolve_suspense BOOLEAN NULL,
  user_inserted BOOLEAN NULL,
  post_flag BOOLEAN NULL,
  order_code_id BIGINT NULL,
  pay_trans_status_code_id BIGINT NULL,
  payment_appliance_id BIGINT,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table payment_reverse_history
-- -----------------------------------------------------
CREATE TABLE opm.payment_reverse_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  payment_id BIGINT NOT NULL,
  payment_reversal_reason_id BIGINT NOT NULL,
  apply_to_gl BOOLEAN NOT NULL ,
  reverser VARCHAR(128) NOT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table account_confirmation_validation_history
-- -----------------------------------------------------
CREATE TABLE opm.account_confirmation_validation_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  account_id BIGINT NOT NULL,
  data_check_status VARCHAR(128) NOT NULL,
  data_check_status_validator VARCHAR(128) NOT NULL ,
  data_check_status_reason VARCHAR(128) NOT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table account_confirmation_validation_entry_history
-- -----------------------------------------------------
CREATE TABLE opm.account_confirmation_validation_entry_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  field_name VARCHAR(128) NOT NULL ,
  valid BOOLEAN NOT NULL ,
  account_confirmation_validation_id BIGINT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table service_credit_preference_history
-- -----------------------------------------------------
CREATE TABLE opm.service_credit_preference_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  use_agents BOOLEAN NOT NULL ,
  use_status_bar BOOLEAN NOT NULL ,
  use_message_box BOOLEAN NOT NULL ,
  other VARCHAR(128) NOT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table payment_transaction
-- -----------------------------------------------------
CREATE TABLE opm.payment_transaction (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  pay_trans_batch_number VARCHAR(256) NULL,
  pay_trans_block_number VARCHAR(256) NULL,
  pay_trans_sequence_number VARCHAR(256) NULL,
  scm_claim_number VARCHAR(256) NULL,
  scm_date_of_birth TIMESTAMP NULL,
  pay_trans_payment_amount DECIMAL(10,6) NULL,
  pay_trans_transaction_date TIMESTAMP NULL,
  pay_trans_status_code BIGINT NULL,
  pay_trans_status_date TIMESTAMP NULL,
  technician_user_key BIGINT NULL,
  payment_applied_order_code VARCHAR(256) NULL,
  post_flag BOOLEAN NULL,
  csd VARCHAR(256) NULL,
  user_inserted BOOLEAN NULL,
  ach_payment BOOLEAN NULL,
  payment_status_code INTEGER NULL,
  resolved_suspense BOOLEAN NULL,
  update_to_completed BOOLEAN NULL,
  history_payment BOOLEAN NULL,
  gov_refund BOOLEAN NULL,
  disapprove BOOLEAN NULL,
  pay_transaction_key INTEGER NULL,
  PRIMARY KEY (id));



  
-- -----------------------------------------------------
-- Table invoice_data
-- -----------------------------------------------------
CREATE TABLE opm.invoice_data (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  pay_transaction_key INTEGER NULL,
  scm_claimnumber VARCHAR(256) NULL,
  scm_date_of_birth TIMESTAMP NULL,
  scm_name VARCHAR(256) NULL,
  account_status INTEGER NULL,
  account_status_description VARCHAR(256) NULL,
  account_balance DECIMAL(10,6) NULL,
  account_payment_total DECIMAL(10,6) NULL,
  account_balance_new DECIMAL(10,6) NULL,
  todays_payment_total DECIMAL(10,6) NULL,
  pay_trans_status_code INTEGER NULL,
  pay_trans_status_description VARCHAR(256) NULL,
  pay_trans_payment_amount DECIMAL(10,6) NULL,
  over_payment_amount DECIMAL(10,6) NULL,
  pay_trans_transaction_date TIMESTAMP NULL,
  retirement_type_code INTEGER NULL,
  retirement_type_description VARCHAR(256) NULL,
  ach_payment BOOLEAN NULL,
  payment_application_order VARCHAR(256) NULL,
  note VARCHAR(256) NULL,
  pre_1082_deposit_total_payment DECIMAL(10,6) NULL,
  pre_1082_redeposit_total_payment DECIMAL(10,6) NULL,
  post_1082_deposit_total_payment DECIMAL(10,6) NULL,
  post_1082_redeposit_total_payment DECIMAL(10,6) NULL,
  fers_total_payment DECIMAL(10,6) NULL,
  ach_stop_letter BOOLEAN NULL,
  print_initial_bill BOOLEAN NULL,
  update_completed BOOLEAN NULL,
  reversed_payment BOOLEAN NULL,
  print_invoice BOOLEAN NULL,
  refund_required BOOLEAN NULL,
  update_to_completed BOOLEAN NULL,
  over_the_payment_amount DECIMAL(10,6) NULL,
  number_payments_today INTEGER NULL,
  account_note_type VARCHAR(256) NULL,
  PRIMARY KEY (id));

 -- -----------------------------------------------------
-- Table all_details
-- -----------------------------------------------------
CREATE TABLE opm.all_details (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  payment_type VARCHAR(256) NULL,
  payment_date TIMESTAMP NULL,
  julian_date INTEGER NULL,
  julian_date_report INTEGER NULL,
  gl_filler VARCHAR(256) NULL,
  gl_code VARCHAR(256) NULL,
  fiscal_year INTEGER NULL,
  gl_accounting_code VARCHAR(256) NULL,
  recipient_amount DECIMAL(10,6) NULL,
  revenue_source_code VARCHAR(256) NULL,
  agency VARCHAR(256) NULL,
  pay_transaction_key VARCHAR(256) NULL,
  scm_claim_number VARCHAR(256) NULL,
  scm_date_of_birth VARCHAR(256) NULL,
  scm_retirement_type_description VARCHAR(256) NULL,
  claimant_name VARCHAR(256) NULL,
  print_date TIMESTAMP NULL,
  total_non_postal_fers DECIMAL(10,6) NULL,
  total_postal_fers DECIMAL(10,6) NULL,
  total_csrs DECIMAL(10,6) NULL,
  julian_now INTEGER NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table audit_batch_log_id
-- -----------------------------------------------------
CREATE TABLE opm.audit_batch_log_id (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  audit_batch_log_id VARCHAR(256),
  batch_date TIMESTAMP,
  batch_number INT,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table mainframe_import
-- -----------------------------------------------------
CREATE TABLE opm.mainframe_import (
  id BIGSERIAL NOT NULL,
  record_string VARCHAR(256),
  import_date TIMESTAMP,
  processing_flag BOOLEAN,
  error_flag BOOLEAN,
  ach_flag BOOLEAN,
  file_name VARCHAR(256),
  audit_batch_log_id VARCHAR(256),
  pay_transaction_key INT,
  payment_type VARCHAR(128),
  deleted BOOLEAN NULL,
  audit_batch_id BIGINT NULL,
  suspended_flag BOOLEAN NULL,
  unresolved_flag BOOLEAN NULL,
  postedPending_flag BOOLEAN NULL,
  ach_status_checked BOOLEAN NULL,
  batch_daily_payments BIGINT NULL,
  pay_trans_key BIGINT NULL,
  PRIMARY KEY (id));



-- -----------------------------------------------------
-- Table payments_applied_order_code_history
-- -----------------------------------------------------
CREATE TABLE opm.payments_applied_order_code_history (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  payment_account VARCHAR(128) NULL ,
  display_order INTEGER NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table payment_refund_link_history
-- -----------------------------------------------------
CREATE TABLE opm.payment_refund_link_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  payment_needing_refund BIGINT NULL,
  refund_for_payment BIGINT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table user_account_assignment_history
-- -----------------------------------------------------
CREATE TABLE opm.user_account_assignment_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  user_id BIGINT NULL,
  account_id BIGINT NULL ,
  assignment_date TIMESTAMP NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table a01_print_suppression_case_history
-- -----------------------------------------------------
CREATE TABLE opm.a01_print_suppression_case_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL ,
  reason_for_print_suppression INTEGER NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table invoice_history
-- -----------------------------------------------------
CREATE TABLE opm.invoice_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  pay_transaction_key BIGINT NULL,
  deposit DECIMAL(10,2) NULL,
  redeposit DECIMAL(10,2) NULL,
  tot_var_redeposit DECIMAL(10,2) NULL,
  non_ded DECIMAL(10,2) NULL,
  fers_w DECIMAL(10,2) NULL,
  acc_int_dep DECIMAL(10,2) NULL,
  acc_int_rdep DECIMAL(10,2) NULL,
  acc_int_non_dep DECIMAL(10,2) NULL,
  acc_int_var_rdep DECIMAL(10,2) NULL,
  acc_int_fers DECIMAL(10,2) NULL,
  tot_pay_d DECIMAL(10,2) NULL,
  tot_pay_r DECIMAL(10,2) NULL,
  tot_pay_n DECIMAL(10,2) NULL,
  tot_pay_vr DECIMAL(10,2) NULL,
  tot_pay_fers DECIMAL(10,2) NULL,
  last_pay TIMESTAMP NULL,
  calc_date TIMESTAMP NULL,
  last_invoice_id BIGINT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );
  
-- -----------------------------------------------------
-- Table adjustment_transaction_history
-- -----------------------------------------------------
CREATE TABLE opm.adjustment_transaction_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  pay_transaction_key BIGINT NULL,
  claim_number VARCHAR(128) NULL,
  acc_int_dep DECIMAL(10,2) NULL,
  acc_int_rdep DECIMAL(10,2) NULL,
  acc_int_non_dep DECIMAL(10,2) NULL,
  acc_int_var_rdep DECIMAL(10,2) NULL,
  acc_int_fers DECIMAL(10,2) NULL,
  modification_date TIMESTAMP NULL,
  approved_date TIMESTAMP NULL,
  processed_date TIMESTAMP NULL,
  technician_user_key BIGINT NULL,
  manager_user_key BIGINT NULL,
  approved BOOLEAN NULL,
  disapproved BOOLEAN NULL,  
  modified BOOLEAN NULL,
  note VARCHAR(1024) NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );
  
-- -----------------------------------------------------
-- Table payment_interest_detail_history
-- -----------------------------------------------------
CREATE TABLE opm.payment_interest_detail_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  pay_transaction_key BIGINT NULL,
  account_type BIGINT NULL,
  num_whole_years INTEGER NULL,
  calculated_interest DECIMAL(10,2) NULL,
  last_pay_to_eoy_factor DECIMAL(10,2) NULL,
  partial_to_this_factor DECIMAL(10,2) NULL,
  this_interest_rate DECIMAL(10,2) NULL,
  last_payment_date TIMESTAMP NULL,
  transaction_date TIMESTAMP NULL,
  computed_date TIMESTAMP NULL,
  post BOOLEAN NULL,
  gui BOOLEAN NULL,
  last_payment_was_this_year BOOLEAN NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );
    
-- -----------------------------------------------------
-- Table interest_rate_history
-- -----------------------------------------------------
CREATE TABLE opm.interest_rate_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  interest_year INTEGER NULL,
  interest_rate DECIMAL(10,6) NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table payment_move_transaction_history
-- -----------------------------------------------------
CREATE TABLE opm.payment_move_transaction_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  pay_transaction_key BIGINT NULL,
  claim_number VARCHAR(128) NULL,
  tot_pay_d DECIMAL(10,2) NULL,
  tot_pay_r DECIMAL(10,2) NULL,
  tot_pay_n DECIMAL(10,2) NULL,
  tot_pay_vr DECIMAL(10,2) NULL,
  tot_pay_fers DECIMAL(10,2) NULL,
  modification_date TIMESTAMP NULL,
  approved_date TIMESTAMP NULL,
  processed_date TIMESTAMP NULL,
  technician_user_key BIGINT NULL,
  manager_user_key BIGINT NULL,
  approved BOOLEAN NULL,
  disapproved BOOLEAN NULL,  
  modified BOOLEAN NULL,
  note VARCHAR(1024) NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );
  
-- -----------------------------------------------------
-- Table payment_transaction_note_history
-- -----------------------------------------------------
CREATE TABLE opm.payment_transaction_note_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  pay_transaction_key BIGINT NULL,
  note VARCHAR(1024) NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );
  
-- -----------------------------------------------------
-- Table claim_without_service_history
-- -----------------------------------------------------
CREATE TABLE opm.claim_without_service_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL,
  date_of_birth TIMESTAMP NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );
  
-- -----------------------------------------------------
-- Table gl_code_history
-- -----------------------------------------------------
CREATE TABLE opm.gl_code_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NULL,
  code VARCHAR(128) NULL,
  payment_type VARCHAR(128) NULL,
  retirement_type_id BIGINT NULL,
  post_office BOOLEAN NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table interest_grace_period_history
-- -----------------------------------------------------
CREATE TABLE opm.interest_grace_period_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL,
  post_982_redeposit BOOLEAN NULL,
  pre_1082_redeposit BOOLEAN NULL,
  post_982_deposit BOOLEAN NULL,
  pre_1082_deposit BOOLEAN NULL,
  fers_deposit BOOLEAN NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );
    
-- -----------------------------------------------------
-- Table deduction_rate_history
-- -----------------------------------------------------
CREATE TABLE opm.deduction_rate_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  service_type VARCHAR(128) NULL,
  retirement_type_id BIGINT NULL,
  start_date TIMESTAMP NULL,
  end_date TIMESTAMP NULL,
  days_in_period INTEGER NULL,
  rate DECIMAL(10,6) NULL,
  service_type_description VARCHAR(1024) NULL,
  deduction_conversion_factor DECIMAL(10,6) NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );
    
-- -----------------------------------------------------
-- Table holiday_history
-- -----------------------------------------------------
CREATE TABLE opm.holiday_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  holiday VARCHAR(128) NULL,
  exact_date BOOLEAN NULL,
  week_day INTEGER NULL,
  month_number INTEGER NULL,
  day_of_month INTEGER NULL,
  week_of_month INTEGER NULL,
  holiday_id INTEGER NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );
  
-- -----------------------------------------------------
-- Table scm_first_insert_history
-- -----------------------------------------------------
CREATE TABLE opm.scm_first_insert_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL,
  last_action TIMESTAMP NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );
  
-- -----------------------------------------------------
-- Table gl_payment_type_history
-- -----------------------------------------------------
CREATE TABLE opm.gl_payment_type_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  payment_code VARCHAR(128) NULL,
  code_description VARCHAR(1024) NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table interest_suppression_history
-- -----------------------------------------------------
CREATE TABLE opm.interest_suppression_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL,
  post_982_redeposit BOOLEAN  NULL,
  pre_1082_redeposit BOOLEAN NULL,
  post_982_deposit BOOLEAN NULL,
  pre_1082_deposit BOOLEAN NULL,
  fers_deposit BOOLEAN NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table contact_info_history
-- -----------------------------------------------------
CREATE TABLE opm.contact_info_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NULL,
  text VARCHAR(1024) NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table time_factor_history
-- -----------------------------------------------------
CREATE TABLE opm.time_factor_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  num_days INTEGER NULL,
  num_months INTEGER NULL,
  time_factor DECIMAL(10,6) NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table annuitant_list_history
-- -----------------------------------------------------
CREATE TABLE opm.annuitant_list_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table new_claim_number_history
-- -----------------------------------------------------
CREATE TABLE opm.new_claim_number_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  claim_number VARCHAR(128) NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table audit_batch_history
-- -----------------------------------------------------
CREATE TABLE opm.audit_batch_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  event_year INTEGER NULL,
  event_month INTEGER NULL,
  event_day INTEGER NULL,
  file_received BOOLEAN NULL,
  daily_action BOOLEAN NULL,
  manual_batch BOOLEAN NULL,
  error_importing BOOLEAN NULL,
  error_processing BOOLEAN NULL,
  latest_batch BOOLEAN NULL,
  amount_imported DECIMAL(10,6) NULL,
  amount_processed DECIMAL(10,6) NULL,
  number_accepted INTEGER NULL,
  number_unresolved INTEGER NULL,
  number_suspended INTEGER NULL,
  number_ach_accepted INTEGER NULL,
  number_ach_unresolved INTEGER NULL,
  number_ach_suspended INTEGER NULL,
  number_change_requests INTEGER NULL,
  payments_processed INTEGER NULL,
  initial_bills_processed INTEGER NULL,
  reversed_processed INTEGER NULL,
  ach_stop_letters INTEGER NULL,
  refund_memos INTEGER NULL,
  error_count_processing INTEGER NULL,
  error_count_importing INTEGER NULL,
  user_key BIGINT NULL,
  batch_time TIMESTAMP NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );

-- -----------------------------------------------------
-- Table batch_daily_payments_history
-- -----------------------------------------------------
CREATE TABLE opm.batch_daily_payments_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  audit_batch_id BIGINT NULL,
  pay_transaction_key BIGINT NULL,
  number_payment_today INTEGER NULL,
  batch_time TIMESTAMP NULL,
  account_status_id BIGINT NULL,
  pay_trans_status_code BIGINT NULL,
  claim_number VARCHAR(128) NULL ,
  account_balance DECIMAL(10,6) NULL ,
  over_payment_amount DECIMAL(10,6) NULL ,
  ach_payment BOOLEAN NULL,
  ach_stop_letter BOOLEAN NULL,
  print_invoice BOOLEAN NULL,
  refund_required BOOLEAN NULL,
  reversed_payment BOOLEAN NULL,
  update_to_completed BOOLEAN NULL,
  print_initial_bill BOOLEAN NULL,
  latest_batch BOOLEAN NULL,
  error_processing BOOLEAN NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')) );
  

-- -----------------------------------------------------
-- Table payment_statement_print
-- -----------------------------------------------------
CREATE TABLE opm.payment_statement_print (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  message VARCHAR(8192) NOT NULL,
  message_date TIMESTAMP NOT NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table report_generation_data
-- -----------------------------------------------------
CREATE TABLE opm.report_generation_data (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  payment_invoices_processed INT NULL ,
  bills_printed INT NULL ,
  reveals_printed INT NULL ,
  letters_printed INT NULL ,
  refunds_printed INT NULL ,
  PRIMARY KEY (id) );


-- -----------------------------------------------------
-- Table report_generation_data_history
-- -----------------------------------------------------
CREATE TABLE opm.report_generation_data_history (
  id BIGINT NOT NULL,
  deleted BOOLEAN NOT NULL,
  payment_invoices_processed INT NULL ,
  bills_printed INT NULL ,
  reveals_printed INT NULL ,
  letters_printed INT NULL ,
  refunds_printed INT NULL ,
  action_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  action VARCHAR(1) NOT NULL CHECK (action IN ('I','D','U')));
  
  -- -----------------------------------------------------
-- Table letter
-- -----------------------------------------------------
CREATE TABLE opm.letter (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  content TEXT NULL ,
  PRIMARY KEY (id) );
  
    
  -- -----------------------------------------------------
-- Table reference
-- -----------------------------------------------------
CREATE TABLE opm.reference (
  id BIGSERIAL NOT NULL,
  deleted BOOLEAN NOT NULL,
  name VARCHAR(128) NOT NULL ,
  content TEXT NULL ,
  PRIMARY KEY (id) );
