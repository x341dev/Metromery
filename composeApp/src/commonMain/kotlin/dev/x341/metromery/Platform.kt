package dev.x341.metromery

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform