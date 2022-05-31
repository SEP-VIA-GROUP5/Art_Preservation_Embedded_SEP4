#include <../GoogleTesting/FreeRTOS.h>
#include <../GoogleTesting/event_groups.h>
#include <../GoogleTesting/message_buffer.h>
#include "Setup.h"

lora_driver_payload_t* _uplink_payload;
//method for receiving and setting the metrics
void ApplicationTask()
{
		//Application sends a request to each of the sensors to 
		xEventGroupSetBits(measureEventGroup, ALL_MEASURE_BITS);
		
		//EventGroup waits for all bits to be ready - when sensors finished measuring the metrics they send ready BITS back to the application
		EventBits_t eventBits = xEventGroupWaitBits(dataReadyEventGroup,ALL_READY_BITS,pdTRUE,pdTRUE,portMAX_DELAY);
		
		if(eventBits & (ALL_READY_BITS))
		{
			//printf("ALL DATA COLLECTED\n");
			//printf("Temperature is: %x,\n and humidity is: %x, \n CO2 is: %x \n",getTemperature(),getHumidity(),getCo2());
			
			//Data is collected and set for each of the metrics
			//setTemperature(getTemperature());
			//setHumidity(getHumidity());
			//setCo2Ppm(getCo2());
			
			
			//When metrics are set, they are sent as a payload package to UpLinkHandler with a MessageBuffer.
			
			xMessageBufferSend(upLinkMessageBuffer,&_uplink_payload,sizeof(_uplink_payload),portMAX_DELAY);
											
			//Application is delayed with 2 minutes until the next measurement starts.										
			vTaskDelay(pdMS_TO_TICKS(120000));
		}
		
		//Application with other 50 milliseconds until the next measurement starts.
		vTaskDelay(pdMS_TO_TICKS(50));
}


//This method is used in main.c class - it creates the task for measuring the metrics and sends them through a payload package after
void createApplicationTask()
{
	while (1)
	{
		ApplicationTask();
	}
}