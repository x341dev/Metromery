package dev.x341.metromery

import androidx.compose.runtime.*
import com.russhwolf.settings.Settings
import dev.x341.metromery.navigation.NavigationWrapper
import dev.x341.metromery.theme.MetromeryTheme
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.dsl.koinConfiguration

@Composable
fun App() {
    KoinApplication(
        configuration = koinConfiguration(declaration = { modules(appModule) }),
        content = {
            val settings: Settings = koinInject()
            var isDarkMode by remember { mutableStateOf(settings.getBoolean("dark_mode", false)) }
            MetromeryTheme(darkTheme = isDarkMode) {
                NavigationWrapper(
                    isDarkMode = isDarkMode,
                    onToggleDarkMode = {
                        isDarkMode = !isDarkMode

                        settings.putBoolean("dark_mode", isDarkMode)
                    },
                    settings
                )
            }
        }
    )
}
