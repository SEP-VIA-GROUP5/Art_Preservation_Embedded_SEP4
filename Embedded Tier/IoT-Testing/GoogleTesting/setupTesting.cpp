#include "gtest/gtest.h"
#include "FreeRTOS_MOCK_FFF.h"

extern "C" {
#include "Setup.h"
#include "FreeRTOS.h"
#include "task.h"
#include "semphr.h"
#include "event_groups.h"
#include "queue.h"
}

class SetupTesting : public::testing::Test
{
protected:
	void SetUp() override
	{
		RESET_FAKE(xMessageBufferCreate);
		RESET_FAKE(xSemaphoreCreateBinary);
		RESET_FAKE(xSemaphoreGive);
		RESET_FAKE(xQueueCreate);
		RESET_FAKE(xEventGroupCreate);
		FFF_RESET_HISTORY();

	}

	void TearDown() override
	{

	}
};


TEST_F(SetupTesting, Initialise_DownLinkMessageBuffer) {
	createDownLinkMessageBuffer();

	ASSERT_EQ(1, xMessageBufferCreate_Fake.call_count);
	ASSERT_EQ(40, xMessageBufferCreate_Fake.arg0_val);

}
