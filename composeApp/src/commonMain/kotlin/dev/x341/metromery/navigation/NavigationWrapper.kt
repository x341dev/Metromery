package dev.x341.metromery.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.x341.metromery.MetromeryViewModel
import dev.x341.metromery.screen.CardsScreen
import dev.x341.metromery.screen.GameScreen
import dev.x341.metromery.screen.HomeScreen
import dev.x341.metromery.screen.SettingsScreen

@Composable
fun NavigationWrapper(
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit
) {
    val sharedViewModel = viewModel { MetromeryViewModel() }
    val backStack = rememberNavBackStack(navConfig, Route.Home)

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = entryProvider {
                    entry<Route.Home> {
                        HomeScreen(
                            onNavigateToGame = { backStack.add(Route.Game) },
                            onNavigateToCards = { backStack.add(Route.Cards) },
                            onNavigateToSettings = { backStack.add(Route.Settings) },
                            viewModel = sharedViewModel
                        )
                    }
                    entry<Route.Game> {
                        GameScreen(viewModel = sharedViewModel)
                    }
                    entry<Route.Cards> {
                        CardsScreen(viewModel = sharedViewModel)
                    }
                    entry<Route.Settings> {
                        SettingsScreen(
                            isDarkMode = isDarkMode,
                            onToggleDarkMode = onToggleDarkMode,
                            onNavigateBack = { backStack.removeLastOrNull() }
                        )
                    }
                }
            )
        }
    }
}
