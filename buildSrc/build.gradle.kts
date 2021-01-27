plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

dependencies {
    implementation(Dependencies.Jetbrains.Kotlin.Plugin.Gradle)
    implementation(Dependencies.Jetbrains.Kotlin.Plugin.Serialization)
    implementation(Dependencies.Android.Tools.Build.Gradle)
//    implementation(Dependencies.TouchLab.KotlinXcodeSync)
}

kotlin {
    // Add Deps to compilation, so it will become available in main project
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}