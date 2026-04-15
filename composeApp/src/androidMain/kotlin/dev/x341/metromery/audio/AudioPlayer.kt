package dev.x341.metromery.audio

import android.media.MediaPlayer
import dev.x341.metromery.AppContextHolder
import dev.x341.metromery.R

actual class AudioPlayer actual constructor() {
    private var mediaPlayer: MediaPlayer? = null

    actual fun playSound(path: String) {
        val context = AppContextHolder.context
        mediaPlayer = MediaPlayer.create(context, R.raw.file)
        mediaPlayer?.start()
    }
}