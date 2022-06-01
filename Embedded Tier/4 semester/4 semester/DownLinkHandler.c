//A class responsible for receiving and handling data via WebSockets

#include "DownLinkHandler.h"

lora_driver_payload_t lora_downlink_payload;


/*Function for setting the norm values
* If the configuration mutex (shared resource) is not taken by someone else then
* it takes it and sets the norm values received via WebSockets from data warehouse.
* After that it gives back the mutex
*/
void lora_downLink_task()
{
	for(;;)
	{	
		xMessageBufferReceive(downLinkMessageBuffer, &lora_downlink_payload, sizeof(lora_driver_payload_t), portMAX_DELAY);
		printf("DOWN LINK<<<<<: from port: %d with %d bytes received!",lora_downlink_payload->portNo, lora_downlink_payload->len);
		if (lora_downlink_payload->len == 5)
		{
			if( xSemaphoreTake( configMutex, ( TickType_t ) 10 ) == pdTRUE )
			{		
				setCo2Norm((lora_downlink_payload.bytes[0]<<8) + (lora_downlink_payload.bytes[1]));
				setHumNorm(lora_downlink_payload.bytes[2]);
				setTempNorm((lora_downlink_payload.bytes[3]<<8) + (lora_downlink_payload.bytes[4])/10);
				printf("Received from downlink - CO2 norm: %d Hum norm: %d Temp norm: %d\n", getCo2Norm(), getHumNorm(), getTempNorm());
				xSemaphoreGive(configMutex);
			}
			else{
				printf("We could not access the shared resource: configMutex");
			}
			
		}

		vTaskDelay(pdMS_TO_TICKS(100));
	
	}

}


//Function for down link handler task creation
void lora_downlink_handler_create(UBaseType_t lora_handler_task_priority)
{
	xTaskCreate(
	lora_downLink_task,
	"lora_downlink"
	, configMINIMAL_STACK_SIZE
	, NULL
	,    tskIDLE_PRIORITY + lora_handler_task_priority
	, NULL );
}