USE [sep4Testing]
GO
/**** temperature****/

INSERT INTO [Stage].[temperature] ([temperature_id]
      ,[max]
      ,[temperature])
SELECT  [id]
      ,[max]
      ,[temperature]
  FROM [sep4Testing].[edw].[temperature]

  /**** co2****/
  INSERT INTO [Stage].[co2] ([co2_id]
      ,[max]
      ,[co2])
SELECT  [id]
      ,[max]
      ,[co2]
  FROM [sep4Testing].[edw].[co2]

  /**** humidity****/

   INSERT INTO [Stage].[humidity] ([humidity_id]
      ,[max]
      ,[humidity])
SELECT  [id]
      ,[max]
      ,[humidity]
  FROM [sep4Testing].[edw].[humidity]

  /**** room****/

   INSERT INTO [Stage].[room] ([room_id]
      ,[name]
      ,[number]
      )
SELECT  [id]
      ,[name]
      ,[number]
     
  FROM [sep4Testing].[edw].[room]

  /**** metrics****/

   INSERT INTO [Stage].[metrics] ([id]
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
  FROM [sep4Testing].[edw].[metrics]