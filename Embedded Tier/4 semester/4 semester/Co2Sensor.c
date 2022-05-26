/*
 * Co2Sensor.c
 *
 * Created: 05/24/22 3:47:14 PM
 *  Author: rytis
 */ 

#include "Co2Sensor.h"

void myCo2CallBack(uint16_t ppm);

uint16_t co2Ppm = 0;
mh_z19_returnCode_t rc;

void initializeCo2Driver(){
	//rc = mh_z19_takeMeassuring();
	mh_z19_initialise(ser_USART3);
	mh_z19_injectCallBack(myCo2CallBack);
}

void Co2Task(void* pvpParameter){
	while(1){
		EventBits_t eventBits = xEventGroupWaitBits(measureEventGroup,CO2_MEASURE_BIT,pdTRUE,pdTRUE,portMAX_DELAY);
		if(eventBits & (CO2_MEASURE_BIT)){
			printf("Measuring CO2... \n");
			measureCo2();
			xEventGroupSetBits(dataReadyEventGroup,CO2_READY_BIT);
			vTaskDelay(pdMS_TO_TICKS(10));
		}
	}
}

void myCo2CallBack(uint16_t ppm)
{
	co2Ppm = ppm;
}

uint16_t getCo2(){
	return co2Ppm;
}

void measureCo2(){
	rc = mh_z19_takeMeassuring();
	if (rc != MHZ19_OK)
	{
		printf("Something went wrong with CO2 sensor");
	}
}

void createCo2Task(UBaseType_t Taskpriority){
	initializeCo2Driver();
	xTaskCreate(
	Co2Task,
	"Co2Task",
	configMINIMAL_STACK_SIZE,
	NULL,
	tskIDLE_PRIORITY + Taskpriority,
	NULL
	);
}