package app.data.u5

import util.math.Rational
import util.math.q

enum class Recipe(
    val time: Rational,
    val inputs: List<Component>,
    val products: List<Component>,
) {
    IRON_INGOT(
        2.q,
        listOf(Component(Item.IRON_ORE, 1.q)),
        listOf(Component(Item.IRON_INGOT, 1.q)),
    ),
    IRON_PLATE(
        6.q,
        listOf(Component(Item.IRON_INGOT, 3.q)),
        listOf(Component(Item.IRON_PLATE, 2.q)),
    ),
    IRON_ROD(
        4.q,
        listOf(Component(Item.IRON_INGOT, 1.q)),
        listOf(Component(Item.IRON_ROD, 1.q)),
    ),
    ROTOR(
        15.q,
        listOf(
            Component(Item.IRON_ROD, 5.q),
            Component(Item.SCREW, 25.q)
        ),
        listOf(Component(Item.ROTOR, 1.q)),
    ),
    SCREW(
        6.q,
        listOf(Component(Item.IRON_ROD, 1.q)),
        listOf(Component(Item.SCREW, 4.q)),
    ),
    ;

    data class Component(
        val item: Item,
        val quantity: Rational,
    )
}
