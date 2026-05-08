package dev.x341.metromery

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import dev.x341.metromery.component.CardComponent
import dev.x341.metromery.model.Card
import kotlin.test.Test
import kotlin.test.assertTrue

class CardComponentTest: BaseTest() {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testCardShowsMWhenFlipped() = runComposeUiTest {
        val testCard = Card(
            id = 1,
            name = "Test Card",
            imagePath = null,
            isFlipped = true
        )
        var clickRegistered = false

        setContent {
            CardComponent(
                card = testCard,
                isFlipped = true,
                onClick = { clickRegistered = true }
            )
        }

        onNodeWithText("M").assertIsDisplayed()

        onNodeWithText("M").performClick()

        assertTrue(clickRegistered)
    }
}