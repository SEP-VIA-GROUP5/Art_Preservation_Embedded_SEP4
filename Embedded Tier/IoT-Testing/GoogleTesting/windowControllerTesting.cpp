#include "gtest/gtest.h"
#include "FreeRTOS_MOCK_FFF.h"

extern "C" {
#include <TemperatureHumiditySensor.h>
#include <Setup.h>
#include <Configuration.h>
#include <windowController.h>
#include "FreeRTOS.h"
#include "message_buffer.h"
#include "event_groups.h"
#include "task.h"
}

class WindowControllerTesting : public::testing::Test
{
protected:
	void SetUp() override
	{
		RESET_FAKE(xSemaphoreTake);
		RESET_FAKE(xEventGroupWaitBits);
		RESET_FAKE(xSemaphoreGive);
		RESET_FAKE(vTaskDelay);
		FFF_RESET_HISTORY();

	}

	void TearDown() override
	{

	}
};


TEST_F(WindowControllerTesting, OpeningWindow) {
	//more then 50 for temperature
	measureTempAndHum(600, 200);
	xEventGroupWaitBits_fake.return_val = ALL_READY_BITS;
	createConfiguration();
	xSemaphoreTake_fake.return_val = pdTRUE;
	windowControllerTask();
	ASSERT_EQ(1, xEventGroupWaitBits_fake.call_count);
	ASSERT_EQ(1, xSemaphoreTake_fake.call_count);
	ASSERT_EQ(1, getOpen());
	ASSERT_EQ(2, xSemaphoreGive_fake.call_count);
}

TEST_F(WindowControllerTesting, CloseWindow) {
	//less then 50 degrees for temperature
	measureTempAndHum(40, 200);
	xEventGroupWaitBits_fake.return_val = ALL_READY_BITS;
	createConfiguration();
	xSemaphoreTake_fake.return_val = pdTRUE;
	windowControllerTask();
	ASSERT_EQ(1, xEventGroupWaitBits_fake.call_count);
	ASSERT_EQ(1, xSemaphoreTake_fake.call_count);
	ASSERT_EQ(0, getOpen());
	ASSERT_EQ(2, xSemaphoreGive_fake.call_count);
}

TEST_F(WindowControllerTesting, BitsNotReady) {
	windowControllerTask();
	ASSERT_EQ(1, xEventGroupWaitBits_fake.call_count);
}

TEST_F(WindowControllerTesting, SemaphoreNotGiven)
{
	xEventGroupWaitBits_fake.return_val = ALL_READY_BITS;
	createConfiguration();
	xSemaphoreTake_fake.return_val = pdFALSE;
	windowControllerTask();
	ASSERT_EQ(1, xEventGroupWaitBits_fake.call_count);
	ASSERT_EQ(1, vTaskDelay_fake.call_count);
}