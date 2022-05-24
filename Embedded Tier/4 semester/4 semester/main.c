/*
* main.c
* Author : IHA
*
* Example main file including LoRaWAN setup
* Just for inspiration :)
*/

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

void initializeUsedData()
{
	initializeEventGroup();
	
	createUpLinkMessageBuffer();
	
	lora_driver_initialise(ser_USART1, NULL);
}

void create_tasks(void)
{
	createTempAndHumTask(1);
	createCo2Task(1);
	createApplicationTask(2);
    lora_handler_uplink_payload(3);
}

void initialiseSystem()
{
	// Set output ports for leds used in the example
	DDRA |= _BV(DDA0) | _BV(DDA7);

	// Make it possible to use stdio on COM port 0 (USB) on Arduino board - Setting 57600,8,N,1
	initializeUsedData();
	stdio_initialise(ser_USART0);
	// Let's create some tasks
	create_tasks();

	// vvvvvvvvvvvvvvvvv BELOW IS LoRaWAN initialisation vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// Status Leds driver
	status_leds_initialise(5); // Priority 5 for internal task
	// Initialise the LoRaWAN driver without down-link buffer
	//lora_driver_initialise(1, NULL);
	// Create LoRaWAN task and start it up with priority 3
	//lora_handler_initialise(3); 
	
}

/*-----------------------------------------------------------*/
int main(void)
{
	initialiseSystem(); // Must be done as the very first thing!!
	printf("Program Started!!\n");
	vTaskStartScheduler(); // Initialise and run the freeRTOS scheduler. Execution should never return from here.

	/* Replace with your application code */
	while (1)
	{
	}
}

