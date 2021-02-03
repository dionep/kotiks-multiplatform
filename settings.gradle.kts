enableFeaturePreview("GRADLE_METADATA")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
}


include(":model")
include(":shared:mvi")
include(":androidApp")
include(":backend")
