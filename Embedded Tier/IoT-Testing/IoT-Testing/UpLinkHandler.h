/*
 * UpLinkHandler.h
 *
 * Created: 23.05.2022 13:30:58
 *  Author: baico
 */ 

#include <stddef.h>
#include <stdio.h>

#include <ATMEGA_FreeRTOS.h>

#include <lora_driver.h>
#include <status_leds.h>
#include "SensorDataPackageHandler.h"
#include "message_buffer.h"
#include "Setup.h"

// Parameters for OTAA join - You have got these in a mail from IHA
#define LORA_appEUI "9276B3CF3B069355"
#define LORA_appKEY "84860CBA5C5116F9EC56E1B4346CA899"

void lora_handler_uplink_payload(UBaseType_t lora_handler_task_priority);
void _lora_setup(void);
void lora_handler_task( void *pvParameters );
