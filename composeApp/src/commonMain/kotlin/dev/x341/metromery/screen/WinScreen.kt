package dev.x341.metromery.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun WinScreen(
    attempts: Int,
    difficulty: Int,
    onPlayAgain: () -> Unit,
    onHome: () -> Unit
) {
    val pairs = difficulty * 3
    val stars = when {
        attempts <= pairs + 2 -> 3
        attempts <= pairs * 2 -> 2
        else -> 1
    }

    var trophyVisible by remember { mutableStateOf(false) }
    val trophyScale by animateFloatAsState(
        targetValue = if (trophyVisible) 1f else 0f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = "trophyScale"
    )

    LaunchedEffect(Unit) { trophyVisible = true }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.97f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            // Trophy icon with bounce animation
            Icon(
                imageVector = Icons.Default.EmojiEvents,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(96.dp)
                    .scale(trophyScale)
            )

            Text(
                text = "You Won!",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            // Star rating
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(3) { index ->
                    Icon(
                        imageVector = if (index < stars) Icons.Default.Star else Icons.Default.StarBorder,
                        contentDescription = null,
                        tint = if (index < stars) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            // Stats card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem(label = "Attempts", value = "$attempts")
                    StatItem(label = "Pairs", value = "$pairs")
                    StatItem(
                        label = "Accuracy",
                        value = "${(pairs * 100 / attempts.coerceAtLeast(1))}%"
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = onPlayAgain,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Play Again", style = MaterialTheme.typography.labelLarge)
            }

            OutlinedButton(
                onClick = onHome,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Home, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Back to Home", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Composable
private fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
