package dev.x341.metromery

import android.app.Application

class MetromeryApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppContextHolder.init(this)
    }
}