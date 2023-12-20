sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Camera : Screen("camera")
    object History : Screen("history")
    object Bookmark : Screen("bookmark")
    object Detail : Screen("detail")

    companion object {
        fun createRoute(type: ItemType, fruitId: String): String {
            return "${type.route}/detail/$fruitId"
        }
    }

    enum class ItemType(val route: String) {
        HISTORY("history"),
        BOOKMARK("bookmark")
    }
}
