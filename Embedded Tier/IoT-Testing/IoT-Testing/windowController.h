/*
 * windowController.h
 *
 * Created: 05/27/22 12:35:04 PM
 *  Author: rytis
 */ 

#pragma once

#include <FreeRTOS.h>
#include <task.h>
#include <event_groups.h>

#include "TemperatureHumiditySensor.h"
#include "Configuration.h"
#include "Co2Sensor.h"
#include "Setup.h"


int isOpen;

void createWindowController();
void openWindow();
void closeWindow();
void windowControllerTask(void *pvpParameter);
void createWindowControllerTask(UBaseType_t TaskPriority);


