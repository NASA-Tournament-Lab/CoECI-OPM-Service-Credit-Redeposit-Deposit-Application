# This is the common DSL resource file for interest calculation.
#
# version 1.1 - OPM - Rules Engine - Scenarios Conversion 2 - Interest Update Assembly 
# - Updated rules for this assembly
#
# @author albertwang, TCSASSEMBLER
# @version 1.1

# Fields Compare Conditions
[condition][]and has retirement type as {value}=, retirementType != null && retirementType.name == {value}
[condition][]and has period type as {value}=, periodType != null && periodType.name == {value}
[condition][]and has service type as {value}=, serviceType != null && serviceType.name == {value}
[condition][]and has pay type as {value}=, payType != null && payType.name == {value}

# Fields
[condition][]begin date=beginDate
[condition][]end date=endDate
[condition][]total earnings=totalEarnings
[condition][]total deduction=totalDeduction
[condition][]retirement type=retirementType

# Validations
[condition][]has no {field}={field} == null
[condition][]has {field1} later than {field2}={field1} > {field2}
[condition][]has {field1} earlier than {field2}={field1} < {field2}

# Main Conditions
[condition][]There is an Extended Service Period that mid-point has not calculated yet=$extendedServicePeriod : ExtendedServicePeriod(midPoint == null, validationErrors.size() == 0)
[condition][]There is an Extended Service Period that {constraints} = $extendedServicePeriod : ExtendedServicePeriod({constraints})
[condition][]The message {message} has not been reported yet=eval(!$extendedServicePeriod.getValidationErrors().contains({message})) 

[condition][]interest accrual date has not calculated yet=interestAccrualDate == null, validationErrors.size() == 0
[condition][]and is Conner Case=, connerCase == true
[condition][]and is after {value}=, beginDate > {value}

# Consequences
[consequence][]Calculate mid-point=modify($extendedServicePeriod) { setMidPoint(calculateMidPoint($extendedServicePeriod)); }

# Report Error, add to global variable validationErrors
[consequence][]Report error {message}=$extendedServicePeriod.getValidationErrors().add({message});update($extendedServicePeriod);

# Set Interest Accrual Date
[consequence][]Set Interest Accrual Date as "01-Jan-1999"=modify($extendedServicePeriod) { setInterestAccrualDate(calculateConnerCaseInterestAccuralDate($extendedServicePeriod)); }