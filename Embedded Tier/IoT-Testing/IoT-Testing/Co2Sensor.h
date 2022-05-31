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

#include <../GoogleTesting/FreeRTOS.h>
#include <../GoogleTesting/task.h>
#include <../GoogleTesting/semphr.h>
#include <../GoogleTesting/task.h>
#include <../GoogleTesting/event_groups.h>
#include "Setup.h"

uint16_t getCo2();
void Co2Task();
void createCo2Task();