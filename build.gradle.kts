// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    //Dagger Hilt
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.android.library) apply false

    // Kover plugin
    id("org.jetbrains.kotlinx.kover") version "0.8.3" apply false

    id("io.gitlab.arturbosch.detekt").version("1.23.6")
}