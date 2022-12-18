package app.data.recipe

import app.data.Item
import app.data.building.Generator
import util.math.Rational
import util.math.q

enum class GenerationRecipe(
    val generator: Generator,
    fuel: Item,
) : Recipe {
    FLOWER_PETALS(Generator.BIOMASS_BURNER, Item.FLOWER_PETALS),
    HATCHER_REMAINS(Generator.BIOMASS_BURNER, Item.HATCHER_REMAINS),
    HOG_REMAINS(Generator.BIOMASS_BURNER, Item.HOG_REMAINS),
    LEAVES(Generator.BIOMASS_BURNER, Item.LEAVES),
    MYCELIA(Generator.BIOMASS_BURNER, Item.MYCELIA),
    SPITTER_REMAINS(Generator.BIOMASS_BURNER, Item.SPITTER_REMAINS),
    STINGER_REMAINS(Generator.BIOMASS_BURNER, Item.STINGER_REMAINS),
    WOOD(Generator.BIOMASS_BURNER, Item.WOOD),
    BIOMASS(Generator.BIOMASS_BURNER, Item.BIOMASS),
    SOLID_BIOFUEL(Generator.BIOMASS_BURNER, Item.SOLID_BIOFUEL),
    COLOR_CARTRIDGE(Generator.BIOMASS_BURNER, Item.COLOR_CARTRIDGE),

    COAL(Generator.COAL_GENERATOR, Item.COAL),
    PETROLEUM_COKE(Generator.COAL_GENERATOR, Item.PETROLEUM_COKE),

    FUEL(Generator.FUEL_GENERATOR, Item.FUEL),
    TURBOFUEL(Generator.FUEL_GENERATOR, Item.TURBOFUEL);

    init {
        check(fuel.energy > 0.q) { "Cannot use $fuel as fuel." }
    }

    override val displayName = fuel.displayName

    override val time = fuel.energy / generator.generation

    override val inputs = mapOf(
        fuel to 1.q,
        Item.WATER to generator.water * time / 60.q,
    )
    override val outputs = mapOf<Item, Rational>()
}
