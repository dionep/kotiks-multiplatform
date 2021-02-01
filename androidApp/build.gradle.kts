plugins {
    id("com.android.application")
    kotlin("android")
}

setupAndroidSdkVersions()

repositories {
    maven(url = "https://dl.bintray.com/touchlabpublic/kotlin") // TODO remove this once Koin is officially published
}


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

    buildFeatures.viewBinding = true

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
    implementation(Dependencies.RereKt.Rekukler)
    implementation(Dependencies.AndroidX.Fragment.Ktx)
    implementation(Dependencies.AndroidX.Lifecycle.CommonJava8)
    implementation(project(Modules.Shared.toString()))
    implementation(Dependencies.Koin.Core)
}