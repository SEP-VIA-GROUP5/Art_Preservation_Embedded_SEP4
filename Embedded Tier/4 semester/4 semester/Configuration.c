/*
 * Configuration.c
 *
 * Created: 5/25/2022 11:14:41 AM
 *  Author: Lukas
 */ 

/*Configuration.c is a shared resource class that holds the norm
* values for CO2, Temperature and humidity
*/

#include "Configuration.h"

//Variables for norms of:
//CO2 measurements
uint16_t co2Norm;
//Temperature measurements
uint16_t tempNorm;
//Humidity measurements
uint16_t humNorm;

//Mutex handle
SemaphoreHandle_t configMutex;

/*
* Function for initializing the mutex of configuration class,
* and the norm default values
*/
void createConfiguration(){
	
	printf("Mutex was created\n");
	co2Norm = 1000;
	tempNorm = 0x1A;
	printf("Config norm: %x\n", tempNorm);
	humNorm = 1000;
	configMutex = xSemaphoreCreateMutex();
	xSemaphoreGive(configMutex);
}

//Getters for norm values
uint16_t getCo2Norm(){
	return co2Norm;
}
uint16_t getTempNorm(){
	return tempNorm;
}
uint16_t getHumNorm(){
	return humNorm;
}

//Setters for norm values
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