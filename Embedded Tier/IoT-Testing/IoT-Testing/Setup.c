#include <../GoogleTesting/FreeRTOS.h>
#include <../GoogleTesting/semphr.h>
#include <../GoogleTesting/queue.h>
#include <../GoogleTesting/event_groups.h>
#include <../GoogleTesting/message_buffer.h>
#include "Setup.h"

EventGroupHandle_t measureEventGroup;
EventGroupHandle_t dataReadyEventGroup;
MessageBufferHandle_t upLinkMessageBuffer;
MessageBufferHandle_t downLinkMessageBuffer;

void initializeEventGroup() //Initializing every event group
{
	dataReadyEventGroup = xEventGroupCreate();
	measureEventGroup = xEventGroupCreate();
}


void createUpLinkMessageBuffer() //Created a buffer for messages so it can be uploaded and send in websockets
{
	
	upLinkMessageBuffer = xMessageBufferCreate(sizeof(lora_driver_payload_t)*2);
	if(upLinkMessageBuffer == NULL)
	{
		printf("Not enough heap memory for uplink message buffer");
	}
	else
	{
		printf("Uplink message buffer created successfully");
	}
}

void createDownLinkMessageBuffer() //Created a buffer for messages so it can be received from websockets
{
	
	downLinkMessageBuffer = xMessageBufferCreate(sizeof(lora_driver_payload_t)*2);
	if(downLinkMessageBuffer == NULL)
	{
		printf("Not enough heap memory for downlink message buffer");
	}
	else
	{
		printf("Downlink message buffer created successfully");
	}
}