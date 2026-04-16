package dev.x341.metromery.util

import io.github.aakira.napier.Napier

class Logging {
    val TAG: String = "Metromery"

    fun debug(message: String) {
        Napier.d(tag = TAG) { message }
    }

    fun info(message: String) {
        Napier.i(tag = TAG) { message }
    }

    fun warn(message: String) {
        Napier.w(tag = TAG) { message }
    }

    fun error(message: String) {
        Napier.e(tag = TAG) { message }
    }

    fun error(message: String, throwable: Throwable) {
        Napier.e(tag = TAG, throwable = throwable) { message }
    }

    fun error(throwable: Throwable) {
        Napier.e(tag = TAG, throwable = throwable) { "An error occurred" }
    }

    fun critical(message: String, throwable: Throwable? = null) {
        if (throwable != null) {
            Napier.wtf(tag = TAG, throwable = throwable) { message }
        } else {
            Napier.wtf(tag = TAG) { message }
        }
    }
}