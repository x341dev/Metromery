package dev.x341.metromery.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.x341.metromery.MetromeryViewModel

private val ColorEasy = Color(0xFF4CAF50)
private val ColorNormal = Color(0xFFFFB300)
private val ColorHard = Color(0xFFE53935)
private val ColorInsane = Color(0xFF8E24AA)

@Composable
fun MetromeryLayout(
    viewModel: MetromeryViewModel,
    title: String,
    topBarActions: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val currentThemeColor = when (viewModel.difficulty) {
        1 -> ColorEasy
        2 -> ColorNormal
        3 -> ColorHard
        4 -> ColorInsane
        else -> MaterialTheme.colorScheme.primary
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Draw the top bar
        MetromeryTopBar(title, topBarActions)

        // Draw the main content
        val dynamicColorScheme = MaterialTheme.colorScheme.copy(primary = currentThemeColor)

        MaterialTheme(colorScheme = dynamicColorScheme) {
            Box(modifier = Modifier.weight(1f).fillMaxSize(), contentAlignment = Alignment.Center) {

                Box(modifier = Modifier.widthIn(max = 600.dp).fillMaxSize()) {
                    content()
                }
            }
        }
    }
}