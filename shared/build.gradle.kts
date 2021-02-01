import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("plugin.serialization")
}

setupMultiplatform()
setupCatsBinaries()

repositories {
    maven(url = "https://dl.bintray.com/touchlabpublic/kotlin") // TODO remove this once Koin is officially published
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                api(project(Modules.Shared.Mvi.toString()))
                api(project(Modules.Shared.Model.toString()))
                api(Dependencies.Koin.Core)

                implementation(Dependencies.Badoo.Reaktive.Utils)
                implementation(Dependencies.Badoo.Reaktive.Reaktive)
                implementation(Dependencies.Badoo.Reaktive.CoroutinesInterop)

                implementation(Dependencies.Ktor.Json)
                implementation(Dependencies.Ktor.Serialization)
                implementation(Dependencies.Ktor.Logging)

                implementation(Dependencies.Jetbrains.Kotlinx.Serialization.Core)
                implementation(Dependencies.Jetbrains.Kotlinx.Serialization.Json)
            }
        }

        commonTest {
            dependencies {
                implementation(Dependencies.Badoo.Reaktive.ReaktiveTesting)
            }
        }

        androidMain {
            dependencies {
                api(Dependencies.Koin.Core)
                implementation(Dependencies.Ktor.Android)
                implementation(Dependencies.AndroidX.Core.Ktx)
                implementation(Dependencies.AndroidX.Fragment.Ktx)
            }
        }

        iosCommonMain {
            dependencies {
                implementation(Dependencies.Ktor.Ios)
            }
        }
    }
}


val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = getTargets().getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)