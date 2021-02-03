object Modules {

    object Apps : Group() {
        object Android : Module(group = this, name = "androidApp")
        object Ios : Module(group = this, name = "iosApp")
    }

    object Model : Module(group = Group(), name = "model")

    object Shared : Group("shared") {
        object Mvi : Module(group = this, name = "mvi")

        override fun toString() = ":shared"
    }

    open class Group(val groupName: String = "")

    open class Module private constructor(
        private val notation: String
    ) : CharSequence by notation {
        constructor(
            group: Group,
            name: String
        ) : this(":${group.groupName}:$name")

        override fun toString(): String = notation
    }
}