USE [sep4]
GO

/****** Object:  Table [Stage].[humidity]    Script Date: 02/06/2022 00.48.58 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [Stage].[humidity](
	[humidity_id] [int] NOT NULL,
	[humidity] [float] NULL,
	[max] [float] NULL,
 CONSTRAINT [PK_humidity] PRIMARY KEY CLUSTERED 
(
	[humidity_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


