#pragma once

#include <ATMEGA_FreeRTOS.h>
#include <event_groups.h>
#include "TemperatureHumiditySensor.h"
#include "Setup.h"

void createApplicationTask(UBaseType_t Taskpriority);
void ApplicationTask(void *pvParameters);