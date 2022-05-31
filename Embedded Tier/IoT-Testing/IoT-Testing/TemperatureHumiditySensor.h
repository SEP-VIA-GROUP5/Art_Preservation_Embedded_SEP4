#pragma once

#include <stdint.h>
#include <stdio.h>

#include <../GoogleTesting/FreeRTOS.h>
#include <../GoogleTesting/task.h>
#include <../GoogleTesting/event_groups.h>
#include "Setup.h"



uint16_t getHumidity();
uint16_t getTemperature();
void measureTempAndHum(uint16_t temperatureMeasured, uint16_t humidityMeasured);
void TempAndHumTask();
void createTempAndHumTask();