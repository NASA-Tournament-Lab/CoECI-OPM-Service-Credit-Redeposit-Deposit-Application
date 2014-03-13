set datestyle='US';

----------------------------------------------------
-- 			pay_trans_status_code table
----------------------------------------------------
INSERT INTO opm.pay_trans_status_code (id, deleted, description, category, display_order, next_state_link, batch_processing_order, final_state, needs_approval, show_on_suspense, include_in_balance, nightly_batch, deletable, reversable, manual_entered, suspense_action, can_hit_gl, reversing_type, balanced_scorecard, send_to_dbts)
VALUES (1, false, 'code', 'category', 1, 1, 1, true, true, true, true, true, true, true, true, true, true, true, true, true);

----------------------------------------------------
-- 			payments_applied_order_code table
----------------------------------------------------
INSERT INTO opm.payments_applied_order_code (id, deleted, payment_account, display_order) VALUES (1, false, '123', 1);

----------------------------------------------------
-- 			pay_code table
----------------------------------------------------
INSERT INTO opm.pay_code (id, deleted, name) VALUES (1, false, '1');
INSERT INTO opm.pay_code (id, deleted, name) VALUES (2, false, '1');
INSERT INTO opm.pay_code (id, deleted, name) VALUES (3, false, '1');

----------------------------------------------------
-- 			account_status table
----------------------------------------------------
INSERT INTO opm.account_status(id, deleted, name) VALUES(1, false, 'Active');
INSERT INTO opm.account_status(id, deleted, name) VALUES(2, false, 'Suspended');
INSERT INTO opm.account_status(id, deleted, name) VALUES(3, false, 'Closed');
INSERT INTO opm.account_status(id, deleted, name) VALUES(4, false, 'History');
INSERT INTO opm.account_status(id, deleted, name) VALUES(5, false, 'New');

----------------------------------------------------
-- 			country table
----------------------------------------------------
INSERT INTO opm.country(id, deleted, name) VALUES (1, false,'Afghanistan');
INSERT INTO opm.country(id, deleted, name) VALUES (2, false,'Albania');
INSERT INTO opm.country(id, deleted, name) VALUES (3, false,'Algeria');
INSERT INTO opm.country(id, deleted, name) VALUES (4, false,'Andorra');
INSERT INTO opm.country(id, deleted, name) VALUES (5, false,'Angola');
INSERT INTO opm.country(id, deleted, name) VALUES (6, false,'Antigua and Barbuda');
INSERT INTO opm.country(id, deleted, name) VALUES (7, false,'Argentina');
INSERT INTO opm.country(id, deleted, name) VALUES (8, false,'Armenia');
INSERT INTO opm.country(id, deleted, name) VALUES (9, false,'Aruba');
INSERT INTO opm.country(id, deleted, name) VALUES (10, false,'Australia');
INSERT INTO opm.country(id, deleted, name) VALUES (11, false,'Austria');
INSERT INTO opm.country(id, deleted, name) VALUES (12, false,'Azerbaijan');
INSERT INTO opm.country(id, deleted, name) VALUES (13, false,'Bahamas, The');
INSERT INTO opm.country(id, deleted, name) VALUES (14, false,'Bahrain');
INSERT INTO opm.country(id, deleted, name) VALUES (15, false,'Bangladesh');
INSERT INTO opm.country(id, deleted, name) VALUES (16, false,'Barbados');
INSERT INTO opm.country(id, deleted, name) VALUES (17, false,'Belarus');
INSERT INTO opm.country(id, deleted, name) VALUES (18, false,'Belgium');
INSERT INTO opm.country(id, deleted, name) VALUES (19, false,'Belize');
INSERT INTO opm.country(id, deleted, name) VALUES (20, false,'Benin');
INSERT INTO opm.country(id, deleted, name) VALUES (21, false,'Bhutan');
INSERT INTO opm.country(id, deleted, name) VALUES (22, false,'Bolivia');
INSERT INTO opm.country(id, deleted, name) VALUES (23, false,'Bosnia and Herzegovina');
INSERT INTO opm.country(id, deleted, name) VALUES (24, false,'Botswana');
INSERT INTO opm.country(id, deleted, name) VALUES (25, false,'Brazil');
INSERT INTO opm.country(id, deleted, name) VALUES (26, false,'Brunei ');
INSERT INTO opm.country(id, deleted, name) VALUES (27, false,'Bulgaria');
INSERT INTO opm.country(id, deleted, name) VALUES (28, false,'Burkina Faso');
INSERT INTO opm.country(id, deleted, name) VALUES (29, false,'Burma');
INSERT INTO opm.country(id, deleted, name) VALUES (30, false,'Burundi');
INSERT INTO opm.country(id, deleted, name) VALUES (31, false,'Cambodia');
INSERT INTO opm.country(id, deleted, name) VALUES (32, false,'Cameroon');
INSERT INTO opm.country(id, deleted, name) VALUES (33, false,'Canada');
INSERT INTO opm.country(id, deleted, name) VALUES (34, false,'Cape Verde');
INSERT INTO opm.country(id, deleted, name) VALUES (35, false,'Central African Republic');
INSERT INTO opm.country(id, deleted, name) VALUES (36, false,'Chad');
INSERT INTO opm.country(id, deleted, name) VALUES (37, false,'Chile');
INSERT INTO opm.country(id, deleted, name) VALUES (38, false,'China');
INSERT INTO opm.country(id, deleted, name) VALUES (39, false,'Colombia');
INSERT INTO opm.country(id, deleted, name) VALUES (40, false,'Comoros');
INSERT INTO opm.country(id, deleted, name) VALUES (41, false,'Congo, Democratic Republic of the');
INSERT INTO opm.country(id, deleted, name) VALUES (42, false,'Costa Rica');
INSERT INTO opm.country(id, deleted, name) VALUES (43, false,'Cote d''Ivoire');
INSERT INTO opm.country(id, deleted, name) VALUES (44, false,'Croatia');
INSERT INTO opm.country(id, deleted, name) VALUES (45, false,'Cuba');
INSERT INTO opm.country(id, deleted, name) VALUES (46, false,'Curacao');
INSERT INTO opm.country(id, deleted, name) VALUES (47, false,'Cyprus');
INSERT INTO opm.country(id, deleted, name) VALUES (48, false,'Czech Republic');
INSERT INTO opm.country(id, deleted, name) VALUES (49, false,'Denmark');
INSERT INTO opm.country(id, deleted, name) VALUES (50, false,'Djibouti');
INSERT INTO opm.country(id, deleted, name) VALUES (51, false,'Dominica');
INSERT INTO opm.country(id, deleted, name) VALUES (52, false,'Dominican Republic');
INSERT INTO opm.country(id, deleted, name) VALUES (53, false,'East Timor (see Timor-Leste)');
INSERT INTO opm.country(id, deleted, name) VALUES (54, false,'Ecuador');
INSERT INTO opm.country(id, deleted, name) VALUES (55, false,'Egypt');
INSERT INTO opm.country(id, deleted, name) VALUES (56, false,'El Salvador');
INSERT INTO opm.country(id, deleted, name) VALUES (57, false,'Equatorial Guinea');
INSERT INTO opm.country(id, deleted, name) VALUES (58, false,'Eritrea');
INSERT INTO opm.country(id, deleted, name) VALUES (59, false,'Estonia');
INSERT INTO opm.country(id, deleted, name) VALUES (60, false,'Ethiopia');
INSERT INTO opm.country(id, deleted, name) VALUES (61, false,'Fiji');
INSERT INTO opm.country(id, deleted, name) VALUES (62, false,'Finland');
INSERT INTO opm.country(id, deleted, name) VALUES (63, false,'France');
INSERT INTO opm.country(id, deleted, name) VALUES (64, false,'Gabon');
INSERT INTO opm.country(id, deleted, name) VALUES (65, false,'Gambia, The');
INSERT INTO opm.country(id, deleted, name) VALUES (66, false,'Georgia');
INSERT INTO opm.country(id, deleted, name) VALUES (67, false,'Germany');
INSERT INTO opm.country(id, deleted, name) VALUES (68, false,'Ghana');
INSERT INTO opm.country(id, deleted, name) VALUES (69, false,'Greece');
INSERT INTO opm.country(id, deleted, name) VALUES (70, false,'Grenada');
INSERT INTO opm.country(id, deleted, name) VALUES (71, false,'Guatemala');
INSERT INTO opm.country(id, deleted, name) VALUES (72, false,'Guinea');
INSERT INTO opm.country(id, deleted, name) VALUES (73, false,'Guinea-Bissau');
INSERT INTO opm.country(id, deleted, name) VALUES (74, false,'Guyana');
INSERT INTO opm.country(id, deleted, name) VALUES (75, false,'Haiti');
INSERT INTO opm.country(id, deleted, name) VALUES (76, false,'Holy See');
INSERT INTO opm.country(id, deleted, name) VALUES (77, false,'Honduras');
INSERT INTO opm.country(id, deleted, name) VALUES (78, false,'Hong Kong');
INSERT INTO opm.country(id, deleted, name) VALUES (79, false,'Hungary');
INSERT INTO opm.country(id, deleted, name) VALUES (80, false,'Iceland');
INSERT INTO opm.country(id, deleted, name) VALUES (81, false,'India');
INSERT INTO opm.country(id, deleted, name) VALUES (82, false,'Indonesia');
INSERT INTO opm.country(id, deleted, name) VALUES (83, false,'Iran');
INSERT INTO opm.country(id, deleted, name) VALUES (84, false,'Iraq');
INSERT INTO opm.country(id, deleted, name) VALUES (85, false,'Ireland');
INSERT INTO opm.country(id, deleted, name) VALUES (86, false,'Israel');
INSERT INTO opm.country(id, deleted, name) VALUES (87, false,'Italy');
INSERT INTO opm.country(id, deleted, name) VALUES (88, false,'Jamaica');
INSERT INTO opm.country(id, deleted, name) VALUES (89, false,'Japan');
INSERT INTO opm.country(id, deleted, name) VALUES (90, false,'Jordan');
INSERT INTO opm.country(id, deleted, name) VALUES (91, false,'Kazakhstan');
INSERT INTO opm.country(id, deleted, name) VALUES (92, false,'Kenya');
INSERT INTO opm.country(id, deleted, name) VALUES (93, false,'Kiribati');
INSERT INTO opm.country(id, deleted, name) VALUES (94, false,'Korea, North');
INSERT INTO opm.country(id, deleted, name) VALUES (95, false,'Korea, South');
INSERT INTO opm.country(id, deleted, name) VALUES (96, false,'Kosovo');
INSERT INTO opm.country(id, deleted, name) VALUES (97, false,'Kuwait');
INSERT INTO opm.country(id, deleted, name) VALUES (98, false,'Kyrgyzstan');
INSERT INTO opm.country(id, deleted, name) VALUES (99, false,'Laos');
INSERT INTO opm.country(id, deleted, name) VALUES (100, false,'Latvia');
INSERT INTO opm.country(id, deleted, name) VALUES (101, false,'Lebanon');
INSERT INTO opm.country(id, deleted, name) VALUES (102, false,'Lesotho');
INSERT INTO opm.country(id, deleted, name) VALUES (103, false,'Liberia');
INSERT INTO opm.country(id, deleted, name) VALUES (104, false,'Libya');
INSERT INTO opm.country(id, deleted, name) VALUES (105, false,'Liechtenstein');
INSERT INTO opm.country(id, deleted, name) VALUES (106, false,'Lithuania');
INSERT INTO opm.country(id, deleted, name) VALUES (107, false,'Luxembourg');
INSERT INTO opm.country(id, deleted, name) VALUES (108, false,'Macau');
INSERT INTO opm.country(id, deleted, name) VALUES (109, false,'Macedonia');
INSERT INTO opm.country(id, deleted, name) VALUES (110, false,'Madagascar');
INSERT INTO opm.country(id, deleted, name) VALUES (111, false,'Malawi');
INSERT INTO opm.country(id, deleted, name) VALUES (112, false,'Malaysia');
INSERT INTO opm.country(id, deleted, name) VALUES (113, false,'Maldives');
INSERT INTO opm.country(id, deleted, name) VALUES (114, false,'Mali');
INSERT INTO opm.country(id, deleted, name) VALUES (115, false,'Malta');
INSERT INTO opm.country(id, deleted, name) VALUES (116, false,'Marshall Islands');
INSERT INTO opm.country(id, deleted, name) VALUES (117, false,'Mauritania');
INSERT INTO opm.country(id, deleted, name) VALUES (118, false,'Mauritius');
INSERT INTO opm.country(id, deleted, name) VALUES (119, false,'Mexico');
INSERT INTO opm.country(id, deleted, name) VALUES (120, false,'Micronesia');
INSERT INTO opm.country(id, deleted, name) VALUES (121, false,'Moldova');
INSERT INTO opm.country(id, deleted, name) VALUES (122, false,'Monaco');
INSERT INTO opm.country(id, deleted, name) VALUES (123, false,'Mongolia');
INSERT INTO opm.country(id, deleted, name) VALUES (124, false,'Montenegro');
INSERT INTO opm.country(id, deleted, name) VALUES (125, false,'Morocco');
INSERT INTO opm.country(id, deleted, name) VALUES (126, false,'Mozambique');
INSERT INTO opm.country(id, deleted, name) VALUES (127, false,'Namibia');
INSERT INTO opm.country(id, deleted, name) VALUES (128, false,'Nauru');
INSERT INTO opm.country(id, deleted, name) VALUES (129, false,'Nepal');
INSERT INTO opm.country(id, deleted, name) VALUES (130, false,'Netherlands');
INSERT INTO opm.country(id, deleted, name) VALUES (131, false,'Netherlands Antilles');
INSERT INTO opm.country(id, deleted, name) VALUES (132, false,'New Zealand');
INSERT INTO opm.country(id, deleted, name) VALUES (133, false,'Nicaragua');
INSERT INTO opm.country(id, deleted, name) VALUES (134, false,'Niger');
INSERT INTO opm.country(id, deleted, name) VALUES (135, false,'Nigeria');
INSERT INTO opm.country(id, deleted, name) VALUES (136, false,'North Korea');
INSERT INTO opm.country(id, deleted, name) VALUES (137, false,'Norway');
INSERT INTO opm.country(id, deleted, name) VALUES (138, false,'Oman');
INSERT INTO opm.country(id, deleted, name) VALUES (139, false,'Pakistan');
INSERT INTO opm.country(id, deleted, name) VALUES (140, false,'Palau');
INSERT INTO opm.country(id, deleted, name) VALUES (141, false,'Palestinian Territories');
INSERT INTO opm.country(id, deleted, name) VALUES (142, false,'Panama');
INSERT INTO opm.country(id, deleted, name) VALUES (143, false,'Papua New Guinea');
INSERT INTO opm.country(id, deleted, name) VALUES (144, false,'Paraguay');
INSERT INTO opm.country(id, deleted, name) VALUES (145, false,'Peru');
INSERT INTO opm.country(id, deleted, name) VALUES (146, false,'Philippines');
INSERT INTO opm.country(id, deleted, name) VALUES (147, false,'Poland');
INSERT INTO opm.country(id, deleted, name) VALUES (148, false,'Portugal');
INSERT INTO opm.country(id, deleted, name) VALUES (149, false,'Qatar');
INSERT INTO opm.country(id, deleted, name) VALUES (150, false,'Romania');
INSERT INTO opm.country(id, deleted, name) VALUES (151, false,'Russia');
INSERT INTO opm.country(id, deleted, name) VALUES (152, false,'Rwanda');
INSERT INTO opm.country(id, deleted, name) VALUES (153, false,'Saint Kitts and Nevis');
INSERT INTO opm.country(id, deleted, name) VALUES (154, false,'Saint Lucia');
INSERT INTO opm.country(id, deleted, name) VALUES (155, false,'Saint Vincent and the Grenadines');
INSERT INTO opm.country(id, deleted, name) VALUES (156, false,'Samoa');
INSERT INTO opm.country(id, deleted, name) VALUES (157, false,'San Marino');
INSERT INTO opm.country(id, deleted, name) VALUES (158, false,'Sao Tome and Principe');
INSERT INTO opm.country(id, deleted, name) VALUES (159, false,'Saudi Arabia');
INSERT INTO opm.country(id, deleted, name) VALUES (160, false,'Senegal');
INSERT INTO opm.country(id, deleted, name) VALUES (161, false,'Serbia');
INSERT INTO opm.country(id, deleted, name) VALUES (162, false,'Seychelles');
INSERT INTO opm.country(id, deleted, name) VALUES (163, false,'Sierra Leone');
INSERT INTO opm.country(id, deleted, name) VALUES (164, false,'Singapore');
INSERT INTO opm.country(id, deleted, name) VALUES (165, false,'Sint Maarten');
INSERT INTO opm.country(id, deleted, name) VALUES (166, false,'Slovakia');
INSERT INTO opm.country(id, deleted, name) VALUES (167, false,'Slovenia');
INSERT INTO opm.country(id, deleted, name) VALUES (168, false,'Solomon Islands');
INSERT INTO opm.country(id, deleted, name) VALUES (169, false,'Somalia');
INSERT INTO opm.country(id, deleted, name) VALUES (170, false,'South Africa');
INSERT INTO opm.country(id, deleted, name) VALUES (171, false,'South Korea');
INSERT INTO opm.country(id, deleted, name) VALUES (172, false,'South Sudan');
INSERT INTO opm.country(id, deleted, name) VALUES (173, false,'Spain');
INSERT INTO opm.country(id, deleted, name) VALUES (174, false,'Sri Lanka');
INSERT INTO opm.country(id, deleted, name) VALUES (175, false,'Sudan');
INSERT INTO opm.country(id, deleted, name) VALUES (176, false,'Suriname');
INSERT INTO opm.country(id, deleted, name) VALUES (177, false,'Swaziland');
INSERT INTO opm.country(id, deleted, name) VALUES (178, false,'Sweden');
INSERT INTO opm.country(id, deleted, name) VALUES (179, false,'Switzerland');
INSERT INTO opm.country(id, deleted, name) VALUES (180, false,'Syria');
INSERT INTO opm.country(id, deleted, name) VALUES (181, false,'Taiwan');
INSERT INTO opm.country(id, deleted, name) VALUES (182, false,'Tajikistan');
INSERT INTO opm.country(id, deleted, name) VALUES (183, false,'Tanzania');
INSERT INTO opm.country(id, deleted, name) VALUES (184, false,'Thailand ');
INSERT INTO opm.country(id, deleted, name) VALUES (185, false,'Timor-Leste');
INSERT INTO opm.country(id, deleted, name) VALUES (186, false,'Togo');
INSERT INTO opm.country(id, deleted, name) VALUES (187, false,'Tonga');
INSERT INTO opm.country(id, deleted, name) VALUES (188, false,'Trinidad and Tobago');
INSERT INTO opm.country(id, deleted, name) VALUES (189, false,'Tunisia');
INSERT INTO opm.country(id, deleted, name) VALUES (190, false,'Turkey');
INSERT INTO opm.country(id, deleted, name) VALUES (191, false,'Turkmenistan');
INSERT INTO opm.country(id, deleted, name) VALUES (192, false,'Tuvalu');
INSERT INTO opm.country(id, deleted, name) VALUES (193, false,'Uganda');
INSERT INTO opm.country(id, deleted, name) VALUES (194, false,'Ukraine');
INSERT INTO opm.country(id, deleted, name) VALUES (195, false,'United Arab Emirates');
INSERT INTO opm.country(id, deleted, name) VALUES (196, false,'United Kingdom');
INSERT INTO opm.country(id, deleted, name) VALUES (197, false,'Uruguay');
INSERT INTO opm.country(id, deleted, name) VALUES (198, false,'Uzbekistan');
INSERT INTO opm.country(id, deleted, name) VALUES (199, false,'Vanuatu');
INSERT INTO opm.country(id, deleted, name) VALUES (200, false,'Venezuela');
INSERT INTO opm.country(id, deleted, name) VALUES (201, false,'Vietnam');
INSERT INTO opm.country(id, deleted, name) VALUES (202, false,'Yemen');
INSERT INTO opm.country(id, deleted, name) VALUES (203, false,'Zambia');
INSERT INTO opm.country(id, deleted, name) VALUES (204, false,'Zimbabwe');

----------------------------------------------------
-- 			state table
----------------------------------------------------
INSERT INTO opm.state(id, deleted, name) values(1, false, 'Not Applicable');
INSERT INTO opm.state(id, deleted, name) values(2, false, 'AL');
INSERT INTO opm.state(id, deleted, name) values(3, false, 'AK');
INSERT INTO opm.state(id, deleted, name) values(4, false, 'AZ');
INSERT INTO opm.state(id, deleted, name) values(5, false, 'AR');
INSERT INTO opm.state(id, deleted, name) values(6, false, 'CA');
INSERT INTO opm.state(id, deleted, name) values(7, false, 'CO');
INSERT INTO opm.state(id, deleted, name) values(8, false, 'CT');
INSERT INTO opm.state(id, deleted, name) values(9, false, 'DE');
INSERT INTO opm.state(id, deleted, name) values(10, false, 'FL');
INSERT INTO opm.state(id, deleted, name) values(11, false, 'GA');
INSERT INTO opm.state(id, deleted, name) values(12, false, 'HI');
INSERT INTO opm.state(id, deleted, name) values(13, false, 'ID');
INSERT INTO opm.state(id, deleted, name) values(14, false, 'IL');
INSERT INTO opm.state(id, deleted, name) values(15, false, 'IN');
INSERT INTO opm.state(id, deleted, name) values(16, false, 'IA');
INSERT INTO opm.state(id, deleted, name) values(17, false, 'KS');
INSERT INTO opm.state(id, deleted, name) values(18, false, 'KY');
INSERT INTO opm.state(id, deleted, name) values(19, false, 'LA');
INSERT INTO opm.state(id, deleted, name) values(20, false, 'ME');
INSERT INTO opm.state(id, deleted, name) values(21, false, 'MD');
INSERT INTO opm.state(id, deleted, name) values(22, false, 'MA');
INSERT INTO opm.state(id, deleted, name) values(23, false, 'MI');
INSERT INTO opm.state(id, deleted, name) values(24, false, 'MN');
INSERT INTO opm.state(id, deleted, name) values(25, false, 'MS');
INSERT INTO opm.state(id, deleted, name) values(26, false, 'MO');
INSERT INTO opm.state(id, deleted, name) values(27, false, 'MT');
INSERT INTO opm.state(id, deleted, name) values(28, false, 'NE');
INSERT INTO opm.state(id, deleted, name) values(29, false, 'NV');
INSERT INTO opm.state(id, deleted, name) values(30, false, 'NH');
INSERT INTO opm.state(id, deleted, name) values(31, false, 'NJ');
INSERT INTO opm.state(id, deleted, name) values(32, false, 'NM');
INSERT INTO opm.state(id, deleted, name) values(33, false, 'NY');
INSERT INTO opm.state(id, deleted, name) values(34, false, 'NC');
INSERT INTO opm.state(id, deleted, name) values(35, false, 'ND');
INSERT INTO opm.state(id, deleted, name) values(36, false, 'OH');
INSERT INTO opm.state(id, deleted, name) values(37, false, 'OK');
INSERT INTO opm.state(id, deleted, name) values(38, false, 'OR');
INSERT INTO opm.state(id, deleted, name) values(39, false, 'PA');
INSERT INTO opm.state(id, deleted, name) values(40, false, 'RI');
INSERT INTO opm.state(id, deleted, name) values(41, false, 'SC');
INSERT INTO opm.state(id, deleted, name) values(42, false, 'SD');
INSERT INTO opm.state(id, deleted, name) values(43, false, 'TN');
INSERT INTO opm.state(id, deleted, name) values(44, false, 'TX');
INSERT INTO opm.state(id, deleted, name) values(45, false, 'UT');
INSERT INTO opm.state(id, deleted, name) values(46, false, 'VT');
INSERT INTO opm.state(id, deleted, name) values(47, false, 'VA');
INSERT INTO opm.state(id, deleted, name) values(48, false, 'WA');
INSERT INTO opm.state(id, deleted, name) values(49, false, 'WV');
INSERT INTO opm.state(id, deleted, name) values(50, false, 'WI');
INSERT INTO opm.state(id, deleted, name) values(51, false, 'WY');

----------------------------------------------------
-- 			retirement_type table
----------------------------------------------------
INSERT INTO opm.retirement_type(id, deleted, name) VALUES(1, false, 'FERS');
INSERT INTO opm.retirement_type(id, deleted, name) VALUES(2, false, 'CSRS');

----------------------------------------------------
-- 			service_type table
----------------------------------------------------
INSERT INTO opm.service_type(id, deleted, name) VALUES(1, false, 'GS (0)');
INSERT INTO opm.service_type(id, deleted, name) VALUES(2, false, 'Wage Grade');
INSERT INTO opm.service_type(id, deleted, name) VALUES(3, false, 'ES-Executive Schedule');
INSERT INTO opm.service_type(id, deleted, name) VALUES(4, false, 'SES-Senior Executive Schedule');
INSERT INTO opm.service_type(id, deleted, name) VALUES(5, false, 'Senior Official');
INSERT INTO opm.service_type(id, deleted, name) VALUES(6, false, 'Legislators');

----------------------------------------------------
-- 			form_type table
----------------------------------------------------
INSERT INTO opm.form_type(id, deleted, name) VALUES(1, false, 'FERS');
INSERT INTO opm.form_type(id, deleted, name) VALUES(2, false, 'CSRS');

----------------------------------------------------
-- 			suffix table
----------------------------------------------------
INSERT INTO opm.suffix(id, deleted, name) VALUES(1, false,'I');
INSERT INTO opm.suffix(id, deleted, name) VALUES(2, false,'II');
INSERT INTO opm.suffix(id, deleted, name) VALUES(3, false,'III');
INSERT INTO opm.suffix(id, deleted, name) VALUES(4, false,'JR');
INSERT INTO opm.suffix(id, deleted, name) VALUES(5, false,'SR');

----------------------------------------------------
-- 			appointment_type table
----------------------------------------------------
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(1, false, 'appointment type 1');
INSERT INTO opm.appointment_type(id, deleted, name) VALUES(2, false, 'appointment type 2');

----------------------------------------------------
-- 			pay_type table
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

----------------------------------------------------
-- 			transfer_type table
----------------------------------------------------
INSERT INTO opm.transfer_type(id, deleted, name) VALUES(1, false, 'INTEREST_ADJUSTMENT');
INSERT INTO opm.transfer_type(id, deleted, name) VALUES(2, false, 'SUSPENDED_PAYMENT');
INSERT INTO opm.transfer_type(id, deleted, name) VALUES(3, false, 'SUSPENDED_PAYMENT');
INSERT INTO opm.transfer_type(id, deleted, name) VALUES(4, false, 'SUSPENDED_PAYMENT');
INSERT INTO opm.transfer_type(id, deleted, name) VALUES(5, false, 'ORDINARY');

----------------------------------------------------
-- 			application_designation table
----------------------------------------------------
INSERT INTO opm.application_designation(id, deleted, name) VALUES(1, false, 'Default User Order');
INSERT INTO opm.application_designation(id, deleted, name) VALUES(2, false, 'Pre Deposit');
INSERT INTO opm.application_designation(id, deleted, name) VALUES(3, false, 'Post Deposit');
INSERT INTO opm.application_designation(id, deleted, name) VALUES(4, false, 'Pre Redeposit');
INSERT INTO opm.application_designation(id, deleted, name) VALUES(5, false, 'Post Redeposit');
INSERT INTO opm.application_designation(id, deleted, name) VALUES(6, false, 'FERS');

----------------------------------------------------
-- 			payment_status table
----------------------------------------------------
INSERT INTO opm.payment_status(id, deleted, name) VALUES(1, false, 'ORDINARY');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(2, false, 'Reversed - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(3, false, 'Reversed - Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(4, false, 'Voluntary Contributions - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(5, false, 'Voluntary Contributions - Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(6, false, 'Direct Pay Life - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(7, false, 'Direct Pay Life - Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(8, false, 'Suspense Refund Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(9, false, 'Suspense Refund Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(10, false, 'Annuity - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(11, false, 'Annuity - Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(12, false, 'Debit Voucher - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(13, false, 'Debit Voucher - Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(14, false, 'Health Benefits - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(15, false, 'Health Benefits - Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(16, false, 'Adjustment - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(17, false, 'Adjustment - Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(18, false, 'Write-Off - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(19, false, 'Write-Off - Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(20, false, 'Credit Balance Refund - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(21, false, 'Credit Balance Refund - Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(22, false, 'Batch Auto Refund Canceled - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(23, false, 'Batch Auto Refund Canceled - Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(24, false, 'Manual Adjustment Cancelled - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(25, false, 'Manual Adjustment Cancelled - Pending');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(26, false, 'Batch Auto Refund - Pending Approval');
INSERT INTO opm.payment_status(id, deleted, name) VALUES(27, false, 'Batch Auto Refund - Pending');


----------------------------------------------------
-- 			address table
----------------------------------------------------
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(1, false, '39387 Corscot Parkway', '4895 Maryland Place', '264 Stoughton Trail', '8 International Hill', '04 Pine View Point', 'Walnut Creek', 42, '49039', 3);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(2, false, '48 Montana Center', '689 Farragut Drive', '706 Oakridge Place', '66 Kings Point', '5253 Morningstar Pass', 'Nevada City', 9, '04908', 189);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(3, false, '42631 Harbort Place', '6 Crest Line Lane', '8 Granby Junction', '58038 Sommers Road', '05399 Westport Hill', 'Colton', 50, '42548.7656', 184);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(4, false, '0 Meadow Valley Pass', '8448 Vahlen Crossing', '99503 Melody Terrace', '68449 Hovde Crossing', '4 Reindahl Point', 'Palm Desert', 49, '51306', 160);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(5, false, '5665 Magdeline Way', '65130 Erie Road', '296 International Road', '721 Annamark Junction', '5425 Badeau Crossing', 'La Habra', 38, '87453', 85);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(6, false, '7529 Loeprich Parkway', '04079 Pierstorff Way', '070 Jay Terrace', '31 Steensland Crossing', '8727 Graceland Alley', 'Santa Rosa', 3, '90618.5789', 131);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(7, false, '78 Pine View Crossing', '83 Grover Center', '7 Mandrake Place', '68611 John Wall Trail', '9 Graceland Crossing', 'Poway', 31, '20513.3605', 187);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(8, false, '248 Moland Lane', '6 Hayes Park', '26925 Larry Point', '2281 Sage Way', '8361 Stone Corner Parkway', 'Marysville', 26, '57948', 1);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(9, false, '8331 Clemons Place', '0073 Kingsford Court', '50 Carioca Avenue', '9232 Petterle Drive', '5 Namekagon Place', 'Watsonville', 36, '72148.1397', 85);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(10, false, '567 Sunnyside Parkway', '32203 Maple Road', '83 Florence Point', '2882 Troy Lane', '0 Towne Street', 'Ione', 39, '27286.7909', 114);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(11, false, '32 Longview Park', '316 Green Ridge Circle', '44480 Donald Road', '2 Westport Pass', '4 Logan Plaza', 'El Centro', 11, '86107', 194);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(12, false, '07148 Schmedeman Pass', '5814 Lakewood Gardens Road', '7938 Norway Maple Crossing', '21 Larry Center', '13 Clemons Center', 'Paso Robles', 1, '88444.8171', 106);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(13, false, '21 Elgar Drive', '44909 Scofield Circle', '24 Gerald Terrace', '5 Dorton Street', '78 Independence Circle', 'Lakewood', 44, '33635', 45);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(14, false, '1385 Amoth Point', '899 Kinsman Pass', '87 Cambridge Alley', '10 Amoth Hill', '7888 Carberry Alley', 'Colfax', 23, '67693', 64);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(15, false, '86733 Veith Drive', '1036 Merrick Lane', '8 Marcy Road', '75645 Morning Trail', '54 Brentwood Parkway', 'Isleton', 29, '13118', 47);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(16, false, '5429 Thompson Circle', '8 Briar Crest Way', '1 Hayes Junction', '7544 Nobel Drive', '8 Ridge Oak Road', 'Redding', 1, '79666.4189', 72);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(17, false, '8362 Village Green Alley', '708 Southridge Center', '814 Ronald Regan Avenue', '3469 Pawling Avenue', '8 Dennis Parkway', 'Ukiah', 17, '31190', 192);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(18, false, '21091 Vermont Drive', '89985 Evergreen Junction', '3148 Browning Drive', '475 Bobwhite Park', '32 Vahlen Trail', 'Vacaville', 5, '34363.9140', 174);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(19, false, '27493 Nova Way', '126 John Wall Street', '4233 Merrick Circle', '8571 6th Court', '64130 Melrose Way', 'Maricopa', 36, '86764.5409', 8);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(20, false, '41801 Monterey Court', '3 Dryden Place', '6045 Lighthouse Bay Plaza', '230 Green Ridge Park', '22000 Erie Court', 'Tulare', 12, '11579.7144', 90);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(21, false, '1 Caliangt Terrace', '13 Quincy Hill', '2 Monument Plaza', '6 Lerdahl Circle', '7820 Mcbride Drive', 'Carlsbad', 17, '23992.4651', 15);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(22, false, '44 Marcy Way', '61078 Homewood Road', '6119 Starling Court', '53847 Burning Wood Way', '18343 Blue Bill Park Park', 'Lemon Grove', 40, '79313.9882', 154);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(23, false, '50 Arrowood Point', '58044 Mifflin Pass', '1924 Golf View Avenue', '77684 Miller Crossing', '5 Nelson Lane', 'Laguna Hills', 6, '54014', 204);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(24, false, '54 Union Court', '801 Kenwood Road', '77349 Melrose Crossing', '6002 Reinke Road', '81714 Hintze Point', 'Crescent City', 32, '62671.0991', 62);
INSERT INTO opm.address(id,deleted, street1, street2, street3, street4, street5, city, state_id, zip_code, country_id) VALUES(25, false, '32691 Kinsman Pass', '4 Farmco Point', '1 Parkside Way', '996 Golf Drive', '396 Oxford Terrace', 'Martinez', 19, '65494', 116);

-------------------------------------------
-----   account_holder table
-------------------------------------------
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (1, false, 'Rachel', 'Gonzalez', 'rgonzalez@oloo.name', '1982-01-14', 'Mr', 'Seaside', '83738.2251', 'J', '046-44-6641', '860-895-3337', 4, 'PO', 5, 1);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (2, false, 'Patricia', 'Sullivan', 'psullivan@jamia.org', '1994-06-21', 'Dr', 'Tehama', '90361.1191', 'M', '654-01-8174', '843-833-7402', 4, 'NP', 50, 2);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (3, false, 'Elizabeth', 'Flores', 'eflores@yozio.info', '1986-04-18', 'Mr', 'Red Bluff', '84730', 'S', '327-32-6278', '217-302-2831', 5, 'NP', 4, 3);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (4, false, 'Betty', 'Washington', 'bwashington@yakidoo.info', '1983-04-29', 'Dr', 'Ripon', '46984.8704', 'E', '538-51-7454', '509-645-9197', 4, 'NP', 7, 4);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (5, false, 'Todd', 'Richards', 'trichards@tagcat.name', '2000-06-08', 'Dr', 'West Covina', '83102.7801', 'M', '519-72-1744', '208-821-3566', 1, 'NP', 38, 5);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (6, false, 'Jason', 'Banks', 'jbanks@feedfish.gov', '1977-01-12', 'Ms', 'Cloverdale', '83165', 'J', '761-01-2958', '731-287-2939', 5, 'PO', 21, 6);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (7, false, 'Evelyn', 'Stewart', 'estewart@jaxbean.net', '1987-10-28', 'Dr', 'Rio Vista', '39063', 'J', '574-86-6140', '907-295-5168', 4, 'NP', 6, 7);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (8, false, 'Kelly', 'Wilson', 'kwilson@photofeed.mil', '1980-02-18', 'Mr', 'Healdsburg', '58237.8222', 'A', '028-18-4879', '508-563-9121', 2, 'NP', 50, 8);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (9, false, 'Louise', 'Robertson', 'lrobertson@myworks.info', '1995-02-24', 'Dr', 'Baldwin Park', '09644', 'L', '690-03-9493', '252-644-7106', 2, 'PO', 28, 9);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (10, false, 'John', 'Stewart', 'jstewart@livetube.net', '1976-05-15', 'Mr', 'Danville', '26036.7662', 'J', '769-36-6645', '954-357-0424', 2, 'PO', 24, 10);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (11, false, 'Heather', 'Scott', 'hscott@gabtune.edu', '1984-01-26', 'Mr', 'San Mateo', '91534', 'A', '024-34-5876', '508-760-1012', 2, 'NP', 42, 11);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (12, false, 'Harry', 'Nelson', 'hnelson@oyope.org', '1974-05-08', 'Mrs', 'Los Alamitos', '91550.3254', 'A', '151-02-9932', '908-751-4668', 5, 'PO', 29, 12);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (13, false, 'Jacqueline', 'Baker', 'jbaker@wikizz.biz', '1987-05-02', 'Rev', 'Oakland', '31440.3135', 'D', '306-80-0204', '317-215-9899', 4, 'NP', 51, 13);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (14, false, 'Ernest', 'Ramirez', 'eramirez@fatz.edu', '1977-04-12', 'Mrs', 'Seaside', '81077', 'A', '393-56-1720', '262-825-6399', 1, 'PO', 27, 14);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (15, false, 'Timothy', 'Lawrence', 'tlawrence@flipopia.com', '1996-06-19', 'Rev', 'Belvedere', '51621.1409', 'H', '249-52-4190', '864-838-8839', 5, 'NP', 24, 15);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (16, false, 'Cheryl', 'Thompson', 'cthompson@fivebridge.gov', '1974-11-28', 'Honorable', 'Oakland', '69464.9845', 'A', '514-58-5707', '785-206-4693', 1, 'NP', 17, 16);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (17, false, 'Thomas', 'Austin', 'taustin@yambee.gov', '1973-05-02', 'Mr', 'Irvine', '35138.6113', 'C', '300-96-5313', '937-257-2287', 3, 'NP', 37, 17);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (18, false, 'Amanda', 'Stone', 'astone@feedfish.net', '1972-03-29', 'Honorable', 'Palmdale', '86013', 'M', '570-27-0184', '626-515-5533', 2, 'PO', 21, 18);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (19, false, 'Evelyn', 'Lawrence', 'elawrence@muxo.com', '1998-06-07', 'Rev', 'Baldwin Park', '84293.5784', 'L', '245-04-8534', '336-522-1325', 1, 'PO', 2, 19);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (20, false, 'Jose', 'Williamson', 'jwilliamson@ainyx.gov', '1978-08-12', 'Mrs', 'Suisun City', '35729.1544', 'G', '629-64-9826', '903-738-5806', 4, 'PO', 18, 20);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (21, false, 'Bruce', 'Harvey', 'bharvey@einti.com', '1974-12-12', 'Honorable', 'Plymouth', '70554.1794', 'R', '237-90-7071', '252-717-7075', 4, 'PO', 9, 21);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (22, false, 'Teresa', 'Palmer', 'tpalmer@oloo.com', '1973-05-14', 'Ms', 'Yuba City', '16881.7031', 'B', '352-70-6569', '224-948-0096', 2, 'PO', 15, 22);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (23, false, 'Ruth', 'Spencer', 'rspencer@mynte.com', '1984-07-24', 'Mr', 'Sand City', '81763', 'C', '762-01-0311', '423-323-5393', 3, 'NP', 5, 23);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (24, false, 'Nicholas', 'Arnold', 'narnold@realfire.org', '1998-10-31', 'Rev', 'Dixon', '23034', 'L', '042-88-5199', '860-384-1228', 4, 'NP', 44, 24);
INSERT INTO opm.account_holder(id, deleted, first_name, last_name, email, birth_date, title, city_of_employment, geo_code, middle_initial, ssn, telephone, suffix_id, department_code, state_of_employment_id, address_id) 
values (25, false, 'Raymond', 'Thomas', 'rthomas@thoughtstorm.net', '1996-03-11', 'Honorable', 'Pismo Beach', '81467.4498', 'H', '356-50-4778', '708-710-6597', 4, 'PO', 9, 25);

---------------------------------------------------
-----   billing_summary table
---------------------------------------------------
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (1, false, '03/14/2013', '05/21/2003', '09/10/1999', '10/26/2013', 'D', '12/24/2012', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (2, false, '12/29/2012', '06/16/2004', '04/20/1994', '06/23/2013', 'C', '08/10/2013', false);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (3, false, '10/27/2013', '12/12/2008', '03/17/1995', '02/25/2013', 'D', '05/10/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (4, false, '02/04/2013', '06/24/2009', '05/22/1996', '02/14/2013', 'C', '01/24/2013', false);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (5, false, '12/13/2012', '01/05/2009', '09/18/1993', '05/03/2013', 'C', '04/30/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (6, false, '06/16/2013', '02/18/2001', '06/02/2000', '02/27/2013', 'C', '08/07/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (7, false, '01/03/2013', '09/23/2002', '07/28/1993', '07/30/2013', 'D', '11/07/2013', false);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (8, false, '01/02/2013', '01/24/2011', '08/31/1999', '08/21/2013', 'D', '11/21/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (9, false, '02/19/2013', '12/16/2011', '11/06/1994', '10/16/2013', 'C', '07/10/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (10, false, '07/29/2013', '09/25/2008', '04/19/2000', '12/17/2012', 'C', '10/24/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (11, false, '01/24/2013', '09/02/2012', '01/06/1995', '04/16/2013', 'C', '09/07/2013', false);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (12, false, '04/20/2013', '07/29/2003', '05/06/2000', '01/07/2013', 'D', '10/31/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (13, false, '05/14/2013', '06/27/2010', '06/09/1994', '11/16/2013', 'D', '05/18/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (14, false, '10/22/2013', '12/17/2007', '10/09/1997', '12/05/2013', 'D', '12/20/2012', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (15, false, '05/05/2013', '05/11/2009', '10/10/1999', '05/27/2013', 'C', '01/28/2013', false);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (16, false, '06/27/2013', '09/16/2010', '04/18/1991', '09/11/2013', 'C', '08/20/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (17, false, '10/26/2013', '04/30/2006', '02/25/1992', '05/02/2013', 'C', '01/10/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (18, false, '08/02/2013', '07/30/2009', '07/29/1999', '03/24/2013', 'C', '04/02/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (19, false, '07/09/2013', '05/06/2013', '01/01/1998', '03/28/2013', 'D', '02/13/2013', false);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (20, false, '05/26/2013', '12/28/2006', '07/18/1992', '03/14/2013', 'C', '03/23/2013', false);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (21, false, '11/07/2013', '11/29/2006', '06/09/1998', '08/08/2013', 'D', '11/15/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (22, false, '01/22/2013', '09/21/2006', '07/18/1997', '07/06/2013', 'D', '10/23/2013', false);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (23, false, '07/29/2013', '12/30/2009', '06/03/1998', '08/02/2013', 'C', '11/28/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (24, false, '09/21/2013', '01/21/2004', '09/30/1994', '06/18/2013', 'C', '03/22/2013', true);
INSERT INTO opm.billing_summary (id, deleted, computed_date, last_deposit_date, first_billing_date, last_interest_calculation, transaction_type, last_transaction_date, stop_ach_payments) VALUES (25, false, '04/07/2013', '05/08/2005', '09/24/1995', '11/22/2013', 'D', '11/09/2013', true);

---------------------------------------------------
-----   account table
---------------------------------------------------
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(1, false, 'FERS', 1, 1, 354312.44, 1, 2, true, true, 'user1', '2013-11-29', '2013-02-18', '1926-10-21', NULL, '1', 1);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(2, false, 'FERS', 1, 2, 343008.78, 2, 1, true, true, 'user2', '2013-10-06', '2013-10-16', '1958-11-1', NULL, '2', 1);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(3, false, 'FERS', 2, 3, 280711.45, 3, 4, true, true, 'user3', '2013-04-24', '2013-05-03', '1930-5-17', NULL, '3', 1);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(4, false, 'FERS', 1, 4, 435222.43, 4, 1, false, true, 'user4', '2013-04-12', '2012-12-31', '1937-9-4', NULL, '4', 1);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(5, false, 'FERS', 2, 5, 328984.57, 5, 1, false, true, 'user5', '2013-08-25', '2013-09-09', '1963-5-1', NULL, '5', 1);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(6, false, 'FERS', 1, 6, 445288.9, 6, 5, true, false, 'user6', '2013-08-09', '2013-05-07', '1970-4-11', NULL, '6', 1);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(7, false, 'FERS', 1, 7, 381404.72, 7, 4, false, false, 'user7', '2013-01-21', '2013-02-23', '1972-11-5', NULL, '7', 1);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(8, false, 'FERS', 1, 8, 424859.08, 8, 5, false, true, 'user8', '2013-07-03', '2012-12-31', '1927-11-26', NULL, '8', 1);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(9, false, 'FERS', 1, 9, 438900.38, 9, 2, true, false, 'user9', '2013-04-29', '2013-11-05', '1952-12-5', NULL, '9', 1);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(10, false, 'FERS', 1, 10, 274082.27, 10, 4, true, true, 'user10', '2013-01-14', '2013-07-31', '1955-4-10', NULL, '10', 2);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(11, false, 'FERS', 1, 11, 285823.52, 11, 3, false, true, 'user11', '2013-01-06', '2013-06-23', '1966-7-25', NULL, '11', 2);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(12, false, 'FERS', 1, 12, 397607.1, 12, 2, false, true, 'user12', '2013-06-11', '2013-01-12', '1958-7-15', NULL, '12', 2);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(13, false, 'FERS', 1, 13, 468146.43, 13, 1, false, false, 'user13', '2012-12-20', '2013-05-20', '1962-11-14', NULL, '13', 2);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(14, false, 'FERS', 1, 14, 279608.64, 14, 1, true, true, 'user14', '2013-12-09', '2013-07-03', '1971-5-30', NULL, '14', 2);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(15, false, 'FERS', 1, 15, 474574.69, 15, 1, true, true, 'user15', '2013-02-10', '2012-12-18', '1961-2-25', NULL, '15', 2);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(16, false, 'FERS', 1, 16, 228273.82, 16, 4, false, false, 'user16', '2013-02-11', '2013-03-02', '1923-1-7', NULL, '16', 2);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(17, false, 'FERS', 1, 17, 318090.91, 17, 3, true, false, 'user17', '2013-05-09', '2013-05-01', '1921-1-31', NULL, '17', 2);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(18, false, 'FERS', 1, 18, 404415.81, 18, 4, true, false, 'user18', '2013-12-02', '2013-04-09', '1979-10-18', NULL, '18', 2);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(19, false, 'FERS', 1, 19, 480927.54, 19, 4, false, true, 'user19', '2013-06-05', '2013-07-31', '1951-2-14', NULL, '19', 2);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(20, false, 'FERS', 1, 20, 240875.94, 20, 2, false, true, 'user20', '2013-10-24', '2013-05-31', '1976-9-27', NULL, '20', 2);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(21, false, 'FERS', 1, 21, 447051.39, 21, 1, true, true, 'user21', '2013-02-16', '2013-05-01', '1956-2-29', NULL, '21', 2);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id)
VALUES(22, false, 'FERS', 1, 22, 464952.56, 22, 4, true, false, 'user22', '2013-11-28', '2013-04-10', '1952-4-22', NULL, '22', 3);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(23, false, 'FERS', 1, 23, 390599.12, 23, 1, true, true, 'user23', '2013-03-26', '2013-04-13', '1964-12-2', NULL, '23', 3);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(24, false, 'FERS', 2, 24, 251186.32, 24, 1, false, true, 'user24', '2013-02-28', '2013-04-04', '1921-9-17', NULL, '24', 3);
INSERT INTO opm.account(id, deleted, plan_type, form_type_id, account_holder_id, balance, billing_summary_id, account_status_id, grace, frozen, claim_officer, claim_officer_assignment_date, returned_from_record_date, claimant_birthdate, account_confirmation_validation_id, claim_number, pay_code_id) 
VALUES(25, false, 'FERS', 1, 25, 358372.91, 25, 3, true, true, 'user25', '2013-01-17', '2013-04-10', '1952-7-22', NULL, '25', 3);

---------------------------------------------------
-----   billing table
---------------------------------------------------
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(1, false, 'wyoung', 13571.33, 15298.61, 8449.61, 20420.33, 1, 1, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(2, false, 'jcox', 13548.24, 18136.03, 7149.38, 24534.88, 2, 2, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(3, false, 'dwatson', 17656.0, 16621.32, 5798.56, 28478.76, 3, 3, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(4, false, 'athompson', 16278.99, 16237.45, 6514.87, 26001.57, 4, 4, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(5, false, 'tnelson', 14064.74, 19096.39, 6811.78, 26349.35, 5, 5, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(6, false, 'pperez', 18754.47, 18341.08, 7658.91, 29436.64, 6, 6, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(7, false, 'alynch', 18830.3, 9295.31, 5398.97, 22726.64, 7, 7, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(8, false, 'khall', 10383.14, 17012.48, 7644.43, 19751.19, 8, 8, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(9, false, 'lmiller', 10199.26, 9459.89, 6778.98, 12880.17, 9, 9, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(10, false, 'ccoleman', 13359.18, 14123.85, 8694.38, 18788.65, 10, 10, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(11, false, 'pturner', 15857.77, 11119.57, 8282.48, 18694.86, 11, 11, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(12, false, 'jparker', 10847.07, 15074.15, 8803.15, 17118.07, 12, 12, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(13, false, 'wlopez', 17840.74, 14073.52, 4854.77, 27059.49, 13, 13, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(14, false, 'ahowell', 16282.97, 17275.15, 7710.69, 25847.43, 14, 14, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(15, false, 'jrichardson', 9150.27, 12086.95, 8424.21, 12813.01, 15, 15, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(16, false, 'mfuller', 14759.98, 12695.46, 5688.77, 21766.67, 16, 16, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(17, false, 'sreyes', 17905.47, 15045.74, 8100.91, 24850.3, 17, 17, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(18, false, 'mrichardson', 15600.82, 14563.27, 5046.76, 25117.33, 18, 18, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(19, false, 'choward', 16275.9, 18861.22, 4266.35, 30870.77, 19, 19, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(20, false, 'holiver', 9746.65, 17224.65, 6163.56, 20807.74, 20, 20, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(21, false, 'cmyers', 11257.17, 10280.24, 3252.08, 18285.33, 21, 21, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(22, false, 'gperkins', 13037.03, 12545.48, 5069.63, 20512.88, 22, 22, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(23, false, 'jcollins', 13597.03, 17028.32, 5299.26, 25326.08, 23, 23, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(24, false, 'sward', 19393.01, 12403.39, 7967.08, 23829.32, 24, 24, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(25, false, 'dbradley', 16366.53, 10386.56, 3642.32, 23110.77, 25, 25, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(26, false, 'shernandez', 17224.04, 19545.37, 5899.66, 30869.75, 26, 1, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(27, false, 'jalexander', 9800.19, 15171.34, 4799.68, 20171.85, 27, 2, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(28, false, 'bramirez', 18698.18, 18289.47, 6454.19, 30533.46, 28, 3, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(29, false, 'bedwards', 13663.47, 15556.05, 4281.63, 24937.88, 29, 4, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(30, false, 'rrodriguez', 12872.01, 11961.96, 3094.33, 21739.64, 30, 5, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(31, false, 'bjohnson', 16876.2, 13023.29, 7256.02, 22643.47, 31, 6, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(32, false, 'rcarr', 19523.35, 16409.56, 5110.31, 30822.60, 32, 7, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(33, false, 'wwells', 9384.34, 13011.97, 5201.33, 17194.97, 33, 8, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(34, false, 'cmitchell', 11731.42, 19997.48, 4607.79, 27121.11, 34, 9, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(35, false, 'hjacobs', 15115.09, 9881.42, 8217.79, 16778.72, 35, 10, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(36, false, 'aspencer', 10309.99, 19387.51, 4619.73, 25077.77, 36, 11, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(37, false, 'rbrown', 9555.0, 19238.26, 7203.55, 21589.71, 37, 12, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(38, false, 'jwagner', 11753.88, 14933.61, 3015.72, 23671.76, 38, 13, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(39, false, 'sford', 18034.07, 18368.9, 7372.4, 29030.57, 39, 14, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(40, false, 'hperry', 17633.52, 17282.72, 6263.8, 28652.44, 40, 15, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(41, false, 'pdaniels', 12307.5, 15413.69, 3940.66, 23780.53, 41, 16, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(42, false, 'kellis', 9426.94, 9075.82, 5249.76, 13253.00, 42, 17, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(43, false, 'pmeyer', 14838.13, 14535.8, 4546.5, 24827.43, 43, 18, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(44, false, 'jsanchez', 11525.74, 19633.21, 3449.26, 27709.68, 44, 19, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(45, false, 'adavis', 13063.41, 10887.91, 7697.83, 16253.49, 45, 20, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(46, false, 'jkelley', 15105.05, 9647.95, 3627.02, 21125.98, 46, 21, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(47, false, 'jhanson', 11370.39, 17689.28, 3614.15, 25445.51, 47, 22, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(48, false, 'jnichols', 18437.48, 14668.47, 6950.72, 26155.22, 48, 23, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(49, false, 'gharris', 18470.8, 11041.19, 6098.31, 23413.67, 49, 24, false);
INSERT INTO opm.billing(id, deleted, name, initial_billing, additional_interest, total_payments, balance, payment_order, billing_summary_id, frozen) VALUES(50, false, 'twelch', 17988.83, 11052.34, 5358.58, 23682.59, 50, 25, false);

---------------------------------------------------
-----   payment table
---------------------------------------------------
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number, gov_refund, disapprove, history_payment, resolve_suspense, user_inserted, post_flag, order_code_id, pay_trans_status_code_id) 
VALUES (1, false, 1, '04/30/1978', '10/30/2013', 10553.1, 'Beverly Howard', '10/27/1970', 971, '07/14/2013', '07/20/2013', 5, false, 'ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec', '8d3ad280-3831-43fb-b46d-672e10b730a6', false, 17386.72, 1, '08/13/1969', 1, 35091.56, 1611, 16576.52, 17120.34, 13325.97, 18449.53, 'bhill', 'PENDING', 'ORDINARY', 1, '235', '442', '842', '900302', '046-44-6641', '622', '900302', true, false, true, false, true, true, 1, 1);
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number, gov_refund, disapprove, history_payment, resolve_suspense, user_inserted, post_flag, order_code_id, pay_trans_status_code_id) 
VALUES (2, false, 1, '07/21/1973', '05/17/2012', 12759.51, 'Christine Marshall', '08/10/1961', 198, '02/13/2013', '01/13/2013', 4, true, 'ut nulla sed accumsan felis ut at dolor quis odio', '3cf7394a-2ce1-44d3-957f-012df46897aa', true, 19413.98, 2, '11/19/1972', 1, 15600.75, 1842, 15648.83, 18833.83, 10051.79, 12952.53, 'tburns', 'PENDING', 'ORDINARY', 2, '635', '422', '689', '939098', '654-01-8174', '296', '939098', true, false, true, false, true, true, 1, 1);
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number, gov_refund, disapprove, history_payment, resolve_suspense, user_inserted, post_flag, order_code_id, pay_trans_status_code_id) 
VALUES (3, false, 1, '05/27/1971', '09/27/2013', 16101.86, 'Jane Weaver', '01/04/1963', 952, '07/20/2013', '04/13/2013', 5, false, 'elementum in hac habitasse platea dictumst morbi vestibulum velit id pretium iaculis', 'dbd122ba-6ff3-41b7-9f20-b2f0ef76d107', true, 13090.1, 1, '11/16/1980', 1, 15757.72, 113, 16375.38, 16454.55, 15234.85, 9207.85, 'swilliams', 'APPROVED', 'SUSPENDED_PAYMENT', 3, '354', '970', '806', '991527', '327-32-6278', '354', '991527', true, false, true, false, true, true, 1, 1);
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number, gov_refund, disapprove, history_payment, resolve_suspense, user_inserted, post_flag, order_code_id, pay_trans_status_code_id) 
VALUES (4, false, 1, '09/30/1974', '02/15/2013', 10897.03, 'Judith Kim', '12/25/1960', 936, '11/26/2013', '05/07/2013', 1, true, 'cubilia curae nulla dapibus dolor vel est donec odio justo sollicitudin ut suscipit a feugiat et eros', '0fa875e7-01a7-4956-b1e4-5c361ff95d85', false, 40354.53, 1, '07/20/1964', 2, 29867.29, 1516, 9860.79, 14815.27, 17982.89, 18966.95, 'emills', 'PENDING', 'INTEREST_ADJUSTMENT', 4, '948', '870', '278', '908040', '538-51-7454', '948', '908040', true, false, true, false, true, true, 1, 1);
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number, gov_refund, disapprove, history_payment, resolve_suspense, user_inserted, post_flag, order_code_id, pay_trans_status_code_id) 
VALUES (5, false, 1, '08/29/1979', '04/11/2013', 17414.41, 'Todd Diaz', '03/01/1963', 356, '09/08/2013', '12/28/2012', 2, true, 'cursus id turpis integer aliquet massa id lobortis convallis tortor risus', '1bb9ee42-d847-4dfd-a9e1-4b43b9b53a79', true, 26319.05, 2, '08/10/1977', 1, 45059.01, 1988, 12213.42, 11878.37, 14321.5, 12647.76, 'bjohnson', 'APPROVED', 'SUSPENDED_PAYMENT', 5, '551', '182', '589', '982969', '519-72-1744', '551', '982969', true, false, true, false, true, true, 1, 1);
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number, gov_refund, disapprove, history_payment, resolve_suspense, user_inserted, post_flag, order_code_id, pay_trans_status_code_id) 
VALUES (6, false, 12, '05/12/1980', '12/20/2012', 15445.01, 'Donna Bailey', '03/28/1964', 607, '10/08/2013', '12/05/2013', 1, true, 'adipiscing elit proin interdum mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue vivamus metus arcu adipiscing', '835a4214-6e4d-487e-8a91-64875739ff04', true, 17593.17, 2, '12/15/1965', 2, 40586.37, 830, 9140.5, 15141.74, 18262.14, 18384.33, 'rramos', 'PENDING', 'SUSPENDED_PAYMENT', 1, '821', '635', '580', '929063', '761-01-2958', '821', '929063', true, false, true, false, true, true, 1, 1);
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number, gov_refund, disapprove, history_payment, resolve_suspense, user_inserted, post_flag, order_code_id, pay_trans_status_code_id) 
VALUES (7, false, 1, '02/04/1971', '05/27/2013', 17197.69, 'Beverly Miller', '01/16/1967', 782, '09/24/2013', '04/25/2013', 5, true, 'posuere cubilia curae duis faucibus accumsan odio curabitur convallis duis consequat dui nec nisi volutpat eleifend', 'fa6ce8c5-7868-474e-bbc5-719c65222884', true, 25168.41, 1, '01/02/1977', 1, 15414.84, 1049, 11045.64, 16266.04, 17854.58, 11644.18, 'cjordan', 'PENDING', 'SUSPENDED_PAYMENT', 2, '785', '904', '718', '988601', '574-86-6140', '785', '988601', true, false, true, false, true, true, 1, 1);
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number, gov_refund, disapprove, history_payment, resolve_suspense, user_inserted, post_flag, order_code_id, pay_trans_status_code_id) 
VALUES (8, false, 5, '06/27/1973', '04/11/2013', 13240.32, 'Michael Perkins', '05/25/1965', 924, '09/13/2013', '04/12/2013', 2, true, 'nulla nunc purus phasellus in felis donec semper sapien a libero nam dui proin leo odio porttitor id', 'd98c97f0-98dc-4152-80de-20eaf2a2f25f', true, 12623.77, 1, '03/08/1979', 1, 24114.74, 1840, 14894.86, 16567.98, 14856.16, 16006.97, 'rfreeman', 'APPROVED', 'SUSPENDED_PAYMENT', 3, '228', '284', '884', '914251', '028-18-4879', '228', '914251', true, false, true, false, true, true, 1, 1);
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number, gov_refund, disapprove, history_payment, resolve_suspense, user_inserted, post_flag, order_code_id, pay_trans_status_code_id) 
VALUES (9, false, 24, '07/10/1973', '09/22/2013', 19900.05, 'Howard Hansen', '09/15/1964', 361, '05/12/2013', '12/09/2013', 3, false, 'tellus nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy', '8fa06fe3-cac2-4e94-91f6-d9f93e16ad47', true, 35208.6, 1, '03/02/1976', 2, 44141.73, 416, 18438.72, 13894.39, 10225.55, 17718.58, 'sallen', 'APPROVED', 'SUSPENDED_PAYMENT', 4, '934', '649', '575', '971041', '690-03-9493', '934', '971041', true, false, true, false, true, true, 1, 1);
INSERT INTO opm.payment (id, deleted, payment_status_id, account_holder_birthdate, deposit_date, amount, claimant, claimant_birthday, sequence, transaction_date, status_date, apply_designation_id, apply_to_gl, note, transaction_key, ach, account_balance, account_status_id, master_claimant_birthday, master_account_status_id, master_account_balance, master_account_id, pre_deposit_amount, pre_redeposit_amount, post_deposit_amount, post_redeposit_amount, approval_user, approval_status, payment_type, account_id, batch_number, block_number, sequence_number, master_claim_number, ssn, import_id, claim_number, gov_refund, disapprove, history_payment, resolve_suspense, user_inserted, post_flag, order_code_id, pay_trans_status_code_id) 
VALUES (10, false, 1, '01/11/1979', '06/13/2013', 9129.47, 'Edward Thomas', '10/20/1962', 760, '06/18/2013', '07/05/2013', 6, true, 'aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus', '686c660f-00d0-4f62-89d0-e29128cfb6f2', true, 44748.13, 1, '06/03/1972', 1, 29694.0, 816, 10084.16, 11024.42, 16769.59, 10632.59, 'dcruz', 'PENDING', 'ORDINARY', 5, '507', '538', '437', '941094', '769-36-6645', '507', '941094', true, false, true, false, true, true, 1, 1);

----------------------------------------------------
-- 			calculation_status table
----------------------------------------------------
INSERT INTO opm.calculation_status(id, deleted, name) VALUES(1, false, 'SUCCESS');
INSERT INTO opm.calculation_status(id, deleted, name) VALUES(2, false, 'FAILED');
INSERT INTO opm.calculation_status(id, deleted, name) VALUES(3, false, 'Status Calculation Saved');
INSERT INTO opm.calculation_status(id, deleted, name) VALUES(4, false, 'Status Calculation');
INSERT INTO opm.calculation_status(id, deleted, name) VALUES(5, false, 'Triggered Pending');

---------------------------------------------------
-----   calculation_result table
---------------------------------------------------
INSERT INTO opm.calculation_result(id, deleted, calculation_status_id, official, apply_to_real_payment, summary_data_id) VALUES(1, false, 1, false, false, NULL);
INSERT INTO opm.calculation_result(id, deleted, calculation_status_id, official, apply_to_real_payment, summary_data_id) VALUES(2, false, 5, true, false, NULL);

---------------------------------------------------
-----   calculation_version table
---------------------------------------------------
INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(1, false, 'calculation version 1', '07/06/2013', 1, 1);
INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(2, false, 'calculation version 2', '10/24/2013', 2, 1);
INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(3, false, 'calculation version 3', '04/08/2013', NULL, 1);
INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(4, false, 'calculation version 4', '12/23/2012', NULL, 1);
INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(5, false, 'calculation version 5', '07/07/2013', NULL, 1);
INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(6, false, 'calculation version 6', '04/12/2013', NULL, 1);
INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(7, false, 'calculation version 7', '03/19/2013', NULL, 1);
INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(8, false, 'calculation version 8', '01/27/2013', NULL, 1);
INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(9, false, 'calculation version 9', '03/15/2013', NULL, 1);
INSERT INTO opm.calculation_version(id, deleted, name, calculation_date, calculation_result_id, account_id) VALUES(10, false, 'calculation version 10', '02/26/2013', NULL, 1);

---------------------------------------------------
-----   calculation_result_item table
---------------------------------------------------
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(1, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 1, '1.11', '1.11', '1.11', '1.11', 1, 1, 1, 'INSERTED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(2, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 1, '2.22', '2.22', '2.22', '2.22', 1, 1, 1, 'INSERTED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(3, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 2, '3.33', '3.33', '3.33', '3.33', 1, 1, 1, 'DELETED', 'CSRS_POST_10_82_DEPOSIT', 1);
INSERT INTO opm.calculation_result_item(id, deleted, start_date, end_date, mid_date, effective_date, period_type_id, deduction_amount, total_interest, payment_applied, balance, calculation_result_id, version, line, status, service_category, retirement_type_id)
VALUES(4, false, '01/01/2014', '02/01/2014', '01/15/2014', '02/02/2014', 2, '4.44', '4.44', '4.44', '4.44', 1, 1, 1, 'UPDATED', 'CSRS_POST_10_82_DEPOSIT', 1);

---------------------------------------------------
-----   calculation table
---------------------------------------------------
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(1, false, '04/15/2012', '04/12/2013', 2, 2, 1, 1, 36917.94, 5, 1, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(2, false, '05/25/2012', '12/10/2013', 1, 7, 2, 1, 31885.32, 1, 2, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(3, false, '02/26/2012', '09/20/2013', 2, 7, 1, 2, 25765.49, 6, 3, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(4, false, '06/09/2012', '12/15/2012', 1, 8, 2, 2, 41274.51, 1, 4, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(5, false, '05/07/2012', '05/01/2013', 2, 1, 1, 1, 24121.13, 1, 5, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(6, false, '05/25/2012', '03/30/2013', 1, 1, 2, 1, 41849.91, 2, 6, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(7, false, '04/20/2012', '09/12/2013', 1, 3, 1, 2, 22016.96, 2, 7, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(8, false, '05/24/2012', '02/11/2013', 2, 8, 2, 2, 20245.12, 2, 8, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(9, false, '06/08/2012', '04/09/2013', 2, 2, 1, 2, 34347.08, 5, 9, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(10, false, '06/04/2012', '05/29/2013', 1, 2, 1, 1, 21279.35, 9, 10, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(11, false, '03/03/2012', '12/29/2012', 1, 7, 2, 1, 42570.15, 4, 1, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(12, false, '04/24/2012', '11/12/2013', 2, 11, 2, 2, 40456.54, 1, 2, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(13, false, '05/02/2012', '03/03/2013', 2, 6, 1, 2, 41469.69, 1, 3, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(14, false, '02/19/2012', '09/02/2013', 1, 9, 2, 2, 36940.14, 1, 4, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(15, false, '03/08/2012', '06/16/2013', 1, 9, 2, 1, 41791.5, 6, 5, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(16, false, '02/20/2012', '09/04/2013', 1, 4, 1, 1, 26156.41, 2, 6, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(17, false, '04/28/2012', '11/12/2013', 1, 2, 1, 2, 33122.73, 4, 7, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(18, false, '03/05/2012', '10/24/2013', 2, 4, 2, 1, 49115.15, 9, 8, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(19, false, '03/21/2012', '09/21/2013', 2, 2, 1, 1, 39246.82, 1, 9, '04/12/2013');
INSERT INTO opm.calculation(id, deleted, begin_date, end_date, retirement_type_id, period_type_id, appointment_type_id, service_type_id, amount, pay_type_id, calculation_version_id, interest_accrual_date) VALUES(20, false, '03/03/2012', '10/09/2013', 2, 11, 2, 2, 46642.67, 4, 10, '04/12/2013');


---------------------------------------------------
-----   deduction_rate table
---------------------------------------------------
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (1, false, 'service type 1', 1, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (2, false, 'service type 1', 1, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (3, false, 'service type 1', 1, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (4, false, 'service type 1', 1, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (5, false, 'service type 2', 2, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (6, false, 'service type 2', 2, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (7, false, 'service type 3', 1, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);
INSERT INTO opm.deduction_rate (id, deleted, service_type, retirement_type_id, start_date, end_date, days_in_period, rate, service_type_description, deduction_conversion_factor)
VALUES (8, false, 'service type 3', 1, '01/01/2014', '02/01/2014', 10, 99.99, 'service type description', 1.99);

---------------------------------------------------
-----   payment_transaction table
---------------------------------------------------
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (1, false, '123', '123', '123', '123', '01/01/2014', '200.00', '01/01/2014', 'ACCEPTED', '01/01/2014', 'key', '123', true, '1', false, true, 1, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (2, false, '123', '123', '123', '123', '01/01/2014', '50.00', '01/01/2014', 'NONE', '01/01/2014', 'key', '123', true, '1', false, true, 1, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (3, false, '123', '123', '123', '123', '01/01/2014', '50.00', '01/01/2014', 'POSTED_PENDING', '01/01/2014', 'key', '123', true, '1', false, true, 1, false, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (4, false, '123', '123', '123', '123', '01/01/2014', '80.00', '01/01/2014', 'POSTED_PENDING', '01/01/2014', 'key', '123', true, '1', true, true, 1, true, true);
INSERT INTO opm.payment_transaction (id, deleted, pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, csd, user_inserted, ach_payment, payment_status_code, resolved_suspense, update_to_completed)
VALUES (5, false, '123', '123', '123', '123', '01/01/2014', '30.00', '01/01/2014', 'REVERSE_PENDING', '01/01/2014', 'key', '123', true, '1', true, true, 1, true, true);

---------------------------------------------------
-----   refund_transaction table
---------------------------------------------------
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (1, false, '79efe83e-6a3a-4cec-9512-b4ab0a55cc9c', 20.00, '1', '01/01/2014', 'user1', 1);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (2, false, 'b74210a7-1157-4d14-a5d3-452345951f18', 20.00, '1', '01/02/2014', 'user1', 3);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (3, false, '55954f24-2512-4507-a667-fab718cc34db', 37952.91, '3', '01/03/2014', 'user2', 4);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (4, false, '78bb2c8d-5eb4-40b1-a5f4-fa5372a7e2f1', 22321.38, '4', '01/04/2014', 'user2', 3);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (5, false, '3e7113ed-7dd5-4722-b48d-14937b06e226', 18680.04, '5', '01/05/2014', 'user3', 1);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (6, false, 'dad3358a-fcb8-4c82-9ea9-27ced4e88018', 35564.68, '6', '01/06/2014', 'user3', 2);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (7, false, '21fc6719-798c-4865-a6b2-ff1dafc396cb', 30258.12, '7', '01/07/2014', 'user4', 2);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (8, false, '25e1f10d-dbab-465e-9f21-43396775ad39', 28329.64, '8', '01/08/2014', 'user4', 3);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (9, false, '04fde94b-80b3-44c4-ba5b-3255f0b0303c', 37769.76, '9', '01/09/2014', 'user5', 1);
INSERT INTO opm.refund_transaction (id, deleted, transaction_key, amount, claim_number, refund_date, refund_username, transfer_type_id) VALUES (10, false, '601b7e07-780b-4aef-8634-0a098c34fd84', 17528.01, '10', '01/10/2014', 'user5', 3);

----------------------------------------------------
-- 			role_permission table
----------------------------------------------------
INSERT INTO opm.role_permission(id, deleted, rolename, action) VALUES(1, false, 'role1', 'action1');
INSERT INTO opm.role_permission(id, deleted, rolename, action) VALUES(2, false, 'role1', 'action2');
INSERT INTO opm.role_permission(id, deleted, rolename, action) VALUES(3, false, 'role2', 'action3');
INSERT INTO opm.role_permission(id, deleted, rolename, action) VALUES(4, false, 'role2', 'action4');
INSERT INTO opm.role_permission(id, deleted, rolename, action) VALUES(5, false, 'role3', 'action5');
INSERT INTO opm.role_permission(id, deleted, rolename, action) VALUES(6, false, 'role3', 'action6');
INSERT INTO opm.role_permission(id, deleted, rolename, action) VALUES(7, false, 'role4', 'action7');
INSERT INTO opm.role_permission(id, deleted, rolename, action) VALUES(8, false, 'role4', 'action8');
INSERT INTO opm.role_permission(id, deleted, rolename, action) VALUES(9, false, 'role5', 'action9');
INSERT INTO opm.role_permission(id, deleted, rolename, action) VALUES(10, false, 'role5', 'action10');

----------------------------------------------------
-- 			user_status table
----------------------------------------------------
INSERT INTO opm.user_status(id, deleted, name) VALUES(1, false, 'Active User');
INSERT INTO opm.user_status(id, deleted, name) VALUES(2, false, 'Suspended User');

----------------------------------------------------
-- 			role table
----------------------------------------------------
INSERT INTO opm.role(id, deleted, name) VALUES(1, false, 'role1');
INSERT INTO opm.role(id, deleted, name) VALUES(2, false, 'role2');
INSERT INTO opm.role(id, deleted, name) VALUES(3, false, 'role3');
INSERT INTO opm.role(id, deleted, name) VALUES(4, false, 'role4');
INSERT INTO opm.role(id, deleted, name) VALUES(5, false, 'role5');
INSERT INTO opm.role(id, deleted, name) VALUES(6, false, 'role6');
INSERT INTO opm.role(id, deleted, name) VALUES(7, false, 'role7');
INSERT INTO opm.role(id, deleted, name) VALUES(8, false, 'role8');
INSERT INTO opm.role(id, deleted, name) VALUES(9, false, 'role9');
INSERT INTO opm.role(id, deleted, name) VALUES(10, false, 'role10');
INSERT INTO opm.role(id, deleted, name) VALUES(11, false, 'role11');
INSERT INTO opm.role(id, deleted, name) VALUES(12, false, 'role12');
INSERT INTO opm.role(id, deleted, name) VALUES(13, false, 'role13');

-------------------------------------------
-----   app_user table
-------------------------------------------
INSERT INTO opm.app_user(id, deleted, first_name, last_name, email, telephone, username, default_tab, role_id, user_status_id, network_id) VALUES(1, false,'Jacqueline','Hill','jhill@quimba.com','046-44-6641','user1','SUSPENSE', 1, 1,'AMAZONA-20SVSGC\\FINANTECH');
INSERT INTO opm.app_user(id, deleted, first_name, last_name, email, telephone, username, default_tab, role_id, user_status_id, network_id) VALUES(2, false,'Ann','Hart','ahart@mymm.net','654-01-8174','user2','NOTIFICATION_LOG', 2, 1,'AMAZONA-20SVSGC\\FACES');
INSERT INTO opm.app_user(id, deleted, first_name, last_name, email, telephone, username, default_tab, role_id, user_status_id, network_id) VALUES(3, false,'Gloria','Baker','gbaker@roombo.biz','327-32-6278','user3','WORK_QUEUE', 3, 1,'AMAZONA-20SVSGC\\INFOTECH');
INSERT INTO opm.app_user(id, deleted, first_name, last_name, email, telephone, username, default_tab, role_id, user_status_id, network_id) VALUES(4, false,'Paul','Frazier','pfrazier@lajo.info','538-51-7454','user4','NOTIFICATION_LOG', 4, 1,'AMAZONA-20SVSGC\\REFUDATAENTRY');
INSERT INTO opm.app_user(id, deleted, first_name, last_name, email, telephone, username, default_tab, role_id, user_status_id, network_id) VALUES(5, false,'Arthur','Palmer','apalmer@tagopia.mil','519-72-1744','user5','SUSPENSE', 5, 1,'AMAZONA-20SVSGC\\RECEIVSUPVIS');
INSERT INTO opm.app_user(id, deleted, first_name, last_name, email, telephone, username, default_tab, role_id, user_status_id, network_id) VALUES(6, false,'Alan','Spencer','aspencer@roodel.com','761-01-2958','user6','WORK_QUEUE', 6, 1,'AMAZONA-20SVSGC\\FINANTECH');
INSERT INTO opm.app_user(id, deleted, first_name, last_name, email, telephone, username, default_tab, role_id, user_status_id, network_id) VALUES(7, false,'Carlos','Franklin','cfranklin@topiczoom.mil','574-86-6140','user7','VIEW_ACCOUNT', 7, 1,'AMAZONA-20SVSGC\\REFUDATAENTRY');
INSERT INTO opm.app_user(id, deleted, first_name, last_name, email, telephone, username, default_tab, role_id, user_status_id, network_id) VALUES(8, false,'Christine','Peters','cpeters@minyx.name','028-18-4879','user8','NOTIFICATION_LOG', 8, 1,'AMAZONA-20SVSGC\\BATCH');
INSERT INTO opm.app_user(id, deleted, first_name, last_name, email, telephone, username, default_tab, role_id, user_status_id, network_id) VALUES(9, false,'Russell','Harrison','rharrison@browseblab.org','690-03-9493','user9','NOTIFICATION_LOG', 9, 1,'AMAZONA-20SVSGC\\FINANTECH');
INSERT INTO opm.app_user(id, deleted, first_name, last_name, email, telephone, username, default_tab, role_id, user_status_id, network_id) VALUES(10, false,'Julia','Gibson','jgibson@skivee.biz','769-36-6645','user10','NOTIFICATION_LOG', 10, 1,'AMAZONA-20SVSGC\\FACES');

-------------------------------------------
-----   audit_record table
-------------------------------------------
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (1, false, 'user1', 'ip', 'action1', '01/01/2014', 'description');
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (2, false, 'user2', 'ip', 'action2', '01/01/2014', 'description');
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (3, false, 'user3', 'ip', 'action3', '01/01/2014', 'description');
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (4, false, 'user4', 'ip', 'action4', '01/01/2014', 'description');
INSERT INTO opm.audit_record (id, deleted, username, ip_address, action, date, description) VALUES (5, false, 'user5', 'ip', 'action5', '01/01/2014', 'description');

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
