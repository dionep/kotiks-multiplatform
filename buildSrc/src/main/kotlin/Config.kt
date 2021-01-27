import com.android.build.gradle.BaseExtension
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

fun Project.setupMultiplatform() {
    plugins.apply("com.android.library")
    plugins.apply("kotlin-multiplatform")
    setupAndroidSdkVersions()

    kotlin {
        android {
            publishLibraryVariants("release", "debug")
        }

        iosX64()
        iosArm64()

        sourceSets {
            commonMain {
                dependencies {
                    implementation(Dependencies.Jetbrains.Kotlin.StdLib.Common)
                }
            }

            commonTest {
                dependencies {
                    implementation(Dependencies.Jetbrains.Kotlin.Test.Common)
                    implementation(Dependencies.Jetbrains.Kotlin.TestAnnotations.Common)
                }
            }

            androidMain {
                dependsOn(commonMain())

                dependencies {
                    implementation(Dependencies.Jetbrains.Kotlin.StdLib.Jdk7)
                }
            }

            androidTest {
                dependsOn(commonTest())

                dependencies {
                    implementation(Dependencies.Jetbrains.Kotlin.Test.Junit)
                }
            }

            iosCommonMain().dependsOn(commonMain())
            iosCommonTest().dependsOn(commonTest())

            iosX64Main().dependsOn(iosCommonMain())
            iosX64Test().dependsOn(iosCommonTest())

            iosArm64Main().dependsOn(iosCommonMain())
            iosArm64Test().dependsOn(iosCommonTest())
        }
    }

}

fun Project.setupAndroidSdkVersions() {
    android {
        compileSdkVersion(29)

        defaultConfig {
            targetSdkVersion(29)
            minSdkVersion(21)
        }
    }
}

// Workaround since iosX64() and iosArm64() function are not resolved if used in a module with Kotlin 1.3.70
fun Project.setupKittensBinaries() {
    fun KotlinNativeTarget.setupIosBinaries() {
        binaries {
            framework {
                baseName = "Cats"
                freeCompilerArgs = freeCompilerArgs.plus("-Xobjc-generics").toMutableList()

                export(project(":shared:mvi"))
            }
        }
    }

    kotlin {
        iosX64().setupIosBinaries()
        iosArm64().setupIosBinaries()
    }
}

fun Project.android(block: BaseExtension.() -> Unit) {
    extensions.getByType<BaseExtension>().block()
}

fun Project.kotlin(block: KotlinMultiplatformExtension.() -> Unit) {
    extensions.getByType<KotlinMultiplatformExtension>().block()
}

typealias SourceSets = NamedDomainObjectContainer<KotlinSourceSet>

fun KotlinMultiplatformExtension.sourceSets(block: SourceSets.() -> Unit) {
    sourceSets.block()
}

private fun SourceSets.getOrCreate(name: String): KotlinSourceSet = findByName(name) ?: create(name)

// common

fun SourceSets.commonMain(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("commonMain").apply(block)

fun SourceSets.commonTest(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("commonTest").apply(block)

// android

fun SourceSets.androidMain(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("androidMain").apply(block)

fun SourceSets.androidTest(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("androidTest").apply(block)

// iosCommon

fun SourceSets.iosCommonMain(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosCommonMain").apply(block)

fun SourceSets.iosCommonTest(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosCommonTest").apply(block)

// iosX64

fun SourceSets.iosX64Main(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosX64Main").apply(block)

fun SourceSets.iosX64Test(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosX64Test").apply(block)

// iosArm64

fun SourceSets.iosArm64Main(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosArm64Main").apply(block)

fun SourceSets.iosArm64Test(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosArm64Test").apply(block)
