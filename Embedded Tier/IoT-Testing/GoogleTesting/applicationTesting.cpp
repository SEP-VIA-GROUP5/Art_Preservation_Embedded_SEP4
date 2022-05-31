#include "gtest/gtest.h"
#include "FreeRTOS_MOCK_FFF.h"

extern "C" {
#include <Application.h>
#include "FreeRTOS.h"
#include "message_buffer.h"
#include "event_groups.h"
#include "task.h"
}

class ApplicationTesting : public::testing::Test
{
protected:
	void SetUp() override
	{
		RESET_FAKE(xEventGroupSetBits);
		RESET_FAKE(xEventGroupWaitBits);
		RESET_FAKE(xMessageBufferSend);
		RESET_FAKE(vTaskDelay);
		FFF_RESET_HISTORY();

	}

	void TearDown() override
	{

	}
};

TEST_F(ApplicationTesting, Initialise_WithoutHavingTheBitsReady) {
	createApplicationTask();

	ASSERT_EQ(1, xEventGroupSetBits_fake.call_count);
	ASSERT_EQ(ALL_MEASURE_BITS, xEventGroupSetBits_fake.arg0_val);
	ASSERT_EQ(1, xEventGroupWaitBits_fake.call_count);
	ASSERT_EQ(pdMS_TO_TICKS(50), vTaskDelay_fake.arg0_val);
}

TEST_F(ApplicationTesting, Initialise_WithBitsReady) {
	createApplicationTask();

	ASSERT_EQ(1, xEventGroupSetBits_fake.call_count);
	ASSERT_EQ(ALL_MEASURE_BITS, xEventGroupSetBits_fake.arg0_val);
	ASSERT_EQ(1, xEventGroupWaitBits_fake.call_count);
	ASSERT_EQ(ALL_READY_BITS, xEventGroupWaitBits_fake.arg0_val);
	ASSERT_EQ(1, xMessageBufferSend_fake.call_count);
	ASSERT_EQ(pdMS_TO_TICKS(120000), vTaskDelay_fake.arg0_val);
	ASSERT_EQ(pdMS_TO_TICKS(50), vTaskDelay_fake.arg0_val);
}