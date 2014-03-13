/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.entities.batchprocessing;

/**
 * <p>Represents the type of the lookup payment application order.</p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
public enum LookUpPaymentApplicationOrder {

    /** Represents the default user order.*/
    DEFAULT_USER_ORDER,

    /** Represents the fers order.*/
    FERS,

    /** Represents the order of post 982 redeposit.*/
    POST_9_82_REDEPOSIT,

    /** Represents the order of pre 1082 redeposit.*/
    PRE_10_82_REDEPOSIT,

    /** Represents the order of post 982 deposit.*/
    POST_9_82_DEPOSIT,

    /** Represents the order of pre 1082 deposit.*/
    PRE_10_82_DEPOSIT;
}

