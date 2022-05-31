
/*
 * DownLinkHandler.h
 *
 * Created: 5/25/2022 11:01:21 AM
 *  Author: ljusk
 * DownLinkHandler.h
 *
 *  Author: Lukas
 */ 

//A header for class DownLinkHandler.c

#pragma once

#include <stdint.h>
#include <stdio.h>

#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <lora_driver.h>

#include "message_buffer.h"
#include "Setup.h"
#include "Configuration.h"

//Function for down link handler task creation
void lora_downlink_handler_create(UBaseType_t lora_handler_task_priority);
