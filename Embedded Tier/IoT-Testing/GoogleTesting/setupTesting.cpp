#include "gtest/gtest.h"
#include "FreeRTOS_MOCK_FFF.h"

extern "C" {
#include <Setup.h>
#include "FreeRTOS.h"
#include "message_buffer.h"
}

class SetupTesting : public::testing::Test
{
protected:
	void SetUp() override
	{
		RESET_FAKE(xMessageBufferCreate);
		RESET_FAKE(xEventGroupCreate);
		FFF_RESET_HISTORY();

	}

	void TearDown() override
	{

	}
};


TEST_F(SetupTesting, Initialise_DownLinkMessageBuffer) {
	createDownLinkMessageBuffer();

	ASSERT_EQ(1, xMessageBufferCreate_fake.call_count);
	ASSERT_EQ(44, xMessageBufferCreate_fake.arg0_val);
}

TEST_F(SetupTesting, Initialise_UpLinkMessageBuffer) {
	createUpLinkMessageBuffer();

	ASSERT_EQ(1, xMessageBufferCreate_fake.call_count);
	ASSERT_EQ(44, xMessageBufferCreate_fake.arg0_val);
}

TEST_F(SetupTesting, Initialise_EventGroup) {
	initializeEventGroup();

	ASSERT_EQ(2, xEventGroupCreate_fake.call_count);
}
