/*
 * WindowController.c
 *
 * Created: 05/27/22 12:39:22 PM
 *  Author: rytis
 */ 

#include "WindowController.h"

void createWindowController(){ //This function initializes the window controller
	rc_servo_initialise();
	isOpen = 0; //This variable acts like a bool, and when it is 0 - it means that the window is closed, and when it is 1 - open
}
void openWindow(){ //This function opens the window
	rc_servo_setPosition(0,80);
	isOpen = 1;
}
void closeWindow(){ //This function closes the window
	rc_servo_setPosition(0,0);
	isOpen = 0;
}
//This task checks the parameters and compares it to norms. If the norms are overstepped, the window will open. When it goes back to normal the window will close
void windowControllerTask(void *pvpParameter){
	for(;;)
	{
			EventBits_t eventBits = xEventGroupWaitBits(dataReadyEventGroup,ALL_READY_BITS,pdTRUE,pdTRUE,portMAX_DELAY);
		if(eventBits & (ALL_READY_BITS)) //Here it is checked if all the data is ready
		{
			if( xSemaphoreTake( configMutex, ( TickType_t ) 50 ) == pdTRUE ) //Check if the configuration mutex is available
		{
		printf("Norm: %u, Actual temp: %u\n", (unsigned int)getTempNorm(), (unsigned int)getTemperature());	
			if(getTempNorm()<=(getTemperature()/10) || getHumNorm()<=(getHumidity()/10) || getCo2Norm()<=(getCo2()/10)) //Comparing norms and actual measured values
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
			xSemaphoreGive(configMutex); //Giving back the configuration mutex to other classes
		}
		//if configuration is taken by someone else
		else
		{
			printf("We could not access the shared resource: configMutex\n");
		}
		vTaskDelay(pdMS_TO_TICKS(60000)); //delaying task until the next measurement
		}
			
	}
}
void createWindowControllerTask(UBaseType_t TaskPriority){ //Defining the task
	xTaskCreate(
		windowControllerTask
	,  "WindowControllerTask"
	,  configMINIMAL_STACK_SIZE
	,  NULL
	,  tskIDLE_PRIORITY + TaskPriority
	,  NULL );
}