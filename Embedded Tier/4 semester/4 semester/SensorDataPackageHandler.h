#pragma once

#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>

#include <ATMEGA_FreeRTOS.h>
#include <lora_driver.h>

void setHumidity(uint16_t value);
void setTemperature(uint16_t value);
void setCo2Ppm(uint16_t value);
lora_driver_payload_t sensorDataPackageHandler_getLoRaPayload(uint8_t port_No);