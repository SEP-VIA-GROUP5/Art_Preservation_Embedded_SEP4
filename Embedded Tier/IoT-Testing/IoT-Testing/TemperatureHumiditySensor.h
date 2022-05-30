#pragma once

#include <stdint.h>
#include <stdio.h>

#include <FreeRTOS.h>
#include <task.h>
#include <stdio.h>
#include "Setup.h"
#include <event_groups.h>



void measureTempAndHum();
void TempAndHumTask();
void createTempAndHumTask();