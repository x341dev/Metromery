package dev.x341.metromery.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import dev.x341.metromery.MetromeryViewModel
import dev.x341.metromery.component.CardComponent

@Composable
fun GameScreen(
    viewModel: MetromeryViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.selectRandomCards()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        viewModel.selectedCards.forEach { card ->
            CardComponent(card = card)
        }
    }
}