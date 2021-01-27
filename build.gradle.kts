buildscript {
    val kotlin_version by extra("1.4.21")
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha04")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlin_version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
    }
}

plugins {
    kotlin("plugin.serialization")
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}