package app.data.u6

import util.math.Rational
import util.math.q

enum class U6ProductionBuilding(
    val milestone: U6Milestone,
    val displayName: String,
    val cost: Map<U6Item, Rational>,
    val power: Rational = 0.q,
) {
    CRAFT_BENCH(
        U6Milestone.GAME_START,
        "Craft Bench",
        cost = mapOf(
            U6Item.IRON_PLATE to 3.q,
            U6Item.IRON_ROD to 2.q,
        )
    ),
    EQUIPMENT_WORKSHOP(
        U6Milestone.HUB_UPGRADE_1,
        "Equipment Workshop",
        cost = mapOf(
            U6Item.IRON_PLATE to 6.q,
            U6Item.IRON_ROD to 4.q,
        ),
    ),
    SMELTER(
        U6Milestone.HUB_UPGRADE_2,
        "Smelter",
        cost = mapOf(
            U6Item.IRON_ROD to 5.q,
            U6Item.WIRE to 8.q,
        ),
        power = 4.q,
    ),
    CONSTRUCTOR(
        U6Milestone.HUB_UPGRADE_3,
        "Constructor",
        cost = mapOf(
            U6Item.REINFORCED_IRON_PLATE to 2.q,
            U6Item.CABLE to 8.q,
        ),
        power = 4.q,
    ),
    ASSEMBLER(
        U6Milestone.PART_ASSEMBLY,
        "Assembler",
        cost = mapOf(
            U6Item.REINFORCED_IRON_PLATE to 8.q,
            U6Item.ROTOR to 4.q,
            U6Item.CABLE to 10.q,
        ),
        power = 15.q,
    ),
    AWESOME_SINK(
        U6Milestone.RESOURCE_SINK_BONUS_PROGRAM,
        "AWESOME Sink",
        cost = mapOf(
            U6Item.REINFORCED_IRON_PLATE to 15.q,
            U6Item.CABLE to 30.q,
            U6Item.CONCRETE to 45.q,
        ),
    ),
    FOUNDRY(
        U6Milestone.BASIC_STEEL_PRODUCTION,
        "Foundry",
        cost = mapOf(
            U6Item.MODULAR_FRAME to 10.q,
            U6Item.ROTOR to 10.q,
            U6Item.CONCRETE to 20.q,
        ),
        power = 16.q,
    ),
    REFINERY(
        U6Milestone.OIL_PROCESSING,
        "Refinery",
        cost = mapOf(
            U6Item.MOTOR to 10.q,
            U6Item.ENCASED_INDUSTRIAL_BEAM to 10.q,
            U6Item.STEEL_PIPE to 30.q,
            U6Item.COPPER_SHEET to 20.q,
        ),
        power = 30.q,
    ),
    MANUFACTURER(
        U6Milestone.INDUSTRIAL_MANUFACTURING, // TODO
        "Manufacturer",
        cost = mapOf(), // TODO
        power = 0.q, // TODO
    ),
}
