#include "gtest/gtest.h"
#include "FreeRTOS_MOCK_FFF.h"

extern "C" {
#include <Configuration.h>
#include "FreeRTOS.h"
#include "message_buffer.h"
#include "event_groups.h"
#include "task.h"
}

class ConfigurationTesting : public::testing::Test
{
protected:
	void SetUp() override
	{
		RESET_FAKE(xSemaphoreCreateMutex);
		RESET_FAKE(xSemaphoreGive);
		FFF_RESET_HISTORY();

	}

	void TearDown() override
	{

	}
};

TEST_F(ConfigurationTesting, Initiliase_Configuration) {
	createConfiguration();
	ASSERT_EQ(1, xSemaphoreCreateMutex_fake.call_count);
	ASSERT_EQ(1, xSemaphoreGive_fake.call_count);
	//default norms
	ASSERT_EQ(1000, getCo2Norm());
	ASSERT_EQ(0x1A, getTempNorm());
	ASSERT_EQ(1000, getHumNorm());
}