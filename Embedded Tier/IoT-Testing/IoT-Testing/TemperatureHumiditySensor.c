//A class that handles temperature and humidity measuring.

 
#include "TemperatureHumiditySensor.h"
#include <FreeRTOS.h>
#include <task.h>
#include <stdio.h>
#include "Setup.h"
#include <event_groups.h>

//Initializing temperature and humidity as 0. 
uint16_t temperature = 0;
uint16_t humidity = 0;




/*
A function that wakes up the driver and measures temperature and humidity.
*/
void measureTempAndHum(uint16_t temperatureMeasured, uint16_t humidityMeasured)
{
	temperature = temperatureMeasured;
	humidity = humidityMeasured;
}

/*
 A function, that waits until Application asks for measurements through event group,
 When the measurements are needed, the measure function is called, temperature and humidity,
 are measured and temperature and humidty are set to those measurements. 
*/
void TempAndHumTask()
{
	
	while(1)
	{
		EventBits_t eventBits = xEventGroupWaitBits(measureEventGroup,HUMIDITY_TEMPERATURE_MEASURE_BIT,pdTRUE,pdTRUE,portMAX_DELAY);
		if(eventBits & (HUMIDITY_TEMPERATURE_MEASURE_BIT))
		{
			puts("Measuring metrics...");
			measureTempAndHum();
			temperature = hih8120_getTemperature_x10();
			humidity = hih8120_getHumidityPercent_x10();
			printf("Temperature: %d\n",temperature);
			//printf("Humidity: %d\n",Humidity);
			//Use it for later when we have both sensors = xEventGroupSetBits(dataReadyEventGroup,ALL_READY_BIT);
			xEventGroupSetBits(dataReadyEventGroup,HUMIDITY_TEMPERATURE_READY_BIT);
		}
		vTaskDelay(pdMS_TO_TICKS(10));
	}
}

// A function that creates temperature and humidity task.
void createTempAndHumTask()
{
	while (1)
	{
		TempAndHumTask();
	}
}




