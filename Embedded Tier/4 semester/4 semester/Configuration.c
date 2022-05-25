
/*
 * Configuration.c
 *
 * Created: 5/25/2022 11:14:41 AM
 *  Author: ljusk
 */ 

#include "Configuration.h"

uint16_t co2Norm;
uint16_t tempNorm;
uint16_t humNorm;

SemaphoreHandle_t configMutex;

void createConfiguration(){
	configMutex = xSemaphoreCreateMutex();
	co2Norm = 0;
	tempNorm = 0;
	humNorm = 0;
}

uint16_t getCo2Norm(){
	return co2Norm;
}
uint16_t getTempNorm(){
	return tempNorm;
}
uint16_t getHumNorm(){
	return humNorm;
}


uint16_t setCo2Norm(uint16_t norm)
{
	co2Norm = norm;
}
uint16_t setTempNorm(uint16_t norm)
{
	tempNorm = norm;
}
uint16_t setHumNorm(uint16_t norm)
{
	humNorm = norm;
}