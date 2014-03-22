-- ======================================================
-- Stored Procedure: OPM.BatchMainframeImportInsert
--
-- Description: this store procedure will perform batch main frame import insert operation.
--
-- IN parameters :
-- @pRecordString - the record string. 
-- @pImportDate - the import date. 
-- @pACHFlag - the payment ach flag. 
-- @pFileName - the import file name. 
-- @pAuditBatchId - the audit batch id. 
--
-- Returns:
--   the mainframe_import row inserted, or the existing not-processed duplicate one.
--
-- Author: liuliquan
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.BatchMainframeImportInsert(
    pRecordString VARCHAR (120),
    pImportDate TIMESTAMP,
    pACHFlag BOOLEAN, 
    pFileName VARCHAR (50),
    pAuditBatchId BIGINT)
    RETURNS SETOF opm.mainframe_import AS
$BODY$
DECLARE
    l_MainFrameImportId BIGINT = null;
    l_ProcessingFlag BOOLEAN = null;
BEGIN
    -- Check for duplicates
    SELECT id, processing_flag INTO l_MainFrameImportId, l_ProcessingFlag
    FROM opm.mainframe_import
    WHERE audit_batch_id = pAuditBatchId
      AND record_string = pRecordString
      AND deleted=false;
    
    IF l_MainFrameImportId is null THEN -- Not exist yet, insert it
            INSERT INTO opm.mainframe_import(
                deleted, audit_batch_id, import_date, record_string, 
                file_name, ach_flag, processing_flag, error_flag,
                suspended_flag, unresolved_flag, postedPending_flag, ach_status_checked,
                pay_transaction_key) 
            VALUES (false, pAuditBatchId, pImportDate, pRecordString, 
                pFileName, pACHFlag, true, false, 
                false, false, false, true,
                null)
            RETURNING id INTO l_MainFrameImportId;
    ELSE
        IF l_ProcessingFlag = false THEN -- Exist and already processed
            l_MainFrameImportId = null;
        END IF;
    END IF;

    IF l_MainFrameImportId is null THEN
        return;
    END IF;

    return QUERY SELECT *
    FROM opm.mainframe_import
    WHERE id = l_MainFrameImportId;

END;
$BODY$
LANGUAGE plpgsql;


-- ======================================================
-- Stored Procedure: OPM.ClaimantName
--
-- Description: this store procedure will get the claimant name.
--
-- IN parameters :
-- @pSCMClaimNumber - the claim number.
--
-- Returns:
--   The claimant name.
--
-- Author: faeton, zaixiang
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.ClaimantName(pSCMClaimNumber VARCHAR(7))
 RETURNS VARCHAR(100) AS
$BODY$
DECLARE
    l_SCMFName VARCHAR(40);
    l_SCMMInitial VARCHAR(1);
    l_SCMLastname VARCHAR(40);
    l_SCMSuffix VARCHAR(3);
    l_SCMName VARCHAR(40);
    l_ClaimantName VARCHAR(100);
BEGIN
    SELECT COALESCE(ah.first_name, ''), UPPER (COALESCE(ah.middle_initial, '')),
        COALESCE(ah.last_name, ''), UPPER (COALESCE(su.name, '')) 
    INTO l_SCMFName,l_SCMMInitial, l_SCMLastname, l_SCMSuffix
    FROM opm.account a 
    INNER JOIN opm.account_holder ah ON a.account_holder_id=ah.id AND ah.deleted=false
    INNER JOIN opm.suffix su ON ah.suffix_id=su.id AND su.deleted=false
    WHERE a.claim_number = pSCMClaimNumber AND a.deleted=false;
    
    l_SCMName = l_SCMFName || ' ' || l_SCMLastname;
    l_SCMFName = UPPER(substr(l_SCMFName, 1, 1)) || substr (l_SCMFName, 2, 40);
    l_SCMLastname = UPPER(substr(l_SCMLastname, 1, 1)) || substr (l_SCMLastname, 2, 40);

    IF char_length(l_SCMFName) = 0 AND char_length(l_SCMName) > char_length(l_SCMLastname) THEN
        l_ClaimantName = l_SCMName;
    ELSE
        l_ClaimantName = LTRIM(l_SCMFName) || ' ' || LTRIM(l_SCMMInitial)  || ' ' ||
            LTRIM(l_SCMLastname) || ' ' || LTRIM (l_SCMSuffix)|| ' ';
    END IF;

    RETURN COALESCE(l_ClaimantName, '');
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.GetPayCodeId
--
-- Description: this store procedure will get the pay code id.
--
-- IN parameters :
-- @pName - the pay code name.
--
-- Out parameters :
-- @value - the pay code id.
--
-- Author: faeton, TCSASSEMBLER
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
CREATE OR REPLACE FUNCTION OPM.GetPayCodeId(pName VARCHAR (128), out value BIGINT)
   AS
$BODY$
BEGIN
    select id into value from opm.pay_code where name=pName AND deleted=false;
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.GetTotalInterest
--
-- Description: this store procedure will get the total interest from the corresponding table.
--
-- IN parameters :
-- @pSCMClaimNumber - the claim number.
-- @pLabel - the label.
-- @tableName - the table name.
-- @columnName - the column name.
--
-- Out parameters :
-- @val - the total interest.
--
-- Author: faeton, zaixiang
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.GetTotalInterest(
    pSCMClaimNumber VARCHAR (7),
    pLabel VARCHAR(128), 
    tableName VARCHAR(128),
    columnName VARCHAR(128),
    OUT val DECIMAL(10,2))
    RETURNS DECIMAL(10,2) AS
$BODY$
DECLARE
    str VARCHAR(500); 
BEGIN
    str = 'select sum(rede.' || columnName || ') from opm.account acc ' ||
        'INNER JOIN opm.calculation_version ver ON ver.deleted=false AND ver.account_id = acc.id ' ||
        'INNER JOIN ' || tableName || ' rede ' ||  'ON rede.calculation_result_id = ver.calculation_result_id  AND rede.deleted=false ' ||
        E'where rede.label=\'' || pLabel || E'\' and acc.claim_number=\'' || pSCMClaimNumber || E'\' AND acc.deleted=false';
        
    EXECUTE str INTO val;
    val = coalesce(val, 0);
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.GetBalance
--
-- Description: this store procedure will get the balance from the corresponding table.
--
-- IN parameters :
-- @pSCMClaimNumber - the claim number.
--
-- Out parameters :
-- @val - the balance.
--
-- Author: faeton, zaixiang
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.GetBalance(
    pSCMClaimNumber VARCHAR (7),
    OUT val DECIMAL(10,2))
    RETURNS DECIMAL(10,2) AS
$BODY$
DECLARE
    l_SCMAccIntDep DECIMAL(10,2) = 0;
    l_SCMAccIntRdep DECIMAL(10,2) = 0;
    l_SCMAccIntNonDed DECIMAL(10,2) = 0;
    l_SCMAccIntVarRdep DECIMAL(10,2) = 0;
    l_SCMAccIntFers DECIMAL(10,2) = 0;
    l_SCMTotPayvr DECIMAL(10,2) = 0;
    l_SCMTotPayr DECIMAL(10,2) = 0;
    l_SCMTotPayn DECIMAL(10,2) = 0;
    l_SCMTotPayd DECIMAL(10,2) = 0;
    l_SCMTotPayfers DECIMAL(10,2) = 0;
BEGIN
    l_SCMAccIntVarRdep = OPM.GetTotalInterest(pSCMClaimNumber,'POST_10_82', 'opm.redeposit', 'interest');
    l_SCMAccIntRdep = OPM.GetTotalInterest(pSCMClaimNumber,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'interest');
    l_SCMAccIntNonDed = OPM.GetTotalInterest(pSCMClaimNumber,'POST_10_82', 'opm.dedeposit', 'interest');
    l_SCMAccIntDep = OPM.GetTotalInterest(pSCMClaimNumber,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'interest');
    l_SCMAccIntFers = l_SCMAccIntVarRdep + l_SCMAccIntRdep + l_SCMAccIntNonDed + l_SCMAccIntDep;
    
    l_SCMTotPayvr = OPM.GetTotalInterest(pSCMClaimNumber,'POST_10_82', 'opm.redeposit', 'total');
    l_SCMTotPayr = OPM.GetTotalInterest(pSCMClaimNumber,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'total');
    l_SCMTotPayn = OPM.GetTotalInterest(pSCMClaimNumber,'POST_10_82', 'opm.dedeposit', 'total');
    l_SCMTotPayd = OPM.GetTotalInterest(pSCMClaimNumber,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'total');
    l_SCMTotPayfers = l_SCMTotPayvr + l_SCMTotPayr + l_SCMTotPayn + l_SCMTotPayd;
    
    SELECT (coalesce(S.total_deposit,0) + coalesce(S.total_redeposit,0) + coalesce(S.total_var_redeposit,0) + coalesce(S.total_non_deposit,0) + coalesce(S.total_fers_w,0) +
        l_SCMAccIntDep + l_SCMAccIntRdep + l_SCMAccIntNonDed + l_SCMAccIntVarRdep + l_SCMAccIntFers - l_SCMTotPayfers -
        l_SCMTotPayvr - l_SCMTotPayn - l_SCMTotPayr - l_SCMTotPayd) into val
    FROM opm.account S WHERE claim_number=pSCMClaimNumber AND deleted=false;
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.JulianDate
--
-- Description: this store procedure will get the julian date.
--
-- IN parameters :
-- @pDate2Convert - the date to convert.
-- @pNumCharacters - the number of characters to return.
--
-- Returns:
--   The julian date.
--
-- Author: faeton, TCSASSEMBLER
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
CREATE OR REPLACE FUNCTION OPM.JulianDate(pDate2Convert TIMESTAMP, pNumCharacters INTEGER)
 RETURNS VARCHAR(7) AS
$BODY$
DECLARE
    l_Datestring VARCHAR (20);
    l_DaysInYear INTEGER;
    l_Year INTEGER;
BEGIN
    l_Year = to_number(to_char(pDate2Convert, 'YYYY'), '0000');
        
  -- Get the last day of last year
  l_Datestring = '12/31/' || to_char(l_Year - 1, '0000');
  
  -- How many days since then?
  select EXTRACT(DAY FROM (pDate2Convert - to_timestamp(l_Datestring, 'MM DD YYYY'))) into l_DaysInYear;
  
  -- Add the year to the number
  l_Datestring = to_char(l_Year * 1000 + l_DaysInYear, '0000000');
  
  -- Return the number of characters requested
  return substr(l_Datestring, char_length(l_Datestring) - pNumCharacters + 1, pNumCharacters);
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.IsThisHoliday
--
-- Description: this store procedure will check if the date is holiday.
--
-- IN parameters :
-- @pDate2Test - the date to test.
--
-- Returns:
--   True if it is hoday, false otherwise.
--
-- Author: faeton, TCSASSEMBLER
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
CREATE OR REPLACE FUNCTION OPM.IsThisHoliday(pDate2Test TIMESTAMP)
 RETURNS BOOLEAN AS
$BODY$
DECLARE 
   result BOOLEAN = false;
   str VARCHAR(30);
   l_FirstOfMonth TIMESTAMP;
   l_RC INTEGER;
   l_MonthNumber INTEGER;
   CheckThisMonthsHolidays refcursor; 
   l_Holiday VARCHAR(50);
   l_ExactDate BOOLEAN;
   l_DayOfMonth INTEGER;
   l_WeekDay INTEGER ;
   l_WeekOfMonth INTEGER;
   l_HolidayWeekOfMonth INTEGER;
   l_LastWeekOfMonth INTEGER;
BEGIN
    select extract(month from pDate2Test) || '/1/' || extract (year from pDate2Test) into str;
    l_FirstOfMonth  = to_timestamp(str, 'MM DD YYYY');
    select extract(month from pDate2Test) INTO l_MonthNumber;
    
    -- =============================================
    -- Declare and using a READ_ONLY cursor
    -- =============================================
     OPEN CheckThisMonthsHolidays FOR SELECT holiday, exact_date, day_of_month, 

      week_day, week_of_month 
    FROM opm.holiday
    WHERE month_number = l_MonthNumber FOR READ ONLY;   
  
    FETCH CheckThisMonthsHolidays INTO l_Holiday, l_ExactDate, l_DayOfMonth, --l_AdjustedDayOfMonth,
        l_WeekDay, l_WeekOfMonth;
    WHILE FOUND LOOP
        IF l_ExactDate = true THEN -- Check for a match on month and day of month
            IF l_DayOfMonth = to_number(to_char(pDate2Test, 'DD'), '00') THEN
                result = true;
            END IF;
        ELSE -- Check for a match on week of the month and day of the week
            IF to_number(to_char(pDate2Test, 'D'), '0') = l_WeekDay THEN -- This is the right weekday for this holiday
                IF l_WeekOfMonth > 6 THEN -- Then the holiday is on the last week of the month
                    IF to_number(to_char(pDate2Test+interval '1 week', 'MM'), '00') <>
                        to_number(to_char(pDate2Test, 'MM'), '00') THEN -- This is the last week of the month
                        result = true;
                    END IF;           
                ELSE -- Holiday is not on the last week of the month
                    IF to_number(to_char(pDate2Test, 'WW '), '00')  = (to_number(to_char(l_FirstOfMonth, 'WW '), '00') +
                        l_WeekOfMonth) THEN -- This is the right week #
                        result = true;
                    END IF;
                END IF;
            END IF;
        END IF;
      FETCH CheckThisMonthsHolidays INTO l_Holiday, l_ExactDate, l_DayOfMonth, --l_AdjustedDayOfMonth,
          l_WeekDay, l_WeekOfMonth;
    END LOOP;
    
    CLOSE CheckThisMonthsHolidays;
        
    return result;
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.NextBusinessDay
--
-- Description: this store procedure will get the next business day.
--
-- IN parameters :
-- @pStartDate - the start date.
-- @pAdjustmentDays - the adjustment days.
--
-- Out parameters :
-- @pAdjustedDate - the next business day.
--
-- Author: faeton, zaixiang
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.NextBusinessDay(
    pStartDate TIMESTAMP,
    pAdjustmentDays INTEGER,
    OUT pAdjustedDate TIMESTAMP)
    RETURNS TIMESTAMP AS
$BODY$
DECLARE 
    l_Direction INTEGER;
    result BOOLEAN = false;
BEGIN
    IF pAdjustmentDays > 0 THEN
      l_Direction = 1;
    ELSE
      l_Direction = -1;
    END IF;
    
    pAdjustedDate = pStartDate + ( pAdjustmentDays || ' days')::interval;
    
    result = opm.IsThisHoliday(pAdjustedDate);
  
    WHILE to_number(to_char(pAdjustedDate, 'D '), '0')  = 1 -- Sunday
      OR  to_number(to_char(pAdjustedDate, 'D '), '0')  = 7 -- Saturday
      OR result = true -- From IsThisHoliday test
    LOOP
      pAdjustedDate = pAdjustedDate + ( l_Direction || ' days')::interval;
      result = opm.IsThisHoliday(pAdjustedDate);
    END LOOP;
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.ComputePaymentTransactionStatusCode
--
-- Description: this store procedure will compute payment transaction status code.
--
-- IN parameters :
-- @pPayTransactionKey - the payment transaction key.
--
-- Out parameters :
-- @pCalculatedStatusCode - the calculated status code.
--
-- Author: faeton, zaixiang
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.ComputePaymentTransactionStatusCode(
    pPayTransactionKey BIGINT,
    OUT pCalculatedStatusCode VARCHAR(128))
   AS
$BODY$
DECLARE
    l_PTDateOfBirth TIMESTAMP; 
    l_PTClaimNumber VARCHAR (7);
    l_NumberMatches INTEGER;
    l_TempResult VARCHAR(128);
    l_AccountBalance DECIMAL(10,2) = 0;
BEGIN
    -- Get the CSD and date from the payment record
    SELECT scm_date_of_birth, scm_claim_number
    FROM opm.payment_transaction
    WHERE id = pPayTransactionKey AND deleted=false INTO l_PTDateOfBirth, l_PTClaimNumber;
    
    SELECT COUNT(claim_number) 
    FROM opm.account S
    LEFT JOIN opm.account_holder ah ON S.account_holder_id=ah.id AND ah.deleted=false
    LEFT OUTER JOIN opm.account_status LAS ON LAS.id = S.account_status_id AND LAS.deleted=false
    WHERE claim_number = l_PTClaimNumber
      AND S.deleted=false
      AND ah.birth_date = l_PTDateOfBirth
      AND LAS.name IN ('Active', 'First Bill Generated') -- Active or first bill generated
    INTO l_NumberMatches;
    
    IF l_NumberMatches = 0 THEN   -- Master Record account not active or match on birth date
        l_TempResult = NULL;  -- Unknown
    ELSE        -- So far, so good. Check that the account still owes money...

        SELECT OPM.GetBalance(l_PTClaimNumber)
         INTO l_AccountBalance
         FROM opm.account
         WHERE claim_number = l_PTClaimNumber AND deleted=false;
    
        IF l_AccountBalance > 0.0 THEN -- Client still owes money
            l_TempResult = 'Accepted';  -- Accepted
        ELSE
            l_TempResult = 'Unresolved';  -- Unresolved because account is paid off
        END IF;
    END IF;

    IF l_TempResult IS NULL THEN -- No exact match. Check for partial match...
        SELECT COUNT (claim_number) INTO l_NumberMatches FROM opm.account WHERE claim_number = l_PTClaimNumber AND deleted=false;
        IF l_NumberMatches = 0 THEN   -- Master Record account either not active or match on birth date
            l_TempResult = 'Suspended';   -- Suspended
        ELSE
            l_TempResult = 'Unresolved';  -- Unresolved because there is an Master Record but it does not fully match
        END IF;
    END IF;
  
    pCalculatedStatusCode = l_TempResult;
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.PaymentNoteAppend
--
-- Description: this store procedure will append the note to the corresponding record in
-- the payment_transaction_note table.
--
-- IN parameters :
-- @pPaymentTransactionKey - the payment transaction key.
-- @pNotation - the notation.
--
-- Returns:
--   0 if it is successful, -1 otherwise.
--
-- Author: faeton, zaixiang
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.PaymentNoteAppend(
    pPaymentTransactionKey BIGINT, 
    pNotation varchar (500)
)
    RETURNS INTEGER AS
$BODY$
DECLARE
    l_error_var INTEGER = 0;
    l_rowcount_var INTEGER = 0;
    l_NumNotes INTEGER = 0;
    l_ReturnCodeValue INTEGER = 0;
BEGIN
    SELECT COUNT (pay_transaction_key)
    INTO l_NumNotes
    FROM opm.payment_transaction_note
    WHERE pay_transaction_key = pPaymentTransactionKey AND deleted=false;
  
    BEGIN
        IF l_NumNotes = 0 THEN
            INSERT INTO opm.payment_transaction_note(pay_transaction_key, note, deleted)
            VALUES(pPaymentTransactionKey, pNotation, false);
        ELSEIF char_length(LTRIM(pNotation)) > 0 THEN
            UPDATE opm.payment_transaction_note
            SET note = substr(note || ' ' || pNotation, 1, 500)
            WHERE pay_transaction_key=pPaymentTransactionKey;
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            l_ReturnCodeValue = -1;
    END;  

    RETURN l_ReturnCodeValue;
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.BatchInputBankPayments
--
-- Description: this store procedure will perform batch input bank payments operation.
--
-- IN parameters :
-- @pPayTransBatchNumber - the payment transaction batch number. 
-- @pPayTransBlockNumber - the payment transaction block number. 
-- @pPayTransSequenceNumber - the payment transaction sequence number. 
-- @pSCMClaimNumber - the claim number. 
-- @pSCMDateOfBirth - the birth date. 
-- @pPayTransPaymentAmount - the payment transaction amount. 
-- @pPayTransTransactionDate - the payment transaction date. 
-- @pACHPaymentFlag - the ACH payment flag. 
-- @pNetworkId - the network id. 
--
-- Out parameters :
-- @pPayTransactionKey - the payment transaction key.
-- @pReturn - the return value.
--
-- Author: faeton, zaixiang
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.BatchInputBankPayments(
    pPayTransBatchNumber VARCHAR (3),
    pPayTransBlockNumber VARCHAR (2),
    pPayTransSequenceNumber VARCHAR (2),
    pSCMClaimNumber VARCHAR (7),
    pSCMDateOfBirth TIMESTAMP,
    pPayTransPaymentAmount DECIMAL(10,6),
    pPayTransTransactionDate TIMESTAMP, 
    pACHPaymentFlag BOOLEAN, 
    pNetworkId VARCHAR (128),
    OUT pPayTransactionKey BIGINT,
    OUT pReturn VARCHAR (128)) AS
$BODY$
DECLARE
    l_LastErrorNumber VARCHAR (128) = '0';
    l_LookUpPaymentAppliedOrderCode VARCHAR(256);
    l_PostFlag BOOLEAN;
    l_PayTransStatusCode INTEGER;
    l_NumberLikeThis INTEGER;
    l_SCMEnteredBy BIGINT;
BEGIN
    -- Use the default code for the payment applied order
    l_LookUpPaymentAppliedOrderCode = NULL;
    
    -- Set the post flag and the code for pending payments
    l_PayTransStatusCode = 0; -- Pending
    l_PostFlag = false;   -- Pending
    
    SELECT id INTO l_SCMEnteredBy FROM opm.app_user WHERE network_id=pNetworkId AND deleted=false;
    
    -- Check for duplicates
    SELECT COUNT(id) 
    INTO l_NumberLikeThis
    FROM opm.payment_transaction
    WHERE pay_trans_batch_number = pPayTransBatchNumber
      AND pay_trans_block_number = pPayTransBlockNumber
      AND pay_trans_sequence_number = pPayTransSequenceNumber
      AND pay_trans_payment_amount = pPayTransPaymentAmount
      AND pay_trans_transaction_date  = pPayTransTransactionDate
      AND scm_claim_number = pSCMClaimNumber
      AND deleted=false;
    
    IF l_NumberLikeThis = 0 THEN
        BEGIN
            INSERT INTO opm.payment_transaction(
                pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, scm_claim_number, 
                scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code,
                pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag, user_inserted,
                ach_payment, deleted) 
            VALUES (pPayTransBatchNumber, pPayTransBlockNumber, pPayTransSequenceNumber, pSCMClaimNumber, 
                pSCMDateOfBirth, pPayTransPaymentAmount, pPayTransTransactionDate, l_PayTransStatusCode, 
                current_timestamp, l_SCMEnteredBy, l_LookUpPaymentAppliedOrderCode, l_PostFlag, false,
                pACHPaymentFlag, false)
            RETURNING id INTO pPayTransactionKey;
        EXCEPTION
            WHEN OTHERS THEN
                l_LastErrorNumber = SQLSTATE;
        END;
    ELSE
        l_LastErrorNumber = '17';
        pPayTransactionKey = 0;
    END IF;
    
    -- We cannot use the LASTVAL() value because the insert trigger overwrites it
    IF pPayTransactionKey IS NOT NULL AND l_LastErrorNumber = '0' THEN
      pReturn = '0';
    ELSE
      pReturn = l_LastErrorNumber;
    END IF;
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.BatchPerformBankChanges
--
-- Description: this store procedure will perform batch bank changes operation.
--
-- IN parameters :
-- @pSCMClaimnumber - the claim number.
-- @pSCMDateOfBirth - the birth date.
-- @pFieldNumber - the field number.
-- @pDataElement - the data element.
-- @pSCMCity - the city.
-- @pSCMState - the state.
-- @pSCMZipcode - the zip code.
-- @pSCMFName - the first name.
-- @pSCMMInitial - the initial.
-- @pSCMLastname - the last name.
-- @pSCMSuffix - the suffix.
--
-- Returns:
--   the error code.
--
-- Author: faeton, zaixiang
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.BatchPerformBankChanges(
    pSCMClaimnumber VARCHAR (7),
    pSCMDateOfBirth TIMESTAMP,
    pFieldNumber INTEGER, 
    pDataElement VARCHAR (40), 
    pSCMCity VARCHAR (20), 
    pSCMState VARCHAR (2), 
    pSCMZipcode VARCHAR (10), 
    pSCMFName VARCHAR (40), 
    pSCMMInitial VARCHAR (1), 
    pSCMLastname VARCHAR (40), 
    pSCMSuffix VARCHAR (3), 
    OUT pUpdateCount INTEGER, 
    OUT pErrorCode VARCHAR (128)) AS
$BODY$
DECLARE
    l_payCodeC BIGINT;
    l_stateId BIGINT;
    l_suffixId BIGINT;
    l_addressId BIGINT;
    l_accountId BIGINT;
    l_accountHolderId BIGINT;

BEGIN
    BEGIN
	    pUpdateCount = 0;
	    pErrorCode = '0';

	    SELECT id INTO l_payCodeC FROM opm.pay_code WHERE name='C' AND deleted=false;

        SELECT ah.id, ah.address_id, S.id INTO l_accountHolderId, l_addressId, l_accountId
        FROM opm.account_holder ah
              INNER JOIN opm.account S ON S.account_holder_id=ah.id AND
              S.claim_number=pSCMClaimnumber AND ah.birth_date=pSCMDateOfBirth
              AND ah.deleted=false AND S.deleted=false;

        IF l_accountId is null OR l_accountHolderId is null OR l_addressId is null THEN
            RETURN;
        END IF;

        IF char_length(pSCMCity) > 0 THEN
          UPDATE opm.address SET city = pSCMCity WHERE id = l_addressId;
          pUpdateCount = pUpdateCount + 1;
        END IF;
        
        IF char_length(pSCMState) > 0 THEN
          SELECT id INTO l_stateId FROM opm.state WHERE name=pSCMState AND deleted=false;
          IF l_stateId is not null THEN
            UPDATE opm.address SET state_id = l_stateId WHERE id = l_addressId;
            pUpdateCount = pUpdateCount + 1;
          END IF;
        END IF;
        
        IF char_length(pSCMZipcode) > 0 THEN
          UPDATE opm.address SET zip_code = pSCMZipcode WHERE id = l_addressId;
          pUpdateCount = pUpdateCount + 1;
        END IF;
        
        /*
        Corrected data will replace the corresponding data element in the 
        data base as follows depending on the field number:
        
        Field 
        Number  Data Element
        01  Name
        02  Address Line 1
        03  Address Line 2
        04  Address Line 3
        05  Address Line 4
        06  Address Line 5
        07  Last Name
        09  Data of Birth
        10  SSN
        */   
        IF pFieldNumber = 1 THEN


          IF char_length(pSCMFName) > 0 THEN 
              UPDATE opm.account_holder ah SET first_name = pSCMFName WHERE id = l_accountHolderId;
              pUpdateCount = pUpdateCount + 1;
          END IF;

          IF char_length(pSCMMInitial) > 0 THEN 
              UPDATE opm.account_holder ah SET middle_initial = pSCMMInitial WHERE id = l_accountHolderId;
              pUpdateCount = pUpdateCount + 1;
          END IF;

          IF char_length(pSCMLastname) > 0 THEN 
              UPDATE opm.account_holder ah SET last_name = pSCMLastname WHERE id = l_accountHolderId;
              pUpdateCount = pUpdateCount + 1;
          END IF;

          IF char_length(pSCMSuffix) > 0 THEN 
            SELECT id INTO l_suffixId FROM opm.suffix WHERE name=pSCMSuffix AND deleted=false;
            IF l_suffixId is not null THEN
              UPDATE opm.account_holder SET suffix_id = l_suffixId WHERE id = l_accountHolderId;
              pUpdateCount = pUpdateCount + 1;
            END IF;
          END IF;
        ELSIF pFieldNumber = 2 THEN
          UPDATE opm.address SET street1 = pDataElement WHERE id = l_addressId;
          pUpdateCount = pUpdateCount + 1;
        ELSIF pFieldNumber = 3 THEN
          UPDATE opm.address SET street2 = pDataElement WHERE id = l_addressId;
          pUpdateCount = pUpdateCount + 1;
        ELSIF pFieldNumber = 4 THEN
          UPDATE opm.address SET street3 = pDataElement WHERE id = l_addressId;
          pUpdateCount = pUpdateCount + 1;
        ELSIF pFieldNumber = 5 THEN
          UPDATE opm.address SET street4 = pDataElement WHERE id = l_addressId;
          pUpdateCount = pUpdateCount + 1;
        ELSIF pFieldNumber = 6 THEN
          UPDATE opm.address SET street5 = pDataElement WHERE id = l_addressId;
          pUpdateCount = pUpdateCount + 1;
        ELSIF pFieldNumber = 7 THEN
          UPDATE opm.account_holder ah SET last_name = pDataElement WHERE id = l_accountHolderId;
          pUpdateCount = pUpdateCount + 1;
        ELSIF pFieldNumber = 9 THEN
          UPDATE opm.account_holder ah
          SET birth_date = to_timestamp(substr(pDataElement, 1, 2) || '/' || substr(pDataElement, 3, 2) ||
              '/' ||substr(pDataElement, 5, 2), 'MM DD YY')
          WHERE id = l_accountHolderId;
          pUpdateCount = pUpdateCount + 1;
        ELSIF pFieldNumber = 10 THEN
          UPDATE opm.account_holder ah SET ssn = pDataElement WHERE id = l_accountHolderId;
          pUpdateCount = pUpdateCount + 1;
        ELSE 
          pErrorCode = '-97';
        END IF;

        IF pUpdateCount > 0 THEN
            UPDATE opm.account SET pay_code_id = l_payCodeC WHERE id = l_accountId;
        END IF;

    EXCEPTION
        WHEN OTHERS THEN
            pErrorCode = SQLSTATE;
    END;
        
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.BatchCollateNewPayments
--
-- Description: this store procedure will perform batch collate new payments operation.
--
-- Out parameters :
-- @pAcceptedCount - the accepted count.
-- @pUnresolvedCount - the unresolved count.
-- @pSuspendedCount - the suspended count.
-- @pAcceptedACHCount - the accepted ACH count.
-- @pUnresolvedACHCount - the unresolved ACH count.
-- @pSuspendedACHCount - the suspended ACH count.
--
-- Author: faeton, zaixiang
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.BatchCollateNewPayments(
    pAcceptedCount OUT INTEGER, 
    pUnresolvedCount OUT INTEGER, 
    pSuspendedCount OUT INTEGER, 
    pAcceptedACHCount OUT INTEGER,  
    pUnresolvedACHCount OUT INTEGER, 
    pSuspendedACHCount OUT INTEGER)
RETURNS record AS
$BODY$
DECLARE
    ModifyCursor refcursor; 
    l_PayTransactionKey BIGINT;
    l_CSD varchar (7);
    l_BirthDate TIMESTAMP;
    l_PaymentStatusCode VARCHAR (128);
    l_ACHPayment BOOLEAN;
    l_ResolvedSuspense BOOLEAN;
    l_ValidClaimNumber varchar (7);
    l_CorrectBirthDate TIMESTAMP;
    l_AccountStatusCode VARCHAR(128);
    l_PaymentOrderArray VARCHAR(128);
    l_SCMLastPay TIMESTAMP; 
    l_SCMCode20Date TIMESTAMP;
    l_HistoryPaymentFlag BOOLEAN;
    l_PayTransStatusCodeId BIGINT;
    l_PayCodeId BIGINT;
BEGIN
    /* Go through all the "Pending" payments there were just loaded from the mainframe file
       and decide if they are accepted, unresolved or suspended. */
    OPEN ModifyCursor FOR SELECT
        B.id, scm_claim_number, scm_date_of_birth, sc.description, ach_payment, resolved_suspense
    FROM opm.payment_transaction B
    LEFT JOIN opm.pay_trans_status_code sc ON sc.id = B.pay_trans_status_code AND sc.deleted=false
    WHERE sc.id = 0 AND user_inserted = false AND B.deleted=false;  
  
    pAcceptedCount = 0;
    pUnresolvedCount = 0;
    pSuspendedCount = 0;
    pAcceptedACHCount = 0;
    pUnresolvedACHCount = 0;
    pSuspendedACHCount = 0;

    FETCH ModifyCursor INTO l_PayTransactionKey, l_CSD, l_BirthDate, l_PaymentStatusCode,
        l_ACHPayment, l_ResolvedSuspense;
    WHILE FOUND LOOP

    
        l_ResolvedSuspense = false; -- Assume it will not be a suspense payment
        l_ValidClaimNumber = ''; -- INVALID. If the next select returns no rows, this value will not change.
        SELECT claim_number, ah.birth_date, LAS.name, 
           payment_order, last_pay, code_20_date
        INTO l_ValidClaimNumber, l_CorrectBirthDate, l_AccountStatusCode, l_PaymentOrderArray
             L_SCMLastPay, l_SCMCode20Date
        FROM opm.account S
        LEFT JOIN opm.account_holder ah ON S.account_holder_id=ah.id AND ah.deleted=false
        LEFT JOIN opm.account_status LAS ON LAS.id = S.account_status_id AND LAS.deleted=false 
        WHERE claim_number = l_CSD AND ah.birth_date = l_BirthDate AND l_AccountStatusCode = 'Active' AND S.deleted=false

        AND OPM.GetBalance(l_CSD) > 0;
    
        IF l_SCMLastPay < to_date('01/01/1900','MM DD YYYY') THEN -- Not set correctly when initial bill triggered. 
            l_SCMLastPay = l_SCMCode20Date;
        END IF;
    
        -- If this account is active or initial then this is not a history payment.
        IF  l_AccountStatusCode = 'Active' OR l_AccountStatusCode = 'First Bill Generated' THEN
            l_HistoryPaymentFlag = false;
        ELSE  
            l_HistoryPaymentFlag = true;
        END IF;
    
        IF l_ValidClaimNumber = '' THEN -- Invalid claim number means suspense or unresolved
            SELECT claim_number, ah.birth_date, LAS.name, payment_order
            INTO l_ValidClaimNumber, l_CorrectBirthDate, l_AccountStatusCode, l_PaymentOrderArray
            FROM opm.account S
            LEFT JOIN opm.account_holder ah ON S.account_holder_id=ah.id AND ah.deleted=false
            LEFT JOIN opm.account_status LAS ON LAS.id = S.account_status_id AND LAS.deleted=false 
            WHERE claim_number = l_CSD AND deleted=false; -- Is a matching account in the Service Credit Master table?
      
            IF l_ValidClaimNumber = '' THEN -- No match on claim number means suspense
                l_PaymentStatusCode = 'Suspended';  -- Suspended
                l_ResolvedSuspense = true;
                IF l_ACHPayment = true THEN
                    pSuspendedACHCount = pSuspendedACHCount + 1;
                ELSE
                    pSuspendedCount = pSuspendedCount + 1;
                END IF;
            ELSE -- Either the Birth Date doesn't match or this account is not active. That means unresolved.
                l_PaymentStatusCode = 'Unresolved';  -- Unresolved
                l_ResolvedSuspense = true;
                IF l_ACHPayment = true THEN
                    pUnresolvedACHCount = pUnresolvedACHCount + 1;
                ELSE
                    pUnresolvedCount =pUnresolvedCount + 1;
                END IF;
            END IF;
        ELSE -- It's active and matches on CSD & Birth Date so this payment is Accepted for posting.
            -- The "Accepted" state (PaymentStatusCode = 1) is now obsolete.
            -- SET @PaymentStatusCode = 1 -- 
            l_PaymentStatusCode = 'PostedPending'; -- Posted - Pending
            IF l_ACHPayment = true THEN
                pAcceptedACHCount = pAcceptedACHCount + 1;
            ELSE
                pAcceptedCount = pAcceptedCount + 1;
            END IF;
        END IF;
    
        select id INTO l_PayTransStatusCodeId FROM opm.pay_trans_status_code WHERE description=l_PaymentStatusCode AND deleted=false;
        UPDATE opm.payment_transaction
        SET pay_trans_status_code = l_PayTransStatusCodeId, resolved_suspense = l_ResolvedSuspense
        WHERE id = l_PayTransactionKey;
        
        IF l_PaymentStatusCode = 'PostedPending' THEN
          UPDATE opm.mainframe_import
          SET suspended_flag=false, unresolved_flag=false, postedPending_flag=true
          WHERE pay_transaction_key=l_PayTransactionKey;
        ELSE 
          IF l_PaymentStatusCode = 'Unresolved' THEN
            UPDATE opm.mainframe_import
            SET suspended_flag=false, unresolved_flag=true, postedPending_flag=false
            WHERE pay_transaction_key=l_PayTransactionKey;
      
            IF l_HistoryPaymentFlag = true THEN
              SELECT id INTO l_PayCodeId FROM opm.pay_code WHERE name='4' AND deleted=false;
              UPDATE opm.account 
              SET pay_code_id = l_PayCodeId, last_action = current_timestamp -- Receipt for closed account 
                                                                                       -- Unresolved payment arrives   
              WHERE SCMClaimNumber = l_CSD;
            END IF;
          ELSE
            UPDATE opm.mainframe_import
            SET suspended_flag=true, unresolved_flag=false, postedPending_flag=false
            WHERE pay_transaction_key=l_PayTransactionKey; 
          END IF;
        END IF;
        FETCH ModifyCursor INTO l_PayTransactionKey, l_CSD, l_BirthDate, l_PaymentStatusCode,
            l_ACHPayment, l_ResolvedSuspense;
    END LOOP;
    
    CLOSE ModifyCursor;
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.BatchDailyPaymentsSelect
--
-- Description: this store procedure will perform batch daily payments select operation.
--
-- IN parameters :
-- @pNetworkId - the network id.
--
-- Returns:
--   the result set.
--
-- Author: faeton, zaixiang
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.BatchDailyPaymentsSelect(
    pNetworkId VARCHAR (128))
 RETURNS TABLE(
    numberPaymentsToday INTEGER,
    todaysPaymentTotal DECIMAL(10,2), 
    batch_time TIMESTAMP,
    accountStatus BIGINT,
    payTransStatusCode BIGINT,
    scmClaimNumber VARCHAR(128),
    accountBalance DECIMAL(10,6),
    overPaymentAmount DECIMAL(10,6) ,
    achPayment BOOLEAN,
    achStopLetter BOOLEAN,
    printInvoice BOOLEAN,
    refundRequired BOOLEAN,
    reversedPayment BOOLEAN,
    updateToCompleted BOOLEAN,
    printInitialBill BOOLEAN,
    r_latest_batch BOOLEAN,
    errorProcessing BOOLEAN,
    payTransactionKey BIGINT,
    auditBatchId BIGINT,
    SCMDeposit DECIMAL(10,2),
    SCMRedeposit DECIMAL(10,2),
    SCMTotVarRedeposit DECIMAL(10,2),
    SCMNonDed DECIMAL(10,2),
    SCMFersW DECIMAL(10,2),
    r_SCMAccIntDep DECIMAL(10,2),
    r_SCMAccIntRdep DECIMAL(10,2),
    r_SCMAccIntNonDed DECIMAL(10,2),
    r_SCMAccIntVarRdep DECIMAL(10,2),
    r_SCMAccIntFers DECIMAL(10,2),
    r_SCMTotPayd DECIMAL(10,2),
    r_SCMTotPayr DECIMAL(10,2),
    r_SCMTotPayn DECIMAL(10,2),
    r_SCMTotPayvr DECIMAL(10,2),
    r_SCMTotPayfers DECIMAL(10,2),
    r_SCMCompDate TIMESTAMP,
    SCMLastPay TIMESTAMP,
    retirementTypeCode VARCHAR(128),
    scmDateOfBirth TIMESTAMP,
    ImportDate TIMESTAMP,
    scmName VARCHAR(128),
    payTransPaymentAmount DECIMAL(10,6),
    payTransTransactionDate TIMESTAMP,
    ResolvedSuspense BOOLEAN,
    HistoryPayment BOOLEAN,
    paymentApplicationOrder VARCHAR(256),
    accountStatusDescription VARCHAR(128),
    payTransStatusDescription VARCHAR(128),
    retirementTypeDescription VARCHAR(128)) AS
$BODY$
DECLARE
    l_AuditBatchIDLog BIGINT;
    l_UserRequestingPrintNow BIGINT;
    l_Notation VARCHAR (500);
    PendingSuspensePayments refcursor;
    SuspenseRefundsWithCreditBalanceRefunds refcursor;
    InvoiceLinker refcursor;
    l_PayTransStatusCodeId BIGINT;
    l_PayTransStatusCodeId1 BIGINT;
    l_PayTransactionKey BIGINT;
    l_SCMClaimNumber VARCHAR(128);
    l_AccountStatus VARCHAR(128);
    l_OldBalance DECIMAL(10,2);
    l_PaymentAmount DECIMAL(10,6);
    l_GoodBirthDate BOOLEAN;
    l_OK2Process BOOLEAN;
    l_NewBalance DECIMAL(10,2);
    l_account_status_id BIGINT;
    l_Reason VARCHAR (80);
    l_CalculatedStatusCode VARCHAR(128);
    l_NewBatchDate TIMESTAMP;
    l_RefundAmount DECIMAL(10,2);
    l_SCMDateOfBirth TIMESTAMP;
    l_PayTransBatchNumber VARCHAR(3);
    l_PayTransBlockNumber VARCHAR (2);
    l_PayTransSequenceNumber VARCHAR (2);
    l_PayTransPaymentAmount DECIMAL(10,2);
    l_PayTransTransactionDate TIMESTAMP;
    l_PayTransStatusDate TIMESTAMP;
    l_TechnicianUserKey BIGINT;
    l_NewPayTransactionKey BIGINT;
    l_NextInvoiceID BIGINT;
    l_InvoiceID BIGINT;
    l_OldClaimnumber varchar(7)= 'XXXXXXX';
    l_NewLinkID INTEGER; 
    l_RT INTEGER;
    l_RefundTransactionDate TIMESTAMP;
    l_EventYear INTEGER;
    l_EventMonth INTEGER;
    l_EventDay INTEGER;
BEGIN 
    select id INTO l_PayTransStatusCodeId FROM opm.pay_trans_status_code WHERE description='PostedPending' AND deleted=false;
    SELECT id INTO l_UserRequestingPrintNow FROM opm.app_user where network_id=pNetworkId AND deleted=false;
    select id INTO l_account_status_id FROM opm.account_status WHERE name='Active' AND deleted=false;

    l_EventYear = extract(year from current_timestamp);
    l_EventMonth = extract(month from current_timestamp);
    l_EventDay = extract(day from current_timestamp);
        
    SELECT  COALESCE(MAX(id), -1) INTO l_AuditBatchIDLog FROM opm.audit_batch
    WHERE event_year = l_EventYear
        AND l_EventMonth = event_month
        AND l_EventDay = event_day
        AND l_UserRequestingPrintNow = user_key
        AND deleted=false;

    -- Look for any "Pending" payments that are no longer valid
    -- because the account changed before the payment could be processed. 
    -- =============================================
    -- Declare and using a READ_ONLY cursor
    -- =============================================
    OPEN PendingSuspensePayments FOR 
    SELECT P.id, S.claim_number, LAS.name,
        OPM.GetBalance(S.claim_number) AS OldBalance,
        P.pay_trans_payment_amount, 
        CASE WHEN ah.birth_date = P.scm_date_of_birth THEN true ELSE false END AS GoodBirthDate
    FROM opm.payment_transaction P
        LEFT JOIN opm.account S ON P.scm_claim_number = S.claim_number AND S.deleted=false
        LEFT JOIN opm.account_holder ah ON S.account_holder_id=ah.id AND ah.deleted=false
        LEFT OUTER JOIN opm.account_status LAS ON LAS.id = S.account_status_id AND LAS.deleted=false
    WHERE (P.pay_trans_payment_amount <> 0) 
      AND P.deleted=false
      AND (P.pay_trans_status_code = l_PayTransStatusCodeId) 
      AND (LAS.name <> 'Active' 
        OR ah.birth_date <> P.scm_date_of_birth
        OR OPM.GetBalance(S.claim_number)<= -25) FOR READ ONLY;
        
    FETCH PendingSuspensePayments INTO l_PayTransactionKey, l_SCMClaimNumber, l_AccountStatus,
        l_OldBalance, l_PaymentAmount, l_GoodBirthDate;
    WHILE FOUND LOOP
        l_OK2Process = false;
        l_NewBalance = l_OldBalance - l_PaymentAmount;
        IF l_GoodBirthDate = true THEN -- Check the Account
            IF l_AccountStatus = 'Active' THEN
                l_OK2Process = true;
            ELSE -- Account Status back to inactive. If this was approved since the last batch processing, then do it.
                UPDATE opm.account SET account_status_id = l_account_status_id
                    WHERE claim_number = l_SCMClaimNumber;
            END IF;
        ELSE -- Birthdates don't match
            l_OK2Process = false;
            l_Reason = 'the birthday on account was changed.';
        END IF;
    
        IF l_OK2Process = true THEN-- Account must be good.
            IF l_OldBalance <= 0 THEN-- Check the amounts
                -- Account Paid off
                IF l_NewBalance < -25 THEN-- Too much credit!
                    l_OK2Process = false;
                    l_Reason = 'the credit balance would be too large.';
                END IF;
            END IF;
        END IF;
        IF l_OK2Process = false THEN -- Don't process this payment
            l_CalculatedStatusCode = opm.ComputePaymentTransactionStatusCode(l_PayTransactionKey);
            IF l_CalculatedStatusCode IS NOT NULL AND l_CalculatedStatusCode<> 'Pending' THEN
                select id INTO l_PayTransStatusCodeId FROM opm.pay_trans_status_code
                WHERE description=l_CalculatedStatusCode AND deleted=false;
                UPDATE opm.payment_transaction
                SET pay_trans_status_code = l_PayTransStatusCodeId, technician_user_key =
                l_UserRequestingPrintNow, pay_trans_status_date = current_timestamp
                WHERE ID = l_PayTransactionKey;
            END IF;
          
            -- If there was a refund record for this payment, delete it. 
            -- PayTransStatusDescription Refund Pending Refund Complete Refund Pending Approval
            IF l_CalculatedStatusCode NOT IN ('SuspenseRefundPending', 'SuspenseRefundComplete',
                'SuspenseRefundPendingApproval') THEN-- Expect this will always be true.
                DELETE FROM opm.refund_transaction WHERE to_number(transaction_key, '99G999D9S') = l_PayTransactionKey;
            END IF;
           
            /************ Add the note to the payment **************************/ 
            l_Notation = 'Nightly batch process declined to process this payment because ' || l_Reason;
            l_RT = opm.PaymentNoteAppend(l_PayTransactionKey, l_Notation);
        END IF;
    
        FETCH PendingSuspensePayments INTO l_PayTransactionKey, l_SCMClaimNumber, l_AccountStatus,
            l_OldBalance, l_PaymentAmount, l_GoodBirthDate;
    END LOOP;
    
    CLOSE PendingSuspensePayments;
    
    -- If a claimant requests a statement Recalc & reprint on the same day as another payment,
    -- there will be too many payments for today. Delete the reprint request. 
    -- Do this if there is another payment on that same account that is not yet final.
    -- i.e. it's "pending" and not "complete."
    select id INTO l_PayTransStatusCodeId FROM opm.pay_trans_status_code WHERE description='ManualAdjustmentCancelled' AND deleted=false;
    select id INTO l_PayTransStatusCodeId1 FROM opm.pay_trans_status_code WHERE description='PostedPending' AND deleted=false;
                
    UPDATE opm.payment_transaction
    SET pay_trans_status_code = l_PayTransStatusCodeId -- Manual Adjustment Cancelled 
    WHERE id IN 
      (
      SELECT P.id
      FROM opm.payment_transaction P
        INNER JOIN opm.payment_transaction P2 
          ON P.scm_claim_number = P2.scm_claim_number     -- Same claimant
            AND P.id <> P2.id -- Different payment
            AND P2.deleted=false
        INNER JOIN opm.pay_trans_status_code SC ON SC.id = P2.pay_trans_status_code AND SC.deleted=false
      WHERE (P.pay_trans_payment_amount = 0)  -- Reprint request
        AND (P.pay_trans_status_code = l_PayTransStatusCodeId1) -- Posted Pending
        AND (SC.final_state = false)    -- The other payment for this claimant is not in a final state.
        AND P.deleted=false
      );
        
    -- Reset yesterday's processing
    UPDATE opm.batch_daily_payments SET latest_batch = false WHERE latest_batch = true AND update_to_completed = true;
    
    -- Use a variable for the date so that each record is exactly the same. 
    l_NewBatchDate = current_timestamp;
    
    select id INTO l_PayTransStatusCodeId FROM opm.pay_trans_status_code WHERE description='SuspenseRefundPending' AND deleted=false;
    
    -- Add Credit Balance Refund records for Suspense Refunds that also refunded credit balances. 
    OPEN SuspenseRefundsWithCreditBalanceRefunds
    FOR SELECT R.amount, R.claim_number, R.refund_date, 
        P.pay_trans_batch_number, P.pay_trans_block_number, P.pay_trans_sequence_number, 
        P.pay_trans_payment_amount, P.scm_date_of_birth, P.pay_trans_transaction_date, 
        P.pay_trans_status_date, P.technician_user_key, P.id
    FROM opm.refund_transaction R
        INNER JOIN opm.payment_transaction P ON to_number(R.transaction_key, '99G999D9S') = P.id AND P.deleted=false
    WHERE (P.pay_trans_status_code = l_PayTransStatusCodeId) -- Suspense Refund Pending
        AND R.deleted=false
    ORDER BY P.pay_trans_transaction_date;
    
    FETCH SuspenseRefundsWithCreditBalanceRefunds INTO l_RefundAmount, l_SCMClaimnumber, l_RefundTransactionDate,
        l_PayTransBatchNumber, l_PayTransBlockNumber, l_PayTransSequenceNumber, l_PayTransPaymentAmount,
        l_SCMDateOfBirth,l_PayTransTransactionDate, l_PayTransStatusDate, l_TechnicianUserKey, l_PayTransactionKey;
    WHILE FOUND LOOP 
        select id INTO l_PayTransStatusCodeId FROM opm.pay_trans_status_code
        WHERE description='CreditBalanceRefundPending' AND deleted=false;
        
        INSERT INTO opm.payment_transaction(pay_trans_batch_number, pay_trans_block_number, pay_trans_sequence_number, 
          scm_claim_number, scm_date_of_birth, pay_trans_payment_amount, pay_trans_transaction_date,
          pay_trans_status_code, pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag,
          user_inserted, ach_payment, resolved_suspense, history_payment, disapprove, gov_refund, deleted)
        VALUES(l_PayTransBatchNumber, l_PayTransBlockNumber, l_PayTransSequenceNumber, l_SCMClaimNumber,
          l_SCMDateOfBirth, -l_RefundAmount, l_RefundTransactionDate, l_PayTransStatusCodeId, current_timestamp,
          l_TechnicianUserKey, NULL, false, true, false, false, false, true, true, false)
        RETURNING id INTO l_NewPayTransactionKey;

        l_Notation = 'Refund the credit balance and the suspense payment on the same voucher. Total refund voucher = $' 
            || (l_RefundAmount + l_PayTransPaymentAmount)::text;
    
        l_RT = opm.PaymentNoteAppend(l_PayTransactionKey, l_Notation);
        l_RT = opm.PaymentNoteAppend(l_NewPayTransactionKey, l_Notation);
    
        FETCH SuspenseRefundsWithCreditBalanceRefunds INTO l_RefundAmount, l_SCMClaimnumber, l_RefundTransactionDate,
            l_PayTransBatchNumber, l_PayTransBlockNumber, l_PayTransSequenceNumber, l_PayTransPaymentAmount,
            l_SCMDateOfBirth, l_PayTransTransactionDate, l_PayTransStatusDate, l_TechnicianUserKey,
            l_PayTransactionKey;
    END LOOP;
    CLOSE SuspenseRefundsWithCreditBalanceRefunds;
    
    select id INTO l_PayTransStatusCodeId FROM opm.pay_trans_status_code WHERE description='PostedPending' AND deleted=false;
    select id INTO l_PayTransStatusCodeId1 FROM opm.pay_trans_status_code WHERE description='ReversedPending' AND deleted=false;
    
    -- Fill the table with today's payments. 
    INSERT INTO opm.batch_daily_payments(audit_batch_id,pay_transaction_key,number_payment_today,
        batch_time,account_status_id,pay_trans_status_code,claim_number,account_balance, over_payment_amount,
        ach_payment,ach_stop_letter,print_invoice, refund_required,reversed_payment,update_to_completed,
        print_initial_bill,latest_batch,error_processing,deleted)
      SELECT l_AuditBatchIDLog AS AuditBatchIDLog,
        P.id, 
        ( SELECT COUNT (I.id) 
          FROM opm.payment_transaction I 
          WHERE I.scm_claim_number = P.scm_claim_number 
            AND I.pay_trans_status_code = l_PayTransStatusCodeId
            AND I.pay_trans_status_code = P.pay_trans_status_code
            AND coalesce(I.payment_applied_order_code, '0') = coalesce(P.payment_applied_order_code, '0')
            AND I.pay_trans_transaction_date = P.pay_trans_transaction_date
        ) AS NumberPaymentsToday, 
        l_NewBatchDate AS BatchDate,
        COALESCE (S.account_status_id, 0) AS AccountStatus,
        P.pay_trans_status_code, 
        P.scm_claim_number, 
        COALESCE (OPM.GetBalance(S.claim_number), 0) AS AccountBalance, 
        0, -- OverPaymentAmount,
        P.ach_payment,
        false, -- ACHStopLetter
        false, -- PrintInvoice
        false, -- RefundRequired
        false, -- ReversedPayment
        false, -- UpdateToCompleted,
        false, -- PrintInitialBill
        true, -- LatestBatch
        false, -- ErrorProcessing
        false--deleted
      FROM opm.payment_transaction P
        LEFT JOIN opm.pay_trans_status_code L ON L.id = P.pay_trans_status_code AND L.deleted=false
        LEFT JOIN opm.account S ON P.scm_claim_number = S.claim_number AND S.deleted=false
        LEFT JOIN opm.account_holder ah ON S.account_holder_id=ah.id AND P.scm_date_of_birth = ah.birth_date AND ah.deleted=false
      WHERE (L.nightly_batch = true) -- Type of payment processed by nightly batch
        AND P.deleted=false
        AND P.id NOT IN 
          (
            SELECT pay_transaction_key FROM opm.batch_daily_payments WHERE audit_batch_id = l_AuditBatchIDLog AND deleted=false
          );
          
    /*
    10  Posted - Pending
    11  Posted - Complete
    12  Reversed - Pending
    13  Reversed - Complete
    20  Voluntary Contributions - Pending
    21  Voluntary Contributions - Complete
    22  Direct Pay Life - Pending
    23  Direct Pay Life - Complete
    24  Refund Pending
    25  Refund Complete
    */
    -- Insert the current state for every payment that will be processed now. 
    CREATE temp TABLE NewInvoiceRecords on commit drop AS
    SELECT DISTINCT B.claim_number, 
      B.pay_transaction_key, 
      S.total_deposit, 
      S.total_redeposit, 
      S.total_var_redeposit, 
      S.total_non_deposit, 
      S.total_fers_w, 
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'interest') AS SCMAccIntDep, 
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'interest') AS SCMAccIntRdep, 
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'interest') AS SCMAccIntNonDed, 
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'interest') AS SCMAccIntVarRdep, 
      (OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'interest') +
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'interest') +
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'interest') +
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'interest')) AS SCMAccIntFers, 
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'total') AS SCMTotPayd, 
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'total') AS SCMTotPayr, 
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'total') AS SCMTotPayn, 
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'total') AS SCMTotPayvr, 
      (OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'total') +
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'total') +
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'total') +
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'total')) AS SCMTotPayfers, 
      S.last_pay,
      0 AS PreviousInvoiceID
    FROM opm.batch_daily_payments B
      INNER JOIN opm.account S ON B.claim_number = S.claim_number AND S.deleted=false
      INNER JOIN opm.payment_transaction P ON P.id = B.pay_transaction_key AND P.deleted=false
      -- Payment Pending or Reversal Pending
    WHERE (P.pay_trans_status_code IN (l_PayTransStatusCodeId, l_PayTransStatusCodeId1)) AND B.deleted=false;
  
    -- Backup all the CSD numbers with payments
    CREATE temp TABLE NewClaims on commit drop AS
        SELECT DISTINCT claim_number FROM NewInvoiceRecords;
        
    -- Delete the payment records that are already in the invoice table.
    -- We may wind up with a CSD number in #NewClaims but not in #NewInvoiceRecords
    DELETE FROM NewInvoiceRecords WHERE pay_transaction_key IN 
      (
      SELECT I.pay_transaction_key 
      FROM NewInvoiceRecords N
        INNER JOIN opm.invoice I ON N.pay_transaction_key = I.pay_transaction_key AND I.deleted=false
        INNER JOIN opm.payment_transaction P ON P.id = I.pay_transaction_key AND P.deleted=false
      WHERE P.pay_trans_status_code = l_PayTransStatusCodeId); -- already in invoices table. 
  
    -- Insert the current state for every payment that will be processed now. 
    INSERT INTO opm.invoice(pay_transaction_key,deposit,redeposit,tot_var_redeposit,non_ded,fers_w,
        acc_int_dep,acc_int_rdep,acc_int_non_dep,acc_int_var_rdep,acc_int_fers,tot_pay_d,tot_pay_r,
        tot_pay_n,tot_pay_vr,tot_pay_fers,last_pay,calc_date,last_invoice_id,deleted)
        SELECT pay_transaction_key, 
          total_deposit, 
          total_redeposit, 
          total_var_redeposit, 
          total_non_deposit, 
          total_fers_w, 
          SCMAccIntDep, 
          SCMAccIntRdep, 
          SCMAccIntNonDed, 
          SCMAccIntVarRdep, 
          SCMAccIntFers, 
          SCMTotPayd, 
          SCMTotPayr, 
          SCMTotPayn, 
          SCMTotPayvr, 
          SCMTotPayfers, 
          last_pay,
          current_timestamp, 
          0,
          false
        FROM NewInvoiceRecords;
        
    -- ======================================================================
    -- Update the previous invoice record with the record that was just inserted.
    -- ======================================================================
    /*
    Each Claim with a payment is in the #NewClaims table.
    Get all the payments for that CSD number with the corresponding invoice records.
    Starting with the last payment, set the link to 0.
    Then set the links going back to the first payment on the Claim.
    */
    OPEN InvoiceLinker
    FOR SELECT I.id, N.claim_number, I.last_invoice_id
    FROM NewClaims N
      INNER JOIN opm.payment_transaction P ON N.claim_number = P.scm_claim_number AND P.deleted=false
      INNER JOIN opm.invoice I ON I.pay_transaction_key = P.id AND I.deleted=false
    ORDER BY N.claim_number, I.calc_date DESC, I.last_pay DESC;
    
    FETCH InvoiceLinker INTO l_InvoiceID, l_SCMClaimnumber, l_NextInvoiceID;
    WHILE FOUND LOOP
        IF l_SCMClaimnumber <> l_OldClaimnumber THEN
            l_NewLinkID = 0;
            l_OldClaimnumber = l_SCMClaimnumber;
        END IF;
   
        UPDATE opm.invoice SET last_invoice_id = l_NewLinkID WHERE id = l_InvoiceID;
    
        l_NewLinkID = l_InvoiceID;
        
        FETCH InvoiceLinker INTO l_InvoiceID, l_SCMClaimnumber, l_NextInvoiceID;
    END LOOP;
    
    CLOSE InvoiceLinker;
    
    -- Fill the table with today's initial bills. 
    INSERT INTO opm.batch_daily_payments(audit_batch_id,pay_transaction_key,number_payment_today,
        batch_time,account_status_id,pay_trans_status_code,claim_number,account_balance, over_payment_amount,
        ach_payment,ach_stop_letter,print_invoice,refund_required,reversed_payment,update_to_completed,
        print_initial_bill,latest_batch,error_processing,deleted)
    SELECT DISTINCT l_AuditBatchIDLog AS AuditBatchIDLog,
        0, -- PayTransactionKey, 
        0 AS NumberPaymentsToday, 
        l_NewBatchDate AS BatchDate,
        S.account_status_id, 
        0, -- PayTransStatusCode, 
        S.claim_number, 
        OPM.GetBalance(S.claim_number) AS AccountBalance, 
        0, -- OverPaymentAmount,
        false, -- ACHPayment
        false, -- ACHStopLetter
        false, -- PrintInvoice
        false, -- RefundRequired
        false, -- ReversedPayment
        false, -- UpdateToCompleted,
        true, -- PrintInitialBill
        true, -- LatestBatch
        false, -- ErrorProcessing
        false
    FROM opm.calculation_result CSP
        INNER JOIN opm.calculation_version ver ON ver.deleted=false AND ver.calculation_result_id = CSP.id AND ver.deleted=false
        INNER JOIN opm.account S ON S.id = ver.account_id AND S.deleted=false
        LEFT OUTER JOIN opm.account_status LAS ON LAS.id = S.account_status_id AND LAS.deleted=false
    WHERE LAS.name = 'First Bill Generated' AND CSP.official = true AND OPM.GetBalance(S.claim_number) > 0
        AND CSP.deleted=false;
 
    return QUERY SELECT B.number_payment_today, 
      COALESCE((SELECT SUM (I.pay_trans_payment_amount) 
        FROM opm.payment_transaction I 
        WHERE I.scm_claim_number = P.scm_claim_number 
          AND I.pay_trans_status_code = P.pay_trans_status_code AND I.deleted=false)
        , 0) AS TotalPaymentsToday, 
      B.batch_time, B.account_status_id, 
      B.pay_trans_status_code, B.claim_number, B.account_balance, 
      B.over_payment_amount, B.ach_payment, B.ach_stop_letter, 
      B.print_invoice, B.refund_required, B.reversed_payment, 
      B.update_to_completed, B.print_initial_bill, B.latest_batch, B.error_processing,
      B.pay_transaction_key, B.audit_batch_id, 
      COALESCE (S.total_deposit, 0) AS SCMDeposit,
      COALESCE (S.total_redeposit, 0) AS SCMRedeposit,
      COALESCE (S.total_var_redeposit, 0) AS SCMTotVarRedeposit,
      COALESCE (S.total_non_deposit, 0) AS SCMNonDed,
      COALESCE (S.total_fers_w, 0) AS SCMFersW,
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'interest') AS SCMAccIntDep, 
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'interest') AS SCMAccIntRdep, 
      OPM.GetTotalInterest(S.claim_number,'POST_10_82', 'opm.dedeposit', 'interest') AS SCMAccIntNonDed, 
      OPM.GetTotalInterest(S.claim_number,'POST_10_82', 'opm.redeposit', 'interest') AS SCMAccIntVarRdep, 
      (OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'interest') +
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'interest') +
      OPM.GetTotalInterest(S.claim_number,'POST_10_82', 'opm.dedeposit', 'interest') +
      OPM.GetTotalInterest(S.claim_number,'POST_10_82', 'opm.redeposit', 'interest')) AS SCMAccIntFers, 
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'total') AS SCMTotPayd, 
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'total') AS SCMTotPayr, 
      OPM.GetTotalInterest(S.claim_number,'POST_10_82', 'opm.dedeposit', 'total') AS SCMTotPayn, 
      OPM.GetTotalInterest(S.claim_number,'POST_10_82', 'opm.redeposit', 'total') AS SCMTotPayvr, 
      (OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'total') +
      OPM.GetTotalInterest(S.claim_number,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'total') +
      OPM.GetTotalInterest(S.claim_number,'POST_10_82', 'opm.dedeposit', 'total') +
      OPM.GetTotalInterest(S.claim_number,'POST_10_82', 'opm.redeposit', 'total')) AS SCMTotPayfers,    
      COALESCE (S.computation_date, to_timestamp('01/01/1800', 'MM DD YYYY'))::TIMESTAMP AS SCMCompDate,
      COALESCE (S.last_pay, to_timestamp('01/01/1800', 'MM DD YYYY'))::TIMESTAMP AS SCMLastPay,
      COALESCE (retirement.name, '') AS RetirementTypeCode,
      COALESCE (ah.birth_date, to_timestamp('01/01/1800', 'MM DD YYYY'))::TIMESTAMP AS SCMDateOfBirth,
      COALESCE (M.import_date, to_timestamp('01/01/1800', 'MM DD YYYY'))::TIMESTAMP AS ImportDate,
      opm.ClaimantName (B.claim_number) AS SCMName,
      COALESCE (P.pay_trans_payment_amount, 0) AS PayTransPaymentAmount,
      COALESCE (P.pay_trans_transaction_date,
          to_timestamp('01/01/1800', 'MM DD YYYY'))::TIMESTAMP AS PayTransTransactionDate,
      COALESCE (P.resolved_suspense, false) AS ResolvedSuspense,
      COALESCE (P.history_payment, false) AS HistoryPayment,
      COALESCE (P.payment_applied_order_code, NULL) AS PaymentAppliedOrderCode,
      sta.name AS AccountStatusDescription, 
      COALESCE (statusCode.description, 'Initial Bill') AS PayTransStatusDescription,
      COALESCE (retirement.description, '') AS SCMRetirementTypeDescription
    FROM opm.batch_daily_payments B
    LEFT JOIN opm.account S ON B.claim_number = S.claim_number AND S.deleted=false
    LEFT JOIN opm.account_holder ah ON S.account_holder_id=ah.id AND ah.deleted=false
    LEFT JOIN opm.payment_transaction P ON B.pay_transaction_key = P.id AND P.deleted=false
    LEFT JOIN opm.mainframe_import M ON P.id = M.pay_transaction_key AND M.deleted=false
    LEFT JOIN opm.account_status sta ON B.account_status_id = sta.id AND sta.deleted=false
    LEFT JOIN opm.pay_trans_status_code statusCode ON B.pay_trans_status_code = statusCode.id AND statusCode.deleted=false
    LEFT JOIN opm.retirement_type retirement ON S.form_type_id = retirement.id AND retirement.deleted=false
    WHERE (B.latest_batch = true) AND (B.update_to_completed = false) AND (B.audit_batch_id = l_AuditBatchIDLog)
        AND B.deleted=false
    ORDER BY B.audit_batch_id, S.claim_number, statusCode.description, P.pay_trans_transaction_date;
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.BatchDailyPaymentsUpdate
--
-- Description: this store procedure will perform batch daily payments update operation.
--
-- IN parameters :
-- @pPayTransactionKey - the payment transaction key.
-- @pOverPaymentAmount - the over payment amount.
-- @pPrintInvoice - the print invoice flag.
-- @pRefundRequired - the refund required flag.
-- @pReversedPayment - the reversed payment flag.
-- @pUpdateToCompleted - the update to completed flag.
-- @pACHStopLetter - the ACH stop letter flag.
-- @pACHPayment - the ACH payment flag.
-- @pPrintInitialBill - the print initial bill flag.
-- @pErrorProcessing - the error processing flag.
-- @pAccountStatus - the account status.
-- @pSCMClaimNumber - the SCM claim number.
-- @pAuditBatchIDLog - the audit batch log id.
-- @pNetworkId - the network id.
--
-- Returns:
--   the error code.
--
-- Author: faeton, zaixiang
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.BatchDailyPaymentsUpdate(
    pPayTransactionKey BIGINT,
    pOverPaymentAmount DECIMAL(10,2),
    pPrintInvoice BOOLEAN, 
    pRefundRequired BOOLEAN, 
    pReversedPayment BOOLEAN,
    pUpdateToCompleted BOOLEAN,
    pACHStopLetter BOOLEAN,
    pACHPayment BOOLEAN,
    pPrintInitialBill BOOLEAN,
    pErrorProcessing BOOLEAN,
    pAccountStatus VARCHAR (128),
    pSCMClaimNumber VARCHAR (7),
    pAuditBatchIDLog BIGINT,
    pNetworkId VARCHAR (128)
)
    RETURNS INTEGER AS
$BODY$
DECLARE
    l_PayTransBatchNumber VARCHAR (256);
    l_PayTransBlockNumber VARCHAR (256);
    l_PayTransSequenceNumber VARCHAR (256);
    l_ResolvedSuspense BOOLEAN;
    l_PayTransStatusCode VARCHAR(128);
    l_NextStateLink VARCHAR(128);
    l_NextStateLinkId BIGINT;
    l_SCMDateOfBirth TIMESTAMP;
    l_PayTransTransactionDate TIMESTAMP;
    l_PayTransPaymentAmount DECIMAL(10,2);
    l_ExtraRefundAmount DECIMAL(10,2);
    l_RefundPayTransactionKey BIGINT ;
    l_NightlyBatchCode BOOLEAN;
    l_RefundNote VARCHAR (500);
    l_NumPayments INTEGER;
    l_RefundStatusCode VARCHAR(128);
    l_SCMEnteredBy BIGINT;
    l_PaymentTransactionId BIGINT;
    l_AccountStatusId BIGINT;
    l_PayTransStatusCodeId BIGINT = 0;
BEGIN
    SELECT id INTO l_SCMEnteredBy FROM opm.app_user WHERE network_id=pNetworkId And deleted=false;
    select id INTO l_AccountStatusId FROM opm.account_status WHERE name=pAccountStatus And deleted=false;
    
    SELECT pay_trans_batch_number,
         pay_trans_block_number,
         pay_trans_sequence_number,
         status1.description, 
         P.resolved_suspense,
         P.scm_date_of_birth,
         P.pay_trans_transaction_date,
         P.pay_trans_payment_amount,
         ABS(COALESCE (R.amount, 0)), 
         status1.nightly_batch,
         status2.description,
         status2.id
    INTO 
        l_PayTransBatchNumber, l_PayTransBlockNumber, l_PayTransSequenceNumber,
        l_PayTransStatusCode, l_ResolvedSuspense, l_SCMDateOfBirth,
        l_PayTransTransactionDate, l_PayTransPaymentAmount, l_ExtraRefundAmount,
        l_NightlyBatchCode, l_NextStateLink, l_NextStateLinkId
    FROM opm.payment_transaction P
        LEFT JOIN opm.pay_trans_status_code status1 ON status1.id = P.pay_trans_status_code And status1.deleted=false
        LEFT JOIN opm.pay_trans_status_code status2 ON status2.id = status1.next_state_link And status2.deleted=false
        LEFT JOIN opm.refund_transaction R ON P.id = to_number(R.transaction_key, '99G999D9S') And R.deleted=false
    WHERE P.id = pPayTransactionKey And P.deleted=false;

    /*
    Add one to the PayTransStatusCode to change it from Pending to Complete.
    10->11, 12->13, 20->21, 22->23, & 24->25
    10  Posted - Pending
    11  Posted - Complete
    12  Reversed - Pending
    13  Reversed - Complete
    20  Voluntary Contributions - Pending
    21  Voluntary Contributions - Complete
    22  Direct Pay Life - Pending
    23  Direct Pay Life - Complete
    24  Refund Pending
    25  Refund Complete
    */
    IF pUpdateToCompleted = true THEN
        IF pPrintInitialBill = true THEN
            IF pAccountStatus = 'Active' THEN
              UPDATE opm.account SET account_status_id = l_AccountStatusId
              WHERE claim_number = pSCMClaimNumber AND account_status_id IN (
                 SELECT id from opm.account_status WHERE name='First Bill Generated' And deleted=false);
            END IF;
        ELSE
            IF l_NightlyBatchCode = true THEN -- This is a valid code for batch updates
                -- Regular Update
                l_PayTransStatusCode = l_NextStateLink;
          
                UPDATE opm.payment_transaction
                SET   -- Do not update user. Leave it set to person who set the payment to "Pending"
                  pay_trans_status_code = l_NextStateLinkId,
                  pay_trans_status_date = current_timestamp 
                WHERE id = pPayTransactionKey;

                -- either a partial refund of a payment that was more than
                -- the balance OR a refunded payment. 
                IF pRefundRequired = true THEN
                    /* if this payment became a refund - complete, then enter the refund payment
                    plus any refunded credit balance */
                    
                    --Suspense Refund Complete means a refunded payment
                    IF l_NextStateLink = 'SuspenseRefundComplete' THEN 
                        pOverPaymentAmount = ABS(l_PayTransPaymentAmount);
                        l_RefundStatusCode = 'BatchAutoRefund'; --Batch Auto Refund
                        l_RefundNote = 'Auto Batch Refund of Suspense Refund.';
              
                        IF l_ExtraRefundAmount > 0 THEN
                            select id INTO l_PayTransStatusCodeId FROM opm.pay_trans_status_code
                            WHERE description='CreditBalanceRefundComplete' And deleted=false;
                            
                            -- Credit Balance Refund - Pending Approval   
                            INSERT INTO opm.payment_transaction(pay_trans_batch_number, pay_trans_block_number,
                                pay_trans_sequence_number, scm_claim_number, scm_date_of_birth,
                                pay_trans_payment_amount, pay_trans_transaction_date, pay_trans_status_code, 
                                pay_trans_status_date, technician_user_key, payment_applied_order_code, post_flag,
                                user_inserted, ach_payment, resolved_suspense, history_payment, disapprove,
                                gov_refund, deleted)
                            VALUES(l_PayTransBatchNumber, l_PayTransBlockNumber, l_PayTransSequenceNumber,
                                pSCMClaimNumber, l_SCMDateOfBirth, -l_ExtraRefundAmount, l_PayTransTransactionDate,
                                l_PayTransStatusCodeId, current_timestamp, l_SCMEnteredBy, NULL,
                              false, true, false, true, false, false, true, false)
                            RETURNING id INTO l_PaymentTransactionId;             
                              
                            -- Get the ID of the inserted record.
                            INSERT INTO opm.payment_transaction_note(pay_transaction_key, note, deleted)
                            VALUES(l_PaymentTransactionId,
                            'Refund the credit balance and the suspense payment on the same voucher.', false);
                        END IF;--@ExtraRefundAmount > 0
                    END IF;--@NextStateLink = 25
              
                    /* if this payment is an overpayment, make it pending approval */
                    
                    --Posted Complete means a partial refund of a payment that was more than the balance
                    IF l_NextStateLink = 'PostedComplete' THEN
                        -- Batch Auto Refund - Pending Approval
                        l_RefundStatusCode = 'BatchAutoRefundPendingApproval';
                        l_RefundNote = 'Auto Batch Refund of account overpayment.';
                    END IF;--@NextStateLink = 11 
                            
                    select id INTO l_PayTransStatusCodeId FROM opm.pay_trans_status_code
                    WHERE description=l_RefundStatusCode And deleted=false;
                    
                    INSERT INTO opm.payment_transaction(pay_trans_batch_number, pay_trans_block_number,
                        pay_trans_sequence_number, scm_claim_number, scm_date_of_birth, pay_trans_payment_amount,
                        pay_trans_transaction_date, pay_trans_status_code, pay_trans_status_date, technician_user_key,
                        payment_applied_order_code, post_flag, user_inserted, ach_payment, resolved_suspense,
                        history_payment, disapprove, gov_refund, deleted)
                    VALUES(l_PayTransBatchNumber, l_PayTransBlockNumber, l_PayTransSequenceNumber, pSCMClaimNumber,
                        l_SCMDateOfBirth, -pOverPaymentAmount, l_PayTransTransactionDate, l_PayTransStatusCodeId,
                        current_timestamp, l_SCMEnteredBy, NULL, false, false, pACHPayment, false, false, false,
                        true, false) RETURNING id INTO l_PaymentTransactionId;
            
                    -- Get the ID of the inserted record.
                    SELECT id into l_RefundPayTransactionKey
                    FROM opm.payment_transaction 
                    WHERE (id = l_PaymentTransactionId) AND (gov_refund = true) And deleted=false;
                    
                    -- Link the payment and the refund
                    INSERT INTO opm.payment_refund_link(payment_needing_refund, refund_for_payment, deleted)
                    VALUES(pPayTransactionKey, l_RefundPayTransactionKey, false);
                     
                    /************ Add the note to the payment **************************/ 
                    SELECT  COUNT(pay_transaction_key) 
                    INTO l_NumPayments
                    FROM  opm.payment_transaction_note
                    WHERE pay_transaction_key = pPayTransactionKey And deleted=false;
              
                    IF l_NumPayments = 0 THEN -- We only want one note per payment.
                      INSERT INTO opm.payment_transaction_note(pay_transaction_key, note, deleted)
                      VALUES(pPayTransactionKey, substr(l_RefundNote, 1, 500), false);
                    ELSE 
                      UPDATE opm.payment_transaction_note SET note = substr(note || ' - ' ||
                          l_RefundNote, 1, 500) WHERE pay_transaction_key = pPayTransactionKey;
                    END IF;
                    /************ Add the note to the payment **************************/
          
                END IF;--@RefundRequired = 1
            END IF; --@NightlyBatchCode = 1
        END IF;
    END IF;
 
    select id INTO l_PayTransStatusCodeId FROM opm.pay_trans_status_code WHERE description=l_PayTransStatusCode And deleted=false;
    
    UPDATE opm.batch_daily_payments
    SET over_payment_amount=pOverPaymentAmount,
      print_invoice=pPrintInvoice,
      refund_required=pRefundRequired,
      reversed_payment=pReversedPayment,
      update_to_completed = pUpdateToCompleted,
      ach_stop_letter=pACHStopLetter,
      error_processing=pErrorProcessing,
      account_status_id=l_AccountStatusId,
      pay_trans_status_code = l_PayTransStatusCodeId
    WHERE pay_transaction_key = pPayTransactionKey
      AND claim_number = pSCMClaimNumber
      AND print_initial_bill = pPrintInitialBill 
      AND audit_batch_id = pAuditBatchIDLog;

    RETURN (0);
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.BatchApplyOverpayment
--
-- Description: this store procedure will apply the over payment.
--
-- IN parameters :
-- @pClaimNumber - the claim number.
-- @pTotPayfers - the tot payfers of account.
-- @pTotPayvr - the tot payvr of account.
-- @pTotPayn - the tot payn of account.
-- @pTotPayr - the tot payr of account.
-- @pTotPayd - the tot payd of account.
-- @pRefundTriggerAmount - the refund trigger amount.
--
-- Out parameters :
-- @pOverpaymentAmount - the over payment amount.
-- @pReturn - the result code.
--
-- Author: faeton, TCSASSEMBLER
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.BatchApplyOverpayment(pClaimNumber VARCHAR (7),
       pTotPayfers DECIMAL(10,2), pTotPayvr DECIMAL(10,2), pTotPayn  DECIMAL(10,2),
       pTotPayr DECIMAL(10,2), pTotPayd DECIMAL(10,2), pRefundTriggerAmount DECIMAL(10,2),
       OUT pOverpaymentAmount DECIMAL(10,2), out pReturn INTEGER) AS
$BODY$
DECLARE
    l_NewBalance DECIMAL(10,2) = 0;
    l_OldBalance DECIMAL(10,2) = 0;
    l_LastErrorNumber INTEGER = 0;
    l_TransactionCode char(1) = 0;
    l_AccIntDep DECIMAL(10,2) = 0;
    l_AccIntRdep DECIMAL(10,2) = 0;
    l_AccIntNonDed DECIMAL(10,2) = 0;
    l_AccIntVarRdep DECIMAL(10,2) = 0;
    l_AccIntFers DECIMAL(10,2) = 0;
    l_pay_code_id BIGINT = 0;
    l_TotPayvr DECIMAL(10,2) = 0;
    l_TotPayr DECIMAL(10,2) = 0;
    l_TotPayn DECIMAL(10,2) = 0;
    l_TotPayd DECIMAL(10,2) = 0;
    l_TotPayfers DECIMAL(10,2) = 0;
BEGIN
    pOverpaymentAmount = 0;
    pReturn = 0;
    l_AccIntVarRdep = OPM.GetTotalInterest(pClaimNumber,'POST_10_82', 'opm.redeposit', 'interest');
    l_AccIntRdep = OPM.GetTotalInterest(pClaimNumber,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'interest');
    l_AccIntNonDed = OPM.GetTotalInterest(pClaimNumber,'POST_10_82', 'opm.dedeposit', 'interest');
    l_AccIntDep = OPM.GetTotalInterest(pClaimNumber,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'interest');
    l_AccIntFers = l_AccIntVarRdep + l_AccIntRdep + l_AccIntNonDed + l_AccIntDep;
    
    l_TotPayvr = OPM.GetTotalInterest(pClaimNumber,'POST_10_82', 'opm.redeposit', 'total');
    l_TotPayr = OPM.GetTotalInterest(pClaimNumber,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'total');
    l_TotPayn = OPM.GetTotalInterest(pClaimNumber,'POST_10_82', 'opm.dedeposit', 'total');
    l_TotPayd = OPM.GetTotalInterest(pClaimNumber,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'total');
    l_TotPayfers = l_TotPayvr + l_TotPayr + l_TotPayn + l_TotPayd;

    l_TransactionCode = '4'; --Receipt for closed account
    
    -- Set parameter values
    SELECT (total_deposit + total_redeposit + total_var_redeposit +
           total_non_deposit + total_fers_w + l_AccIntDep + l_AccIntRdep + 
           l_AccIntNonDed + l_AccIntVarRdep + l_AccIntFers - pTotPayfers -
           pTotPayvr - pTotPayn - pTotPayr - pTotPayd),              
           (total_deposit + total_redeposit + total_var_redeposit +
           total_non_deposit + total_fers_w + l_AccIntDep + l_AccIntRdep + 
           l_AccIntNonDed + l_AccIntVarRdep + l_AccIntFers -
           l_TotPayd - l_TotPayr - l_TotPayn - l_TotPayvr - l_TotPayfers)
           INTO l_NewBalance, l_OldBalance
    FROM opm.account
    WHERE  claim_number = pClaimNumber AND deleted=false;
    
    pOverpaymentAmount = l_OldBalance;
    pRefundTriggerAmount = - abs(pRefundTriggerAmount); -- Make sure it's negative
        
    IF (l_OldBalance > 0 OR l_NewBalance > 0) THEN -- If not paid off already, forget it.
        pReturn = -1;
        return;
    ELSE
        IF l_NewBalance < pRefundTriggerAmount THEN -- Large credit balances not allowed
            pReturn = -2;
            return;
        ELSE
            select opm.pay_code.id INTO l_pay_code_id FROM opm.pay_code WHERE name=l_TransactionCode AND deleted=false;
            UPDATE opm.account
            SET last_action = current_timestamp, 
              pay_code_id =  l_pay_code_id, --Receipt for closed account
              tot_pay_vr = pTotPayvr, 
              tot_pay_r = pTotPayr, 
              tot_pay_n = pTotPayn, 
              tot_pay_d = pTotPayd, 
              tot_pay_fers = pTotPayfers
          WHERE (claim_number = pClaimNumber);
        END IF;
    END IF;
    
    pOverpaymentAmount = l_NewBalance;
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.BatchDailyAccountUpdate
--
-- Description: this store procedure will update the account.
--
-- IN parameters :
-- @pAuditBatchIDLog - the audit batch log id.
--
-- Return - the updated record count.
--
-- Author: faeton, TCSASSEMBLER
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.BatchDailyAccountUpdate(pAuditBatchIDLog BIGINT)
    RETURNS  INTEGER AS
$BODY$
DECLARE
      l_UpdateCounter INTEGER = 0;
      
      -- =============================================
      -- Declare and using a READ_ONLY cursor
      -- =============================================
      l_AccountCleanUp CURSOR FOR SELECT acs.name AS accountStatus, P.ach_payment,
          B.over_payment_amount, B.claim_number, 
      B.reversed_payment, role.name, B.print_initial_bill, 
      B.update_to_completed, P.user_inserted, P.resolved_suspense, P.payment_status_code, 
      S.stop_ach_payment, S.account_status_id, S.pay_code_id
      FROM opm.batch_daily_payments B
          LEFT OUTER JOIN opm.account_status acs ON acs.id = B.account_status_id AND acs.deleted=false
          LEFT OUTER JOIN opm.account S ON S.claim_number = B.claim_number AND S.deleted=false
          LEFT OUTER JOIN opm.payment_transaction P ON B.pay_transaction_key = P.id AND P.deleted=false
          LEFT OUTER JOIN opm.app_user u ON u.id = P.technician_user_key AND u.deleted=false 
          LEFT OUTER JOIN opm.role role ON role.id = u.role_id AND role.deleted=false
      WHERE (B.audit_batch_id = pAuditBatchIDLog AND B.deleted=false)
      ORDER BY B.claim_number, P.pay_trans_status_date FOR READ ONLY;
    
    l_AccountStatus VARCHAR(128);
    l_UserRoleCode VARCHAR(128);
    l_ACHPayment BOOLEAN = false;
    l_OverPaymentAmount DECIMAL(10,6) = 0;
    l_SCMClaimnumber VARCHAR(128);
    l_ReversedPayment BOOLEAN = false;
    l_PrintInitialBill BOOLEAN = false;
    l_UpdateToCompleted BOOLEAN = false;
    l_UserInserted BOOLEAN = false;
    l_ResolvedSuspense BOOLEAN = false;
    l_PayTransStatusCode BIGINT = 0;
    l_OldStopACHPayment BOOLEAN = false;
    l_OldAccountStatus BIGINT = 0;
    l_OldSCMPayCode BIGINT = 0;
    l_NewStopACHPayment BOOLEAN = false;
    l_NewAccountStatus BIGINT = 0;
    l_NewSCMPayCode BIGINT = 0;
    l_payCodeA BIGINT = OPM.GetPayCodeId('A');
    l_payCode6 BIGINT = OPM.GetPayCodeId('6');
    l_payCode1 BIGINT = OPM.GetPayCodeId('1');
    l_payCode5 BIGINT = OPM.GetPayCodeId('5');
    l_payCode2 BIGINT = OPM.GetPayCodeId('2');
    l_payCode3 BIGINT = OPM.GetPayCodeId('3');
    l_payCodeD BIGINT = OPM.GetPayCodeId('D');
BEGIN
    OPEN l_AccountCleanUp;
    FETCH l_AccountCleanUp INTO l_AccountStatus, l_ACHPayment, l_OverPaymentAmount, l_SCMClaimnumber,
      l_ReversedPayment, l_UserRoleCode, l_PrintInitialBill, l_UpdateToCompleted, l_UserInserted,
      l_ResolvedSuspense, l_PayTransStatusCode, l_OldStopACHPayment, l_OldAccountStatus, l_OldSCMPayCode;
    WHILE FOUND LOOP
        l_UpdateCounter = l_UpdateCounter + 1;
    
        l_NewStopACHPayment = l_OldStopACHPayment;
        l_NewAccountStatus = l_OldAccountStatus;
        l_NewSCMPayCode = l_OldSCMPayCode;
    
        IF l_PrintInitialBill = true THEN
            l_NewSCMPayCode = l_payCodeA;
        END IF;
        
        IF l_PayTransStatusCode = 11 THEN -- Posted - Complete
            l_NewSCMPayCode = l_payCode6;
        END IF;
        
        IF l_ResolvedSuspense = true THEN
          IF l_PayTransStatusCode = 11 THEN -- Posted - Complete
              l_NewSCMPayCode = l_payCode1;
          ELSE
              l_NewSCMPayCode = l_payCode5;
          END IF;
        END IF;
        
        IF l_ReversedPayment = true THEN
            l_NewSCMPayCode = l_payCode2;
        END IF;
        
        IF l_UserInserted = true AND l_UserRoleCode = 'Service Credit Data Entry Technician' THEN -- Over the counter payments
            l_NewSCMPayCode = l_payCode3;
        END IF;
    
        IF l_AccountStatus = 'History' THEN
            select id into l_NewAccountStatus from opm.account_status where name = l_AccountStatus;
            l_NewSCMPayCode = l_payCodeD;
        END IF;
        
        IF l_OverPaymentAmount > 0 AND l_ACHPayment = true THEN -- ACH Overpayment
            l_NewStopACHPayment = true;
        END IF;
    
        UPDATE opm.account
          SET stop_ach_payment = l_NewStopACHPayment, 
            account_status_id = l_NewAccountStatus, 
            pay_code_id = l_NewSCMPayCode
          WHERE claim_number = l_SCMClaimNumber;

      FETCH l_AccountCleanUp INTO l_AccountStatus, l_ACHPayment, l_OverPaymentAmount, l_SCMClaimnumber,
      l_ReversedPayment, l_UserRoleCode, l_PrintInitialBill, l_UpdateToCompleted, l_UserInserted,
      l_ResolvedSuspense, l_PayTransStatusCode, l_OldStopACHPayment, l_OldAccountStatus, l_OldSCMPayCode;
    END LOOP;
    CLOSE l_AccountCleanUp;

    return l_UpdateCounter;
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.BatchAdjustPaymentTotals
--
-- Description: this store procedure will adjust the total payments.
--
-- IN parameters :
-- @pClaimNumber - the claim number.
-- @pTotPayfers - the tot payfers of account.
-- @pTotPayvr - the tot payvr of account.
-- @pTotPayn - the tot payn of account.
-- @pTotPayr - the tot payr of account.
-- @pTotPayd - the tot payd of account.
-- @pPayTransStatusCode - the payment transaction status code.
--
-- Returns:
--   The result code.
--
-- Author: faeton, TCSASSEMBLER
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.BatchAdjustPaymentTotals(pClaimNumber VARCHAR (7),
    pTotPayfers DECIMAL(10,2), pTotPayvr DECIMAL(10,2),
    pTotPayn  DECIMAL(10,2), pTotPayr DECIMAL(10,2), pTotPayd DECIMAL(10,2), pPayTransStatusCode INTEGER)
    RETURNS INTEGER AS
$BODY$
DECLARE
    l_RC INTEGER = 0;
    l_NewBalance DECIMAL(10,2) = 0;
    l_TransactionCode char(1) = 0;
    l_AccIntDep DECIMAL(10,2) = 0;
    l_AccIntRdep DECIMAL(10,2) = 0;
    l_AccIntNonDed DECIMAL(10,2) = 0;
    l_AccIntVarRdep DECIMAL(10,2) = 0;
    l_AccIntFers DECIMAL(10,2) = 0;
    l_pay_code_id BIGINT = 0;
    l_PayTransStatusDescription VARCHAR(128);
BEGIN
    l_AccIntVarRdep = OPM.GetTotalInterest(pClaimNumber,'POST_10_82', 'opm.redeposit', 'interest');
    l_AccIntRdep = OPM.GetTotalInterest(pClaimNumber,'CSRS_POST_10_82_DEPOSIT', 'opm.redeposit', 'interest');
    l_AccIntNonDed = OPM.GetTotalInterest(pClaimNumber,'POST_10_82', 'opm.dedeposit', 'interest');
    l_AccIntDep = OPM.GetTotalInterest(pClaimNumber,'CSRS_POST_10_82_DEPOSIT', 'opm.dedeposit', 'interest');
    l_AccIntFers = l_AccIntVarRdep + l_AccIntRdep + l_AccIntNonDed + l_AccIntDep;
    l_TransactionCode = 'B'; -- Adjustment to account
    SELECT (total_deposit + total_redeposit + total_var_redeposit + total_non_deposit + total_fers_w 
        + l_AccIntDep + l_AccIntRdep + l_AccIntNonDed + l_AccIntVarRdep + l_AccIntFers
        - pTotPayfers - pTotPayvr - pTotPayn - pTotPayr - pTotPayd) INTO l_NewBalance
    FROM opm.account
    WHERE  claim_number = pClaimNumber AND deleted=false;
    
    /*
    This function is for Write-offs, Credit Balance Refunds, & Adjustments
    This function called if PaymentTransactionCodes is one of the following:
    WriteOffPending (53)                  Add to the payment total. Amount is +
    CreditBalanceRefundPending (60)       Subtract from the payment total. Amount is -
    */
    select description from opm.pay_trans_status_code where id=pPayTransStatusCode AND deleted=false into l_PayTransStatusDescription;
    IF (l_NewBalance < 0 AND l_PayTransStatusDescription IN ('WriteOffPending', 'CreditBalanceRefundPending')) THEN
        l_RC = -2; -- Credit balances not allowed
    ELSE
      select opm.pay_code.id INTO l_pay_code_id FROM opm.pay_code WHERE name=l_TransactionCode AND deleted=false;
      UPDATE opm.account
      SET last_action = current_timestamp, 
          pay_code_id =  l_pay_code_id, -- Adjustment
          tot_pay_vr = pTotPayvr, 
          tot_pay_r = pTotPayr, 
          tot_pay_n = pTotPayn, 
          tot_pay_d = pTotPayd, 
          tot_pay_fers = pTotPayfers
      WHERE (claim_number = pClaimNumber);
    END IF;
    RETURN l_RC;
END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.BatchDailyGL
--
-- Description: this store procedure will get the GL records set.
--
-- IN parameters :
-- @pDayToProcess - the date time to process.
--
-- Return - the GL records set.
--
-- Author: faeton, TCSASSEMBLER
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.BatchDailyGL(pDayToProcess TIMESTAMP)
 RETURNS TABLE(
    feeder_system_id VARCHAR(50), 
    payment_type VARCHAR(256),
    payment_date TIMESTAMP,
    julian_date VARCHAR(7),
    julian_date_report VARCHAR(7),
    gl_filler VARCHAR(50),
    gl_code VARCHAR(40),
    fiscal_year INTEGER,
    gl_accounting_code VARCHAR(40),
    recipient_amount DECIMAL(10,2),
    revenue_source_code VARCHAR(40),
    agency varchar(40),
    pay_transaction_key BIGINT,
    scm_claim_number varchar(7),
    scm_date_Of_birth TIMESTAMP,
    scm_retirement_type_description varchar (50),
    claimant_name varchar(100),
    print_date TIMESTAMP,
    total_non_postal_fers DECIMAL(10,2),
    total_postal_fers DECIMAL(10,2),
    total_csrs DECIMAL(10,2),
    julian_now VARCHAR(7)) AS
$BODY$
DECLARE
    l_FeederSystemID VARCHAR(50);
    l_EventYear INTEGER;
    l_EventMonth INTEGER;
    l_EventDay INTEGER;
    l_AuditBatchIDLog INTEGER ;
    l_RefundDate TIMESTAMP; 
    l_RevenueSourceCode VARCHAR(50);
    l_TotalNonPostalFERS DECIMAL(10,2);
    l_TotalPostalFERS DECIMAL(10,2);
    l_TotalCSRS DECIMAL(10,2);
BEGIN
    -- Default is latest
    IF pDayToProcess IS NULL THEN
      SELECT event_year, event_month, event_day, id, to_timestamp(to_char(event_month, '00') || '/' ||
          to_char(event_day, '00') || '/' || to_char(event_year, '0000'), 'MM DD YYYY')
      FROM opm.audit_batch
      WHERE daily_action = true AND deleted=false
      ORDER BY batch_time DESC limit 1 INTO l_EventYear, l_EventMonth, l_EventDay, l_AuditBatchIDLog, pDayToProcess;
    ELSE
        -- User specified date
        l_EventYear = to_number(to_char(pDayToProcess, 'YYYY '), '0000');
        l_EventMonth = to_number(to_char(pDayToProcess, 'MM '), '00');
        l_EventDay = to_number(to_char(pDayToProcess, 'DD '), '00');
    END IF;

    select opm.NextBusinessDay(pDayToProcess, 2) into l_RefundDate;
    
    -- Posted, VC, & DPLI
    CREATE temp TABLE GLPayments on commit drop AS
    SELECT DISTINCT 
      CASE WHEN P.user_inserted = true THEN pDayToProcess
        ELSE P.pay_trans_transaction_date END AS PaymentDate,
      CASE WHEN sc.description = 'PostedComplete' THEN
        (CASE WHEN P.resolved_suspense = true THEN 'R1'
          WHEN P.user_inserted = true THEN 'R3'
          WHEN ps.name = 'History' THEN 'R4'
          ELSE 'R6' 
        END)
      WHEN sc.description = 'VoluntaryContributionsComplete' THEN 'VC'
      WHEN sc.description = 'DirectPayLifeComplete' THEN 'LI'
      ELSE '??' END
      AS PaymentType,
      COALESCE (f.name, '') AS RetirementTypeDescription,
      CASE WHEN ah.agency_code = '18' AND f.name = '3108(FERS)' 
        THEN true
        ELSE false END AS PostOffice, 
      B.pay_transaction_key, 
      P.pay_trans_payment_amount AS PayTransPaymentAmount, 
      B.pay_trans_status_code, 
      COALESCE(f.id, 0) AS RetirementTypeCode, 
      B.account_status_id, 
      COALESCE (LAS.name, '') AS AccountStatusDescription,
      B.over_payment_amount, 
      B.ach_payment, 
      B.refund_required, 
      B.reversed_payment, 
      B.update_to_completed, 
      P.pay_trans_transaction_date, 
      P.resolved_suspense, 
      ps.name, 
      P.user_inserted, 
      P.scm_claim_number, 
      COALESCE (opm.ClaimantName(S.claim_number), 'Unknown') AS ClaimantName,
      P.scm_date_of_birth
    FROM opm.audit_batch A 
      INNER JOIN opm.batch_daily_payments B ON A.id = B.audit_batch_id AND B.deleted=false
      INNER JOIN opm.payment_transaction P ON B.pay_transaction_key = P.id AND P.deleted=false
      LEFT JOIN opm.pay_trans_status_code sc ON sc.id = B.pay_trans_status_code AND sc.deleted=false
      LEFT JOIN opm.account S ON S.claim_number = B.claim_number AND S.deleted=false
      LEFT JOIN opm.account_holder ah ON S.account_holder_id=ah.id AND ah.deleted=false
      LEFT JOIN opm.form_type f ON f.id = S.form_type_id AND f.deleted=false
      LEFT OUTER JOIN opm.account_status LAS ON LAS.id = S.account_status_id AND LAS.deleted=false
      LEFT JOIN opm.payment pm ON pm.account_id = S.id AND pm.deleted=false
      LEFT JOIN opm.payment_status ps ON ps.id = pm.payment_status_id AND ps.deleted=false
    WHERE (B.update_to_completed = true) AND (B.print_initial_bill = false) 
      AND P.pay_trans_payment_amount <> 0
      AND 
      (
        sc.description = 'PostedComplete' AND P.user_inserted = false -- Include all good lockbox payments
        OR
        sc.description = 'PostedComplete' AND P.user_inserted = true AND
            substr (P.pay_trans_block_number, 1, 1) <> '9' -- Do not include the block 90 manual payments.
        OR
        sc.description IN ('VoluntaryContributionsComplete', 'DirectPayLifeComplete') -- CREDITS
      )
      AND A.event_year = l_EventYear
      AND A.event_month = l_EventMonth
      AND A.event_day = l_EventDay
      AND A.deleted=false;
   
    --'Reversed Payments'
    INSERT INTO GLPayments
    SELECT DISTINCT 
      l_RefundDate AS PaymentDate,
      CASE WHEN P.resolved_suspense = true THEN 'RS'
        ELSE 'RP' 
      END AS PaymentType,
      COALESCE (f.name, '') AS RetirementTypeDescription,
      CASE WHEN ah.agency_code = '18' AND f.name = '3108(FERS)'
        THEN true
        ELSE false END AS PostOffice, 
      B.pay_transaction_key,
      -P.pay_trans_payment_amount AS PayTransPaymentAmount, 
      B.pay_trans_status_code, 
      COALESCE(f.id, 0) AS RetirementTypeCode,
      B.account_status_id, 
      COALESCE (LAS.name, '') AS AccountStatusDescription, 
      B.over_payment_amount, 
      B.ach_payment, 
      B.refund_required, 
      B.reversed_payment, 
      B.update_to_completed, 
      P.pay_trans_transaction_date, 
      P.resolved_suspense, 
      ps.name, 
      P.user_inserted, 
      P.scm_claim_number, 
      COALESCE (opm.ClaimantName(S.claim_number), 'Unknown') AS ClaimantName,
      P.scm_date_of_birth
    FROM opm.audit_batch  A 
      INNER JOIN opm.batch_daily_payments  B ON A.id = B.audit_batch_id AND B.deleted=false
      INNER JOIN opm.payment_transaction  P ON B.pay_transaction_key = P.id AND P.deleted=false
      LEFT JOIN opm.account  S ON S.claim_number = B.claim_number AND S.deleted=false
      LEFT JOIN opm.pay_trans_status_code sc ON sc.id = B.pay_trans_status_code AND sc.deleted=false
      LEFT JOIN opm.account_holder ah ON S.account_holder_id=ah.id AND ah.deleted=false
      LEFT JOIN opm.form_type f ON f.id = S.form_type_id AND f.deleted=false
      LEFT OUTER JOIN opm.account_status LAS ON LAS.id = S.account_status_id AND LAS.deleted=false
      LEFT JOIN opm.payment pm ON pm.account_id = S.id AND pm.deleted=false
      LEFT JOIN opm.payment_status ps ON ps.id = pm.payment_status_id AND ps.deleted=false
    WHERE (B.update_to_completed = true) AND (B.print_initial_bill = false) 
      AND sc.description  = 'ReversedComplete' -- DEBITS
      AND A.event_year = l_EventYear
      AND A.event_month = l_EventMonth
      AND A.event_day = l_EventDay
      AND A.deleted=false
    ORDER BY B.pay_transaction_key;
    
    --Refunds
    INSERT INTO GLPayments
    SELECT DISTINCT 
      l_RefundDate AS PaymentDate,
      CASE WHEN P.resolved_suspense = true THEN 'RS'
        ELSE 'RP' 
      END AS PaymentType,
      COALESCE (f.name, '') AS RetirementTypeDescription,
      CASE WHEN ah.agency_code = '18' AND f.name = '3108(FERS)' 
        THEN true
        ELSE false END AS PostOffice, 
      B.pay_transaction_key,
      (Refund.pay_trans_payment_amount + COALESCE(R.amount, 0))
        AS PayTransPaymentAmount, -- The account Credit balance is not a resolved refund
      Refund.pay_trans_status_code, 
      COALESCE(f.id, 0) AS RetirementTypeCode,
      B.account_status_id, 
      COALESCE(LAS.name, '') AS AccountStatusDescription, 
      B.over_payment_amount, 
      B.ach_payment, 
      B.refund_required, 
      B.reversed_payment, 
      B.update_to_completed, 
      Refund.pay_trans_transaction_date, 
      P.resolved_suspense, 
      ps.name,  
      P.user_inserted, 
      P.scm_claim_number, 
      COALESCE(opm.ClaimantName(S.claim_number), 'Unknown') AS ClaimantName,
      P.scm_date_of_birth
    FROM opm.audit_batch A 
      INNER JOIN opm.batch_daily_payments B ON A.id = B.audit_batch_id AND B.deleted=false
      INNER JOIN opm.payment_transaction P ON B.pay_transaction_key = P.id AND P.deleted=false 
      LEFT OUTER JOIN opm.refund_transaction R ON P.id = to_number(R.transaction_key, '99G999D9S') AND R.deleted=false
      INNER JOIN opm.payment_refund_link L ON L.payment_needing_refund = B.pay_transaction_key AND L.deleted=false
      INNER JOIN opm.payment_transaction Refund ON L.refund_for_payment = Refund.id AND Refund.deleted=false
      LEFT JOIN opm.pay_trans_status_code sc ON sc.id = Refund.pay_trans_status_code AND sc.deleted=false
      LEFT JOIN opm.account  S ON S.claim_number = B.claim_number AND S.deleted=false
      LEFT JOIN opm.account_holder ah ON S.account_holder_id=ah.id AND ah.deleted=false
      LEFT JOIN opm.form_type f ON f.id = S.form_type_id AND f.deleted=false
      LEFT OUTER JOIN opm.account_status LAS ON LAS.id = S.account_status_id AND LAS.deleted=false 
      LEFT JOIN opm.payment pm ON pm.account_id = S.id AND pm.deleted=false
      LEFT JOIN opm.payment_status ps ON ps.id = pm.payment_status_id AND ps.deleted=false
    WHERE (B.update_to_completed = true) AND (b.print_initial_bill = false) 
      AND sc.description = 'BatchAutoRefund' -- Refunded to Claimant
      AND A.event_year = l_EventYear
      AND A.event_month = l_EventMonth
      AND A.event_day = l_EventDay
      AND A.deleted=false
    ORDER BY B.pay_transaction_key;

    --'Credit balance refunds on Manual Refunds'
    INSERT INTO GLPayments 
    SELECT DISTINCT 
      l_RefundDate AS PaymentDate,
      'RP' AS PaymentType,
      COALESCE (f.name, '') AS RetirementTypeDescription,
      CASE WHEN ah.agency_code = '18' AND f.name = '3108(FERS)'  
        THEN true
        ELSE false END AS PostOffice, 
      B.pay_transaction_key,
      COALESCE(-R.amount, 0) AS PayTransPaymentAmount,
      B.pay_trans_status_code, 
      COALESCE(f.id, 0) AS RetirementTypeCode,
      B.account_status_id, 
      COALESCE(LAS.name, '') AS AccountStatusDescription, 
      B.over_payment_amount, 
      B.ach_payment, 
      B.refund_required, 
      B.reversed_payment, 
      B.update_to_completed,
      R.refund_date AS PayTransTransactionDate,
      false, 
      '' AS HistoryPayment, 
      false, 
      S.claim_number, 
      COALESCE(opm.ClaimantName(S.claim_number), 'Unknown') AS ClaimantName,
      ah.birth_date
    FROM opm.audit_batch  A 
      INNER JOIN opm.batch_daily_payments  B ON A.id = B.audit_batch_id AND B.deleted=false-- DEBITS
      INNER JOIN opm.refund_transaction R ON to_number(R.transaction_key, '99G999D9S') = B.pay_transaction_key AND R.deleted=false
      LEFT JOIN opm.pay_trans_status_code sc ON sc.id = B.pay_trans_status_code AND sc.deleted=false
      LEFT JOIN opm.account S ON S.claim_number = B.claim_number AND S.deleted=false
      LEFT JOIN opm.account_holder ah ON S.account_holder_id=ah.id AND ah.deleted=false
      LEFT JOIN opm.form_type f ON f.id = S.form_type_id AND f.deleted=false
      LEFT OUTER JOIN opm.account_status LAS ON LAS.id = S.account_status_id AND LAS.deleted=false 
      LEFT JOIN opm.payment pm ON pm.account_id = S.id AND pm.deleted=false
      LEFT JOIN opm.payment_status ps ON ps.id = pm.payment_status_id AND ps.deleted=false
    WHERE (B.update_to_completed = true) AND (B.print_initial_bill = false) 
      AND sc.description = 'SuspenseRefundComplete' -- DEBITS
      AND A.event_year = l_EventYear
      AND A.event_month = l_EventMonth
      AND A.event_day = l_EventDay
      AND A.deleted=false
    ORDER BY B.pay_transaction_key;
    
    CREATE temp TABLE GLAllocations on commit drop AS
    SELECT G.pay_transaction_key, COALESCE(L.code, '??') AS GLCode, G.PaymentType
    FROM GLPayments G 
      LEFT JOIN opm.gl_code L ON G.PaymentType = L.payment_type AND L.name = 'General-Ledger-Code'AND L.deleted=false;

     
    CREATE temp TABLE GLFiller on commit drop AS
    SELECT G.pay_transaction_key, COALESCE(L.code, '??') AS GLFiller, G.PaymentType
    FROM GLPayments G 
      LEFT JOIN opm.gl_code L
        ON G.PaymentType = L.payment_type AND G.PostOffice = L.post_office
          AND L.name = 'Filler-Position' AND L.deleted=false
          AND G.RetirementTypeCode = L.retirement_type_id;

    
    CREATE temp TABLE GLAccountingCode on commit drop AS
    SELECT G.pay_transaction_key, COALESCE(L.code, '??') AS GLAccountingCode, G.PaymentType
    FROM GLPayments G 
      LEFT JOIN opm.gl_code L
        ON G.PaymentType = L.payment_type AND G.PostOffice = L.post_office
          AND L.name = 'Accounting-Code' AND L.deleted=false
          AND G.RetirementTypeCode = L.retirement_type_id;

    
    CREATE temp TABLE RevenueSourceCode on commit drop AS
    SELECT G.pay_transaction_key, COALESCE(L.code, '??') AS RevenueSourceCode, G.PaymentType
    FROM GLPayments G 
      LEFT JOIN opm.gl_code L
        ON G.PaymentType = L.payment_type 
          AND L.name = 'Revenue-Source-Code' AND L.deleted=false;
    
    SELECT code FROM opm.gl_code WHERE name = 'Feeder-System-ID' AND deleted=false ORDER BY id limit 1 INTO l_FeederSystemID;
    SELECT code FROM opm.gl_code WHERE name = 'Revenue-Source-Code' AND deleted=false ORDER BY id limit 1 INTO l_RevenueSourceCode;
    
    SELECT COALESCE(SUM (PayTransPaymentAmount), 0) FROM GLPayments G WHERE G.RetirementTypeDescription = '3108(FERS)' AND
                                                                          PostOffice = false INTO l_TotalNonPostalFERS;
    SELECT COALESCE(SUM (PayTransPaymentAmount), 0) FROM GLPayments G WHERE G.RetirementTypeDescription = '3108(FERS)' AND 
                                                                          PostOffice = true INTO l_TotalPostalFERS;
    SELECT COALESCE(SUM (PayTransPaymentAmount), 0) FROM GLPayments G WHERE G.RetirementTypeDescription = '2803(CSRS)' 
                                                                    INTO l_TotalCSRS;

    return QUERY SELECT l_FeederSystemID AS feeder_system_id,
      CAST(P.PaymentType as VARCHAR),
      to_timestamp(to_char(P.PaymentDate, 'MM/DD/YYYY'), 'MM/DD/YYYY')::TIMESTAMP AS PaymentDate,
      opm.JulianDate (P.PaymentDate, 7) AS JulianDate,
      opm.JulianDate (pDayToProcess, 3) AS JulianDateReport,
      F.GLFiller,
      A.GLCode, 
      CASE WHEN LTRIM(C.GLAccountingCode) = 'SYS1' 
      THEN 0
      WHEN LTRIM(C.GLAccountingCode) = 'SYS2' 
      THEN 0
      ELSE CASE to_char(current_timestamp, 'MM')
          WHEN '10' THEN CAST(to_number(to_char(current_timestamp, 'YYYY'), '0000') + 1 AS INTEGER)
          WHEN '11' THEN CAST(to_number(to_char(current_timestamp, 'YYYY'), '0000') + 1 AS INTEGER)
          WHEN '12' THEN CAST(to_number(to_char(current_timestamp, 'YYYY'), '0000') + 1 AS INTEGER)
          ELSE CAST(to_number(to_char(current_timestamp, 'YYYY'), '0000') AS INTEGER) END 
      END AS FiscalYear,
      C.GLAccountingCode,
      P.PayTransPaymentAmount AS ReceiptAmount,
      R.RevenueSourceCode, 
      CASE WHEN P.PostOffice = true THEN CAST('Postal' as VARCHAR) ELSE CAST('Non-Postal' as VARCHAR) END AS Agency,
      P.pay_transaction_key, P.scm_claim_number, P.scm_date_of_birth, 
      P.RetirementTypeDescription,
      P.ClaimantName, 
      pDayToProcess AS PrintDate,
      l_TotalNonPostalFERS AS TotalNonPostalFERS,
      l_TotalPostalFERS AS TotalPostalFERS,
      l_TotalCSRS AS TotalCSRS,
      opm.JulianDate(current_date, 7) AS JulianNow
    FROM GLPayments P
      INNER JOIN GLAllocations A ON A.pay_transaction_key = P.pay_transaction_key AND P.PaymentType = A.PaymentType
      INNER JOIN GLFiller F ON F.pay_transaction_key = P.pay_transaction_key AND P.PaymentType = F.PaymentType
      INNER JOIN GLAccountingCode C ON C.pay_transaction_key = P.pay_transaction_key AND P.PaymentType = C.PaymentType
      INNER JOIN RevenueSourceCode R ON R.pay_transaction_key = P.pay_transaction_key AND P.PaymentType = R.PaymentType
    ORDER BY P.PaymentType, A.GLCode, F.GLFiller, C.GLAccountingCode;

END;
$BODY$
LANGUAGE plpgsql;

-- ======================================================
-- Stored Procedure: OPM.BatchDailyGLFile
--
-- Description: this store procedure will get the GL records set via calling the opm.BatchDailyGL function.
--
-- IN parameters :
-- @pDayToProcess - the date time to process.
--
-- Return - the GL records set.
--
-- Author: faeton, TCSASSEMBLER
-- Copyright (C) 2014 TopCoder Inc., All rights reserved.
-- ======================================================
CREATE OR REPLACE FUNCTION OPM.BatchDailyGLFile(pDayToProcess TIMESTAMP)
 RETURNS TABLE(
    feederSystemId VARCHAR(50), 
    paymentDate TIMESTAMP,
    julianDate VARCHAR(7),
    gLFiller VARCHAR(50),
    gLCode VARCHAR(40),
    fiscalYear INTEGER,
    gLAccountingCode VARCHAR(40),
    recipientAmount DECIMAL(10,6),
    revenueSourceCode VARCHAR(256),
    numberOfPayments INTEGER,
    agency varchar(40),
    printDate TIMESTAMP,
    totalNonPostalFers DECIMAL(10,2),
    totalPostalFers DECIMAL(10,2),
    totalCsrs DECIMAL(10,2),
    julianNow VARCHAR(7)) AS
$BODY$
BEGIN
    CREATE temp TABLE temp_all_details on commit drop AS

    SELECT 

        gl.feeder_system_id,
        gl.payment_type,
        gl.payment_date,
        gl.julian_date,
        gl.julian_date_report,
        gl.gl_filler,
        gl.gl_code,
        gl.fiscal_year,
        gl.gl_accounting_code,
        gl.revenue_source_code,
        gl.agency,
        gl.pay_transaction_key,
        gl.scm_claim_number,
        gl.scm_date_Of_birth,
        gl.claimant_name,
        gl.print_date,
        gl.total_non_postal_fers,
        gl.total_postal_fers,
        gl.total_csrs,
        gl.julian_now,
        gl.recipient_amount,
        gl.scm_retirement_type_description
    FROM opm.BatchDailyGL(pDayToProcess) gl;

    RETURN QUERY SELECT
        feeder_system_id,
        payment_date,
        opm.JulianDate(payment_date, 7) AS JulianDate, 
        gl_filler,
        gl_code,
        fiscal_year, 
        gl_accounting_code, 
        SUM (recipient_amount) AS ReceiptAmount, 
        revenue_source_code, 
        CAST(COUNT (pay_transaction_key) AS INTEGER) AS NumberOfPayments, 
        t.agency, 
        print_date, 
        total_non_postal_fers, 
        total_postal_fers, 
        total_csrs, 
        julian_now
    FROM temp_all_details t

    GROUP BY feeder_system_id, JulianDate, payment_date, gl_filler, gl_code, fiscal_year, 
        t.agency, revenue_source_code, gl_accounting_code, 
        print_date, total_non_postal_fers, total_postal_fers, total_csrs, julian_now
    ORDER BY payment_date, gl_filler, gl_code, gl_accounting_code;
END;
$BODY$
LANGUAGE plpgsql;