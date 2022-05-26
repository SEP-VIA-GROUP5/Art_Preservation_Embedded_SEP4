
/*
 * Configuration.h
 *
 * Created: 5/25/2022 11:10:32 AM
 *  Author: ljusk
 */ 

#pragma once

#include <ATMEGA_FreeRTOS.h>
#include <semphr.h>

extern uint16_t co2Norm;
extern uint16_t tempNorm;
extern uint16_t humNorm;

extern SemaphoreHandle_t configMutex;

void createConfiguration();

uint16_t getCo2Norm();
uint16_t getTempNorm();
uint16_t getHumNorm();

uint16_t setCo2Norm(uint16_t norm);
uint16_t setTempNorm(uint16_t norm);
uint16_t setHumNorm(uint16_t norm);
