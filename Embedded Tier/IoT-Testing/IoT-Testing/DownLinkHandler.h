
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

#include <FreeRTOS.h>
#include <task.h>
#include <message_buffer.h>
#include "Setup.h"
#include "Configuration.h"

extern lora_driver_payload_t* lora_downlink_payload;

//Function for down link handler task creation
void lora_downlink_handler_create(UBaseType_t lora_handler_task_priority);
