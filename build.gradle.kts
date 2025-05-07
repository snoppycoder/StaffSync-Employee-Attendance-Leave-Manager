plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("org.jetbrains.kotlin.kapt") version "2.2.0-Beta2" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.0-Beta2" apply false // Match app module
}