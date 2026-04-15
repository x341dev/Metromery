package dev.x341.metromery.audio

import javazoom.jl.player.Player

actual class AudioPlayer actual constructor() {
    actual fun playSound(path: String) {
        val inputStream = AudioPlayer::class.java.getResourceAsStream(path)
        val player = Player(inputStream)
        Thread { player.play() }.start()
    }
}
