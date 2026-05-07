package dev.x341.metromery.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.x341.metromery.MetromeryViewModel
import dev.x341.metromery.component.CardComponent
import dev.x341.metromery.component.GameTopBarActions
import dev.x341.metromery.component.MetromeryLayout

private val difficultyLabel = mapOf(1 to "Easy", 2 to "Normal", 3 to "Hard")

@Composable
fun GameScreen(
    viewModel: MetromeryViewModel,
    onNavigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.selectRandomCards()
    }

    MetromeryLayout(
        viewModel = viewModel,
        title = "Game",
        topBarActions = { GameTopBarActions(viewModel, onNavigateBack) }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(viewModel.selectedCards) { index, card ->
                CardComponent(
                    card = card,
                    isFlipped = card.isFlipped,
                    onClick = { viewModel.flipCard(index) }
                )
            }
        }
    }

    AnimatedVisibility(
        visible = viewModel.isGameWon,
        enter = fadeIn(tween(300)) + slideInVertically(tween(400)) { it / 3 },
        exit = fadeOut() + slideOutVertically { it / 3 }
    ) {
        WinScreen(
            attempts = viewModel.attemps,
            difficulty = viewModel.difficulty,
            onPlayAgain = { viewModel.selectRandomCards() },
            onHome = onNavigateBack
        )
    }
}
