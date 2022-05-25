
/*
 * DownLinkHandler.c
 *
 * Created: 5/25/2022 11:02:04 AM
 *  Author: ljusk
 */ 

#include "DownlinkHandler.h"

lora_driver_payload_t lora_downlink_payload;


void lora_downLink_task()
{
	for(;;)
	{
		xMessageBufferReceive(downLinkMessageBuffer, &lora_downlink_payload, sizeof(lora_driver_payload_t), portMAX_DELAY);
		printf("DOWN LINK<<<<<: from port: %d with %d bytes received!",lora_downlink_payload.portNo, lora_downlink_payload.len);
		if (1 == lora_downlink_payload.len)
		{
			//if no one is using configuration
			if( xSemaphoreTake( configMutex, ( TickType_t ) 10 ) == pdTRUE )
			{
				setCo2Norm(lora_downlink_payload.bytes[0] + lora_downlink_payload.bytes[1]);
				setTempNorm(lora_downlink_payload.bytes[2] + lora_downlink_payload.bytes[3]);
				setTempNorm(lora_downlink_payload.bytes[4] + lora_downlink_payload.bytes[4]);	
			}
			//if configuration is taken by someone else
			else{
				printf("We could not access the shared resource: configMutex");
			}
			
		}

		vTaskDelay(pdMS_TO_TICKS(100));
	}

}


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