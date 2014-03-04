# This is the common DSL resource file for interest calculation.
#
# @author albertwang, TCSASSEMBLER
# @version 1.0


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


# Consequences
[consequence][]Calculate mid-point=modify($extendedServicePeriod) { setMidPoint(calculateMidPoint($extendedServicePeriod)); }

# Report Error, add to global variable validationErrors
[consequence][]Report error {message}=$extendedServicePeriod.getValidationErrors().add({message});update($extendedServicePeriod);