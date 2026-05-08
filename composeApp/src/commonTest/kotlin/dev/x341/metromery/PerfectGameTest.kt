package dev.x341.metromery

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import com.russhwolf.settings.MapSettings
import dev.x341.metromery.screen.GameScreen
import dev.x341.metromery.theme.MetromeryTheme
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class PerfectGameTest : BaseTest() {

    @Test
    fun testPerfectGameShowsWinScreen() = runComposeUiTest {
        val viewModel = MetromeryViewModel(MapSettings())

        setContent {
            MetromeryTheme {
                GameScreen(viewModel = viewModel, onNavigateBack = {})
            }
        }

        // Wait for LaunchedEffect to call selectRandomCards()
        waitForIdle()

        // Read the shuffled cards and find matching pair indices
        val cards = viewModel.selectedCards.toList()
        val pairs = cards.indices.groupBy { cards[it].id }.values.toList()

        // Simulate a perfect game: flip each matching pair in order
        for (pair in pairs) {
            viewModel.flipCard(pair[0])
            viewModel.flipCard(pair[1])
            waitForIdle()
        }

        // Win screen should be visible with "You Won!" text
        onNodeWithText("You Won!").assertIsDisplayed()
    }

    @Test
    fun testPerfectGameDisplaysCorrectAttempts() = runComposeUiTest {
        val viewModel = MetromeryViewModel(MapSettings())
        viewModel.modifyDifficulty(1) // difficulty 1 = 3 pairs

        setContent {
            MetromeryTheme {
                GameScreen(viewModel = viewModel, onNavigateBack = {})
            }
        }

        waitForIdle()

        val cards = viewModel.selectedCards.toList()
        val pairs = cards.indices.groupBy { cards[it].id }.values.toList()

        for (pair in pairs) {
            viewModel.flipCard(pair[0])
            viewModel.flipCard(pair[1])
            waitForIdle()
        }

        // Win screen stats: a perfect game on difficulty 1 has 100% accuracy
        onNodeWithText("100%").assertIsDisplayed()
        onNodeWithText("Attempts").assertIsDisplayed()
        onNodeWithText("Pairs").assertIsDisplayed()
    }

    @Test
    fun testPerfectGameShowsPlayAgainButton() = runComposeUiTest {
        val viewModel = MetromeryViewModel(MapSettings())

        setContent {
            MetromeryTheme {
                GameScreen(viewModel = viewModel, onNavigateBack = {})
            }
        }

        waitForIdle()

        val cards = viewModel.selectedCards.toList()
        val pairs = cards.indices.groupBy { cards[it].id }.values.toList()

        for (pair in pairs) {
            viewModel.flipCard(pair[0])
            viewModel.flipCard(pair[1])
            waitForIdle()
        }

        // Assert buttons exist — "Back to Home" may be scrolled out of the test viewport
        onNodeWithText("Play Again").assertIsDisplayed()
        onAllNodesWithText("Back to Home")[0].assertExists()
    }
}