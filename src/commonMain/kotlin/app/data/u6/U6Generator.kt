package app.data.u6

import util.math.Rational
import util.math.q

enum class U6Generator(
    val milestone: U6Milestone,
    val displayName: String,
    val cost: Map<U6Item, Rational>,
    val power: Rational,
    val fuels: List<U6Item>,
    val water: Rational = 0.q,
) {
    BIOMASS_BURNER(
        U6Milestone.HUB_UPGRADE_6,
        "Biomass Burner",
        cost = mapOf(
            U6Item.IRON_PLATE to 15.q,
            U6Item.IRON_ROD to 15.q,
            U6Item.WIRE to 25.q,
        ),
        power = 30.q,
        fuels = listOf(
            U6Item.LEAVES,
            U6Item.FLOWER_PETALS,
            U6Item.WOOD,
            U6Item.MYCELIA,
            U6Item.BIOMASS,
            U6Item.SOLID_BIOFUEL,
            U6Item.HOG_REMAINS,
            U6Item.SPITTER_REMAINS,
            U6Item.STINGER_REMAINS,
            U6Item.HATCHER_REMAINS
        )
    ),
    COAL_GENERATOR(
        U6Milestone.COAL_POWER,
        "Coal Generator",
        cost = mapOf(
            U6Item.REINFORCED_IRON_PLATE to 20.q,
            U6Item.ROTOR to 10.q,
            U6Item.CABLE to 30.q,
        ),
        power = 75.q,
        fuels = listOf(U6Item.COAL, U6Item.PETROLEUM_COKE),
        water = 45.q,
    ),

    ;

    init {
        check(fuels.isNotEmpty()) { "Generators must have at least one fuel.`" }
        fuels.filterNot { it.energy > 0.q }.takeUnless { it.isEmpty() }?.let {
            throw IllegalArgumentException("$it cannot be used as fuels.")
        }
    }
}
