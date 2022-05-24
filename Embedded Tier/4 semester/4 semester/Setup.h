#pragma once
#include <ATMEGA_FreeRTOS.h>
#include <event_groups.h>


#define HUMIDITY_TEMPERATURE_MEASURE_BIT (1<<0)
#define HUMIDITY_TEMPERATURE_READY_BIT (1<<1)
#define CO2_MEASURE_BIT (1<<2)
#define CO2_READY_BIT (1<<3)
#define ALL_READY_BITS (HUMIDITY_TEMPERATURE_READY_BIT | CO2_READY_BIT)
#define ALL_MEASURE_BITS (HUMIDITY_TEMPERATURE_MEASURE_BIT | CO2_MEASURE_BIT)

extern EventGroupHandle_t measureEventGroup;
extern EventGroupHandle_t dataReadyEventGroup;

void initializeEventGroup();