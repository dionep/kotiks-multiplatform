plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization")
}

application {
    mainClassName = "com.kotiksmultiplatform.backend.MainKt"
}

dependencies {
//    implementation(project(Modules.Model.toString()))
    implementation(kotlin("stdlib"))
    implementation(Dependencies.KtorBackend.Netty)
    implementation(Dependencies.KtorBackend.ServerCore)
    implementation(Dependencies.Logback.Classic)
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    create("stage") {
        dependsOn(getByName("installDist"))
    }
}