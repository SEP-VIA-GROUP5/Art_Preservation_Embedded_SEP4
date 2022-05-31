/*
 * DownLinkHandler.c
 *
 * Created: 5/25/2022 11:02:04 AM
 *  Author: Lukas
 */ 

//A class responsible for receiving and handling data via WebSockets

#include <stdint.h>
#include <stdio.h>

#include <../GoogleTesting/FreeRTOS.h>
#include <../GoogleTesting/task.h>
#include <../GoogleTesting/message_buffer.h>
#include <../GoogleTesting/semphr.h>
#include "DownLinkHandler.h"
#include "Setup.h"
#include "Configuration.h"

lora_driver_payload_t lora_downlink_payload;


void setPayLoadLen(int length)
{
	lora_downlink_payload.len = length;
}
/*Function for setting the norm values
* If the configuration mutex (shared resource) is not taken by someone else then
* it takes it and sets the norm values received via WebSockets from data warehouse.
* After that it gives back the mutex
*/ 
void lora_downLink_task()
{
		xMessageBufferReceive(downLinkMessageBuffer, &lora_downlink_payload, sizeof(lora_driver_payload_t), portMAX_DELAY);
		if (lora_downlink_payload.len != 0)
		{
			if( xSemaphoreTake( configMutex, ( TickType_t ) 10 ) == pdTRUE )
			{
				printf("Mutex was taken");
				//setCo2Norm((lora_downlink_payload->bytes[0] << 8) + lora_downlink_payload->bytes[1]);
				//setHumNorm(lora_downlink_payload->bytes[2]);
				//setTempNorm((lora_downlink_payload->bytes[3] << 8) + lora_downlink_payload->bytes[4]);	
				
				xSemaphoreGive(configMutex);
			}
			else{
				printf("We could not access the shared resource: configMutex");
			}
			
		}

		vTaskDelay(pdMS_TO_TICKS(100));

}


//Function for down link handler task creation
void lora_downlink_handler_create()
{
	while (1)
	{
		lora_downLink_task();
	}
}
//lora_downlink_payload->portNo=2;
		//printf("Port number");
		//lora_downlink_payload->len=6;