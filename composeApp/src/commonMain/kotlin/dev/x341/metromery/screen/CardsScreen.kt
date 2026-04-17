package dev.x341.metromery.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.x341.metromery.MetromeryViewModel
import dev.x341.metromery.component.CardComponent

@Composable
fun CardsScreen(
    viewModel: MetromeryViewModel
) {
    val flippedStates = remember { mutableStateMapOf<Int, Boolean>() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.cards) { card ->
                val isFlipped = flippedStates[card.id] ?: false
                CardComponent(
                    card = card,
                    isFlipped = isFlipped,
                    onClick = { flippedStates[card.id] = !isFlipped }
                )
            }
        }
    }
}