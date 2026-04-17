package dev.x341.metromery.model

import metromery.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

data class Card (
    val id: Int,
    val name: String,
    val imagePath: DrawableResource?,
    val difficulty: Int = 1,
    var isFlipped: Boolean = true
)

object CardRepository {
    val cards = listOf(
        Card(1, "L1", Res.drawable.L1, 1),
        Card(2, "L2", Res.drawable.L2, 1),
        Card(3, "L3", Res.drawable.L3, 1),
        Card(4, "L4", Res.drawable.L4, 1),
        Card(5, "L5", Res.drawable.L5, 1),
        Card(6, "L6", Res.drawable.L6, 1),
        Card(7, "L7", Res.drawable.L7, 1),
        Card(8, "L8", Res.drawable.L8, 1),
        Card(94, "L9N", Res.drawable.L9N, 1),
        Card(91, "L9S", Res.drawable.L9S, 1),
        Card(104, "L10N", Res.drawable.L10N, 1),
        Card(101, "L10S", Res.drawable.L10S, 1),
        Card(11, "L11", Res.drawable.L11, 1),
        Card(12, "L12", Res.drawable.L12, 1),
    )
}