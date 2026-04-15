package dev.x341.metromery.audio

import kotlinx.browser.document
import org.w3c.dom.HTMLAudioElement

actual class AudioPlayer actual constructor() {
    @OptIn(ExperimentalWasmJsInterop::class)
    actual fun playSound(path: String) {
        val audio = document.createElement("audio") as HTMLAudioElement
        audio.src = path
        audio.play()
    }
}
