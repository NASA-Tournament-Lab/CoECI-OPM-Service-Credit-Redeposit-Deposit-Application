

INSERT INTO LookUpGLPaymentType(PaymentCode, CodeDescription) VALUES('c1', 'Description 1') ;
INSERT INTO LookUpGLPaymentType(PaymentCode, CodeDescription) VALUES('c2', 'Description 2') ;

INSERT INTO LookupRetirementTypeCode(SCMRetirementTypeCode, SCMRetirementTypeDescription, RetireMentTypeDisplayOrder) VALUES(1, 'Description 1', 1) ;
INSERT INTO LookupRetirementTypeCode(SCMRetirementTypeCode, SCMRetirementTypeDescription, RetireMentTypeDisplayOrder) VALUES(2, 'Description 2', 2) ;

INSERT INTO LookUpGLCodes(GLName, GLCode, PaymentType, SCMRetirementTypeCode, PostOffice) VALUES('Name1', 'Code1', 't1', 1, 0) ;
INSERT INTO LookUpGLCodes(GLName, GLCode, PaymentType, SCMRetirementTypeCode, PostOffice) VALUES('Name2', 'Code2', 't2', 2, 1) ;

INSERT INTO LookupInterestRates(InterestYear, InterestRate) VALUES(1, 1) ;
INSERT INTO LookupInterestRates(InterestYear, InterestRate) VALUES(2, 2) ;

INSERT INTO LookupInterestSuppression(SCMClaimNumber, Post982Redeposit, Pre1082Redeposit, Post982Deposit, Pre1082Deposit, FersDeposit) VALUES('Number1', 0, 0, 0, 0, 0) ;
INSERT INTO LookupInterestSuppression(SCMClaimNumber, Post982Redeposit, Pre1082Redeposit, Post982Deposit, Pre1082Deposit, FersDeposit) VALUES('Number2', 1, 1, 1, 1, 1) ;

INSERT INTO LookUpDeductionRates(SCMServiceTypeCode, SCMRetirementTypeCode, StartDate, EndDate, DaysInPeriod, Rate, ServiceTypeDescription, DeductionConversionFactor) VALUES('t1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 'Description 1', 1) ;
INSERT INTO LookUpDeductionRates(SCMServiceTypeCode, SCMRetirementTypeCode, StartDate, EndDate, DaysInPeriod, Rate, ServiceTypeDescription, DeductionConversionFactor) VALUES('t2', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 2, 'Description 2', 2) ;


INSERT INTO LookupSCMPayCode(SCMPayCode, SCMPayCodeDescription) VALUES('A', 'Description 1') ;
INSERT INTO LookupSCMPayCode(SCMPayCode, SCMPayCodeDescription) VALUES('B', 'Description 2') ;

INSERT INTO LookUpPaymentAppliedOrder(LookUpPaymentAppliedOrderCode, PaymentAccount, PaymentAppliedDisplayOrder) VALUES(1, 'PaymentAccount1', 1) ;
INSERT INTO LookUpPaymentAppliedOrder(LookUpPaymentAppliedOrderCode, PaymentAccount, PaymentAppliedDisplayOrder) VALUES(2, 'PaymentAccount2', 2) ;

INSERT INTO LookupAgencyCode(SCMAgencyCode, SCMAgencyDescription, AgencyCodeDisplayOrder) VALUES(1, 'Description 1', 1) ;
INSERT INTO LookupAgencyCode(SCMAgencyCode, SCMAgencyDescription, AgencyCodeDisplayOrder) VALUES(2, 'Description 2', 2) ;

INSERT INTO LookupInterestGracePeriod(SCMClaimNumber, Post982Redeposit, Pre1082Redeposit, Post982Deposit, Pre1082Deposit, FersDeposit) VALUES('Number1', 0, 0, 0, 0, 0) ;
INSERT INTO LookupInterestGracePeriod(SCMClaimNumber, Post982Redeposit, Pre1082Redeposit, Post982Deposit, Pre1082Deposit, FersDeposit) VALUES('Number2', 1, 1, 1, 1, 1) ;

INSERT INTO LookupPayTransStatusCode(PayTransStatusCode, PayTransStatusDescription, PayTransDisplayCategory, PayTransDisplayOrder, NextStateLink, BatchProcessingOrder, FinalState, NeedsApproval, ShowOnSuspense, IncludeInBalance, NightlyBatch, Deletable, Reversable, ManualEntered, SuspenseAction, CanHitGL, ReversingType, BalancedScorecard, SendToDBTS) VALUES(1, 'Description 1', 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) ;
INSERT INTO LookupPayTransStatusCode(PayTransStatusCode, PayTransStatusDescription, PayTransDisplayCategory, PayTransDisplayOrder, NextStateLink, BatchProcessingOrder, FinalState, NeedsApproval, ShowOnSuspense, IncludeInBalance, NightlyBatch, Deletable, Reversable, ManualEntered, SuspenseAction, CanHitGL, ReversingType, BalancedScorecard, SendToDBTS) VALUES(2, 'Description 2', 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1) ;

INSERT INTO LookupChangeTransFieldNumberCode(ChangeTransFieldNumberCode, ChangeTransFieldNumberDescription) VALUES('c1', 'Description 1') ;
INSERT INTO LookupChangeTransFieldNumberCode(ChangeTransFieldNumberCode, ChangeTransFieldNumberDescription) VALUES('c2', 'Description 2') ;

INSERT INTO LookupUserStatusCode(UserStatusCode, UserStatusDescription) VALUES(1, 'Description 1') ;
INSERT INTO LookupUserStatusCode(UserStatusCode, UserStatusDescription) VALUES(2, 'Description 2') ;

INSERT INTO LookupAccountStatus(AccountStatus, AccountStatusDescription, AcountStatusCategory, AccountStatusDisplayOrder) VALUES(1, 'Description 1', 1, 1) ;
INSERT INTO LookupAccountStatus(AccountStatus, AccountStatusDescription, AcountStatusCategory, AccountStatusDisplayOrder) VALUES(2, 'Description 2', 2, 2) ;

INSERT INTO LookupServiceTypeCode(SCMServiceTypeCode, SCMServiceTypeDescription, FERSDepositAllowedAfter88, ServiceTypeDisplayOrder) VALUES('1', 'Description 1', 1, 1) ;
INSERT INTO LookupServiceTypeCode(SCMServiceTypeCode, SCMServiceTypeDescription, FERSDepositAllowedAfter88, ServiceTypeDisplayOrder) VALUES('2', 'Description 2', 2, 2) ;

INSERT INTO LookupPayTypeCode(PayTypeCode, PayTypeDescription, PayTypeDisplayOrder) VALUES('A', 'Description 1', 1) ;
INSERT INTO LookupPayTypeCode(PayTypeCode, PayTypeDescription, PayTypeDisplayOrder) VALUES('B', 'Description 2', 2) ;

INSERT INTO LookupAppointmentTypeCode(SCMAppointmentTypeCode, SCMAppointmentTypeDescription, AppoinmentTypeDisplayOrder, AppointmentTypeCategory) VALUES('A', 'Description 1', 1, 'A') ;
INSERT INTO LookupAppointmentTypeCode(SCMAppointmentTypeCode, SCMAppointmentTypeDescription, AppoinmentTypeDisplayOrder, AppointmentTypeCategory) VALUES('B', 'Description 2', 2, 'B') ;

INSERT INTO LookupPeriodTypeCode(SCMPeriodTypeCode, SCMPeriodTypeDescription, PeriodTypeDisplayOrder) VALUES('D', 'Description 1', 1) ;
INSERT INTO LookupPeriodTypeCode(SCMPeriodTypeCode, SCMPeriodTypeDescription, PeriodTypeDisplayOrder) VALUES('R', 'Description 2', 2) ;

INSERT INTO AnnuitantList(SCMClaimNumber) VALUES('Number1') ;
INSERT INTO AnnuitantList(SCMClaimNumber) VALUES('Number2') ;

INSERT INTO USStates(StateID, State, Abbreviation) VALUES(1, 'State 1', 's1') ;
INSERT INTO USStates(StateID, State, Abbreviation) VALUES(2, 'State 2', 's2') ;

INSERT INTO NewClaimNumbers(SCMClaimNumber) VALUES('Number1') ;
INSERT INTO NewClaimNumbers(SCMClaimNumber) VALUES('Number2') ;

INSERT INTO NameSuffix(SuffixID, Suffix) VALUES(1, 's1') ;
INSERT INTO NameSuffix(SuffixID, Suffix) VALUES(2, 's2') ;

INSERT INTO Holidays(Holiday, ExactDate, WeekDay, MonthNumber, DayOfMonth, WeekOfMonth) VALUES('Holiday 1', 0, 1, 1, 1, 1) ;
INSERT INTO Holidays(Holiday, ExactDate, WeekDay, MonthNumber, DayOfMonth, WeekOfMonth) VALUES('Holiday 2', 1, 2, 2, 2, 2) ;

INSERT INTO ClaimsWithoutService(SCMClaimNumber, SCMDateOfBirth) VALUES('Number1', CURRENT_TIMESTAMP) ;
INSERT INTO ClaimsWithoutService(SCMClaimNumber, SCMDateOfBirth) VALUES('Number2', CURRENT_TIMESTAMP) ;

INSERT INTO SCMFirstInsert(SCMClaimNumber, scmlastact) VALUES('Number1', CURRENT_TIMESTAMP) ;
INSERT INTO SCMFirstInsert(SCMClaimNumber, scmlastact) VALUES('Number2', CURRENT_TIMESTAMP) ;

INSERT INTO A01_PrintSuppressionCases(SCMClaimNumber, ReasonForPrintSuppression) VALUES('Number1', 1) ;
INSERT INTO A01_PrintSuppressionCases(SCMClaimNumber, ReasonForPrintSuppression) VALUES('Number2', 2) ;

INSERT INTO TimeFactor(NumDays, NumMonths, TimeFactor) VALUES(1, 1, 1) ;
INSERT INTO TimeFactor(NumDays, NumMonths, TimeFactor) VALUES(2, 2, 2) ;

INSERT INTO ContactInfo(ContactName, ContactText) VALUES('Name 1', 'Text 1') ;
INSERT INTO ContactInfo(ContactName, ContactText) VALUES('Name 2', 'Text 2') ;

INSERT INTO LookupUserRoleCode(UserRoleCode, UserRoleName, UserRoleDescription, AddAccount) VALUES(1, 'RoleName1', 'Description 1', 1) ;
INSERT INTO LookupUserRoleCode(UserRoleCode, UserRoleName, UserRoleDescription, AddOverTheCounterPayments) VALUES(2, 'RoleName2', 'Description 2', 1) ;

INSERT INTO Users(UserNetworkID, UserID, UserPassword, UserStatusCode, UserFirstName, UserLastName, UserPhoneNumber, UserEmail) VALUES('NetworkID1', 'UserID1', 'Password1', 1, 'FirstName1', 'LastName1', 'PhoneNumber1', 'Email1') ;
INSERT INTO Users(UserNetworkID, UserID, UserPassword, UserStatusCode, UserFirstName, UserLastName, UserPhoneNumber, UserEmail) VALUES('NetworkID2', 'UserID2', 'Password2', 2, 'FirstName2', 'LastName2', 'PhoneNumber2', 'Email2') ;

UPDATE USERS SET UserSupervisorKey = (SELECT UserKey FROM Users WHERE UserID='UserID1') WHERE UserID='UserID2';

INSERT INTO UserRoleAssignment(UserKey, UserRoleCode) SELECT UserKey, 1 FROM Users WHERE UserID='UserID1';
INSERT INTO UserRoleAssignment(UserKey, UserRoleCode) SELECT UserKey, 2 FROM Users WHERE UserID='UserID2';

INSERT INTO AuditBatch(EventYear, EventMonth, EventDay, FileReceived, DailyAction, ManualBatch, ErrorImporting, ErrorProcessing, LatestBatch, AmountImported, AmountProcessed, NumberACHAccepted, NumberACHUnresolved, NumberACHSuspended, NumberAccepted, NumberUnresolved, NumberSuspended, NumberChangeRequests, ErrorCountImporting, PaymentsProcessed, InitialBillsProcessed, ReversedProcessed, ACHStopLetters, RefundMemos, ErrorCountProcessing, UserKey, BatchTime) VALUES(1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, CURRENT_TIMESTAMP) ;
INSERT INTO AuditBatch(EventYear, EventMonth, EventDay, FileReceived, DailyAction, ManualBatch, ErrorImporting, ErrorProcessing, LatestBatch, AmountImported, AmountProcessed, NumberACHAccepted, NumberACHUnresolved, NumberACHSuspended, NumberAccepted, NumberUnresolved, NumberSuspended, NumberChangeRequests, ErrorCountImporting, PaymentsProcessed, InitialBillsProcessed, ReversedProcessed, ACHStopLetters, RefundMemos, ErrorCountProcessing, UserKey, BatchTime) VALUES(2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, CURRENT_TIMESTAMP) ;

INSERT INTO BatchDailyPayments(AuditBatchIDLog, PayTransactionKey, NumberPaymentsToday, BatchDate, AccountStatus, PayTransStatusCode, SCMClaimNumber, AccountBalance, OverPaymentAmount, ACHPayment, ACHStopLetter, PrintInvoice, RefundRequired, ReversedPayment, UpdateToCompleted, PrintInitialBill, LatestBatch, ErrorProcessing) SELECT IDLog, 1, 1, CURRENT_TIMESTAMP, 1, 1, 'Number1', 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 FROM AuditBatch WHERE UserKey=1;
INSERT INTO BatchDailyPayments(AuditBatchIDLog, PayTransactionKey, NumberPaymentsToday, BatchDate, AccountStatus, PayTransStatusCode, SCMClaimNumber, AccountBalance, OverPaymentAmount, ACHPayment, ACHStopLetter, PrintInvoice, RefundRequired, ReversedPayment, UpdateToCompleted, PrintInitialBill, LatestBatch, ErrorProcessing) SELECT IDLog, 2, 2, CURRENT_TIMESTAMP, 2, 2, 'Number2', 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1 FROM AuditBatch WHERE UserKey=2;

INSERT INTO ServiceCreditMaster(SCMClaimNumber, SCMSSN, SCMDateOfBirth, SCMFName, SCMMInitial, SCMLastName, SCMSuffix, SCMName, SCMAddress1, SCMAddress2, SCMAddress3, SCMAddress4, SCMAddress5, SCMCity, SCMState, SCMZipcode, SCMTelephone, SCMPosition, SCMAgencyCode, SCMJobLocationCity, SCMJobLocationState, SCMDeposit, SCMRedeposit, SCMTotVarRedeposit, SCMNonDed, SCMFersW, SCMAccIntDep, SCMAccIntRdep, SCMAccIntNonDed, SCMAccIntVarRdep, SCMAccIntFers, SCMTotPayd, SCMTotPayr, SCMTotPayn, SCMTotPayvr, SCMTotPayfers, SCMCompDate, SCMVarIntCompDate, SCMLastAct, SCMLastPay, SCMPayCode, SCMTimePer, SCMAddServ, SCMNoInterest, SCMCode20Date, SCMFlagPreRedep, SCMFlagPostRedep, SCMPriorClaimNumber, AccountStatus, PaymentOrder, NewClaimNumber, StopACHPayment, DBTSAccount) VALUES('Number1', 'SSN1', CURRENT_TIMESTAMP, 'FName1', '1', 'LastName1', 's1', 'Name1', 'Address11', 'Address12', 'Address13', 'Address14', 'Address15', 'City1', 's1', 'Zipcode1', 'Telephone1', 'Position1', 'a1', 'JobLocationCity1', 's1', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A', 1, 'A', 0, CURRENT_TIMESTAMP, '0', '0', 'Number1', 1, 'Order1', 1, 0, 0) ;
INSERT INTO ServiceCreditMaster(SCMClaimNumber, SCMSSN, SCMDateOfBirth, SCMFName, SCMMInitial, SCMLastName, SCMSuffix, SCMName, SCMAddress1, SCMAddress2, SCMAddress3, SCMAddress4, SCMAddress5, SCMCity, SCMState, SCMZipcode, SCMTelephone, SCMPosition, SCMAgencyCode, SCMJobLocationCity, SCMJobLocationState, SCMDeposit, SCMRedeposit, SCMTotVarRedeposit, SCMNonDed, SCMFersW, SCMAccIntDep, SCMAccIntRdep, SCMAccIntNonDed, SCMAccIntVarRdep, SCMAccIntFers, SCMTotPayd, SCMTotPayr, SCMTotPayn, SCMTotPayvr, SCMTotPayfers, SCMCompDate, SCMVarIntCompDate, SCMLastAct, SCMLastPay, SCMPayCode, SCMTimePer, SCMAddServ, SCMNoInterest, SCMCode20Date, SCMFlagPreRedep, SCMFlagPostRedep, SCMPriorClaimNumber, AccountStatus, PaymentOrder, NewClaimNumber, StopACHPayment, DBTSAccount) VALUES('Number2', 'SSN2', CURRENT_TIMESTAMP, 'FName2', '2', 'LastName2', 's2', 'Name2', 'Address21', 'Address22', 'Address23', 'Address24', 'Address25', 'City2', 's2', 'Zipcode2', 'Telephone2', 'Position2', 'a2', 'JobLocationCity2', 's2', 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'B', 2, 'B', 1, CURRENT_TIMESTAMP, '1', '1', 'Number2', 2, 'Order2', 2, 1, 1) ;

INSERT INTO ServicePeriods(SCMClaimNumber, SCMVersion, SCMLineNumber, SCMBeginDate, SCMEndDate, SCMRetirementTypeCode, SCMAgencyCode, SCMServiceTypeCode, SCMAppointmentTypeCode, SCMEnteredAmount, SCMHoursInYear, SCMAnnualizedAmount, SCMPeriodType, SCMDateEntered, SCMEnteredBy, SCMPayTypeCode) VALUES('Number1', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, '1', 'A', 1, 1, 1, 'D', CURRENT_TIMESTAMP, 1, 'A') ;
INSERT INTO ServicePeriods(SCMClaimNumber, SCMVersion, SCMLineNumber, SCMBeginDate, SCMEndDate, SCMRetirementTypeCode, SCMAgencyCode, SCMServiceTypeCode, SCMAppointmentTypeCode, SCMEnteredAmount, SCMHoursInYear, SCMAnnualizedAmount, SCMPeriodType, SCMDateEntered, SCMEnteredBy, SCMPayTypeCode) VALUES('Number2', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 2, '2', 'B', 2, 2, 2, 'R', CURRENT_TIMESTAMP, 2, 'B') ;

INSERT INTO CalculatedServicePeriods(SCMClaimNumber, Version, LineNumber, SCMRetirementTypeCode, SCMPeriodTypeCode, SCMEnteredBy, LastUpdate, ServicePeriodStartDate, ServicePeriodEndDate, ServicePeriodMidpoint, ServicePeriodEffectiveDate, DeductionAmount, InterestAmount, PaymentsApplied, TotalBalance, IsOfficial, PaymentOrder, InterestAccrualDate, CalculationStatus) VALUES('Number1', 3, 3, 1, 'D', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 1, 1, 0, 1, CURRENT_TIMESTAMP, 1) ;
INSERT INTO CalculatedServicePeriods(SCMClaimNumber, Version, LineNumber, SCMRetirementTypeCode, SCMPeriodTypeCode, SCMEnteredBy, LastUpdate, ServicePeriodStartDate, ServicePeriodEndDate, ServicePeriodMidpoint, ServicePeriodEffectiveDate, DeductionAmount, InterestAmount, PaymentsApplied, TotalBalance, IsOfficial, PaymentOrder, InterestAccrualDate, CalculationStatus) VALUES('Number2', 4, 4, 2, 'R', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 2, 2, 2, 1, 2, CURRENT_TIMESTAMP, 2) ;

INSERT INTO AccountNote(SCMClaimNumber, AccountNoteText, AccountNoteDate, AccountNotePriority, AccountNoteAuthorKey) SELECT 'Number1', 'Text 1', CURRENT_TIMESTAMP, 1, UserKey FROM Users WHERE UserID='UserID1';
INSERT INTO AccountNote(SCMClaimNumber, AccountNoteText, AccountNoteDate, AccountNotePriority, AccountNoteAuthorKey) SELECT 'Number2', 'Text 2', CURRENT_TIMESTAMP, 2, UserKey FROM Users WHERE UserID='UserID2';

INSERT INTO UserAccountAssignments(SCMClaimNumber, UserKey, AssignmentDate) SELECT 'Number1', UserKey, CURRENT_TIMESTAMP FROM Users WHERE UserID='UserID1';
INSERT INTO UserAccountAssignments(SCMClaimNumber, UserKey, AssignmentDate) SELECT 'Number2', UserKey, CURRENT_TIMESTAMP FROM Users WHERE UserID='UserID2';

INSERT INTO PaymentTransaction(PayTransBatchNumber, PayTransBlockNumber, PayTransSequenceNumber, SCMClaimnumber, SCMDateOfBirth, PayTransPaymentAmount, PayTransTransactionDate, PayTransStatusCode, PayTransStatusDate, TechnicianUserKey, PaymentAppliedOrderCode, PostFlag, UserInserted, ACHPayment, ResolvedSuspense, HistoryPayment, Disapprove, GovRefund, Apply2GL) SELECT 'BN1', 'B1', 'S1', 'Number1', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, UserKey, 1, 0, 0, 0, 0, 0, 0, 0, 0 FROM Users WHERE UserID='UserID1';
INSERT INTO PaymentTransaction(PayTransBatchNumber, PayTransBlockNumber, PayTransSequenceNumber, SCMClaimnumber, SCMDateOfBirth, PayTransPaymentAmount, PayTransTransactionDate, PayTransStatusCode, PayTransStatusDate, TechnicianUserKey, PaymentAppliedOrderCode, PostFlag, UserInserted, ACHPayment, ResolvedSuspense, HistoryPayment, Disapprove, GovRefund, Apply2GL) SELECT 'BN2', 'B2', 'S2', 'Number2', CURRENT_TIMESTAMP, 2, CURRENT_TIMESTAMP, 2, CURRENT_TIMESTAMP, UserKey, 2, 1, 1, 1, 1, 1, 1, 1, 1 FROM Users WHERE UserID='UserID2';

INSERT INTO PaymentTranactionNotes(PayTransactionKey, Note) SELECT PayTransactionKey, 'Note 1' FROM PaymentTransaction WHERE SCMClaimnumber='Number1';
INSERT INTO PaymentTranactionNotes(PayTransactionKey, Note) SELECT PayTransactionKey, 'Note 2' FROM PaymentTransaction WHERE SCMClaimnumber='Number2';

INSERT INTO RefundTransaction(RefundAmount, SCMClaimnumber, RefundTransactionDate, TechnicianUserKey, PayTransactionKey) SELECT 1, 'Number1', CURRENT_TIMESTAMP, u.UserKey, p.PayTransactionKey FROM Users u, PaymentTransaction p WHERE u.UserID='UserID1' AND p.SCMClaimnumber='Number1';
INSERT INTO RefundTransaction(RefundAmount, SCMClaimnumber, RefundTransactionDate, TechnicianUserKey, PayTransactionKey) SELECT 2, 'Number2', CURRENT_TIMESTAMP, u.UserKey, p.PayTransactionKey FROM Users u, PaymentTransaction p WHERE u.UserID='UserID2' AND p.SCMClaimnumber='Number2';

INSERT INTO PaymentRefundLinks(PaymentNeedingRefund, RefundForPayment) SELECT PayTransactionKey, PayTransactionKey FROM PaymentTransaction WHERE SCMClaimnumber='Number1';
INSERT INTO PaymentRefundLinks(PaymentNeedingRefund, RefundForPayment) SELECT PayTransactionKey, PayTransactionKey FROM PaymentTransaction WHERE SCMClaimnumber='Number2';

INSERT INTO Invoices(PayTransactionKey, SCMDeposit, SCMRedeposit, SCMTotVarRedeposit, SCMNonDed, SCMFersW, SCMAccIntDep, SCMAccIntRdep, SCMAccIntNonDed, SCMAccIntVarRdep, SCMAccIntFers, SCMTotPayd, SCMTotPayr, SCMTotPayn, SCMTotPayvr, SCMTotPayfers, LastPay, CalcDate, NextInvoiceID) SELECT PayTransactionKey, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1 FROM PaymentTransaction WHERE SCMClaimnumber='Number1';
INSERT INTO Invoices(PayTransactionKey, SCMDeposit, SCMRedeposit, SCMTotVarRedeposit, SCMNonDed, SCMFersW, SCMAccIntDep, SCMAccIntRdep, SCMAccIntNonDed, SCMAccIntVarRdep, SCMAccIntFers, SCMTotPayd, SCMTotPayr, SCMTotPayn, SCMTotPayvr, SCMTotPayfers, LastPay, CalcDate, NextInvoiceID) SELECT PayTransactionKey, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2 FROM PaymentTransaction WHERE SCMClaimnumber='Number2';

INSERT INTO PaymentInterestDetails(PayTransactionKey, AccountType, NumWholeYears, CalculatedInterest, LastPayToEOYFactor, PartialToThisPayFactor, ThisInterestRate, LastPaymentDate, TransactionDate, ComputedDate, Post, GUI, LastPaymentWasThisYear) SELECT PayTransactionKey, 1, 1, 1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 0, 0 FROM PaymentTransaction WHERE SCMClaimnumber='Number1';
INSERT INTO PaymentInterestDetails(PayTransactionKey, AccountType, NumWholeYears, CalculatedInterest, LastPayToEOYFactor, PartialToThisPayFactor, ThisInterestRate, LastPaymentDate, TransactionDate, ComputedDate, Post, GUI, LastPaymentWasThisYear) SELECT PayTransactionKey, 2, 2, 2, 2, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 1 FROM PaymentTransaction WHERE SCMClaimnumber='Number2';

INSERT INTO AdjustmentTransaction(SCMClaimnumber, SCMAccIntDep, SCMAccIntRdep, SCMAccIntNonDed, SCMAccIntVarRdep, SCMAccIntFers, ModificationDate, ApprovalDate, ProcessedDate, TechnicianUserKey, ManagerUserKey, Approved, Disapproved, Modified, Note) SELECT 'Number1', 1, 1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, UserKey, UserKey, 0, 0, 0, 'Note 1' FROM Users WHERE UserID='UserID1';
INSERT INTO AdjustmentTransaction(SCMClaimnumber, SCMAccIntDep, SCMAccIntRdep, SCMAccIntNonDed, SCMAccIntVarRdep, SCMAccIntFers, ModificationDate, ApprovalDate, ProcessedDate, TechnicianUserKey, ManagerUserKey, Approved, Disapproved, Modified, Note) SELECT 'Number2', 2, 2, 2, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, UserKey, UserKey, 1, 1, 1, 'Note 2' FROM Users WHERE UserID='UserID2';

INSERT INTO PaymentMoveTransaction(SCMClaimnumber, SCMTotPayd, SCMTotPayr, SCMTotPayn, SCMTotPayvr, SCMTotPayfers, ModificationDate, ApprovalDate, ProcessedDate, TechnicianUserKey, ManagerUserKey, Approved, Disapproved, Modified, Note) SELECT 'Number1', 1, 1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, UserKey, UserKey, 0, 0, 0, 'Note 1' FROM Users WHERE UserID='UserID1';
INSERT INTO PaymentMoveTransaction(SCMClaimnumber, SCMTotPayd, SCMTotPayr, SCMTotPayn, SCMTotPayvr, SCMTotPayfers, ModificationDate, ApprovalDate, ProcessedDate, TechnicianUserKey, ManagerUserKey, Approved, Disapproved, Modified, Note) SELECT 'Number2', 2, 2, 2, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, UserKey, UserKey, 1, 1, 1, 'Note 2' FROM Users WHERE UserID='UserID2';














