package app.data

import util.math.Rational
import util.math.q

enum class Item(
    val displayName: String,
    val energy: Rational = 0.q,
) {
    /* Environment */
    BACON_AGARIC("Bacon Agaric"),
    BERYL_NUT("Beryl Nut"),
    BLUE_POWER_SLUG("Blue Power Slug"),
    FLOWER_PETALS("Flower Petals", energy = 100.q),
    HATCHER_REMAINS("Hatcher Remains", energy = 250.q),
    HOG_REMAINS("Hog Remains", energy = 250.q),
    LEAVES("Leaves", energy = 15.q),
    MYCELIA("Mycelia", energy = 20.q),
    PALEBERRY("Paleberry"),
    PURPLE_POWER_SLUG("Purple Power Slug"),
    SPITTER_REMAINS("Spitter Remains", energy = 250.q),
    STINGER_REMAINS("Stinger Remains", energy = 250.q),
    WOOD("Wood", energy = 100.q),
    YELLOW_POWER_SLUG("Yellow Power Slug"),

    /* Tier 0 -- Game Start */
    IRON_ORE("Iron Ore"),
    IRON_INGOT("Iron Ingot"),
    IRON_PLATE("Iron Plate"),
    IRON_ROD("Iron Rod"),
    XENO_ZAPPER("Xeno-Zapper"),

    /* Tier 0 - HUB Upgrade 1 */
    PORTABLE_MINER("Portable Miner"),

    /* Tier 0 -- HUB Upgrade 2 */
    COPPER_ORE("Copper Ore"),
    COPPER_INGOT("Copper Ingot"),
    WIRE("Wire"),
    CABLE("Cable"),

    /* Tier 0 -- HUB Upgrade 3 */
    LIMESTONE("Limestone"),
    CONCRETE("Concrete"),
    SCREW("Screw"),
    REINFORCED_IRON_PLATE("Reinforced Iron Plate"),

    /* Tier 0 -- HUB Upgrade 6 */
    BIOMASS("Biomass", energy = 180.q),

    /* Tier 1 -- Field Research */
    BEACON("Beacon"),

    /* Tier 2 -- Part Assembly */
    COPPER_SHEET("Copper Sheet"),
    ROTOR("Rotor"),
    MODULAR_FRAME("Modular Frame"),
    SMART_PLATING("Smart Plating"),

    /* Tier 2 -- Obstacle Clearing */
    SOLID_BIOFUEL("Solid Biofuel", energy = 450.q),
    CHAINSAW("Chainsaw"),

    /* Tier 2 -- Resource Sink Bonus Program */
    COLOR_CARTRIDGE("Color Cartridge", energy = 900.q),

    /* Tier 3 -- Coal Power */
    COAL("Coal", energy = 300.q),
    WATER("Water"),

    /* Tier 3 -- Basic Steel Production */
    STEEL_INGOT("Steel Ingot"),
    STEEL_BEAM("Steel Beam"),
    STEEL_PIPE("Steel Pipe"),
    VERSATILE_FRAMEWORK("Versatile Framework"),

    /* Tier 4 -- Advanced Steel Production */
    ENCASED_INDUSTRIAL_BEAM("Encased Industrial Beam"),
    STATOR("Stator"),
    MOTOR("Motor"),
    AUTOMATED_WIRING("Automated Wiring"),
    HEAVY_MODULAR_FRAME("Heavy Modular Frame"),

    /* Tier 4 -- Improved Melee Combat */
    XENO_BASHER("Xeno-Basher"),

    /* Tier 5 -- Oil Processing */
    CRUDE_OIL("Crude Oil"),
    PLASTIC("Plastic"),
    RUBBER("Rubber"),
    FUEL("Fuel", energy = 750.q),
    HEAVY_OIL_RESIDUE("Heavy Oil Residue"),
    POLYMER_RESIN("Polymer Resin"),
    PETROLEUM_COKE("Petroleum Coke", energy = 180.q),
    CIRCUIT_BOARD("Circuit Board"),

    /* Tier 5 -- Industrial Manufacturing */
    COMPUTER("Computer"),
    MODULAR_ENGINE("Modular Engine"),
    ADAPTIVE_CONTROL_UNIT("Adaptive Control Unit"),

    /* Tier 6 -- Expanded Power Infrastructure */
    CATERIUM_ORE("Caterium Ore"),

    /* FICSMAS */
    FICSMAS_GIFT("FICSMAS Gift"),
    RED_FICSMAS_ORNAMENT("Red FICSMAS Ornament"),
    FICSMAS_ORNAMENT_BUNDLE("FICSMAS Ornament Bundle"),

    /* Other -- TO BE CLASSIFIED */
    CATERIUM_INGOT("Caterium Ingot"),
    QUICKWIRE("Quickwire"),
    AI_LIMITER("AI Limiter"),
    ZIPLINE("Zipline"),
    RAW_QUARTZ("Raw Quartz"),
    QUARTZ_CRYSTAL("Quartz Crystal"),
    SULFUR("Sulfur"),

    /* Research */
    ALIEN_PROTEIN("Alien Protein"),
    BLADE_RUNNERS("Blade Runners"),
    CRYSTAL_OSCILLATOR("Crystal Oscillator"),
    FABRIC("Fabric"),
    IRON_REBAR("Iron Rebar"),
    MEDICINAL_INHALER("Medicinal Inhaler"),
    ALIEN_DNA_CAPSULE("Alien DNA Capsule"),
    PARACHUTE("Parachute"),
    POWER_SHARD("Power Shard"),
    REBAR_GUN("Rebar Gun"),
    SILICA("Silica"),
    SHATTER_REBAR("Shatter Rebar"),
    STUN_REBAR("Stun Rebar"),
}
