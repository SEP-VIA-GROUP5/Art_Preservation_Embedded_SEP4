#include "Setup.h"
EventGroupHandle_t measureEventGroup;
EventGroupHandle_t dataReadyEventGroup;
MessageBufferHandle_t upLinkMessageBuffer;
//MessageBufferHandle_t downLinkMessageBuffer;

void initializeEventGroup()
{
	dataReadyEventGroup = xEventGroupCreate();
	measureEventGroup = xEventGroupCreate();
}


void createUpLinkMessageBuffer()
{
	
	upLinkMessageBuffer = xMessageBufferCreate(sizeof(lora_driver_payload_t)*2);
	if(upLinkMessageBuffer == NULL )
	{
		printf("Not enough heap memory for uplink message buffer");
	}
	else
	{
		printf("Uplink message buffer created succesffully");
	}
}