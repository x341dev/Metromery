package dev.x341.metromery.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.x341.metromery.MetromeryViewModel

@Composable
fun HomeTopBarActions(onNavigateToSettings: () -> Unit) {
    IconButton(onClick = onNavigateToSettings) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun GameTopBarActions(
    viewModel: MetromeryViewModel,
    onNavigateBack: () -> Unit
) {
    val difficultyLabel = mapOf(1 to "Easy", 2 to "Normal", 3 to "Hard", 4 to "Insane")

    Surface(
        shape = RoundedCornerShape(50),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
    ) {
        Text(
            text = difficultyLabel[viewModel.difficulty] ?: "Easy",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }

    IconButton(onClick = { viewModel.selectRandomCards() }) {
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "Restart",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }

    IconButton(onClick = onNavigateBack) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun CardsTopBarActions(onNavigateBack: () -> Unit) {
    IconButton(onClick = onNavigateBack) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}