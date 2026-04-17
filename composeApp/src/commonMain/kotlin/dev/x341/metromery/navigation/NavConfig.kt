package dev.x341.metromery.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val navConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Route.Home::class, Route.Home.serializer())
            subclass(Route.Game::class, Route.Game.serializer())
            subclass(Route.Cards::class, Route.Cards.serializer())
            subclass(Route.Settings::class, Route.Settings.serializer())
        }
    }
}
