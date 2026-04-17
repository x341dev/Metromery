package dev.x341.metromery

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.x341.metromery.model.Card
import dev.x341.metromery.model.CardRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class MetromeryViewModel : ViewModel() {
    var difficulty by mutableStateOf(1)
        private set

    val cards = CardRepository.cards
    val selectedCards = mutableStateListOf<Card>()

    var showMessage by mutableStateOf(false)
        private set

    var attemps by mutableStateOf(0)
        private set

    var isGameWon by mutableStateOf(false)
        private set

    private var firstCardIndex: Int? = null
    private var isProcessing = false


    fun modifyShowMessage() {
        showMessage = !showMessage
    }

    fun modifyDifficulty(newDifficulty: Int) {
        if (newDifficulty in 1..3) {
            difficulty = newDifficulty
        }
    }

    fun selectRandomCards() {
        attemps = 0
        isGameWon = false
        firstCardIndex = null
        isProcessing = false
        selectedCards.clear()

        val mappedCards = cards.shuffled().take(difficulty * 3)
        var gameCards = mappedCards + mappedCards
        gameCards = gameCards.shuffled()
        selectedCards.addAll(gameCards.map { it.copy(isFlipped = true) })
    }

    fun flipCard(index: Int) {
        if (isProcessing) return
        val card = selectedCards[index]
        if (!card.isFlipped) return

        selectedCards[index] = card.copy(isFlipped = false)

        if (firstCardIndex == null) {
            firstCardIndex = index
        } else {
            val firstIndex = firstCardIndex!!
            val firstCard = selectedCards[firstIndex]

            attemps++

            if (firstCard.id == card.id) {
                firstCardIndex = null

                if (selectedCards.none() { it.isFlipped }) {
                    isGameWon = true
                }
            } else {
                isProcessing = true
                viewModelScope.launch {
                    delay(1000)
                    selectedCards[firstIndex] = selectedCards[firstIndex].copy(isFlipped = true)
                    selectedCards[index] = selectedCards[index].copy(isFlipped = true)
                    firstCardIndex = null
                    isProcessing = false
                }
            }
        }
    }
}