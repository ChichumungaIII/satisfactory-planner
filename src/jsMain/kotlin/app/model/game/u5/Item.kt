package app.model.game.u5

enum class Item(
    private val displayName: String,
) {
    IRON_INGOT("Iron Ingot"),
    IRON_ORE("Iron Ore"),
    IRON_PLATE("Iron Plate"),
    IRON_ROD("Iron Rod"),
    ROTOR("Rotor"),
    SCREW("Screw"),
    ;

    fun displayName() = displayName
}
