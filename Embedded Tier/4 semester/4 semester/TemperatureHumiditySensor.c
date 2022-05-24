/*
 * TemperatureHumiditySensor.c
 *
 * Created: 5/5/2022 8:48:25 PM
 *  Author: titas
 */ 
#include "TemperatureHumiditySensor.h"

uint16_t temperature = 0;
uint16_t humidity = 0;

//Do we need const here?

uint16_t getHumidity()
{
	return humidity;
}

uint16_t getTemperature()
{
	return temperature;
}



void create(UBaseType_t Taskpriority)
{
	initializeTempAndHumDriver();
	createTempAndHumTask(Taskpriority);
	
}

void initializeTempAndHumDriver()
{
	hih8120_driverReturnCode_t returnCode = hih8120_initialise();

	if ( HIH8120_OK == returnCode )
	{
		printf("Temp and Hum Driver Initialized ok\n");
	}
	
	else {
		printf("TEMP AND HUM OUT OF HEAP \n");
	}
}

void measureTempAndHum()
{
	if ( HIH8120_OK != hih8120_wakeup() )
	{
		printf("TEMP AND HUM WAKE UP WENT WRONG\n");
	}
	
	vTaskDelay(pdMS_TO_TICKS(50));
	
	if ( HIH8120_OK !=  hih8120_measure() )
	{
		printf("TEMP AND HUM MEASURING UP WENT WRONG\n");
	}
	
	vTaskDelay(pdMS_TO_TICKS(20));
}

void TempAndHumTask(void* pvpParameter)
{
	
	while(1)
	{
		//Use it for later when we have both sensors = EventBits_t eventBits = xEventGroupWaitBits(measureEventGroup,ALL_MEASURE_BITS,pdTRUE,pdTRUE,portMAX_DELAY);
		EventBits_t eventBits = xEventGroupWaitBits(measureEventGroup,HUMIDITY_TEMPERATURE_MEASURE_BIT,pdTRUE,pdTRUE,portMAX_DELAY);
		if(eventBits & (HUMIDITY_TEMPERATURE_MEASURE_BIT))
		{
			puts("Measuring metrics...");
			measureTempAndHum();
			temperature = hih8120_getTemperature_x10();
			humidity = hih8120_getHumidityPercent_x10();
			//printf("Temperature: %d\n",Temp);
			//printf("Humidity: %d\n",Humidity);
			//Use it for later when we have both sensors = xEventGroupSetBits(dataReadyEventGroup,ALL_READY_BIT);
			xEventGroupSetBits(dataReadyEventGroup,HUMIDITY_TEMPERATURE_READY_BIT);
		}
		vTaskDelay(pdMS_TO_TICKS(10));
	}
}

void createTempAndHumTask(UBaseType_t Taskpriority)
{
	initializeTempAndHumDriver();
	xTaskCreate(
	TempAndHumTask
	,  "TempAndHumTask"
	,  configMINIMAL_STACK_SIZE
	,  NULL
	,  tskIDLE_PRIORITY + Taskpriority
	,  NULL );
}




