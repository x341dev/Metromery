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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.x341.metromery.model.Card
import org.jetbrains.compose.resources.painterResource

@Composable
fun CardComponent(
    card: Card,
    initialFlipped: Boolean = true
) {
    var flipped by remember { mutableStateOf(initialFlipped) }

    Card(
        colors = if (flipped) CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ) else CardDefaults.cardColors(),
        modifier = Modifier.clip(RoundedCornerShape(8.dp)).size(100.dp, 150.dp),
        onClick = { flipped = !flipped },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (!flipped) {
                val painter = card.imagePath?.let { painterResource(it) }
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