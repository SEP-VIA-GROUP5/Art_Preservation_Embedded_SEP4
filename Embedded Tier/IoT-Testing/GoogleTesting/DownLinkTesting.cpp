#include "gtest/gtest.h"
#include "FreeRTOS_MOCK_FFF.h"

extern "C" {


#include <DownLinkHandler.h>
#include <Setup.h>
#include <Configuration.h>

#include "FreeRTOS.h"
#include "message_buffer.h"
#include "event_groups.h"
#include "task.h"
}

class DownLinkTesting : public::testing::Test
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

TEST_F(DownLinkTesting, DownLinkMessageReceived) {
	createConfiguration();
	xSemaphoreTake_fake.return_val = pdTRUE;
	setPayLoadLen(5); //length might be 5 all time
	lora_downLink_task();
	ASSERT_EQ(1, xMessageBufferReceive_fake.call_count);
	ASSERT_EQ(1, xSemaphoreTake_fake.call_count);
	ASSERT_EQ(2, xSemaphoreGive_fake.call_count); //2 because it also calls createConfiguration
	ASSERT_EQ(1, vTaskDelay_fake.call_count);
}

TEST_F(DownLinkTesting, DownLinkMessageReceivedbutSemaphoreNotGiven) {
	createConfiguration();
	xSemaphoreTake_fake.return_val = pdFALSE;
	setPayLoadLen(5); //length might be 5 all time
	lora_downLink_task();
	ASSERT_EQ(1, xMessageBufferReceive_fake.call_count);
	ASSERT_EQ(1, xSemaphoreTake_fake.call_count);
	ASSERT_EQ(1, xSemaphoreGive_fake.call_count);
	ASSERT_EQ(1, vTaskDelay_fake.call_count);
}

TEST_F(DownLinkTesting, DownLinkMessageNotReceived) {
	setPayLoadLen(0); //downLinkMessage not received
	lora_downLink_task();
	ASSERT_EQ(1, xMessageBufferReceive_fake.call_count);
	ASSERT_EQ(0, xSemaphoreTake_fake.call_count);
	ASSERT_EQ(0, xSemaphoreGive_fake.call_count);
	ASSERT_EQ(1, vTaskDelay_fake.call_count);
}