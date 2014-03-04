USE [ServiceCredit]
GO;;
/****** Object:  Table [dbo].[AnnuityRollExtract]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
CREATE TABLE [dbo].[AnnuityRollExtract](
	[CLAIM] [nvarchar](255) NULL,
	[SSN] [nvarchar](255) NULL,
	[ACDate] [nvarchar](255) NULL,
	[SCMClaimNumber] [nvarchar](255) NULL,
	[PRCODE] [nvarchar](255) NULL,
	[TOTSV] [nvarchar](255) NULL,
	[LNAME] [nvarchar](255) NULL
) ON [PRIMARY]
GO;;
/****** Object:  Table [dbo].[LookUpGLCodes]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookUpGLCodes](
	[GLID] [int] IDENTITY(1,1) NOT NULL,
	[GLName] [varchar](50) NOT NULL,
	[GLCode] [varchar](50) NOT NULL,
	[PaymentType] [char](2) NULL,
	[SCMRetirementTypeCode] [tinyint] NULL,
	[PostOffice] [bit] NOT NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The ID Number of this field' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpGLCodes', @level2type=N'COLUMN',@level2name=N'GLID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The Name of the General Ledger code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpGLCodes', @level2type=N'COLUMN',@level2name=N'GLName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The Value of the General Ledger code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpGLCodes', @level2type=N'COLUMN',@level2name=N'GLCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Payment Code: R1, R3, R4, or R6' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpGLCodes', @level2type=N'COLUMN',@level2name=N'PaymentType'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Map to LookupRetirementType table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpGLCodes', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if the Agency Code for the account is Post Office' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpGLCodes', @level2type=N'COLUMN',@level2name=N'PostOffice'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The codes for the General Ledger query. This can be easily changed if  the accountants change the GL codes.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpGLCodes'
GO;;
/****** Object:  Table [dbo].[LookupPayTransStatusCode]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupPayTransStatusCode](
	[PayTransStatusCode] [tinyint] NOT NULL,
	[PayTransStatusDescription] [varchar](50) NULL,
	[PayTransDisplayCategory] [tinyint] NULL,
	[PayTransDisplayOrder] [tinyint] NULL,
	[NextStateLink] [tinyint] NULL,
	[BatchProcessingOrder] [tinyint] NULL,
	[FinalState] [bit] NULL,
	[NeedsApproval] [bit] NULL,
	[ShowOnSuspense] [bit] NULL,
	[IncludeInBalance] [bit] NULL,
	[NightlyBatch] [bit] NULL,
	[Deletable] [bit] NULL,
	[Reversable] [bit] NULL,
	[ManualEntered] [bit] NULL,
	[SuspenseAction] [bit] NULL,
	[CanHitGL] [bit] NULL,
	[ReversingType] [bit] NULL,
	[BalancedScorecard] [bit] NULL,
	[SendToDBTS] [bit] NULL,
 CONSTRAINT [PK_LookupPayTransStatusCode] PRIMARY KEY CLUSTERED 
(
	[PayTransStatusCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'After the next process, this payment will enter this state. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'NextStateLink'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The order for processing payments during the nightly batch. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'BatchProcessingOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This payment is done although it may be cancelled or reversed.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'FinalState'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Show this payment in the manager''s approval screen' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'NeedsApproval'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Show on the Suspense tab in the application' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'ShowOnSuspense'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Include this payment''s money amount in the account balance. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'IncludeInBalance'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This type will be processed tonight. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'NightlyBatch'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This state is for payments that may be deleted. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'Deletable'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This state is for payments may be reversed or cancelled' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'Reversable'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This state is for payments that were manually entered with the application' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'ManualEntered'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This state is for payments that were suspended at one time. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'SuspenseAction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this type of payment may hit the general ledger during the evening batch process' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'CanHitGL'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this was a reversal or cancellation of another "Final" type' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'ReversingType'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this type of payment should appear in the quarterly Balanced Scorecard reports. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'BalancedScorecard'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this should go to DBTS in their inbound file.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode', @level2type=N'COLUMN',@level2name=N'SendToDBTS'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This look up table shows the possible states for payments and info about those states.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTransStatusCode'
GO;;
/****** Object:  Table [dbo].[LookUpPaymentAppliedOrder]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookUpPaymentAppliedOrder](
	[LookUpPaymentAppliedOrderCode] [int] NOT NULL,
	[PaymentAccount] [varchar](50) NOT NULL,
	[PaymentAppliedDisplayOrder] [tinyint] NOT NULL,
 CONSTRAINT [PK_LookUpPaymentAppliedOrder] PRIMARY KEY CLUSTERED 
(
	[LookUpPaymentAppliedOrderCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'ID field for this table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpPaymentAppliedOrder', @level2type=N'COLUMN',@level2name=N'LookUpPaymentAppliedOrderCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'One of the four types of accounts.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpPaymentAppliedOrder', @level2type=N'COLUMN',@level2name=N'PaymentAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The order in which to display these rows.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpPaymentAppliedOrder', @level2type=N'COLUMN',@level2name=N'PaymentAppliedDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Lists the four types of accounts that receive payment. The customer chooses the order to apply the payments.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpPaymentAppliedOrder'
GO;;
/****** Object:  Table [dbo].[LookupAccountStatus]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupAccountStatus](
	[AccountStatus] [tinyint] NOT NULL,
	[AccountStatusDescription] [varchar](50) NULL,
	[AcountStatusCategory] [tinyint] NULL,
	[AccountStatusDisplayOrder] [tinyint] NULL,
 CONSTRAINT [PK_LookupAccountStatus] PRIMARY KEY CLUSTERED 
(
	[AccountStatus] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'a numeric code representing the status of an account' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=4404 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'the description of the account status represented by the record' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AcountStatusCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AcountStatusCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2952 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AcountStatusCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Indicates whether an account status is open (0) or closed(1)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AcountStatusCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AcountStatusCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AcountStatusCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AcountStatusCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Specifies an order that records are displayed in user interface lists' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatusDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatusDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatusDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus', @level2type=N'COLUMN',@level2name=N'AccountStatusDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A lookup table that associates account status codes with their descriptions' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=10000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAccountStatus'
GO;;
/****** Object:  Table [dbo].[LookupAgencyCode]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupAgencyCode](
	[SCMAgencyCode] [int] NOT NULL,
	[SCMAgencyDescription] [varchar](50) NULL,
	[AgencyCodeDisplayOrder] [int] NULL,
 CONSTRAINT [PK_LookupAgencyCode] PRIMARY KEY CLUSTERED 
(
	[SCMAgencyCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The agency code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAgencyCode', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAgencyCode', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAgencyCode', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAgencyCode', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The name of the agency' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAgencyCode', @level2type=N'COLUMN',@level2name=N'SCMAgencyDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAgencyCode', @level2type=N'COLUMN',@level2name=N'SCMAgencyDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAgencyCode', @level2type=N'COLUMN',@level2name=N'SCMAgencyDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAgencyCode', @level2type=N'COLUMN',@level2name=N'SCMAgencyDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Specifies an order that records are displayed in user interface lists' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAgencyCode', @level2type=N'COLUMN',@level2name=N'AgencyCodeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A lookup table that associates agency codes with their descriptions' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAgencyCode'
GO;;
/****** Object:  Table [dbo].[LookupAppointmentTypeCode]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupAppointmentTypeCode](
	[SCMAppointmentTypeCode] [char](1) NOT NULL,
	[SCMAppointmentTypeDescription] [varchar](50) NULL,
	[AppoinmentTypeDisplayOrder] [tinyint] NULL,
	[AppointmentTypeCategory] [char](1) NULL,
 CONSTRAINT [PK__LookupWorkTypeCo__52593CB8] PRIMARY KEY CLUSTERED 
(
	[SCMAppointmentTypeCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2628 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Description of the appointment' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'AppoinmentTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'AppoinmentTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2184 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'AppoinmentTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Specifies the order that codes are displayed in user interface lists. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'AppoinmentTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'AppoinmentTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'AppoinmentTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'AppointmentTypeCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'AppointmentTypeCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'AppointmentTypeCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'C - Covered by CSRS, F - Covered by FERS, N - Covered by neither, B - Covered by both' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'AppointmentTypeCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'AppointmentTypeCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'AppointmentTypeCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode', @level2type=N'COLUMN',@level2name=N'AppointmentTypeCategory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A lookup table that associates appointment type codes with their descriptions' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=10000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAppointmentTypeCode'
GO;;
/****** Object:  Table [dbo].[LookupAuditActivity]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupAuditActivity](
	[AuditActivity] [tinyint] NOT NULL,
	[AuditActivityDescription] [varchar](50) NULL,
 CONSTRAINT [PK_LookupAuditActivity] PRIMARY KEY CLUSTERED 
(
	[AuditActivity] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The numeric code assigned to various transaction activities in the audit tables' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivityDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivityDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=3288 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivityDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The  description of the audit activity' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivityDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivityDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivityDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity', @level2type=N'COLUMN',@level2name=N'AuditActivityDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A lookup table that associates audit activity codes with their descriptions' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=20000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupAuditActivity'
GO;;
/****** Object:  Table [dbo].[LookupCalculationStatus]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupCalculationStatus](
	[CalculationStatus] [tinyint] NOT NULL,
	[CalculationStatusDescription] [varchar](50) NULL,
	[CalculationStatusCategory] [tinyint] NULL,
	[CalculationStatusDisplayOrder] [tinyint] NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[LookupChangeTransFieldNumberCode]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupChangeTransFieldNumberCode](
	[ChangeTransFieldNumberCode] [varchar](2) NOT NULL,
	[ChangeTransFieldNumberDescription] [varchar](50) NULL,
 CONSTRAINT [PK_LookupChangeTransFieldNumberCode] PRIMARY KEY CLUSTERED 
(
	[ChangeTransFieldNumberCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=3024 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Numeric code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=5076 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Descriptive name for this code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode', @level2type=N'COLUMN',@level2name=N'ChangeTransFieldNumberDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A lookup table that associates field codes from "Master File Adjustment" fields with their descriptions' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=10000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupChangeTransFieldNumberCode'
GO;;
/****** Object:  Table [dbo].[LookupInterestRates]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
CREATE TABLE [dbo].[LookupInterestRates](
	[InterestYear] [int] NOT NULL,
	[InterestRate] [numeric](18, 9) NULL,
	[InterestRateID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_LookupInterestRates] PRIMARY KEY CLUSTERED 
(
	[InterestRateID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The calendar year for which a variable interest rate is applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupInterestRates', @level2type=N'COLUMN',@level2name=N'InterestYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupInterestRates', @level2type=N'COLUMN',@level2name=N'InterestYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupInterestRates', @level2type=N'COLUMN',@level2name=N'InterestYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupInterestRates', @level2type=N'COLUMN',@level2name=N'InterestYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The variable interest rate for the year expressed as a decimal (eg. 7.5% = .075)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupInterestRates', @level2type=N'COLUMN',@level2name=N'InterestRate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupInterestRates', @level2type=N'COLUMN',@level2name=N'InterestRate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupInterestRates', @level2type=N'COLUMN',@level2name=N'InterestRate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupInterestRates', @level2type=N'COLUMN',@level2name=N'InterestRate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Unique Identifier for the year' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupInterestRates', @level2type=N'COLUMN',@level2name=N'InterestRateID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A lookup table that associates calendar years with the variable interest rates applied to Service Credit balances in those years' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupInterestRates'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupInterestRates'
GO;;
/****** Object:  Table [dbo].[BackupServicePeriods]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[BackupServicePeriods](
	[ServicePeriodsKey] [int] IDENTITY(1,1) NOT NULL,
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[SCMVersion] [int] NULL,
	[SCMLineNumber] [int] NULL,
	[SCMBeginDate] [datetime] NULL,
	[SCMEndDate] [datetime] NULL,
	[SCMRetirementTypeCode] [tinyint] NULL,
	[SCMAgencyCode] [int] NULL,
	[SCMServiceTypeCode] [varchar](2) NULL,
	[SCMAppointmentTypeCode] [char](1) NULL,
	[SCMEnteredAmount] [money] NULL,
	[SCMHoursInYear] [int] NULL,
	[SCMAnnualizedAmount] [money] NULL,
	[SCMPeriodType] [char](1) NULL,
	[SCMDateEntered] [datetime] NULL,
	[SCMEnteredBy] [int] NULL,
	[SCMPayTypeCode] [char](1) NULL,
	[SCMEffectiveDate] [datetime] NULL,
	[AsOfDate] [datetime] NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[ClaimsWithoutService]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[ClaimsWithoutService](
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[SCMDateOfBirth] [datetime] NULL,
 CONSTRAINT [PK_ClaimsWithoutService] PRIMARY KEY CLUSTERED 
(
	[SCMClaimNumber] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[LookupPayTypeCode]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupPayTypeCode](
	[PayTypeCode] [char](1) NOT NULL,
	[PayTypeDescription] [varchar](50) NULL,
	[PayTypeDisplayOrder] [tinyint] NULL,
 CONSTRAINT [PK_LookupPayTypeCode] PRIMARY KEY CLUSTERED 
(
	[PayTypeCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Code number for this state' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=1884 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Descriptive name' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2484 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Specifies the order that codes are displayed in user interface lists. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode', @level2type=N'COLUMN',@level2name=N'PayTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A lookup table that associates pay type codes that are entered for service periods with their descriptions.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=10000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPayTypeCode'
GO;;
/****** Object:  Table [dbo].[LookupPeriodTypeCode]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupPeriodTypeCode](
	[SCMPeriodTypeCode] [char](1) NOT NULL,
	[SCMPeriodTypeDescription] [varchar](50) NULL,
	[PeriodTypeDisplayOrder] [tinyint] NULL,
 CONSTRAINT [PK_LookupPeriodTypeCode] PRIMARY KEY CLUSTERED 
(
	[SCMPeriodTypeCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The code of the service period as represented in the original service credit master' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=1992 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The description of the period type code represented in the service credit master' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'PeriodTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'PeriodTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'PeriodTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Specifies the order that codes are displayed in user interface lists. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'PeriodTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'PeriodTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode', @level2type=N'COLUMN',@level2name=N'PeriodTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A lookup table that associates period type codes that are entered for service periods with their descriptions.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=N'LookupPeriodTypeCode.PeriodTypeDisplayOrder' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x01 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=10000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupPeriodTypeCode'
GO;;
/****** Object:  Table [dbo].[A01_PrintSuppressionCases]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[A01_PrintSuppressionCases](
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[ReasonForPrintSuppression] [int] NULL,
 CONSTRAINT [PK_A01_PrintSuppressionCases] PRIMARY KEY CLUSTERED 
(
	[SCMClaimNumber] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[LookupRetirementTypeCode]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupRetirementTypeCode](
	[SCMRetirementTypeCode] [tinyint] NOT NULL,
	[SCMRetirementTypeDescription] [varchar](50) NULL,
	[RetireMentTypeDisplayOrder] [tinyint] NULL,
 CONSTRAINT [PK_LookupRetirementTYpeCode] PRIMARY KEY CLUSTERED 
(
	[SCMRetirementTypeCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2340 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The retirement type code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The retirement type description' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'RetireMentTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'RetireMentTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'RetireMentTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Specifies the order that codes are displayed in user interface lists. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'RetireMentTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'RetireMentTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode', @level2type=N'COLUMN',@level2name=N'RetireMentTypeDisplayOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A lookup table that associates pay type codes that are entered for accounts and service periods with their descriptions.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=10000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupRetirementTypeCode'
GO;;
/****** Object:  Table [dbo].[LookupSCMPayCode]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupSCMPayCode](
	[SCMPayCode] [char](1) NOT NULL,
	[SCMPayCodeDescription] [varchar](50) NULL,
 CONSTRAINT [PK_LookupSCMPayCode] PRIMARY KEY CLUSTERED 
(
	[SCMPayCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The type of transaction for the last transaction on the SCM' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCodeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCodeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2580 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCodeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Descriptive name for this state' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCodeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCodeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCodeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode', @level2type=N'COLUMN',@level2name=N'SCMPayCodeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A lookup table that associates pay codes that are maintained for accounts with their descriptions.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=10000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupSCMPayCode'
GO;;
/****** Object:  Table [dbo].[PayDateFix]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[PayDateFix](
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[SCMSSN] [char](9) NULL,
	[SCMDateOfBirth] [datetime] NULL,
	[SCMFName] [varchar](40) NULL,
	[SCMMInitial] [char](1) NULL,
	[SCMLastname] [varchar](40) NULL,
	[SCMSuffix] [varchar](3) NULL,
	[SCMName] [varchar](40) NULL,
	[SCMAddress1] [varchar](40) NULL,
	[SCMAddress2] [varchar](40) NULL,
	[SCMAddress3] [varchar](40) NULL,
	[SCMAddress4] [varchar](40) NULL,
	[SCMAddress5] [varchar](40) NULL,
	[SCMCity] [varchar](20) NULL,
	[SCMState] [char](2) NULL,
	[SCMZipcode] [varchar](10) NULL,
	[SCMTelephone] [char](10) NULL,
	[SCMPosition] [varchar](20) NULL,
	[SCMAgencyCode] [char](2) NULL,
	[SCMJobLocationCity] [varchar](20) NULL,
	[SCMJobLocationState] [char](2) NULL,
	[SCMDeposit] [money] NULL,
	[SCMRedeposit] [money] NULL,
	[SCMTotVarRedeposit] [money] NULL,
	[SCMNonDed] [money] NULL,
	[SCMFersW] [money] NULL,
	[SCMAccIntDep] [money] NULL,
	[SCMAccIntRdep] [money] NULL,
	[SCMAccIntNonDed] [money] NULL,
	[SCMAccIntVarRdep] [money] NULL,
	[SCMAccIntFers] [money] NULL,
	[SCMTotPayd] [money] NULL,
	[SCMTotPayr] [money] NULL,
	[SCMTotPayn] [money] NULL,
	[SCMTotPayvr] [money] NULL,
	[SCMTotPayfers] [money] NULL,
	[SCMCompDate] [datetime] NULL,
	[SCMVarIntCompDate] [datetime] NULL,
	[SCMLastAct] [datetime] NULL,
	[SCMLastPay] [datetime] NULL,
	[SCMPayCode] [char](1) NULL,
	[SCMRdepCode] [tinyint] NULL,
	[SCMTimePer] [tinyint] NULL,
	[SCMAddServ] [char](1) NULL,
	[SCMNoInterest] [tinyint] NULL,
	[SCMCode20Date] [datetime] NULL,
	[SCMFlagPreRedep] [char](1) NULL,
	[SCMFlagPostRedep] [char](1) NULL,
	[SCMPriorClaimNumber] [varchar](7) NULL,
	[RetirementTypeCode] [tinyint] NULL,
	[AccountStatus] [tinyint] NULL,
	[PaymentOrder] [varchar](10) NULL,
	[NewClaimNumber] [int] NULL,
	[StopACHPayment] [bit] NOT NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[NotificationAddressInfo]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[NotificationAddressInfo](
	[SCMClaimNumber] [varchar](7) NULL,
	[SCMFName] [varchar](40) NULL,
	[SCMLastname] [varchar](40) NULL,
	[SCMName] [varchar](40) NULL,
	[SCMAddress1] [varchar](40) NULL,
	[SCMAddress2] [varchar](40) NULL,
	[SCMAddress3] [varchar](40) NULL,
	[SCMAddress4] [varchar](40) NULL,
	[SCMAddress5] [varchar](40) NULL,
	[SCMCity] [varchar](20) NULL,
	[SCMState] [char](2) NULL,
	[SCMZipcode] [varchar](10) NULL,
	[Balance] [decimal](18, 2) NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[AddressDataFix]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[AddressDataFix](
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[SCMFName] [varchar](40) NULL,
	[SCMLastname] [varchar](40) NULL,
	[SCMName] [varchar](40) NULL,
	[SCMAddress1] [varchar](40) NULL,
	[SCMAddress2] [varchar](40) NULL,
	[SCMAddress3] [varchar](40) NULL,
	[SCMAddress4] [varchar](40) NULL,
	[SCMAddress5] [varchar](40) NULL,
	[SCMCity] [varchar](20) NULL,
	[SCMState] [char](2) NULL,
	[SCMZipcode] [varchar](10) NULL,
	[Temp1] [nvarchar](40) NULL,
	[Temp2] [nvarchar](40) NULL,
	[Temp3] [nvarchar](40) NULL,
	[Temp4] [nvarchar](40) NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[LookupUserStatusCode]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupUserStatusCode](
	[UserStatusCode] [int] NOT NULL,
	[UserStatusDescription] [varchar](50) NULL,
 CONSTRAINT [PK_LookupUserStatusCode] PRIMARY KEY CLUSTERED 
(
	[UserStatusCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 = Newly added, 1 = Active, 2 = Suspended/History' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2316 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The description of the user code for the record' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode', @level2type=N'COLUMN',@level2name=N'UserStatusDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This look up table shows the possible statuses for users of the Service Credit system.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=20000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserStatusCode'
GO;;
/****** Object:  Table [dbo].[CalculatedServicePeriods]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[CalculatedServicePeriods](
	[CalculatedServicePeriodsKey] [int] IDENTITY(1,1) NOT NULL,
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[SCMEnteredBy] [int] NULL,
	[SCMPeriodTypeCode] [char](1) NULL,
	[SCMRetirementTypeCode] [tinyint] NULL,
	[CalculationDate] [datetime] NULL,
	[LastUpdate] [datetime] NULL,
	[AsOfDate] [datetime] NULL,
	[Version] [int] NULL,
	[LineNumber] [int] NULL,
	[IsOfficial] [bit] NULL,
	[ServicePeriodStartDate] [datetime] NULL,
	[ServicePeriodEndDate] [datetime] NULL,
	[ServicePeriodMidpoint] [datetime] NULL,
	[ServicePeriodEffectiveDate] [datetime] NULL,
	[DeductionAmount] [money] NULL,
	[InterestAmount] [money] NULL,
	[PaymentsApplied] [money] NULL,
	[TotalBalance] [money] NULL,
	[CalculationStatus] [tinyint] NULL,
	[PaymentOrder] [int] NULL,
	[InterestAccrualDate] [datetime] NULL
) ON [PRIMARY]
SET ANSI_PADDING OFF
ALTER TABLE [dbo].[CalculatedServicePeriods] ADD [SCMServiceTypeCode] [varchar](2) NULL
ALTER TABLE [dbo].[CalculatedServicePeriods] ADD  CONSTRAINT [PK_CalculatedServicePeriods] PRIMARY KEY CLUSTERED 
(
	[CalculatedServicePeriodsKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculatedServicePeriodsKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculatedServicePeriodsKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculatedServicePeriodsKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The CSD number of the account' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The ID of the user who performed the calculation' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A foreign key to the LookupPeriodType table K, L, M, N - for Post and pre redeposit and deposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date the calculation was performed' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'LastUpdate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'LastUpdate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'LastUpdate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date the record was last updated' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'LastUpdate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'LastUpdate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'LastUpdate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'LastUpdate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'AsOfDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'AsOfDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'AsOfDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date used to calculate interest' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'AsOfDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'AsOfDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'AsOfDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'AsOfDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'Version'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'Version'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'Version'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The version number corresponding to the entered service periods' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'Version'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'LineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'LineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'LineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The line number of the calculated service period for display purposes' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'LineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'IsOfficial'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'IsOfficial'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'IsOfficial'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A boolean value indicating whether this is the official triggered calculation result' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'IsOfficial'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'IsOfficial'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'IsOfficial'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'IsOfficial'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodStartDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodStartDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodStartDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The starting date of the extended service period (may include multiple entered service periods)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodStartDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The ending date of the extended service period (may include multiple entered service periods)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodMidpoint'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodMidpoint'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodMidpoint'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The midpoint  date of the extended service period (may include multiple entered service periods)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodMidpoint'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodMidpoint'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodMidpoint'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The effective date of the extended service period for lump sum and refund service periods' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'DeductionAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'DeductionAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'DeductionAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The principle amount required to pay back a deposit or redeposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'DeductionAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'DeductionAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'DeductionAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'DeductionAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'InterestAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'InterestAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'InterestAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The calculated interest required to pay back the deposit or redeposit as of the AsOfDate' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'InterestAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'InterestAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'PaymentsApplied'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'PaymentsApplied'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'PaymentsApplied'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The total of all payments received and posted for this service period' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'PaymentsApplied'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'PaymentsApplied'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'PaymentsApplied'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'PaymentsApplied'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'TotalBalance'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'TotalBalance'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'TotalBalance'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The balance remaining to pay off the service period' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'TotalBalance'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'TotalBalance'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'TotalBalance'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'TotalBalance'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2565 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The status of this calculated service period record - foreign key to the LookupCalculationStatus table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods', @level2type=N'COLUMN',@level2name=N'CalculationStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The Calculated Service Periods table contains the calculated amount due for the periods of service, effective dates, any money already applied, and if this is an official version to bill.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=10000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CalculatedServicePeriods'
GO;;
/****** Object:  Table [dbo].[ConfigurationSettings]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[ConfigurationSettings](
	[UserKey] [int] NOT NULL,
	[PreferenceSettings] [varbinary](8000) NULL,
 CONSTRAINT [PK_ConfigurationSettings] PRIMARY KEY CLUSTERED 
(
	[UserKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[ContactInfo]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[ContactInfo](
	[IDContactInfo] [int] IDENTITY(1,1) NOT NULL,
	[ContactName] [varchar](20) NOT NULL,
	[ContactText] [varchar](1000) NOT NULL,
 CONSTRAINT [IX_ContactName] UNIQUE NONCLUSTERED 
(
	[ContactName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Friendly Name for this contact' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ContactInfo', @level2type=N'COLUMN',@level2name=N'ContactName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Free form address, telephone number, email, etc.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ContactInfo', @level2type=N'COLUMN',@level2name=N'ContactText'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Contact Information for the Crystal Reports & memos that we send to the applicants.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ContactInfo'
GO;;
/****** Object:  Table [dbo].[Holidays]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[Holidays](
	[Holiday] [varchar](50) NOT NULL,
	[ExactDate] [bit] NOT NULL,
	[WeekDay] [tinyint] NOT NULL,
	[MonthNumber] [tinyint] NOT NULL,
	[DayOfMonth] [tinyint] NOT NULL,
	[WeekOfMonth] [tinyint] NOT NULL,
	[HolidayID] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The Holiday name' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Holidays', @level2type=N'COLUMN',@level2name=N'Holiday'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this holiday is on an exact day of the month and is not always observed on a Monday' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Holidays', @level2type=N'COLUMN',@level2name=N'ExactDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'1 through 7 representing Sunday through Saturday' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Holidays', @level2type=N'COLUMN',@level2name=N'WeekDay'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Month of the holiday as a number 1-12' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Holidays', @level2type=N'COLUMN',@level2name=N'MonthNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'If the ExactDate is true, then this is a value between 1 and 31 representing the day of the month. If false it is ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Holidays', @level2type=N'COLUMN',@level2name=N'DayOfMonth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The day of the month' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Holidays', @level2type=N'COLUMN',@level2name=N'WeekOfMonth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Identifier key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Holidays', @level2type=N'COLUMN',@level2name=N'HolidayID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Do not run the batch process on these days.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Holidays'
GO;;
/****** Object:  Table [dbo].[PaymentTransaction]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[PaymentTransaction](
	[PayTransactionKey] [int] IDENTITY(1,1) NOT NULL,
	[PayTransBatchNumber] [varchar](3) NULL,
	[PayTransBlockNumber] [varchar](2) NULL,
	[PayTransSequenceNumber] [varchar](2) NULL,
	[SCMClaimnumber] [varchar](7) NULL,
	[SCMDateOfBirth] [datetime] NULL,
	[PayTransPaymentAmount] [money] NULL,
	[PayTransTransactionDate] [datetime] NULL,
	[PayTransStatusCode] [tinyint] NULL,
	[PayTransStatusDate] [datetime] NULL,
	[TechnicianUserKey] [int] NOT NULL,
	[PaymentAppliedOrderCode] [int] NOT NULL,
	[PostFlag] [bit] NOT NULL,
	[UserInserted] [bit] NOT NULL,
	[ACHPayment] [bit] NOT NULL,
	[ResolvedSuspense] [bit] NOT NULL,
	[HistoryPayment] [bit] NOT NULL,
	[Disapprove] [bit] NOT NULL,
	[GovRefund] [bit] NOT NULL,
	[Apply2GL] [bit] NULL,
 CONSTRAINT [PK_PaymentTransaction] PRIMARY KEY CLUSTERED 
(
	[PayTransactionKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The primary key to the Payment Transaction table - identity' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBatchNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBatchNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBatchNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Batch number as retrieved from host-to-host transfer input file' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBatchNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBatchNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBatchNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBatchNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBlockNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBlockNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBlockNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Block number as retrieved from host-to-host transfer input file' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBlockNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBlockNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBlockNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransBlockNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransSequenceNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransSequenceNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransSequenceNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Sequence number as retrieved from host-to-host transfer input file' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransSequenceNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransSequenceNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransSequenceNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransSequenceNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMClaimnumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMClaimnumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMClaimnumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'CSD Claim number - foreign key in the ServiceCreditMaster table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMClaimnumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMClaimnumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMClaimnumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMClaimnumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Date Of Birth as retrieved from host-to-host transfer input file' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransPaymentAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransPaymentAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransPaymentAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The dollar amount of the payment retrieved from host-to-host transfer input file' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransPaymentAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransPaymentAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransPaymentAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransPaymentAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransTransactionDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransTransactionDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransTransactionDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The payment transaction date as retrieved from host-to-host transfer input file' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransTransactionDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransTransactionDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransTransactionDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransTransactionDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Indicates status of transaction 0 - Pending, 1 - Accepted payment, 2 - questionable' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date and time that the PayTransStatusCode was last changed' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PayTransStatusDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'TechnicianUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'TechnicianUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=3360 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'TechnicianUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The UserKey (foreign key in the Users table) of the technician who last updated the payment transaction' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'TechnicianUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'TechnicianUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'TechnicianUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'TechnicianUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'this payment applies to this Type of Payment Account.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PaymentAppliedOrderCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'For check box in datagrid' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'PostFlag'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this payment was manually entered by a user with the Service Credit application' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'UserInserted'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this was an ACH payment through the lockbox bank.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'ACHPayment'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if the account was set to history, even if the account is set back to active.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'HistoryPayment'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Set by the approval form if the supervisor want to disapprove the change. Delete manual payment or "unreverse" a reversed payment. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'Disapprove'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this is a Government refund check to claimant' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'GovRefund'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this payment should enter the general ledger during the evening batch process.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction', @level2type=N'COLUMN',@level2name=N'Apply2GL'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The payment transaction table stores all information related to service credit payments.  Payment transactions may or may not be related to a service credit master account by the SCMClaimnumber field.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=10000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransaction'
GO;;
/****** Object:  Table [dbo].[LookupServiceTypeCode]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupServiceTypeCode](
	[SCMServiceTypeCode] [varchar](2) NOT NULL,
	[SCMServiceTypeDescription] [varchar](50) NULL,
	[ServiceTypeDisplayOrder] [tinyint] NULL,
	[FERSDepositAllowedAfter88] [int] NULL,
 CONSTRAINT [PK_LookupServiceTypeCode] PRIMARY KEY CLUSTERED 
(
	[SCMServiceTypeCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[LookupInterestSuppression]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupInterestSuppression](
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[Post982Redeposit] [bit] NOT NULL,
	[Pre1082Redeposit] [bit] NOT NULL,
	[Post982Deposit] [bit] NOT NULL,
	[Pre1082Deposit] [bit] NOT NULL,
	[FersDeposit] [bit] NOT NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[ServiceCreditMaster]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[ServiceCreditMaster](
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[SCMSSN] [char](9) NULL,
	[SCMDateOfBirth] [datetime] NULL,
	[SCMFName] [varchar](40) NULL,
	[SCMMInitial] [char](1) NULL,
	[SCMLastname] [varchar](40) NULL,
	[SCMSuffix] [varchar](3) NULL,
	[SCMName] [varchar](40) NULL,
	[SCMAddress1] [varchar](40) NULL,
	[SCMAddress2] [varchar](40) NULL,
	[SCMAddress3] [varchar](40) NULL,
	[SCMAddress4] [varchar](40) NULL,
	[SCMAddress5] [varchar](40) NULL,
	[SCMCity] [varchar](20) NULL,
	[SCMState] [char](2) NULL,
	[SCMZipcode] [varchar](10) NULL,
	[SCMTelephone] [char](10) NULL,
	[SCMPosition] [varchar](20) NULL,
	[SCMAgencyCode] [char](2) NULL,
	[SCMJobLocationCity] [varchar](20) NULL,
	[SCMJobLocationState] [char](2) NULL,
	[SCMDeposit] [money] NULL,
	[SCMRedeposit] [money] NULL,
	[SCMTotVarRedeposit] [money] NULL,
	[SCMNonDed] [money] NULL,
	[SCMFersW] [money] NULL,
	[SCMAccIntDep] [money] NULL,
	[SCMAccIntRdep] [money] NULL,
	[SCMAccIntNonDed] [money] NULL,
	[SCMAccIntVarRdep] [money] NULL,
	[SCMAccIntFers] [money] NULL,
	[SCMTotPayd] [money] NULL,
	[SCMTotPayr] [money] NULL,
	[SCMTotPayn] [money] NULL,
	[SCMTotPayvr] [money] NULL,
	[SCMTotPayfers] [money] NULL,
	[SCMCompDate] [datetime] NULL,
	[SCMVarIntCompDate] [datetime] NULL,
	[SCMLastAct] [datetime] NULL,
	[SCMLastPay] [datetime] NULL,
	[SCMPayCode] [char](1) NULL,
	[SCMRdepCode] [tinyint] NULL,
	[SCMTimePer] [tinyint] NULL,
	[SCMAddServ] [char](1) NULL,
	[SCMNoInterest] [tinyint] NULL,
	[SCMCode20Date] [datetime] NULL,
	[SCMFlagPreRedep] [char](1) NULL,
	[SCMFlagPostRedep] [char](1) NULL,
	[SCMPriorClaimNumber] [varchar](7) NULL,
	[RetirementTypeCode] [tinyint] NULL,
	[AccountStatus] [tinyint] NULL,
	[PaymentOrder] [varchar](10) NULL,
	[NewClaimNumber] [int] NULL,
	[StopACHPayment] [bit] NOT NULL,
	[DBTSAccount] [bit] NULL,
 CONSTRAINT [PK_ServiceCreditMaster] PRIMARY KEY CLUSTERED 
(
	[SCMClaimNumber] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'CSD Claim Number' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMSSN'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMSSN'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMSSN'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Social Security Number' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMSSN'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMSSN'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMSSN'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMSSN'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Date of Birth' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee first name' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee middle initial' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMMInitial'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastname'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastname'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastname'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Last Name' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastname'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastname'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastname'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastname'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee name suffix' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMSuffix'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Full Employee Name - M-NAME' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress1'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress1'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress1'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Address Line 1' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress1'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress1'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress1'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress1'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress2'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress2'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress2'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Address Line 2' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress2'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress2'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress2'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress2'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress3'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress3'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress3'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Address Line 3' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress3'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress3'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress3'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress3'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress4'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress4'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress4'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Address Line 4' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress4'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress4'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress4'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress4'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress5'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress5'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress5'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Address Line 5' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress5'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress5'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress5'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddress5'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee address City' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee address state' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMState'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee address zipcode' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMZipcode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee telephone number' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTelephone'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee title at last/current position' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPosition'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ageny Code - foreign key to LookupAgencyCode table - the agency submitting the application' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'city of last/current employing agency' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMJobLocationCity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'state of last/current employing agency' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMJobLocationState'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pre 10/82 Deposit - Pre 10/82 Deposit Total Payment amount (Field 19)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMDeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pre 10/82 Redeposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotVarRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotVarRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotVarRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Post 10/82 Redeposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotVarRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotVarRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotVarRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotVarRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Post 10/82 Deposit Post 10/82 Deposit Total Payment Amount (Field 21)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFersW'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFersW'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFersW'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'FERS deposit (treated as Post82 deposit for most purposes)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFersW'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFersW'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFersW'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFersW'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntDep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntDep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntDep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Accumulated Interest on Pre 10/82 deposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntDep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntDep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntDep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntDep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Accumulated Interest on Pre 10/82 Redeposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Accumulated Interest on post 10/82 deposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntVarRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntVarRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntVarRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Accumulated Interest on post 10/82 redeposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntVarRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntVarRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntVarRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntVarRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntFers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntFers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntFers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Accoumulated interest on FERS deposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntFers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntFers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntFers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAccIntFers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayd'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayd'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayd'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total Pre 10/82 Deposit payments' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayd'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayd'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayd'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayd'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total Pre 10/82 Redposit Payments' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayn'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayn'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayn'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total Post 10/82 Deposit payments' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayn'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayn'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayn'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayn'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayvr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayvr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayvr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total Post 10/82 Redeposit payments' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayvr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayvr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayvr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayvr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayfers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayfers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayfers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total of FERS payments' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayfers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayfers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayfers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTotPayfers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Initial Billing date' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMVarIntCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMVarIntCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMVarIntCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Date through which variable interest computations have been made' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMVarIntCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMVarIntCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMVarIntCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMVarIntCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastAct'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastAct'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastAct'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Date of most recent account activity' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastAct'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastAct'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastAct'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastAct'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastPay'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastPay'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastPay'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Date of most recent payment' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastPay'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastPay'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastPay'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMLastPay'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Payment Code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRdepCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRdepCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRdepCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Redeposit only code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRdepCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRdepCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRdepCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMRdepCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTimePer'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTimePer'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTimePer'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of periods billed for service credit amounts' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTimePer'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTimePer'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTimePer'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMTimePer'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddServ'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddServ'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddServ'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Additional periods of service code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddServ'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddServ'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddServ'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMAddServ'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNoInterest'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNoInterest'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=1584 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNoInterest'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'No additional interest switch' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNoInterest'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNoInterest'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNoInterest'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMNoInterest'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCode20Date'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCode20Date'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCode20Date'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Date of service credit computation' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCode20Date'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCode20Date'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCode20Date'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMCode20Date'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPreRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPreRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=1968 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPreRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pre 10/82 Redeposit indicator' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPreRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPreRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPreRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPreRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPostRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPostRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPostRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Post 10/82 Redeposit indicator' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPostRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPostRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPostRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMFlagPostRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPriorClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPriorClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2640 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPriorClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The claim number of a previous claim - usually null' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPriorClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPriorClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPriorClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'SCMPriorClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'RetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'RetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'RetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The code representing the employee''s current retirement system - foreign key to the LookupRetirementTypeCode table (CSRS, FERS, or unknown)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'RetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'RetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'RetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'RetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Overall Account status 1=Active, 0=History' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A digit for each character position indicating the payment order of Post82Redeposit, Pre82Redeposit, Post82Deposit, and Pre82Deposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'PaymentOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'PaymentOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'PaymentOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'PaymentOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The new claim number assigned as accounts are created' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'NewClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'NewClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'NewClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'NewClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this account belongs to DBTS.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster', @level2type=N'COLUMN',@level2name=N'DBTSAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'ServiceCreditMaster is the main table for storage of service credit account information.  Each record corresponds to one and only on employee in the Service Credit System' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=10000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMaster'
GO;;
/****** Object:  Table [dbo].[LookupDeductionRates]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupDeductionRates](
	[SCMServiceTypeCode] [varchar](2) NOT NULL,
	[SCMRetirementTypeCode] [tinyint] NOT NULL,
	[StartDate] [datetime] NOT NULL,
	[EndDate] [datetime] NULL,
	[DaysInPeriod] [int] NULL,
	[Rate] [decimal](18, 9) NULL,
	[ServiceTypeDescription] [varchar](50) NULL,
	[DeductionConversionFactor] [decimal](18, 9) NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[StoredProcedureReturnCodes]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[StoredProcedureReturnCodes](
	[IDReturnCode] [int] IDENTITY(1,1) NOT NULL,
	[StoredProcedure] [varchar](50) NOT NULL,
	[ReturnCode] [int] NOT NULL,
	[ErrorNumber] [int] NULL,
	[Description] [varchar](100) NOT NULL,
 CONSTRAINT [PK_StoredProcedureReturnCodes] PRIMARY KEY CLUSTERED 
(
	[IDReturnCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Unique Key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'StoredProcedureReturnCodes', @level2type=N'COLUMN',@level2name=N'IDReturnCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Name of the procedure in the database' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'StoredProcedureReturnCodes', @level2type=N'COLUMN',@level2name=N'StoredProcedure'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number returned from the stored procedure' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'StoredProcedureReturnCodes', @level2type=N'COLUMN',@level2name=N'ReturnCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'If this return code corresponds to a common error, the number ID of that error type.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'StoredProcedureReturnCodes', @level2type=N'COLUMN',@level2name=N'ErrorNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Programmer''s description of what this return code means. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'StoredProcedureReturnCodes', @level2type=N'COLUMN',@level2name=N'Description'
GO;;
/****** Object:  Table [dbo].[LookupInterestSuppressionAudit]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupInterestSuppressionAudit](
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[Post982Redeposit] [bit] NOT NULL,
	[Pre1082Redeposit] [bit] NOT NULL,
	[Post982Deposit] [bit] NOT NULL,
	[Pre1082Deposit] [bit] NOT NULL,
	[FersDeposit] [bit] NOT NULL,
	[AuditUserID] [varchar](128) NULL,
	[AuditUpdateTime] [datetime] NULL,
	[AuditActivity] [tinyint] NULL,
	[AuditKey] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_LookupInterestSuppressionAudit] PRIMARY KEY CLUSTERED 
(
	[AuditKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[LookupUserRoleCode]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupUserRoleCode](
	[UserRoleCode] [int] NOT NULL,
	[UserRoleDescription] [varchar](200) NULL,
	[UserRoleName] [varchar](50) NULL,
	[AddAccount] [bit] NULL,
	[AddNotes] [bit] NULL,
	[AddOverTheCounterPayments] [bit] NULL,
	[AddPaymentTransactions] [bit] NULL,
	[AddServiceHistory] [bit] NULL,
	[AdjustAccount] [bit] NULL,
	[AdjustInterest] [bit] NULL,
	[ApproveBillingTransactions] [bit] NULL,
	[ApprovePaymentTransactions] [bit] NULL,
	[AssignUserRoles] [bit] NULL,
	[CalculateServiceHistory] [bit] NULL,
	[ChangeAccount] [bit] NULL,
	[ChangeAccountStatus] [bit] NULL,
	[DeleteNotes] [bit] NULL,
	[DeleteServiceHistory] [bit] NULL,
	[EditNotes] [bit] NULL,
	[EditServiceHistory] [bit] NULL,
	[GenerateStatement] [bit] NULL,
	[MovePayments] [bit] NULL,
	[PostPaymentTransaction] [bit] NULL,
	[PostPriorPayments] [bit] NULL,
	[RecalculateInitialBilling] [bit] NULL,
	[RerunGL] [bit] NULL,
	[ReversePaymentTransaction] [bit] NULL,
	[SearchAccount] [bit] NULL,
	[UnpostPaymentTransaction] [bit] NULL,
	[UpdateInterest] [bit] NULL,
	[ViewAccount] [bit] NULL,
	[ViewAuditTrail] [bit] NULL,
	[ViewPaymentTransaction] [bit] NULL,
 CONSTRAINT [PK_LookupUserRoleCode] PRIMARY KEY CLUSTERED 
(
	[UserRoleCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'An integer key that identifies the user role' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UserRoleCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UserRoleCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UserRoleCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UserRoleCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A description of the user role' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UserRoleDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UserRoleDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UserRoleDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UserRoleDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The name of the user role (group) for selection in user interface' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UserRoleName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UserRoleName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UserRoleName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UserRoleName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'a user in the role may create new accounts' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'a user in the role may add account notes' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddNotes'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddNotes'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddNotes'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddNotes'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Add new over-the-counter payments
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddOverTheCounterPayments'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Add payment transactions for adjustments, refunds, write-offs, etc.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddPaymentTransactions'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddPaymentTransactions'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddPaymentTransactions'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddPaymentTransactions'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This role may make a new calc version' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AddServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This role may edit the Billing Summary non-monetary fields' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AdjustAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AdjustAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AdjustAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AdjustAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Edit Billing Summary Additional Interest
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AdjustInterest'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Approve Billing Summary Additional Interest and Move Payments
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ApproveBillingTransactions'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Approve over-the-counter payments or suspense resolution
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ApprovePaymentTransactions'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ApprovePaymentTransactions'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ApprovePaymentTransactions'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ApprovePaymentTransactions'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'a manager in this role may assign roles to users' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AssignUserRoles'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AssignUserRoles'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AssignUserRoles'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'AssignUserRoles'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'a user in this role may calculate service credit amounts' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'CalculateServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'CalculateServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'CalculateServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'CalculateServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This role may edit Basic Info except Account Status' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ChangeAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ChangeAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ChangeAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ChangeAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Edit Basic Info Account Status
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ChangeAccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'a user in the role may delete account notes of other users' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'DeleteNotes'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'DeleteNotes'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'DeleteNotes'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'DeleteNotes'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'a user in this role may delete service history versions' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'DeleteServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'DeleteServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'DeleteServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'DeleteServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'a user in the role may edit account notes of other users' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'EditNotes'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'EditNotes'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'EditNotes'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'EditNotes'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'a user in this role may edit service history records' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'EditServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'EditServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'EditServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'EditServiceHistory'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This permission allows the user to generate a new statement based on current account information' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'GenerateStatement'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Edit Billing Summary Move Payments
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'MovePayments'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This role may post (resolve) suspense payments' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'PostPaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'PostPaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'PostPaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'PostPaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A user in this role may post prior payment recorded transactions' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'PostPriorPayments'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'PostPriorPayments'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'PostPriorPayments'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'PostPriorPayments'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Can cancel a billing and make a new "initial" bill
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'RecalculateInitialBilling'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Allowed to rerun the GL from last night' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'RerunGL'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'RerunGL'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'RerunGL'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'RerunGL'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'a user in this role may reverse payment transactions (a status change)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ReversePaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ReversePaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ReversePaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ReversePaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'a user in this role may search for accounts' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'SearchAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'SearchAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'SearchAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'SearchAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Manager who may change payment from any pending state back to its earliest known state' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UnpostPaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Can use the update interest to current date button' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'UpdateInterest'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This role may use the program' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ViewAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ViewAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ViewAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ViewAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'a system administrator in this role may view adut trail records - 0 = no authorization, 1 = may view audit trail records' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ViewAuditTrail'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ViewAuditTrail'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ViewAuditTrail'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ViewAuditTrail'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'a user in this role may view suspense payments' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ViewPaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'106' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ViewPaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ViewPaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode', @level2type=N'COLUMN',@level2name=N'ViewPaymentTransaction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A lookup table that associates user role codes with their descriptions and the activities that are permitted for users assigned to that role' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookupUserRoleCode'
GO;;
/****** Object:  Table [dbo].[USStates]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[USStates](
	[StateID] [smallint] NOT NULL,
	[State] [varchar](20) NOT NULL,
	[Abbreviation] [varchar](2) NOT NULL,
 CONSTRAINT [PK_USStates] PRIMARY KEY CLUSTERED 
(
	[StateID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The name of the state' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'USStates', @level2type=N'COLUMN',@level2name=N'State'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The state abbreviation' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'USStates', @level2type=N'COLUMN',@level2name=N'Abbreviation'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The list of valid US state abbreviations.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'USStates'
GO;;
/****** Object:  Table [dbo].[UserAccountAssignments]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[UserAccountAssignments](
	[UserKey] [int] NOT NULL,
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[AssignmentDate] [datetime] NOT NULL,
 CONSTRAINT [PK_UserAccountAssignments] PRIMARY KEY CLUSTERED 
(
	[UserKey] ASC,
	[SCMClaimNumber] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A foreign key to the Users table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A foreign keyto the ServiceCreditMaster table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date and time the case was assigned to the user' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments', @level2type=N'COLUMN',@level2name=N'AssignmentDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments', @level2type=N'COLUMN',@level2name=N'AssignmentDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments', @level2type=N'COLUMN',@level2name=N'AssignmentDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments', @level2type=N'COLUMN',@level2name=N'AssignmentDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This table links the users with their assigned accounts.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserAccountAssignments'
GO;;
/****** Object:  Table [dbo].[LookupInterestGracePeriod]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupInterestGracePeriod](
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[Post982Redeposit] [bit] NOT NULL,
	[Pre1082Redeposit] [bit] NOT NULL,
	[Post982Deposit] [bit] NOT NULL,
	[Pre1082Deposit] [bit] NOT NULL,
	[FersDeposit] [bit] NOT NULL,
 CONSTRAINT [PK_LookupInterestGracePeriod] PRIMARY KEY CLUSTERED 
(
	[SCMClaimNumber] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[UsersAudit]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[UsersAudit](
	[UserKey] [int] NULL,
	[UserNetworkID] [varchar](128) NULL,
	[UserID] [varchar](20) NULL,
	[UserPassword] [varchar](20) NULL,
	[UserStatusCode] [int] NULL,
	[UserFirstName] [varchar](40) NULL,
	[UserLastName] [varchar](40) NULL,
	[UserPhoneNumber] [varchar](18) NULL,
	[UserEmail] [varchar](50) NULL,
	[UserSupervisorKey] [int] NULL,
	[AuditUserID] [varchar](128) NULL,
	[AuditUpdateTime] [datetime] NULL,
	[AuditActivity] [tinyint] NULL,
	[AuditKey] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_UsersAudit] PRIMARY KEY CLUSTERED 
(
	[AuditKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The network logon ID for the user' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UsersAudit', @level2type=N'COLUMN',@level2name=N'UserNetworkID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UsersAudit', @level2type=N'COLUMN',@level2name=N'UserNetworkID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UsersAudit', @level2type=N'COLUMN',@level2name=N'UserNetworkID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UsersAudit', @level2type=N'COLUMN',@level2name=N'UserNetworkID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The UserKey field value for the user''s supervisor' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UsersAudit', @level2type=N'COLUMN',@level2name=N'UserSupervisorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UsersAudit', @level2type=N'COLUMN',@level2name=N'UserSupervisorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UsersAudit', @level2type=N'COLUMN',@level2name=N'UserSupervisorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UsersAudit', @level2type=N'COLUMN',@level2name=N'UserSupervisorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The userid of the person making a change to the Users table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UsersAudit', @level2type=N'COLUMN',@level2name=N'AuditUserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date and time the change was submitted' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UsersAudit', @level2type=N'COLUMN',@level2name=N'AuditUpdateTime'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The numeric code assigned to the record indicating what type of activity generated the audit transaction' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UsersAudit', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A unique integer used as the primary key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UsersAudit', @level2type=N'COLUMN',@level2name=N'AuditKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Stores audit trail of activity in the Users table.  One record is stored for each insert, update, or delete action. For insert/update activities, the audit record stores the new values.  For Deletions, the deleted values are stored.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UsersAudit'
GO;;
/****** Object:  Table [dbo].[ExportDates]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
CREATE TABLE [dbo].[ExportDates](
	[IDDate] [int] IDENTITY(1,1) NOT NULL,
	[LastExportDate] [datetime] NOT NULL
) ON [PRIMARY]
GO;;
/****** Object:  Table [dbo].[AuditBatch]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
CREATE TABLE [dbo].[AuditBatch](
	[IDLog] [int] IDENTITY(1,1) NOT NULL,
	[EventYear] [smallint] NOT NULL,
	[EventMonth] [tinyint] NOT NULL,
	[EventDay] [tinyint] NOT NULL,
	[FileReceived] [bit] NOT NULL,
	[DailyAction] [bit] NOT NULL,
	[ManualBatch] [bit] NOT NULL,
	[ErrorImporting] [bit] NOT NULL,
	[ErrorProcessing] [bit] NOT NULL,
	[LatestBatch] [bit] NOT NULL,
	[AmountImported] [money] NOT NULL,
	[AmountProcessed] [money] NOT NULL,
	[NumberACHAccepted] [int] NOT NULL,
	[NumberACHUnresolved] [int] NOT NULL,
	[NumberACHSuspended] [int] NOT NULL,
	[NumberAccepted] [int] NOT NULL,
	[NumberUnresolved] [int] NOT NULL,
	[NumberSuspended] [int] NOT NULL,
	[NumberChangeRequests] [int] NOT NULL,
	[ErrorCountImporting] [int] NOT NULL,
	[PaymentsProcessed] [int] NOT NULL,
	[InitialBillsProcessed] [int] NOT NULL,
	[ReversedProcessed] [int] NOT NULL,
	[ACHStopLetters] [int] NOT NULL,
	[RefundMemos] [int] NOT NULL,
	[ErrorCountProcessing] [int] NOT NULL,
	[UserKey] [int] NOT NULL,
	[BatchTime] [datetime] NOT NULL,
 CONSTRAINT [PK_AuditBatch] PRIMARY KEY CLUSTERED 
(
	[IDLog] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Identity field' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'IDLog'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Year of this importation and processing' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'EventYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Month of this importation and processing' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'EventMonth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Day of this importation and processing' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'EventDay'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if at least one lockbox bank input file was imported' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'FileReceived'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if the once-a-day pending processing was done' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'DailyAction'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this batch record was inserted by the Service Credit application by a supervisor who approved and printed the invoices at the same time.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'ManualBatch'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if an error occurred during file importaion' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'ErrorImporting'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if an error occurred during payment processing' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'ErrorProcessing'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this is the last batch processed overnight' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'LatestBatch'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Dollar total of amount imported on this day from one or more lockbox files' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'AmountImported'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Dollar total of payments processed ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'AmountProcessed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of ACH payments matched to an active claimant' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'NumberACHAccepted'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of ACH payments matched to a claimant but with a stopping error.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'NumberACHUnresolved'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of ACH payments that could not be matched to a claimant' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'NumberACHSuspended'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of checks matched to an active claimant' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'NumberAccepted'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of checks matched to a claimant but with a stopping error.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'NumberUnresolved'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of checks that could not be matched to a claimant' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'NumberSuspended'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of Change Requests received' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'NumberChangeRequests'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of errors that occurred while importing files' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'ErrorCountImporting'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'# payments processed or received' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'PaymentsProcessed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of initial bills generated' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'InitialBillsProcessed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of payments that were reversed' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'ReversedProcessed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number Letters sent telling claimants to stop ACH payments' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'ACHStopLetters'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of payments that overpaid & require a refund' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'RefundMemos'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of payments that could not be processed' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'ErrorCountProcessing'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pointer to Users table for the person or service that processed these payments.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Time and date this record was created or updated' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch', @level2type=N'COLUMN',@level2name=N'BatchTime'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Log of the daily batch service. Use this for reporting and ensuring that the pending bills are processed only once daily.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AuditBatch'
GO;;
/****** Object:  Table [dbo].[SCMFirstInsert]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[SCMFirstInsert](
	[SCMClaimNumber] [varchar](7) NULL,
	[scmlastact] [datetime] NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[StoredProcPermissions]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[StoredProcPermissions](
	[IDStoredProcPermissions] [int] IDENTITY(1,1) NOT NULL,
	[StoredProcName] [varchar](100) NOT NULL,
	[ServiceCredit] [bit] NOT NULL,
	[SC_Batch] [bit] NOT NULL,
	[SC_Close] [bit] NOT NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[NewClaimNumbers]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[NewClaimNumbers](
	[SCMClaimNumber] [varchar](7) NOT NULL,
 CONSTRAINT [PK_NewClaimNumbers] PRIMARY KEY CLUSTERED 
(
	[SCMClaimNumber] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[NewClaims]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[NewClaims](
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[SCMSSN] [char](9) NULL,
	[SCMDateOfBirth] [datetime] NULL,
	[SCMFName] [varchar](40) NULL,
	[SCMMInitial] [char](1) NULL,
	[SCMLastname] [varchar](40) NULL,
	[SCMSuffix] [varchar](3) NULL,
	[SCMName] [varchar](40) NULL,
	[SCMAddress1] [varchar](40) NULL,
	[SCMAddress2] [varchar](40) NULL,
	[SCMAddress3] [varchar](40) NULL,
	[SCMAddress4] [varchar](40) NULL,
	[SCMAddress5] [varchar](40) NULL,
	[SCMCity] [varchar](20) NULL,
	[SCMState] [char](2) NULL,
	[SCMZipcode] [varchar](10) NULL,
	[SCMTelephone] [char](10) NULL,
	[SCMPosition] [varchar](20) NULL,
	[SCMAgencyCode] [char](2) NULL,
	[SCMJobLocationCity] [varchar](20) NULL,
	[SCMJobLocationState] [char](2) NULL,
	[SCMDeposit] [money] NULL,
	[SCMRedeposit] [money] NULL,
	[SCMTotVarRedeposit] [money] NULL,
	[SCMNonDed] [money] NULL,
	[SCMFersW] [money] NULL,
	[SCMAccIntDep] [money] NULL,
	[SCMAccIntRdep] [money] NULL,
	[SCMAccIntNonDed] [money] NULL,
	[SCMAccIntVarRdep] [money] NULL,
	[SCMAccIntFers] [money] NULL,
	[SCMTotPayd] [money] NULL,
	[SCMTotPayr] [money] NULL,
	[SCMTotPayn] [money] NULL,
	[SCMTotPayvr] [money] NULL,
	[SCMTotPayfers] [money] NULL,
	[SCMCompDate] [datetime] NULL,
	[SCMVarIntCompDate] [datetime] NULL,
	[SCMLastAct] [datetime] NULL,
	[SCMLastPay] [datetime] NULL,
	[SCMPayCode] [char](1) NULL,
	[SCMRdepCode] [tinyint] NULL,
	[SCMTimePer] [tinyint] NULL,
	[SCMAddServ] [char](1) NULL,
	[SCMNoInterest] [tinyint] NULL,
	[SCMCode20Date] [datetime] NULL,
	[SCMFlagPreRedep] [char](1) NULL,
	[SCMFlagPostRedep] [char](1) NULL,
	[SCMPriorClaimNumber] [varchar](7) NULL,
	[RetirementTypeCode] [tinyint] NULL,
	[AccountStatus] [tinyint] NULL,
	[PaymentOrder] [varchar](10) NULL,
	[NewClaimNumber] [int] NULL,
	[StopACHPayment] [bit] NOT NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[LookUpGLPaymentType]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookUpGLPaymentType](
	[LookUpGLPaymentTypeKey] [int] IDENTITY(1,1) NOT NULL,
	[PaymentCode] [char](2) NOT NULL,
	[CodeDescription] [char](50) NOT NULL,
 CONSTRAINT [PK_LookUpGLPaymentType] PRIMARY KEY CLUSTERED 
(
	[LookUpGLPaymentTypeKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Index Key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpGLPaymentType', @level2type=N'COLUMN',@level2name=N'LookUpGLPaymentTypeKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The two-character code for this type of payment' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpGLPaymentType', @level2type=N'COLUMN',@level2name=N'PaymentCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Description of the code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpGLPaymentType', @level2type=N'COLUMN',@level2name=N'CodeDescription'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Describes the "R" codes for the different payments. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'LookUpGLPaymentType'
GO;;
/****** Object:  Table [dbo].[ServiceCreditMasterAudit]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[ServiceCreditMasterAudit](
	[SCMClaimNumber] [varchar](7) NULL,
	[SCMSSN] [char](9) NULL,
	[SCMDateOfBirth] [datetime] NULL,
	[SCMFName] [varchar](40) NULL,
	[SCMMInitial] [char](1) NULL,
	[SCMLastname] [varchar](40) NULL,
	[SCMSuffix] [varchar](3) NULL,
	[SCMName] [varchar](40) NULL,
	[SCMAddress1] [varchar](40) NULL,
	[SCMAddress2] [varchar](40) NULL,
	[SCMAddress3] [varchar](40) NULL,
	[SCMAddress4] [varchar](40) NULL,
	[SCMAddress5] [varchar](40) NULL,
	[SCMCity] [varchar](20) NULL,
	[SCMState] [char](2) NULL,
	[SCMZipcode] [varchar](10) NULL,
	[SCMTelephone] [char](10) NULL,
	[SCMPosition] [varchar](20) NULL,
	[SCMAgencyCode] [char](2) NULL,
	[SCMJobLocationCity] [varchar](20) NULL,
	[SCMJobLocationState] [char](2) NULL,
	[SCMDeposit] [money] NULL,
	[SCMRedeposit] [money] NULL,
	[SCMTotVarRedeposit] [money] NULL,
	[SCMNonDed] [money] NULL,
	[SCMFersW] [money] NULL,
	[SCMAccIntDep] [money] NULL,
	[SCMAccIntRdep] [money] NULL,
	[SCMAccIntNonDed] [money] NULL,
	[SCMAccIntVarRdep] [money] NULL,
	[SCMAccIntFers] [money] NULL,
	[SCMTotPayd] [money] NULL,
	[SCMTotPayr] [money] NULL,
	[SCMTotPayn] [money] NULL,
	[SCMTotPayvr] [money] NULL,
	[SCMTotPayfers] [money] NULL,
	[SCMCompDate] [datetime] NULL,
	[SCMVarIntCompDate] [datetime] NULL,
	[SCMLastAct] [datetime] NULL,
	[SCMLastPay] [datetime] NULL,
	[SCMPayCode] [char](1) NULL,
	[SCMRdepCode] [tinyint] NULL,
	[SCMTimePer] [tinyint] NULL,
	[SCMAddServ] [char](1) NULL,
	[SCMNoInterest] [tinyint] NULL,
	[SCMCode20Date] [datetime] NULL,
	[SCMFlagPreRedep] [char](1) NULL,
	[SCMFlagPostRedep] [char](1) NULL,
	[SCMPriorClaimNumber] [varchar](7) NULL,
	[RetirementTypeCode] [tinyint] NULL,
	[AccountStatus] [tinyint] NULL,
	[PaymentOrder] [varchar](10) NULL,
	[NewClaimNumber] [int] NULL,
	[StopACHPayment] [bit] NOT NULL,
	[DBTSAccount] [bit] NULL,
	[AuditUserID] [varchar](128) NULL,
	[AuditUpdateTime] [datetime] NULL,
	[AuditActivity] [tinyint] NULL,
	[AuditKey] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_ServiceCreditMasterAudit] PRIMARY KEY CLUSTERED 
(
	[AuditKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'CSD Claim Number' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Social Security Number' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMSSN'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMSSN'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMSSN'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Date of Birth' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Last Name' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMLastname'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMLastname'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMLastname'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Full Employee Name - M-NAME' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Address Line 1' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress1'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress1'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress1'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Address Line 2' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress2'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress2'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress2'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Address Line 3' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress3'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress3'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress3'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Address Line 4' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress4'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress4'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress4'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Employee Address Line 5' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress5'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress5'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddress5'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ageny Code - foreign key to LookupAgencyCode table - the agency submitting the application' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pre 10/82 Deposit - Pre 10/82 Deposit Total Payment amount (Field 19)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMDeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMDeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMDeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pre 10/82 Redeposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Post 10/82 Redeposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotVarRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotVarRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotVarRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Post 10/82 Deposit Post 10/82 Deposit Total Payment Amount (Field 21)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMFersW'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMFersW'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMFersW'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Accumulated Interest on Pre 10/82 deposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntDep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntDep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntDep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Accumulated Interest on Pre 10/82 Redeposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Accumulated Interest on post 10/82 deposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Accumulated Interest on post 10/82 redeposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntVarRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntVarRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntVarRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntFers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAccIntFers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total Pre 10/82 Deposit payments' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayd'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayd'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayd'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total Pre 10/82 Redposit Payments' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total Post 10/82 Deposit payments' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayn'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayn'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayn'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total Post 10/82 Redeposit payments' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayvr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayvr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayvr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayfers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTotPayfers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Initial Billing date' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Date through which variable interest computations have been made' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMVarIntCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMVarIntCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMVarIntCompDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Date of most recent account activity' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMLastAct'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMLastAct'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMLastAct'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Date of most recent payment' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMLastPay'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMLastPay'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMLastPay'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Payment Code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMPayCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Redeposit only code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMRdepCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMRdepCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMRdepCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of periods billed for service credit amounts' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTimePer'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTimePer'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMTimePer'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Additional periods of service code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddServ'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddServ'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMAddServ'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'No additional interest switch' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMNoInterest'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMNoInterest'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMNoInterest'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Date of service credit computation' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMCode20Date'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMCode20Date'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMCode20Date'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pre 10/82 Redeposit indicator' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMFlagPreRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMFlagPreRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMFlagPreRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Post 10/82 Redeposit indicator' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMFlagPostRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMFlagPostRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMFlagPostRedep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The claim number of a previous claim - usually null' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMPriorClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMPriorClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'SCMPriorClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The code representing the employee''s current retirement system - foreign key to the LookupRetirementTypeCode table (CSRS, FERS, or unknown)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'RetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'RetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'RetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Overall Account status 1=Active, 0=History' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A digit for each character position indicating the payment order of Post82Redeposit, Pre82Redeposit, Post82Deposit, and Pre82Deposit' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'PaymentOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'PaymentOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'PaymentOrder'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The new claim number assigned as accounts are created' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'NewClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'NewClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'NewClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this account is used with DBTS instead of SCRD' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'DBTSAccount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The userid of the person making a change to the ServiceCreditMaster table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'AuditUserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date and time the change was submitted' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'AuditUpdateTime'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The numeric code assigned to the record indicating what type of activity generated the audit transaction' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A unique integer used as the primary key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit', @level2type=N'COLUMN',@level2name=N'AuditKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Stores audit trail of activity in the ServiceCreditMaster table.  One record is stored for each insert, update, or delete action. For insert/update activities, the audit record stores the new values.  For Deletions, the deleted values are stored.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServiceCreditMasterAudit'
GO;;
/****** Object:  Table [dbo].[TimeFactor]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
CREATE TABLE [dbo].[TimeFactor](
	[NumDays] [smallint] NOT NULL,
	[NumMonths] [smallint] NOT NULL,
	[TimeFactor] [decimal](18, 6) NOT NULL
) ON [PRIMARY]
GO;;
/****** Object:  Table [dbo].[NameSuffix]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[NameSuffix](
	[SuffixID] [smallint] NOT NULL,
	[Suffix] [varchar](3) NOT NULL,
 CONSTRAINT [PK_NameSuffix] PRIMARY KEY CLUSTERED 
(
	[SuffixID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The suffix of the name' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NameSuffix', @level2type=N'COLUMN',@level2name=N'Suffix'
GO;;
/****** Object:  Table [dbo].[CalculatedServicePeriodsAudit]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[CalculatedServicePeriodsAudit](
	[CalculatedServicePeriodsKey] [int] NOT NULL,
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[SCMEnteredBy] [int] NULL,
	[SCMPeriodTypeCode] [char](1) NULL,
	[SCMRetirementTypeCode] [tinyint] NULL,
	[CalculationDate] [datetime] NULL,
	[LastUpdate] [datetime] NULL,
	[AsOfDate] [datetime] NULL,
	[Version] [int] NULL,
	[LineNumber] [int] NULL,
	[IsOfficial] [bit] NULL,
	[ServicePeriodStartDate] [datetime] NULL,
	[ServicePeriodEndDate] [datetime] NULL,
	[ServicePeriodMidpoint] [datetime] NULL,
	[ServicePeriodEffectiveDate] [datetime] NULL,
	[DeductionAmount] [money] NULL,
	[InterestAmount] [money] NULL,
	[PaymentsApplied] [money] NULL,
	[TotalBalance] [money] NULL,
	[CalculationStatus] [tinyint] NULL,
	[PaymentOrder] [int] NULL,
	[InterestAccrualDate] [datetime] NULL,
	[SCMServiceTypeCode] [varchar](2) NULL,
	[AuditUserID] [varchar](128) NULL,
	[AuditUpdateTime] [datetime] NULL,
	[AuditActivity] [tinyint] NULL,
	[AuditKey] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[UserRoleAssignmentAudit]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[UserRoleAssignmentAudit](
	[UserKey] [int] NULL,
	[UserRoleCode] [int] NULL,
	[AuditUserID] [varchar](128) NULL,
	[AuditUpdateTime] [datetime] NULL,
	[AuditActivity] [tinyint] NULL,
	[AuditKey] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_UserRoleAssignmentAudit] PRIMARY KEY CLUSTERED 
(
	[AuditKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pointer to the Users table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignmentAudit', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pointer to the LookUpUserRoleCode table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignmentAudit', @level2type=N'COLUMN',@level2name=N'UserRoleCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The userid of the person making a change to the UserRoleAssignment table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignmentAudit', @level2type=N'COLUMN',@level2name=N'AuditUserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date and time the change was submitted' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignmentAudit', @level2type=N'COLUMN',@level2name=N'AuditUpdateTime'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The numeric code assigned to the record indicating what type of activity generated the audit transaction' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignmentAudit', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A unique integer used as the primary key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignmentAudit', @level2type=N'COLUMN',@level2name=N'AuditKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Stores audit trail of activity in the UserRoleAssignment table.  One record is stored for each insert, update, or delete action. For insert/update activities, the audit record stores the new values.  For Deletions, the deleted values are stored.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignmentAudit'
GO;;
/****** Object:  Table [dbo].[LookupInterestGracePeriodAudit]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[LookupInterestGracePeriodAudit](
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[Post982Redeposit] [bit] NOT NULL,
	[Pre1082Redeposit] [bit] NOT NULL,
	[Post982Deposit] [bit] NOT NULL,
	[Pre1082Deposit] [bit] NOT NULL,
	[FersDeposit] [bit] NOT NULL,
	[AuditUserID] [varchar](128) NULL,
	[AuditUpdateTime] [datetime] NULL,
	[AuditActivity] [tinyint] NULL,
	[AuditKey] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_LookupInterestGracePeriodAudit] PRIMARY KEY CLUSTERED 
(
	[AuditKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[NewDuplicateAccounts]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[NewDuplicateAccounts](
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[SCMSSN] [char](9) NULL,
	[SCMDateOfBirth] [datetime] NULL,
	[SCMFName] [varchar](40) NULL,
	[SCMMInitial] [char](1) NULL,
	[SCMLastname] [varchar](40) NULL,
	[SCMSuffix] [varchar](3) NULL,
	[SCMName] [varchar](40) NULL,
	[SCMAddress1] [varchar](40) NULL,
	[SCMAddress2] [varchar](40) NULL,
	[SCMAddress3] [varchar](40) NULL,
	[SCMAddress4] [varchar](40) NULL,
	[SCMAddress5] [varchar](40) NULL,
	[SCMCity] [varchar](20) NULL,
	[SCMState] [char](2) NULL,
	[SCMZipcode] [varchar](10) NULL,
	[SCMTelephone] [char](10) NULL,
	[SCMPosition] [varchar](20) NULL,
	[SCMAgencyCode] [char](2) NULL,
	[SCMJobLocationCity] [varchar](20) NULL,
	[SCMJobLocationState] [char](2) NULL,
	[SCMDeposit] [money] NULL,
	[SCMRedeposit] [money] NULL,
	[SCMTotVarRedeposit] [money] NULL,
	[SCMNonDed] [money] NULL,
	[SCMFersW] [money] NULL,
	[SCMAccIntDep] [money] NULL,
	[SCMAccIntRdep] [money] NULL,
	[SCMAccIntNonDed] [money] NULL,
	[SCMAccIntVarRdep] [money] NULL,
	[SCMAccIntFers] [money] NULL,
	[SCMTotPayd] [money] NULL,
	[SCMTotPayr] [money] NULL,
	[SCMTotPayn] [money] NULL,
	[SCMTotPayvr] [money] NULL,
	[SCMTotPayfers] [money] NULL,
	[SCMCompDate] [datetime] NULL,
	[SCMVarIntCompDate] [datetime] NULL,
	[SCMLastAct] [datetime] NULL,
	[SCMLastPay] [datetime] NULL,
	[SCMPayCode] [char](1) NULL,
	[SCMRdepCode] [tinyint] NULL,
	[SCMTimePer] [tinyint] NULL,
	[SCMAddServ] [char](1) NULL,
	[SCMNoInterest] [tinyint] NULL,
	[SCMCode20Date] [datetime] NULL,
	[SCMFlagPreRedep] [char](1) NULL,
	[SCMFlagPostRedep] [char](1) NULL,
	[SCMPriorClaimNumber] [varchar](7) NULL,
	[RetirementTypeCode] [tinyint] NULL,
	[AccountStatus] [tinyint] NULL,
	[PaymentOrder] [varchar](10) NULL,
	[NewClaimNumber] [int] NULL,
	[StopACHPayment] [bit] NOT NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[UserRoleAssignment_Save]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
CREATE TABLE [dbo].[UserRoleAssignment_Save](
	[UserKey] [int] NOT NULL,
	[UserRoleCode] [int] NOT NULL
) ON [PRIMARY]
GO;;
/****** Object:  Table [dbo].[PaymentInterestDetails]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
CREATE TABLE [dbo].[PaymentInterestDetails](
	[PayTransactionKey] [int] NOT NULL,
	[AccountType] [tinyint] NOT NULL,
	[NumWholeYears] [tinyint] NULL,
	[CalculatedInterest] [money] NULL,
	[LastPayToEOYFactor] [numeric](18, 9) NULL,
	[PartialToThisPayFactor] [numeric](18, 9) NULL,
	[ThisInterestRate] [numeric](18, 9) NULL,
	[LastPaymentdate] [datetime] NULL,
	[TransactionDate] [datetime] NULL,
	[ComputedDate] [datetime] NOT NULL,
	[Post] [bit] NULL,
	[GUI] [bit] NULL,
	[LastPaymentWasThisYear] [bit] NULL
) ON [PRIMARY]
GO;;
/****** Object:  Table [dbo].[PaymentTransactionAudit]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[PaymentTransactionAudit](
	[PayTransactionKey] [int] NULL,
	[PayTransBatchNumber] [varchar](3) NULL,
	[PayTransBlockNumber] [varchar](2) NULL,
	[PayTransSequenceNumber] [varchar](2) NULL,
	[SCMClaimnumber] [varchar](7) NULL,
	[SCMDateOfBirth] [datetime] NULL,
	[PayTransPaymentAmount] [money] NULL,
	[PayTransTransactionDate] [datetime] NULL,
	[PayTransStatusCode] [tinyint] NULL,
	[PayTransStatusDate] [datetime] NULL,
	[TechnicianUserKey] [int] NULL,
	[PaymentAppliedOrderCode] [int] NULL,
	[PostFlag] [bit] NOT NULL,
	[UserInserted] [bit] NOT NULL,
	[ACHPayment] [bit] NULL,
	[ResolvedSuspense] [bit] NULL,
	[HistoryPayment] [bit] NULL,
	[Disapprove] [bit] NULL,
	[GovRefund] [bit] NULL,
	[Apply2GL] [bit] NULL,
	[AuditUserID] [varchar](128) NULL,
	[AuditUpdateTime] [datetime] NULL,
	[AuditActivity] [tinyint] NULL,
	[AuditKey] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_PaymentTransactionAudit] PRIMARY KEY CLUSTERED 
(
	[AuditKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The primary key to the Payment Transaction table - identity' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Batch number as retrieved from host-to-host transfer input file' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'PayTransBatchNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Block number as retrieved from host-to-host transfer input file' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'PayTransBlockNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Sequence number as retrieved from host-to-host transfer input file' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'PayTransSequenceNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'CSD Claim number - foreign key in the ServiceCreditMaster table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'SCMClaimnumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Date Of Birth as retrieved from host-to-host transfer input file' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'SCMDateOfBirth'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The dollar amount of the payment retrieved from host-to-host transfer input file' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'PayTransPaymentAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The payment transaction date as retrieved from host-to-host transfer input file' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'PayTransTransactionDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Indicates status of transaction 0 - Pending, 1 - Accepted payment, 2 - questionable' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'PayTransStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date and time that the PayTransStatusCode was last changed' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'PayTransStatusDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The UserKey (foreign key in the Users table) of the technician who last updated the payment transaction' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'TechnicianUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'TechnicianUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'TechnicianUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This payment applies to this Type of Payment Account' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'PaymentAppliedOrderCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'For Check Box in Datagrid' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'PostFlag'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this payment was manually entered by a user with the Service Credit application' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'UserInserted'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this was an ACH payment through the lockbox bank.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'ACHPayment'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this payment was made on a history account, even if the account is set back to active' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'HistoryPayment'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this is a Government refund check to claimant' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'GovRefund'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this payment should enter the general ledger during the evening batch process.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'Apply2GL'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The userid of the person making a change to the PaymentTransaction table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'AuditUserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date and time the change was submitted' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'AuditUpdateTime'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'he numeric code assigned to the record indicating what type of activity generated the audit transaction' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A unique integer used as the primary key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit', @level2type=N'COLUMN',@level2name=N'AuditKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Stores audit trail of activity in the PaymentTransaction table.  One record is stored for each insert, update, or delete action. For insert/update activities, the audit record stores the new values.  For Deletions, the deleted values are stored.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTransactionAudit'
GO;;
/****** Object:  Table [dbo].[PaymentTranactionNotesAudit]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[PaymentTranactionNotesAudit](
	[PayTransactionKey] [int] NULL,
	[Note] [varchar](500) NULL,
	[AuditUserID] [varchar](128) NULL,
	[AuditUpdateTime] [datetime] NULL,
	[AuditActivity] [tinyint] NULL,
	[AuditKey] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_PaymentTranactionNotesAudit] PRIMARY KEY CLUSTERED 
(
	[AuditKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The primary key to the Payment notes table - identity' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTranactionNotesAudit', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The text of the note' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTranactionNotesAudit', @level2type=N'COLUMN',@level2name=N'Note'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The userid of the person making a change to the PaymentNote table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTranactionNotesAudit', @level2type=N'COLUMN',@level2name=N'AuditUserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date and time the change was submitted' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTranactionNotesAudit', @level2type=N'COLUMN',@level2name=N'AuditUpdateTime'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The numeric code assigned to the record indicating what type of activity generated the audit transaction' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTranactionNotesAudit', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A unique integer used as the primary key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTranactionNotesAudit', @level2type=N'COLUMN',@level2name=N'AuditKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Stores audit trail of activity in the PaymentTranactionNotes table.  One record is stored for each insert, update, or delete action. For insert/update activities, the audit record stores the new values.  For Deletions, the deleted values are stored.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTranactionNotesAudit'
GO;;
/****** Object:  Table [dbo].[AnnuitantList]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[AnnuitantList](
	[SCMClaimNumber] [varchar](7) NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
/****** Object:  Table [dbo].[AccountNote]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[AccountNote](
	[AccountNoteID] [int] IDENTITY(1,1) NOT NULL,
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[AccountNoteText] [varchar](7000) NOT NULL,
	[AccountNoteDate] [datetime] NOT NULL,
	[AccountNotePriority] [tinyint] NULL,
	[AccountNoteAuthorKey] [int] NOT NULL,
 CONSTRAINT [PK_AccountNote] PRIMARY KEY CLUSTERED 
(
	[AccountNoteID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The primary key to the account notes table - identity' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2196 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The Claim Number in the service credit master' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteText'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteText'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2172 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteText'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The text of the note' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteText'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteText'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteText'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteText'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=1932 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date and time the note was entered or updated' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNotePriority'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNotePriority'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2076 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNotePriority'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'An integer that indicates the relative priority of a note - higher numbers indicate higher priority' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNotePriority'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNotePriority'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNotePriority'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNotePriority'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteAuthorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteAuthorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2856 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteAuthorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A foreign key to the Users table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteAuthorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteAuthorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteAuthorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote', @level2type=N'COLUMN',@level2name=N'AccountNoteAuthorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Stores notes containing arbitrary text.  Linked to ServiceCreditMaster table through foreign key SCMClaimNumber.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=10000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNote'
GO;;
/****** Object:  Table [dbo].[AccountNoteAudit]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[AccountNoteAudit](
	[AccountNoteID] [int] NULL,
	[SCMClaimNumber] [varchar](7) NULL,
	[AccountNoteText] [varchar](7000) NULL,
	[AccountNoteDate] [datetime] NULL,
	[AccountNotePriority] [tinyint] NULL,
	[AccountNoteAuthorKey] [int] NULL,
	[AuditUserID] [varchar](128) NULL,
	[AuditUpdateTime] [datetime] NULL,
	[AuditActivity] [tinyint] NULL,
	[AuditKey] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_AccountNoteAudit] PRIMARY KEY CLUSTERED 
(
	[AuditKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The primary key to the account notes table - identity' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNoteAudit', @level2type=N'COLUMN',@level2name=N'AccountNoteID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The Claim Number in the service credit master' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNoteAudit', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The text of the note' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNoteAudit', @level2type=N'COLUMN',@level2name=N'AccountNoteText'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date and time the note was entered or updated' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNoteAudit', @level2type=N'COLUMN',@level2name=N'AccountNoteDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'An integer that indicates the relative priority of a note - higher numbers indicate higher priority' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNoteAudit', @level2type=N'COLUMN',@level2name=N'AccountNotePriority'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A foreign key to the Users table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNoteAudit', @level2type=N'COLUMN',@level2name=N'AccountNoteAuthorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNoteAudit', @level2type=N'COLUMN',@level2name=N'AccountNoteAuthorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNoteAudit', @level2type=N'COLUMN',@level2name=N'AccountNoteAuthorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The userid of the person making a change to the AccountNote table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNoteAudit', @level2type=N'COLUMN',@level2name=N'AuditUserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date and time the change was submitted' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNoteAudit', @level2type=N'COLUMN',@level2name=N'AuditUpdateTime'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The numeric code assigned to the record indicating what type of activity generated the audit transaction' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNoteAudit', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A unique integer used as the primary key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNoteAudit', @level2type=N'COLUMN',@level2name=N'AuditKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Stores audit trail of activity in the AccountNote table.  One record is stored for each insert, update, or delete action. For insert/update activities, the audit record stores the new values.  For Deletions, the deleted values are stored.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountNoteAudit'
GO;;
/****** Object:  Table [dbo].[AnnualPayIncreaseChart]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
CREATE TABLE [dbo].[AnnualPayIncreaseChart](
	[PayIncreaseDate] [datetime] NOT NULL,
 CONSTRAINT [PK_AnnualPayIncreaseChart] PRIMARY KEY CLUSTERED 
(
	[PayIncreaseDate] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date on which an automatic federal pay increase became effective' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AnnualPayIncreaseChart', @level2type=N'COLUMN',@level2name=N'PayIncreaseDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AnnualPayIncreaseChart', @level2type=N'COLUMN',@level2name=N'PayIncreaseDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AnnualPayIncreaseChart', @level2type=N'COLUMN',@level2name=N'PayIncreaseDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AnnualPayIncreaseChart', @level2type=N'COLUMN',@level2name=N'PayIncreaseDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Stores a simple list of dates on which federal automatic pay adjustments became effective.  Used to determine when a sevice period entered as part the service history for an account spans an automatic adjustment date.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AnnualPayIncreaseChart'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AnnualPayIncreaseChart'
GO;;
/****** Object:  Table [dbo].[ServicePeriods]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[ServicePeriods](
	[ServicePeriodsKey] [int] IDENTITY(1,1) NOT NULL,
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[SCMVersion] [int] NULL,
	[SCMLineNumber] [int] NULL,
	[SCMBeginDate] [datetime] NULL,
	[SCMEndDate] [datetime] NULL,
	[SCMRetirementTypeCode] [tinyint] NULL,
	[SCMAgencyCode] [int] NULL,
	[SCMServiceTypeCode] [varchar](2) NULL,
	[SCMAppointmentTypeCode] [char](1) NULL,
	[SCMEnteredAmount] [money] NULL,
	[SCMHoursInYear] [int] NULL,
	[SCMAnnualizedAmount] [money] NULL,
	[SCMPeriodType] [char](1) NULL,
	[SCMDateEntered] [datetime] NULL,
	[SCMEnteredBy] [int] NULL,
	[SCMPayTypeCode] [char](1) NULL,
	[SCMEffectiveDate] [datetime] NULL,
	[AsOfDate] [datetime] NULL,
 CONSTRAINT [PK_ServicePeriods] PRIMARY KEY CLUSTERED 
(
	[ServicePeriodsKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodsKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodsKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodsKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Uniquie identifier for ServicePeriods table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodsKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodsKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodsKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'ServicePeriodsKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The foreign key to the ServiceCreditMaster account record to which this service period record applies' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMVersion'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMVersion'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMVersion'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Multiple calculations can be performed using different sets of service periods, this represents the version (case) used for a calculation' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMVersion'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMVersion'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMVersion'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMVersion'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMLineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMLineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMLineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This indicates the order that the entries where submitted' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMLineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMLineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMLineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMLineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMBeginDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMBeginDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMBeginDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The begining date of the service period' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMBeginDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMBeginDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMBeginDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMBeginDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The ending Date of the service period' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'CSRS=1 FERS=2' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This identifies the agency where the service occurred' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMServiceTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMServiceTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=2364 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMServiceTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The type of service eg. deposit, redeposit, law enforcement, congress' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMServiceTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMServiceTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMServiceTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMServiceTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The type of work/appointment - Career, Temporary, etc.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The amount entered for this time period by the CSRep.  If this is less than $200, it is considered an hourly rate, otherwise, it is considered an annual salary rate' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMHoursInYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMHoursInYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMHoursInYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This is the number of hours in a work year used in the calculation. eg. 2080' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMHoursInYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMHoursInYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMHoursInYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMHoursInYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAnnualizedAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAnnualizedAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAnnualizedAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This is the calculated annualized value of the service' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAnnualizedAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAnnualizedAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=N'$#,##0.00;($#,##0.00)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAnnualizedAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMAnnualizedAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodType'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodType'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodType'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'From the VSAM service credit master - D=Deposit, R=Redeposit - add for new SC Separated, LWOP, Military, etc.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodType'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodType'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodType'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPeriodType'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMDateEntered'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMDateEntered'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMDateEntered'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date and time that the entry was made' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMDateEntered'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMDateEntered'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMDateEntered'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMDateEntered'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The ID of the rep who entered this period' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'1 - Salary, 2 - Hourly, 3 - Earnings, 4 - Refund/Lump Sum, 5 - Daily Rate, 6 - Monthly Rate' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The effective date of a lump sum payment such as a refund.  If the service period record represents a refund, this is the date that it was paid, and the beginning and ending dates may not apply.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods', @level2type=N'COLUMN',@level2name=N'SCMEffectiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The ServicePeriods table contains zero or more records for each account in the ServiceCreditMaster database.  Each record corresponds to an employment period in the service history of the employee.  Service periods are further grouped by SCMVersion.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=N'ServicePeriods.ServicePeriodsKey DESC' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x01 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriods'
GO;;
/****** Object:  Table [dbo].[ServicePeriodsAudit]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[ServicePeriodsAudit](
	[ServicePeriodsKey] [int] NULL,
	[SCMClaimNumber] [varchar](7) NULL,
	[SCMVersion] [int] NULL,
	[SCMLineNumber] [int] NULL,
	[SCMBeginDate] [datetime] NULL,
	[SCMEndDate] [datetime] NULL,
	[SCMRetirementTypeCode] [tinyint] NULL,
	[SCMAgencyCode] [int] NULL,
	[SCMServiceTypeCode] [varchar](2) NULL,
	[SCMAppointmentTypeCode] [char](1) NULL,
	[SCMEnteredAmount] [money] NULL,
	[SCMHoursInYear] [int] NULL,
	[SCMAnnualizedAmount] [money] NULL,
	[SCMPeriodType] [char](1) NULL,
	[SCMDateEntered] [datetime] NULL,
	[SCMEnteredBy] [int] NULL,
	[SCMPayTypeCode] [char](1) NULL,
	[SCMEffctiveDate] [datetime] NULL,
	[AsOfDate] [datetime] NULL,
	[AuditUserID] [varchar](128) NULL,
	[AuditUpdateTime] [datetime] NULL,
	[AuditActivity] [tinyint] NULL,
	[AuditKey] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_ServicePeriodsAudit] PRIMARY KEY CLUSTERED 
(
	[AuditKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'ServicePeriodsKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'ServicePeriodsKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'ServicePeriodsKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMVersion'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMVersion'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMVersion'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMLineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMLineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMLineNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMBeginDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMBeginDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMBeginDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEndDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMRetirementTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMAgencyCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMServiceTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMServiceTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMServiceTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMAppointmentTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEnteredAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEnteredAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEnteredAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMHoursInYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMHoursInYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMHoursInYear'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMAnnualizedAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMAnnualizedAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMAnnualizedAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMPeriodType'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMPeriodType'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMPeriodType'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMDateEntered'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMDateEntered'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMDateEntered'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEnteredBy'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMPayTypeCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEffctiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEffctiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEffctiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEffctiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEffctiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'SCMEffctiveDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditUserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditUserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditUserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The userid of the person making a change to the ServicePeriodsAudit table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditUserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditUpdateTime'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditUpdateTime'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditUpdateTime'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date and time the change was submitted' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditUpdateTime'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The numeric code assigned to the record indicating what type of activity generated the audit transaction' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditActivity'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnHidden', @value=0x00 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnOrder', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_ColumnWidth', @value=65535 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A unique integer used as the primary key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit', @level2type=N'COLUMN',@level2name=N'AuditKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DefaultView', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Stores audit trail of activity in the ServicePeriods table.  One record is stored for each insert, update, or delete action. For insert/update activities, the audit record stores the new values.  For Deletions, the deleted values are stored.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Filter', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderBy', @value=N'ServicePeriodsAudit.AuditUpdateTime DESC' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_OrderByOn', @value=0x01 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_TableMaxRecords', @value=10000 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ServicePeriodsAudit'
GO;;
/****** Object:  Table [dbo].[Users]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[Users](
	[UserKey] [int] IDENTITY(1,1) NOT NULL,
	[UserNetworkID] [varchar](128) NOT NULL,
	[UserID] [varchar](50) NOT NULL,
	[UserPassword] [varchar](20) NOT NULL,
	[UserStatusCode] [int] NOT NULL,
	[UserFirstName] [varchar](40) NULL,
	[UserLastName] [varchar](40) NULL,
	[UserPhoneNumber] [varchar](18) NULL,
	[UserEmail] [varchar](50) NULL,
	[UserSupervisorKey] [int] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[UserKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The unique key for each user in the service credit system' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The network logon ID for the user' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserNetworkID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserNetworkID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserNetworkID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserNetworkID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The unique user id (logon id) for each user in the service credit system' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The encrypted password for the user' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserPassword'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserPassword'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserPassword'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserPassword'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The current status of the user (0 = inactive, 1 = active, 2=suspended)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The first name of the user' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserFirstName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserFirstName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserFirstName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserFirstName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The last name of the user' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserLastName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserLastName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserLastName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserLastName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The phone number for contacting the user - not required' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserPhoneNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserPhoneNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserPhoneNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserPhoneNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The email address of the user - require for automated email notifications' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserEmail'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserEmail'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserEmail'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserEmail'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The UserKey field value for the user''s supervisor' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserSupervisorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserSupervisorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserSupervisorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'UserSupervisorKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This table stores the users of the Service Credit system.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users'
GO;;
/****** Object:  Table [dbo].[PaymentRefundLinks]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
CREATE TABLE [dbo].[PaymentRefundLinks](
	[PaymentNeedingRefund] [int] NOT NULL,
	[RefundForPayment] [int] NOT NULL
) ON [PRIMARY]
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'PayTransactionKey of the payment received.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentRefundLinks', @level2type=N'COLUMN',@level2name=N'PaymentNeedingRefund'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'PayTransactionKey of the matching refund' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentRefundLinks', @level2type=N'COLUMN',@level2name=N'RefundForPayment'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This table links the PayTransactionKey of a payment needing a refund to its corresponding refund.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentRefundLinks'
GO;;
/****** Object:  Table [dbo].[PaymentTranactionNotes]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[PaymentTranactionNotes](
	[PayTransactionKey] [int] NOT NULL,
	[Note] [varchar](500) NOT NULL,
 CONSTRAINT [PK_PaymentTranactionNotes] PRIMARY KEY CLUSTERED 
(
	[PayTransactionKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Link to Payment Transactions table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTranactionNotes', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Text entered by the user' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTranactionNotes', @level2type=N'COLUMN',@level2name=N'Note'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'If a note has to be added to a receipt/invoice, this table has the note. Example, the reason for a payment reversal.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentTranactionNotes'
GO;;
/****** Object:  Table [dbo].[RefundTransaction]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[RefundTransaction](
	[RefundTransactionKey] [int] IDENTITY(1,1) NOT NULL,
	[RefundAmount] [money] NOT NULL,
	[SCMClaimnumber] [varchar](7) NOT NULL,
	[RefundTransactionDate] [datetime] NOT NULL,
	[TechnicianUserKey] [int] NOT NULL,
	[PayTransactionKey] [int] NOT NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Database ID Number' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RefundTransaction', @level2type=N'COLUMN',@level2name=N'RefundTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Amount of credit as a negative number so we can add it to payments.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RefundTransaction', @level2type=N'COLUMN',@level2name=N'RefundAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'CSD Claim number - foreign key in the ServiceCreditMaster table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RefundTransaction', @level2type=N'COLUMN',@level2name=N'SCMClaimnumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date that this refund request was generated.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RefundTransaction', @level2type=N'COLUMN',@level2name=N'RefundTransactionDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The UserKey (foreign key in the Users table) of the technician who last updated the payment transaction' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RefundTransaction', @level2type=N'COLUMN',@level2name=N'TechnicianUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Link to PaymentTransaction table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RefundTransaction', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Records of balance refunds to be added to refunded payments if the user chooses to refund the credit balance when refunding an extra payment. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RefundTransaction'
GO;;
/****** Object:  Table [dbo].[Invoices]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
CREATE TABLE [dbo].[Invoices](
	[InvoiceID] [int] IDENTITY(1,1) NOT NULL,
	[PayTransactionKey] [int] NOT NULL,
	[SCMDeposit] [money] NOT NULL,
	[SCMRedeposit] [money] NOT NULL,
	[SCMTotVarRedeposit] [money] NOT NULL,
	[SCMNonDed] [money] NOT NULL,
	[SCMFersW] [money] NOT NULL,
	[SCMAccIntDep] [money] NOT NULL,
	[SCMAccIntRdep] [money] NOT NULL,
	[SCMAccIntNonDed] [money] NOT NULL,
	[SCMAccIntVarRdep] [money] NOT NULL,
	[SCMAccIntFers] [money] NOT NULL,
	[SCMTotPayd] [money] NOT NULL,
	[SCMTotPayr] [money] NOT NULL,
	[SCMTotPayn] [money] NOT NULL,
	[SCMTotPayvr] [money] NOT NULL,
	[SCMTotPayfers] [money] NOT NULL,
	[LastPay] [datetime] NOT NULL,
	[CalcDate] [datetime] NOT NULL,
	[NextInvoiceID] [int] NOT NULL,
 CONSTRAINT [IX_Invoices_1] UNIQUE NONCLUSTERED 
(
	[InvoiceID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Primary Index Key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'InvoiceID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Link to payment that generated this invoice. Zero if this is an initial bill.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pre 10/82 Deposit before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMDeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pre 10/82 Redeposit before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Post 10/82 Redeposit before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMTotVarRedeposit'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Post 10/82 Deposit before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'FERS Deposit total before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMFersW'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Accumulated Interest on Pre 10/82 deposit before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMAccIntDep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Accumulated Interest on Pre 10/82 Redeposit before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMAccIntRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Accumulated Interest on post 10/82 deposit before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMAccIntNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Accumulated Interest on post 10/82 redeposit before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMAccIntVarRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'FERS Interest before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMAccIntFers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total Pre 10/82 Deposit payments before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMTotPayd'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total Pre 10/82 Redposit Payments before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMTotPayr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total Post 10/82 Deposit payments before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMTotPayn'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total Post 10/82 Redeposit payments before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMTotPayvr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Total FERS payments before this payment was applied' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'SCMTotPayfers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The date of the previous payment and the date used to calculate these dollars amounts.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'LastPay'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Timestamp for this record creation' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'CalcDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Linked List pointer to the next payment invoice record for this account.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices', @level2type=N'COLUMN',@level2name=N'NextInvoiceID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Each record is saved after a payment is received and before an invoice is calculated & sent to an claimant. It ties to the PaymentTransaction table by PayTransactionKey. The latter is zero if this is an initial bill.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Invoices'
GO;;
/****** Object:  Table [dbo].[BalanceExceptions]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[BalanceExceptions](
	[SCMClaimNumber] [varchar](7) NOT NULL,
	[ExceptionDate] [datetime] NOT NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'CSD of the account with a known exception.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BalanceExceptions', @level2type=N'COLUMN',@level2name=N'SCMClaimNumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Date entered into table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BalanceExceptions', @level2type=N'COLUMN',@level2name=N'ExceptionDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Lists the accounts that are not in balance because of exceptional circumstances. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BalanceExceptions'
GO;;
/****** Object:  Table [dbo].[PaymentMoveTransaction]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[PaymentMoveTransaction](
	[TransactionKey] [int] IDENTITY(1,1) NOT NULL,
	[SCMClaimnumber] [varchar](7) NOT NULL,
	[SCMTotPayd] [money] NOT NULL,
	[SCMTotPayr] [money] NOT NULL,
	[SCMTotPayn] [money] NOT NULL,
	[SCMTotPayvr] [money] NOT NULL,
	[SCMTotPayfers] [money] NOT NULL,
	[ModificationDate] [datetime] NOT NULL,
	[ApprovalDate] [datetime] NULL,
	[ProcessedDate] [datetime] NULL,
	[TechnicianUserKey] [int] NOT NULL,
	[ManagerUserKey] [int] NULL,
	[Approved] [bit] NULL,
	[Disapproved] [bit] NULL,
	[Modified] [bit] NULL,
	[Note] [varchar](2000) NOT NULL,
 CONSTRAINT [PK_PaymentMoveTransaction] PRIMARY KEY CLUSTERED 
(
	[TransactionKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Primary Identity Key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'TransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Link to Claimant' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'SCMClaimnumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Payment change to pre 10/82 deposit interest' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'SCMTotPayd'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Payment change to pre 10/82 Redeposit interest' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'SCMTotPayr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Payment change to post 9/82 deposit interest' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'SCMTotPayn'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Payment change to post 9/82 Redeposit interest' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'SCMTotPayvr'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Payment change to FERS interest' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'SCMTotPayfers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Timestamp when user proposed Payment Move' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'ModificationDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Timestamp when approved or disapproved' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'ApprovalDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Timestamp when processed' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'ProcessedDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Link to user who proposed ajustment' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'TechnicianUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Link to user who approved this Payment Move' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'ManagerUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if approved, otherwise false' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'Approved'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if disapproved, otherwise false' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction', @level2type=N'COLUMN',@level2name=N'Disapproved'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'User moves payment money to another retirement type. Shows if this was denied or approved.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PaymentMoveTransaction'
GO;;
/****** Object:  Table [dbo].[AdjustmentTransaction]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[AdjustmentTransaction](
	[TransactionKey] [int] IDENTITY(1,1) NOT NULL,
	[SCMClaimnumber] [varchar](7) NOT NULL,
	[SCMAccIntDep] [money] NOT NULL,
	[SCMAccIntRdep] [money] NOT NULL,
	[SCMAccIntNonDed] [money] NOT NULL,
	[SCMAccIntVarRdep] [money] NOT NULL,
	[SCMAccIntFers] [money] NOT NULL,
	[ModificationDate] [datetime] NOT NULL,
	[ApprovalDate] [datetime] NULL,
	[ProcessedDate] [datetime] NULL,
	[TechnicianUserKey] [int] NOT NULL,
	[ManagerUserKey] [int] NULL,
	[Approved] [bit] NOT NULL,
	[Disapproved] [bit] NOT NULL,
	[Modified] [bit] NOT NULL,
	[Note] [varchar](2000) NOT NULL,
 CONSTRAINT [PK_AdjustmentTransaction] PRIMARY KEY CLUSTERED 
(
	[TransactionKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Primary Identity Key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'TransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Link to Claimant' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'SCMClaimnumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Adjustment to pre 10/82 deposit interest' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'SCMAccIntDep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Adjustment to pre 10/82 Redeposit interest' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'SCMAccIntRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Adjustment to post 9/82 deposit interest' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'SCMAccIntNonDed'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Adjustment to post 9/82 Redeposit interest' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'SCMAccIntVarRdep'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Adjustment to FERS interest' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'SCMAccIntFers'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Timestamp when user proposed adjustment' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'ModificationDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Timestamp when approved or disapproved' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'ApprovalDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Timestamp when processed' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'ProcessedDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Link to user who proposed ajustment' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'TechnicianUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Link to user who approved this adjustment' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'ManagerUserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if approved, otherwise false' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'Approved'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if disapproved, otherwise false' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'Disapproved'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if modified in program but not saved, otherwise false' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction', @level2type=N'COLUMN',@level2name=N'Modified'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'User adjustments to the additional interest fields. Shows if this was denied or approved.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AdjustmentTransaction'
GO;;
/****** Object:  Table [dbo].[UserRoleAssignment]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
CREATE TABLE [dbo].[UserRoleAssignment](
	[UserKey] [int] NOT NULL,
	[UserRoleCode] [int] NOT NULL,
 CONSTRAINT [PK_UserRoleAssignment] PRIMARY KEY CLUSTERED 
(
	[UserKey] ASC,
	[UserRoleCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pointer to the Users table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignment', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignment', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignment', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignment', @level2type=N'COLUMN',@level2name=N'UserKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pointer to the LookUpUserRoleCode Table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignment', @level2type=N'COLUMN',@level2name=N'UserRoleCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_DisplayControl', @value=N'109' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignment', @level2type=N'COLUMN',@level2name=N'UserRoleCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Format', @value=NULL , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignment', @level2type=N'COLUMN',@level2name=N'UserRoleCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_IMEMode', @value=N'0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignment', @level2type=N'COLUMN',@level2name=N'UserRoleCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Orientation', @value=0 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'UserRoleAssignment'
GO;;
/****** Object:  Table [dbo].[SupervisorRoles]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
CREATE TABLE [dbo].[SupervisorRoles](
	[SupervisorID] [int] IDENTITY(1,1) NOT NULL,
	[UserRoleCode] [int] NOT NULL,
	[SupervisorRoleCode] [int] NOT NULL,
 CONSTRAINT [PK_SupervisorRoles] PRIMARY KEY CLUSTERED 
(
	[SupervisorID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Role from LookupUserRoleCode table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'SupervisorRoles', @level2type=N'COLUMN',@level2name=N'UserRoleCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'UserRoleCode of the Supervisor for this role' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'SupervisorRoles', @level2type=N'COLUMN',@level2name=N'SupervisorRoleCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Every user role needs a supervisor. This table links up the roles: supervisor & subordinate.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'SupervisorRoles'
GO;;
/****** Object:  Table [dbo].[BatchDailyPayments]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[BatchDailyPayments](
	[AuditBatchIDLog] [int] NULL,
	[PayTransactionKey] [int] NULL,
	[NumberPaymentsToday] [int] NULL,
	[BatchDate] [datetime] NOT NULL,
	[AccountStatus] [tinyint] NULL,
	[PayTransStatusCode] [tinyint] NULL,
	[SCMClaimnumber] [varchar](7) NULL,
	[AccountBalance] [money] NULL,
	[OverPaymentAmount] [money] NULL,
	[ACHPayment] [bit] NULL,
	[ACHStopLetter] [bit] NULL,
	[PrintInvoice] [bit] NULL,
	[RefundRequired] [bit] NULL,
	[ReversedPayment] [bit] NULL,
	[UpdateToCompleted] [bit] NULL,
	[PrintInitialBill] [bit] NULL,
	[LatestBatch] [bit] NULL,
	[ErrorProcessing] [bit] NULL
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The number for this batch. Same for all entries in the batch.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'AuditBatchIDLog'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Pointer to paymenttransaction table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Number of payments for this claimant today. Roll up into one receipt/invoice.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'NumberPaymentsToday'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Autogenerated timestamp for this batch' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'BatchDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Account Status at the time of this batch insert' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'AccountStatus'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Payment status at the time of this batch insert' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'PayTransStatusCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Claimant CSD Number' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'SCMClaimnumber'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Account balance at the time of this batch insert' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'AccountBalance'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Amount for the Refund Memo. Triggers the Memo if > 0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'OverPaymentAmount'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Payment type is ACH if true, check or other manual if false.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'ACHPayment'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True: need to print the stop ACH letter' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'ACHStopLetter'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if another invoice needs to be printed.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'PrintInvoice'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Implies PrintInvoice is false. This payment was more than the amount required to pay off the balance. Refund the difference.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'RefundRequired'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this invoice is triggered by a bounced check' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'ReversedPayment'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if ready to update from "Pending" to "Completed"' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'UpdateToCompleted'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this is an initial bill RI36-23' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'PrintInitialBill'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True for the latest batch' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'LatestBatch'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this payment threw an exception during batch processing.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments', @level2type=N'COLUMN',@level2name=N'ErrorProcessing'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This table of the payments processed this day is for Crystal Reporting. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchDailyPayments'
GO;;
/****** Object:  Table [dbo].[BatchImportErrorLog]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[BatchImportErrorLog](
	[IDBankError] [int] IDENTITY(1,1) NOT NULL,
	[AuditBatchIDLog] [int] NOT NULL,
	[TransactionCode] [char](1) NOT NULL,
	[TransactionLine] [varchar](50) NULL,
	[ErrorMessage] [varchar](400) NULL,
 CONSTRAINT [PK_BatchImportErrorLog] PRIMARY KEY CLUSTERED 
(
	[IDBankError] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Unique record ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchImportErrorLog', @level2type=N'COLUMN',@level2name=N'IDBankError'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Link to batch log table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchImportErrorLog', @level2type=N'COLUMN',@level2name=N'AuditBatchIDLog'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The type of change record sent by the bank. Our system accepts "A" & "C" transactions.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchImportErrorLog', @level2type=N'COLUMN',@level2name=N'TransactionCode'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The text from the mainframe file. This is the record that did not parse.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchImportErrorLog', @level2type=N'COLUMN',@level2name=N'TransactionLine'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The program''s description of what''s wrong.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchImportErrorLog', @level2type=N'COLUMN',@level2name=N'ErrorMessage'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This lists the errors encountered while importing the lockbox bank''s changes and payments.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchImportErrorLog'
GO;;
/****** Object:  Table [dbo].[MainframeImport]    Script Date: 10/11/2012 10:59:14 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[MainframeImport](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[AuditBatchIDLog] [int] NULL,
	[PayTransactionKey] [int] NOT NULL,
	[ImportDate] [datetime] NOT NULL,
	[RecordString] [varchar](120) NULL,
	[FileName] [varchar](50) NOT NULL,
	[ProcessingFlag] [bit] NOT NULL,
	[ErrorFlag] [bit] NOT NULL,
	[ACHFlag] [bit] NOT NULL,
	[SuspendedFlag] [bit] NOT NULL,
	[UnresolvedFlag] [bit] NOT NULL,
	[PostedPendingFlag] [bit] NOT NULL,
	[ACHStatusChecked] [bit] NOT NULL,
 CONSTRAINT [PK_MainframeImport] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Primary key so we can keep track of which records to delete after a successful importation.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MainframeImport', @level2type=N'COLUMN',@level2name=N'ID'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'If this is a payment, then this field links to the PaymentTransaction table' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MainframeImport', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The autogenerated date of this importation. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MainframeImport', @level2type=N'COLUMN',@level2name=N'ImportDate'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This holds a single line from a BRID04 Transaction file. It is loaded by a script on the Connect:Direct computer.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MainframeImport', @level2type=N'COLUMN',@level2name=N'RecordString'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'File that held this record' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MainframeImport', @level2type=N'COLUMN',@level2name=N'FileName'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Set to true when processing starts to prevent conflicts if the database bcp loader happens to run at the same time.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MainframeImport', @level2type=N'COLUMN',@level2name=N'ProcessingFlag'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Set to true if the record is too malformed for importation' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MainframeImport', @level2type=N'COLUMN',@level2name=N'ErrorFlag'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'True if this payment is an ACH payment' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MainframeImport', @level2type=N'COLUMN',@level2name=N'ACHFlag'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'If if this payment was suspended at the time of importation.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MainframeImport', @level2type=N'COLUMN',@level2name=N'SuspendedFlag'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'If if this payment was unresolved at the time of importation.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MainframeImport', @level2type=N'COLUMN',@level2name=N'UnresolvedFlag'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'If if this payment was posted-pending at the time of importation.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MainframeImport', @level2type=N'COLUMN',@level2name=N'PostedPendingFlag'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'False if this record has not yet been checked for ACH Status, Set to true after the check.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MainframeImport', @level2type=N'COLUMN',@level2name=N'ACHStatusChecked'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This table has just strings to be processed by the the Service Credit batch service. It will determine which file each record is and will load the correct table.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MainframeImport'
GO;;
/****** Object:  Table [dbo].[BatchProcessingErrorLog]    Script Date: 10/11/2012 10:59:13 ******/
SET ANSI_NULLS ON
GO;;
SET QUOTED_IDENTIFIER ON
GO;;
SET ANSI_PADDING ON
GO;;
CREATE TABLE [dbo].[BatchProcessingErrorLog](
	[IDProcessingError] [int] IDENTITY(1,1) NOT NULL,
	[AuditBatchIDLog] [int] NULL,
	[PayTransactionKey] [int] NOT NULL,
	[ErrorMessage] [varchar](500) NULL,
 CONSTRAINT [PK_BatchProcessingErrorLog] PRIMARY KEY CLUSTERED 
(
	[IDProcessingError] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO;;
SET ANSI_PADDING OFF
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Unique record ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchProcessingErrorLog', @level2type=N'COLUMN',@level2name=N'IDProcessingError'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Link to the Batch Log' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchProcessingErrorLog', @level2type=N'COLUMN',@level2name=N'AuditBatchIDLog'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'An ID code for the process that threw the exception.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchProcessingErrorLog', @level2type=N'COLUMN',@level2name=N'PayTransactionKey'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'The program''s description of what''s wrong.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchProcessingErrorLog', @level2type=N'COLUMN',@level2name=N'ErrorMessage'
GO;;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This lists the errors encountered while processing the daily payments.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BatchProcessingErrorLog'
GO;;
/****** Object:  Default [DF_A01_PrintSuppressionCases_ReasonForPrintSuppression]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[A01_PrintSuppressionCases] ADD  CONSTRAINT [DF_A01_PrintSuppressionCases_ReasonForPrintSuppression]  DEFAULT (0) FOR [ReasonForPrintSuppression]
GO;;
/****** Object:  Default [DF_AccountNote_AccountNoteText]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AccountNote] ADD  CONSTRAINT [DF_AccountNote_AccountNoteText]  DEFAULT (' ') FOR [AccountNoteText]
GO;;
/****** Object:  Default [DF_AccountNote_AccountNoteDate]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AccountNote] ADD  CONSTRAINT [DF_AccountNote_AccountNoteDate]  DEFAULT (getdate()) FOR [AccountNoteDate]
GO;;
/****** Object:  Default [DF_AccountNote_AccountNotePriority]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AccountNote] ADD  CONSTRAINT [DF_AccountNote_AccountNotePriority]  DEFAULT (0) FOR [AccountNotePriority]
GO;;
/****** Object:  Default [DF_AdjustmentTransaction_SCMAccIntDep]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AdjustmentTransaction] ADD  CONSTRAINT [DF_AdjustmentTransaction_SCMAccIntDep]  DEFAULT (0) FOR [SCMAccIntDep]
GO;;
/****** Object:  Default [DF_AdjustmentTransaction_SCMAccIntRdep]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AdjustmentTransaction] ADD  CONSTRAINT [DF_AdjustmentTransaction_SCMAccIntRdep]  DEFAULT (0) FOR [SCMAccIntRdep]
GO;;
/****** Object:  Default [DF_AdjustmentTransaction_SCMAccIntNonDed]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AdjustmentTransaction] ADD  CONSTRAINT [DF_AdjustmentTransaction_SCMAccIntNonDed]  DEFAULT (0) FOR [SCMAccIntNonDed]
GO;;
/****** Object:  Default [DF_AdjustmentTransaction_SCMAccIntVarRdep]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AdjustmentTransaction] ADD  CONSTRAINT [DF_AdjustmentTransaction_SCMAccIntVarRdep]  DEFAULT (0) FOR [SCMAccIntVarRdep]
GO;;
/****** Object:  Default [DF_AdjustmentTransaction_SCMAccIntFers]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AdjustmentTransaction] ADD  CONSTRAINT [DF_AdjustmentTransaction_SCMAccIntFers]  DEFAULT (0) FOR [SCMAccIntFers]
GO;;
/****** Object:  Default [DF_AdjustmentTransaction_ModificationDate]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AdjustmentTransaction] ADD  CONSTRAINT [DF_AdjustmentTransaction_ModificationDate]  DEFAULT (getdate()) FOR [ModificationDate]
GO;;
/****** Object:  Default [DF_AdjustmentTransaction_Approved]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AdjustmentTransaction] ADD  CONSTRAINT [DF_AdjustmentTransaction_Approved]  DEFAULT (0) FOR [Approved]
GO;;
/****** Object:  Default [DF_AdjustmentTransaction_Disapproved]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AdjustmentTransaction] ADD  CONSTRAINT [DF_AdjustmentTransaction_Disapproved]  DEFAULT (0) FOR [Disapproved]
GO;;
/****** Object:  Default [DF_AdjustmentTransaction_Modified]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AdjustmentTransaction] ADD  CONSTRAINT [DF_AdjustmentTransaction_Modified]  DEFAULT (0) FOR [Modified]
GO;;
/****** Object:  Default [DF_AuditBatch_FileReceived]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_FileReceived]  DEFAULT (0) FOR [FileReceived]
GO;;
/****** Object:  Default [DF_AuditBatch_DailyAction]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_DailyAction]  DEFAULT (0) FOR [DailyAction]
GO;;
/****** Object:  Default [DF_AuditBatch_ErrorImporting]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_ErrorImporting]  DEFAULT (0) FOR [ErrorImporting]
GO;;
/****** Object:  Default [DF_AuditBatch_ErrorProcessing]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_ErrorProcessing]  DEFAULT (0) FOR [ErrorProcessing]
GO;;
/****** Object:  Default [DF_AuditBatch_LatestBatch]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_LatestBatch]  DEFAULT (0) FOR [LatestBatch]
GO;;
/****** Object:  Default [DF_AuditBatch_AmountImported]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_AmountImported]  DEFAULT (0) FOR [AmountImported]
GO;;
/****** Object:  Default [DF_AuditBatch_AmountProcessed]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_AmountProcessed]  DEFAULT (0) FOR [AmountProcessed]
GO;;
/****** Object:  Default [DF_AuditBatch_NumberACHAccepted]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_NumberACHAccepted]  DEFAULT (0) FOR [NumberACHAccepted]
GO;;
/****** Object:  Default [DF_AuditBatch_NumberACHUnresolved]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_NumberACHUnresolved]  DEFAULT (0) FOR [NumberACHUnresolved]
GO;;
/****** Object:  Default [DF_AuditBatch_NumberACHSuspended]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_NumberACHSuspended]  DEFAULT (0) FOR [NumberACHSuspended]
GO;;
/****** Object:  Default [DF_AuditBatch_AcceptedPayments]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_AcceptedPayments]  DEFAULT (0) FOR [NumberAccepted]
GO;;
/****** Object:  Default [DF_AuditBatch_Unresolved]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_Unresolved]  DEFAULT (0) FOR [NumberUnresolved]
GO;;
/****** Object:  Default [DF_AuditBatch_NumberSuspended]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_NumberSuspended]  DEFAULT (0) FOR [NumberSuspended]
GO;;
/****** Object:  Default [DF_AuditBatch_NumberChangeRequests]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_NumberChangeRequests]  DEFAULT (0) FOR [NumberChangeRequests]
GO;;
/****** Object:  Default [DF_AuditBatch_ErrorCountImporting]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_ErrorCountImporting]  DEFAULT (0) FOR [ErrorCountImporting]
GO;;
/****** Object:  Default [DF_AuditBatch_PaymentsProcessed]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_PaymentsProcessed]  DEFAULT (0) FOR [PaymentsProcessed]
GO;;
/****** Object:  Default [DF_AuditBatch_InitialBillsProcessed]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_InitialBillsProcessed]  DEFAULT (0) FOR [InitialBillsProcessed]
GO;;
/****** Object:  Default [DF_AuditBatch_ReversedProcessed]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_ReversedProcessed]  DEFAULT (0) FOR [ReversedProcessed]
GO;;
/****** Object:  Default [DF_AuditBatch_ACHStopLetters]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_ACHStopLetters]  DEFAULT (0) FOR [ACHStopLetters]
GO;;
/****** Object:  Default [DF_AuditBatch_RefundMemos]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_RefundMemos]  DEFAULT (0) FOR [RefundMemos]
GO;;
/****** Object:  Default [DF_AuditBatch_PaymentErrors]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_PaymentErrors]  DEFAULT (0) FOR [ErrorCountProcessing]
GO;;
/****** Object:  Default [DF_AuditBatch_BatchTime]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AuditBatch] ADD  CONSTRAINT [DF_AuditBatch_BatchTime]  DEFAULT (getdate()) FOR [BatchTime]
GO;;
/****** Object:  Default [DF_BalanceExceptions_ExceptionDate]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BalanceExceptions] ADD  CONSTRAINT [DF_BalanceExceptions_ExceptionDate]  DEFAULT (getdate()) FOR [ExceptionDate]
GO;;
/****** Object:  Default [DF_BatchDailyPayments_BatchDate]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BatchDailyPayments] ADD  CONSTRAINT [DF_BatchDailyPayments_BatchDate]  DEFAULT (getdate()) FOR [BatchDate]
GO;;
/****** Object:  Default [DF_BatchDailyPayments_OverPaymentAmount]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BatchDailyPayments] ADD  CONSTRAINT [DF_BatchDailyPayments_OverPaymentAmount]  DEFAULT (0) FOR [OverPaymentAmount]
GO;;
/****** Object:  Default [DF_BatchDailyPayments_ACHStopLetter]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BatchDailyPayments] ADD  CONSTRAINT [DF_BatchDailyPayments_ACHStopLetter]  DEFAULT (0) FOR [ACHStopLetter]
GO;;
/****** Object:  Default [DF_BatchDailyPayments_PrintInvoice]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BatchDailyPayments] ADD  CONSTRAINT [DF_BatchDailyPayments_PrintInvoice]  DEFAULT (0) FOR [PrintInvoice]
GO;;
/****** Object:  Default [DF_BatchDailyPayments_RefundRequired]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BatchDailyPayments] ADD  CONSTRAINT [DF_BatchDailyPayments_RefundRequired]  DEFAULT (0) FOR [RefundRequired]
GO;;
/****** Object:  Default [DF_BatchDailyPayments_ReversedPayment]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BatchDailyPayments] ADD  CONSTRAINT [DF_BatchDailyPayments_ReversedPayment]  DEFAULT (0) FOR [ReversedPayment]
GO;;
/****** Object:  Default [DF_BatchDailyPayments_UpdateToCompleted]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BatchDailyPayments] ADD  CONSTRAINT [DF_BatchDailyPayments_UpdateToCompleted]  DEFAULT (0) FOR [UpdateToCompleted]
GO;;
/****** Object:  Default [DF_BatchDailyPayments_PrintInitialBill]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BatchDailyPayments] ADD  CONSTRAINT [DF_BatchDailyPayments_PrintInitialBill]  DEFAULT (0) FOR [PrintInitialBill]
GO;;
/****** Object:  Default [DF_BatchDailyPayments_ErrorProcessing]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BatchDailyPayments] ADD  CONSTRAINT [DF_BatchDailyPayments_ErrorProcessing]  DEFAULT (0) FOR [ErrorProcessing]
GO;;
/****** Object:  Default [DF_Invoices_PayTransactionKey]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[Invoices] ADD  CONSTRAINT [DF_Invoices_PayTransactionKey]  DEFAULT (0) FOR [PayTransactionKey]
GO;;
/****** Object:  Default [DF_Invoices_PriorBalance]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[Invoices] ADD  CONSTRAINT [DF_Invoices_PriorBalance]  DEFAULT (0) FOR [SCMDeposit]
GO;;
/****** Object:  Default [DF_Invoices_NewBalance]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[Invoices] ADD  CONSTRAINT [DF_Invoices_NewBalance]  DEFAULT (0) FOR [SCMRedeposit]
GO;;
/****** Object:  Default [DF_Invoices_CalcDate]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[Invoices] ADD  CONSTRAINT [DF_Invoices_CalcDate]  DEFAULT (getdate()) FOR [CalcDate]
GO;;
/****** Object:  Default [DF_Invoices_NextInvoiceID]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[Invoices] ADD  CONSTRAINT [DF_Invoices_NextInvoiceID]  DEFAULT (0) FOR [NextInvoiceID]
GO;;
/****** Object:  Default [DF_LookupDeductionRates_CSRSConversionFactor]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[LookupDeductionRates] ADD  CONSTRAINT [DF_LookupDeductionRates_CSRSConversionFactor]  DEFAULT (0) FOR [DeductionConversionFactor]
GO;;
/****** Object:  Default [DF_LookUpGLCodes_GLName]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[LookUpGLCodes] ADD  CONSTRAINT [DF_LookUpGLCodes_GLName]  DEFAULT (' ') FOR [GLName]
GO;;
/****** Object:  Default [DF_LookUpGLCodes_GLCode]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[LookUpGLCodes] ADD  CONSTRAINT [DF_LookUpGLCodes_GLCode]  DEFAULT ('') FOR [GLCode]
GO;;
/****** Object:  Default [DF_LookUpGLCodes_SCMRetirementTypeCode]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[LookUpGLCodes] ADD  CONSTRAINT [DF_LookUpGLCodes_SCMRetirementTypeCode]  DEFAULT (0) FOR [SCMRetirementTypeCode]
GO;;
/****** Object:  Default [DF_LookUpGLCodes_PostOffice]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[LookUpGLCodes] ADD  CONSTRAINT [DF_LookUpGLCodes_PostOffice]  DEFAULT (0) FOR [PostOffice]
GO;;
/****** Object:  Default [DF_LookupInterestGracePeriodAudit_AuditUpdateTime]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[LookupInterestGracePeriodAudit] ADD  CONSTRAINT [DF_LookupInterestGracePeriodAudit_AuditUpdateTime]  DEFAULT (getdate()) FOR [AuditUpdateTime]
GO;;
/****** Object:  Default [DF_LookupInterestGracePeriodAudit_AuditActivity]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[LookupInterestGracePeriodAudit] ADD  CONSTRAINT [DF_LookupInterestGracePeriodAudit_AuditActivity]  DEFAULT (1) FOR [AuditActivity]
GO;;
/****** Object:  Default [DF_LookupInterestSuppressionAudit_AuditUpdateTime]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[LookupInterestSuppressionAudit] ADD  CONSTRAINT [DF_LookupInterestSuppressionAudit_AuditUpdateTime]  DEFAULT (getdate()) FOR [AuditUpdateTime]
GO;;
/****** Object:  Default [DF_LookupInterestSuppressionAudit_AuditActivity]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[LookupInterestSuppressionAudit] ADD  CONSTRAINT [DF_LookupInterestSuppressionAudit_AuditActivity]  DEFAULT (1) FOR [AuditActivity]
GO;;
/****** Object:  Default [DF_LookUpPaymentAppliedOrder_CalculationStatusDisplayOrder]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[LookUpPaymentAppliedOrder] ADD  CONSTRAINT [DF_LookUpPaymentAppliedOrder_CalculationStatusDisplayOrder]  DEFAULT (1) FOR [PaymentAppliedDisplayOrder]
GO;;
/****** Object:  Default [DF_LookupPayTransStatusCode_NightlyBatch]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupPayTransStatusCode] ADD  CONSTRAINT [DF_LookupPayTransStatusCode_NightlyBatch]  DEFAULT (0) FOR [NightlyBatch]
GO;;
/****** Object:  Default [DF_LookupPayTransStatusCode_Deletable]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupPayTransStatusCode] ADD  CONSTRAINT [DF_LookupPayTransStatusCode_Deletable]  DEFAULT (0) FOR [Deletable]
GO;;
/****** Object:  Default [DF_LookupPayTransStatusCode_Reversable]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupPayTransStatusCode] ADD  CONSTRAINT [DF_LookupPayTransStatusCode_Reversable]  DEFAULT (0) FOR [Reversable]
GO;;
/****** Object:  Default [DF_LookupPayTransStatusCode_ManualEntered]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupPayTransStatusCode] ADD  CONSTRAINT [DF_LookupPayTransStatusCode_ManualEntered]  DEFAULT (0) FOR [ManualEntered]
GO;;
/****** Object:  Default [DF_LookupPayTransStatusCode_SuspenseAction]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupPayTransStatusCode] ADD  CONSTRAINT [DF_LookupPayTransStatusCode_SuspenseAction]  DEFAULT (0) FOR [SuspenseAction]
GO;;
/****** Object:  Default [DF_LookupPayTransStatusCode_CanHitGL]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupPayTransStatusCode] ADD  CONSTRAINT [DF_LookupPayTransStatusCode_CanHitGL]  DEFAULT (0) FOR [CanHitGL]
GO;;
/****** Object:  Default [DF_LookupPayTransStatusCode_ReversingType]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupPayTransStatusCode] ADD  CONSTRAINT [DF_LookupPayTransStatusCode_ReversingType]  DEFAULT (0) FOR [ReversingType]
GO;;
/****** Object:  Default [DF_LookupPayTransStatusCode_BalancedScorecard]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupPayTransStatusCode] ADD  CONSTRAINT [DF_LookupPayTransStatusCode_BalancedScorecard]  DEFAULT (0) FOR [BalancedScorecard]
GO;;
/****** Object:  Default [DF_LookupPayTransStatusCode_SendToDBTS]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupPayTransStatusCode] ADD  CONSTRAINT [DF_LookupPayTransStatusCode_SendToDBTS]  DEFAULT (0) FOR [SendToDBTS]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_AddAccount]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_AddAccount]  DEFAULT (0) FOR [AddAccount]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_AddNotes]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_AddNotes]  DEFAULT (0) FOR [AddNotes]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_AddPaymentTransactions]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_AddPaymentTransactions]  DEFAULT (0) FOR [AddPaymentTransactions]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_AddServiceHistory]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_AddServiceHistory]  DEFAULT (0) FOR [AddServiceHistory]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_AdjustAccount]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_AdjustAccount]  DEFAULT (0) FOR [AdjustAccount]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_AdjustPaymentTransactions]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_AdjustPaymentTransactions]  DEFAULT (0) FOR [ApprovePaymentTransactions]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_AssignUserRoles]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_AssignUserRoles]  DEFAULT (0) FOR [AssignUserRoles]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_CalculateServiceHistory]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_CalculateServiceHistory]  DEFAULT (0) FOR [CalculateServiceHistory]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_ChangeAccount]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_ChangeAccount]  DEFAULT (0) FOR [ChangeAccount]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_DeleteNotes]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_DeleteNotes]  DEFAULT (0) FOR [DeleteNotes]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_DeleteServiceHistory]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_DeleteServiceHistory]  DEFAULT (0) FOR [DeleteServiceHistory]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_EditNotes]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_EditNotes]  DEFAULT (0) FOR [EditNotes]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_EditServiceHistory]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_EditServiceHistory]  DEFAULT (0) FOR [EditServiceHistory]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_GenerateStatement]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_GenerateStatement]  DEFAULT (0) FOR [GenerateStatement]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_PostPaymentTransaction]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_PostPaymentTransaction]  DEFAULT (0) FOR [PostPaymentTransaction]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_ChangePaymentStatus]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_ChangePaymentStatus]  DEFAULT (0) FOR [PostPriorPayments]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_RerunGL]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_RerunGL]  DEFAULT (0) FOR [RerunGL]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_ReversePaymentTransaction]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_ReversePaymentTransaction]  DEFAULT (0) FOR [ReversePaymentTransaction]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_SearchAccount]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_SearchAccount]  DEFAULT (0) FOR [SearchAccount]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_UnpostPaymentTransaction]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_UnpostPaymentTransaction]  DEFAULT (0) FOR [UnpostPaymentTransaction]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_UpdateInterest]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_UpdateInterest]  DEFAULT (0) FOR [UpdateInterest]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_ViewAccount]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_ViewAccount]  DEFAULT (0) FOR [ViewAccount]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_ViewAuditTrail]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_ViewAuditTrail]  DEFAULT (0) FOR [ViewAuditTrail]
GO;;
/****** Object:  Default [DF_LookupUserRoleCode_ViewPaymentTransaction]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[LookupUserRoleCode] ADD  CONSTRAINT [DF_LookupUserRoleCode_ViewPaymentTransaction]  DEFAULT (0) FOR [ViewPaymentTransaction]
GO;;
/****** Object:  Default [DF_MainframeImport_PayTransactionKey]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[MainframeImport] ADD  CONSTRAINT [DF_MainframeImport_PayTransactionKey]  DEFAULT (0) FOR [PayTransactionKey]
GO;;
/****** Object:  Default [DF_MainframeImport_ImportDate]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[MainframeImport] ADD  CONSTRAINT [DF_MainframeImport_ImportDate]  DEFAULT (getdate()) FOR [ImportDate]
GO;;
/****** Object:  Default [DF_MainframeImport_FileName]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[MainframeImport] ADD  CONSTRAINT [DF_MainframeImport_FileName]  DEFAULT ('New') FOR [FileName]
GO;;
/****** Object:  Default [DF_MainframeImport_ProcessingFlag]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[MainframeImport] ADD  CONSTRAINT [DF_MainframeImport_ProcessingFlag]  DEFAULT (0) FOR [ProcessingFlag]
GO;;
/****** Object:  Default [DF_MainframeImport_ErrorFlag]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[MainframeImport] ADD  CONSTRAINT [DF_MainframeImport_ErrorFlag]  DEFAULT (0) FOR [ErrorFlag]
GO;;
/****** Object:  Default [DF_MainframeImport_ACHFlag]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[MainframeImport] ADD  CONSTRAINT [DF_MainframeImport_ACHFlag]  DEFAULT (0) FOR [ACHFlag]
GO;;
/****** Object:  Default [DF_MainframeImport_SuspendedFlag]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[MainframeImport] ADD  CONSTRAINT [DF_MainframeImport_SuspendedFlag]  DEFAULT (0) FOR [SuspendedFlag]
GO;;
/****** Object:  Default [DF_MainframeImport_UnresolvedFlag]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[MainframeImport] ADD  CONSTRAINT [DF_MainframeImport_UnresolvedFlag]  DEFAULT (0) FOR [UnresolvedFlag]
GO;;
/****** Object:  Default [DF_MainframeImport_PostedPendingFlag]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[MainframeImport] ADD  CONSTRAINT [DF_MainframeImport_PostedPendingFlag]  DEFAULT (0) FOR [PostedPendingFlag]
GO;;
/****** Object:  Default [DF_MainframeImport_ACHStatusChecked]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[MainframeImport] ADD  CONSTRAINT [DF_MainframeImport_ACHStatusChecked]  DEFAULT (0) FOR [ACHStatusChecked]
GO;;
/****** Object:  Default [DF__PaymentIn__Compu__64ECEE3F]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentInterestDetails] ADD  DEFAULT (getdate()) FOR [ComputedDate]
GO;;
/****** Object:  Default [DF_PaymentMoveTransaction_SCMTotPayd]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentMoveTransaction] ADD  CONSTRAINT [DF_PaymentMoveTransaction_SCMTotPayd]  DEFAULT (0) FOR [SCMTotPayd]
GO;;
/****** Object:  Default [DF_PaymentMoveTransaction_SCMTotPayr]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentMoveTransaction] ADD  CONSTRAINT [DF_PaymentMoveTransaction_SCMTotPayr]  DEFAULT (0) FOR [SCMTotPayr]
GO;;
/****** Object:  Default [DF_PaymentMoveTransaction_SCMTotPayn]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentMoveTransaction] ADD  CONSTRAINT [DF_PaymentMoveTransaction_SCMTotPayn]  DEFAULT (0) FOR [SCMTotPayn]
GO;;
/****** Object:  Default [DF_PaymentMoveTransaction_SCMTotPayvr]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentMoveTransaction] ADD  CONSTRAINT [DF_PaymentMoveTransaction_SCMTotPayvr]  DEFAULT (0) FOR [SCMTotPayvr]
GO;;
/****** Object:  Default [DF_PaymentMoveTransaction_SCMTotPayfers]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentMoveTransaction] ADD  CONSTRAINT [DF_PaymentMoveTransaction_SCMTotPayfers]  DEFAULT (0) FOR [SCMTotPayfers]
GO;;
/****** Object:  Default [DF_PaymentMoveTransaction_ModificationDate]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentMoveTransaction] ADD  CONSTRAINT [DF_PaymentMoveTransaction_ModificationDate]  DEFAULT (getdate()) FOR [ModificationDate]
GO;;
/****** Object:  Default [DF_PaymentMoveTransaction_Approved]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentMoveTransaction] ADD  CONSTRAINT [DF_PaymentMoveTransaction_Approved]  DEFAULT (0) FOR [Approved]
GO;;
/****** Object:  Default [DF_PaymentMoveTransaction_Disapproved]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentMoveTransaction] ADD  CONSTRAINT [DF_PaymentMoveTransaction_Disapproved]  DEFAULT (0) FOR [Disapproved]
GO;;
/****** Object:  Default [DF_PaymentMoveTransaction_Modified]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentMoveTransaction] ADD  CONSTRAINT [DF_PaymentMoveTransaction_Modified]  DEFAULT (0) FOR [Modified]
GO;;
/****** Object:  Default [DF_PaymentTransaction_PayTransStatus]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransaction] ADD  CONSTRAINT [DF_PaymentTransaction_PayTransStatus]  DEFAULT (0) FOR [PayTransStatusCode]
GO;;
/****** Object:  Default [DF_PaymentTransaction_PayTransStatusDate]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransaction] ADD  CONSTRAINT [DF_PaymentTransaction_PayTransStatusDate]  DEFAULT (getdate()) FOR [PayTransStatusDate]
GO;;
/****** Object:  Default [DF_PaymentTransaction_TechnicianID]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransaction] ADD  CONSTRAINT [DF_PaymentTransaction_TechnicianID]  DEFAULT (0) FOR [TechnicianUserKey]
GO;;
/****** Object:  Default [DF_PaymentTransaction_PaymentAppliedOrderCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransaction] ADD  CONSTRAINT [DF_PaymentTransaction_PaymentAppliedOrderCode]  DEFAULT (1) FOR [PaymentAppliedOrderCode]
GO;;
/****** Object:  Default [DF_PaymentTransaction_PostFlag]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransaction] ADD  CONSTRAINT [DF_PaymentTransaction_PostFlag]  DEFAULT (0) FOR [PostFlag]
GO;;
/****** Object:  Default [DF_PaymentTransaction_UserInserted]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransaction] ADD  CONSTRAINT [DF_PaymentTransaction_UserInserted]  DEFAULT (0) FOR [UserInserted]
GO;;
/****** Object:  Default [DF_PaymentTransaction_ACHPayment]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransaction] ADD  CONSTRAINT [DF_PaymentTransaction_ACHPayment]  DEFAULT (0) FOR [ACHPayment]
GO;;
/****** Object:  Default [DF_PaymentTransaction_ResolvedSuspense]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransaction] ADD  CONSTRAINT [DF_PaymentTransaction_ResolvedSuspense]  DEFAULT (0) FOR [ResolvedSuspense]
GO;;
/****** Object:  Default [DF_PaymentTransaction_HistoryPayment]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransaction] ADD  CONSTRAINT [DF_PaymentTransaction_HistoryPayment]  DEFAULT (0) FOR [HistoryPayment]
GO;;
/****** Object:  Default [DF_PaymentTransaction_Disapprove]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransaction] ADD  CONSTRAINT [DF_PaymentTransaction_Disapprove]  DEFAULT (0) FOR [Disapprove]
GO;;
/****** Object:  Default [DF_PaymentTransaction_GovRefund]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransaction] ADD  CONSTRAINT [DF_PaymentTransaction_GovRefund]  DEFAULT (0) FOR [GovRefund]
GO;;
/****** Object:  Default [DF_PaymentTransaction_Apply2GL]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransaction] ADD  CONSTRAINT [DF_PaymentTransaction_Apply2GL]  DEFAULT (0) FOR [Apply2GL]
GO;;
/****** Object:  Default [DF_PaymentTransactionAudit_PostFlag]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransactionAudit] ADD  CONSTRAINT [DF_PaymentTransactionAudit_PostFlag]  DEFAULT (0) FOR [PostFlag]
GO;;
/****** Object:  Default [DF_PaymentTransactionAudit_UserInserted]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransactionAudit] ADD  CONSTRAINT [DF_PaymentTransactionAudit_UserInserted]  DEFAULT (0) FOR [UserInserted]
GO;;
/****** Object:  Default [DF_PaymentTransactionAudit_ResolvedSuspense]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransactionAudit] ADD  CONSTRAINT [DF_PaymentTransactionAudit_ResolvedSuspense]  DEFAULT (0) FOR [ResolvedSuspense]
GO;;
/****** Object:  Default [DF_PaymentTransactionAudit_HistoryPayment]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransactionAudit] ADD  CONSTRAINT [DF_PaymentTransactionAudit_HistoryPayment]  DEFAULT (0) FOR [HistoryPayment]
GO;;
/****** Object:  Default [DF_PaymentTransactionAudit_GovRefund]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTransactionAudit] ADD  CONSTRAINT [DF_PaymentTransactionAudit_GovRefund]  DEFAULT (0) FOR [GovRefund]
GO;;
/****** Object:  Default [DF_RefundTransaction_RefundAmount]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[RefundTransaction] ADD  CONSTRAINT [DF_RefundTransaction_RefundAmount]  DEFAULT (0) FOR [RefundAmount]
GO;;
/****** Object:  Default [DF_RefundTransaction_SCMClaimnumber]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[RefundTransaction] ADD  CONSTRAINT [DF_RefundTransaction_SCMClaimnumber]  DEFAULT (1111111) FOR [SCMClaimnumber]
GO;;
/****** Object:  Default [DF_RefundTransaction_RefundTransactionDate]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[RefundTransaction] ADD  CONSTRAINT [DF_RefundTransaction_RefundTransactionDate]  DEFAULT (getdate()) FOR [RefundTransactionDate]
GO;;
/****** Object:  Default [DF_RefundTransaction_TechnicianUserKey]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[RefundTransaction] ADD  CONSTRAINT [DF_RefundTransaction_TechnicianUserKey]  DEFAULT (0) FOR [TechnicianUserKey]
GO;;
/****** Object:  Default [DF_ServiceCreditMaster_SCMClaimNumber]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServiceCreditMaster] ADD  CONSTRAINT [DF_ServiceCreditMaster_SCMClaimNumber]  DEFAULT (0) FOR [SCMClaimNumber]
GO;;
/****** Object:  Default [DF_ServiceCreditMaster_SCMName]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServiceCreditMaster] ADD  CONSTRAINT [DF_ServiceCreditMaster_SCMName]  DEFAULT (0) FOR [SCMName]
GO;;
/****** Object:  Default [DF_ServiceCreditMaster_SCMLastAct]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServiceCreditMaster] ADD  CONSTRAINT [DF_ServiceCreditMaster_SCMLastAct]  DEFAULT (getdate()) FOR [SCMLastAct]
GO;;
/****** Object:  Default [DF_ServiceCreditMaster_SCMRetirementTypeCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServiceCreditMaster] ADD  CONSTRAINT [DF_ServiceCreditMaster_SCMRetirementTypeCode]  DEFAULT (0) FOR [RetirementTypeCode]
GO;;
/****** Object:  Default [DF_ServiceCreditMaster_AccountStatus]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServiceCreditMaster] ADD  CONSTRAINT [DF_ServiceCreditMaster_AccountStatus]  DEFAULT (0) FOR [AccountStatus]
GO;;
/****** Object:  Default [DF_ServiceCreditMaster_PaymentOrder]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServiceCreditMaster] ADD  CONSTRAINT [DF_ServiceCreditMaster_PaymentOrder]  DEFAULT ('12345') FOR [PaymentOrder]
GO;;
/****** Object:  Default [DF_ServiceCreditMaster_StopACHPayment]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServiceCreditMaster] ADD  CONSTRAINT [DF_ServiceCreditMaster_StopACHPayment]  DEFAULT (0) FOR [StopACHPayment]
GO;;
/****** Object:  Default [DF_ServiceCreditMaster_DBTSAccount]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServiceCreditMaster] ADD  CONSTRAINT [DF_ServiceCreditMaster_DBTSAccount]  DEFAULT (0) FOR [DBTSAccount]
GO;;
/****** Object:  Default [DF_ServiceCreditMasterAudit_SCMName]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServiceCreditMasterAudit] ADD  CONSTRAINT [DF_ServiceCreditMasterAudit_SCMName]  DEFAULT (0) FOR [SCMName]
GO;;
/****** Object:  Default [DF_ServiceCreditMasterAudit_RetirementTypeCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServiceCreditMasterAudit] ADD  CONSTRAINT [DF_ServiceCreditMasterAudit_RetirementTypeCode]  DEFAULT (0) FOR [RetirementTypeCode]
GO;;
/****** Object:  Default [DF_ServiceCreditMasterAudit_AccountStatus]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServiceCreditMasterAudit] ADD  CONSTRAINT [DF_ServiceCreditMasterAudit_AccountStatus]  DEFAULT (0) FOR [AccountStatus]
GO;;
/****** Object:  Default [DF_ServiceCreditMasterAudit_StopACHPayment]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServiceCreditMasterAudit] ADD  CONSTRAINT [DF_ServiceCreditMasterAudit_StopACHPayment]  DEFAULT (0) FOR [StopACHPayment]
GO;;
/****** Object:  Default [DF_ServicePeriods_SCMVersion]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods] ADD  CONSTRAINT [DF_ServicePeriods_SCMVersion]  DEFAULT (0) FOR [SCMVersion]
GO;;
/****** Object:  Default [DF_ServicePeriods_SCMLineNumber]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods] ADD  CONSTRAINT [DF_ServicePeriods_SCMLineNumber]  DEFAULT (0) FOR [SCMLineNumber]
GO;;
/****** Object:  Default [DF_ServicePeriods_SCMAgencyCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods] ADD  CONSTRAINT [DF_ServicePeriods_SCMAgencyCode]  DEFAULT (0) FOR [SCMAgencyCode]
GO;;
/****** Object:  Default [DF_ServicePeriods_SCMEnteredAmount]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods] ADD  CONSTRAINT [DF_ServicePeriods_SCMEnteredAmount]  DEFAULT (0) FOR [SCMEnteredAmount]
GO;;
/****** Object:  Default [DF_ServicePeriods_SCMHoursInYear]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods] ADD  CONSTRAINT [DF_ServicePeriods_SCMHoursInYear]  DEFAULT (0) FOR [SCMHoursInYear]
GO;;
/****** Object:  Default [DF_ServicePeriods_SCMAnnualizedAmount]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods] ADD  CONSTRAINT [DF_ServicePeriods_SCMAnnualizedAmount]  DEFAULT (0) FOR [SCMAnnualizedAmount]
GO;;
/****** Object:  Default [DF_ServicePeriods_SCMDateEntered]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods] ADD  CONSTRAINT [DF_ServicePeriods_SCMDateEntered]  DEFAULT (getdate()) FOR [SCMDateEntered]
GO;;
/****** Object:  Default [DF_ServicePeriods_SCMEnteredBy]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods] ADD  CONSTRAINT [DF_ServicePeriods_SCMEnteredBy]  DEFAULT (0) FOR [SCMEnteredBy]
GO;;
/****** Object:  Default [DF_StoredProcedureReturnCodes_ErrorNumber]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[StoredProcedureReturnCodes] ADD  CONSTRAINT [DF_StoredProcedureReturnCodes_ErrorNumber]  DEFAULT (0) FOR [ErrorNumber]
GO;;
/****** Object:  Default [DF_StoredProcPermissions_ServiceCredit]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[StoredProcPermissions] ADD  CONSTRAINT [DF_StoredProcPermissions_ServiceCredit]  DEFAULT (0) FOR [ServiceCredit]
GO;;
/****** Object:  Default [DF_StoredProcPermissions_SC_Batch]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[StoredProcPermissions] ADD  CONSTRAINT [DF_StoredProcPermissions_SC_Batch]  DEFAULT (0) FOR [SC_Batch]
GO;;
/****** Object:  Default [DF_StoredProcPermissions_SC_Close]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[StoredProcPermissions] ADD  CONSTRAINT [DF_StoredProcPermissions_SC_Close]  DEFAULT (0) FOR [SC_Close]
GO;;
/****** Object:  Default [DF_UserAccountAssignments_AssignmentDate]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[UserAccountAssignments] ADD  CONSTRAINT [DF_UserAccountAssignments_AssignmentDate]  DEFAULT (getdate()) FOR [AssignmentDate]
GO;;
/****** Object:  Default [DF_Users_UserStatusCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_Users_UserStatusCode]  DEFAULT (0) FOR [UserStatusCode]
GO;;
/****** Object:  ForeignKey [FK_AdjustmentTransaction_ServiceCreditMaster]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AdjustmentTransaction]  WITH NOCHECK ADD  CONSTRAINT [FK_AdjustmentTransaction_ServiceCreditMaster] FOREIGN KEY([SCMClaimnumber])
REFERENCES [dbo].[ServiceCreditMaster] ([SCMClaimNumber])
GO;;
ALTER TABLE [dbo].[AdjustmentTransaction] CHECK CONSTRAINT [FK_AdjustmentTransaction_ServiceCreditMaster]
GO;;
/****** Object:  ForeignKey [FK_AdjustmentTransaction_UsersManager]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AdjustmentTransaction]  WITH NOCHECK ADD  CONSTRAINT [FK_AdjustmentTransaction_UsersManager] FOREIGN KEY([ManagerUserKey])
REFERENCES [dbo].[Users] ([UserKey])
NOT FOR REPLICATION
GO;;
ALTER TABLE [dbo].[AdjustmentTransaction] CHECK CONSTRAINT [FK_AdjustmentTransaction_UsersManager]
GO;;
/****** Object:  ForeignKey [FK_AdjustmentTransaction_UsersTechnician]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[AdjustmentTransaction]  WITH NOCHECK ADD  CONSTRAINT [FK_AdjustmentTransaction_UsersTechnician] FOREIGN KEY([TechnicianUserKey])
REFERENCES [dbo].[Users] ([UserKey])
GO;;
ALTER TABLE [dbo].[AdjustmentTransaction] CHECK CONSTRAINT [FK_AdjustmentTransaction_UsersTechnician]
GO;;
/****** Object:  ForeignKey [FK_BalanceExceptions_ServiceCreditMaster]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BalanceExceptions]  WITH NOCHECK ADD  CONSTRAINT [FK_BalanceExceptions_ServiceCreditMaster] FOREIGN KEY([SCMClaimNumber])
REFERENCES [dbo].[ServiceCreditMaster] ([SCMClaimNumber])
GO;;
ALTER TABLE [dbo].[BalanceExceptions] NOCHECK CONSTRAINT [FK_BalanceExceptions_ServiceCreditMaster]
GO;;
/****** Object:  ForeignKey [FK_BatchDailyPayments_AuditBatch]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BatchDailyPayments]  WITH NOCHECK ADD  CONSTRAINT [FK_BatchDailyPayments_AuditBatch] FOREIGN KEY([AuditBatchIDLog])
REFERENCES [dbo].[AuditBatch] ([IDLog])
GO;;
ALTER TABLE [dbo].[BatchDailyPayments] CHECK CONSTRAINT [FK_BatchDailyPayments_AuditBatch]
GO;;
/****** Object:  ForeignKey [FK_BatchImportErrorLog_AuditBatch]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BatchImportErrorLog]  WITH NOCHECK ADD  CONSTRAINT [FK_BatchImportErrorLog_AuditBatch] FOREIGN KEY([AuditBatchIDLog])
REFERENCES [dbo].[AuditBatch] ([IDLog])
GO;;
ALTER TABLE [dbo].[BatchImportErrorLog] CHECK CONSTRAINT [FK_BatchImportErrorLog_AuditBatch]
GO;;
/****** Object:  ForeignKey [FK_BatchProcessingErrorLog_AuditBatch]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[BatchProcessingErrorLog]  WITH NOCHECK ADD  CONSTRAINT [FK_BatchProcessingErrorLog_AuditBatch] FOREIGN KEY([AuditBatchIDLog])
REFERENCES [dbo].[AuditBatch] ([IDLog])
GO;;
ALTER TABLE [dbo].[BatchProcessingErrorLog] CHECK CONSTRAINT [FK_BatchProcessingErrorLog_AuditBatch]
GO;;
/****** Object:  ForeignKey [FK_Invoices_PaymentTransaction]    Script Date: 10/11/2012 10:59:13 ******/
ALTER TABLE [dbo].[Invoices]  WITH CHECK ADD  CONSTRAINT [FK_Invoices_PaymentTransaction] FOREIGN KEY([PayTransactionKey])
REFERENCES [dbo].[PaymentTransaction] ([PayTransactionKey])
GO;;
ALTER TABLE [dbo].[Invoices] CHECK CONSTRAINT [FK_Invoices_PaymentTransaction]
GO;;
/****** Object:  ForeignKey [FK_MainframeImport_AuditBatch]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[MainframeImport]  WITH NOCHECK ADD  CONSTRAINT [FK_MainframeImport_AuditBatch] FOREIGN KEY([AuditBatchIDLog])
REFERENCES [dbo].[AuditBatch] ([IDLog])
GO;;
ALTER TABLE [dbo].[MainframeImport] CHECK CONSTRAINT [FK_MainframeImport_AuditBatch]
GO;;
/****** Object:  ForeignKey [FK_PaymentMoveTransaction_ServiceCreditMaster]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentMoveTransaction]  WITH NOCHECK ADD  CONSTRAINT [FK_PaymentMoveTransaction_ServiceCreditMaster] FOREIGN KEY([SCMClaimnumber])
REFERENCES [dbo].[ServiceCreditMaster] ([SCMClaimNumber])
GO;;
ALTER TABLE [dbo].[PaymentMoveTransaction] CHECK CONSTRAINT [FK_PaymentMoveTransaction_ServiceCreditMaster]
GO;;
/****** Object:  ForeignKey [FK_PaymentMoveTransaction_UsersManager]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentMoveTransaction]  WITH NOCHECK ADD  CONSTRAINT [FK_PaymentMoveTransaction_UsersManager] FOREIGN KEY([ManagerUserKey])
REFERENCES [dbo].[Users] ([UserKey])
NOT FOR REPLICATION
GO;;
ALTER TABLE [dbo].[PaymentMoveTransaction] CHECK CONSTRAINT [FK_PaymentMoveTransaction_UsersManager]
GO;;
/****** Object:  ForeignKey [FK_PaymentMoveTransaction_UsersTechnician]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentMoveTransaction]  WITH NOCHECK ADD  CONSTRAINT [FK_PaymentMoveTransaction_UsersTechnician] FOREIGN KEY([TechnicianUserKey])
REFERENCES [dbo].[Users] ([UserKey])
GO;;
ALTER TABLE [dbo].[PaymentMoveTransaction] CHECK CONSTRAINT [FK_PaymentMoveTransaction_UsersTechnician]
GO;;
/****** Object:  ForeignKey [FK_PaymentRefundLinks_PaymentTransaction]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentRefundLinks]  WITH CHECK ADD  CONSTRAINT [FK_PaymentRefundLinks_PaymentTransaction] FOREIGN KEY([PaymentNeedingRefund])
REFERENCES [dbo].[PaymentTransaction] ([PayTransactionKey])
GO;;
ALTER TABLE [dbo].[PaymentRefundLinks] CHECK CONSTRAINT [FK_PaymentRefundLinks_PaymentTransaction]
GO;;
/****** Object:  ForeignKey [FK_PaymentRefundLinks_PaymentTransaction1]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentRefundLinks]  WITH CHECK ADD  CONSTRAINT [FK_PaymentRefundLinks_PaymentTransaction1] FOREIGN KEY([RefundForPayment])
REFERENCES [dbo].[PaymentTransaction] ([PayTransactionKey])
GO;;
ALTER TABLE [dbo].[PaymentRefundLinks] CHECK CONSTRAINT [FK_PaymentRefundLinks_PaymentTransaction1]
GO;;
/****** Object:  ForeignKey [FK_PaymentTranactionNotes_PaymentTransaction]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[PaymentTranactionNotes]  WITH CHECK ADD  CONSTRAINT [FK_PaymentTranactionNotes_PaymentTransaction] FOREIGN KEY([PayTransactionKey])
REFERENCES [dbo].[PaymentTransaction] ([PayTransactionKey])
ON UPDATE CASCADE
ON DELETE CASCADE
GO;;
ALTER TABLE [dbo].[PaymentTranactionNotes] CHECK CONSTRAINT [FK_PaymentTranactionNotes_PaymentTransaction]
GO;;
/****** Object:  ForeignKey [FK_RefundTransaction_PaymentTransaction]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[RefundTransaction]  WITH CHECK ADD  CONSTRAINT [FK_RefundTransaction_PaymentTransaction] FOREIGN KEY([PayTransactionKey])
REFERENCES [dbo].[PaymentTransaction] ([PayTransactionKey])
GO;;
ALTER TABLE [dbo].[RefundTransaction] CHECK CONSTRAINT [FK_RefundTransaction_PaymentTransaction]
GO;;
/****** Object:  ForeignKey [FK_ServicePeriods_LookupAgencyCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods]  WITH NOCHECK ADD  CONSTRAINT [FK_ServicePeriods_LookupAgencyCode] FOREIGN KEY([SCMAgencyCode])
REFERENCES [dbo].[LookupAgencyCode] ([SCMAgencyCode])
ON UPDATE CASCADE
NOT FOR REPLICATION
GO;;
ALTER TABLE [dbo].[ServicePeriods] CHECK CONSTRAINT [FK_ServicePeriods_LookupAgencyCode]
GO;;
/****** Object:  ForeignKey [FK_ServicePeriods_LookupAppointmentTypeCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods]  WITH NOCHECK ADD  CONSTRAINT [FK_ServicePeriods_LookupAppointmentTypeCode] FOREIGN KEY([SCMAppointmentTypeCode])
REFERENCES [dbo].[LookupAppointmentTypeCode] ([SCMAppointmentTypeCode])
ON UPDATE CASCADE
NOT FOR REPLICATION
GO;;
ALTER TABLE [dbo].[ServicePeriods] CHECK CONSTRAINT [FK_ServicePeriods_LookupAppointmentTypeCode]
GO;;
/****** Object:  ForeignKey [FK_ServicePeriods_LookupPayTypeCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods]  WITH NOCHECK ADD  CONSTRAINT [FK_ServicePeriods_LookupPayTypeCode] FOREIGN KEY([SCMPayTypeCode])
REFERENCES [dbo].[LookupPayTypeCode] ([PayTypeCode])
ON UPDATE CASCADE
NOT FOR REPLICATION
GO;;
ALTER TABLE [dbo].[ServicePeriods] CHECK CONSTRAINT [FK_ServicePeriods_LookupPayTypeCode]
GO;;
/****** Object:  ForeignKey [FK_ServicePeriods_LookupPeriodTypeCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods]  WITH NOCHECK ADD  CONSTRAINT [FK_ServicePeriods_LookupPeriodTypeCode] FOREIGN KEY([SCMPeriodType])
REFERENCES [dbo].[LookupPeriodTypeCode] ([SCMPeriodTypeCode])
ON UPDATE CASCADE
NOT FOR REPLICATION
GO;;
ALTER TABLE [dbo].[ServicePeriods] CHECK CONSTRAINT [FK_ServicePeriods_LookupPeriodTypeCode]
GO;;
/****** Object:  ForeignKey [FK_ServicePeriods_LookupRetirementTypeCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods]  WITH NOCHECK ADD  CONSTRAINT [FK_ServicePeriods_LookupRetirementTypeCode] FOREIGN KEY([SCMRetirementTypeCode])
REFERENCES [dbo].[LookupRetirementTypeCode] ([SCMRetirementTypeCode])
NOT FOR REPLICATION
GO;;
ALTER TABLE [dbo].[ServicePeriods] CHECK CONSTRAINT [FK_ServicePeriods_LookupRetirementTypeCode]
GO;;
/****** Object:  ForeignKey [FK_ServicePeriods_ServiceCreditMaster]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriods]  WITH NOCHECK ADD  CONSTRAINT [FK_ServicePeriods_ServiceCreditMaster] FOREIGN KEY([SCMClaimNumber])
REFERENCES [dbo].[ServiceCreditMaster] ([SCMClaimNumber])
ON UPDATE CASCADE
ON DELETE CASCADE
NOT FOR REPLICATION
GO;;
ALTER TABLE [dbo].[ServicePeriods] CHECK CONSTRAINT [FK_ServicePeriods_ServiceCreditMaster]
GO;;
/****** Object:  ForeignKey [FK_ServicePeriodsAudit_LookupAuditActivity]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[ServicePeriodsAudit]  WITH NOCHECK ADD  CONSTRAINT [FK_ServicePeriodsAudit_LookupAuditActivity] FOREIGN KEY([AuditActivity])
REFERENCES [dbo].[LookupAuditActivity] ([AuditActivity])
ON UPDATE CASCADE
NOT FOR REPLICATION
GO;;
ALTER TABLE [dbo].[ServicePeriodsAudit] CHECK CONSTRAINT [FK_ServicePeriodsAudit_LookupAuditActivity]
GO;;
/****** Object:  ForeignKey [FK_SupervisorRoles_LookupSuperUserRoleCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[SupervisorRoles]  WITH NOCHECK ADD  CONSTRAINT [FK_SupervisorRoles_LookupSuperUserRoleCode] FOREIGN KEY([SupervisorRoleCode])
REFERENCES [dbo].[LookupUserRoleCode] ([UserRoleCode])
GO;;
ALTER TABLE [dbo].[SupervisorRoles] CHECK CONSTRAINT [FK_SupervisorRoles_LookupSuperUserRoleCode]
GO;;
/****** Object:  ForeignKey [FK_SupervisorRoles_LookupUserRoleCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[SupervisorRoles]  WITH NOCHECK ADD  CONSTRAINT [FK_SupervisorRoles_LookupUserRoleCode] FOREIGN KEY([UserRoleCode])
REFERENCES [dbo].[LookupUserRoleCode] ([UserRoleCode])
GO;;
ALTER TABLE [dbo].[SupervisorRoles] CHECK CONSTRAINT [FK_SupervisorRoles_LookupUserRoleCode]
GO;;
/****** Object:  ForeignKey [FK_UserRoleAssignment_LookupUserRoleCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[UserRoleAssignment]  WITH NOCHECK ADD  CONSTRAINT [FK_UserRoleAssignment_LookupUserRoleCode] FOREIGN KEY([UserRoleCode])
REFERENCES [dbo].[LookupUserRoleCode] ([UserRoleCode])
ON UPDATE CASCADE
ON DELETE CASCADE
NOT FOR REPLICATION
GO;;
ALTER TABLE [dbo].[UserRoleAssignment] CHECK CONSTRAINT [FK_UserRoleAssignment_LookupUserRoleCode]
GO;;
/****** Object:  ForeignKey [FK_UserRoleAssignment_Users]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[UserRoleAssignment]  WITH NOCHECK ADD  CONSTRAINT [FK_UserRoleAssignment_Users] FOREIGN KEY([UserKey])
REFERENCES [dbo].[Users] ([UserKey])
ON DELETE CASCADE
NOT FOR REPLICATION
GO;;
ALTER TABLE [dbo].[UserRoleAssignment] CHECK CONSTRAINT [FK_UserRoleAssignment_Users]
GO;;
/****** Object:  ForeignKey [FK_Users_LookupUserStatusCode]    Script Date: 10/11/2012 10:59:14 ******/
ALTER TABLE [dbo].[Users]  WITH NOCHECK ADD  CONSTRAINT [FK_Users_LookupUserStatusCode] FOREIGN KEY([UserStatusCode])
REFERENCES [dbo].[LookupUserStatusCode] ([UserStatusCode])
ON UPDATE CASCADE
NOT FOR REPLICATION
GO;;
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_LookupUserStatusCode]
GO;;
