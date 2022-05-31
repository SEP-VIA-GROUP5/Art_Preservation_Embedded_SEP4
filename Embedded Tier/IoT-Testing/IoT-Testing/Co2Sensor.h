/*
 * Co2Sensor.h
 *
 * Created: 05/24/22 2:48:55 PM
 *  Author: rytis
 */ 

#pragma once

#include <stdint.h>
#include <stdio.h>
#include <stdbool.h>

#include <FreeRTOS.h>
#include <task.h>
#include <semphr.h>
#include <task.h>
#include <stdio.h>
#include <event_groups.h>
#include "Setup.h"

uint16_t getCo2();
void Co2Task();
void createCo2Task();