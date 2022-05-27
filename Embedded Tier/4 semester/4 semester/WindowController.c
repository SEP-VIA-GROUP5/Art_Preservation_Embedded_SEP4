/*
 * WindowController.c
 *
 * Created: 05/27/22 12:39:22 PM
 *  Author: rytis
 */ 

int isOpen = 0;

void createWindowController(){
	rc_servo_initialise();
}
void openWindow(){
	rc_servo_setPosition(0,60);
	isOpen = 1;
}
void closeWindow(){
	rc_servo_setPosition(0,0);
	isOpen = 0;
}
void windowControllerTask(void *pvpParameter){
	while(1)
	{
		if( xSemaphoreTake( configMutex, ( TickType_t ) 10 ) == pdTRUE )
		{
			if(getCo2Norm()<=getCo2() || getHumNorm()<=getHumidity() || getTempNorm()<=getTemperature()){
				openWindow();
				printf("Window is opened");
			}
			else{
				if(isOpen==1){
					closeWindow();
					printf("Window is closed");
				}
			}
		}
		//if configuration is taken by someone else
		else{
			printf("We could not access the shared resource: configMutex");
		}
		vTaskDelay(pdMS_TO_TICKS(10));
	}
}
void createWindowControllerTask(UBaseType_t TaskPriority){
	xTaskCreate(
	windowControllerTask();
	,  "WindowControllerTask"
	,  configMINIMAL_STACK_SIZE
	,  NULL
	,  tskIDLE_PRIORITY + TaskPriority
	,  NULL );
}