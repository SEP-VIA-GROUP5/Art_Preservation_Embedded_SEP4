/*
 * windowController.h
 *
 * Created: 05/27/22 12:35:04 PM
 *  Author: rytis
 */ 

#pragma once

#include <stdio.h>
#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <stdint.h>

#include "rc_servo.h"
#include "Configuration.h"
#include "Co2Sensor.h"
#include "TemperatureHumiditySensor.h"

void createWindowController();
void openWindow();
void closeWindow();
void windowControllerTask(void *pvpParameter);
void createWindowControllerTask(UBaseType_t TaskPriority);


