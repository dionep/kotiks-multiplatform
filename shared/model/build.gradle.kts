plugins {
    kotlin("plugin.serialization")
}

setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation (Dependencies.Jetbrains.Kotlin.Plugin.Serialization)
            }
        }
    }
}