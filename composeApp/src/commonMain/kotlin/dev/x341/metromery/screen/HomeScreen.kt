package dev.x341.metromery.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.x341.metromery.MetromeryViewModel
import dev.x341.metromery.component.HomeTopBarActions
import dev.x341.metromery.component.MetromeryLayout

private val ColorEasy = Color(0xFF4CAF50)
private val ColorNormal = Color(0xFFFFB300)
private val ColorHard = Color(0xFFE53935)
private val ColorInsane = Color(0xFF8E24AA)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToGame: () -> Unit,
    onNavigateToCards: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: MetromeryViewModel
) {
    MetromeryLayout(
        viewModel = viewModel,
        title = "Metrometry",
        topBarActions = { HomeTopBarActions(onNavigateToSettings) }
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 600.dp)
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Select difficulty",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DifficultyButton(1, "Easy", ColorEasy, viewModel.difficulty, Modifier.weight(1f)) { viewModel.modifyDifficulty(it) }
                    DifficultyButton(2, "Normal", ColorNormal, viewModel.difficulty, Modifier.weight(1f)) { viewModel.modifyDifficulty(it) }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DifficultyButton(3, "Hard", ColorHard, viewModel.difficulty, Modifier.weight(1f)) { viewModel.modifyDifficulty(it) }
                    DifficultyButton(4, "Insane", ColorInsane, viewModel.difficulty, Modifier.weight(1f)) { viewModel.modifyDifficulty(it) }
                }
            }

            Spacer(Modifier.height(40.dp))

            Button(
                onClick = onNavigateToGame,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Play Game", style = MaterialTheme.typography.labelLarge)
            }

            Spacer(Modifier.height(12.dp))

            FilledTonalButton(
                onClick = onNavigateToCards,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Style, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Browse Cards", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Composable
fun DifficultyButton(
    level: Int,
    label: String,
    baseColor: Color,
    currentDifficulty: Int,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    val isSelected = currentDifficulty == level

    Button(
        onClick = { onClick(level) },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) baseColor else baseColor.copy(alpha = 0.15f),
            contentColor = if (isSelected) Color.White else baseColor
        ),
        elevation = if (isSelected) ButtonDefaults.buttonElevation(defaultElevation = 4.dp) else null
    ) {
        Text(text = label, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
    }
}