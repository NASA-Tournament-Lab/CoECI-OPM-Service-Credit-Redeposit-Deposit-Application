# This is the common DSL resource file for deduction calculation.
#
# version 1.1 - OPM - Rules Engine - Scenarios Conversion 2 - Deduction Update Assembly 
# - Updated rules for this assembly
#
# @author albertwang, TCSASSEMBLER
# @version 1.1

# name entities
[condition][]retirement type=retirementType != null, retirementType.name
[condition][]period type=periodType != null, periodType.name
[condition][]service type=serviceType != null, serviceType.name
[condition][]pay type=payType != null, payType.name
[condition][]appointment type=appointmentType != null, appointmentType.name

#fields
[condition][]begin date=beginDate
[condition][]end date=endDate
[condition][]amount=amount

#field check
[condition][]has {field} as {value}={field} == {value}
[condition][]and has {field} as {value}=, {field} == {value}
[condition][]and has {field} as {value}=, {field} == {value}
[condition][]and has {field} as {value}=, {field} == {value}
[condition][]and has {field} as {value}=, {field} == {value}
[condition][]has no {field}={field} == null
[condition][]and is Conner Case=, connerCase == true
[condition][]and does not have Interest Accrual Date=, interestAccrualDate == null

#date check
[condition][]has {field1} later than {field2}={field1} > {field2}
[condition][]has {field1} earlier than {field2}={field1} < {field2}
[condition][]and is after {date}=, beginDate >= {date}
[condition][]and is before {date}=, endDate < {date}
[condition][]and spans {date}=, beginDate < {date}, endDate >= {date}

#Related to Service Period
[condition][]There is a Service Period that {constraints}=$servicePeriod : ServicePeriod({constraints})
[condition][]There is a valid Service Period that {constraints}=$servicePeriod : ServicePeriod(validationErrors.size() == 0, {constraints})
[condition][]The message {message} has not been reported yet=eval(!$servicePeriod.getValidationErrors().containsValue({message})) 

#Calculations
[consequence][]Calculate earnings in service period for annual salary=calculateEarningsForAnnualSalary($servicePeriod);
[consequence][]Calculate earnings in service period for hourly rate with multiplier {multiplier}=calculateEarningsForHourlyRate($servicePeriod, {multiplier});
[consequence][]Calculate earnings in service period for monthly salary=calculateEarningsForMonthlySalary($servicePeriod);
[consequence][]Calculate earnings in service period as the amount=$servicePeriod.setEarnings($servicePeriod.getAmount());

[consequence][]Calculate deduction in service period at deduction rate {deductionRate}=calculateDeduction($servicePeriod, {deductionRate});
[consequence][]Set deduction in service period to {deductionValue}=$servicePeriod.setDeduction(BigDecimal.valueOf({deductionValue}));
[consequence][]Calculate deduction in service period as the earnings=$servicePeriod.setDeduction($servicePeriod.getEarnings());
[consequence][]Set earnings in service period to {earningsValue}=$servicePeriod.setEarnings(BigDecimal.valueOf({earningsValue}));
[consequence][]Continue Extended Service Period with no more than {breakThresholdInDays} days break=continueExtendedServicePeriod(extendedServicePeriods, $servicePeriod, {breakThresholdInDays}, servicePeriodSplitDates);

[consequence][]Split Service Period at {date}=splitServicePeriod($servicePeriod, parseDate({date}), kcontext);

# Report Error, add to global variable validationErrors
#[consequence][]Report error {message}=$servicePeriod.getValidationErrors().add({message});update($servicePeriod);
[consequence][]Report error {message} on {field}=$servicePeriod.getValidationErrors().put({field}, {message});update($servicePeriod);