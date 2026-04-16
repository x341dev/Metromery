package dev.x341.metromery.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.x341.metromery.model.Card
import org.jetbrains.compose.resources.painterResource

@Composable
fun CardComponent(
    card: Card
) {
    var flipped by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.clip(RoundedCornerShape(8.dp)),
        onClick = { flipped = !flipped }

    ) {
        if (!flipped) {
            if (card.imagePath != null) {
                // 画像を表示するコードをここに追加
                Image(
                    painter = painterResource(card.imagePath),
                    contentDescription = card.name
                )
            } else {
                // 画像がない場合の表示コードをここに追加
                Text(text = card.name)
            }
        } else {
            // カードが裏返っている場合の表示コードをここに追加
            Text(text = "Card Back")
        }

    }
}