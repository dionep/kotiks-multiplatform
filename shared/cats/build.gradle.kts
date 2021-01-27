plugins {
    kotlin("plugin.serialization")
}

setupMultiplatform()
setupCatsBinaries()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(Modules.Shared.Mvi.toString()))
                implementation(project(Modules.Shared.Model.toString()))
                implementation(Dependencies.Badoo.Reaktive.Utils)
                implementation(Dependencies.Badoo.Reaktive.Reaktive)
                implementation(Dependencies.Jetbrains.Kotlinx.Serialization.RuntimeCommon)
                implementation(Dependencies.Jetbrains.Kotlin.Plugin.Serialization)
            }
        }

        commonTest {
            dependencies {
                implementation(Dependencies.Badoo.Reaktive.ReaktiveTesting)
            }
        }

        androidMain {
            dependencies {
                implementation(Dependencies.Jetbrains.Kotlinx.Serialization.Runtime)
            }
        }

        iosCommonMain {
            dependencies {
                implementation(Dependencies.Jetbrains.Kotlinx.Serialization.RuntimeNative)
            }
        }
    }
}
