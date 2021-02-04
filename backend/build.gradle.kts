plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization")
}

application {
    mainClassName = "com.kotiksmultiplatform.backend.MainKt"
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(Dependencies.Backend.Ktor.Netty)
    implementation(Dependencies.Backend.Ktor.ServerCore)
    implementation(Dependencies.Backend.Ktor.Gson)
    implementation(Dependencies.Backend.Database.JetbrainsExposed.Exposed)
    implementation(Dependencies.Backend.Database.H2Database.H2)
    implementation(Dependencies.Logback.Classic)
    implementation(Dependencies.Jetbrains.Kotlinx.Serialization.Json)
    implementation(Dependencies.Ktor.Json)
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    create("stage") {
        dependsOn(getByName("installDist"))
    }
}