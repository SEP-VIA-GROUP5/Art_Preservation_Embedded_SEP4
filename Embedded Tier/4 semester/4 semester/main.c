#include <stdio.h>
#include <avr/io.h>

#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <semphr.h>

#include <stdio_driver.h>
#include <serial.h>
#include <event_groups.h>

 // Needed for LoRaWAN
#include <lora_driver.h>
#include <status_leds.h>

#include "Application.h"
#include "TemperatureHumiditySensor.h"
#include "Co2Sensor.h"
#include "Setup.h"
#include "UpLinkHandler.h"
#include "DownLinkHandler.h"
#include "Configuration.h"
#include "WindowController.h"

//Initializing the essential data
void initializeUsedData(void)
{
	initializeEventGroup();
	createUpLinkMessageBuffer();
	createDownLinkMessageBuffer();
	createConfiguration();
	createWindowController();
	lora_driver_initialise(ser_USART1, downLinkMessageBuffer);
}

//Creating tasks and assigning priorities
void create_tasks(void)
{
	createTempAndHumTask(1);
	createCo2Task(1);
	createApplicationTask(2);
    lora_handler_uplink_payload(4);
	lora_downlink_handler_create(3);
	createWindowControllerTask(2);
}

void initialiseSystem(void)
{
	// Set output ports for leds
	DDRA |= _BV(DDA0) | _BV(DDA7);

	// Make it possible to use stdio on COM port 0 (USB) on Arduino board - Setting 57600,8,N,1
	initializeUsedData();
	stdio_initialise(ser_USART0);
	create_tasks();

	// vvvvvvvvvvvvvvvvv BELOW IS LoRaWAN initialisation vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// Status Leds driver
	status_leds_initialise(5); // Priority 5 for internal task
}

/*-----------------------------------------------------------*/
int main(void)
{
	initialiseSystem(); // Must be done as the very first thing!!
	printf("Program Started!!\n");
	vTaskStartScheduler(); // Initialise and run the freeRTOS scheduler. Execution should never return from here.

	while (1)
	{
	}
}

