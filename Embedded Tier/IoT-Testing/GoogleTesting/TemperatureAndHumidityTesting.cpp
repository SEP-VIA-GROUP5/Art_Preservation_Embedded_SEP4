#include "gtest/gtest.h"
#include "FreeRTOS_MOCK_FFF.h"

extern "C" {
#include <TemperatureHumiditySensor.h>
#include "FreeRTOS.h"
#include "message_buffer.h"
#include "event_groups.h"
#include "task.h"
}

class TemperatureAndHumidityTesting : public::testing::Test
{
protected:
	void SetUp() override
	{
		RESET_FAKE(xEventGroupSetBits);
		RESET_FAKE(xEventGroupWaitBits);
		RESET_FAKE(vTaskDelay);
		FFF_RESET_HISTORY();

	}

	void TearDown() override
	{

	}
};


TEST_F(TemperatureAndHumidityTesting, Initialise_MeasureBitNotSent) {
	TempAndHumTask();
	ASSERT_EQ(1, xEventGroupWaitBits_fake.call_count);
	ASSERT_EQ(1, vTaskDelay_fake.call_count);
}

TEST_F(TemperatureAndHumidityTesting, Initialise_MeasureBitSent) {
	xEventGroupWaitBits_fake.return_val = HUMIDITY_TEMPERATURE_MEASURE_BIT;
	TempAndHumTask();
	ASSERT_EQ(1, xEventGroupWaitBits_fake.call_count);
	ASSERT_EQ(HUMIDITY_TEMPERATURE_MEASURE_BIT, xEventGroupWaitBits_fake.return_val);
	ASSERT_EQ(1, xEventGroupSetBits_fake.call_count);
	ASSERT_EQ(1, vTaskDelay_fake.call_count);
}