package dev.x341.metromery.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tries: ${viewModel.attemps}",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(viewModel.selectedCards) { index, card ->
                CardComponent(
                    card = card,
                    isFlipped = card.isFlipped,
                    onClick = { viewModel.flipCard(index) })
            }
        }
    }

    // Temporary victory dialog
    if (viewModel.isGameWon) {
        AlertDialog(
            onDismissRequest = {  },
            title = { Text(text = "You win!") },
            text = { Text(text = "Tries: ${viewModel.attemps}") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.selectRandomCards()
                    }
                ) {
                    Text("Play Again")
                }
            }
        )
    }
}