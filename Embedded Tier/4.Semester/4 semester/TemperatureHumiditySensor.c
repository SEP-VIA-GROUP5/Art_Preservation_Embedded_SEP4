/*
 * TemperatureHumiditySensor.c
 *
 * Created: 5/5/2022 8:48:25 PM
 *  Author: titas
 */ 
#include <hih8120.h>

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
		uint16_t Temp = 0;
		uint16_t Humidity = 0;
		if(xSemaphoreTake(tempHumSemaphore,portMAX_DELAY)==pdTRUE)
		{
			measureTempAndHum();
			Temp = hih8120_getTemperature_x10();
			Humidity = hih8120_getHumidityPercent_x10();
			//printf("Temperature: %d\n",Temp);
			//printf("Humidity: %d\n",Humidity);
			xQueueSend(sensorDataQueue,&Temp,portMAX_DELAY);
			xQueueSend(sensorDataQueue,&Humidity,portMAX_DELAY);

			xEventGroupSetBits(dataEventGroup,BIT_HUMIDITY_TEMPERATURE);

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




