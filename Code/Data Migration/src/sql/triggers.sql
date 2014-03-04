-- -----------------------------------------------------
-- Table app_user trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.app_user_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.app_user_history(id, deleted, username, default_tab, network_id, role_id, first_name, last_name, email, telephone, user_status_id, supervisor_id, action)
            VALUES (NEW.id, NEW.deleted, NEW.username, NEW.default_tab, NEW.network_id, NEW.role_id, NEW.first_name, NEW.last_name, NEW.email, NEW.telephone, NEW.user_status_id, NEW.supervisor_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.app_user_history(id, deleted, username, default_tab, network_id, role_id, first_name, last_name, email, telephone, user_status_id, supervisor_id, action)
            VALUES (OLD.id, OLD.deleted, OLD.username, OLD.default_tab, OLD.network_id, OLD.role_id, OLD.first_name, OLD.last_name, OLD.email, OLD.telephone, OLD.user_status_id, OLD.supervisor_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER app_user_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.app_user
  FOR EACH ROW EXECUTE PROCEDURE opm.app_user_func();
  
-- -----------------------------------------------------
-- Table notification trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.notification_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.notification_history(id, deleted, date, details, sender, read, recipient, recipient_role_id, action)
            VALUES (NEW.id, NEW.deleted, NEW.date, NEW.details, NEW.sender, NEW.read, NEW.recipient, NEW.recipient_role_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.notification_history(id, deleted, date, details, sender, read, recipient, recipient_role_id, action)
            VALUES (OLD.id, OLD.deleted, OLD.date, OLD.details, OLD.sender, OLD.read, OLD.recipient, OLD.recipient_role_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER notification_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.notification
  FOR EACH ROW EXECUTE PROCEDURE opm.notification_func();
  
-- -----------------------------------------------------
-- Table info trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.info_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.info_history(id, deleted, date, details, action)
            VALUES (NEW.id, NEW.deleted, NEW.date, NEW.details, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.info_history(id, deleted, date, details, action)
            VALUES (OLD.id, OLD.deleted, OLD.date, OLD.details, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER info_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.info
  FOR EACH ROW EXECUTE PROCEDURE opm.info_func();
  
-- -----------------------------------------------------
-- Table error trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.error_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.error_history(id, deleted, date, details, action)
            VALUES (NEW.id, NEW.deleted, NEW.date, NEW.details, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.error_history(id, deleted, date, details, action)
            VALUES (OLD.id, OLD.deleted, OLD.date, OLD.details, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER error_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.error
  FOR EACH ROW EXECUTE PROCEDURE opm.error_func();
  
-- -----------------------------------------------------
-- Table account_holder trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.account_holder_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.account_holder_history(id, deleted, last_name, first_name, middle_initial, suffix_id, birth_date, ssn, telephone, email, title, department_code, geo_code, city_of_employment, state_of_employment_id, address_id, holder_position, agency_code, action)
            VALUES (NEW.id, NEW.deleted, NEW.last_name, NEW.first_name, NEW.middle_initial, NEW.suffix_id, NEW.birth_date, NEW.ssn, NEW.telephone, NEW.email, NEW.title, NEW.department_code, NEW.geo_code, NEW.city_of_employment, NEW.state_of_employment_id, NEW.address_id, NEW.holder_position, NEW.agency_code, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.account_holder_history(id, deleted, last_name, first_name, middle_initial, suffix_id, birth_date, ssn, telephone, email, title, department_code, geo_code, city_of_employment, state_of_employment_id, address_id, holder_position, agency_code, action)
            VALUES (OLD.id, OLD.deleted, OLD.last_name, OLD.first_name, OLD.middle_initial, OLD.suffix_id, OLD.birth_date, OLD.ssn, OLD.telephone, OLD.email, OLD.title, OLD.department_code, OLD.geo_code, OLD.city_of_employment, OLD.state_of_employment_id, OLD.address_id, OLD.holder_position, OLD.agency_code, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER account_holder_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.account_holder
  FOR EACH ROW EXECUTE PROCEDURE opm.account_holder_func();
  
-- -----------------------------------------------------
-- Table account trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.account_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.account_history(id, deleted, claim_number, plan_type, form_type_id, account_holder_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, balance, billing_summary_id, account_confirmation_validation_id, total_deposit, total_redeposit, total_var_redeposit, total_non_deposit, total_fers_w, acc_int_dep, acc_int_rdep, acc_int_non_dep, acc_int_var_rdep, acc_int_fers, tot_pay_d, tot_pay_r, tot_pay_n, tot_pay_vr, tot_pay_fers, computation_date, var_int_computation_date, last_action, last_pay, pay_code_id, time_period, additional_service, no_interest, code_20_date, flag_preredeposit, flag_postredeposit, prior_claim_number, payment_order, new_claim_number, stop_ach_payment, dbts_account, action)
            VALUES (NEW.id, NEW.deleted, NEW.claim_number, NEW.plan_type, NEW.form_type_id, NEW.account_holder_id, NEW.account_status_id, NEW.grace, NEW.frozen, NEW.claim_officer, NEW.claim_officer_assignment_date, NEW.returned_from_record_date, NEW.claimant_birthdate, NEW.balance, NEW.billing_summary_id, NEW.account_confirmation_validation_id, NEW.total_deposit, NEW.total_redeposit, NEW.total_var_redeposit, NEW.total_non_deposit, NEW.total_fers_w, NEW.acc_int_dep, NEW.acc_int_rdep, NEW.acc_int_non_dep, NEW.acc_int_var_rdep, NEW.acc_int_fers, NEW.tot_pay_d, NEW.tot_pay_r, NEW.tot_pay_n, NEW.tot_pay_vr, NEW.tot_pay_fers, NEW.computation_date, NEW.var_int_computation_date, NEW.last_action, NEW.last_pay, NEW.pay_code_id, NEW.time_period, NEW.additional_service, NEW.no_interest, NEW.code_20_date, NEW.flag_preredeposit, NEW.flag_postredeposit, NEW.prior_claim_number, NEW.payment_order, NEW.new_claim_number, NEW.stop_ach_payment, NEW.dbts_account, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.account_history(id, deleted, claim_number, plan_type, form_type_id, account_holder_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, balance, billing_summary_id, account_confirmation_validation_id, total_deposit, total_redeposit, total_var_redeposit, total_non_deposit, total_fers_w, acc_int_dep, acc_int_rdep, acc_int_non_dep, acc_int_var_rdep, acc_int_fers, tot_pay_d, tot_pay_r, tot_pay_n, tot_pay_vr, tot_pay_fers, computation_date, var_int_computation_date, last_action, last_pay, pay_code_id, time_period, additional_service, no_interest, code_20_date, flag_preredeposit, flag_postredeposit, prior_claim_number, payment_order, new_claim_number, stop_ach_payment, dbts_account, action)
            VALUES (OLD.id, OLD.deleted, OLD.claim_number, OLD.plan_type, OLD.form_type_id, OLD.account_holder_id, OLD.account_status_id, OLD.grace, OLD.frozen, OLD.claim_officer, OLD.claim_officer_assignment_date, OLD.returned_from_record_date, OLD.claimant_birthdate, OLD.balance, OLD.billing_summary_id, OLD.account_confirmation_validation_id, OLD.total_deposit, OLD.total_redeposit, OLD.total_var_redeposit, OLD.total_non_deposit, OLD.total_fers_w, OLD.acc_int_dep, OLD.acc_int_rdep, OLD.acc_int_non_dep, OLD.acc_int_var_rdep, OLD.acc_int_fers, OLD.tot_pay_d, OLD.tot_pay_r, OLD.tot_pay_n, OLD.tot_pay_vr, OLD.tot_pay_fers, OLD.computation_date, OLD.var_int_computation_date, OLD.last_action, OLD.last_pay, OLD.pay_code_id, OLD.time_period, OLD.additional_service, OLD.no_interest, OLD.code_20_date, OLD.flag_preredeposit, OLD.flag_postredeposit, OLD.prior_claim_number, OLD.payment_order, OLD.new_claim_number, OLD.stop_ach_payment, OLD.dbts_account, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER account_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.account
  FOR EACH ROW EXECUTE PROCEDURE opm.account_func();
  
-- -----------------------------------------------------
-- Table address trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.address_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.address_history(id, deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id, action)
            VALUES (NEW.id, NEW.deleted, NEW.street1, NEW.street2, NEW.street3, NEW.street4, NEW.street5, NEW.city, NEW.state_id, NEW.zip_code, NEW.country_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.address_history(id, deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id, action)
            VALUES (OLD.id, OLD.deleted, OLD.street1, OLD.street2, OLD.street3, OLD.street4, OLD.street5, OLD.city, OLD.state_id, OLD.zip_code, OLD.country_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER address_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.address
  FOR EACH ROW EXECUTE PROCEDURE opm.address_func();
  
-- -----------------------------------------------------
-- Table account_note trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.account_note_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.account_note_history(id, deleted, date, writer, text, account_id, priority, action)
            VALUES (NEW.id, NEW.deleted, NEW.date, NEW.writer, NEW.text, NEW.account_id, NEW.priority, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.account_note_history(id, deleted, date, writer, text, account_id, priority, action)
            VALUES (OLD.id, OLD.deleted, OLD.date, OLD.writer, OLD.text, OLD.account_id, OLD.priority, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER account_note_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.account_note
  FOR EACH ROW EXECUTE PROCEDURE opm.account_note_func();
  
-- -----------------------------------------------------
-- Table billing_summary trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.billing_summary_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.billing_summary_history(id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments, action)
            VALUES (NEW.id, NEW.deleted, NEW.computed_date, NEW.last_deposit_date, NEW.first_billing_date, NEW.last_interest_calculation, NEW.transaction_type, NEW.last_transaction_date, NEW.stop_ach_payments, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.billing_summary_history(id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments, action)
            VALUES (OLD.id, OLD.deleted, OLD.computed_date, OLD.last_deposit_date, OLD.first_billing_date, OLD.last_interest_calculation, OLD.transaction_type, OLD.last_transaction_date, OLD.stop_ach_payments, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER billing_summary_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.billing_summary
  FOR EACH ROW EXECUTE PROCEDURE opm.billing_summary_func();
  
-- -----------------------------------------------------
-- Table billing trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.billing_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.billing_history(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, action)
            VALUES (NEW.id, NEW.deleted, NEW.name, NEW.initial_billing, NEW.additional_interest, NEW.total_payments, NEW.balance, NEW.payment_order, NEW.billing_summary_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.billing_history(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, action)
            VALUES (OLD.id, OLD.deleted, OLD.name, OLD.initial_billing, OLD.additional_interest, OLD.total_payments, OLD.balance, OLD.payment_order, OLD.billing_summary_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER billing_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.billing
  FOR EACH ROW EXECUTE PROCEDURE opm.billing_func();
  
-- -----------------------------------------------------
-- Table calculation_version trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.calculation_version_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.calculation_version_history(id, deleted, name, calculation_date, calculation_result_id, version, line_number, action)
            VALUES (NEW.id, NEW.deleted, NEW.name, NEW.calculation_date, NEW.calculation_result_id, NEW.version, NEW.line_number, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.calculation_version_history(id, deleted, name, calculation_date, calculation_result_id, version, line_number, action)
            VALUES (OLD.id, OLD.deleted, OLD.name, OLD.calculation_date, OLD.calculation_result_id, OLD.version, OLD.line_number, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER calculation_version_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.calculation_version
  FOR EACH ROW EXECUTE PROCEDURE opm.calculation_version_func();
  
-- -----------------------------------------------------
-- Table fers_deposit trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.fers_deposit_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.fers_deposit_history(id, account_id, calculation_version_id, action)
            VALUES (NEW.id, NEW.account_id, NEW.calculation_version_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.fers_deposit_history(id, account_id, calculation_version_id, action)
            VALUES (OLD.id, OLD.account_id, OLD.calculation_version_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER fers_deposit_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.fers_deposit
  FOR EACH ROW EXECUTE PROCEDURE opm.fers_deposit_func();
  
-- -----------------------------------------------------
-- Table fers_redeposit trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.fers_redeposit_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.fers_redeposit_history(id, account_id, calculation_version_id, action)
            VALUES (NEW.id, NEW.account_id, NEW.calculation_version_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.fers_redeposit_history(id, account_id, calculation_version_id, action)
            VALUES (OLD.id, OLD.account_id, OLD.calculation_version_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER fers_redeposit_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.fers_redeposit
  FOR EACH ROW EXECUTE PROCEDURE opm.fers_redeposit_func();
  
-- -----------------------------------------------------
-- Table calculation trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.calculation_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.calculation_history(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, agency_code_id, hours_in_year, annualized_amount, date_entered, entered_by, calculation_version_id, action)
            VALUES (NEW.id, NEW.deleted, NEW.begin_date, NEW.end_date, NEW.retirement_type_id, NEW.period_type_id, NEW.appointment_type_id, NEW.service_type_id, NEW.amount, NEW.pay_type_id, NEW.agency_code_id, NEW.hours_in_year, NEW.annualized_amount, NEW.date_entered, NEW.entered_by, NEW.calculation_version_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.calculation_history(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, agency_code_id, hours_in_year, annualized_amount, date_entered, entered_by, calculation_version_id, action)
            VALUES (OLD.id, OLD.deleted, OLD.begin_date, OLD.end_date, OLD.retirement_type_id, OLD.period_type_id, OLD.appointment_type_id, OLD.service_type_id, OLD.amount, OLD.pay_type_id, OLD.agency_code_id, OLD.hours_in_year, OLD.annualized_amount, OLD.date_entered, OLD.entered_by, OLD.calculation_version_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER calculation_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.calculation
  FOR EACH ROW EXECUTE PROCEDURE opm.calculation_func();
  
-- -----------------------------------------------------
-- Table calculation_result trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.calculation_result_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.calculation_result_history(id, deleted, calculation_status_id, official, apply_to_real_payment, summary_data_id, payment_order, interest_accrual_date, action)
            VALUES (NEW.id, NEW.deleted, NEW.calculation_status_id, NEW.official, NEW.apply_to_real_payment, NEW.summary_data_id, NEW.payment_order, NEW.interest_accrual_date, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.calculation_result_history(id, deleted, calculation_status_id, official, apply_to_real_payment, summary_data_id, payment_order, interest_accrual_date, action)
            VALUES (OLD.id, OLD.deleted, OLD.calculation_status_id, OLD.official, OLD.apply_to_real_payment, OLD.summary_data_id, OLD.payment_order, OLD.interest_accrual_date, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER calculation_result_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.calculation_result
  FOR EACH ROW EXECUTE PROCEDURE opm.calculation_result_func();
  
-- -----------------------------------------------------
-- Table calculation_result_item trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.calculation_result_item_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.calculation_result_item_history(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, action)
            VALUES (NEW.id, NEW.deleted, NEW.start_date, NEW.end_date, NEW.mid_date, NEW.effective_date, NEW.period_type_id, NEW.deduction_amount, NEW.total_interest, NEW.payment_applied, NEW.balance, NEW.calculation_result_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.calculation_result_item_history(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, action)
            VALUES (OLD.id, OLD.deleted, OLD.start_date, OLD.end_date, OLD.mid_date, OLD.effective_date, OLD.period_type_id, OLD.deduction_amount, OLD.total_interest, OLD.payment_applied, OLD.balance, OLD.calculation_result_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER calculation_result_item_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.calculation_result_item
  FOR EACH ROW EXECUTE PROCEDURE opm.calculation_result_item_func();
  
-- -----------------------------------------------------
-- Table redeposit trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.redeposit_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.redeposit_history(id, deleted, label, deposit, interest, total, calculation_result_id, action)
            VALUES (NEW.id, NEW.deleted, NEW.label, NEW.deposit, NEW.interest, NEW.total, NEW.calculation_result_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.redeposit_history(id, deleted, label, deposit, interest, total, calculation_result_id, action)
            VALUES (OLD.id, OLD.deleted, OLD.label, OLD.deposit, OLD.interest, OLD.total, OLD.calculation_result_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER redeposit_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.redeposit
  FOR EACH ROW EXECUTE PROCEDURE opm.redeposit_func();
  
-- -----------------------------------------------------
-- Table dedeposit trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.dedeposit_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.dedeposit_history(id, deleted, label, deposit, interest, total, calculation_result_id, action)
            VALUES (NEW.id, NEW.deleted, NEW.label, NEW.deposit, NEW.interest, NEW.total, NEW.calculation_result_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.dedeposit_history(id, deleted, label, deposit, interest, total, calculation_result_id, action)
            VALUES (OLD.id, OLD.deleted, OLD.label, OLD.deposit, OLD.interest, OLD.total, OLD.calculation_result_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER dedeposit_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.dedeposit
  FOR EACH ROW EXECUTE PROCEDURE opm.dedeposit_func();
  
-- -----------------------------------------------------
-- Table summary_data trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.summary_data_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.summary_data_history(id, deleted, total_payments_required, total_initial_interest, total_payments_applied, total_balance, action)
            VALUES (NEW.id, NEW.deleted, NEW.total_payments_required, NEW.total_initial_interest, NEW.total_payments_applied, NEW.total_balance, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.summary_data_history(id, deleted, total_payments_required, total_initial_interest, total_payments_applied, total_balance, action)
            VALUES (OLD.id, OLD.deleted, OLD.total_payments_required, OLD.total_initial_interest, OLD.total_payments_applied, OLD.total_balance, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER summary_data_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.summary_data
  FOR EACH ROW EXECUTE PROCEDURE opm.summary_data_func();
  
-- -----------------------------------------------------
-- Table refund_transaction trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.refund_transaction_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.refund_transaction_history(id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id, action)
            VALUES (NEW.id, NEW.deleted, NEW.transaction_key, NEW.amount, NEW.claim_number, NEW.refund_date, NEW.refund_username, NEW.transfer_type_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.refund_transaction_history(id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id, action)
            VALUES (OLD.id, OLD.deleted, OLD.transaction_key, OLD.amount, OLD.claim_number, OLD.refund_date, OLD.refund_username, OLD.transfer_type_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER refund_transaction_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.refund_transaction
  FOR EACH ROW EXECUTE PROCEDURE opm.refund_transaction_func();
  
-- -----------------------------------------------------
-- Table notification_READBY trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.notification_READBY_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.notification_READBY_history(id, notification_id, value, action)
            VALUES (NEW.id, NEW.notification_id, NEW.value, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.notification_READBY_history(id, notification_id, value, action)
            VALUES (OLD.id, OLD.notification_id, OLD.value, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER notification_READBY_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.notification_READBY
  FOR EACH ROW EXECUTE PROCEDURE opm.notification_READBY_func();
  
-- -----------------------------------------------------
-- Table pay_trans_status_code trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.pay_trans_status_code_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.pay_trans_status_code_history(id, deleted, description, category, display_order, next_state_link, batch_processing_order, final_state, needs_approval, show_on_suspense, include_in_balance, nightly_batch, deletable, reversable, manual_entered, suspense_action, can_hit_gl, reversing_type, balanced_scorecard, send_to_dbts, action)
            VALUES (NEW.id, NEW.deleted, NEW.description, NEW.category, NEW.display_order, NEW.next_state_link, NEW.batch_processing_order, NEW.final_state, NEW.needs_approval, NEW.show_on_suspense, NEW.include_in_balance, NEW.nightly_batch, NEW.deletable, NEW.reversable, NEW.manual_entered, NEW.suspense_action, NEW.can_hit_gl, NEW.reversing_type, NEW.balanced_scorecard, NEW.send_to_dbts, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.pay_trans_status_code_history(id, deleted, description, category, display_order, next_state_link, batch_processing_order, final_state, needs_approval, show_on_suspense, include_in_balance, nightly_batch, deletable, reversable, manual_entered, suspense_action, can_hit_gl, reversing_type, balanced_scorecard, send_to_dbts, action)
            VALUES (OLD.id, OLD.deleted, OLD.description, OLD.category, OLD.display_order, OLD.next_state_link, OLD.batch_processing_order, OLD.final_state, OLD.needs_approval, OLD.show_on_suspense, OLD.include_in_balance, OLD.nightly_batch, OLD.deletable, OLD.reversable, OLD.manual_entered, OLD.suspense_action, OLD.can_hit_gl, OLD.reversing_type, OLD.balanced_scorecard, OLD.send_to_dbts, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER pay_trans_status_code_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.pay_trans_status_code
  FOR EACH ROW EXECUTE PROCEDURE opm.pay_trans_status_code_func();
  
-- -----------------------------------------------------
-- Table payment trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.payment_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.payment_history(id, deleted, batch_number, block_number, sequence_number, claim_number, payment_status_id, account_holder_birthdate, deposit_date, amount, ssn, claimant, claimant_birthday, import_id, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claim_number, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, gov_refund, disapprove, history_payment, resolve_suspense, user_inserted, post_flag, order_code_id, pay_trans_status_code_id, action)
            VALUES (NEW.id, NEW.deleted, NEW.batch_number, NEW.block_number, NEW.sequence_number, NEW.claim_number, NEW.payment_status_id, NEW.account_holder_birthdate, NEW.deposit_date, NEW.amount, NEW.ssn, NEW.claimant, NEW.claimant_birthday, NEW.import_id, NEW.sequence, NEW.transaction_date, NEW.status_date, NEW.apply_designation_id, NEW.apply_to_gl, NEW.note, NEW.transaction_key, NEW.ach, NEW.account_balance, NEW.account_status_id, NEW.master_claim_number, NEW.master_claimant_birthday, NEW.master_account_status_id, NEW.master_account_balance, NEW.master_account_id, NEW.pre_deposit_amount, NEW.pre_redeposit_amount, NEW.post_deposit_amount, NEW.post_redeposit_amount, NEW.approval_user, NEW.approval_status, NEW.payment_type, NEW.account_id, NEW.gov_refund, NEW.disapprove, NEW.history_payment, NEW.resolve_suspense, NEW.user_inserted, NEW.post_flag, NEW.order_code_id, NEW.pay_trans_status_code_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.payment_history(id, deleted, batch_number, block_number, sequence_number, claim_number, payment_status_id, account_holder_birthdate, deposit_date, amount, ssn, claimant, claimant_birthday, import_id, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claim_number, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, gov_refund, disapprove, history_payment, resolve_suspense, user_inserted, post_flag, order_code_id, pay_trans_status_code_id, action)
            VALUES (OLD.id, OLD.deleted, OLD.batch_number, OLD.block_number, OLD.sequence_number, OLD.claim_number, OLD.payment_status_id, OLD.account_holder_birthdate, OLD.deposit_date, OLD.amount, OLD.ssn, OLD.claimant, OLD.claimant_birthday, OLD.import_id, OLD.sequence, OLD.transaction_date, OLD.status_date, OLD.apply_designation_id, OLD.apply_to_gl, OLD.note, OLD.transaction_key, OLD.ach, OLD.account_balance, OLD.account_status_id, OLD.master_claim_number, OLD.master_claimant_birthday, OLD.master_account_status_id, OLD.master_account_balance, OLD.master_account_id, OLD.pre_deposit_amount, OLD.pre_redeposit_amount, OLD.post_deposit_amount, OLD.post_redeposit_amount, OLD.approval_user, OLD.approval_status, OLD.payment_type, OLD.account_id, OLD.gov_refund, OLD.disapprove, OLD.history_payment, OLD.resolve_suspense, OLD.user_inserted, OLD.post_flag, OLD.order_code_id, OLD.pay_trans_status_code_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER payment_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.payment
  FOR EACH ROW EXECUTE PROCEDURE opm.payment_func();
  
-- -----------------------------------------------------
-- Table payment_reverse trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.payment_reverse_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.payment_reverse_history(id, deleted, payment_id, payment_reversal_reason_id, apply_to_gl, reverser, action)
            VALUES (NEW.id, NEW.deleted, NEW.payment_id, NEW.payment_reversal_reason_id, NEW.apply_to_gl, NEW.reverser, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.payment_reverse_history(id, deleted, payment_id, payment_reversal_reason_id, apply_to_gl, reverser, action)
            VALUES (OLD.id, OLD.deleted, OLD.payment_id, OLD.payment_reversal_reason_id, OLD.apply_to_gl, OLD.reverser, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER payment_reverse_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.payment_reverse
  FOR EACH ROW EXECUTE PROCEDURE opm.payment_reverse_func();
  
-- -----------------------------------------------------
-- Table account_confirmation_validation trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.account_confirmation_validation_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.account_confirmation_validation_history(id, deleted, account_id, data_check_status, data_check_status_validator, data_check_status_reason, action)
            VALUES (NEW.id, NEW.deleted, NEW.account_id, NEW.data_check_status, NEW.data_check_status_validator, NEW.data_check_status_reason, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.account_confirmation_validation_history(id, deleted, account_id, data_check_status, data_check_status_validator, data_check_status_reason, action)
            VALUES (OLD.id, OLD.deleted, OLD.account_id, OLD.data_check_status, OLD.data_check_status_validator, OLD.data_check_status_reason, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER account_confirmation_validation_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.account_confirmation_validation
  FOR EACH ROW EXECUTE PROCEDURE opm.account_confirmation_validation_func();
  
-- -----------------------------------------------------
-- Table account_confirmation_validation_entry trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.account_confirmation_validation_entry_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.account_confirmation_validation_entry_history(id, deleted, field_name, valid, account_confirmation_validation_id, action)
            VALUES (NEW.id, NEW.deleted, NEW.field_name, NEW.valid, NEW.account_confirmation_validation_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.account_confirmation_validation_entry_history(id, deleted, field_name, valid, account_confirmation_validation_id, action)
            VALUES (OLD.id, OLD.deleted, OLD.field_name, OLD.valid, OLD.account_confirmation_validation_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER account_confirmation_validation_entry_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.account_confirmation_validation_entry
  FOR EACH ROW EXECUTE PROCEDURE opm.account_confirmation_validation_entry_func();
  
-- -----------------------------------------------------
-- Table service_credit_preference trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.service_credit_preference_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.service_credit_preference_history(id, deleted, use_agents, use_status_bar, use_message_box, other, action)
            VALUES (NEW.id, NEW.deleted, NEW.use_agents, NEW.use_status_bar, NEW.use_message_box, NEW.other, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.service_credit_preference_history(id, deleted, use_agents, use_status_bar, use_message_box, other, action)
            VALUES (OLD.id, OLD.deleted, OLD.use_agents, OLD.use_status_bar, OLD.use_message_box, OLD.other, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER service_credit_preference_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.service_credit_preference
  FOR EACH ROW EXECUTE PROCEDURE opm.service_credit_preference_func();
  
-- -----------------------------------------------------
-- Table payments_applied_order_code trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.payments_applied_order_code_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.payments_applied_order_code_history(id, deleted, payment_account, display_order, action)
            VALUES (NEW.id, NEW.deleted, NEW.payment_account, NEW.display_order, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.payments_applied_order_code_history(id, deleted, payment_account, display_order, action)
            VALUES (OLD.id, OLD.deleted, OLD.payment_account, OLD.display_order, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER payments_applied_order_code_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.payments_applied_order_code
  FOR EACH ROW EXECUTE PROCEDURE opm.payments_applied_order_code_func();
  
-- -----------------------------------------------------
-- Table payment_refund_link trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.payment_refund_link_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.payment_refund_link_history(id, deleted, payment_needing_refund, refund_for_payment, action)
            VALUES (NEW.id, NEW.deleted, NEW.payment_needing_refund, NEW.refund_for_payment, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.payment_refund_link_history(id, deleted, payment_needing_refund, refund_for_payment, action)
            VALUES (OLD.id, OLD.deleted, OLD.payment_needing_refund, OLD.refund_for_payment, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER payment_refund_link_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.payment_refund_link
  FOR EACH ROW EXECUTE PROCEDURE opm.payment_refund_link_func();
  
-- -----------------------------------------------------
-- Table user_account_assignment trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.user_account_assignment_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.user_account_assignment_history(id, deleted, user_id, account_id, assignment_date, action)
            VALUES (NEW.id, NEW.deleted, NEW.user_id, NEW.account_id, NEW.assignment_date, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.user_account_assignment_history(id, deleted, user_id, account_id, assignment_date, action)
            VALUES (OLD.id, OLD.deleted, OLD.user_id, OLD.account_id, OLD.assignment_date, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER user_account_assignment_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.user_account_assignment
  FOR EACH ROW EXECUTE PROCEDURE opm.user_account_assignment_func();
  
-- -----------------------------------------------------
-- Table a01_print_suppression_case trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.a01_print_suppression_case_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.a01_print_suppression_case_history(id, deleted, claim_number, reason_for_print_suppression, action)
            VALUES (NEW.id, NEW.deleted, NEW.claim_number, NEW.reason_for_print_suppression, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.a01_print_suppression_case_history(id, deleted, claim_number, reason_for_print_suppression, action)
            VALUES (OLD.id, OLD.deleted, OLD.claim_number, OLD.reason_for_print_suppression, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER a01_print_suppression_case_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.a01_print_suppression_case
  FOR EACH ROW EXECUTE PROCEDURE opm.a01_print_suppression_case_func();
  
-- -----------------------------------------------------
-- Table invoice trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.invoice_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.invoice_history(id, deleted, pay_transaction_key, deposit, redeposit, tot_var_redeposit, non_ded, fers_w, acc_int_dep, acc_int_rdep, acc_int_non_dep, acc_int_var_rdep, acc_int_fers, tot_pay_d, tot_pay_r, tot_pay_n, tot_pay_vr, tot_pay_fers, last_pay, calc_date, last_invoice_id, action)
            VALUES (NEW.id, NEW.deleted, NEW.pay_transaction_key, NEW.deposit, NEW.redeposit, NEW.tot_var_redeposit, NEW.non_ded, NEW.fers_w, NEW.acc_int_dep, NEW.acc_int_rdep, NEW.acc_int_non_dep, NEW.acc_int_var_rdep, NEW.acc_int_fers, NEW.tot_pay_d, NEW.tot_pay_r, NEW.tot_pay_n, NEW.tot_pay_vr, NEW.tot_pay_fers, NEW.last_pay, NEW.calc_date, NEW.last_invoice_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.invoice_history(id, deleted, pay_transaction_key, deposit, redeposit, tot_var_redeposit, non_ded, fers_w, acc_int_dep, acc_int_rdep, acc_int_non_dep, acc_int_var_rdep, acc_int_fers, tot_pay_d, tot_pay_r, tot_pay_n, tot_pay_vr, tot_pay_fers, last_pay, calc_date, last_invoice_id, action)
            VALUES (OLD.id, OLD.deleted, OLD.pay_transaction_key, OLD.deposit, OLD.redeposit, OLD.tot_var_redeposit, OLD.non_ded, OLD.fers_w, OLD.acc_int_dep, OLD.acc_int_rdep, OLD.acc_int_non_dep, OLD.acc_int_var_rdep, OLD.acc_int_fers, OLD.tot_pay_d, OLD.tot_pay_r, OLD.tot_pay_n, OLD.tot_pay_vr, OLD.tot_pay_fers, OLD.last_pay, OLD.calc_date, OLD.last_invoice_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER invoice_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.invoice
  FOR EACH ROW EXECUTE PROCEDURE opm.invoice_func();
  
-- -----------------------------------------------------
-- Table adjustment_transaction trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.adjustment_transaction_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.adjustment_transaction_history(id, deleted, pay_transaction_key, claim_number, acc_int_dep, acc_int_rdep, acc_int_non_dep, acc_int_var_rdep, acc_int_fers, modification_date, approved_date, processed_date, technician_user_key, manager_user_key, approved, disapproved, modified, note, action)
            VALUES (NEW.id, NEW.deleted, NEW.pay_transaction_key, NEW.claim_number, NEW.acc_int_dep, NEW.acc_int_rdep, NEW.acc_int_non_dep, NEW.acc_int_var_rdep, NEW.acc_int_fers, NEW.modification_date, NEW.approved_date, NEW.processed_date, NEW.technician_user_key, NEW.manager_user_key, NEW.approved, NEW.disapproved, NEW.modified, NEW.note, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.adjustment_transaction_history(id, deleted, pay_transaction_key, claim_number, acc_int_dep, acc_int_rdep, acc_int_non_dep, acc_int_var_rdep, acc_int_fers, modification_date, approved_date, processed_date, technician_user_key, manager_user_key, approved, disapproved, modified, note, action)
            VALUES (OLD.id, OLD.deleted, OLD.pay_transaction_key, OLD.claim_number, OLD.acc_int_dep, OLD.acc_int_rdep, OLD.acc_int_non_dep, OLD.acc_int_var_rdep, OLD.acc_int_fers, OLD.modification_date, OLD.approved_date, OLD.processed_date, OLD.technician_user_key, OLD.manager_user_key, OLD.approved, OLD.disapproved, OLD.modified, OLD.note, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER adjustment_transaction_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.adjustment_transaction
  FOR EACH ROW EXECUTE PROCEDURE opm.adjustment_transaction_func();
  
-- -----------------------------------------------------
-- Table payment_interest_detail trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.payment_interest_detail_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.payment_interest_detail_history(id, deleted, pay_transaction_key, account_type, num_whole_years, calculated_interest, last_pay_to_eoy_factor, partial_to_this_factor, this_interest_rate, last_payment_date, transaction_date, computed_date, post, gui, last_payment_was_this_year, action)
            VALUES (NEW.id, NEW.deleted, NEW.pay_transaction_key, NEW.account_type, NEW.num_whole_years, NEW.calculated_interest, NEW.last_pay_to_eoy_factor, NEW.partial_to_this_factor, NEW.this_interest_rate, NEW.last_payment_date, NEW.transaction_date, NEW.computed_date, NEW.post, NEW.gui, NEW.last_payment_was_this_year, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.payment_interest_detail_history(id, deleted, pay_transaction_key, account_type, num_whole_years, calculated_interest, last_pay_to_eoy_factor, partial_to_this_factor, this_interest_rate, last_payment_date, transaction_date, computed_date, post, gui, last_payment_was_this_year, action)
            VALUES (OLD.id, OLD.deleted, OLD.pay_transaction_key, OLD.account_type, OLD.num_whole_years, OLD.calculated_interest, OLD.last_pay_to_eoy_factor, OLD.partial_to_this_factor, OLD.this_interest_rate, OLD.last_payment_date, OLD.transaction_date, OLD.computed_date, OLD.post, OLD.gui, OLD.last_payment_was_this_year, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER payment_interest_detail_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.payment_interest_detail
  FOR EACH ROW EXECUTE PROCEDURE opm.payment_interest_detail_func();
  
-- -----------------------------------------------------
-- Table interest_rate trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.interest_rate_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.interest_rate_history(id, deleted, interest_year, interest_rate, action)
            VALUES (NEW.id, NEW.deleted, NEW.interest_year, NEW.interest_rate, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.interest_rate_history(id, deleted, interest_year, interest_rate, action)
            VALUES (OLD.id, OLD.deleted, OLD.interest_year, OLD.interest_rate, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER interest_rate_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.interest_rate
  FOR EACH ROW EXECUTE PROCEDURE opm.interest_rate_func();
  
-- -----------------------------------------------------
-- Table payment_move_transaction trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.payment_move_transaction_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.payment_move_transaction_history(id, deleted, pay_transaction_key, claim_number, tot_pay_d, tot_pay_r, tot_pay_n, tot_pay_vr, tot_pay_fers, modification_date, approved_date, processed_date, technician_user_key, manager_user_key, approved, disapproved, modified, note, action)
            VALUES (NEW.id, NEW.deleted, NEW.pay_transaction_key, NEW.claim_number, NEW.tot_pay_d, NEW.tot_pay_r, NEW.tot_pay_n, NEW.tot_pay_vr, NEW.tot_pay_fers, NEW.modification_date, NEW.approved_date, NEW.processed_date, NEW.technician_user_key, NEW.manager_user_key, NEW.approved, NEW.disapproved, NEW.modified, NEW.note, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.payment_move_transaction_history(id, deleted, pay_transaction_key, claim_number, tot_pay_d, tot_pay_r, tot_pay_n, tot_pay_vr, tot_pay_fers, modification_date, approved_date, processed_date, technician_user_key, manager_user_key, approved, disapproved, modified, note, action)
            VALUES (OLD.id, OLD.deleted, OLD.pay_transaction_key, OLD.claim_number, OLD.tot_pay_d, OLD.tot_pay_r, OLD.tot_pay_n, OLD.tot_pay_vr, OLD.tot_pay_fers, OLD.modification_date, OLD.approved_date, OLD.processed_date, OLD.technician_user_key, OLD.manager_user_key, OLD.approved, OLD.disapproved, OLD.modified, OLD.note, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER payment_move_transaction_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.payment_move_transaction
  FOR EACH ROW EXECUTE PROCEDURE opm.payment_move_transaction_func();
  
-- -----------------------------------------------------
-- Table payment_transaction_note trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.payment_transaction_note_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.payment_transaction_note_history(id, deleted, pay_transaction_key, note, action)
            VALUES (NEW.id, NEW.deleted, NEW.pay_transaction_key, NEW.note, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.payment_transaction_note_history(id, deleted, pay_transaction_key, note, action)
            VALUES (OLD.id, OLD.deleted, OLD.pay_transaction_key, OLD.note, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER payment_transaction_note_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.payment_transaction_note
  FOR EACH ROW EXECUTE PROCEDURE opm.payment_transaction_note_func();
  
-- -----------------------------------------------------
-- Table claim_without_service trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.claim_without_service_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.claim_without_service_history(id, deleted, claim_number, date_of_birth, action)
            VALUES (NEW.id, NEW.deleted, NEW.claim_number, NEW.date_of_birth, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.claim_without_service_history(id, deleted, claim_number, date_of_birth, action)
            VALUES (OLD.id, OLD.deleted, OLD.claim_number, OLD.date_of_birth, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER claim_without_service_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.claim_without_service
  FOR EACH ROW EXECUTE PROCEDURE opm.claim_without_service_func();
  
-- -----------------------------------------------------
-- Table gl_code trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.gl_code_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.gl_code_history(id, deleted, name, code, payment_type, retirement_type_id, post_office, action)
            VALUES (NEW.id, NEW.deleted, NEW.name, NEW.code, NEW.payment_type, NEW.retirement_type_id, NEW.post_office, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.gl_code_history(id, deleted, name, code, payment_type, retirement_type_id, post_office, action)
            VALUES (OLD.id, OLD.deleted, OLD.name, OLD.code, OLD.payment_type, OLD.retirement_type_id, OLD.post_office, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER gl_code_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.gl_code
  FOR EACH ROW EXECUTE PROCEDURE opm.gl_code_func();
  
-- -----------------------------------------------------
-- Table interest_grace_period trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.interest_grace_period_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.interest_grace_period_history(id, deleted, claim_number, post_982_redeposit, pre_1082_redeposit, post_982_deposit, pre_1082_deposit, fers_deposit, action)
            VALUES (NEW.id, NEW.deleted, NEW.claim_number, NEW.post_982_redeposit, NEW.pre_1082_redeposit, NEW.post_982_deposit, NEW.pre_1082_deposit, NEW.fers_deposit, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.interest_grace_period_history(id, deleted, claim_number, post_982_redeposit, pre_1082_redeposit, post_982_deposit, pre_1082_deposit, fers_deposit, action)
            VALUES (OLD.id, OLD.deleted, OLD.claim_number, OLD.post_982_redeposit, OLD.pre_1082_redeposit, OLD.post_982_deposit, OLD.pre_1082_deposit, OLD.fers_deposit, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER interest_grace_period_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.interest_grace_period
  FOR EACH ROW EXECUTE PROCEDURE opm.interest_grace_period_func();
  
-- -----------------------------------------------------
-- Table deduction_rate trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.deduction_rate_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.deduction_rate_history(id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor, action)
            VALUES (NEW.id, NEW.deleted, NEW.service_type, NEW.retirement_type_id, NEW.start_date, NEW.end_date, NEW.days_in_period, NEW.rate, NEW.service_type_description, NEW.deduction_conversion_factor, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.deduction_rate_history(id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor, action)
            VALUES (OLD.id, OLD.deleted, OLD.service_type, OLD.retirement_type_id, OLD.start_date, OLD.end_date, OLD.days_in_period, OLD.rate, OLD.service_type_description, OLD.deduction_conversion_factor, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER deduction_rate_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.deduction_rate
  FOR EACH ROW EXECUTE PROCEDURE opm.deduction_rate_func();
  
-- -----------------------------------------------------
-- Table holiday trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.holiday_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.holiday_history(id, deleted, holiday, exact_date, week_day, month_number, day_of_month, week_of_month, holiday_id, action)
            VALUES (NEW.id, NEW.deleted, NEW.holiday, NEW.exact_date, NEW.week_day, NEW.month_number, NEW.day_of_month, NEW.week_of_month, NEW.holiday_id, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.holiday_history(id, deleted, holiday, exact_date, week_day, month_number, day_of_month, week_of_month, holiday_id, action)
            VALUES (OLD.id, OLD.deleted, OLD.holiday, OLD.exact_date, OLD.week_day, OLD.month_number, OLD.day_of_month, OLD.week_of_month, OLD.holiday_id, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER holiday_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.holiday
  FOR EACH ROW EXECUTE PROCEDURE opm.holiday_func();
  
-- -----------------------------------------------------
-- Table scm_first_insert trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.scm_first_insert_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.scm_first_insert_history(id, deleted, claim_number, last_action, action)
            VALUES (NEW.id, NEW.deleted, NEW.claim_number, NEW.last_action, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.scm_first_insert_history(id, deleted, claim_number, last_action, action)
            VALUES (OLD.id, OLD.deleted, OLD.claim_number, OLD.last_action, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER scm_first_insert_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.scm_first_insert
  FOR EACH ROW EXECUTE PROCEDURE opm.scm_first_insert_func();
  
-- -----------------------------------------------------
-- Table gl_payment_type trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.gl_payment_type_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.gl_payment_type_history(id, deleted, payment_code, code_description, action)
            VALUES (NEW.id, NEW.deleted, NEW.payment_code, NEW.code_description, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.gl_payment_type_history(id, deleted, payment_code, code_description, action)
            VALUES (OLD.id, OLD.deleted, OLD.payment_code, OLD.code_description, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER gl_payment_type_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.gl_payment_type
  FOR EACH ROW EXECUTE PROCEDURE opm.gl_payment_type_func();
  
-- -----------------------------------------------------
-- Table interest_suppression trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.interest_suppression_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.interest_suppression_history(id, deleted, claim_number, post_982_redeposit, pre_1082_redeposit, post_982_deposit, pre_1082_deposit, fers_deposit, action)
            VALUES (NEW.id, NEW.deleted, NEW.claim_number, NEW.post_982_redeposit, NEW.pre_1082_redeposit, NEW.post_982_deposit, NEW.pre_1082_deposit, NEW.fers_deposit, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.interest_suppression_history(id, deleted, claim_number, post_982_redeposit, pre_1082_redeposit, post_982_deposit, pre_1082_deposit, fers_deposit, action)
            VALUES (OLD.id, OLD.deleted, OLD.claim_number, OLD.post_982_redeposit, OLD.pre_1082_redeposit, OLD.post_982_deposit, OLD.pre_1082_deposit, OLD.fers_deposit, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER interest_suppression_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.interest_suppression
  FOR EACH ROW EXECUTE PROCEDURE opm.interest_suppression_func();
  
-- -----------------------------------------------------
-- Table contact_info trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.contact_info_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.contact_info_history(id, deleted, name, text, action)
            VALUES (NEW.id, NEW.deleted, NEW.name, NEW.text, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.contact_info_history(id, deleted, name, text, action)
            VALUES (OLD.id, OLD.deleted, OLD.name, OLD.text, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER contact_info_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.contact_info
  FOR EACH ROW EXECUTE PROCEDURE opm.contact_info_func();
  
-- -----------------------------------------------------
-- Table time_factor trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.time_factor_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.time_factor_history(id, deleted, num_days, num_months, time_factor, action)
            VALUES (NEW.id, NEW.deleted, NEW.num_days, NEW.num_months, NEW.time_factor, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.time_factor_history(id, deleted, num_days, num_months, time_factor, action)
            VALUES (OLD.id, OLD.deleted, OLD.num_days, OLD.num_months, OLD.time_factor, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER time_factor_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.time_factor
  FOR EACH ROW EXECUTE PROCEDURE opm.time_factor_func();
  
-- -----------------------------------------------------
-- Table annuitant_list trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.annuitant_list_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.annuitant_list_history(id, deleted, claim_number, action)
            VALUES (NEW.id, NEW.deleted, NEW.claim_number, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.annuitant_list_history(id, deleted, claim_number, action)
            VALUES (OLD.id, OLD.deleted, OLD.claim_number, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER annuitant_list_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.annuitant_list
  FOR EACH ROW EXECUTE PROCEDURE opm.annuitant_list_func();
  
-- -----------------------------------------------------
-- Table new_claim_number trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.new_claim_number_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.new_claim_number_history(id, deleted, claim_number, action)
            VALUES (NEW.id, NEW.deleted, NEW.claim_number, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.new_claim_number_history(id, deleted, claim_number, action)
            VALUES (OLD.id, OLD.deleted, OLD.claim_number, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER new_claim_number_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.new_claim_number
  FOR EACH ROW EXECUTE PROCEDURE opm.new_claim_number_func();
  
-- -----------------------------------------------------
-- Table audit_batch trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.audit_batch_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.audit_batch_history(id, deleted, event_year, event_month, event_day, file_received, daily_action, manual_batch, error_importing, error_processing, latest_batch, amount_imported, amount_processed, number_accepted, number_unresolved, number_suspended, number_ach_accepted, number_ach_unresolved, number_ach_suspended, number_change_requests, payments_processed, initial_bills_processed, reversed_processed, ach_stop_letters, refund_memos, error_count_processing, user_key, batch_time, action)
            VALUES (NEW.id, NEW.deleted, NEW.event_year, NEW.event_month, NEW.event_day, NEW.file_received, NEW.daily_action, NEW.manual_batch, NEW.error_importing, NEW.error_processing, NEW.latest_batch, NEW.amount_imported, NEW.amount_processed, NEW.number_accepted, NEW.number_unresolved, NEW.number_suspended, NEW.number_ach_accepted, NEW.number_ach_unresolved, NEW.number_ach_suspended, NEW.number_change_requests, NEW.payments_processed, NEW.initial_bills_processed, NEW.reversed_processed, NEW.ach_stop_letters, NEW.refund_memos, NEW.error_count_processing, NEW.user_key, NEW.batch_time, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.audit_batch_history(id, deleted, event_year, event_month, event_day, file_received, daily_action, manual_batch, error_importing, error_processing, latest_batch, amount_imported, amount_processed, number_accepted, number_unresolved, number_suspended, number_ach_accepted, number_ach_unresolved, number_ach_suspended, number_change_requests, payments_processed, initial_bills_processed, reversed_processed, ach_stop_letters, refund_memos, error_count_processing, user_key, batch_time, action)
            VALUES (OLD.id, OLD.deleted, OLD.event_year, OLD.event_month, OLD.event_day, OLD.file_received, OLD.daily_action, OLD.manual_batch, OLD.error_importing, OLD.error_processing, OLD.latest_batch, OLD.amount_imported, OLD.amount_processed, OLD.number_accepted, OLD.number_unresolved, OLD.number_suspended, OLD.number_ach_accepted, OLD.number_ach_unresolved, OLD.number_ach_suspended, OLD.number_change_requests, OLD.payments_processed, OLD.initial_bills_processed, OLD.reversed_processed, OLD.ach_stop_letters, OLD.refund_memos, OLD.error_count_processing, OLD.user_key, OLD.batch_time, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER audit_batch_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.audit_batch
  FOR EACH ROW EXECUTE PROCEDURE opm.audit_batch_func();
  
-- -----------------------------------------------------
-- Table batch_daily_payments trigger
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION opm.batch_daily_payments_func() RETURNS TRIGGER AS $$
    BEGIN
    	  IF (TG_OP = 'UPDATE') OR (TG_OP = 'INSERT') THEN
            INSERT INTO opm.batch_daily_payments_history(id, deleted, audit_batch_log_id, pay_transaction_key, number_payment_today, batch_time, account_status_id, pay_trans_status_code, claim_number, account_balance, over_payment_amount, ach_payment, ach_stop_letter, print_invoice, refund_required, reversed_payment, update_to_completed, print_initial_bill, latest_batch, error_processing, action)
            VALUES (NEW.id, NEW.deleted, NEW.audit_batch_log_id, NEW.pay_transaction_key, NEW.number_payment_today, NEW.batch_time, NEW.account_status_id, NEW.pay_trans_status_code, NEW.claim_number, NEW.account_balance, NEW.over_payment_amount, NEW.ach_payment, NEW.ach_stop_letter, NEW.print_invoice, NEW.refund_required, NEW.reversed_payment, NEW.update_to_completed, NEW.print_initial_bill, NEW.latest_batch, NEW.error_processing, substring(TG_OP,1,1));
            RETURN NEW;
        ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO opm.batch_daily_payments_history(id, deleted, audit_batch_log_id, pay_transaction_key, number_payment_today, batch_time, account_status_id, pay_trans_status_code, claim_number, account_balance, over_payment_amount, ach_payment, ach_stop_letter, print_invoice, refund_required, reversed_payment, update_to_completed, print_initial_bill, latest_batch, error_processing, action)
            VALUES (OLD.id, OLD.deleted, OLD.audit_batch_log_id, OLD.pay_transaction_key, OLD.number_payment_today, OLD.batch_time, OLD.account_status_id, OLD.pay_trans_status_code, OLD.claim_number, OLD.account_balance, OLD.over_payment_amount, OLD.ach_payment, OLD.ach_stop_letter, OLD.print_invoice, OLD.refund_required, OLD.reversed_payment, OLD.update_to_completed, OLD.print_initial_bill, OLD.latest_batch, OLD.error_processing, substring(TG_OP,1,1));
            RETURN OLD;
        END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER batch_daily_payments_trigger 
  AFTER INSERT OR UPDATE OR DELETE ON opm.batch_daily_payments
  FOR EACH ROW EXECUTE PROCEDURE opm.batch_daily_payments_func();