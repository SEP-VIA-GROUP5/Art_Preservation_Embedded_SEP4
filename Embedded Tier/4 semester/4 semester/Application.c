#include "Application.h"

//method for receiving and setting the metrics
void ApplicationTask(void *pvParameters)
{
	for (;;)
	{
		//Application sends a request to each of the sensors to 
		xEventGroupSetBits(measureEventGroup, ALL_MEASURE_BITS);
		
		//EventGroup waits for all bits to be ready - when sensors finished measuring the metrics they send ready BITS back to the application
		EventBits_t eventBits = xEventGroupWaitBits(dataReadyEventGroup,ALL_READY_BITS,pdTRUE,pdTRUE,portMAX_DELAY);
		
		if(eventBits & (ALL_READY_BITS))
		{
			printf("ALL DATA COLLECTED\n");
			printf("Temperature is: %x,\n and humidity is: %x, \n CO2 is: %x \n",getTemperature(),getHumidity(),getCo2());
			
			//Data is collected and set for each of the metrics
			setTemperature(getTemperature());
			setHumidity(getHumidity());
			setCo2Ppm(getCo2());
			
			
			//When metrics are set, they are sent as a payload package to UpLinkHandler with a MessageBuffer.
			lora_driver_payload_t _uplink_payload = sensorDataPackageHandler_getLoRaPayload(2);
			xMessageBufferSend(upLinkMessageBuffer,&_uplink_payload,sizeof(_uplink_payload),portMAX_DELAY);
											
			//Application is delayed with 2 minutes until the next measurement starts.										
			vTaskDelay(pdMS_TO_TICKS(120000));
		}
		
		//Application with other 50 milliseconds until the next measurement starts.
		vTaskDelay(pdMS_TO_TICKS(50));
	}
}


//This method is used in main.c class - it creates the task for measuring the metrics and sends them through a payload package after
void createApplicationTask(UBaseType_t Taskpriority)
{
	xTaskCreate(
	ApplicationTask	//method
	,  "AppTask" //Name of method
	,  configMINIMAL_STACK_SIZE	//The size of the stack to configurate the method
	,  NULL // (void *pvParameters)
	,  tskIDLE_PRIORITY + Taskpriority //the priority of the task
	,  NULL ); //No TaskHandle created.
}