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
    implementation(Dependencies.KtorBackend.Netty)
    implementation(Dependencies.KtorBackend.ServerCore)
    implementation(Dependencies.Logback.Classic)
    implementation(Dependencies.Jetbrains.Kotlinx.Serialization.Json)
    implementation(Dependencies.KtorBackend.Gson)
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