

/****co2****/

SELECT count(*) as CountOnSource
FROM [sep4Testing].[edw].[co2]

SELECT count(*) as CountOnSource
FROM sep4.edw.metrics


/****temperature****/

SELECT count(*) as CountOnSource
FROM [sep4Testing].[edw].[temperature]

SELECT count(*) as CountOnSource
FROM sep4.edw.temperature

/**accuracy:check sum of temperature**/
SELECT SUM(temperature) as SumOfTemperature
FROM [sep4Testing].[edw].temperature

SELECT SUM(temperature) as SumOfTemperature
FROM [sep4].[edw].temperature


