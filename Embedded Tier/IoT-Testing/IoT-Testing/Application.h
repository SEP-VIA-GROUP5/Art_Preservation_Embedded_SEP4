#pragma once

#include <FreeRTOS.h>
#include <event_groups.h>
#include "TemperatureHumiditySensor.h"
#include "SensorDataPackageHandler.h"
#include "Setup.h"
#include "message_buffer.h"
#include "Co2Sensor.h"
#include "Configuration.h"

void createApplicationTask();
void ApplicationTask();