#include "Application.h"

void ApplicationTask(void *pvParameters)
{
	for (;;)
	{
		//if(ALL_READY_BITS == ALL_READY_BITS){
			//Use in the future when we have both sensors = xEventGroupSetBits(measureEventGroup, ALL_MEASURE_BITS);
			xEventGroupSetBits(measureEventGroup, HUMIDITY_TEMPERATURE_MEASURE_BIT);
		//}
		
		//Use in the future when we have both sensors = EventBits_t eventBits = xEventGroupWaitBits(dataReadyEventGroup,ALL_READY_BITS,pdTRUE,pdTRUE,portMAX_DELAY);
		EventBits_t eventBits = xEventGroupWaitBits(dataReadyEventGroup,HUMIDITY_TEMPERATURE_READY_BIT,pdTRUE,pdTRUE,portMAX_DELAY);
		if((eventBits &(HUMIDITY_TEMPERATURE_READY_BIT))==(HUMIDITY_TEMPERATURE_READY_BIT))
		{
			printf("ALL DATA COLLECTED\n");
			printf("Temperature is: %x, and humidity is: %x\n",getTemperature(),getHumidity());
			
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