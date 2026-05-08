package dev.x341.metromery

import com.russhwolf.settings.MapSettings
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MetromeryViewModelTest: BaseTest() {

    @Test
    fun testModifyDifficultyUpdatesValueAndSettings() {

        val fakeSettings = MapSettings()
        val viewModel = MetromeryViewModel(fakeSettings)

        assertEquals(1, viewModel.difficulty)

        viewModel.modifyDifficulty(3)

        assertEquals(3, viewModel.difficulty)
        assertEquals(3, fakeSettings.getInt("difficulty", 0))
    }

    @Test
    fun testSelectRandomCardsResetsGameState() {
        val viewModel = MetromeryViewModel(MapSettings())

        viewModel.modifyDifficulty(2)

        viewModel.selectRandomCards()

        assertEquals(0, viewModel.attemps)
        assertEquals(false, viewModel.isGameWon)
        assertEquals(12, viewModel.selectedCards.size) // 2 * 3 = 6 unique cards, duplicated to 12 total
        assertTrue(viewModel.selectedCards.all { it.isFlipped })
    }
}