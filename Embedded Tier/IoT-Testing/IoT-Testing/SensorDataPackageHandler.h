#pragma once

#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>

#include <FreeRTOS.h>

void setHumidity(uint16_t value);
void setTemperature(uint16_t value);
void setCo2Ppm(uint16_t value);