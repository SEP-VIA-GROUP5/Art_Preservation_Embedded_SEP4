/*
 * WindowController.c
 *
 * Created: 05/27/22 12:39:22 PM
 *  Author: rytis
 */ 

#include "WindowController.h"

void createWindowController(){
	rc_servo_initialise();
	isOpen = 0;
}
void openWindow(){
	rc_servo_setPosition(0,80);
	isOpen = 1;
}
void closeWindow(){
	rc_servo_setPosition(0,0);
	isOpen = 0;
}
void windowControllerTask(void *pvpParameter){
	for(;;)
	{
			EventBits_t eventBits = xEventGroupWaitBits(dataReadyEventGroup,ALL_READY_BITS,pdTRUE,pdTRUE,portMAX_DELAY);
		if(eventBits & (ALL_READY_BITS))
		{
			if( xSemaphoreTake( configMutex, ( TickType_t ) 50 ) == pdTRUE )
		{
		printf("Norm: %u, Actual temp: %u\n", (unsigned int)getTempNorm(), (unsigned int)getTemperature());	
			if(getTempNorm()<=(getTemperature()/10))
			{
				openWindow();
				printf("Is it open?: %d", isOpen);
				
				printf("Window is opened");
			}
			else
			{
				if(isOpen==1)
				{
					closeWindow();
					printf("Window is closed");
				}
			}
			xSemaphoreGive(configMutex);
		}
		//if configuration is taken by someone else
		else
		{
			printf("We could not access the shared resource: configMutex\n");
		}
		vTaskDelay(pdMS_TO_TICKS(60000));
		}
			
	}
}
void createWindowControllerTask(UBaseType_t TaskPriority){
	xTaskCreate(
		windowControllerTask
	,  "WindowControllerTask"
	,  configMINIMAL_STACK_SIZE
	,  NULL
	,  tskIDLE_PRIORITY + TaskPriority
	,  NULL );
}