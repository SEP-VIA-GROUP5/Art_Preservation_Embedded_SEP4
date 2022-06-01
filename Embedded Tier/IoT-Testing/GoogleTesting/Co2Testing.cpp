#include "gtest/gtest.h"
#include "FreeRTOS_MOCK_FFF.h"

extern "C" {
#include <Co2Sensor.h>
#include "FreeRTOS.h"
#include "message_buffer.h"
#include "event_groups.h"
#include "task.h"
}

class Co2Testing : public::testing::Test
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


TEST_F(Co2Testing, Initialise_MeasureBitNotSent) {
	Co2Task();
	ASSERT_EQ(1, xEventGroupWaitBits_fake.call_count);
}

TEST_F(Co2Testing, Initialise_MeasureBitSent) {
	xEventGroupWaitBits_fake.return_val = CO2_MEASURE_BIT;
	Co2Task();
	ASSERT_EQ(1, xEventGroupWaitBits_fake.call_count);
	ASSERT_EQ(CO2_MEASURE_BIT, xEventGroupWaitBits_fake.return_val);
	ASSERT_EQ(getCo2(), 50);
	ASSERT_EQ(1, xEventGroupSetBits_fake.call_count);
	ASSERT_EQ(1, vTaskDelay_fake.call_count);
}