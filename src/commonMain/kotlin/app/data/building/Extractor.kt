package app.data.building

import app.data.recipe.ExtractionRecipe
import util.math.Rational
import util.math.q

enum class Extractor(
  override val displayName: String,
  override val consumption: Rational,
  val rate: Rational,
) : ProductionBuilding {
  MINER_MK_1(
    "Miner Mk. 1",
    consumption = 5.q,
    rate = 60.q,
  ),
  WATER_EXTRACTOR(
    "Water Extractor",
    consumption = 20.q,
    rate = 120.q
  ),
  MINER_MK_2(
    "Miner Mk. 2",
    consumption = 12.q,
    rate = 120.q,
  ),
  MINER_MK_3(
    "Miner Mk. 3",
    consumption = 30.q,
    rate = 240.q,
  ),
  OIL_EXTRACTOR(
    "Oil Extractor",
    consumption = 40.q,
    rate = 120.q,
  ),
  RESOURCE_WELL_PRESSURIZER(
    "Resource Well Pressurizer",
    consumption = 150.q,
    rate = 0.q,
  ),
  RESOURCE_WELL_EXTRACTOR(
    "Resource Well Extractor",
    consumption = 0.q,
    rate = 60.q,
  );

  override val recipes by lazy { ExtractionRecipe.entries.filter { it.extractor == this } }
}
