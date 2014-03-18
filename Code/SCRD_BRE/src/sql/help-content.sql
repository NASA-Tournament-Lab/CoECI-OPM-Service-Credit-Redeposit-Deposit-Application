
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (1, false, 'Getting Started', $$$<p>You can start the web application like any other website by clicking <a href="">here</a> in your preferred navigator.</p>
<p>You will be asked to provide your username and password. Use the one given to you by the Help Desk.</p>$$$, $$$<h2> Prerequisites </h2>
Before a new employee can use the Service Credit Application certain tasks must be performed.
<ul>
	<li>The supervisor must ask the Help Desk to add the user to the Service Credit Active Directory Global Group.</li>

	<li>The supervisor must ask the LANDesk to provide the new employee with a username and password.</li>


	<li>The supervisor must use the Service Credit program to enter the user's information and choose their "role" in the system.</li>

</ul>


<h2>Starting the web application</h2>
You can start the web application like any other website. Open you preferred navigator. For example
you can use Internet Explorer, Mozilla Firefox, Google Chrome, Opera. Enter the SCRD address in the
address bar. You will be asked to provide your username and password. Use the one given to you by
the Help Desk.
<p>
According to your role you might not see some features. If a feature of the Service Credit System is
not available (may be "grayed out"), and the user feels access is needed, refer to the group
supervisor who is able to grant access to certain functions based on a defined user role.</p>

<h2>Main Application Menus</h2>
Here is the main application windows.

<figure>
	<img src="i/help/mainApplicationPage.png" alt="Main Application Page" width="700px">
  	<figcaption>Main Application Page.</figcaption>
</figure>



You can see the main menu. It will be present at every page.
You can select the relevant checkbox on the previous figure to make any
page your home. The home page will be the first page you will see when you visit the web
application after log in. By default your "Account Details Page" will be the home page. If you think
you will be doing more approval jobs, you can set the approval page as your home

<p> </p>
<ul>
	<li>Click "View Account" to search for account.</li>
	<li>Click "Create New Account" to create new account.</li>
	<li>Click "Work Queue" to view the Work Queue Page.</li>
	<li>Click "Reports" to go to the Reports Page.</li>
<li>Click "Suspense" to go to the Suspense Page.</li>
<li>Click "Approval" to go to the Approval Page.</li>
<li>Click "Payments" to go to the Payments Page.</li>
<li>Click "Tools" to go to the Tools Page.</li>
<li>Click "Admin" to go to the Admin Page.</li>
<li>Click "Notification Log" to go to the Notification Page.</li>


<li>Click "Help" to go to the Help Page.</li>

</ul>


$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (2, false, 'General Information', $$$The new Service Credit Redeposit &amp; Deposit (SCRD) web application is developed to manage Service
Credit accounts, a process that was formerly handled by a Windows application.
<p>Accounts are established for federal employees for periods of federal employment that are either not covered by
retirement deductions (deposits), or were covered and later refunded (re-deposits). There are two
primary groups that perform operations in the service credit system: "Refunds and Deposits" and "Funds Management" Division.</p>

$$$, $$$<p>The new Service Credit Redeposit &amp; Deposit (SCRD) web application is developed to manage Service
Credit accounts, a process that was formerly handled by a Windows application. Accounts are
established for federal employees for periods of federal employment that are either not covered by
retirement deductions (deposits), or were covered and later refunded (re-deposits). There are two
primary groups that perform operations in the service credit system: "Refunds and Deposits" and
"Funds Management" Division.</p>

<h2>Web Application Sections</h2>
The users interact with the Service Credit System via the SCRD web site. This
web application updates the global database for the user.
The Batch Service is installed on one application server. It is the back end of the web application. The
administrators may monitor its progress via the front end using the notifications logs. This back end
reads in the Lockbox bank data and performs the nightly processing.


<h2>General Work Flow</h2>
When federal employees apply to make a deposit or redeposit, they submit Standard form 2803, or
Standard Form 3108 depending on whether they are currently enrolled in the Civil Service
Retirement System (CSRS) or Federal Employees Retirement System (FERS). In either case, the
employee completes part A of the application, then requests that the employing agency complete
part B of the application. The employing agency then sends the application to the Refunds Section in
Boyers. That office determines eligibility, calculates the amount due, and mails an initial bill (RI36-
23) to the claimant.
<p>
After receiving the bill, the claimant will send payments and changes of address through the Lockbox
bank. If the Lockbox data has no errors, the payment will be transmitted from the bank to OPM.
Every business day, OPM receives a file with Service Credit, Voluntary Contributions, and Direct Pay
Life Insurance. This file is split and distributed to these three systems. As soon as the Service Credit
file is available, the "batch" process will read it into the database. Users may notice this increase in
"Pending" payments in the late morning. There will be from 100 to 200 new payments per day.
Some of these will be "Suspense" so a user could be working on the last suspended payment, hit the
"Refresh" button and see twenty new suspense payments appear.</p>


The Batch Service processes payments immediately in order to post the good ones. The logic is
shown in the following diagram.

<figure>
  <img src="i/help/paymentProcessingLogic.png" alt="payment Processing Logic" width="700px">
  <figcaption>Payment Processing Logic.</figcaption>
</figure>


<h2>Nightly Payment Updates</h2>
Every night around 8:30 PM, the "Batch" process will process all the "Pending" payments. This
process will post all payments from the lockbox as well as post any payments corrected or redirected
to their proper account. After posting, new balances are calculated for the claimants and new bills
generated in Boyers.
This table shows the posting states from 'Pending' to 'Complete'. General Ledger (GL) entries are
only made after a payment is 'Complete'. This is different from the previous system that entered
the GLs as the payments came in. Suspense amounts went to GL 0504.

<table class="helpTbl stdTbl" border="1" cellspacing="0" cellpadding="0">

    <thead> 

       <tr><th >
          Before Batch
      </th>
      <th >
          After Batch
      </th>
        <th >
          GL Code
      </th>
        </tr>
    </thead>

<tbody>
   <tr><td >
      Posted - Pending
  </td>
  <td >
      Posted - Complete
  </td>
  <td >
      GL 0501 Debit
  </td>
</tr><tr><td >
Reversed - Pending
</td>
<td >
  Reversed - Complete
</td>
<td >
  GL 0501 Credit
</td>
</tr><tr><td >
Voluntary Contributions - Pending
</td>
<td >
  Voluntary Contributions - Complete
</td>
<td >
  GL 0504 Credit</p>
</td>
</tr><tr><td >
Direct Pay Life - Pending
</td>
<td >
  Direct Pay Life - Complete
</td>
<td >
  GL 0504 Credit
</td>
</tr><tr><td >
Refund Pending
</td>
<td >
  Refund Complete
</td>
<td >
  GL 0504 Credit<span>  </span>&amp; GL 0801 Debit
</td>
</tr></tbody>
<caption>Payment Posting states.</caption>
</table>
<p></p>


<h2>Nightly Reports</h2>
These reports are printed every night. Four reports are printed in Boyers while the fifth is printed in
DC

<table class="helpTbl stdTbl" border="1" cellspacing="0" cellpadding="0">

    <thead>
        <tr><th >
  Location
  </th>
  <th >
  Report
  </th>
  <th >
  Action
  </th>
  <th >
  Description
  </th>
 </tr>
    </thead>


<tbody><tr><td >
  Boyers
  </td>
  <td >
  Payment Invoices
  </td>
  <td >
  Mail
  </td>
  <td >
  RI36-18 receipt for a payment and updated balance and
  interest for the account
  </td>
 </tr><tr><td >
  Boyers
  </td>
  <td >
  Initial Bill Invoices
  </td>
  <td >
  Mail
  </td>
  <td >
  RI36-23 first bill on account
  </td>
 </tr><tr><td >
  Boyers
  </td>
  <td >
  Reversal Notices
  </td>
  <td >
  Mail
  </td>
  <td >
  RI36-18 showing payment removed from account
  </td>
 </tr><tr><td >
  Boyers
  </td>
  <td >
  Stop ACH Letters
  </td>
  <td >
  Mail
  </td>
  <td >
  RI16-28 Direct Payment Authorization
  </td>
 </tr><tr><td >
  DC
  </td>
  <td >
  Refund Memos
  </td>
  <td >
  Authorize
  </td>
  <td >
  Voucher for Refund of
  Excess Recovery Payment
  </td>
 </tr></tbody>
<caption> Generated Reports.</caption>
</table>

<p></p>
<h2>Bank Input</h2>
The bank input process that was established with the former mainframe-based service credit system
(SCRD) still remains in effect. On a daily basis the lockbox (US Bank in St. Louis) sends bank data via
host-to-host transmission to OPM's Data Center Group for three systems (Service Credit, Voluntary
Contributions, and Life Insurance). The reports that are generated in this process will continue to be
produced showing the bank input, as well as the totals and errors that occurred during host-to-host
and initial processing. Rather than send the service credit bank data to another mainframe
procedure as in the past, this file is converted to a text file format for processing in the new system.

<h2>Interfacing with Other Systems</h2>
Transferring money from the Service Credit System to Voluntary Contribution and Direct Pay Lift
Insurance is still done manually. The application will generate the proper paperwork but must be
signed and delivered to the correct office.
The GL File is made automatically by the batch service and loads data indirectly via a daily
ConnectDirect upload.
$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (3, false, 'Account Details Page', $$$You can access the Account Details Page after searching for an account, or just after
creating a new account$$$, $$$You can access the Account Details Page after searching for an account, or just after
<a href="account/viewCreate?step=createAccount&requireNew=true">creating a new account</a>.

It consists of an "Account Summary" section, "Account Notes" section, an "Employee" tab, a "Service
History" tab and a "Payment History" tab.


<h2>Account Summary</h2>
You can expand/hide the Account Summary by clicking on it. It shows a summary of the account
information.

<figure>
  <img src="i/help/accountSummary.png" alt="accountSummary" width="700px">
  <figcaption>Account Summary.</figcaption>
</figure>





<h2>Summary and Statement Print plus Refreshing</h2>
You can print the summary by clicking on "Print Summary" button at the top of the page.
To display a copy of the most recent statement produced for an account, click the "Reprint
Statement" button at the top of. The statement is reproduced in the Reports Viewer.
To update the information to the most recent one click on "Refresh" at the top of the page.

<figure>
  <img src="i/help/summaryAndStatementPrint.png" alt="Summary And Statement Print" width="700px">
  <figcaption>Summary And Statement Print.</figcaption>
</figure>

$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (4, false, 'Account Notes', $$$Account notes are messages, or descriptions of account activity that may be entered by any user of
the Service Credit system, and seen by every other user of the system. Only the originator of the
note may edit or delete the note.
$$$, $$$Account notes are messages, or descriptions of account activity that may be entered by any user of
the Service Credit system, and seen by every other user of the system. Only the originator of the
note may edit or delete the note.
<h2>Create Account Note</h2>
To create a new note, go to the "Account Details" page. Identify the "Account Notes" section. Then,
click the "Add" button. Note that you must have created a new account or have retrieved a claimant
account before adding note. Enter the note in the "Add Account Notes" popup window. Press the
"Save" button to save the note.

<figure>
  <img src="i/help/createAccountNoteStep1.png" alt="createAccountNoteStep1" width="700px">
  <figcaption>Create Account Note Step1.</figcaption>
</figure>

<figure>
  <img src="i/help/createAccountNoteStep2.png" alt="Create Account Note Step2 - Saving" width="700px">
  <figcaption>Create Account Note Step2 - Saving.</figcaption>
</figure>




<h2>Edit Account Note</h2>
To edit a note, select the note then click the "Edit" button. You will see the "Account Notes Edit"
popup windows. Enter your note and click "Save" to save the changes. You can also double-click the
note you want to edit; then you will directly see "Account Notes Edit" popup windows. Click the
"Edit" button to edit the note. Click the "Save" button to save the new changes.

<figure>
  <img src="i/help/editAccountNoteStep1.png" alt="Edit Account Note Step1 - Select the note" width="700px">
  <figcaption>Edit Account Note Step1 - Select the note.</figcaption>
</figure>

<figure>
  <img src="i/help/editAccountNoteStep2.png" alt="Edit Account Note Step2 - Saving" width="700px">
  <figcaption>Edit Account Note Step2 - Saving.</figcaption>
</figure>


<h2>Delete Account Note</h2>
To delete a note, select the note you want to delete. Click the "Delete" button to delete the note.

<figure>
  <img src="i/help/deleteAccountNote.png" alt="Delete Account Note" width="700px">
  <figcaption>Delete Account Note.</figcaption>
</figure>




$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (5, false, 'Employee Tab', $$$The employee tab allows you to view and edit basic information such as the name, birth date, address and status of an account.
$$$, $$$It allows you to view and edit basic information such as:
<ul>
<li>Status (Active, History, etc) of the account</li>
<li>Name</li>
<li>Birthdate</li>
<li>SSN</li>
<li>Address</li>
<li>Telephone</li>
<li>Location of Employment</li>
</ul>

<figure>
  <img src="i/help/employeeTabViewMode.png" alt="Employee Tab View Mode" width="700px">
  <figcaption>Employee Tab View Mode.</figcaption>
</figure>



To edit the infomation, click on the "Edit Basic Info" button to make the necessary changes. When done, click on the "Save"
button to save the changes.

On the "Edit Basic Info" page you can change the account status.

<figure>
  <img src="i/help/employeeTabEditMode.png" alt="Employee Tab Edit Mode" width="700px">
  <figcaption>Employee Tab Edit Mode.</figcaption>
</figure>


<h2>Changing Account Status</h2>
A Service Credit account (case) is assigned a status based on the activity that has occurred and stage
of processing for that account. In the normal Service Credit workflow, the current status of each
account must be one of the following:
<ul>
 <li>New Account</li>
 <li>Accepted - Record Search in Progress</li>
<li>Rejected - Returned For Correction</li>
<li>Rejected - Applicant Not Eligible</li>
<li>Ready For Processing - Unassigned</li>
<li>Processing - Assigned</li>
<li>Processing - Reassigned</li>
<li> Processing Complete - Ready For Review</li>
<li> Processing Complete - Ready For Billing</li>
<li>Initial Bill Triggered</li>
 <li>Active</li>
<li> Account Closed</li>
<li>History</li>
</ul>

<figure>
  <img src="i/help/accountStatusValues.png" alt="Account Status Values" width="700px">
  <figcaption>Account Status Values.</figcaption>
</figure>



<p></p>
To change the status of an account, click the "Edit" button in the "Basic Information" section on the
Employee Tab. Select a new status from the drop-down list, and click the "Save" button to apply the
change.
$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (6, false, 'Billing Summary Tab', $$$The billing summary tab will allow to view and edit the account billing summary information.
You can access it by clicking on "Billing Summary" in the Account Details Page.
$$$, $$$You can access the billing summary tab by clicking on "Billing Summary" in the Account Details
page. This page will only displays if the associated account has billing information.
It will allow to view and edit the account billing summary information.

<figure>
  <img src="i/help/billingSummaryTabViewMode.png" alt="Billing Summary Tab View Mode" width="700px">
  <figcaption>Billing Summary Tab View Mode.</figcaption>
</figure>




<p></p> You will be able to edit billing summary information such as:
<ul>
 <li>Dates (1st billing date, Last Payment date, Last Transaction date, etc)</li>
<li>Payment amounts for FERS, Post 9/82 Deposit and Redeposit, Pre 10/82 Deposit and
   Redeposit</li>
<li>Interest amounts for FERS, Post 9/82 Deposit and Redeposit, Pre 10/82 Deposit and
   Redeposit</li>
 <li>Total payments for FERS, Post 9/82 Deposit and Redeposit, Pre 10/82 Deposit and Redeposit
Payment order</li>
</ul>
<p></p>
Note that you can adjust the values of the total payments but the five numbers must still add up to the
same value. E.g., for an account with a total of $5000.00 in payments, you may add $1000.00 from
pre-10/82 deposit and add $1000.00 to post-9/30 deposit because the numbers would still add up
the same.
To adjust values, click on the "Edit Billing Info" button. Make the necessary changes. When done,
click the "Save" button to save the changes.


<figure>
  <img src="i/help/billingSummaryTabEditMode.png" alt="Billing Summary Tab Edit Mode" width="700px">
  <figcaption>Billing Summary Tab Edit Mode.</figcaption>
</figure>


<p></p>
When you make a change, you will be required to provide the reason for that.

<figure>
  <img src="i/help/MakingChangesToBilling.png" alt="Making Changes To Billing" width="700px">
  <figcaption>Making Changes To Billing.</figcaption>
</figure>



If you made changes to one of the Additional Interest or Total Payments cells, then the changes will
not take effect immediately. The values will revert back to the originals and a pop up window will tell
you that the request was submitted. The request is also logged in an account note.
$$$);
--INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (7, false, 'Billing Summary Information', $$$@helpbillingSummaryInformationSubject@$$$, $$$@helpbillingSummaryInformationContent@$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (8, false, 'Update Interest', $$$You can update the interest of the account to the current date in the "Billing Summary" tab of the "Account Details" Page.
Click on the button "Update Interest" button.$$$, $$$You can access it on the account details page in the "Billing Summary" tab.
<figure>
  <img src="i/help/UpdateInterest.png" alt="Update Interest" width="700px">
  <figcaption>Update Interest.</figcaption>
</figure>


<p></p>
Click on the "Update Interest" button and a new popup will open.
<figure>
  <img src="i/help/UpdateInterestPopup.png" alt="Update Interest Popup" width="700px">
  <figcaption>Update Interest Popup.</figcaption>
</figure>


<p></p>
The interest is calculated and the new invoice appears. The technician must print out this invoice
and mail it to the claimant. It will not be printed by the nightly batch process.

<figure>
  <img src="i/help/UpdateInterestStatement.png" alt="Update Interest Statement" width="700px">
  <figcaption>Update Interest Statement.</figcaption>
</figure>



If the printer jams or the paper is lost, you can reprint this invoice on the same day that you updated
the interest. The "Update Interest" button is still enabled so you can click it again.
Clicking on it again brings up a new prompt.

<figure>
  <img src="i/help/UpdateAlreadyUpdatedInterestPopup.png" alt="Update an Already Updated Interest Popup." width="700px">
  <figcaption>Update an Already Updated Interest Popup.</figcaption>
</figure>

When you click "OK", the same invoice appears again because the program did not do another calculation.$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (9, false, 'Service History Tab', $$$The service history tab allows you to run calculations, to save them, to trigger bills and to view the calculations results.$$$, $$$You can access the Service History tab in the Account Details page, when clicking on "Service
History".
This tab is used by Boyers but DC users have permissions to view it. Information about the claimant's
service is typed into the upper table. The lower part of the screen is filled after the calculation is
done.

<figure>
  <img src="i/help/ServiceHistoryTab.png" alt="Service History Tab" width="700px">
  <figcaption>Service History Tab.</figcaption>
</figure>


<h2>Service History Versions</h2>
An account can have many calculation versions but only one may be the "official" billing. Users may
try different scenarios for a claimant. Each scenario is a Calculation Version. Choose one from the
drop-down list or click the "New Version" button. To copy the displayed information into a new
version, first check the "Copy Current" box.

<figure>
  <img src="i/help/ServiceHistoryVersion.png" alt="Service History Version" width="700px">
  <figcaption>Service History Version.</figcaption>
</figure>


<h2>Service History Data Grid</h2>
Each line of service is a date range and a description of the service performed during that time
period. Each of the "Type" fields is a drop-down box as shown to the right. Choose the correct value
for each field. You can also enter the amount for the period.

<figure>
  <img src="i/help/ServiceHistoryDataGridInput.png" alt="Service History Data Grid Input" width="700px">
  <figcaption>Service History Data Grid Input.</figcaption>
</figure>




<h2> Calculation Buttons</h2>

<figure>
  <img src="i/help/CalculationButtons.png" alt="Calculation Buttons" width="700px">
  <figcaption>Calculation Buttons.</figcaption>
</figure>
After the service history is entered, click on the "Validate Entries" button to check the dates. If there
is a Refund or Lump Sum, then a dialog box pops up to ask for the date of the actual payment.
This "Validate Entries" button will check the service periods and place a warning icon <img src="i/icon-error.png" alt="error" width="22" height="22" class="vMiddle" />
when there is an issue. Double-click the icon to get help solving the problem.
next to each line
<p></p>
If this claimant has already sent payments to Service Credit, click the "Apply Real Payments" check
box. The payments from the Payment Tab will be applied to the bill. If this box is unchecked, then
only payments entered by the Boyers Refund Section will be applied to the bill. Refund Section
"payment" records are labeled as "Prior Payment Recorded" and do not appear to other groups.
<p></p>
When the service history is ready, enter the correct date in the "Calculate As Of" box and click on the
"Run Calculation" button. The program will calculate the amounts but not save them. It will also
insert account notes with a detailed explanation of the calculation. The "Show Calculation" button
will open a Microsoft Word file with this explanation.<p></p>
Nothing is saved until the "Save Calculation" button is clicked. Click on the "Save Calculation" button
to save the calculation results in the database so others can see them. The "Trigger Bill" button will
be enabled once the results are stored. Only a supervisor can trigger a new initial bill for an active
account.

<figure>
  <img src="i/help/CalculationValidationResults.png" alt="Calculation Validation Results" width="700px">
  <figcaption>Calculation Validation Results.</figcaption>
</figure>




When a bill is initiated, the user will trigger it by clicking this button. Once triggered, it will print out
overnight from the batch service.


<h2> Calculation Results</h2>
<figure>
  <img src="i/help/CalculationResults.png" alt="Calculation Results" width="700px">
  <figcaption>Calculation Results.</figcaption>
</figure>
The service histories are aggregated and the billable service periods shown in the results table. The
payments are applied by type and date. Clicking the "Show Sample Initial Bill" button will display an
initial bill for these results in the Reports Viewer.
$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (10, false, 'Payment History Tab', $$$This tab shows all payments processed by the SCRD. These include checks & ACH from the Lockbox
Bank, checks & credit cards over-the-counter payments, and adjustments. The Refunds Section also
sees their "Prior Payment Recorded" records which represent payments prior to April 2006 or
agency credits.
$$$, $$$This tab shows all payments processed by the SCRD. These include checks &amp; ACH from the Lockbox
Bank, checks &amp; credit cards over-the-counter payments, and adjustments. The Refunds Section also
sees their "Prior Payment Recorded" records which represent payments prior to April 2006 or
agency credits.


<h2>Payment History Table</h2>

<figure>
  <img src="i/help/paymentHistoryTab.png" alt="Payment History Table" width="700px">
  <figcaption>Payment History Table.</figcaption>
</figure>


After an account has been retrieved (using the Account Search Function), the payments for the
account show on the Payment History tab. The grid displays the Batch, Block, &amp; Sequence Number
from the lock box bank. This allows the user to identify the payment vis-à-vis the bank's paperwork.
The Transaction Date is the date that the lock box bank accepted the ACH or check payment. All
payment types for the current claimant account are shown, even if they are unresolved (in
suspense). The "Applied To" order will show "Default User Order" unless the claimant requested
special processing for that particular payment. The User is set to "Automated Batch" if nobody
modified the payment. The last user to work with a payment is named in this column. The "GL"
column shows if this payment will hit the General Ledger or not.
<p></p>
Note that the count &amp; total of valid payments in the data grid match the count &amp; total in the
"Account Summary" section

<h2> Buttons </h2>
The enabled buttons depend upon the user's role and the payment's transaction status.

<table class="helpTbl stdTbl" border="1" cellspacing="0" cellpadding="0">
	<caption>Buttons enabled according to user role on Payment History.</caption>
	<thead><tr><th >
   Button Label
   </th>
   <th >
   Description
   </th>
  </tr></thead>

  <tbody><tr><td >
  Hide Prior / Show
  Prior
  </td>
  <td >
  This button is only
  shown for the Refunds Section and replaces the "Cancel Voucher" button. 
  </td>
 </tr><tr><td >
  Cancel Voucher / Debit
  Voucher / Reverse Payment
  </td>
  <td >
  This button will
  reverse the payment or cancel the adjustment. Its wording depends upon the
  payment type. 
  </td>
 </tr><tr><td >
  Refresh List
  </td>
  <td >
  This button will
  reload the table from the database. 
  </td>
 </tr><tr><td >
  Add Payment
  </td>
  <td >
  This button will
  bring up a form for adding a "Prior Payment Recorded" record, an adjustment,
  or an over-the-counter payment. The function depends upon the user’s role.
  </td>
 </tr><tr><td >
  Save Changes
  </td>
  <td >
  After you delete a
  record, click this button to "Save" the change. 
  </td>
 </tr><tr><td >
  Payment Receipt
  </td>
  <td >
  Clicking this button
  will bring up a Reports Viewer with the receipt generated by this payment. This is not
  available for all types.
  </td>
 </tr><tr><td >
  Show Note
  </td>
  <td >
  This button brings up the form for payment notes.
  </td>
 </tr></tbody></table>

<h2> Menu </h2>

Select a payment in the grid and click on it's first column to bring up a menu.


<figure>
  <img src="i/help/paymentHistoryMenu.png" alt="payment History Menu" width="700px">
  <figcaption>Payment History Menu.</figcaption>
</figure>


Most of these options duplicate button functions but two are different. The "Audit History" choice
will bring up a printable audit trail of the payment record. The "Delete Payment" option will delete a
manual entry if it has not yet been approved. Most record types cannot be deleted and must be
reversed or cancelled with supervisory approval. The Payment Receipt is reproduced in the Reports
Viewer.
<h2>Functions</h2>
The different functions offered on this tab are:

<ul>

<li>Reverse Payment<br>
To back out (reverse or cancel) a payment, click in a row and then click the Reverse Payment
or Cancel Voucher button. A pop-up window will prompt you to select a reason. Select from
the drop-down list and click the "Reverse" or "Cancel" button. Microsoft Word will open a
new Refund Memo with most of the fields filled in.</li>

<li>Add Payment<br>

Click this button to add a payment manually. This was called an "R3" payment. Only a
Financial Technician or Supervisor can add a payment.</li>

<li>Payment Receipt<br>
Clicking this button will bring up a Crystal Reports Viewer with the receipt generated by this
payment. This is not available for all types.</li>

<li>Refresh Payment History List<br>
Reload the data grid and confirm that your changes were saved.</li>

<li>Save Changes<br>
After deleting or editing a manual payment, you must click this button to save the change.
Nothing is saved until this button is clicked.</li>
<li>Show Note<br>
Pop up a window to show the note for the selected payment and allow the user to edit it.</li>
</ul>

$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (11, false, 'Payments Notes', $$$A note may be attached to a payment and will always stay with that payment. In the rare case that a
payment is moved to another account, the note will remain with the payment always. Notes are
audited so that all values and changes are logged.
$$$, $$$A note may be attached to a payment and will always stay with that payment. In the rare case that a
payment is moved to another account, the note will remain with the payment always. Notes are
audited so that all values and changes are logged.
<figure>
  <img src="i/help/paymentNote.png" alt="payment Note" width="700px">
  <figcaption>Payment Note.</figcaption>
</figure>





Clicking the "Show Note" button or clicking the first column of the payment and selecting the "Show
Note" option will pop up the window. If there is already a note, it will be shown. You may add or edit
the existing text. Clearing the text will delete the note. You must click the "Save" button or press the
enter key to save your work. Note the explanation in the status bar.
<figure>
  <img src="i/help/AddingPaymentNote.png" alt="AddingPaymentNote" width="700px">
  <figcaption>Adding Payment Note.</figcaption>
</figure>


<p></p>
After you save the note, the status bar at the bottom of the window changes. It confirms that the
note has been saved into the database.
<figure>
  <img src="i/help/SavedPaymentNote.png" alt="SavedPaymentNote" width="700px">
  <figcaption>Saved Payment Note.</figcaption>
</figure>


<p></p>
Changing the text and clicking "Save" again will update the database. The status bar confirms the
update. The original note is logged in the audit trail.
<figure>
  <img src="i/help/UpdatedPaymentNote.png" alt="Updated Payment Note" width="700px">
  <figcaption>Updated Payment Note.</figcaption>
</figure>


<p></p>
To delete a payment note, clear the text and click the "Save" button. The status bar confirms that
the note has been deleted. The original note is logged in the audit trail.
<figure>
  <img src="i/help/DeletedPaymentNote.png" alt="DeletedPaymentNote" width="700px">
  <figcaption>Deleted Payment Note.</figcaption>
</figure>

$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (12, false, 'Payment Reversal/Cancellation', $$$Payments can be reversed for many reasons and many weeks after being received. When you
reverse a payment, a window pops up to ask why.
$$$, $$$
Payments can be reversed for many reasons and many weeks after being received. When you
reverse a payment, a window pops up to ask why.
<figure>
  <img src="i/help/PaymentReversed.png" alt="PaymentReversed" width="700px">
  <figcaption>Payment Reversed.</figcaption>
</figure>


Choose one of the following from the drop-down box.
<figure>
  <img src="i/help/PaymentReverseReasonsValues.png" alt="PaymentReverseReasonsValues" width="700px">
  <figcaption>Payment Reverse Reasons Values.</figcaption>
</figure>


<ul>
<li>Applied to Wrong Account</li>
<li> Bank Account Closed</li>
<li>Data Entry Error</li>
<li>No Acct/Cannot Locate</li>
<li> Non-Sufficient Funds</li>

<li>Account Details Page</li>
<li>Stop Payment</li>
</ul>
Clicking the "Reverse" button will open a Microsoft Word document which is a letter to the claimant
explaining why his payment was reversed and to expect a new invoice with a higher balance.

<h2>Cancel</h2>
Adjustments can be canceled for many reasons and many weeks after being entered. When you
cancel a payment, a window pops up to ask why. Its drop-down box offers the same reasons as the
reversals.

<figure>
  <img src="i/help/PaymentCancel.png" alt="PaymentCancel" width="700px">
  <figcaption>Payment Cancel.</figcaption>
</figure>

<figure>
  <img src="i/help/PaymentCancelledReasonsValues.png" alt="PaymentCancelledReasonsValues" width="700px">
  <figcaption>Payment Cancel Reasons Values.</figcaption>
</figure>

$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (13, false, 'Add Payment', $$$Click the "Add Payment" button on the "Payment History" tab to add a payment manually. This was called an "R3" payment. Only a Financial
Technician can add an over-the-counter payment. Refunds Sections workers can add "Prior Payment
Recorded" records that are never treated as payments except for initial calculations. Receivables
Technicians can add adjustments. Over-the-counter payment and adjustments must be approved by
supervisors before taking effect. What the user sees on their screen and the choices for payment
type depends upon their role.
$$$, $$$Click this button to add a payment manually. This was called an "R3" payment. Only a Financial
Technician can add an over-the-counter payment. Refunds Sections workers can add "Prior Payment
Recorded" records that are never treated as payments except for initial calculations. Receivables
Technicians can add adjustments. Over-the-counter payment and adjustments must be approved by
supervisors before taking effect. What the user sees on their screen and the choices for payment
type depends upon their role.

<figure>
  <img src="i/help/AddPaymentButton.png" alt="AddPaymentButton" width="700px">
  <figcaption>Add Payment Button.</figcaption>
</figure>




<h2>Financial Technician</h2>
Funds Control enters "over-the-counter" payments that may or may not hit the GL. If the "Block
Number" starts with "9" then the payment will not go into the GL.
<p></p>
Financial Technician cannot make a payment to an account that has a credit balance.
<figure>
  <img src="i/help/FinancialTechnicianCannotAddPaymentWithCreditBalance.png" alt="FinancialTechnicianCannotAddPaymentWithCreditBalance" width="700px">
  <figcaption>Financial Technician Canno tAdd Payment With Credit Balance.</figcaption>
</figure>
<p></p>
Financial Technician cannot make a payment to an account with a zero balance.

<figure>
  <img src="i/help/FinancialTechnicianCannotAddPaymentWithZeroBalance.png" alt="FinancialTechnicianCannotAddPaymentWithZeroBalance" width="700px">
  <figcaption>Financial Technician Cannot Add Payment With Zero Balance.</figcaption>
</figure>

<p></p>
Financial Technician cannot also make a payment to an inactive account.

<figure>
  <img src="i/help/FinancialTechnicianCannotAddPaymentForInactiveAccount.png" alt="FinancialTechnicianCannotAddPaymentForInactiveAccount" width="700px">
  <figcaption>Financial Technician Cannot Add Payment For Inactive Account.</figcaption>
</figure>
<p></p>
Financial Technician can make a payment to an active account with a balance due.

<figure>
  <img src="i/help/FinancialTechnicianCanAddPaymentWithDueBalanceAccount.png" alt="FinancialTechnicianCanAddPaymentWithDueBalanceAccount" width="700px">
  <figcaption>Financial Technician Can Add Payment With Due Balance Account.</figcaption>
</figure>

<figure>
  <img src="i/help/FinancialTechnicianCanAddPaymentPopup.png" alt="FinancialTechnicianCanAddPaymentPopup" width="700px">
  <figcaption>Financial Technician Can Add Payment Popup.</figcaption>
</figure>





<h2>Receivables Technician</h2>
Receivables Technicians enter corrections that do not hit the GL for one of two reasons. The money
might be adjusted or "written-off" so that there is no cash. The money came from another system
that already posted the cash to the GL.
<p></p>
Receivables can refund a credit balance. This amount must match the balance at the time of the
nightly batch process or else it will be rejected.
<figure>
  <img src="i/help/ReceivablesTechniciansCanAddPayment.png" alt="ReceivablesTechniciansCanAddPayment" width="700px">
  <figcaption>Receivables Technicians Can Refund Credit Balance.</figcaption>
</figure>

<figure>
  <img src="i/help/CanAddPaymentPopupForReceivableTechnician.png" alt="CanAddPaymentPopupForReceivableTechnician" width="700px">
  <figcaption>Receivables Technicians Can Refund Credit Balance Popup.</figcaption>
</figure>



<p></p>

Receivables Technicians can adjust an account's payment total. This is allowed regardless of the
balance (zero, positive, or negative). It is also allowed if the status is active or history. The date of
this adjustment determines how it will update the master record. When the nightly batch processes

this adjustment, it will first get the master record payment balance on the adjustment date from the
audit trail. Then it will add up all the payments from that date to the present. It will add the value of
this adjustment (subtract if negative) to the sum and apply it to the account. This allows the users to
adjust accounts that were recalculated after being paid off. The adjustment done during the
recalculation can be shown in the payment list and the sum of all payments will match the master
record.

<figure>
  <img src="i/help/ReceivablesTechniciansCanAdjustPayment.png" alt="ReceivablesTechniciansCanAdjustPayment" width="700px">
  <figcaption>Receivables Technicians Can Adjust Payment.</figcaption>
</figure>



<p></p>
Receivables Technicians can also "write-off" balances of $25 or less. It does not matter if the account
is active or history.
<figure>
  <img src="i/help/ReceivablesTechniciansCanWriteOffPayment.png" alt="ReceivablesTechniciansCanWriteOffPayment" width="700px">
  <figcaption>Receivables Technicians Can Write-Off Payment.</figcaption>
</figure>



<h2>Refunds Specialists</h2>
Boyers can enter "payment" records that will never be processed as money. They are only for
entering payments prior to the new system and will only be used for initial billing calculations.
<figure>
  <img src="i/help/RefundsSpecialistsCanAddPaymentPopup.png" alt="RefundsSpecialistsCanAddPaymentPopup" width="700px">
  <figcaption>Refunds Specialists Can Add Payment Popup.</figcaption>
</figure>


<h2> All Other User Types</h2>
All other users cannot enter payments. The button is not present.
<figure>
  <img src="i/help/AllOtherUserRolesCannotAddPayment.png" alt="AllOtherUserRolesCannotAddPayment" width="700px">
  <figcaption>All Other User Roles Cannot Add Payment.</figcaption>
</figure>
$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (14, false, 'Approval Page', $$$To access the approval page, click on "Approval" in the main menu. It provides Interest Adjustments,
Pending Payments and Payment Moves Approval for supervisors.
$$$, $$$Approval Page
To access the approval page, click on "Approval" in the main menu. It provides Interest Adjustments,
Pending Payments and Payment Moves Approval for supervisors.
$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (15, false, 'Approve Interest Adjustments', $$$<p> Account notes are messages, or descriptions of account activity that may be entered by any user of the Service Credit system, and seen by every other user of the system. </p>
<p> Only the originator of the note may edit or delete the note. </p>$$$, $$$
Click on "Approval" page and then on "Interest Adjustments" tab.
This menu item shows a window with all of the Interest Adjustments. Receivable Supervisors see
pending adjustments entered by the Receivable Technicians. For each interest adjustment in the list,
the supervisor may click the "Approve Selected" or "Disapprove Selected" button. Unchecked
payments will not change. There are buttons to see the audit trail of the selected account or to
update the note attached to it.
<p></p>
You will see the list of Interest Adjustments. The adjustments in the list were entered by the
Receivables Technicians.
<figure>
  <img src="i/help/InterestAdjustmentsTab.png" alt="InterestAdjustmentsTab" width="700px">
  <figcaption>Interest Adjustments Tab.</figcaption>
</figure>



<p></p>
To approve or disapprove interest adjustments, select them and click either "Approve Selected"
button or "Disapprove Selected" button. The list will be automatically updated.
All requests to adjust interest should have a note from the technician requesting the change. You
can see the note in the note section.
<p></p>
The "View Audit Trail" button pops up a printable window showing the change history of the
highlighted account.
<figure>
  <img src="i/help/AuditTrailForInterestAdjustments.png" alt="AuditTrailForInterestAdjustments" width="700px">
  <figcaption>Audit Trail For Interest Adjustments.</figcaption>
</figure>

<p></p>
You can update the note entered by the technician who requested the move. You may add an
explanation. Click "Save Note" to save the change or "Discard Changes" to throw it away.
<figure>
  <img src="i/help/NoteforInterestAdjustments.png" alt="NoteforInterestAdjustments" width="700px">
  <figcaption>Note for Interest Adjustments.</figcaption>
</figure>


<p></p>
In the view drop down box you can select three values
<ul>
<li>Unapproved - This is the default view and shows pending requests.</li>
<li>Approved - This view is a historical reference of the approved requests. The "Approve
       Selected" and "Disapprove Selected" buttons are not shown.</li>
<li>Disapproved - This view is a historical reference of the disapproved requests. The "Approve
       Selected" and "Disapprove Selected" buttons are not shown.</li>

</ul>
$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (16, false, 'Approve Payment Moves', $$$You can access the approval for payment moves by clicking on "Approval" page and then on "Payment Moves" tab.
This tab shows all of the Payment Moves. Receivable Supervisors see pending
moves entered by the Receivable Technicians. 
$$$, $$$Click on "Approval" page and then on "Payment Moves" tab.
This menu item shows a window with all of the Payment Moves. Receivable Supervisors see pending
moves entered by the Receivable Technicians. For each payment move in the list, the supervisor may
click the "Approve Selected" or "Disapprove Selected" button. Unchecked payments will not change.
There are buttons to see the audit trail of the selected account or to edit the note attached to it.

<p></p>
You will see the list of payment moves. The moves in the list were entered by the Receivables
Technicians.
<figure>
  <img src="i/help/PaymentMovesTab.png" alt="PaymentMovesTab" width="700px">
  <figcaption>Payment Moves Tab.</figcaption>
</figure>

<p></p>
To approve or disapprove payment moves, select them and click either "Approve Selected" button
or "Disapprove Selected" button. The list will be automatically updated.
All requests to adjust interest should have a note from the technician requesting the change. You
can see the note in the note section.
<p></p>
The "View Audit Trail" button pops up a printable window showing the change history of the
highlighted account.
<figure>
  <img src="i/help/AuditTrailforPaymentMoves.png" alt="AuditTrailforPaymentMoves" width="700px">
  <figcaption>Audit Trail for Payment Moves.</figcaption>
</figure>

<p></p>
You can update the note entered by the technician who requested the move. You may add an
explanation. Click "Save Note" to save the change or "Discard Changes" to throw it away.
<figure>
  <img src="i/help/NoteForPaymentMoves.png" alt="NoteForPaymentMoves" width="700px">
  <figcaption>Note For Payment Moves.</figcaption>
</figure>

<p></p>
In the view drop down box you can select three values
<ul>
<li>Unapproved - This is the default view and shows pending requests.</li>
<li>Approved - This view is a historical reference of the approved requests. The "Approve
       Selected" and "Disapprove Selected" buttons are not shown.</li>
 <li>Disapproved - This view is a historical reference of the disapproved requests. The "Approve
       Selected" and "Disapprove Selected" buttons are not shown</li>

 </ul>
$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (17, false, 'Approve Pending Payments', $$$You can access the approval for pending payments by clicking on "Approval" page and then on "Pending Payments" tab.
This tab shows a window with all of the payments that need supervisor approval. Supervisors
see work from employees that they directly supervise and also employees from a subordinate role.
$$$, $$$
This menu item shows a window with all of the payments that need supervisor approval. Supervisors
see work from employees that they directly supervise and also employees from a subordinate role.
For example, every Financial Supervisor sees all the payments entered by the Financial Technicians.
For each payment in the list, the supervisor may click the "Approve Selected" or "Disapprove
Selected" button. Unchecked payments will not change. There are buttons to see the audit trail of
the selected payment or update the note attached to it.
<p></p>
You will see the "Pending - Approval" list of payments. The payments in the list were entered or
resolved by your supervised users.

<ul>
<li>Users directly supervised by you, regardless of their role</li>
 <li>Users in a subordinate role supervised by somebody else (e.g., a Financial Supervisor will see
   all payments from all Financial Technicians even if they are assigned to another supervisor).</li>
</ul>

<figure>
  <img src="i/help/PendingPaymentsTab.png" alt="PendingPaymentsTab" width="700px">
  <figcaption>Pending Payments Tab.</figcaption>
</figure>

To approve or disapprove pending payments, select them and click either "Approve Selected" button
or "Disapprove Selected" button. The list will be automatically updated.
All pending payments should have an associated note. You can see the note in the note section.
<p></p>
The "View Audit Trail" button pops up a printable window showing the change history of the
highlighted account.
<figure>
  <img src="i/help/AuditTrailForPendingPayments.png" alt="AuditTrailForPendingPayments" width="700px">
  <figcaption>Audit Trail For Pending Payments.</figcaption>
</figure>

<p></p>
You can update the note. You may add an explanation. Click "Save Note" to save the change or
"Discard Changes" to throw it away.
<figure>
  <img src="i/help/NoteForPendingPayments.png" alt="NoteForPendingPayments" width="700px">
  <figcaption>NoteForPendingPayments.</figcaption>
</figure>
<p></p>

The following table shows what happens to the payment that is either approved or disapproved. Manual
adjustments are simply deleted when disapproved. Cancellations should be approved if you want
the payment canceled. Cancellations should be disapproved if you want to keep the payment.


<table class="helpTbl stdTbl" border="1" cellspacing="0" cellpadding="0"><thead><tr><th >
  <caption> What happens to Payment when (dis)/approved.</caption>
   <p class="MsoNormal"><b>Payment Type
   Needing Approval</b></p><p><b></b></p><b></b>
   </th>
   <th >
   <p class="MsoNormal"><b>Approved &amp;
   Processed Overnight</b></p><p><b></b></p><b></b>
   </th>
   <th >
   <p class="MsoNormal"><b>Disapproved</b></p><p><b></b></p><b></b>
   </th>
  </tr></thead><tbody><tr><td >
  <p class="MsoNormal">Adjustment - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Adjustment - Complete</p>
  </td>
  <td >
  <p class="MsoNormal">(Deleted)</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Annuity - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Annuity - Complete</p>
  </td>
  <td >
  <p class="MsoNormal">Suspended / Unresolved</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Batch Auto Refund - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Batch Auto Refund </p>
  </td>
  <td >
  <p class="MsoNormal">Batch Auto Refund Canceled</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Batch Auto Refund Canceled - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Batch Auto Refund Canceled</p>
  </td>
  <td >
  <p class="MsoNormal">Batch Auto Refund</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Credit Balance Refund - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Credit Balance Refund - Complete</p>
  </td>
  <td >
  <p class="MsoNormal">(Deleted)</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Debit Voucher - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Debit Voucher - Complete</p>
  </td>
  <td >
  <p class="MsoNormal">Suspended / <br />
  Unresolved</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Direct Pay Life - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Direct Pay Life - Complete</p>
  </td>
  <td >
  <p class="MsoNormal">Suspended / <br />
  Unresolved</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Health Benefits - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Health Benefits - Complete</p>
  </td>
  <td >
  <p class="MsoNormal">Suspended / <br />
  Unresolved</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Manual Adjustment Cancelled - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Manual Adjustment Cancelled</p>
  </td>
  <td >
  <p class="MsoNormal">Adjustment - Complete / <br />
  Write-Off - Complete / <br />
  Credit Balance Refund - Complete</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Posted - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Posted - Complete</p>
  </td>
  <td >
  <p class="MsoNormal">(Deleted if Over-the-counter) /<br />
  Suspended /<br />
  Unresolved</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Reversed - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Reversed - Complete</p>
  </td>
  <td >
  <p class="MsoNormal">Posted - Complete</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Suspense Debit Voucher - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Suspense Debit Voucher - Complete</p>
  </td>
  <td >
  <p class="MsoNormal">Suspense Refund Complete</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Suspense Refund Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Suspense Refund Complete</p>
  </td>
  <td >
  <p class="MsoNormal">Suspended / <br />
  Unresolved</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Voluntary Contributions - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Voluntary Contributions - Complete</p>
  </td>
  <td >
  <p class="MsoNormal">Suspended / <br />
  Unresolved</p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal">Write-Off - Pending Approval</p>
  </td>
  <td >
  <p class="MsoNormal">Write-Off - Complete</p>
  </td>
  <td >
  <p class="MsoNormal">(Deleted)</p>
  </td>
 </tr></tbody></table>

$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (18, false, 'Suspense Page', $$$On the "Suspense" page a Receivables Technician can see at a glance the status of the latest bank
input. Payment transaction details are displayed in the bottom tier that apply to the highlighted
payment.
$$$, $$$On the "Suspense" page a Receivables Technician can see at a glance the status of the latest bank
input. Payment transaction details are displayed in the bottom tier that apply to the highlighted
payment.

<figure>
  <img src="i/help/SuspensePage.png" alt="SuspensePage" width="700px">
  <figcaption>Suspense Page.</figcaption>
</figure>


<h2>Data Grid</h2>
When you enter the Suspense Grid or click the "Refresh" button, the program loads the current data
from the database. This "snapshot" of data will be neither automatically updated to show changes
by other users nor will update the database unless you tell it to. You may not edit any cell in this grid
except the "Post" checkboxes in rows where the payment state is "Unresolved."
<p></p>
The following information is displayed in columns for every payment in the grid.

<table class="helpTbl stdTbl" border="1" cellspacing="0" cellpadding="0">
<caption> Information Displayed for every Payment in Suspense Page.</caption>
<thead><tr><th >
   <p class="MsoNormal"><b><span>Column</span></b></p><p><b></b></p><b></b>
   </th>
   <th >
   <p class="MsoNormal"><b><span>Description</span></b></p><p><b></b></p><b></b>
   </th>
  </tr></thead><tbody><tr><td >
  <p class="MsoNormal"><span>Post</span></p><p></p>
  </td>
  <td >
  <p class="MsoNormal"><span>Check this box if
  you want to approve the unresolved payment</span></p><p></p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal"><span>Payment Status</span></p><p></p>
  </td>
  <td >
  <p class="MsoNormal"><span>One of the states in
  the previous table</span></p><p></p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal"><span>Deposit Date</span></p><p></p>
  </td>
  <td >
  <p class="MsoNormal"><span>The Deposit Date is
  the date the check or ACH payment was received at the lockbox bank</span></p><p></p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal"><span>BatBlkSeq</span></p><p></p>
  </td>
  <td >
  <p class="MsoNormal"><span>A contraction of the
  Batch, Block and Sequence strings that identify the payment</span></p><p></p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal"><span>CSD</span></p><p></p>
  </td>
  <td >
  <p class="MsoNormal"><span>The Claim Number
  (1111111 if unknown)</span></p><p></p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal"><span>ACH</span></p><p></p>
  </td>
  <td >
  <p class="MsoNormal"><span>If checked, this is
  an ACH payment</span></p><p></p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal"><span>Amount</span></p><p></p>
  </td>
  <td >
  <p class="MsoNormal"><span>Dollar amount of
  payment</span></p><p></p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal"><span>Birth Date</span></p><p></p>
  </td>
  <td >
  <p class="MsoNormal"><span>The birth date on
  the payment</span></p><p></p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal"><span>Name</span></p><p></p>
  </td>
  <td >
  <p class="MsoNormal"><span>The Claimant, blank
  if a suspense payment</span></p><p></p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal"><span>Balance</span></p><p></p>
  </td>
  <td >
  <p class="MsoNormal"><span>The remaining
  balance for this claimant, zero if suspense</span></p><p></p>
  </td>
 </tr><tr><td >
  <p class="MsoNormal"><span>Account</span></p><p></p>
  </td>
  <td >
  <p class="MsoNormal"><span>The status of this account, blank if a suspense payment.</span></p><p></p>
  </td>
 </tr></tbody></table>


<h2>Sorting Rows</h2>
To sort the rows by any column, click the header for the column. The grid is now sorted in ascending
order by that column. To sort it in descending order, click the column header again. For example, to
sort the table by the CSD number, click the column header right on the word "CSD." An upwards
pointing triangle shows that the grid is now sorted in ascending order by CSD. Click it again to sort it
descending order and the triangle points down.

<figure>
  <img src="i/help/SortingTable.png" alt="SortingTable" width="700px">
  <figcaption>Sorting Table.</figcaption>
</figure>


<h2>Payment Transaction Details</h2>
Click in the grid to select it and use the up and down arrow keys to move around. A right-pointing
triangle appears in the left grid border to show the current row. The boxes at the bottom of the
screen show information about the selected record. On the left are details about the payment while
information about the claimant appears on the right. Information highlighted in bold and yellow
quickly shows the problem with the record. For example all of the "Suspended" payments have the
"Payment CSD Number" and "Payment Date of Birth" highlighted because we cannot match them to

any claimant. A "Posted - Pending" payment will not have any fields highlighted. "Unresolved"
payments will show at a glance why they are unresolved. The birth dates might not match or
payment is for a history account. Highlighting makes the discrepancies easy to spot. In this example,
the payment is unresolved because the date of birth on the check is different from the account and
the account is paid off.
<figure>
  <img src="i/help/PaymentTransactionDetails.png" alt="PaymentTransactionDetails" width="700px">
  <figcaption>Payment Transaction Details.</figcaption>
</figure>



<h2>Resolving Suspense Payments</h2>
To resolve a payment, the payment must be corrected, the account must be corrected, or the
payment must be returned or transferred.


<h2>Post Checkbox</h2>
Only one column of the data grid can be modified by the user. Initially, the "Post" checkboxes are all
unchecked because this column is for the application user to select payments for processing. Users
may check the "Post" box for "Unresolved" payments if they want the program to correct the
payment's birth date and the account status. The account status will only change if the account
balance is greater than zero and is set to "History." Click the check boxes of all the "Unresolved"
payments that may be posted. When done, click the "Post Checked Payments" button. If you do not
click the "Post Checked Payments" button the checked records will not be updated. The application
processes these payments using this logic:
<ul>
 <li>If the payment's date of birth does not match the account's date, update the date of birth on
   the payment to match the account. Apply the payment.</li>
<li>If this account is in "History" status and also has a balance due, then set the account to
   "Active" status and apply the payment.</li>
<li>If these logical tests fail, then the payment reverts to the unresolved state.</li>

</ul>



<h2>Buttons</h2>
To work with the payments on the Suspense Page, select one in the grid by clicking anywhere in the
row and then click a button.
<figure>
  <img src="i/help/SuspensePageButtons.png" alt="SuspensePageButtons" width="700px">
  <figcaption>Suspense Page Buttons.</figcaption>
</figure>




<h3>Refresh List</h3>
Select a payment and then click this button to throw away your recent changes or to update your
screen with changes that other users have made.
<h3> Reset Selected Payment</h3>
Selecting a payment and clicking this button will reset that payment to its earliest known state. This
is a way to undo all the work done by the analysts. The payment will be reset to its state as it came
from the lockbox bank.
<h3>Post Checked Payments</h3>
To avoid errors when resolving payments, the application requires a second step to apply the
changes. Clicking the "Post" checkboxes does not complete the task. Clicking buttons for "Refund
Payment," "Refer to Voluntary Contribution," or "Refer to Direct Pay Life Insurance" will not save
your changes. On your screen the payment status is "Ready for..." the new status.


<p></p>
The payments in the following states are NOT saved and can be lost. You must either click the "Post
Checked Payments" button or the "Refresh List" button.
<table class="helpTbl stdTbl" border="1" cellspacing="0" cellpadding="0">
	<caption>Payment Type Not Saved.</caption>
	<thead> <tr>
		<th>Post</th>
		<th>Payment Status</th>
	</tr></thead>

	<tbody>

		<tr>

			<td>Checked</td>
			<td>Ready For Life Insurance </td>

		</tr>

		<tr>

			<td>Checked</td>
			<td>Ready For VC </td>

		</tr>


		<tr>

			<td>Checked</td>
			<td>Ready For Refund </td>

		</tr>


		<tr>

			<td>Checked</td>
			<td>Unresolved </td>

		</tr>


	</tbody>
</table>



<p></p>
The payments in the following states have been saved and are ready to be approved by a supervisor.
If the supervisor approves them, they will change from the "Pending Approval" state to the
"Pending" state. If the supervisor disapproves them, they will revert to their original state from the
lockbox.

<table class="helpTbl stdTbl" border="1" cellspacing="0" cellpadding="0">
	<caption>Payment Saved and Ready to be Approved.</caption>
	<thead> <tr>
		<th>Post</th>
		<th>Payment Status</th>
	</tr></thead>

	<tbody>

		<tr>

			<td>UnChecked</td>
			<td>Direct Pay Life - Pending Approval</td>

		</tr>

		<tr>

			<td>UnChecked</td>
			<td>Voluntary Contributions - Pending Approval </td>

		</tr>


		<tr>

			<td>UnChecked</td>
			<td>Refund Pending Approval</td>

		</tr>


		<tr>

			<td>UnChecked</td>
			<td>Posted - Pending Approval </td>

		</tr>


	</tbody>
</table>

<p></p>
The payments in the following states have been approved and will be processed overnight unless a
supervisor "resets" them to their original state.


<table class="helpTbl stdTbl" border="1" cellspacing="0" cellpadding="0">
	<caption>Payment Type Approved.</caption>
	<thead> <tr>
		<th>Post</th>
		<th>Payment Status</th>
	</tr></thead>

	<tbody>

		<tr>

			<td>UnChecked</td>
			<td>Direct Pay Life - Pending</td>

		</tr>

		<tr>

			<td>UnChecked</td>
			<td>Voluntary Contributions - Pending </td>

		</tr>


		<tr>

			<td>UnChecked</td>
			<td>Refund Pending</td>

		</tr>


		<tr>

			<td>UnChecked</td>
			<td>Posted - Pending </td>

		</tr>


	</tbody>
</table>



<p></p>
Every few minutes or after you have completed a few payments, click on the "Post Checked
Payments" button to save your work. Alternatively, you can forego clicking this button to "undo"
your previous actions.
<h3> Print</h3>
This button will produce a printable list of the payments in the Reports Viewer.
<figure>
  <img src="i/help/PaymentsListPrint.png" alt="PaymentsListPrint" width="700px">
  <figcaption>Payments List Print.</figcaption>
</figure>



<h3> Link Payment to Current Employee</h3>
This box is used to resolve suspense items. A suspense item has to match a data base account at a
minimum on the CSD and birth date fields. This box will facilitate the resolution of a suspense item.
The Receivables technician would perform the following:
<ul>
<li> Look up the suspense item and note the name or address.</li>
<li> Go to the employee tab.</li>
<li>Search on the name and address.</li>
<li> Find the claimant.</li>
<li>Click the Link Payment to Current Employee button so the suspense item's CSD number and
     birth date are corrected and the payment becomes "posted - pending".</li>
 </ul>
<h3> Make this Employee Current</h3>
This button is used to resolve an unresolved payment as follows:
<ul>
<li>Select an unresolved payment on the Suspense tab. Let's say the birth date is 1/2/1943.</li>
<li>Click the Make This Employee Current button.</li>
<li>Let's say that we view the Account Summary window and notice that the claimant's birth
       date is 1/1/1800 (obviously incorrect).</li>
<li>Go to the employee tab.</li>
<li>Correct the birth date.</li>
<li> Return to the Suspense tab.</li>
<li> Click the Post Checked Items button.</li>
 </ul>

The unresolved payment will be a match on both CSD number and birth date.
<h3>Transfer</h3>
Clicking on the "Transfer" button will pop up a window of transfer type choices. Select one and click
the "OK". See section 6.4 for more information.
<h3> Note</h3>
The "Note" button puts a payment note on the selected payment. It is a simple text annotation that
attaches to a single payment. It follows that payment even if it is linked to another claimant.
<h3> History</h3>
The "History" opens a printable audit trail of the selected payment in a Crystal Reports Viewer.



<h2> Payment States </h2>	
	The data grid shows all the payments in the following states.



<table class="helpTbl stdTbl" border="1" cellspacing="0" cellpadding="0">
	<caption>Payment Type Approved.</caption>
	<thead> <tr>
		<th>Status</th>
		<th>Description</th>
	</tr></thead>

	<tbody>

		<tr>

			<td>Adjustment - Pending</td>
			<td>Adjust (debit or credit) the payment total. Approved &amp; ready for nightly batch processing.</td>

		</tr>

		<tr>

			<td>Adjustment - Pending Approval</td>
			<td>Adjust (debit or credit) the payment total. Needs supervisor's approval.</td>

		</tr>


		<tr>

			<td>Annuity - Pending</td>
			<td>This should have gone to the annuity adjusters. Approved &amp; ready for nightly batch processing.</td>

		</tr>


		<tr>

			<td>Annuity - Pending Approval</td>
			<td>This should have gone to the annuity adjusters. Needs supervisor's approval. </td>

		</tr>


		<tr>

			<td>Batch Auto Refund - Pending</td>
			<td>Batch process triggered this refund of an overpayment. Approved &amp; ready for nightly batch processing.</td>

		</tr>

		<tr>

			<td>Batch Auto Refund - Pending Approval</td>
			<td>Batch process triggered this refund of an overpayment. Needs supervisor's approval.</td>

		</tr>


		<tr>

			<td>Batch Auto Refund Canceled - Pending </td>
			<td>Batch process triggered this refund of an overpayment but OPM does not want to refund the money. Approved &amp; ready for nightly batch processing.</td>

		</tr>


		<tr>

			<td>Batch Auto Refund Canceled - Pending Approval</td>
			<td>Batch process triggered this refund of an overpayment but OPM does not want to refund the money. Needs supervisor's approval. </td>

		</tr>


		<tr>

			<td>Credit Balance Refund - Pending</td>
			<td>Refund of a credit balance. Approved &amp; ready for nightly batch processing.</td>

		</tr>

		<tr>

			<td>Credit Balance Refund - Pending Approval</td>
			<td>Refund of a credit balance. Needs supervisor's approval.</td>

		</tr>


		<tr>

			<td>Debit Voucher - Pending </td>
			<td>Payment cancelled before it was applied to an account. Approved &amp; ready for nightly batch processing.</td>

		</tr>


		<tr>

			<td>Debit Voucher - Pending Approval</td>
			<td>Payment cancelled before it was applied to an account. Needs supervisor's approval. </td>

		</tr>

		<tr>

			<td>Direct Pay Life - Pending </td>
			<td>This should have gone to the DPLI Group. Approved &amp; ready for nightly batch processing.</td>

		</tr>

		<tr>

			<td>Direct Pay Life - Pending Approval</td>
			<td>This should have gone to the DPLI Group. Needs supervisor's approval. </td>

		</tr>


		<tr>

			<td>Health Benefits - Pending</td>
			<td>This should have gone to the Health Benefits Group. Approved &amp; ready for nightly batch processing.</td>

		</tr>


		<tr>

			<td>Health Benefits - Pending Approval</td>
			<td>This should have gone to the Health Benefits Group. Needs supervisor's approval. </td>

		</tr>



		<tr>

			<td>Manual Adjustment Cancelled - Pending</td>
			<td>Credit Refund, Adjustment, or Write-Off cancelled. Approved &amp; ready for nightly batch processing.</td>

		</tr>

		<tr>

			<td>Manual Adjustment Cancelled - Pending Approval</td>
			<td>Credit Refund, Adjustment, or Write-Off cancelled. Needs supervisor's approval.</td>

		</tr>


		<tr>

			<td>Posted - Pending Approval</td>
			<td>Manual payment must be approved prior to Nightly Batch Process. Needs supervisor's approval.</td>

		</tr>


		<tr>

			<td>Reset to Original - Pending</td>
			<td>Ready to be set when the user clicks the button.</td>

		</tr>


		<tr>

			<td>Reversed - Pending</td>
			<td>Payment cancelled after it was applied to an account. Approved &amp; ready for nightly batch processing.</td>

		</tr>

		<tr>

			<td>Reversed - Pending Approval</td>
			<td>Payment cancelled after it was applied to an account. Needs supervisor's approval. </td>

		</tr>


		<tr>

			<td>Suspended</td>
			<td>Not matched to any claimant</td>

		</tr>


		<tr>

			<td>Suspense Debit Voucher - Pending </td>
			<td>Payment reversed by the Lockbox Bank before it was posted to an account. Approved &amp; ready for nightly batch processing. </td>

		</tr>


		<tr>

			<td>Suspense Debit Voucher - Pending Approval</td>
			<td>Payment reversed by the Lockbox Bank before it was posted to an account. Needs supervisor's approval.</td>

		</tr>

		<tr>

			<td>Suspense Refund Pending</td>
			<td>Unprocessed payment must return to the sender. Approved &amp; ready for nightly batch processing.</td>

		</tr>


		<tr>

			<td>Suspense Refund Pending Approval</td>
			<td>Unprocessed payment must return to the sender. Needs supervisor's approval.</td>

		</tr>


		<tr>

			<td>Unresolved</td>
			<td>Matched to a claimant but the account is not active or date of birth on the payment is wrong.</td>

		</tr>


		<tr>

			<td>Voluntary Contributions - Pending</td>
			<td>This should have gone to the VC Group. Approved &amp; ready for nightly batch processing.</td>

		</tr>

		<tr>

			<td>Voluntary Contributions - Pending Approval</td>
			<td>This should have gone to the VC Group. Needs supervisor's approval. </td>

		</tr>


		<tr>

			<td>Write-Off - Pending </td>
			<td>Forgive small amounts due. Approved &amp; ready for nightly batch processing.</td>

		</tr>


		<tr>

			<td>Write-Off - Pending Approval</td>

			<td>Forgive small amounts due. Needs supervisor's approval.</td>

		</tr>
	</tbody>
</table>

$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (19, false, 'Transfer Payment Button', $$$Go the Suspense page, select a payment; then click on the Transfer button. Clicking on this Suspense
Page button will pop up a window of transfer type choices. Select one and click the "OK" button.
$$$, $$$
Go the Suspense page, select a payment; then click on the Transfer button. Clicking on this Suspense
Page button will pop up a window of transfer type choices. Select one and click the "OK" button.
<figure>
  <img src="i/help/TransferPayment.png" alt="payment Processing Logic" width="700px">
  <figcaption>Transfer Payment.</figcaption>
</figure>



<h2>Refund Payment </h2>
Choosing this option will generate a "Refund of Excess Recovery Payment" letter. This action
performs several steps:
<ul>
 <li>If there is a credit balance on the account, a pop-up window asks if you want to refund just
   the payment or the payment and the credit balance. For more information, See Refund
  Logic.</li>
 <li>A Microsoft Word document is created with the claimant's name and account information
   filled in.</li>
 <li>This document is saved on the user's computer in their "My Documents" folder.</li>
 <li>The document name is the CSD Number, an underscore, and the string "Refund Memo.doc"</li>
 <li>The Microsoft Word document is opened on the user's screen.</li>
 <li>The user must print and mail the letter manually.</li>
</ul>

<h2>Direct Pay Life Insurance  </h2>
Choosing this option will generate a "Standard Form 1081" and a "FFS Standard Voucher." This
action performs several steps:
<ul>
 <li>A Microsoft Word document is created with the claimant's name and account information
   filled in.</li>
<li>This document is saved on the user's computer in their "My Documents" folder.</li>
 <li>The document name is the CSD Number, an underscore, and the string
   "Std_Form_1081.doc"</li>
<li>The Microsoft Word document is opened on the user's screen.</li>
 <li>The user must print and mail the letter manually.</li>
</ul>


<h2> Voluntary Contribution </h2>
Choosing this option will generate a "FFS Standard Voucher" form. This action performs several
steps:
<ul>
 <li> A Microsoft Word document is created with the claimant's name and account information
   filled in.</li>
  <li>This document is saved on the user's computer in their "My Documents" folder.</li>
  <li>The document name is the CSD Number, an underscore, and the string
   "FFS_Std_Voucher.doc"</li>
  <li>The Microsoft Word document is opened on the user's screen.</li>
  <li>The user must print and mail the letter manually.</li>
</ul>

<h2>  Debit Voucher</h2>
Choosing this option will mark the payment but not generate any voucher form. That's because this
request originated with a voucher form.
<h2> Annuity</h2>
Choosing this option will mark the payment but not generate any voucher form. . This action
performs several steps:
<ul>
 <li>A Microsoft Word document is created with the claimant's name and account information
   filled in.</li>
 <li>This document is saved on the user's computer in their "My Documents" folder.</li>
 <li>The document name is the CSD Number, an underscore, and the string
   "FFS_Std_Voucher.doc"</li>



 <li>The Microsoft Word document is opened on the user's screen.</li>
 
<li>The user must print and mail the letter manually.</li>
</ul>
<h2>Health Benefits</h2>
Choosing this option will mark the payment but not generate any voucher form. . This action
performs several steps:
<ul>
<li> A Microsoft Word document is created with the claimant's name and account information
   filled in.</li>
<li> This document is saved on the user's computer in their "My Documents" folder.</li>
 <li>The document name is the CSD Number, an underscore, and the string
   "FFS_Std_Voucher.doc"</li>
<li>The Microsoft Word document is opened on the user's screen.</li>

<li>The user must print and mail the letter manually.</li>
</ul>




$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (20, false, 'Payments Page', $$$The Payment page allows you to search for payments according to some criteria and to see the results.
To access the Payments Page, click on "Payments" in the main menu.
$$$, $$$This page allows us to search for payments according to some criteria and to see the results.
To access the Payments Page, click on "Payments" in the main menu.
You may search for payments from any account based upon a variety of criteria.
$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (21, false, 'Search for Payments', $$$You can access the search for payment on the Payment Page.
You may search for payments from any account based upon a variety of criteria.$$$, $$$The following figure shows the payment search page.
<figure>
  <img src="i/help/SearchForPayments.png" alt="SearchForPayments" width="700px">
  <figcaption>Search For Payments.</figcaption>
</figure>


Leave the Amount drop-down box set to "Any Amount" if you do not want to search for an amount.
Otherwise, choose one of the following criteria and enter an amount.

<figure>
  <img src="i/help/SearchPaymentAmountValues.png" alt="SearchPaymentAmountValues" width="700px">
  <figcaption>Search Payment Amount Values.</figcaption>
</figure>


Here is the list of the possible value for the Amount dropdown:
<ul>
 <li>Less than</li>
<li> Less than or equal to</li>
<li> Equal to</li>
<li>Greater than or equal to</li>
<li> Greater than</li>

<li>Not Equal to</li>
</ul>
<p></p>
You can specify one of the following statuses for the search. Note that you can even search for
deleted records.
<ul>
 <li>Adjustment</li>
 <li>Annuity</li>
 <li>Any Status</li>
 <li>Batch Auto Refund</li>
 <li>Batch Auto Refund Canceled</li>
 <li>Credit Balance Refund</li>
 <li>Debit Voucher</li>

<li> Deleted Transactions</li>
<li> Direct Pay Life</li>
<li> Health Benefits</li>
<li> Manual Adjustment Cancelled</li>
<li> Posted</li>
<li> Reversed</li>
<li> Suspended</li>
<li> Suspense Refund</li>
 <li>Transferred Amount</li>
 <li>Unresolved</li>
 <li>Voluntary Contributions</li>
<li> Write-Off</li>
</ul>
<figure>
  <img src="i/help/SearchPaymentStatus.png" alt="SearchPaymentStatus" width="700px">
  <figcaption>Search Payment Status.</figcaption>
</figure>

<p></p>
The type may be ACH, Lockbox check or Manual Payment. Note that adjustments like write-off or
credit refund are considered manual payments. Note: the "Prior Payment Recorded" entries from
the Refunds Section are not included in searches.
<figure>
  <img src="i/help/SearchPaymentType.png" alt="SearchPaymentType" width="700px">
  <figcaption>Search Payment Type.</figcaption>
</figure>



The Resolved Suspense check box will only include payments that appeared on the Suspense tab of
the application because the lockbox payments did not match an active account on both the CSD and
the date of birth. If it is unchecked, all payments will match, Posted Lockbox, Resolved Suspense, and
Manual Payments
<p></p>
The deposit date (received date for over-the-counter payments) may be a single date or a range of
dates. The deposit date does not include the time of day, just the calendar date.
<figure>
  <img src="i/help/SearchPaymentDeposited.png" alt="SearchPaymentDeposited" width="700px">
  <figcaption>Search Payment Deposited.</figcaption>
</figure>



For a range of dates, another date field appears. The range is inclusive so that the search will return
payments on the beginning date, on the ending date and everything in between.
<figure>
  <img src="i/help/SearchPaymentsDepositedBetween.png" alt="SearchPaymentsDepositedBetween" width="700px">
  <figcaption>Search Payments Deposited Between.</figcaption>
</figure>



You do not need to quit this page to make another search.
<p></p>
Click the "Find" button will show below the search filter, a table with the results. Depending upon
your criteria, the search may be quick or slow. Please be patient.

$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (22, false, 'Payments Search Results', $$$You can access the payment results after searching for payments on the Payment Page.$$$, $$$The Payments search results window is show below.
<figure>
  <img src="i/help/PaymentsResults.png" alt="Payments Results" width="700px">
  <figcaption>Payments Results.</figcaption>
</figure>

The search criteria are listed in the top part of the page. Since you may open as many of these pages
as you want, this will help you remember your search criteria.
<p></p>
A line just below the search buttons shows the number of matching payments found.
<p></p>
The grid shows the payments. The IDNum is the database identifier that is discretely displayed in the
status bar of the Suspense and Payment History tabs. The last column is the Import Record from the
lockbox bank, if applicable. In the example below, the "Posted Complete" payment records have text
in the Import Record column because the payment came from the lockbox. However, the "Batch
Auto Refund" records are generated by the nightly batch so they have no Import Record entry.
<figure>
  <img src="i/help/PaymentsResultsButtons.png" alt="PaymentsResultsButtons" width="700px">
  <figcaption>Payments Results Buttons.</figcaption>
</figure>




You may see and print the audit trail for any payment record by clicking the "Show Audit Trail"
button. The audit trail opens in the Reports Viewer. The "Copy to Clipboard" button will copy the
cells to the Windows clipboard so you may paste this information into another program. Excel works
very well as this grid is similar to a spreadsheet.
<p></p>
You can view the account associated to a payment by clicking "View Account" button.
You can add a payment by first selecting a payment (this will set the account associated to that
payment). Then click the "Add Payment" button. You can see help section on "Add Payment" for more
information.
$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (23, false, 'Search for Accounts', $$$You can search for account from the "View Account" Page on the main menu.
Once the results are displayed you can click on an account to display its details in the Details Page.
$$$, $$$
To search for accounts, click on "View Account" on the main menu.
The following windows will appear.
<figure>
  <img src="i/help/SearchForAccount.png" alt="SearchForAccount" width="700px">
  <figcaption>Search For Account.</figcaption>
</figure>

<p></p>
Enter in the search criteria in the Search Window. You can either search by Claim Number or Social
Security Number or Last name or Date of Birth, or a combination of both the Last name and the Date
of Birth.
Click the "Search" button or press the enter key to perform the search. If your search returns
multiple results (more than one account), the following "Search Results" window is displayed.
<figure>
  <img src="i/help/AccountSearchResults.png" alt="Account Search Results" width="700px">
  <figcaption>Account Search Results.</figcaption>
</figure>



Click on any row to go to the associated Account Details Page.
$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (24, false, 'Create New Account Page', $$$The Create New Account Page will help you create a new account. To access it, click on "Create New
Account" in the main menu
$$$, $$$The Create New Account Page will help you create a new account. To access it, click on "Create New
Account" in the main menu
$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (25, false, 'Create New Account Page - Basic Information', $$$This is the page where you input basic information like address, names ... when creating a new account.$$$, $$$This is the first step for creating account.
When you access the Create New Account page, you will see the following page.
<figure>
  <img src="i/help/CreateNewAccountStep1.png" alt="Create New Account Step1 - Basic Information" width="700px">
  <figcaption>Create New Account Step1 - Basic Information.</figcaption>
</figure>


Choose the "Form Submitted" first. Then fill in the text fields. Fields with an asterisk next to them
are mandatory. Click the "Next" button when you're done. The "Cancel" button exits with neither
saving the data nor creating the account.
<p></p>
If you make an error, the input with errors will become red. They will have an error icon next to
them. Click on th icon to see the error message.
<figure>
  <img src="i/help/CreateNewAccountStep1Error.png" alt="Create New Account Step1 - Error" width="700px">
  <figcaption>Create New Account Step1 - Error.</figcaption>
</figure>



Make sure everything input has no errors and click "Next" and you will go to Step 2
<figure>
  <img src="i/help/CreateNewAccountStep1Success.png" alt="Create New Account Step1 - Success" width="700px">
  <figcaption>Create New Account Step1 - Success.</figcaption>
</figure>

$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (26, false, 'Create New Account Page - Service History', $$$This is the second step of the new account creation. You will have the opportunity to add calculations version, to run them and to save them directly when creating the new account.$$$, $$$ This is the step 2 for creating a new Account
The page of the step 2 looks like
<figure>
  <img src="i/help/CreateNewAccountStep2.png" alt="CreateNewAccountStep2" width="700px">
  <figcaption>Create New Account Step 2 Service History.</figcaption>
</figure>



This will allow you to do the same things as in the Service History tab in the Account Details Page.
See its help for more information.

<p></p>
At the bottom of the page you will also see the following.
<figure>
  <img src="i/help/CreateNewAccountStep2DateCalculator.png" alt="CreateNewAccountStep2DateCalculator" width="700px">
  <figcaption>Create New Account Step2 Date Calculator.</figcaption>
</figure>


This helps you to calculate the "Decimal Years" between two dates. Enter the starting and ending
dates then click the "Calculate" button. The fractional years and midpoint appear in their boxes.


Note that you can leave this page without doing any modification. Just click the "Next" button and
you will go to step 3.
<ul>
<li> You can go to step 1 by clicking the "Previous" button or "Basic Information" link.</li>
 <li>You can also go to step 3 by clicking "Notes" link.</li>
 <li>You can also directly go to the Final step by clicking "Finish" link.</li>
 <li>The "Cancel" button exits with neither saving the data nor creating the account.</li>
</ul>
$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (27, false, 'Create New Account Page - Notes', $$$This is the third step of a new account creation.  It gives you the possibility to add one note to the newly created account.$$$, $$$This is the third step when creating a new account.
The step 3 will allow you to add a note to the new account.
<figure>
  <img src="i/help/CreateNewAccountStep3Notes.png" alt="Create New Account Step3 Notes" width="700px">
  <figcaption>Create New Account Step3 Notes.</figcaption>
</figure>

You can add a note or leave the notes area empty in order not to add a note.
Click "Next" or "Finish" to go the final step. Click "Previous" to go to step 2.
The "Cancel" button exits with neither saving the data nor creating the account.
$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (28, false, 'Create New Account Page - Finish', $$$This is the last step of a new account creation. It gives you a summary of all information you have inputed. It gives you the possibility to edit them if you want to.
Once everything is according to your wish you can finalise the new account creation.$$$, $$$This is the final step when creating a new account.
This step will show the summary of the account information we want to create.
We will see a summary of the Basic Information.

<figure>
  <img src="i/help/CreateNewAccountFinalStep.png" alt="Create New Account Final Step" width="700px">
  <figcaption>Create New Account Final Step.</figcaption>
</figure>

You can still click the "Edit" button if you want to make changes.
Further below you will also see the summary of the Service History and the Account Notes.

<p></p>
Click either of the "Edit" button if you want to make changes to their respective section.
When you are done, you can click the "Finish" button to create the new account. You will be
redirected to the newly created account details page.
You can still click "Previous" to go to step 3.
The "Cancel" button exits with neither saving the data nor creating the account.
$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (29, false, 'Work Queue Page', $$$The WorkQueue Page keeps track of assigned, pending  and processed cases.
$$$, $$$
The Refunds Sections use the tools on this tab to keep track of assigned and pending cases.

<h2>Show Assigned Cases</h2>
When this option is checked, the list shows all assigned cases assigned to you. If you are a
supervisor, it also shows the cases assigned to all the people you supervise. Supervisors can assign a
case to somebody else.

<h2>Show All Pending Cases</h2>
When this option is checked, the list shows all pending cases. You can assign a case to yourself. If
you are a supervisor, you can assign a case to your workers.
<figure>
  <img src="i/help/WorkQueuePage.png" alt="Work Queue Page" width="700px">
  <figcaption>Work Queue Page.</figcaption>
</figure>




<h2>Reassign Case</h2>
A list of your supervised workers appears. Click on a person's name to assign the selected case to
her.
<figure>
  <img src="i/help/ReassignCase.png" alt="Reassign Case" width="700px">
  <figcaption>Reassign Case.</figcaption>
</figure>

<h2>Unassign Case</h2>
Clicking on this button will remove the selected case from your list and change it to pending.



<h2>Case View</h2>
Select a case and then reassign it to somebody else or assign it to yourself. If you double-click on a
case, it loads and jumps to the Employee Tab.
$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (30, false, 'Reports Page', $$$The Reports Page shows the reports documents, the correspondance documents as well as the
reference documents. You can print any of these documents or export them to Word (.doc), Pdf
(.pdf) or Open Office compatible (.rtf) formats. Please see section 16 (Reports Viewer) for more
information.
$$$, $$$Reports Page
The Reports Page shows the reports documents, the correspondance documents as well as the
reference documents. You can print any of these documents or export them to Word (.doc), Pdf
(.pdf) or Open Office compatible (.rtf) formats. Please see Reports Viewer help section for more
information on how to use the Reports Viewer.
$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (31, false, 'Reports Tab', $$$The Reports Tab is located on the Reports Page. It presents a list of Report documents that you can open from this screen. Click on the template you want
and you will see a summary of the report in a table.
$$$, $$$This is a list of Report documents that you can open from this screen. Click on the template you want
and you will see a summary of the report in a table. Click on the "Print" button to open the report in
the "Reports Viewer". From there you can print it. Each of these documents performs a database
query every time you open it. Some of the more complex reports might take a half-minute to open
during periods of heavy system use.
<p></p>
Click on the name of a Report to view it.
<figure>
  <img src="i/help/Reports.png" alt="Reports" width="700px">
  <figcaption>Reports.</figcaption>
</figure>

$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (32, false, 'Correspondence Tab', $$$The Correspondence Tab is located on the Reports Page. It presents a list of correspondence documents that you can open from this screen. Click on the template you want
and you will see a summary of the report in a table.$$$, $$$This tab shows the correspondence document. It's usage is similar to the Reports tab.
<figure>
  <img src="i/help/CorrespondenceTab.png" alt="CorrespondenceTab" width="700px">
  <figcaption>Correspondence Tab.</figcaption>
</figure>

$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (33, false, 'Reference Tab', $$$The Reference Tab is located on the Reports Page. It presents a list of reference documents that you can open from this screen. Click on the template you want
and you will see a summary of the report in a table.$$$, $$$Reference Tab
This is a list of Adobe Acrobat documents that you can open from this screen. Double-click on the
template you want and an instance of Adobe Acrobat Reader will open up.
<p></p>
To go to this tab, click on the "Reports" page, then click on the "Reference" tab.
Selecting a Reference Document<br>
Click on the name of the reference document that you want to read. The displayed names are the
actual file names without the file extension. Adobe's Acrobat Reader will open it.



<figure>
  <img src="i/help/ReferenceTab.png" alt="ReferenceTab" width="700px">
  <figcaption>Reference Tab.</figcaption>
</figure>
$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (34, false, 'Tools Page', $$$The Tools Page allows you to customize the way you interact with the SCRD website.$$$, $$$The Tools Page allows you to customize the way you interact with the SCRD website.
From there you can set the Service Credit Preferences, the Printing Tools and you can view the Batch Printout Archive.$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (35, false, 'Service Credit Preferences', $$$In the Service Credit Preferences tab, you can set some preferences which will define the way you
see the Service Credit front end
$$$, $$$
In the Service Credit Preferences tab, you can set some preferences which will define the way you
see the Service Credit front end.

<figure>
  <img src="i/help/ServiceCreditPreferences.png" alt="Service Credit Preferences" width="700px">
  <figcaption>Service Credit Preferences.</figcaption>
</figure>
<p></p>
Check the "Use Agents" to use agents. Check the "Use Status Bar" will show status bar at the bottom
of every page. Check "Use Message Box" will show info, error and warning information in custom
message box. You can also set additionnal preferences using the "Some Other Setting" input text.<br>
Click "Save" to save your changes, "Reset" to put everything to where they were before you start
modifications and "Cancel" to cancel.
$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (36, false, 'Printing Tools', $$$The Printing Tools can be accessed on the Tools page and will show you some useful options to customize how the different documents will be printed.
$$$, $$$
This page will show you some useful printing tools.
<figure>
  <img src="i/help/PrintingTools.png" alt="PrintingTools" width="700px">
  <figcaption>Printing Tools.</figcaption>
</figure>

Click on them to see the provided tools.
$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (37, false, 'Batch Printout Archive', $$$To access the Batch Printout Archive, go to the "Tools" Page. Click on the "Batch Printout Archive"
tab. The nightly batch process saves all its printouts in a network folder. You can view these files on
screen. You can also print all or part of the document.
$$$, $$$
To access the Batch Printout Archive, go to the "Tools" Page. Click on the "Batch Printout Archive"
tab.<br>
The nightly batch process saves all its printouts in a network folder. You can view these files on
screen. You can also print all or part of the document.<br>

The program looks up the print archive PDF files in the network share. Double-click the report of
interest to open it in Adobe Acrobat reader. Use that program to navigate and print.
<figure>
  <img src="i/help/BatchPrintoutArchive.png" alt="BatchPrintoutArchive" width="700px">
  <figcaption>Batch Printout Archive.</figcaption>
</figure>

$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (38, false, 'Admin Page', $$$The Admin Page allow you to perform actions that require administrative privileges. For example you can modify user permissions and
regenerate latest reports
$$$, $$$Some features in the Service Credit system require membership in a role with enhanced
authorization - such as "Refunds Supervisor" or "Accounting Supervisor." These features are known
as "Administrative Functions." Some of these Administrative functions may be accessed from the
menu of the Service Credit application under the heading "Admin". Click on "Admin" to access the
Admin Page.
<p>
From there you will be able to "Regenerate Latest Reports" or configure "User Permissions".</p>

$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (39, false, 'Regenerate Latest Reports', $$$Supervisors may view and print the daily reports on-line. These are the reports made by the nightly
batch service but this method queries the database again to pick up any changes since last night.
To regenerate the latest reports, on the Admin Page, click on the tab "Regenerate Latest Reports"
$$$, $$$
Supervisors may view and print the daily reports on-line. These are the reports made by the nightly
batch service but this method queries the database again to pick up any changes since last night.
To regenerate the latest reports, on the Admin Page, click on the tab "Regenerate Latest Reports".
<br>
You will get following view:

<figure>
  <img src="i/help/regenerateLatestReports.png" alt="Regenerate Latest Reports" width="700px">
  <figcaption>Regenerate Latest Reports.</figcaption>
</figure>

<p></p>

The page shows which report had output and how many individual statements were printed for
each. Click each button to generate the reports with last night's data. The reports will be displayed

in the "Reports Viewer". For example, if you click "Initial Bills Invoice" you will see the following
figure.

<figure>
  <img src="i/help/regenerateLatestReportsInitialBill.png" alt="Regenerate Latest Reports Initial Bill" width="700px">
  <figcaption>Regenerate Latest Reports Initial Bill.</figcaption>
</figure>
$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (40, false, 'User Permissions', $$$The System Administrator can change user information, status and permissions with this option.
Supervisors can change user information and status but not permissions.
The "User Permissions" option is only enabled for supervisors. To access it, on the Admin Page click
"User Permissions" tab. You will then see a list of users.
$$$, $$$
The System Administrator can change user information, status and permissions with this option.
Supervisors can change user information and status but not permissions.
The "User Permissions" option is only enabled for supervisors. To access it, on the Admin Page click
"User Permissions" tab. You will then see a list of users.
<h2>User List</h2>
A list of users appear. The type of users depend upon the supervisor's role. Everybody appears if the
supervisor is a System Administrator. Otherwise the supervisor will see the following users:
<ul>
 <li>All new users</li>
 <li>Users directly supervised by you, regardless of their role</li>

<li>Users in a subordinate role supervised by somebody else (e.g., a Financial Supervisor will see
all Financial Technicians even if they are assigned to another supervisor.</li>
</ul>
<p></p>
You can double-click on a user to view its information.
<h2>User Information</h2>
The following figure displays the information you can modify about a user.

<figure>
  <img src="i/help/editUserPermissions.png" alt="Edit User Permissions" width="700px">
  <figcaption>Edit User Permissions.</figcaption>
</figure>

The user status can be one of four types:
<ul>
 <li>Active User - Full access for their role
 <li>New User - No permissions
 <li>Separated User - No longer works with Service Credit
 <li>Suspended User - User access stopped
 	</ul>
 	<p></p>
The "Suspended User" and "Separated User" are functionally the same. Both are included for
annotation purposes.

<p></p>
For information about the permission roles, see the User Role Permissions report in the Reports tab.
Only the System Administrator can change this field.
All of the supervisors are in the Supervisor drop-down list. Pick the person that directly supervises
this user.
$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (41, false, 'Notification Log Page', $$$The Notification Log Page lists the notifications, errors and information of the SCRD application.
It will display important information you need to know about the use of SCRD application.
$$$, $$$Notification Log Page
The Notification Log Page lists the notifications, errors and information of the SCRD application.
It will display important information you need to know about the use of SCRD application.
$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (42, false, 'Notifications', $$$The Notifications tab will show you the list of notifications.
In general, the notifications will be information which will require some further actions from you.
$$$, $$$The Notifications tab will show you the list of notifications. It will give you information about the
payments you need to approve, the pending payment assigned to you. The notifications will
generally contains a link to the relevant page. For example you can have a notification as "You have
4 payments in Approval Page" or "You have 2 records in Work Queue Page". Each time with the link.
<p></p>
In general, the notifications will be information which will require some further actions from you.
<figure>
  <img src="i/help/Notifications.png" alt="Notifications" width="700px">
  <figcaption>Notifications.</figcaption>
</figure>
$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (43, false, 'Notifications of Errors', $$$The Errors tab will show the list of errors occurred through the use of the SCRD application. 
$$$, $$$
The Errors tab will show the list of errors occurred through the use of the SCRD application. It can
tell you for example the number of failed calculations version or the number of failed payment
addition to an account.

<figure>
  <img src="i/help/ErrorsLog.png" alt="Notifications" width="700px">
  <figcaption>ErrorsLog.</figcaption>
</figure>

$$$);
INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (44, false, 'Notifications of Information', $$$The Info tab will show the list of information which does not required further action from you.
$$$, $$$
The Info tab will show the list of information which does not required further action from you. For
example it can tell you that you have successfully validated a calculation version or that you have
updated your account basic information.
<figure>
  <img src="i/help/InfoLog.png" alt="payment Processing Logic" width="700px">
  <figcaption>Info Log.</figcaption>
</figure>

$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (45, false, 'Help Page', $$$The Help Page will show the help for using the SCRD. It will contain a guide for each page of the
application.
$$$, $$$
The Help Page will show the help for using the SCRD. It will contain a guide for each page of the
application.

<figure>
  <img src="i/help/HelpPage.png" alt="HelpPage" width="700px">
  <figcaption>Help Page.</figcaption>
</figure>
$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (46, false, 'Refund Logic', $$$Refunding a suspended or unresolved payment requires a refund to be made to the person who sent it in the check.
This section describes the process to follow for a refund.
$$$, $$$
<h2>Suspended Payment</h2>
A Suspended Payment is one from somebody without a Service Credit account. Since Service Credit
received the funds in error, they must refund them. The Receivables Technician determines that the
payment should not have been cashed for Service Credit and changes its state from "Suspended" to
"Suspense Refund Pending Approval." <br>The supervisor approves this action and changes the payment
status from "Suspense Refund Pending Approval" to "Suspense Refund Pending." Now it's ready for
the Nightly Batch Process to finish the job. The Nightly Batch Process will mark the payment as
"Suspense Refund Complete" and make the offsetting "Batch Auto Refund." Now there is credit
entry as well as a debit entry on the ledger.

<figure>
  <img src="i/help/SuspendedPaymentRefundLogic.png" alt="SuspendedPaymentRefundLogic" width="700px">
  <figcaption>Suspended Payment Refund Logic.</figcaption>
</figure>




<h2>Unresolved Payment</h2>
An Unresolved Payment is one from somebody with a Service Credit account but there is something
wrong with either the payment or the account. Since Service Credit received the funds in error, they
must refund them. The Receivables Technician determines that the payment should not have been
cashed for Service Credit and changes its state from "Suspended" to "Suspense Refund Pending
Approval." The supervisor approves this action and changes the payment status from "Suspense
Refund Pending Approval" to "Suspense Refund Pending." Now it's ready for the Nightly Batch
Process to finish the job. The Nightly Batch Process will mark the payment as "Suspense Refund
Complete" and make the offsetting "Batch Auto Refund." Now there is credit entry as well as a debit
entry on the ledger.


<h2>Credit Balance</h2>
If a payment comes in for an account with a credit balance, the payment will be unresolved and the
Receivables Technician has the option to refund the account's credit balance at the same time when
they refund the overpayment. If the technician clicks "Yes" for the additional refund, the refund
amount on the Refund Memo will equal the sum of the account's credit balance and the refunded
payment. The supervisor will see this sum on the approval screen as well.<p></p>

The process is different from the Suspended Payment refund only in that there is the optional refund
of the pre-existing credit balance.
<figure>
  <img src="i/help/CreditBalanceRefund.png" alt="CreditBalanceRefund" width="700px">
  <figcaption>Credit Balance Refund.</figcaption>
</figure>

<figure>
  <img src="i/help/CreditBalanceRefundLogic.png" alt="CreditBalanceRefundLogic" width="700px">
  <figcaption>Credit Balance Refund Logic.</figcaption>
</figure>





This chart shows the logic flow for the Receivables Branch.
<figure>
  <img src="i/help/LogicFlowforReceivableBranch.png" alt="LogicFlowforReceivableBranch" width="700px">
  <figcaption>Logic Flow for Receivable Branch.</figcaption>
</figure>$$$);

INSERT INTO opm.help_item (id, deleted, title, summary, content) VALUES (47, false, 'Reports Viewer', $$$Reports Viewer is used throughout the website to show reports.
This section describes how to use it.$$$, $$$Reports Viewer is used throughout the website to show reports.
You can download any document as pdf, .doc or .rtf. You can also print a document.
Please to do any of these actions see the following figure



<figure>
  <img src="i/help/ReportsViewer.png" alt="ReportsViewer" width="700px">
  <figcaption>Reports Viewer.</figcaption>
</figure>
$$$);









----------------------------------------------------
-- 			related_help_items table
----------------------------------------------------
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (1,  2, 3);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (2,  1, 3);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (3,  4, 5);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (4,  3, 5);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (5,  3, 6);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (6,  3, 8);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (8,  3, 9);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (9,  3, 10);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (10,  3, 11);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (11,  12, 13);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (12,  13, 14);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (13,  14, 15);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (14,  15, 16);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (15,  16, 17);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (16,  17, 18);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (17,  18, 19);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (18,  20, 21);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (19,  22, 23);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (20,  24, 25);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (21,  25, 26);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (22,  27, 28);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (23,  29, 30);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (24,  30, 31);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (25,  26, 27);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (26,  27, 28);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (27,  28, 29);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (28,  29, 30);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (29,  30, 31);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (30,  31, 32);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (31,  32, 33);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (32,  33, 34);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (33,  34, 35);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (34,  35, 36);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (35,  36, 37);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (36,  37, 38);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (37,  38, 39);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (38,  39, 40);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (39,  40, 41);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (40,  41, 42);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (41,  42, 43);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (42,  43, 44);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (43,  44, 45);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (44,  45, 46);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (45,  46, 47);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (46,  47, 1);
INSERT INTO opm.related_help_items(id, help_item_id1, help_item_id2) values (47,  1, 2);


