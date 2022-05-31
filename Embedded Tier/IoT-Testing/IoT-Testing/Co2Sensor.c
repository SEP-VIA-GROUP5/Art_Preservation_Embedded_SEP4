/*
 * Co2Sensor.c
 *
 * Created: 05/24/22 3:47:14 PM
 *  Author: rytis
 */ 


#include <FreeRTOS.h>
#include <task.h>
#include <stdio.h>
#include <event_groups.h>
#include "Setup.h"
#include "Co2Sensor.h"

void myCo2CallBack(uint16_t ppm);

uint16_t co2Ppm = 0;

//Gets CO2 data
uint16_t getCo2() 
{
	return co2Ppm;
}



//Task for handling measurements when Application asks
void Co2Task()
{
	while(1){
		
		//EventGroup waits until Application sends MEASURE_BIT and tells each of the sensors to start measuring the metrics
		EventBits_t eventBits = xEventGroupWaitBits(measureEventGroup,CO2_MEASURE_BIT,pdTRUE,pdTRUE,portMAX_DELAY);
		
		//Verifies if the MEASURE_BIT has CO2_MEASURE_BIT included
		if(eventBits & (CO2_MEASURE_BIT)){
			printf("Measuring CO2... \n");
			
			//If yes, then the driver starts measuring the CO2
			myCo2CallBack(50);
			
			/*Event Group sets the CO2_READY_BIT - When all 'SENSOR'_READY_BIT's are set, 
			then the application sets the metrics and sends the payload_package.*/
			//***See Application class***
			xEventGroupSetBits(dataReadyEventGroup,CO2_READY_BIT);
			
			//Task is delayed with 10 milliseconds.
			vTaskDelay(pdMS_TO_TICKS(10));
		}
	}
}

//Callback to set the CO2 data
void myCo2CallBack(uint16_t ppm)
{
	co2Ppm = ppm;
}



//creates the Co2Task which is used in main.c class
void createCo2Task()
{
	while (1)
	{
		Co2Task();
	}
}