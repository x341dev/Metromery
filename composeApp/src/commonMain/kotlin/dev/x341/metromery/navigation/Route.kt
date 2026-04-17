package dev.x341.metromery.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Route : NavKey {
    @Serializable
    data object Home : Route()
    @Serializable
    data object Game : Route()
    @Serializable
    data object Cards : Route()
    @Serializable
    data object Settings : Route()
}
