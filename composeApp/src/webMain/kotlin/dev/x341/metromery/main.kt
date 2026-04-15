package dev.x341.metromery = ComposeUIViewController { App() } = ComposeUIViewController { App() }

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    Napier.base(DebugAntilog())
    ComposeViewport {
        App()
    }
}