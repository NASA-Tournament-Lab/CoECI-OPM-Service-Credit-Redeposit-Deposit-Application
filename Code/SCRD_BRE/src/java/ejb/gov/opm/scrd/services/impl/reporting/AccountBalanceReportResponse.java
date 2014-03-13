/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.util.Date;
import java.util.List;
import java.util.Map;

import gov.opm.scrd.services.impl.BaseReportResponse;

/**
 * This class represents the response for the namesake report.
 * <p>
 * <strong>Thread-safety:</strong> This class is mutable and not thread - safe.
 * </p>
 * 
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class AccountBalanceReportResponse extends BaseReportResponse {
	/**
	 * Represents claim number response field. It is accessible by getter and
	 * modified by setter. It can be any value. The default value is null.
	 */
	private String csd;

	/**
	 * Represents namesake response field. It is accessible by getter and
	 * modified by setter. It can be any value. The default value is null.
	 */
	private Map<Date, List<AccountBalanceReportResponseItem>> items;

	/**
	 * Creates a new instance of the {@link AccountBalanceReportResponse} class.
	 */
	public AccountBalanceReportResponse() {
		super();
	}

	/**
	 * Gets the claim number response field.
	 * 
	 * @return the claim number response field.
	 */
	public String getCsd() {
		return csd;
	}

	/**
	 * Sets the claim number response field.
	 * 
	 * @param csd
	 *            the field value.
	 */
	public void setCsd(String csd) {
		this.csd = csd;
	}

	/**
	 * Gets the response items.
	 * 
	 * @return the response items.
	 */
	public Map<Date, List<AccountBalanceReportResponseItem>> getItems() {
		return items;
	}

	/**
	 * Sets the response items.
	 * 
	 * @param items
	 *            the response items.
	 */
	public void setItems(
			Map<Date, List<AccountBalanceReportResponseItem>> items) {
		this.items = items;
	}
}
