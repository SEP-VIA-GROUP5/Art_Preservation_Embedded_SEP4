#include "gtest/gtest.h"
#include "FreeRTOS_MOCK_FFF.h"

extern "C" {
#include <DownLinkHandler.h>
#include <Setup.h>
#include "FreeRTOS.h"
#include "message_buffer.h"
#include "event_groups.h"
#include "task.h"
}

class DownLinkHandlerTesting : public::testing::Test
{
protected:
	void SetUp() override
	{
		RESET_FAKE(xMessageBufferReceive);
		RESET_FAKE(xSemaphoreTake);
		RESET_FAKE(xSemaphoreGive);
		RESET_FAKE(vTaskDelay);
		FFF_RESET_HISTORY();

	}

	void TearDown() override
	{

	}
};

TEST_F(DownLinkHandlerTesting, DownLinkMessageNotReceived) {
	lora_downLink_task();
	ASSERT_EQ(1, xMessageBufferReceive_fake.call_count);
	ASSERT_EQ(1, vTaskDelay_fake.call_count);
}