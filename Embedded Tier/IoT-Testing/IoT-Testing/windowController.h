/*
 * windowController.h
 *
 * Created: 05/27/22 12:35:04 PM
 *  Author: rytis
 */ 

#pragma once

#include <stdint.h>
#include <../GoogleTesting/FreeRTOS.h>
#include <../GoogleTesting/task.h>
#include <../GoogleTesting/event_groups.h>

#include "TemperatureHumiditySensor.h"
#include "Configuration.h"
#include "Co2Sensor.h"
#include "Setup.h"


int isOpen;

int getOpen();
void createWindowController();
void openWindow();
void closeWindow();
void windowControllerTask();
void createWindowControllerTask();


