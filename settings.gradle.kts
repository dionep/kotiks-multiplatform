enableFeaturePreview("GRADLE_METADATA")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
}


include(":shared:mvi")
include(":androidApp")
include(":shared:model")
