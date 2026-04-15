package dev.x341.metromery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.x341.metromery.screen.HomeScreen
import dev.x341.metromery.screen.SettingsScreen

@Composable
fun NavigationWrapper() {
    val backStack = rememberNavBackStack(navConfig, Route.Home)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.Home> {
                HomeScreen(
                    onNavigateSettings = { backStack.add(Route.Settings(id = "test")) },
                )
            }

            entry<Route.Settings> { key ->
                SettingsScreen(id = key.id, onNavigateBack = { backStack.removeLastOrNull() })
            }
        }
    )
}