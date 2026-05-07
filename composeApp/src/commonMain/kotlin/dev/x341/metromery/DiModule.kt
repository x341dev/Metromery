package dev.x341.metromery

import com.russhwolf.settings.Settings
import org.koin.dsl.module

val appModule = module {
    single<Settings> { Settings() }

    factory { MetromeryViewModel(get()) }
}