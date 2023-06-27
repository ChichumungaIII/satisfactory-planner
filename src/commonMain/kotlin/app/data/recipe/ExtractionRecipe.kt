package app.data.recipe

import app.data.Item
import app.data.building.Extractor
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
enum class ExtractionRecipe(
  val extractor: Extractor,
  item: Item,
  purity: Purity,
  customDisplayName: String? = null,
) : Recipe {
  MK_1_IRON_ORE_PURE(Extractor.MINER_MK_1, Item.IRON_ORE, Purity.PURE),
  MK_1_IRON_ORE_NORMAL(Extractor.MINER_MK_1, Item.IRON_ORE, Purity.NORMAL),
  MK_1_IRON_ORE_IMPURE(Extractor.MINER_MK_1, Item.IRON_ORE, Purity.IMPURE),
  MK_1_COPPER_ORE_PURE(Extractor.MINER_MK_1, Item.COPPER_ORE, Purity.PURE),
  MK_1_COPPER_ORE_NORMAL(Extractor.MINER_MK_1, Item.COPPER_ORE, Purity.NORMAL),
  MK_1_COPPER_ORE_IMPURE(Extractor.MINER_MK_1, Item.COPPER_ORE, Purity.IMPURE),
  MK_1_LIMESTONE_PURE(Extractor.MINER_MK_1, Item.LIMESTONE, Purity.PURE),
  MK_1_LIMESTONE_NORMAL(Extractor.MINER_MK_1, Item.LIMESTONE, Purity.NORMAL),
  MK_1_LIMESTONE_IMPURE(Extractor.MINER_MK_1, Item.LIMESTONE, Purity.IMPURE),
  MK_1_COAL_PURE(Extractor.MINER_MK_1, Item.COAL, Purity.PURE),
  MK_1_COAL_NORMAL(Extractor.MINER_MK_1, Item.COAL, Purity.NORMAL),
  MK_1_COAL_IMPURE(Extractor.MINER_MK_1, Item.COAL, Purity.IMPURE),
  MK_1_CATERIUM_ORE_PURE(Extractor.MINER_MK_1, Item.CATERIUM_ORE, Purity.PURE),
  MK_1_CATERIUM_ORE_NORMAL(Extractor.MINER_MK_1, Item.CATERIUM_ORE, Purity.NORMAL),
  MK_1_CATERIUM_ORE_IMPURE(Extractor.MINER_MK_1, Item.CATERIUM_ORE, Purity.IMPURE),
  MK_1_RAW_QUARTZ_PURE(Extractor.MINER_MK_1, Item.RAW_QUARTZ, Purity.PURE),
  MK_1_RAW_QUARTZ_NORMAL(Extractor.MINER_MK_1, Item.RAW_QUARTZ, Purity.NORMAL),
  MK_1_RAW_QUARTZ_IMPURE(Extractor.MINER_MK_1, Item.RAW_QUARTZ, Purity.IMPURE),
  MK_1_SULFUR_PURE(Extractor.MINER_MK_1, Item.SULFUR, Purity.PURE),
  MK_1_SULFUR_NORMAL(Extractor.MINER_MK_1, Item.SULFUR, Purity.NORMAL),
  MK_1_SULFUR_IMPURE(Extractor.MINER_MK_1, Item.SULFUR, Purity.IMPURE),
  MK_1_URANIUM_PURE(Extractor.MINER_MK_1, Item.URANIUM, Purity.PURE),
  MK_1_URANIUM_NORMAL(Extractor.MINER_MK_1, Item.URANIUM, Purity.NORMAL),
  MK_1_URANIUM_IMPURE(Extractor.MINER_MK_1, Item.URANIUM, Purity.IMPURE),

  MK_2_IRON_ORE_PURE(Extractor.MINER_MK_2, Item.IRON_ORE, Purity.PURE),
  MK_2_IRON_ORE_NORMAL(Extractor.MINER_MK_2, Item.IRON_ORE, Purity.NORMAL),
  MK_2_IRON_ORE_IMPURE(Extractor.MINER_MK_2, Item.IRON_ORE, Purity.IMPURE),
  MK_2_COPPER_ORE_PURE(Extractor.MINER_MK_2, Item.COPPER_ORE, Purity.PURE),
  MK_2_COPPER_ORE_NORMAL(Extractor.MINER_MK_2, Item.COPPER_ORE, Purity.NORMAL),
  MK_2_COPPER_ORE_IMPURE(Extractor.MINER_MK_2, Item.COPPER_ORE, Purity.IMPURE),
  MK_2_LIMESTONE_PURE(Extractor.MINER_MK_2, Item.LIMESTONE, Purity.PURE),
  MK_2_LIMESTONE_NORMAL(Extractor.MINER_MK_2, Item.LIMESTONE, Purity.NORMAL),
  MK_2_LIMESTONE_IMPURE(Extractor.MINER_MK_2, Item.LIMESTONE, Purity.IMPURE),
  MK_2_COAL_PURE(Extractor.MINER_MK_2, Item.COAL, Purity.PURE),
  MK_2_COAL_NORMAL(Extractor.MINER_MK_2, Item.COAL, Purity.NORMAL),
  MK_2_COAL_IMPURE(Extractor.MINER_MK_2, Item.COAL, Purity.IMPURE),
  MK_2_CATERIUM_ORE_PURE(Extractor.MINER_MK_2, Item.CATERIUM_ORE, Purity.PURE),
  MK_2_CATERIUM_ORE_NORMAL(Extractor.MINER_MK_2, Item.CATERIUM_ORE, Purity.NORMAL),
  MK_2_CATERIUM_ORE_IMPURE(Extractor.MINER_MK_2, Item.CATERIUM_ORE, Purity.IMPURE),
  MK_2_RAW_QUARTZ_PURE(Extractor.MINER_MK_2, Item.RAW_QUARTZ, Purity.PURE),
  MK_2_RAW_QUARTZ_NORMAL(Extractor.MINER_MK_2, Item.RAW_QUARTZ, Purity.NORMAL),
  MK_2_RAW_QUARTZ_IMPURE(Extractor.MINER_MK_2, Item.RAW_QUARTZ, Purity.IMPURE),
  MK_2_SULFUR_PURE(Extractor.MINER_MK_2, Item.SULFUR, Purity.PURE),
  MK_2_SULFUR_NORMAL(Extractor.MINER_MK_2, Item.SULFUR, Purity.NORMAL),
  MK_2_SULFUR_IMPURE(Extractor.MINER_MK_2, Item.SULFUR, Purity.IMPURE),
  MK_2_URANIUM_PURE(Extractor.MINER_MK_2, Item.URANIUM, Purity.PURE),
  MK_2_URANIUM_NORMAL(Extractor.MINER_MK_2, Item.URANIUM, Purity.NORMAL),
  MK_2_URANIUM_IMPURE(Extractor.MINER_MK_2, Item.URANIUM, Purity.IMPURE),

  MK_3_IRON_ORE_PURE(Extractor.MINER_MK_3, Item.IRON_ORE, Purity.PURE),
  MK_3_IRON_ORE_NORMAL(Extractor.MINER_MK_3, Item.IRON_ORE, Purity.NORMAL),
  MK_3_IRON_ORE_IMPURE(Extractor.MINER_MK_3, Item.IRON_ORE, Purity.IMPURE),
  MK_3_COPPER_ORE_PURE(Extractor.MINER_MK_3, Item.COPPER_ORE, Purity.PURE),
  MK_3_COPPER_ORE_NORMAL(Extractor.MINER_MK_3, Item.COPPER_ORE, Purity.NORMAL),
  MK_3_COPPER_ORE_IMPURE(Extractor.MINER_MK_3, Item.COPPER_ORE, Purity.IMPURE),
  MK_3_LIMESTONE_PURE(Extractor.MINER_MK_3, Item.LIMESTONE, Purity.PURE),
  MK_3_LIMESTONE_NORMAL(Extractor.MINER_MK_3, Item.LIMESTONE, Purity.NORMAL),
  MK_3_LIMESTONE_IMPURE(Extractor.MINER_MK_3, Item.LIMESTONE, Purity.IMPURE),
  MK_3_COAL_PURE(Extractor.MINER_MK_3, Item.COAL, Purity.PURE),
  MK_3_COAL_NORMAL(Extractor.MINER_MK_3, Item.COAL, Purity.NORMAL),
  MK_3_COAL_IMPURE(Extractor.MINER_MK_3, Item.COAL, Purity.IMPURE),
  MK_3_CATERIUM_ORE_PURE(Extractor.MINER_MK_3, Item.CATERIUM_ORE, Purity.PURE),
  MK_3_CATERIUM_ORE_NORMAL(Extractor.MINER_MK_3, Item.CATERIUM_ORE, Purity.NORMAL),
  MK_3_CATERIUM_ORE_IMPURE(Extractor.MINER_MK_3, Item.CATERIUM_ORE, Purity.IMPURE),
  MK_3_RAW_QUARTZ_PURE(Extractor.MINER_MK_3, Item.RAW_QUARTZ, Purity.PURE),
  MK_3_RAW_QUARTZ_NORMAL(Extractor.MINER_MK_3, Item.RAW_QUARTZ, Purity.NORMAL),
  MK_3_RAW_QUARTZ_IMPURE(Extractor.MINER_MK_3, Item.RAW_QUARTZ, Purity.IMPURE),
  MK_3_SULFUR_PURE(Extractor.MINER_MK_3, Item.SULFUR, Purity.PURE),
  MK_3_SULFUR_NORMAL(Extractor.MINER_MK_3, Item.SULFUR, Purity.NORMAL),
  MK_3_SULFUR_IMPURE(Extractor.MINER_MK_3, Item.SULFUR, Purity.IMPURE),
  MK_3_URANIUM_PURE(Extractor.MINER_MK_3, Item.URANIUM, Purity.PURE),
  MK_3_URANIUM_NORMAL(Extractor.MINER_MK_3, Item.URANIUM, Purity.NORMAL),
  MK_3_URANIUM_IMPURE(Extractor.MINER_MK_3, Item.URANIUM, Purity.IMPURE),

  WATER(Extractor.WATER_EXTRACTOR, Item.WATER, Purity.NORMAL, "Water"),

  CRUDE_OIL_PURE(Extractor.OIL_EXTRACTOR, Item.CRUDE_OIL, Purity.PURE),
  CRUDE_OIL_NORMAL(Extractor.OIL_EXTRACTOR, Item.CRUDE_OIL, Purity.NORMAL),
  CRUDE_OIL_IMPURE(Extractor.OIL_EXTRACTOR, Item.CRUDE_OIL, Purity.IMPURE),

  EXTRACT_WATER_PURE(Extractor.RESOURCE_WELL_EXTRACTOR, Item.WATER, Purity.PURE),
  EXTRACT_WATER_NORMAL(Extractor.RESOURCE_WELL_EXTRACTOR, Item.WATER, Purity.NORMAL),
  EXTRACT_WATER_IMPURE(Extractor.RESOURCE_WELL_EXTRACTOR, Item.WATER, Purity.IMPURE),
  EXTRACT_CRUDE_OIL_PURE(Extractor.RESOURCE_WELL_EXTRACTOR, Item.CRUDE_OIL, Purity.PURE),
  EXTRACT_CRUDE_OIL_NORMAL(Extractor.RESOURCE_WELL_EXTRACTOR, Item.CRUDE_OIL, Purity.NORMAL),
  EXTRACT_CRUDE_OIL_IMPURE(Extractor.RESOURCE_WELL_EXTRACTOR, Item.CRUDE_OIL, Purity.IMPURE),
  EXTRACT_NITROGEN_GAS_PURE(Extractor.RESOURCE_WELL_EXTRACTOR, Item.NITROGEN_GAS, Purity.PURE),
  EXTRACT_NITROGEN_GAS_NORMAL(Extractor.RESOURCE_WELL_EXTRACTOR, Item.NITROGEN_GAS, Purity.NORMAL),
  EXTRACT_NITROGEN_GAS_IMPURE(Extractor.RESOURCE_WELL_EXTRACTOR, Item.NITROGEN_GAS, Purity.IMPURE);

  private enum class Purity(val displayName: String, val modifier: Rational) {
    IMPURE("Impure", 1.q / 2.q),
    NORMAL("Normal", 1.q),
    PURE("Pure", 2.q);
  }

  override val displayName = customDisplayName ?: "${item.displayName} (${purity.displayName})"
  override val time = 60.q
  override val inputs = mapOf<Item, Rational>()
  override val outputs = mapOf(item to extractor.rate * purity.modifier)
}
