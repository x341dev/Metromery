package dev.x341.metromery.audio

import android.media.MediaPlayer
import dev.x341.metromery.AppContextHolder

actual class AudioPlayer actual constructor() {
    private var mediaPlayer: MediaPlayer? = null

    actual fun playSound(path: String) {
        val context = AppContextHolder.context
        val resId = context.resources.getIdentifier(path, "raw", context.packageName)
        mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer?.start()
    }
}