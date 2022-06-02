USE [sep4]
GO

/****** Object:  Table [Stage].[temperature]    Script Date: 02/06/2022 00.51.38 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [Stage].[temperature](
	[temperature_id] [int] NOT NULL,
	[temperature] [float] NULL,
	[max] [float] NULL,
 CONSTRAINT [PK_D_Temperature] PRIMARY KEY CLUSTERED 
(
	[temperature_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


