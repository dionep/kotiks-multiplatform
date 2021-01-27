setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Dependencies.Badoo.Reaktive.Utils)
                implementation(Dependencies.Badoo.Reaktive.Reaktive)
            }
        }
    }
}