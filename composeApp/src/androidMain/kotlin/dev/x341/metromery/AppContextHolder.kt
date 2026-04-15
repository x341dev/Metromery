package dev.x341.metromery

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object AppContextHolder {
    private var _context: Context? = null
    val context: Context
        get() = _context
            ?: throw IllegalStateException("AppContextHolder is not initialized.")
    fun init(context: Context) {
        _context = context.applicationContext
    }
}