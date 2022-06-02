USE [sep4]
GO

/****** Object:  Table [edw].[metrics]    Script Date: 02/06/2022 00.44.34 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [edw].[metrics](
	[id] [bigint] NOT NULL,
	[time] [datetime] NULL,
	[co2_id] [bigint] NULL,
	[humidity_id] [bigint] NULL,
	[room] [bigint] NULL,
	[temperature_id] [bigint] NULL,
	[TRIAL157] [char](1) NULL,
 CONSTRAINT [metrics_pkey] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [edw].[metrics]  WITH CHECK ADD  CONSTRAINT [fk4c78wny2xgpk3ne0as2ac3peh] FOREIGN KEY([temperature_id])
REFERENCES [edw].[temperature] ([id])
GO

ALTER TABLE [edw].[metrics] CHECK CONSTRAINT [fk4c78wny2xgpk3ne0as2ac3peh]
GO

ALTER TABLE [edw].[metrics]  WITH CHECK ADD  CONSTRAINT [fk6vvyel7jg8jpfqtwecjx9whby] FOREIGN KEY([humidity_id])
REFERENCES [edw].[humidity] ([id])
GO

ALTER TABLE [edw].[metrics] CHECK CONSTRAINT [fk6vvyel7jg8jpfqtwecjx9whby]
GO

ALTER TABLE [edw].[metrics]  WITH CHECK ADD  CONSTRAINT [fkd6qar925rl31oxn91yd600fbd] FOREIGN KEY([room])
REFERENCES [edw].[room] ([id])
GO

ALTER TABLE [edw].[metrics] CHECK CONSTRAINT [fkd6qar925rl31oxn91yd600fbd]
GO

ALTER TABLE [edw].[metrics]  WITH CHECK ADD  CONSTRAINT [fkkcfvyukh6v1krh8acn8majmh8] FOREIGN KEY([co2_id])
REFERENCES [edw].[co2] ([id])
GO

ALTER TABLE [edw].[metrics] CHECK CONSTRAINT [fkkcfvyukh6v1krh8acn8majmh8]
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'TRIAL' , @level0type=N'SCHEMA',@level0name=N'edw', @level1type=N'TABLE',@level1name=N'metrics', @level2type=N'COLUMN',@level2name=N'id'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'TRIAL' , @level0type=N'SCHEMA',@level0name=N'edw', @level1type=N'TABLE',@level1name=N'metrics', @level2type=N'COLUMN',@level2name=N'time'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'TRIAL' , @level0type=N'SCHEMA',@level0name=N'edw', @level1type=N'TABLE',@level1name=N'metrics', @level2type=N'COLUMN',@level2name=N'co2_id'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'TRIAL' , @level0type=N'SCHEMA',@level0name=N'edw', @level1type=N'TABLE',@level1name=N'metrics', @level2type=N'COLUMN',@level2name=N'humidity_id'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'TRIAL' , @level0type=N'SCHEMA',@level0name=N'edw', @level1type=N'TABLE',@level1name=N'metrics', @level2type=N'COLUMN',@level2name=N'room'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'TRIAL' , @level0type=N'SCHEMA',@level0name=N'edw', @level1type=N'TABLE',@level1name=N'metrics', @level2type=N'COLUMN',@level2name=N'temperature_id'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'TRIAL' , @level0type=N'SCHEMA',@level0name=N'edw', @level1type=N'TABLE',@level1name=N'metrics', @level2type=N'COLUMN',@level2name=N'TRIAL157'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'TRIAL' , @level0type=N'SCHEMA',@level0name=N'edw', @level1type=N'TABLE',@level1name=N'metrics'
GO


