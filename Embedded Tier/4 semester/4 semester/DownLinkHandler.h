
/*
 * DownLinkHandler.h
 *
 * Created: 5/25/2022 11:01:21 AM
 *  Author: ljusk
 */ 

#pragma once

#include <stdint.h>
#include <stdio.h>

#include <ATMEGA_FreeRTOS.h>
#include <message_buffer.h>
#include <task.h>
#include <lora_driver.h>

#include "Setup.h"
#include "Configuration.h"

void lora_downlink_handler_create(UBaseType_t lora_handler_task_priority);
