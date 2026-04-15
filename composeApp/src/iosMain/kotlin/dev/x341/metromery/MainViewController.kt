package dev.x341.metromery

import androidx.compose.ui.window.ComposeUIViewController
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    Napier.base(DebugAntilog())
    return ComposeUIViewController { App() }
}