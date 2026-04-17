package dev.x341.metromery.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.x341.metromery.model.Card
import org.jetbrains.compose.resources.painterResource

@Composable
fun CardComponent(
    card: Card,
    isFlipped: Boolean,
    onClick: () -> Unit
) {
    val painter = card.imagePath?.let { painterResource(it) }

    Card(
        colors = if (isFlipped) CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ) else CardDefaults.cardColors(),
        modifier = Modifier.clip(RoundedCornerShape(8.dp)).size(75.dp, 100.dp),
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (!isFlipped) {
                if (painter != null) {
                    Image(
                        painter = painter,
                        contentDescription = card.name,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Text(text = card.name)
                }
            } else {
                Text(text = "Card Back")
            }
        }
    }
}