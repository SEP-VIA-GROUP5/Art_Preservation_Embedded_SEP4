#pragma once

#include <FreeRTOS.h>
#include <event_groups.h>
#include <message_buffer.h>
#include "TemperatureHumiditySensor.h"
#include "SensorDataPackageHandler.h"
#include "Setup.h"

#include "Co2Sensor.h"
#include "Configuration.h"

void createApplicationTask();
void ApplicationTask();