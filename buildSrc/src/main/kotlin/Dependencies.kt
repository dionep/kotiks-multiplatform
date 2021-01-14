object Versions {
    const val Kotlin = "1.4.10"
    const val Ktor = "1.4.10"
    const val KtorBackend = "1.4.0"
    const val Reaktive = "1.1.17"
    const val Koin = "3.0.1-alpha-2"
    const val Serialization = "1.0.0"
    const val Logback = "1.2.3"
    const val CoreKtx = "1.3.2"
    const val AppCompat = "1.2.0"
    const val IosCoroutines = "1.3.9-native-mt"

    const val GradlePlugin = "7.0.0-alpha04"
    const val KotlinGradlePlugin = "1.4.21"
    const val Material = "1.2.1"
}

object Dependencies {

    object Common {
        data class MultiplatformDependency(
            val android: String,
            val ios: String,
            val common: String
        )

        object Reaktive {
            const val Core = "com.badoo.reaktive:reaktive:${Versions.Reaktive}"
            const val Utils = "com.badoo.reaktive:utils:${Versions.Reaktive}"
            const val CoroutinesInterop = "com.badoo.reaktive:coroutines-interop:${Versions.Reaktive}"
        }

        object Ktor {
            val client = MultiplatformDependency(
                android = "io.ktor:ktor-client-okhttp:${Versions.Ktor}",
                common = "io.ktor:ktor-client-core:${Versions.Ktor}",
                ios = "io.ktor:ktor-client-ios:${Versions.Ktor}"
            )
            const val Json = "io.ktor:ktor-client-json:${Versions.Ktor}"
            const val Serialization = "io.ktor:ktor-client-serialization:${Versions.Ktor}"
            const val Logging = "io.ktor:ktor-client-logging:${Versions.Ktor}"
        }

        const val Logback = "ch.qos.logback:logback-classic:${Versions.Logback}"
        const val Koin = "org.koin:koin-core:${Versions.Koin}"
        const val Serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.Serialization}"

        const val GradlePlugin = "com.android.tools.build:gradle:${Versions.GradlePlugin}"
        const val KotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KotlinGradlePlugin}"
    }

    object Android {
        object AndroidX {
            val CoreKtx = "androidx.core:core-ktx:${Versions.CoreKtx}"
            val AppCompat = "androidx.appcompat:appcompat:${Versions.AppCompat}"
        }
        const val Material = "com.google.android.material:material:${Versions.Material}"
    }

    object iOS {
        const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.IosCoroutines}"
    }
}

object AndroidConfig {
    const val applicationId = "com.dionep.kotiksmultiplatform.androidApp"
    const val compileSdkVersion = 30
    const val targetSdkVersion = 30
    const val minSdkVersion = 24
}