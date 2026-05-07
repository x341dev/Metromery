package dev.x341.metromery.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
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
import dev.x341.metromery.component.CardsTopBarActions
import dev.x341.metromery.component.MetromeryLayout

@Composable
fun CardsScreen(
    viewModel: MetromeryViewModel,
    onNavigateBack: () -> Unit
) {
    val flippedStates = remember { mutableStateMapOf<Int, Boolean>() }

    MetromeryLayout(
        viewModel = viewModel,
        title = "Cards",
        topBarActions = { CardsTopBarActions(onNavigateBack) }
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
                    onClick = { }
                )
            }
        }
    }
}