package dev.x341.metromery

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import dev.x341.metromery.model.Card
import dev.x341.metromery.model.CardRepository
import kotlin.collections.emptyList

class MetromeryViewModel : ViewModel() {
    var difficulty by mutableStateOf(1)
        private set

    val cards = CardRepository.cards
    val selectedCards = mutableStateListOf<Card>()

    var showMessage by mutableStateOf(false)
        private set

    fun modifyShowMessage() {
        showMessage = !showMessage
    }

    fun modifyDifficulty(newDifficulty: Int) {
        if (newDifficulty in 1..3) {
            difficulty = newDifficulty
        }
    }

    fun selectRandomCards() {
        selectedCards.clear()
        val mappedCards = cards.filter { it.difficulty == difficulty }.shuffled()
        selectedCards.addAll(mappedCards.take(difficulty * 4))
    }


}