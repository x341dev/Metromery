package dev.x341.metromery.navigation

import androidx.navigation3.runtime.NavKey
import dev.x341.metromery.MetromeryViewModel
import kotlinx.serialization.Serializable

sealed class Route : NavKey {
    @Serializable
    data object Home : Route()
    @Serializable
    data object Game : Route()
}