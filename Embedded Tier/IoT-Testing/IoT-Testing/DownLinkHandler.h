
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

#include <../GoogleTesting/FreeRTOS.h>
#include <../GoogleTesting/task.h>
#include <../GoogleTesting/message_buffer.h>
#include <../GoogleTesting/semphr.h>
#include "Setup.h"
#include "Configuration.h"

void setPayLoadLen(int length);
void lora_downLink_task();
//Function for down link handler task creation
void lora_downlink_handler_create();
