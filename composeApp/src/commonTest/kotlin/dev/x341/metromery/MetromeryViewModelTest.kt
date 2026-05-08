package dev.x341.metromery

import com.russhwolf.settings.MapSettings
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MetromeryViewModelTest : BaseTest() {

    private fun makeViewModel(difficulty: Int = 1): MetromeryViewModel {
        val settings = MapSettings()
        val vm = MetromeryViewModel(settings)
        vm.modifyDifficulty(difficulty)
        return vm
    }

    // --- modifyDifficulty ---

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
    fun testModifyDifficultyIgnoresValueBelowRange() {
        val viewModel = makeViewModel()
        viewModel.modifyDifficulty(0)
        assertEquals(1, viewModel.difficulty)
    }

    @Test
    fun testModifyDifficultyIgnoresValueAboveRange() {
        val viewModel = makeViewModel()
        viewModel.modifyDifficulty(5)
        assertEquals(1, viewModel.difficulty)
    }

    @Test
    fun testModifyDifficultyAllowsBoundaryValues() {
        val viewModel = makeViewModel()
        viewModel.modifyDifficulty(1)
        assertEquals(1, viewModel.difficulty)
        viewModel.modifyDifficulty(4)
        assertEquals(4, viewModel.difficulty)
    }

    // --- selectRandomCards ---

    @Test
    fun testSelectRandomCardsResetsGameState() {
        val viewModel = makeViewModel(difficulty = 2)

        viewModel.selectRandomCards()

        assertEquals(0, viewModel.attemps)
        assertFalse(viewModel.isGameWon)
        assertEquals(12, viewModel.selectedCards.size) // 2 * 3 = 6 unique, duplicated = 12
        assertTrue(viewModel.selectedCards.all { it.isFlipped })
    }

    @Test
    fun testSelectRandomCardsWithDifficulty1Has6Cards() {
        val viewModel = makeViewModel(difficulty = 1)
        viewModel.selectRandomCards()
        assertEquals(6, viewModel.selectedCards.size) // 1 * 3 * 2
    }

    @Test
    fun testSelectRandomCardsWithDifficulty4Has24Cards() {
        val viewModel = makeViewModel(difficulty = 4)
        viewModel.selectRandomCards()
        assertEquals(24, viewModel.selectedCards.size) // 4 * 3 * 2
    }

    @Test
    fun testSelectRandomCardsContainsMatchingPairs() {
        val viewModel = makeViewModel(difficulty = 2)
        viewModel.selectRandomCards()

        val countById = viewModel.selectedCards.groupBy { it.id }.mapValues { it.value.size }
        assertTrue(countById.values.all { it == 2 }, "Every card id must appear exactly twice")
    }

    @Test
    fun testSelectRandomCardsResetsAfterPreviousGame() {
        val viewModel = makeViewModel(difficulty = 1)
        viewModel.selectRandomCards()

        // Simulate flipping a pair to change game state
        val cards = viewModel.selectedCards.toList()
        val pair = cards.indices.groupBy { cards[it].id }.values.first()
        viewModel.flipCard(pair[0])
        viewModel.flipCard(pair[1])

        // Start a new game
        viewModel.selectRandomCards()

        assertEquals(0, viewModel.attemps)
        assertFalse(viewModel.isGameWon)
        assertTrue(viewModel.selectedCards.all { it.isFlipped })
    }

    // --- flipCard ---

    @Test
    fun testFlipCardFirstCardDoesNotChangeAttempts() {
        val viewModel = makeViewModel(difficulty = 1)
        viewModel.selectRandomCards()

        viewModel.flipCard(0)

        assertEquals(0, viewModel.attemps)
        assertFalse(viewModel.selectedCards[0].isFlipped) // card is now face-up
    }

    @Test
    fun testFlipCardMatchingPairIncreasesAttempts() {
        val viewModel = makeViewModel(difficulty = 1)
        viewModel.selectRandomCards()

        val cards = viewModel.selectedCards.toList()
        val pair = cards.indices.groupBy { cards[it].id }.values.first()

        viewModel.flipCard(pair[0])
        viewModel.flipCard(pair[1])

        assertEquals(1, viewModel.attemps)
    }

    @Test
    fun testFlipCardMatchingPairKeepsBothFaceUp() {
        val viewModel = makeViewModel(difficulty = 1)
        viewModel.selectRandomCards()

        val cards = viewModel.selectedCards.toList()
        val pair = cards.indices.groupBy { cards[it].id }.values.first()

        viewModel.flipCard(pair[0])
        viewModel.flipCard(pair[1])

        assertFalse(viewModel.selectedCards[pair[0]].isFlipped)
        assertFalse(viewModel.selectedCards[pair[1]].isFlipped)
    }

    @Test
    fun testFlipCardNonMatchingPairIncreasesAttempts() {
        val viewModel = makeViewModel(difficulty = 2)
        viewModel.selectRandomCards()

        val cards = viewModel.selectedCards.toList()
        val pairs = cards.indices.groupBy { cards[it].id }.values.toList()
        val firstPair = pairs[0]
        val secondPair = pairs[1]

        viewModel.flipCard(firstPair[0])  // first card of pair A
        viewModel.flipCard(secondPair[0]) // first card of pair B → mismatch

        assertEquals(1, viewModel.attemps)
    }

    @Test
    fun testFlipCardIgnoresAlreadyFaceUpCard() {
        val viewModel = makeViewModel(difficulty = 1)
        viewModel.selectRandomCards()

        viewModel.flipCard(0) // flip face-up (isFlipped=false now)
        val stateAfterFirst = viewModel.selectedCards[0].isFlipped

        // Try to flip the same face-up card again
        viewModel.flipCard(0)

        assertEquals(stateAfterFirst, viewModel.selectedCards[0].isFlipped)
        assertEquals(0, viewModel.attemps) // no attempt registered since first card was ignored
    }

    @Test
    fun testFlipCardWhileProcessingIsIgnored() {
        val viewModel = makeViewModel(difficulty = 2)
        viewModel.selectRandomCards()

        val cards = viewModel.selectedCards.toList()
        val pairs = cards.indices.groupBy { cards[it].id }.values.toList()
        val firstPair = pairs[0]
        val secondPair = pairs[1]

        // Trigger processing: flip two non-matching cards
        viewModel.flipCard(firstPair[0])
        viewModel.flipCard(secondPair[0]) // mismatch → isProcessing = true

        val attemptsAfterMismatch = viewModel.attemps

        // Try to flip a third card while processing
        viewModel.flipCard(firstPair[1])

        // State should not change because flipCard returns early when processing
        assertEquals(attemptsAfterMismatch, viewModel.attemps)
        assertTrue(viewModel.selectedCards[firstPair[1]].isFlipped) // still face-down
    }

    // --- Perfect game ---

    @Test
    fun testPerfectGameCompletesAndWins() {
        val viewModel = makeViewModel(difficulty = 1)
        viewModel.selectRandomCards()

        val cards = viewModel.selectedCards.toList()
        val pairs = cards.indices.groupBy { cards[it].id }.values.toList()
        val totalPairs = pairs.size // = difficulty * 3 = 3

        for (pair in pairs) {
            viewModel.flipCard(pair[0])
            viewModel.flipCard(pair[1])
        }

        assertTrue(viewModel.isGameWon)
        assertEquals(totalPairs, viewModel.attemps)
        assertTrue(viewModel.selectedCards.none { it.isFlipped })
    }

    @Test
    fun testPerfectGameWithDifficulty3() {
        val viewModel = makeViewModel(difficulty = 3)
        viewModel.selectRandomCards()

        val cards = viewModel.selectedCards.toList()
        val pairs = cards.indices.groupBy { cards[it].id }.values.toList()

        for (pair in pairs) {
            viewModel.flipCard(pair[0])
            viewModel.flipCard(pair[1])
        }

        assertTrue(viewModel.isGameWon)
        assertEquals(9, viewModel.attemps) // 3 * 3 = 9 pairs
    }

    @Test
    fun testGameNotWonAfterPartialPairs() {
        val viewModel = makeViewModel(difficulty = 2)
        viewModel.selectRandomCards()

        val cards = viewModel.selectedCards.toList()
        val pairs = cards.indices.groupBy { cards[it].id }.values.toList()

        // Flip only the first pair out of 6
        viewModel.flipCard(pairs[0][0])
        viewModel.flipCard(pairs[0][1])

        assertFalse(viewModel.isGameWon)
    }
}