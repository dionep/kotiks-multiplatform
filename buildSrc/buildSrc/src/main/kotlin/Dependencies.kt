object Dependencies {

    object Jetbrains {
        object Kotlin : Group(name = "org.jetbrains.kotlin") {
            private const val version = "1.4.20"

            object Plugin {
                object Gradle :
                    Dependency(group = Kotlin, name = "kotlin-gradle-plugin", version = version)

                object Serialization :
                    Dependency(group = Kotlin, name = "kotlin-serialization", version = version)
            }

            object StdLib {
                object Common :
                    Dependency(group = Kotlin, name = "kotlin-stdlib-common", version = version)

                object Jdk7 :
                    Dependency(group = Kotlin, name = "kotlin-stdlib-jdk7", version = version)
            }

            object Test {
                object Common :
                    Dependency(group = Kotlin, name = "kotlin-test-common", version = version)

                object Junit :
                    Dependency(group = Kotlin, name = "kotlin-test-junit", version = version)
            }

            object TestAnnotations {
                object Common : Dependency(
                    group = Kotlin,
                    name = "kotlin-test-annotations-common",
                    version = version
                )
            }
        }

        object Kotlinx : Group(name = "org.jetbrains.kotlinx") {
            object Serialization {
                private const val version = "1.0.1"

                object Core : Dependency(group = Kotlinx, name = "kotlinx-serialization-core", version = version)
                object Json : Dependency(group = Kotlinx, name = "kotlinx-serialization-json", version = version)
            }
        }
    }

    object Android {
        object Tools {
            object Build : Group(name = "com.android.tools.build") {
                object Gradle : Dependency(group = Build, name = "gradle", version = "4.1.2")
            }
        }
    }

    object AndroidX {
        object Core : Group(name = "androidx.core") {
            object Ktx : Dependency(group = Core, name = "core-ktx", version = "1.1.0")
        }

        object AppCompat : Group(name = "androidx.appcompat") {
            object AppCompat :
                Dependency(group = AndroidX.AppCompat, name = "appcompat", version = "1.1.0")
        }

        object RecyclerView : Group(name = "androidx.recyclerview") {
            object RecyclerView :
                Dependency(group = AndroidX.RecyclerView, name = "recyclerview", version = "1.1.0")
        }
        object Fragment : Group(name = "androidx.fragment") {
            object Ktx : Dependency(group = this, name = "fragment-ktx", version = "1.2.5")
        }

        object Lifecycle : Group(name = "androidx.lifecycle") {
            object CommonJava8 : Dependency(group = this, name = "lifecycle-common-java8", version = "2.2.0")
        }

        object ConstraintLayout : Group(name = "androidx.constraintlayout") {
            object ConstraintLayout : Dependency(
                group = AndroidX.ConstraintLayout,
                name = "constraintlayout",
                version = "1.1.3"
            )
        }

        object SwypeRefreshLayout : Group(name = "androidx.swiperefreshlayout") {
            object SwypeRefreshLayout : Dependency(group = AndroidX.SwypeRefreshLayout, name = "swiperefreshlayout", version = "1.0.0")
        }
    }

    object Google {
        object Android {
            object Material : Group(name = "com.google.android.material") {
                object Material :
                    Dependency(group = Android.Material, name = "material", version = "1.1.0")
            }
        }
    }

    object Badoo {
        object Reaktive : Group(name = "com.badoo.reaktive") {
            private const val version = "1.1.17"

            object Reaktive :
                Dependency(group = Badoo.Reaktive, name = "reaktive", version = version)

            object ReaktiveTesting :
                Dependency(group = Badoo.Reaktive, name = "reaktive-testing", version = version)

            object Utils : Dependency(group = Badoo.Reaktive, name = "utils", version = version)
        }
    }

    object TouchLab : Group(name = "co.touchlab") {
        object KotlinXcodeSync :
            Dependency(group = TouchLab, name = "kotlinxcodesync", version = "0.2")
    }

    object Squareup {
        object Picasso : Group(name = "com.squareup.picasso") {
            object Picasso : Dependency(group = Squareup.Picasso, name = "picasso", version = "2.71828")
        }
    }

    object RereKt : Group(name = "com.github.Rere-kt") {
        object Rekukler : Dependency(group = this, name = "Rekukler", version = "1.0.0-alpha35")
    }

    open class Group(val name: String)

    open class Dependency private constructor(
        private val notation: String
    ) : CharSequence by notation {
        constructor(
            group: Group,
            name: String,
            version: String
        ) : this("${group.name}:$name:$version")

        override fun toString(): String = notation
    }
}
