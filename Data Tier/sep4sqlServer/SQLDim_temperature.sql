USE [sep4]
GO

/****** Object:  Table [edw].[temperature]    Script Date: 02/06/2022 00.47.25 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [edw].[temperature](
	[id] [bigint] NOT NULL,
	[max] [float] NULL,
	[temperature] [float] NOT NULL,
	[TRIAL167] [char](1) NULL,
 CONSTRAINT [temperature_pkey] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'TRIAL' , @level0type=N'SCHEMA',@level0name=N'edw', @level1type=N'TABLE',@level1name=N'temperature', @level2type=N'COLUMN',@level2name=N'id'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'TRIAL' , @level0type=N'SCHEMA',@level0name=N'edw', @level1type=N'TABLE',@level1name=N'temperature', @level2type=N'COLUMN',@level2name=N'max'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'TRIAL' , @level0type=N'SCHEMA',@level0name=N'edw', @level1type=N'TABLE',@level1name=N'temperature', @level2type=N'COLUMN',@level2name=N'temperature'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'TRIAL' , @level0type=N'SCHEMA',@level0name=N'edw', @level1type=N'TABLE',@level1name=N'temperature', @level2type=N'COLUMN',@level2name=N'TRIAL167'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'TRIAL' , @level0type=N'SCHEMA',@level0name=N'edw', @level1type=N'TABLE',@level1name=N'temperature'
GO


