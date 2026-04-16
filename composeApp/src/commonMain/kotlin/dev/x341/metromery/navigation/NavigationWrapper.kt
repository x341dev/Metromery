package dev.x341.metromery.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.x341.metromery.MetromeryViewModel
import dev.x341.metromery.screen.GameScreen
import dev.x341.metromery.screen.HomeScreen
import dev.x341.metromery.screen.SettingsScreen

@Composable
fun NavigationWrapper() {
    val sharedViewModel = viewModel { MetromeryViewModel() }
    val backStack = rememberNavBackStack(navConfig, Route.Home)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.Home> {
                HomeScreen(
                    onNavigateToGame = { backStack.add(Route.Game) },
                    viewModel = sharedViewModel
                )
            }

            entry<Route.Game> {
                GameScreen(viewModel = sharedViewModel)
            }
        }
    )
}