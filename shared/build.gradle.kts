import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

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
            dependencies {}
        }

        iosCommonMain {
            dependencies {}
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