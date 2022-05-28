/*
 * Configuration.h
 *
 * Created: 5/25/2022 11:10:32 AM
 *  Author: Lukas
 */

//A header for class Configuration.c

#pragma once

#include <ATMEGA_FreeRTOS.h>
#include <semphr.h>

//Variables for norms of:
//CO2 measurements
extern uint16_t co2Norm;
//Temperature measurements
extern uint16_t tempNorm;
//Humidity measurements
extern uint16_t humNorm;

extern SemaphoreHandle_t configMutex;

//Function for initialization
void createConfiguration();

//Getters for norms
uint16_t getCo2Norm();
uint16_t getTempNorm();
uint16_t getHumNorm();

//Setters for norms
uint16_t setCo2Norm(uint16_t norm);
uint16_t setTempNorm(uint16_t norm);
uint16_t setHumNorm(uint16_t norm);
