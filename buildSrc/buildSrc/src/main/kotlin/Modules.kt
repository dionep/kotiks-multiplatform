object Modules {

    object Apps : Group() {
        object Android : Module(group = this, name = "androidApp")
        object Ios : Module(group = this, name = "iosApp")
    }

    object Shared : Group("shared") {
        object Mvi : Module(group = this, name = "mvi")
        object Model : Module(group = this, name = "model")
        object Cats : Module(group = this, name = "cats")
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