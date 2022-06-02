/****** Script for SelectTopNRows command from SSMS  ******/
USE [sep4]
GO
/**** temperature****/

INSERT INTO [sep4].[temperature] ([id]
      ,[max]
      ,[temperature])
SELECT  [temeprature_id]
      ,[max]
      ,[temperature]
  FROM [Stage].[edw].[temperature]

  /**** co2****/
  INSERT INTO [sep4].[co2] ([id]
      ,[max]
      ,[co2])
SELECT  [co2_id]
      ,[max]
      ,[co2]
  FROM [Stage].[edw].[co2]

  /**** humidity****/

   INSERT INTO [sep4].[humidity] ([id]
      ,[max]
      ,[humidity])
SELECT  [humidity_id]
      ,[max]
      ,[humidity]
  FROM [Stage].[edw].[humidity]

  /**** room****/

   INSERT INTO [sep4].[room] ([id]
      ,[name]
      ,[number]
      )
SELECT  [room_id]
      ,[name]
      ,[number]
     
  FROM [Stage].[edw].[room]

  /**** metrics****/

   INSERT INTO [sep4].[metrics] ([id]
      ,[time]
      ,[co2_id]
      ,[humidity_id]
      ,[room_id]
      ,[temperature_id])
SELECT  [id]
      ,[time]
      ,[co2_id]
      ,[humidity_id]
      ,[room]
      ,[temperature_id]
  FROM [Stage].[edw].[metrics]
