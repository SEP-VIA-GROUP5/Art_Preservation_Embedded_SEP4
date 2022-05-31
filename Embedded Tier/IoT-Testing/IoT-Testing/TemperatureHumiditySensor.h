#pragma once

#include <stdint.h>
#include <stdio.h>

#include <FreeRTOS.h>
#include <task.h>
#include <stdio.h>
#include <event_groups.h>
#include "Setup.h"



uint16_t getHumidity();
uint16_t getTemperature();
void measureTempAndHum();
void TempAndHumTask();
void createTempAndHumTask();