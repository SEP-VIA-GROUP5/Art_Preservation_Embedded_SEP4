#pragma once

#include <../GoogleTesting/FreeRTOS.h>
#include <../GoogleTesting/event_groups.h>
#include <../GoogleTesting/message_buffer.h>
#include "TemperatureHumiditySensor.h"
#include "SensorDataPackageHandler.h"
#include "Setup.h"

#include "Co2Sensor.h"
#include "Configuration.h"

void createApplicationTask();
void ApplicationTask();