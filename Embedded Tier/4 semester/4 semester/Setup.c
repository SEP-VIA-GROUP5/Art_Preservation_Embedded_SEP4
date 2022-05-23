#include "Setup.h"
EventGroupHandle_t measureEventGroup;
EventGroupHandle_t dataReadyEventGroup;

void initializeEventGroup()
{
	dataReadyEventGroup = xEventGroupCreate();
	measureEventGroup = xEventGroupCreate();
}