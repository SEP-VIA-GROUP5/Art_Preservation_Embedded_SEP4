#include "gtest/gtest.h"
#include "FreeRTOS_MOCK_FFF.h"

extern "C" {
#include <TemperatureHumiditySensor.h>
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