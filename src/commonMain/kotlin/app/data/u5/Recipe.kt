package app.data.u5

import util.math.Rational
import util.math.q

enum class Recipe(
    val time: Rational,
    val products: List<Component>,
    val inputs: List<Component>,
) {
    CATERIUM_INGOT(
        4.q,
        listOf(
            Component(Item.CATERIUM_INGOT, 1.q),
        ),
        listOf(
            Component(Item.CATERIUM_ORE, 3.q),
        ),
    ),
    COPPER_INGOT(
        2.q,
        listOf(
            Component(Item.COPPER_INGOT, 1.q),
        ),
        listOf(
            Component(Item.COPPER_ORE, 1.q),
        ),
    ),
    IRON_INGOT(
        2.q,
        listOf(
            Component(Item.IRON_INGOT, 1.q),
        ),
        listOf(
            Component(Item.IRON_ORE, 1.q),
        ),
    ),
    PURE_ALUMINUM_INGOT(
        2.q,
        listOf(
            Component(Item.ALUMINUM_INGOT, 1.q),
        ),
        listOf(
            Component(Item.ALUMINUM_SCRAP, 2.q),
        ),
    ),
    IRON_ALLOY_INGOT(
        6.q,
        listOf(
            Component(Item.IRON_INGOT, 5.q),
        ),
        listOf(
            Component(Item.IRON_ORE, 2.q),
            Component(Item.COPPER_ORE, 2.q),
        ),
    ),
    COPPER_ALLOY_INGOT(
        12.q,
        listOf(
            Component(Item.COPPER_INGOT, 20.q),
        ),
        listOf(
            Component(Item.COPPER_ORE, 10.q),
            Component(Item.IRON_ORE, 5.q),
        ),
    ),
    STEEL_INGOT(
        4.q,
        listOf(
            Component(Item.STEEL_INGOT, 3.q),
        ),
        listOf(
            Component(Item.IRON_ORE, 3.q),
            Component(Item.COAL, 3.q),
        ),
    ),
    COKE_STEEL_INGOT(
        12.q,
        listOf(
            Component(Item.STEEL_INGOT, 20.q),
        ),
        listOf(
            Component(Item.IRON_ORE, 15.q),
            Component(Item.PETROLEUM_COKE, 15.q),
        ),
    ),
    COMPACTED_STEEL_INGOT(
        16.q,
        listOf(
            Component(Item.STEEL_INGOT, 10.q),
        ),
        listOf(
            Component(Item.IRON_ORE, 6.q),
            Component(Item.COMPACTED_COAL, 3.q),
        ),
    ),
    SOLID_STEEL_INGOT(
        3.q,
        listOf(
            Component(Item.STEEL_INGOT, 3.q),
        ),
        listOf(
            Component(Item.IRON_INGOT, 2.q),
            Component(Item.COAL, 2.q),
        ),
    ),
    ALUMINUM_INGOT(
        4.q,
        listOf(
            Component(Item.ALUMINUM_INGOT, 4.q),
        ),
        listOf(
            Component(Item.ALUMINUM_SCRAP, 6.q),
            Component(Item.SILICA, 5.q),
        ),
    ),
    IRON_PLATE(
        6.q,
        listOf(
            Component(Item.IRON_PLATE, 2.q),
        ),
        listOf(
            Component(Item.IRON_INGOT, 3.q),
        ),
    ),
    IRON_ROD(
        4.q,
        listOf(
            Component(Item.IRON_ROD, 1.q),
        ),
        listOf(
            Component(Item.IRON_INGOT, 1.q),
        ),
    ),
    STEEL_ROD(
        5.q,
        listOf(
            Component(Item.IRON_ROD, 4.q),
        ),
        listOf(
            Component(Item.STEEL_INGOT, 1.q),
        ),
    ),
    SCREW(
        6.q,
        listOf(
            Component(Item.SCREW, 4.q),
        ),
        listOf(
            Component(Item.IRON_ROD, 1.q),
        ),
    ),
    CAST_SCREW(
        24.q,
        listOf(
            Component(Item.SCREW, 20.q),
        ),
        listOf(
            Component(Item.IRON_INGOT, 5.q),
        ),
    ),
    STEEL_SCREW(
        12.q,
        listOf(
            Component(Item.SCREW, 52.q),
        ),
        listOf(
            Component(Item.STEEL_BEAM, 1.q),
        ),
    ),
    COPPER_SHEET(
        6.q,
        listOf(
            Component(Item.COPPER_SHEET, 1.q),
        ),
        listOf(
            Component(Item.COPPER_INGOT, 2.q),
        ),
    ),
    STEEL_BEAM(
        4.q,
        listOf(
            Component(Item.STEEL_BEAM, 1.q),
        ),
        listOf(
            Component(Item.STEEL_INGOT, 4.q),
        ),
    ),
    STEEL_PIPE(
        6.q,
        listOf(
            Component(Item.STEEL_PIPE, 2.q),
        ),
        listOf(
            Component(Item.STEEL_INGOT, 3.q),
        ),
    ),
    ALUMINUM_CASING(
        2.q,
        listOf(
            Component(Item.ALUMINUM_CASING, 2.q),
        ),
        listOf(
            Component(Item.ALUMINUM_INGOT, 3.q),
        ),
    ),
    WIRE(
        4.q,
        listOf(
            Component(Item.WIRE, 2.q),
        ),
        listOf(
            Component(Item.COPPER_INGOT, 1.q),
        ),
    ),
    IRON_WIRE(
        24.q,
        listOf(
            Component(Item.WIRE, 9.q),
        ),
        listOf(
            Component(Item.IRON_INGOT, 5.q),
        ),
    ),
    CATERIUM_WIRE(
        4.q,
        listOf(
            Component(Item.WIRE, 8.q),
        ),
        listOf(
            Component(Item.CATERIUM_INGOT, 1.q),
        ),
    ),
    CABLE(
        2.q,
        listOf(
            Component(Item.CABLE, 1.q),
        ),
        listOf(
            Component(Item.WIRE, 2.q),
        ),
    ),
    QUICKWIRE(
        5.q,
        listOf(
            Component(Item.QUICKWIRE, 5.q),
        ),
        listOf(
            Component(Item.CATERIUM_INGOT, 1.q),
        ),
    ),
    CONCRETE(
        4.q,
        listOf(
            Component(Item.CONCRETE, 1.q),
        ),
        listOf(
            Component(Item.LIMESTONE, 3.q),
        ),
    ),
    QUARTZ_CRYSTAL(
        8.q,
        listOf(
            Component(Item.QUARTZ_CRYSTAL, 3.q),
        ),
        listOf(
            Component(Item.RAW_QUARTZ, 5.q),
        ),
    ),
    SILICA(
        8.q,
        listOf(
            Component(Item.SILICA, 5.q),
        ),
        listOf(
            Component(Item.RAW_QUARTZ, 3.q),
        ),
    ),
    COPPER_POWDER(
        6.q,
        listOf(
            Component(Item.COPPER_POWDER, 5.q),
        ),
        listOf(
            Component(Item.COPPER_INGOT, 30.q),
        ),
    ),
    BIOMASS_LEAVES(
        5.q,
        listOf(
            Component(Item.BIOMASS, 5.q),
        ),
        listOf(
            Component(Item.LEAVES, 10.q),
        ),
    ),
    BIOMASS_WOOD(
        4.q,
        listOf(
            Component(Item.BIOMASS, 20.q),
        ),
        listOf(
            Component(Item.WOOD, 4.q),
        ),
    ),
    BIOMASS_ALIEN_CARAPACE(
        4.q,
        listOf(
            Component(Item.BIOMASS, 100.q),
        ),
        listOf(
            Component(Item.ALIEN_CARAPACE, 1.q),
        ),
    ),
    BIOMASS_ALIEN_ORGANS(
        8.q,
        listOf(
            Component(Item.BIOMASS, 100.q),
        ),
        listOf(
            Component(Item.ALIEN_ORGANS, 1.q),
        ),
    ),
    BIOMASS_MYCELIA(
        4.q,
        listOf(
            Component(Item.BIOMASS, 10.q),
        ),
        listOf(
            Component(Item.MYCELIA, 10.q),
        ),
    ),
    SOLID_BIOFUEL(
        4.q,
        listOf(
            Component(Item.SOLID_BIOFUEL, 4.q),
        ),
        listOf(
            Component(Item.BIOMASS, 8.q),
        ),
    ),
    BIOCOAL(
        8.q,
        listOf(
            Component(Item.COAL, 6.q),
        ),
        listOf(
            Component(Item.BIOMASS, 5.q),
        ),
    ),
    CHARCOAL(
        4.q,
        listOf(
            Component(Item.COAL, 10.q),
        ),
        listOf(
            Component(Item.WOOD, 1.q),
        ),
    ),
    POWER_SHARD_1(
        8.q,
        listOf(
            Component(Item.POWER_SHARD, 1.q),
        ),
        listOf(
            Component(Item.BLUE_POWER_SLUG, 1.q),
        ),
    ),
    POWER_SHARD_2(
        12.q,
        listOf(
            Component(Item.POWER_SHARD, 2.q),
        ),
        listOf(
            Component(Item.YELLOW_POWER_SLUG, 1.q),
        ),
    ),
    POWER_SHARD_5(
        24.q,
        listOf(
            Component(Item.POWER_SHARD, 5.q),
        ),
        listOf(
            Component(Item.PURPLE_POWER_SLUG, 1.q),
        ),
    ),
    SPIKED_REBAR(
        4.q,
        listOf(
            Component(Item.SPIKED_REBAR, 1.q),
        ),
        listOf(
            Component(Item.IRON_ROD, 1.q),
        ),
    ),
    COLOR_CARTRIDGE(
        8.q,
        listOf(
            Component(Item.COLOR_CARTRIDGE, 10.q),
        ),
        listOf(
            Component(Item.FLOWER_PETALS, 5.q),
        ),
    ),
    EMPTY_CANISTER(
        4.q,
        listOf(
            Component(Item.EMPTY_CANISTER, 4.q),
        ),
        listOf(
            Component(Item.PLASTIC, 2.q),
        ),
    ),
    STEEL_CANISTER(
        3.q,
        listOf(
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
        listOf(
            Component(Item.STEEL_INGOT, 3.q),
        ),
    ),
    EMPTY_FLUID_TANK(
        1.q,
        listOf(
            Component(Item.EMPTY_FLUID_TANK, 1.q),
        ),
        listOf(
            Component(Item.ALUMINUM_INGOT, 1.q),
        ),
    ),
    BLACK_POWDER(
        8.q,
        listOf(
            Component(Item.BLACK_POWDER, 1.q),
        ),
        listOf(
            Component(Item.COAL, 1.q),
            Component(Item.SULFUR, 2.q),
        ),
    ),
    FINE_BLACK_POWDER(
        16.q,
        listOf(
            Component(Item.BLACK_POWDER, 4.q),
        ),
        listOf(
            Component(Item.SULFUR, 2.q),
            Component(Item.COMPACTED_COAL, 1.q),
        ),
    ),
    NOBELISK(
        20.q,
        listOf(
            Component(Item.NOBELISK, 1.q),
        ),
        listOf(
            Component(Item.BLACK_POWDER, 5.q),
            Component(Item.STEEL_PIPE, 10.q),
        ),
    ),
    COATED_IRON_CANISTER(
        4.q,
        listOf(
            Component(Item.EMPTY_CANISTER, 4.q),
        ),
        listOf(
            Component(Item.IRON_PLATE, 2.q),
            Component(Item.COPPER_SHEET, 1.q),
        ),
    ),
    PRESSURE_CONVERSION_CUBE(
        60.q,
        listOf(
            Component(Item.PRESSURE_CONVERSION_CUBE, 1.q),
        ),
        listOf(
            Component(Item.FUSED_MODULAR_FRAME, 1.q),
            Component(Item.RADIO_CONTROL_UNIT, 2.q),
        ),
    ),
    ELECTROMAGNETIC_CONTROL_ROD(
        30.q,
        listOf(
            Component(Item.ELECTROMAGNETIC_CONTROL_ROD, 2.q),
        ),
        listOf(
            Component(Item.STATOR, 3.q),
            Component(Item.AI_LIMITER, 2.q),
        ),
    ),
    ENCASED_PLUTONIUM_CELL(
        12.q,
        listOf(
            Component(Item.ENCASED_PLUTONIUM_CELL, 1.q),
        ),
        listOf(
            Component(Item.PLUTONIUM_PELLET, 2.q),
            Component(Item.CONCRETE, 4.q),
        ),
    ),
    ELECTROMAGNETIC_CONNECTION_ROD(
        15.q,
        listOf(
            Component(Item.ELECTROMAGNETIC_CONTROL_ROD, 2.q),
        ),
        listOf(
            Component(Item.STATOR, 2.q),
            Component(Item.HIGH_SPEED_CONNECTOR, 1.q),
        ),
    ),
    PLUTONIUM_FUEL_UNIT(
        120.q,
        listOf(
            Component(Item.PLUTONIUM_FUEL_ROD, 1.q),
        ),
        listOf(
            Component(Item.ENCASED_PLUTONIUM_CELL, 20.q),
            Component(Item.PRESSURE_CONVERSION_CUBE, 1.q),
        ),
    ),
    FINE_CONCRETE(
        24.q,
        listOf(
            Component(Item.CONCRETE, 10.q),
        ),
        listOf(
            Component(Item.SILICA, 3.q),
            Component(Item.LIMESTONE, 12.q),
        ),
    ),
    RUBBER_CONCRETE(
        12.q,
        listOf(
            Component(Item.CONCRETE, 9.q),
        ),
        listOf(
            Component(Item.LIMESTONE, 10.q),
            Component(Item.RUBBER, 2.q),
        ),
    ),
    COMPACTED_COAL(
        12.q,
        listOf(
            Component(Item.COMPACTED_COAL, 5.q),
        ),
        listOf(
            Component(Item.COAL, 5.q),
            Component(Item.SULFUR, 5.q),
        ),
    ),
    CHEAP_SILICA(
        16.q,
        listOf(
            Component(Item.SILICA, 7.q),
        ),
        listOf(
            Component(Item.RAW_QUARTZ, 3.q),
            Component(Item.LIMESTONE, 5.q),
        ),
    ),
    FUSED_WIRE(
        20.q,
        listOf(
            Component(Item.WIRE, 30.q),
        ),
        listOf(
            Component(Item.COPPER_INGOT, 4.q),
            Component(Item.CATERIUM_INGOT, 1.q),
        ),
    ),
    QUICKWIRE_CABLE(
        24.q,
        listOf(
            Component(Item.CABLE, 11.q),
        ),
        listOf(
            Component(Item.QUICKWIRE, 3.q),
            Component(Item.RUBBER, 2.q),
        ),
    ),
    INSULATED_CABLE(
        12.q,
        listOf(
            Component(Item.CABLE, 20.q),
        ),
        listOf(
            Component(Item.WIRE, 9.q),
            Component(Item.RUBBER, 6.q),
        ),
    ),
    FUSED_QUICKWIRE(
        8.q,
        listOf(
            Component(Item.QUICKWIRE, 12.q),
        ),
        listOf(
            Component(Item.CATERIUM_INGOT, 1.q),
            Component(Item.COPPER_INGOT, 5.q),
        ),
    ),
    AI_LIMITER(
        12.q,
        listOf(
            Component(Item.AI_LIMITER, 1.q),
        ),
        listOf(
            Component(Item.COPPER_SHEET, 5.q),
            Component(Item.QUICKWIRE, 20.q),
        ),
    ),
    CIRCUIT_BOARD(
        8.q,
        listOf(
            Component(Item.CIRCUIT_BOARD, 1.q),
        ),
        listOf(
            Component(Item.COPPER_SHEET, 2.q),
            Component(Item.PLASTIC, 4.q),
        ),
    ),
    ELECTRODE_CIRCUIT_BOARD(
        12.q,
        listOf(
            Component(Item.CIRCUIT_BOARD, 1.q),
        ),
        listOf(
            Component(Item.RUBBER, 6.q),
            Component(Item.PETROLEUM_COKE, 9.q),
        ),
    ),
    SILICON_CIRCUIT_BOARD(
        24.q,
        listOf(
            Component(Item.CIRCUIT_BOARD, 5.q),
        ),
        listOf(
            Component(Item.COPPER_SHEET, 11.q),
            Component(Item.SILICA, 11.q),
        ),
    ),
    CATERIUM_CIRCUIT_BOARD(
        48.q,
        listOf(
            Component(Item.CIRCUIT_BOARD, 7.q),
        ),
        listOf(
            Component(Item.PLASTIC, 10.q),
            Component(Item.QUICKWIRE, 30.q),
        ),
    ),
    CRYSTAL_COMPUTER(
        64.q,
        listOf(
            Component(Item.COMPUTER, 3.q),
        ),
        listOf(
            Component(Item.CIRCUIT_BOARD, 8.q),
            Component(Item.CRYSTAL_OSCILLATOR, 3.q),
        ),
    ),
    OC_SUPERCOMPUTER(
        20.q,
        listOf(
            Component(Item.SUPERCOMPUTER, 1.q),
        ),
        listOf(
            Component(Item.RADIO_CONTROL_UNIT, 3.q),
            Component(Item.COOLING_SYSTEM, 3.q),
        ),
    ),
    COATED_IRON_PLATE(
        12.q,
        listOf(
            Component(Item.IRON_PLATE, 15.q),
        ),
        listOf(
            Component(Item.IRON_INGOT, 10.q),
            Component(Item.PLASTIC, 2.q),
        ),
    ),
    STEEL_COATED_PLATE(
        24.q,
        listOf(
            Component(Item.IRON_PLATE, 18.q),
        ),
        listOf(
            Component(Item.STEEL_INGOT, 3.q),
            Component(Item.PLASTIC, 2.q),
        ),
    ),
    REINFORCED_IRON_PLATE(
        12.q,
        listOf(
            Component(Item.REINFORCED_IRON_PLATE, 1.q),
        ),
        listOf(
            Component(Item.IRON_PLATE, 6.q),
            Component(Item.SCREW, 12.q),
        ),
    ),
    BOLTED_IRON_PLATE(
        12.q,
        listOf(
            Component(Item.REINFORCED_IRON_PLATE, 3.q),
        ),
        listOf(
            Component(Item.IRON_PLATE, 18.q),
            Component(Item.SCREW, 50.q),
        ),
    ),
    STITCHED_IRON_PLATE(
        32.q,
        listOf(
            Component(Item.REINFORCED_IRON_PLATE, 3.q),
        ),
        listOf(
            Component(Item.IRON_PLATE, 10.q),
            Component(Item.WIRE, 20.q),
        ),
    ),
    ADHERED_IRON_PLATE(
        16.q,
        listOf(
            Component(Item.REINFORCED_IRON_PLATE, 1.q),
        ),
        listOf(
            Component(Item.IRON_PLATE, 3.q),
            Component(Item.RUBBER, 1.q),
        ),
    ),
    MODULAR_FRAME(
        60.q,
        listOf(
            Component(Item.MODULAR_FRAME, 2.q),
        ),
        listOf(
            Component(Item.REINFORCED_IRON_PLATE, 3.q),
            Component(Item.IRON_ROD, 12.q),
        ),
    ),
    BOLTED_FRAME(
        24.q,
        listOf(
            Component(Item.MODULAR_FRAME, 2.q),
        ),
        listOf(
            Component(Item.REINFORCED_IRON_PLATE, 3.q),
            Component(Item.SCREW, 56.q),
        ),
    ),
    STEELED_FRAME(
        60.q,
        listOf(
            Component(Item.MODULAR_FRAME, 3.q),
        ),
        listOf(
            Component(Item.REINFORCED_IRON_PLATE, 2.q),
            Component(Item.STEEL_PIPE, 10.q),
        ),
    ),
    ENCASED_INDUSTRIAL_BEAM(
        10.q,
        listOf(
            Component(Item.ENCASED_INDUSTRIAL_BEAM, 1.q),
        ),
        listOf(
            Component(Item.STEEL_BEAM, 4.q),
            Component(Item.CONCRETE, 5.q),
        ),
    ),
    ENCASED_INDUSTRIAL_PIPE(
        15.q,
        listOf(
            Component(Item.ENCASED_INDUSTRIAL_BEAM, 1.q),
        ),
        listOf(
            Component(Item.STEEL_PIPE, 7.q),
            Component(Item.CONCRETE, 5.q),
        ),
    ),
    ALCLAD_ALUMINUM_SHEET(
        6.q,
        listOf(
            Component(Item.ALCLAD_ALUMINUM_SHEET, 3.q),
        ),
        listOf(
            Component(Item.ALUMINUM_INGOT, 3.q),
            Component(Item.COPPER_INGOT, 1.q),
        ),
    ),
    ALCLAD_CASING(
        8.q,
        listOf(
            Component(Item.ALUMINUM_CASING, 15.q),
        ),
        listOf(
            Component(Item.ALUMINUM_INGOT, 20.q),
            Component(Item.COPPER_INGOT, 10.q),
        ),
    ),
    FABRIC(
        4.q,
        listOf(
            Component(Item.FABRIC, 1.q),
        ),
        listOf(
            Component(Item.MYCELIA, 1.q),
            Component(Item.BIOMASS, 5.q),
        ),
    ),
    SMART_PLATING(
        30.q,
        listOf(
            Component(Item.SMART_PLATING, 1.q),
        ),
        listOf(
            Component(Item.REINFORCED_IRON_PLATE, 1.q),
            Component(Item.ROTOR, 1.q),
        ),
    ),
    VERSATILE_FRAMEWORK(
        24.q,
        listOf(
            Component(Item.VERSATILE_FRAMEWORK, 2.q),
        ),
        listOf(
            Component(Item.MODULAR_FRAME, 1.q),
            Component(Item.STEEL_BEAM, 12.q),
        ),
    ),
    AUTOMATED_WIRING(
        24.q,
        listOf(
            Component(Item.AUTOMATED_WIRING, 1.q),
        ),
        listOf(
            Component(Item.STATOR, 1.q),
            Component(Item.CABLE, 20.q),
        ),
    ),
    ASSEMBLY_DIRECTOR_SYSTEM(
        80.q,
        listOf(
            Component(Item.ASSEMBLY_DIRECTOR_SYSTEM, 1.q),
        ),
        listOf(
            Component(Item.ADAPTIVE_CONTROL_UNIT, 2.q),
            Component(Item.SUPERCOMPUTER, 1.q),
        ),
    ),
    ROTOR(
        15.q,
        listOf(
            Component(Item.ROTOR, 1.q),
        ),
        listOf(
            Component(Item.IRON_ROD, 5.q),
            Component(Item.SCREW, 25.q),
        ),
    ),
    COPPER_ROTOR(
        16.q,
        listOf(
            Component(Item.ROTOR, 3.q),
        ),
        listOf(
            Component(Item.COPPER_SHEET, 6.q),
            Component(Item.SCREW, 52.q),
        ),
    ),
    STEEL_ROTOR(
        12.q,
        listOf(
            Component(Item.ROTOR, 1.q),
        ),
        listOf(
            Component(Item.STEEL_PIPE, 2.q),
            Component(Item.WIRE, 6.q),
        ),
    ),
    STATOR(
        12.q,
        listOf(
            Component(Item.STATOR, 1.q),
        ),
        listOf(
            Component(Item.STEEL_PIPE, 3.q),
            Component(Item.WIRE, 8.q),
        ),
    ),
    QUICKWIRE_STATOR(
        15.q,
        listOf(
            Component(Item.STATOR, 2.q),
        ),
        listOf(
            Component(Item.STEEL_PIPE, 4.q),
            Component(Item.QUICKWIRE, 15.q),
        ),
    ),
    MOTOR(
        12.q,
        listOf(
            Component(Item.MOTOR, 1.q),
        ),
        listOf(
            Component(Item.ROTOR, 2.q),
            Component(Item.STATOR, 2.q),
        ),
    ),
    ELECTRIC_MOTOR(
        16.q,
        listOf(
            Component(Item.MOTOR, 2.q),
        ),
        listOf(
            Component(Item.ELECTROMAGNETIC_CONTROL_ROD, 1.q),
            Component(Item.ROTOR, 2.q),
        ),
    ),
    HEAT_SINK(
        8.q,
        listOf(
            Component(Item.HEAT_SINK, 1.q),
        ),
        listOf(
            Component(Item.ALCLAD_ALUMINUM_SHEET, 5.q),
            Component(Item.COPPER_SHEET, 3.q),
        ),
    ),
    HEAT_EXCHANGER(
        6.q,
        listOf(
            Component(Item.HEAT_SINK, 1.q),
        ),
        listOf(
            Component(Item.ALUMINUM_CASING, 3.q),
            Component(Item.RUBBER, 3.q),
        ),
    ),
    BEACON(
        8.q,
        listOf(
            Component(Item.BEACON, 1.q),
        ),
        listOf(
            Component(Item.IRON_PLATE, 3.q),
            Component(Item.IRON_ROD, 1.q),
            Component(Item.WIRE, 15.q),
            Component(Item.CABLE, 2.q),
        ),
    ),
    AUTOMATED_MINER(
        60.q,
        listOf(
            Component(Item.PORTABLE_MINER, 1.q),
        ),
        listOf(
            Component(Item.MOTOR, 1.q),
            Component(Item.STEEL_PIPE, 4.q),
            Component(Item.IRON_ROD, 4.q),
            Component(Item.IRON_PLATE, 2.q),
        ),
    ),
    CRYSTAL_BEACON(
        120.q,
        listOf(
            Component(Item.BEACON, 20.q),
        ),
        listOf(
            Component(Item.STEEL_BEAM, 4.q),
            Component(Item.STEEL_PIPE, 16.q),
            Component(Item.CRYSTAL_OSCILLATOR, 1.q),
        ),
    ),
    HEAVY_MODULAR_FRAME(
        30.q,
        listOf(
            Component(Item.HEAVY_MODULAR_FRAME, 1.q),
        ),
        listOf(
            Component(Item.MODULAR_FRAME, 5.q),
            Component(Item.STEEL_PIPE, 15.q),
            Component(Item.ENCASED_INDUSTRIAL_BEAM, 5.q),
            Component(Item.SCREW, 100.q),
        ),
    ),
    HEAVY_FLEXIBLE_FRAME(
        16.q,
        listOf(
            Component(Item.HEAVY_MODULAR_FRAME, 1.q),
        ),
        listOf(
            Component(Item.MODULAR_FRAME, 5.q),
            Component(Item.ENCASED_INDUSTRIAL_BEAM, 3.q),
            Component(Item.RUBBER, 20.q),
            Component(Item.SCREW, 54.q),
        ),
    ),
    ENCASED_HEAVY_FRAME(
        64.q,
        listOf(
            Component(Item.HEAVY_MODULAR_FRAME, 3.q),
        ),
        listOf(
            Component(Item.MODULAR_FRAME, 8.q),
            Component(Item.ENCASED_INDUSTRIAL_BEAM, 10.q),
            Component(Item.STEEL_PIPE, 36.q),
            Component(Item.CONCRETE, 22.q),
        ),
    ),
    COMPUTER(
        24.q,
        listOf(
            Component(Item.COMPUTER, 1.q),
        ),
        listOf(
            Component(Item.CIRCUIT_BOARD, 10.q),
            Component(Item.CABLE, 9.q),
            Component(Item.PLASTIC, 18.q),
            Component(Item.SCREW, 52.q),
        ),
    ),
    CATERIUM_COMPUTER(
        16.q,
        listOf(
            Component(Item.COMPUTER, 1.q),
        ),
        listOf(
            Component(Item.CIRCUIT_BOARD, 7.q),
            Component(Item.QUICKWIRE, 28.q),
            Component(Item.RUBBER, 12.q),
        ),
    ),
    SUPERCOMPUTER(
        32.q,
        listOf(
            Component(Item.SUPERCOMPUTER, 1.q),
        ),
        listOf(
            Component(Item.COMPUTER, 2.q),
            Component(Item.AI_LIMITER, 2.q),
            Component(Item.HIGH_SPEED_CONNECTOR, 3.q),
            Component(Item.PLASTIC, 28.q),
        ),
    ),
    SUPER_STATE_COMPUTER(
        50.q,
        listOf(
            Component(Item.SUPERCOMPUTER, 2.q),
        ),
        listOf(
            Component(Item.COMPUTER, 3.q),
            Component(Item.ELECTROMAGNETIC_CONTROL_ROD, 2.q),
            Component(Item.BATTERY, 20.q),
            Component(Item.WIRE, 45.q),
        ),
    ),
    CRYSTAL_OSCILLATOR(
        120.q,
        listOf(
            Component(Item.CRYSTAL_OSCILLATOR, 2.q),
        ),
        listOf(
            Component(Item.QUARTZ_CRYSTAL, 36.q),
            Component(Item.CABLE, 28.q),
            Component(Item.REINFORCED_IRON_PLATE, 5.q),
        ),
    ),
    INSULATED_CRYSTAL_OSCILLATOR(
        32.q,
        listOf(
            Component(Item.CRYSTAL_OSCILLATOR, 1.q),
        ),
        listOf(
            Component(Item.QUARTZ_CRYSTAL, 10.q),
            Component(Item.RUBBER, 7.q),
            Component(Item.AI_LIMITER, 1.q),
        ),
    ),
    RADIO_CONTROL_UNIT(
        48.q,
        listOf(
            Component(Item.RADIO_CONTROL_UNIT, 2.q),
        ),
        listOf(
            Component(Item.ALUMINUM_CASING, 32.q),
            Component(Item.CRYSTAL_OSCILLATOR, 1.q),
            Component(Item.COMPUTER, 1.q),
        ),
    ),
    RADIO_CONNECTION_UNIT(
        16.q,
        listOf(
            Component(Item.RADIO_CONTROL_UNIT, 1.q),
        ),
        listOf(
            Component(Item.HEAT_SINK, 4.q),
            Component(Item.HIGH_SPEED_CONNECTOR, 2.q),
            Component(Item.QUARTZ_CRYSTAL, 12.q),
        ),
    ),
    RADIO_CONTROL_SYSTEM(
        40.q,
        listOf(
            Component(Item.RADIO_CONTROL_UNIT, 3.q),
        ),
        listOf(
            Component(Item.CRYSTAL_OSCILLATOR, 1.q),
            Component(Item.CIRCUIT_BOARD, 10.q),
            Component(Item.ALUMINUM_CASING, 60.q),
            Component(Item.RUBBER, 30.q),
        ),
    ),
    PLASTIC_SMART_PLATING(
        24.q,
        listOf(
            Component(Item.SMART_PLATING, 2.q),
        ),
        listOf(
            Component(Item.REINFORCED_IRON_PLATE, 1.q),
            Component(Item.ROTOR, 1.q),
            Component(Item.PLASTIC, 3.q),
        ),
    ),
    FLEXIBLE_FRAMEWORK(
        16.q,
        listOf(
            Component(Item.VERSATILE_FRAMEWORK, 2.q),
        ),
        listOf(
            Component(Item.MODULAR_FRAME, 1.q),
            Component(Item.STEEL_BEAM, 6.q),
            Component(Item.RUBBER, 8.q),
        ),
    ),
    AUTOMATED_SPEED_WIRING(
        32.q,
        listOf(
            Component(Item.AUTOMATED_WIRING, 4.q),
        ),
        listOf(
            Component(Item.STATOR, 2.q),
            Component(Item.WIRE, 40.q),
            Component(Item.HIGH_SPEED_CONNECTOR, 1.q),
        ),
    ),
    MODULAR_ENGINE(
        60.q,
        listOf(
            Component(Item.MODULAR_ENGINE, 1.q),
        ),
        listOf(
            Component(Item.MOTOR, 2.q),
            Component(Item.RUBBER, 15.q),
            Component(Item.SMART_PLATING, 2.q),
        ),
    ),
    ADAPTIVE_CONTROL_UNIT(
        120.q,
        listOf(
            Component(Item.ADAPTIVE_CONTROL_UNIT, 2.q),
        ),
        listOf(
            Component(Item.AUTOMATED_WIRING, 15.q),
            Component(Item.CIRCUIT_BOARD, 10.q),
            Component(Item.HEAVY_MODULAR_FRAME, 2.q),
            Component(Item.COMPUTER, 2.q),
        ),
    ),
    MAGNETIC_FIELD_GENERATOR(
        120.q,
        listOf(
            Component(Item.MAGNETIC_FIELD_GENERATOR, 2.q),
        ),
        listOf(
            Component(Item.VERSATILE_FRAMEWORK, 5.q),
            Component(Item.ELECTROMAGNETIC_CONTROL_ROD, 2.q),
            Component(Item.BATTERY, 10.q),
        ),
    ),
    THERMAL_PROPULSION_ROCKET(
        120.q,
        listOf(
            Component(Item.THERMAL_PROPULSION_ROCKET, 2.q),
        ),
        listOf(
            Component(Item.MODULAR_ENGINE, 5.q),
            Component(Item.TURBO_MOTOR, 2.q),
            Component(Item.COOLING_SYSTEM, 6.q),
            Component(Item.FUSED_MODULAR_FRAME, 2.q),
        ),
    ),
    GAS_FILTER(
        8.q,
        listOf(
            Component(Item.GAS_FILTER, 1.q),
        ),
        listOf(
            Component(Item.COAL, 5.q),
            Component(Item.RUBBER, 2.q),
            Component(Item.FABRIC, 2.q),
        ),
    ),
    IODINE_INFUSED_FILTER(
        16.q,
        listOf(
            Component(Item.IODINE_INFUSED_FILTER, 1.q),
        ),
        listOf(
            Component(Item.GAS_FILTER, 1.q),
            Component(Item.QUICKWIRE, 8.q),
            Component(Item.ALUMINUM_CASING, 1.q),
        ),
    ),
    RIFLE_CARTRIDGE(
        20.q,
        listOf(
            Component(Item.RIFLE_CARTRIDGE, 5.q),
        ),
        listOf(
            Component(Item.BEACON, 1.q),
            Component(Item.STEEL_PIPE, 10.q),
            Component(Item.BLACK_POWDER, 10.q),
            Component(Item.RUBBER, 10.q),
        ),
    ),
    SEISMIC_NOBELISK(
        40.q,
        listOf(
            Component(Item.NOBELISK, 4.q),
        ),
        listOf(
            Component(Item.BLACK_POWDER, 8.q),
            Component(Item.STEEL_PIPE, 8.q),
            Component(Item.CRYSTAL_OSCILLATOR, 1.q),
        ),
    ),
    HIGH_SPEED_CONNECTOR(
        16.q,
        listOf(
            Component(Item.HIGH_SPEED_CONNECTOR, 1.q),
        ),
        listOf(
            Component(Item.QUICKWIRE, 56.q),
            Component(Item.CABLE, 10.q),
            Component(Item.CIRCUIT_BOARD, 1.q),
        ),
    ),
    SILICON_HIGH_SPEED_CONNECTOR(
        40.q,
        listOf(
            Component(Item.HIGH_SPEED_CONNECTOR, 2.q),
        ),
        listOf(
            Component(Item.QUICKWIRE, 60.q),
            Component(Item.SILICA, 25.q),
            Component(Item.CIRCUIT_BOARD, 2.q),
        ),
    ),
    INFUSED_URANIUM_CELL(
        12.q,
        listOf(
            Component(Item.ENCASED_URANIUM_CELL, 4.q),
        ),
        listOf(
            Component(Item.URANIUM, 5.q),
            Component(Item.SILICA, 3.q),
            Component(Item.SULFUR, 5.q),
            Component(Item.QUICKWIRE, 15.q),
        ),
    ),
    URANIUM_FUEL_ROD(
        150.q,
        listOf(
            Component(Item.URANIUM_FUEL_ROD, 1.q),
        ),
        listOf(
            Component(Item.ENCASED_URANIUM_CELL, 50.q),
            Component(Item.ENCASED_INDUSTRIAL_BEAM, 3.q),
            Component(Item.ELECTROMAGNETIC_CONTROL_ROD, 5.q),
        ),
    ),
    URANIUM_FUEL_UNIT(
        300.q,
        listOf(
            Component(Item.URANIUM_FUEL_ROD, 3.q),
        ),
        listOf(
            Component(Item.ENCASED_URANIUM_CELL, 100.q),
            Component(Item.ELECTROMAGNETIC_CONTROL_ROD, 10.q),
            Component(Item.CRYSTAL_OSCILLATOR, 3.q),
            Component(Item.BEACON, 6.q),
        ),
    ),
    PLUTONIUM_FUEL_ROD(
        240.q,
        listOf(
            Component(Item.PLUTONIUM_FUEL_ROD, 1.q),
        ),
        listOf(
            Component(Item.ENCASED_PLUTONIUM_CELL, 30.q),
            Component(Item.STEEL_BEAM, 18.q),
            Component(Item.ELECTROMAGNETIC_CONTROL_ROD, 6.q),
            Component(Item.HEAT_SINK, 10.q),
        ),
    ),
    RIGOUR_MOTOR(
        48.q,
        listOf(
            Component(Item.MOTOR, 6.q),
        ),
        listOf(
            Component(Item.ROTOR, 3.q),
            Component(Item.STATOR, 3.q),
            Component(Item.CRYSTAL_OSCILLATOR, 1.q),
        ),
    ),
    TURBO_MOTOR(
        32.q,
        listOf(
            Component(Item.TURBO_MOTOR, 1.q),
        ),
        listOf(
            Component(Item.COOLING_SYSTEM, 4.q),
            Component(Item.RADIO_CONTROL_UNIT, 2.q),
            Component(Item.MOTOR, 4.q),
            Component(Item.RUBBER, 24.q),
        ),
    ),
    CLASSIC_BATTERY(
        8.q,
        listOf(
            Component(Item.BATTERY, 4.q),
        ),
        listOf(
            Component(Item.SULFUR, 6.q),
            Component(Item.ALCLAD_ALUMINUM_SHEET, 7.q),
            Component(Item.PLASTIC, 8.q),
            Component(Item.WIRE, 12.q),
        ),
    ),
    TURBO_ELECTRIC_MOTOR(
        64.q,
        listOf(
            Component(Item.TURBO_MOTOR, 3.q),
        ),
        listOf(
            Component(Item.MOTOR, 7.q),
            Component(Item.RADIO_CONTROL_UNIT, 9.q),
            Component(Item.ELECTROMAGNETIC_CONTROL_ROD, 5.q),
            Component(Item.ROTOR, 7.q),
        ),
    ),
    TURBO_PRESSURE_MOTOR(
        32.q,
        listOf(
            Component(Item.TURBO_MOTOR, 2.q),
        ),
        listOf(
            Component(Item.MOTOR, 4.q),
            Component(Item.PRESSURE_CONVERSION_CUBE, 1.q),
            Component(Item.PACKAGED_NITROGEN_GAS, 24.q),
            Component(Item.STATOR, 8.q),
        ),
    ),
    PACKAGED_WATER(
        2.q,
        listOf(
            Component(Item.PACKAGED_WATER, 2.q),
        ),
        listOf(
            Component(Item.WATER, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
    ),
    PACKAGED_OIL(
        4.q,
        listOf(
            Component(Item.PACKAGED_OIL, 2.q),
        ),
        listOf(
            Component(Item.CRUDE_OIL, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
    ),
    PACKAGED_HEAVY_OIL_RESIDUE(
        4.q,
        listOf(
            Component(Item.PACKAGED_HEAVY_OIL_RESIDUE, 2.q),
        ),
        listOf(
            Component(Item.HEAVY_OIL_RESIDUE, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
    ),
    PACKAGED_LIQUID_BIOFUEL(
        3.q,
        listOf(
            Component(Item.PACKAGED_LIQUID_BIOFUEL, 2.q),
        ),
        listOf(
            Component(Item.LIQUID_BIOFUEL, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
    ),
    PACKAGED_FUEL(
        3.q,
        listOf(
            Component(Item.PACKAGED_FUEL, 2.q),
        ),
        listOf(
            Component(Item.FUEL, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
    ),
    PACKAGED_ALUMINA_SOLUTION(
        1.q,
        listOf(
            Component(Item.PACKAGED_ALUMINA_SOLUTION, 2.q),
        ),
        listOf(
            Component(Item.ALUMINA_SOLUTION, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
    ),
    PACKAGED_SULFURIC_ACID(
        3.q,
        listOf(
            Component(Item.PACKAGED_SULFURIC_ACID, 2.q),
        ),
        listOf(
            Component(Item.SULFURIC_ACID, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
    ),
    PACKAGED_NITROGEN_GAS(
        1.q,
        listOf(
            Component(Item.PACKAGED_NITROGEN_GAS, 1.q),
        ),
        listOf(
            Component(Item.NITROGEN_GAS, 4.q),
            Component(Item.EMPTY_FLUID_TANK, 1.q),
        ),
    ),
    PACKAGED_NITRIC_ACID(
        2.q,
        listOf(
            Component(Item.PACKAGED_NITRIC_ACID, 1.q),
        ),
        listOf(
            Component(Item.NITRIC_ACID, 1.q),
            Component(Item.EMPTY_FLUID_TANK, 1.q),
        ),
    ),
    PACKAGED_TURBOFUEL(
        6.q,
        listOf(
            Component(Item.PACKAGED_TURBOFUEL, 2.q),
        ),
        listOf(
            Component(Item.TURBOFUEL, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
    ),
    UNPACKAGE_WATER(
        1.q,
        listOf(
            Component(Item.WATER, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
        listOf(
            Component(Item.PACKAGED_WATER, 2.q),
        ),
    ),
    UNPACKAGE_OIL(
        2.q,
        listOf(
            Component(Item.CRUDE_OIL, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
        listOf(
            Component(Item.PACKAGED_OIL, 2.q),
        ),
    ),
    UNPACKAGE_HEAVY_OIL_RESIDUE(
        6.q,
        listOf(
            Component(Item.HEAVY_OIL_RESIDUE, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
        listOf(
            Component(Item.PACKAGED_HEAVY_OIL_RESIDUE, 2.q),
        ),
    ),
    UNPACKAGE_LIQUID_BIOFUEL(
        2.q,
        listOf(
            Component(Item.LIQUID_BIOFUEL, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
        listOf(
            Component(Item.PACKAGED_LIQUID_BIOFUEL, 2.q),
        ),
    ),
    UNPACKAGE_FUEL(
        2.q,
        listOf(
            Component(Item.FUEL, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
        listOf(
            Component(Item.PACKAGED_FUEL, 2.q),
        ),
    ),
    UNPACKAGE_ALUMINA_SOLUTION(
        1.q,
        listOf(
            Component(Item.ALUMINA_SOLUTION, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
        listOf(
            Component(Item.PACKAGED_ALUMINA_SOLUTION, 2.q),
        ),
    ),
    UNPACKAGE_SULFURIC_ACID(
        1.q,
        listOf(
            Component(Item.SULFURIC_ACID, 1.q),
            Component(Item.EMPTY_CANISTER, 1.q),
        ),
        listOf(
            Component(Item.PACKAGED_SULFURIC_ACID, 1.q),
        ),
    ),
    UNPACKAGE_NITROGEN_GAS(
        1.q,
        listOf(
            Component(Item.NITROGEN_GAS, 4.q),
            Component(Item.EMPTY_FLUID_TANK, 1.q),
        ),
        listOf(
            Component(Item.PACKAGED_NITROGEN_GAS, 1.q),
        ),
    ),
    UNPACKAGE_NITRIC_ACID(
        3.q,
        listOf(
            Component(Item.NITRIC_ACID, 1.q),
            Component(Item.EMPTY_FLUID_TANK, 1.q),
        ),
        listOf(
            Component(Item.PACKAGED_NITRIC_ACID, 1.q),
        ),
    ),
    UNPACKAGE_TURBOFUEL(
        6.q,
        listOf(
            Component(Item.TURBOFUEL, 2.q),
            Component(Item.EMPTY_CANISTER, 2.q),
        ),
        listOf(
            Component(Item.PACKAGED_TURBOFUEL, 2.q),
        ),
    ),
    PURE_IRON_INGOT(
        12.q,
        listOf(
            Component(Item.IRON_INGOT, 13.q),
        ),
        listOf(
            Component(Item.IRON_ORE, 7.q),
            Component(Item.WATER, 4.q),
        ),
    ),
    PURE_COPPER_INGOT(
        24.q,
        listOf(
            Component(Item.COPPER_INGOT, 15.q),
        ),
        listOf(
            Component(Item.COPPER_ORE, 6.q),
            Component(Item.WATER, 4.q),
        ),
    ),
    PURE_CATERIUM_INGOT(
        5.q,
        listOf(
            Component(Item.CATERIUM_INGOT, 1.q),
        ),
        listOf(
            Component(Item.CATERIUM_ORE, 2.q),
            Component(Item.WATER, 2.q),
        ),
    ),
    PLASTIC(
        6.q,
        listOf(
            Component(Item.PLASTIC, 2.q),
            Component(Item.HEAVY_OIL_RESIDUE, 1.q),
        ),
        listOf(
            Component(Item.CRUDE_OIL, 3.q),
        ),
    ),
    RESIDUAL_PLASTIC(
        6.q,
        listOf(
            Component(Item.PLASTIC, 2.q),
        ),
        listOf(
            Component(Item.POLYMER_RESIN, 6.q),
            Component(Item.WATER, 2.q),
        ),
    ),
    RECYCLED_PLASTIC(
        12.q,
        listOf(
            Component(Item.PLASTIC, 12.q),
        ),
        listOf(
            Component(Item.RUBBER, 6.q),
            Component(Item.FUEL, 6.q),
        ),
    ),
    POLYMER_RESIN(
        6.q,
        listOf(
            Component(Item.POLYMER_RESIN, 13.q),
            Component(Item.HEAVY_OIL_RESIDUE, 2.q),
        ),
        listOf(
            Component(Item.CRUDE_OIL, 6.q),
        ),
    ),
    RUBBER(
        6.q,
        listOf(
            Component(Item.RUBBER, 2.q),
            Component(Item.HEAVY_OIL_RESIDUE, 2.q),
        ),
        listOf(
            Component(Item.CRUDE_OIL, 3.q),
        ),
    ),
    RESIDUAL_RUBBER(
        6.q,
        listOf(
            Component(Item.RUBBER, 2.q),
        ),
        listOf(
            Component(Item.POLYMER_RESIN, 4.q),
            Component(Item.WATER, 4.q),
        ),
    ),
    RECYCLED_RUBBER(
        12.q,
        listOf(
            Component(Item.RUBBER, 12.q),
        ),
        listOf(
            Component(Item.PLASTIC, 6.q),
            Component(Item.FUEL, 6.q),
        ),
    ),
    HEAVY_OIL_RESIDUE(
        6.q,
        listOf(
            Component(Item.HEAVY_OIL_RESIDUE, 4.q),
            Component(Item.POLYMER_RESIN, 2.q),
        ),
        listOf(
            Component(Item.CRUDE_OIL, 3.q),
        ),
    ),
    PETROLEUM_COKE(
        6.q,
        listOf(
            Component(Item.PETROLEUM_COKE, 12.q),
        ),
        listOf(
            Component(Item.HEAVY_OIL_RESIDUE, 4.q),
        ),
    ),
    FUEL(
        6.q,
        listOf(
            Component(Item.FUEL, 4.q),
            Component(Item.POLYMER_RESIN, 3.q),
        ),
        listOf(
            Component(Item.CRUDE_OIL, 6.q),
        ),
    ),
    RESIDUAL_FUEL(
        6.q,
        listOf(
            Component(Item.FUEL, 4.q),
        ),
        listOf(
            Component(Item.HEAVY_OIL_RESIDUE, 6.q),
        ),
    ),
    DILUTED_PACKAGED_FUEL(
        2.q,
        listOf(
            Component(Item.PACKAGED_FUEL, 2.q),
        ),
        listOf(
            Component(Item.HEAVY_OIL_RESIDUE, 1.q),
            Component(Item.PACKAGED_WATER, 2.q),
        ),
    ),
    LIQUID_BIOFUEL(
        4.q,
        listOf(
            Component(Item.LIQUID_BIOFUEL, 4.q),
        ),
        listOf(
            Component(Item.SOLID_BIOFUEL, 6.q),
            Component(Item.WATER, 3.q),
        ),
    ),
    TURBOFUEL(
        16.q,
        listOf(
            Component(Item.TURBOFUEL, 5.q),
        ),
        listOf(
            Component(Item.FUEL, 6.q),
            Component(Item.COMPACTED_COAL, 4.q),
        ),
    ),
    TURBO_HEAVY_FUEL(
        8.q,
        listOf(
            Component(Item.TURBOFUEL, 4.q),
        ),
        listOf(
            Component(Item.HEAVY_OIL_RESIDUE, 5.q),
            Component(Item.COMPACTED_COAL, 4.q),
        ),
    ),
    WET_CONCRETE(
        3.q,
        listOf(
            Component(Item.CONCRETE, 4.q),
        ),
        listOf(
            Component(Item.LIMESTONE, 6.q),
            Component(Item.WATER, 5.q),
        ),
    ),
    PURE_QUARTZ_CRYSTAL(
        8.q,
        listOf(
            Component(Item.QUARTZ_CRYSTAL, 7.q),
        ),
        listOf(
            Component(Item.RAW_QUARTZ, 9.q),
            Component(Item.WATER, 5.q),
        ),
    ),
    ALUMINA_SOLUTION(
        6.q,
        listOf(
            Component(Item.ALUMINA_SOLUTION, 12.q),
            Component(Item.SILICA, 5.q),
        ),
        listOf(
            Component(Item.BAUXITE, 12.q),
            Component(Item.WATER, 18.q),
        ),
    ),
    ALUMINUM_SCRAP(
        1.q,
        listOf(
            Component(Item.ALUMINUM_SCRAP, 6.q),
            Component(Item.WATER, 2.q),
        ),
        listOf(
            Component(Item.ALUMINA_SOLUTION, 4.q),
            Component(Item.COAL, 2.q),
        ),
    ),
    SLOPPY_ALUMINA(
        3.q,
        listOf(
            Component(Item.ALUMINA_SOLUTION, 12.q),
        ),
        listOf(
            Component(Item.BAUXITE, 10.q),
            Component(Item.WATER, 10.q),
        ),
    ),
    ELECTRODE_ALUMINUM_SCRAP(
        4.q,
        listOf(
            Component(Item.ALUMINUM_SCRAP, 20.q),
            Component(Item.WATER, 7.q),
        ),
        listOf(
            Component(Item.ALUMINA_SOLUTION, 12.q),
            Component(Item.PETROLEUM_COKE, 4.q),
        ),
    ),
    SULFURIC_ACID(
        6.q,
        listOf(
            Component(Item.SULFURIC_ACID, 5.q),
        ),
        listOf(
            Component(Item.SULFUR, 5.q),
            Component(Item.WATER, 5.q),
        ),
    ),
    COATED_CABLE(
        8.q,
        listOf(
            Component(Item.CABLE, 9.q),
        ),
        listOf(
            Component(Item.WIRE, 5.q),
            Component(Item.HEAVY_OIL_RESIDUE, 2.q),
        ),
    ),
    POLYESTER_FABRIC(
        12.q,
        listOf(
            Component(Item.FABRIC, 1.q),
        ),
        listOf(
            Component(Item.POLYMER_RESIN, 16.q),
            Component(Item.WATER, 10.q),
        ),
    ),
    STEAMED_COPPER_SHEET(
        8.q,
        listOf(
            Component(Item.COPPER_SHEET, 3.q),
        ),
        listOf(
            Component(Item.COPPER_INGOT, 3.q),
            Component(Item.WATER, 3.q),
        ),
    ),
    COOLING_SYSTEM(
        10.q,
        listOf(
            Component(Item.COOLING_SYSTEM, 1.q),
        ),
        listOf(
            Component(Item.HEAT_SINK, 2.q),
            Component(Item.RUBBER, 2.q),
            Component(Item.WATER, 5.q),
            Component(Item.NITROGEN_GAS, 25.q),
        ),
    ),
    COOLING_DEVICE(
        32.q,
        listOf(
            Component(Item.COOLING_SYSTEM, 2.q),
        ),
        listOf(
            Component(Item.HEAT_SINK, 5.q),
            Component(Item.MOTOR, 1.q),
            Component(Item.NITROGEN_GAS, 24.q),
        ),
    ),
    FUSED_MODULAR_FRAME(
        40.q,
        listOf(
            Component(Item.FUSED_MODULAR_FRAME, 1.q),
        ),
        listOf(
            Component(Item.HEAVY_MODULAR_FRAME, 1.q),
            Component(Item.ALUMINUM_CASING, 50.q),
            Component(Item.NITROGEN_GAS, 25.q),
        ),
    ),
    HEAT_FUSED_FRAME(
        20.q,
        listOf(
            Component(Item.FUSED_MODULAR_FRAME, 1.q),
        ),
        listOf(
            Component(Item.HEAVY_MODULAR_FRAME, 1.q),
            Component(Item.ALUMINUM_INGOT, 50.q),
            Component(Item.NITRIC_ACID, 8.q),
            Component(Item.FUEL, 10.q),
        ),
    ),
    BATTERY(
        3.q,
        listOf(
            Component(Item.BATTERY, 1.q),
            Component(Item.WATER, 3.q / 2.q),
        ),
        listOf(
            Component(Item.SULFURIC_ACID, 5.q / 2.q),
            Component(Item.ALUMINA_SOLUTION, 2.q),
            Component(Item.ALUMINUM_CASING, 1.q),
        ),
    ),
    ENCASED_URANIUM_CELL(
        12.q,
        listOf(
            Component(Item.ENCASED_URANIUM_CELL, 5.q),
            Component(Item.SULFURIC_ACID, 2.q),
        ),
        listOf(
            Component(Item.URANIUM, 10.q),
            Component(Item.CONCRETE, 3.q),
            Component(Item.SULFURIC_ACID, 8.q),
        ),
    ),
    NON_FISSILE_URANIUM(
        24.q,
        listOf(
            Component(Item.NON_FISSILE_URANIUM, 20.q),
            Component(Item.WATER, 6.q),
        ),
        listOf(
            Component(Item.URANIUM_WASTE, 15.q),
            Component(Item.SILICA, 10.q),
            Component(Item.NITRIC_ACID, 6.q),
            Component(Item.SULFURIC_ACID, 6.q),
        ),
    ),
    FERTILE_URANIUM(
        12.q,
        listOf(
            Component(Item.NON_FISSILE_URANIUM, 20.q),
            Component(Item.WATER, 8.q),
        ),
        listOf(
            Component(Item.URANIUM, 5.q),
            Component(Item.URANIUM_WASTE, 5.q),
            Component(Item.NITRIC_ACID, 3.q),
            Component(Item.SULFURIC_ACID, 5.q),
        ),
    ),
    INSTANT_SCRAP(
        6.q,
        listOf(
            Component(Item.ALUMINUM_SCRAP, 30.q),
            Component(Item.WATER, 5.q),
        ),
        listOf(
            Component(Item.BAUXITE, 15.q),
            Component(Item.COAL, 10.q),
            Component(Item.SULFURIC_ACID, 5.q),
            Component(Item.WATER, 6.q),
        ),
    ),
    NITRIC_ACID(
        6.q,
        listOf(
            Component(Item.NITRIC_ACID, 3.q),
        ),
        listOf(
            Component(Item.NITROGEN_GAS, 12.q),
            Component(Item.WATER, 3.q),
            Component(Item.IRON_PLATE, 1.q),
        ),
    ),
    DILUTED_FUEL(
        6.q,
        listOf(
            Component(Item.FUEL, 10.q),
        ),
        listOf(
            Component(Item.HEAVY_OIL_RESIDUE, 5.q),
            Component(Item.WATER, 10.q),
        ),
    ),
    TURBO_BLEND_FUEL(
        8.q,
        listOf(
            Component(Item.TURBOFUEL, 6.q),
        ),
        listOf(
            Component(Item.FUEL, 2.q),
            Component(Item.HEAVY_OIL_RESIDUE, 4.q),
            Component(Item.SULFUR, 3.q),
            Component(Item.PETROLEUM_COKE, 3.q),
        ),
    ),
    PLUTONIUM_PELLET(
        60.q,
        listOf(
            Component(Item.PLUTONIUM_PELLET, 30.q),
        ),
        listOf(
            Component(Item.NON_FISSILE_URANIUM, 100.q),
            Component(Item.URANIUM_WASTE, 25.q),
        ),
    ),
    INSTANT_PLUTONIUM_CELL(
        120.q,
        listOf(
            Component(Item.ENCASED_PLUTONIUM_CELL, 20.q),
        ),
        listOf(
            Component(Item.NON_FISSILE_URANIUM, 150.q),
            Component(Item.ALUMINUM_CASING, 20.q),
        ),
    ),
    NUCLEAR_PASTA(
        120.q,
        listOf(
            Component(Item.NUCLEAR_PASTA, 1.q),
        ),
        listOf(
            Component(Item.COPPER_POWDER, 200.q),
            Component(Item.PRESSURE_CONVERSION_CUBE, 1.q),
        ),
    ),
    CONSUME_COAL(
        4.q,
        listOf(
        ),
        listOf(
            Component(Item.COAL, 1.q),
            Component(Item.WATER, 3.q),
        ),
    ),
    CONSUME_PETROLEUM_COKE(
        12.q,
        listOf(
        ),
        listOf(
            Component(Item.PETROLEUM_COKE, 5.q),
            Component(Item.WATER, 9.q),
        ),
    ),
    CONSUME_FUEL(
        5.q,
        listOf(
        ),
        listOf(
            Component(Item.FUEL, 1.q),
        ),
    ),
    CONSUME_LIQUID_BIOFUEL(
        5.q,
        listOf(
        ),
        listOf(
            Component(Item.LIQUID_BIOFUEL, 1.q),
        ),
    ),
    CONSUME_TURBOFUEL(
        40.q / 3.q,
        listOf(
        ),
        listOf(
            Component(Item.TURBOFUEL, 1.q),
        ),
    ),
    CONSUME_URANIUM_FUEL_ROD(
        300.q,
        listOf(
            Component(Item.URANIUM_WASTE, 50.q),
        ),
        listOf(
            Component(Item.URANIUM_FUEL_ROD, 1.q),
            Component(Item.WATER, 1500.q),
        ),
    ),
    CONSUME_PLUTONIUM_FUEL_ROD(
        600.q,
        listOf(
            Component(Item.PLUTONIUM_WASTE, 50.q),
        ),
        listOf(
            Component(Item.PLUTONIUM_FUEL_ROD, 1.q),
            Component(Item.WATER, 3000.q),
        ),
    ),
    ;

    data class Component(
        val item: Item,
        val quantity: Rational,
    )
}
