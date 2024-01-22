package com.davidluna.hsbccodechallenge.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.davidluna.hsbccodechallenge.domain.tags.TestTag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class InstrumentedTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldNavigateThroughAllTemperaturesThenRestartList(): Unit = with(composeTestRule) {
        onRoot(useUnmergedTree = true)
            .printToLog("<-- found column")
        onNodeWithTag(TestTag.Temperature.NEXT_BUTTON).apply {
            repeat(26) {
                performClick()
                waitForIdle()
            }
        }.printToLog("<-- found next button")
//        ON DIALOG
        onNodeWithTag(TestTag.ErrorDialog.CARD).assertIsDisplayed()
        onRoot(true)
        onNodeWithTag(TestTag.ErrorDialog.BUTTON).assertIsDisplayed()
            .performClick()
        onNodeWithTag(TestTag.Temperature.NEXT_BUTTON).apply {
            repeat(26) {
                performClick()
                waitForIdle()
            }
        }.printToLog("<-- found next button 2nd round of clicks")

    }


}