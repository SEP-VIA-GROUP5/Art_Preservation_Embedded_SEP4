#include "Application.h"

void ApplicationTask(void *pvParameters)
{
	for (;;)
	{
		//if(ALL_READY_BITS == ALL_READY_BITS){
			//Use in the future when we have both sensors = xEventGroupSetBits(measureEventGroup, ALL_MEASURE_BITS);
			xEventGroupSetBits(measureEventGroup, ALL_MEASURE_BITS);
		//}
		
		//Use in the future when we have both sensors = EventBits_t eventBits = xEventGroupWaitBits(dataReadyEventGroup,ALL_READY_BITS,pdTRUE,pdTRUE,portMAX_DELAY);
		EventBits_t eventBits = xEventGroupWaitBits(dataReadyEventGroup,ALL_READY_BITS,pdTRUE,pdTRUE,portMAX_DELAY);
		if(eventBits & (ALL_READY_BITS))
		{
			printf("ALL DATA COLLECTED\n");
			printf("Temperature is: %x,\n and humidity is: %x, \n CO2 is: %x \n",getTemperature(),getHumidity(),getCo2());
			
			setTemperature(getTemperature());
			setHumidity(getHumidity());
			
			setCo2Ppm(getCo2());
			
			lora_driver_payload_t _uplink_payload = sensorDataPackageHandler_getLoRaPayload(2);
			xMessageBufferSend(upLinkMessageBuffer,&_uplink_payload,sizeof(_uplink_payload),portMAX_DELAY);
																					
			vTaskDelay(pdMS_TO_TICKS(120000));
			//xSemaphoreGive(tempHumSemaphore);
		}
		vTaskDelay(pdMS_TO_TICKS(50));
	}
}

void createApplicationTask(UBaseType_t Taskpriority)
{
	xTaskCreate(
	ApplicationTask
	,  "AppTask"
	,  configMINIMAL_STACK_SIZE
	,  NULL
	,  tskIDLE_PRIORITY + Taskpriority
	,  NULL );
}