plugins {
    kotlin("plugin.serialization")
}

setupMultiplatform()
setupKittensBinaries()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":shared:mvi"))
                implementation(Dependencies.Badoo.Reaktive.Utils)
                implementation(Dependencies.Badoo.Reaktive.Reaktive)
                implementation(Dependencies.Jetbrains.Kotlinx.Serialization.RuntimeCommon)
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
