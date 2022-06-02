USE [sep4]
GO

/****** Object:  Table [Stage].[metrics]    Script Date: 02/06/2022 00.49.44 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [Stage].[metrics](
	[id] [int] NULL,
	[co2_id] [int] NULL,
	[humidity_id] [int] NULL,
	[temperature_id] [int] NULL,
	[room_id] [int] NULL,
	[Time] [datetime] NULL
) ON [PRIMARY]
GO


