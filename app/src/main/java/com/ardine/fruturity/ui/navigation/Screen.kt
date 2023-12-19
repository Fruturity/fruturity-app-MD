sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Camera : Screen("camera")
    object History : Screen("history")
    object Bookmark : Screen("bookmark")

    data class Detail(val type: ItemType, val fruitId: String) : Screen("${type.route}/${fruitId}") {
        companion object {
            fun createRoute(type: ItemType, fruitId: String): String {
                return "${type.route}/$fruitId"
            }
        }
    }

    enum class ItemType(val route: String) {
        HISTORY("history"),
        BOOKMARK("bookmark")
    }
}
