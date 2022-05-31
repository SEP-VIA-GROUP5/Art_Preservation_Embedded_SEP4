//A class for packaging measured data.
#include "SensorDataPackageHandler.h"

uint16_t temperature;
uint16_t humidity;
uint16_t co2Ppm;

//A setter for humidity.
void setHumidity(uint16_t value){
	humidity = value;
}

//A setter for temperature.
void setTemperature(uint16_t value){
	temperature = value;
}

//A setter for CO2.
void setCo2Ppm(uint16_t value){
	co2Ppm = value;
}

