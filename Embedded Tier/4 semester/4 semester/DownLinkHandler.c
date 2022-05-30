/*
 * DownLinkHandler.c
 *
 * Created: 5/25/2022 11:02:04 AM
 *  Author: Lukas
 */ 

//A class responsible for receiving and handling data via WebSockets

#include "DownlinkHandler.h"

/*Function for setting the norm values
* If the configuration mutex (shared resource) is not taken by someone else then
* it takes it and sets the norm values received via WebSockets from data warehouse.
* After that it gives back the mutex
*/
void lora_downLink_task()
{
	lora_driver_payload_t* lora_downlink_payload;
	for(;;)
	{
		printf("<<<<<<<<<Before Lora driver pvp Port\n");
		lora_downlink_payload = pvPortMalloc(sizeof(lora_driver_payload_t));
		printf("<<<<<<<<<After Lora driver pvp Port\n");
	
		//lora_downlink_payload->portNo=2;
		//printf("Port number");
		//lora_downlink_payload->len=6;
		xMessageBufferReceive(downLinkMessageBuffer, &lora_downlink_payload, sizeof(lora_driver_payload_t), portMAX_DELAY);
		printf("<<<<<<<<<After buffer\n");
		printf("DOWN LINK<<<<<: from port: %d with %d bytes received!",lora_downlink_payload->portNo, lora_downlink_payload->len);
		if (lora_downlink_payload->len != 0)
		{
			if( xSemaphoreTake( configMutex, ( TickType_t ) 10 ) == pdTRUE )
			{
				printf("Mutex was taken");
				setCo2Norm(lora_downlink_payload->bytes[0] + lora_downlink_payload->bytes[1]);
				setHumNorm(lora_downlink_payload->bytes[2] + lora_downlink_payload->bytes[3]);
				setTempNorm(lora_downlink_payload->bytes[4] + lora_downlink_payload->bytes[4]);
				
				printf("The CO2: %d, humidity: %d, temperature: %d",getCo2Norm(),getHumNorm(),getTempNorm());	
				
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