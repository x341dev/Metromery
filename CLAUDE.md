# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Android debug APK
./gradlew :composeApp:assembleDebug

# Desktop (JVM) — run directly
./gradlew :composeApp:run

# Web (WASM — modern browsers, faster)
./gradlew :composeApp:wasmJsBrowserDevelopmentRun

# Web (Kotlin/JS — broader browser support)
./gradlew :composeApp:jsBrowserDevelopmentRun

# Run common tests
./gradlew :composeApp:allTests

# iOS — open iosApp/iosApp.xcodeproj in Xcode
```

## Architecture

This is a **Kotlin Multiplatform (KMP)** project using **Compose Multiplatform** for UI. A single `composeApp` module targets Android, iOS, Desktop (JVM), and Web (JS + WASM).

**Source sets in `composeApp/src/`:**
- `commonMain` — shared UI and business logic (all platforms consume this)
- `androidMain`, `iosMain`, `jvmMain`, `jsMain`, `wasmJsMain` — platform-specific entry points and `actual` implementations
- `webMain` — shared code between `jsMain` and `wasmJsMain`
- `commonTest` — cross-platform tests

**Platform abstraction via expect/actual:**
- `commonMain/Platform.kt` declares `expect interface Platform` and `expect fun getPlatform()`
- Each platform source set provides an `actual` implementation (e.g., `Platform.android.kt` returns the Android SDK version)

**Entry points per platform:**
- Android: `androidMain/MainActivity.kt` (`ComponentActivity` + `setContent { App() }`)
- Desktop: `jvmMain/main.kt` (`application { Window { App() } }`)
- iOS: `iosMain/MainViewController.kt` (called from Swift)
- Web: `wasmJsMain/` or `jsMain/` bootstrap

**Shared UI root:** `commonMain/App.kt` — the `App()` composable is the single UI entry point used by all platforms.

## Key Configuration

- **Package / App ID:** `dev.x341.metromery`
- **Kotlin:** 2.3.20 · **Compose Multiplatform:** 1.10.3 · **AGP:** 8.11.2
- **Android:** minSdk 24, targetSdk/compileSdk 36, JVM 11
- **Dependency versions:** managed in `gradle/libs.versions.toml` (Version Catalog)
- **Material Design 3** is the UI component library across all platforms
