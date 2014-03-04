INSERT INTO opm.calculation_result(id, deleted, calculation_status_id, official, apply_to_real_payment, summary_data_id) VALUES(1, false, 1, true, false, 1);
INSERT INTO opm.calculation_result(id, deleted, calculation_status_id, official, apply_to_real_payment, summary_data_id) VALUES(2, false, 5, true, false, 2);
INSERT INTO opm.calculation_result(id, deleted, calculation_status_id, official, apply_to_real_payment, summary_data_id) VALUES(3, false, 3, false, true, 3);
INSERT INTO opm.calculation_result(id, deleted, calculation_status_id, official, apply_to_real_payment, summary_data_id) VALUES(4, false, 5, false, true, 4);
ALTER SEQUENCE opm.calculation_result_id_seq RESTART WITH 5;