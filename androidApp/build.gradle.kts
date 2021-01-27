plugins {
    id("com.android.application")
    kotlin("android")
}

setupAndroidSdkVersions()

android {
    defaultConfig {
        applicationId = "com.dionep.kotliksmultiplatform.androidApp"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).all {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

}

dependencies {
    implementation(Dependencies.Jetbrains.Kotlin.StdLib.Jdk7)
    implementation(Dependencies.AndroidX.AppCompat.AppCompat)
    implementation(Dependencies.AndroidX.RecyclerView.RecyclerView)
    implementation(Dependencies.AndroidX.ConstraintLayout.ConstraintLayout)
    implementation(Dependencies.AndroidX.SwypeRefreshLayout.SwypeRefreshLayout)
    implementation(Dependencies.AndroidX.Core.Ktx)
    implementation(Dependencies.Google.Android.Material.Material)
    implementation(Dependencies.Squareup.Picasso.Picasso)
}