INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(1, false, 'jholmes Jan 5 2013 12:00PM', '2013-07-06', 1, 1);
INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(2, false, 'harmstrong Jan 5 2013 12:00PM', '2013-10-24', 2, 1);
INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(3, false, 'dmorrison Jan 5 2013 12:00PM', '2013-11-27', 3, 1);
INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(4, false, 'rfreeman Jan 5 2013 12:00PM', '2012-12-23', 4, 1);
ALTER SEQUENCE opm.calculation_version_id_seq RESTART WITH 5;