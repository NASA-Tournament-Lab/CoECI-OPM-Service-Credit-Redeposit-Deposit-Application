/*
    Copyright 2014 OPM.gov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package gov.opm.scrd.entities.application;

import gov.opm.scrd.entities.common.IdentifiableEntity;

/**
 * <p>
 * Represents the entity specifying holiday.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class Holiday extends IdentifiableEntity {
    /**
     * <p>
     * Represents the name of holiday. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String holiday;
    /**
     * <p>
     * Represents the flag specifying whether holiday occurs on exact date. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private Boolean exactDate;
    /**
     * <p>
     * Represents the week day of holiday. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer weekDay;
    /**
     * <p>
     * Represents the month number of holiday. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Integer monthNumber;
    /**
     * <p>
     * Represents the day of month of holiday. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Integer dayOfMonth;
    /**
     * <p>
     * Represents the week of month of holiday. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Integer weekOfMonth;
    /**
     * <p>
     * Represents the id of holiday. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private Integer holidayId;

    /**
     * Creates an instance of Holiday.
     */
    public Holiday() {
        // Empty
    }

    /**
     * Gets the name of holiday.
     *
     * @return the name of holiday.
     */
    public String getHoliday() {
        return holiday;
    }

    /**
     * Sets the name of holiday.
     *
     * @param holiday
     *            the name of holiday.
     */
    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    /**
     * Gets the flag specifying whether holiday occurs on exact date.
     *
     * @return the flag specifying whether holiday occurs on exact date.
     */
    public Boolean getExactDate() {
        return exactDate;
    }

    /**
     * Sets the flag specifying whether holiday occurs on exact date.
     *
     * @param exactDate
     *            the flag specifying whether holiday occurs on exact date.
     */
    public void setExactDate(Boolean exactDate) {
        this.exactDate = exactDate;
    }

    /**
     * Gets the week day of holiday.
     *
     * @return the week day of holiday.
     */
    public Integer getWeekDay() {
        return weekDay;
    }

    /**
     * Sets the week day of holiday.
     *
     * @param weekDay
     *            the week day of holiday.
     */
    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    /**
     * Gets the month number of holiday.
     *
     * @return the month number of holiday.
     */
    public Integer getMonthNumber() {
        return monthNumber;
    }

    /**
     * Sets the month number of holiday.
     *
     * @param monthNumber
     *            the month number of holiday.
     */
    public void setMonthNumber(Integer monthNumber) {
        this.monthNumber = monthNumber;
    }

    /**
     * Gets the day of month of holiday.
     *
     * @return the day of month of holiday.
     */
    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    /**
     * Sets the day of month of holiday.
     *
     * @param dayOfMonth
     *            the day of month of holiday.
     */
    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    /**
     * Gets the week of month of holiday.
     *
     * @return the week of month of holiday.
     */
    public Integer getWeekOfMonth() {
        return weekOfMonth;
    }

    /**
     * Sets the week of month of holiday.
     *
     * @param weekOfMonth
     *            the week of month of holiday.
     */
    public void setWeekOfMonth(Integer weekOfMonth) {
        this.weekOfMonth = weekOfMonth;
    }

    /**
     * Gets the id of holiday.
     *
     * @return the id of holiday.
     */
    public Integer getHolidayId() {
        return holidayId;
    }

    /**
     * Sets the id of holiday.
     *
     * @param holidayId
     *            the id of holiday.
     */
    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }
}
