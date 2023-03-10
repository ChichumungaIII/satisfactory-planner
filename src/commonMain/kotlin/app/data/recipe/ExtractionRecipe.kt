package app.data.recipe

import app.data.Item
import app.data.building.Extractor
import util.math.Rational
import util.math.q

enum class ExtractionRecipe(
    val extractor: Extractor,
    item: Item,
    purity: Purity,
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

    WATER(Extractor.WATER_EXTRACTOR, Item.WATER, Purity.NORMAL) {
        override val displayName = "Water"
    },

    CRUDE_OIL_PURE(Extractor.OIL_EXTRACTOR, Item.CRUDE_OIL, Purity.PURE),
    CRUDE_OIL_NORMAL(Extractor.OIL_EXTRACTOR, Item.CRUDE_OIL, Purity.NORMAL),
    CRUDE_OIL_IMPURE(Extractor.OIL_EXTRACTOR, Item.CRUDE_OIL, Purity.IMPURE);

    private enum class Purity(val displayName: String, val modifier: Rational) {
        IMPURE("Impure", 1.q / 2.q),
        NORMAL("Normal", 1.q),
        PURE("Pure", 2.q);
    }

    override val displayName = "${item.displayName} (${purity.displayName})"
    override val time = 60.q
    override val inputs = mapOf<Item, Rational>()
    override val outputs = mapOf(item to extractor.rate * purity.modifier)
}
