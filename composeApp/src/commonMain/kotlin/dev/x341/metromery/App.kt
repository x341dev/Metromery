package dev.x341.metromery

import androidx.compose.runtime.*
import dev.x341.metromery.navigation.NavigationWrapper
import dev.x341.metromery.theme.MetromeryTheme

@Composable
fun App() {
    var isDarkMode by remember { mutableStateOf(false) }
    MetromeryTheme(darkTheme = isDarkMode) {
        NavigationWrapper(
            isDarkMode = isDarkMode,
            onToggleDarkMode = { isDarkMode = !isDarkMode }
        )
    }
}
