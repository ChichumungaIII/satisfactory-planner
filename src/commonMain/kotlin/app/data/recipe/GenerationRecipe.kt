package app.data.recipe

import app.data.Item
import app.data.building.Generator
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
enum class GenerationRecipe(
  val generator: Generator,
  fuel: Item,
) : Recipe {
  HATCHER_REMAINS(Generator.BIOMASS_BURNER, Item.HATCHER_REMAINS),
  HOG_REMAINS(Generator.BIOMASS_BURNER, Item.HOG_REMAINS),
  LEAVES(Generator.BIOMASS_BURNER, Item.LEAVES),
  MYCELIA(Generator.BIOMASS_BURNER, Item.MYCELIA),
  PLASMA_SPITTER_REMAINS(Generator.BIOMASS_BURNER, Item.PLASMA_SPITTER_REMAINS),
  STINGER_REMAINS(Generator.BIOMASS_BURNER, Item.STINGER_REMAINS),
  WOOD(Generator.BIOMASS_BURNER, Item.WOOD),
  BIOMASS(Generator.BIOMASS_BURNER, Item.BIOMASS),
  SOLID_BIOFUEL(Generator.BIOMASS_BURNER, Item.SOLID_BIOFUEL),

  COAL(Generator.COAL_GENERATOR, Item.COAL),
  PETROLEUM_COKE(Generator.COAL_GENERATOR, Item.PETROLEUM_COKE),

  FUEL(Generator.FUEL_GENERATOR, Item.FUEL),
  TURBOFUEL(Generator.FUEL_GENERATOR, Item.TURBOFUEL),

  URANIUM_FUEL_ROD(Generator.NUCLEAR_POWER_PLANT, Item.URANIUM_FUEL_ROD);

  init {
    checkNotNull(fuel.energy) { "Cannot use $fuel as fuel." }
  }

  override val displayName = fuel.displayName

  override val time = fuel.energy!! / generator.generation

  override val inputs = mapOf(
    fuel to 1.q,
    Item.WATER to generator.water * time / 60.q,
  )
  override val outputs = mapOf<Item, Rational>()
}
