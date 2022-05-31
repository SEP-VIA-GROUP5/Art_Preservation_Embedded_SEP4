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
	ApplicationTask();
	ASSERT_EQ(1, xEventGroupSetBits_fake.call_count);
	ASSERT_EQ(1, xEventGroupWaitBits_fake.call_count);
	ASSERT_EQ(1, vTaskDelay_fake.call_count);
}

TEST_F(ApplicationTesting, Initialise_WithBitsReady) {
	xEventGroupWaitBits_fake.return_val = ALL_READY_BITS;
	ApplicationTask();

	ASSERT_EQ(1, xEventGroupSetBits_fake.call_count);
	ASSERT_EQ(1, xEventGroupWaitBits_fake.call_count);
	ASSERT_EQ(ALL_READY_BITS, xEventGroupWaitBits_fake.return_val);
	ASSERT_EQ(1, xMessageBufferSend_fake.call_count);
	ASSERT_EQ(2, vTaskDelay_fake.call_count);
	
}