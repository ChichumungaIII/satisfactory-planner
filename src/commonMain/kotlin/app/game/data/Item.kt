package app.game.data

import app.game.logic.Condition
import app.game.logic.Condition.MilestoneCondition
import util.math.Rational
import util.math.q

enum class Item(
  val displayName: String,
  val category: Category,
  val stack: Rational,
  val sink: Rational? = null,
  val energy: Rational? = null,
  val radiation: Rational? = null,
  val unlock: Condition,
) {
  /* ****** */
  /* Tier 0 */
  /* ****** */

  /* Game Start */

  IRON_ORE(
    "Iron Ore",
    Category.RESOURCES,
    stack = 100.q,
    sink = 1.q,
    unlock = Condition.TRUE,
  ),
  IRON_INGOT(
    "Iron Ingot",
    Category.COMPONENTS,
    stack = 100.q,
    sink = 2.q,
    unlock = Condition.TRUE,
  ),
  IRON_PLATE(
    "Iron Plate",
    Category.COMPONENTS,
    stack = 200.q,
    sink = 6.q,
    unlock = Condition.TRUE,
  ),
  IRON_ROD(
    "Iron Rod",
    Category.COMPONENTS,
    stack = 200.q,
    sink = 4.q,
    unlock = Condition.TRUE,
  ),
  XENO_ZAPPER(
    "Xeno-Zapper",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 1_880.q,
    unlock = Condition.TRUE,
  ),

  /* HUB Upgrade 1 */

  PORTABLE_MINER(
    "Portable Miner",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 56.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_1),
  ),

  /* HUB Upgrade 2 */

  COPPER_ORE(
    "Copper Ore",
    Category.RESOURCES,
    stack = 100.q,
    sink = 3.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_2),
  ),
  COPPER_INGOT(
    "Copper Ingot",
    Category.COMPONENTS,
    stack = 100.q,
    sink = 6.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_2),
  ),
  WIRE(
    "Wire",
    Category.COMPONENTS,
    stack = 500.q,
    sink = 6.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_2),
  ),
  CABLE(
    "Cable",
    Category.COMPONENTS,
    stack = 200.q,
    sink = 24.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_2),
  ),

  /* HUB Upgrade 3 */

  LIMESTONE(
    "Limestone",
    Category.RESOURCES,
    stack = 100.q,
    sink = 2.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_3),
  ),
  CONCRETE(
    "Concrete",
    Category.COMPONENTS,
    stack = 500.q,
    sink = 12.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_3),
  ),
  SCREW(
    "Screw",
    Category.COMPONENTS,
    stack = 500.q,
    sink = 2.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_3),
  ),
  REINFORCED_IRON_PLATE(
    "Reinforced Iron Plate",
    Category.COMPONENTS,
    stack = 100.q,
    sink = 120.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_3),
  ),

  /* HUB Upgrade 6 */

  BIOMASS(
    "Biomass",
    Category.COMPONENTS,
    stack = 200.q,
    sink = 12.q,
    energy = 180.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_6),
  ),

  /* ****** */
  /* TIER 1 */
  /* ****** */

  /* Field Research */

  BEACON(
    "Beacon",
    Category.EQUIPMENT,
    stack = 100.q,
    sink = 320.q,
    unlock = MilestoneCondition(Milestone.FIELD_RESEARCH),
  ),
  OBJECT_SCANNER(
    "Object scanner",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 1_400.q,
    unlock = MilestoneCondition(Milestone.FIELD_RESEARCH),
  ),

  /* ****** */
  /* TIER 2 */
  /* ****** */

  /* Part Assembly */

  COPPER_SHEET(
    "Copper Sheet",
    Category.COMPONENTS,
    stack = 200.q,
    sink = 24.q,
    unlock = MilestoneCondition(Milestone.PART_ASSEMBLY),
  ),
  ROTOR(
    "Rotor",
    Category.COMPONENTS,
    stack = 100.q,
    sink = 140.q,
    unlock = MilestoneCondition(Milestone.PART_ASSEMBLY),
  ),
  MODULAR_FRAME(
    "Modular Frame",
    Category.COMPONENTS,
    stack = 50.q,
    sink = 408.q,
    unlock = MilestoneCondition(Milestone.PART_ASSEMBLY),
  ),
  SMART_PLATING(
    "Smart Plating",
    Category.COMPONENTS,
    stack = 50.q,
    sink = 520.q,
    unlock = MilestoneCondition(Milestone.PART_ASSEMBLY),
  ),

  /* Obstacle Clearing */

  SOLID_BIOFUEL(
    "Solid Biofuel",
    Category.COMPONENTS,
    stack = 200.q,
    sink = 48.q,
    energy = 450.q,
    unlock = MilestoneCondition(Milestone.OBSTACLE_CLEARING),
  ),
  CHAINSAW(
    "Chainsaw",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 2760.q,
    unlock = MilestoneCondition(Milestone.OBSTACLE_CLEARING),
  ),

  /* Resource Sink Bonus Program */

  COLOR_CARTRIDGE(
    "Color Cartridge",
    Category.COMPONENTS,
    stack = 200.q,
    sink = 10.q,
    energy = 900.q,
    unlock = MilestoneCondition(Milestone.RESOURCE_SINK_BONUS_PROGRAM),
  ),

  /* ****** */
  /* NATURE */
  /* ****** */

  BACON_AGARIC(
    "Bacon Agaric",
    Category.NATURE,
    stack = 50.q,
    unlock = Condition.TRUE,
  ),
  BERYL_NUT(
    "Beryl Nut",
    Category.NATURE,
    stack = 100.q,
    unlock = Condition.TRUE,
  ),
  PALEBERRY(
    "Paleberry",
    Category.NATURE,
    stack = 50.q,
    unlock = Condition.TRUE,
  ),

  FLOWER_PETALS(
    "Flower Petals",
    Category.NATURE,
    stack = 500.q,
    sink = 10.q,
    energy = 100.q,
    unlock = Condition.TRUE,
  ),
  LEAVES(
    "Leaves",
    Category.NATURE,
    stack = 500.q,
    sink = 3.q,
    energy = 15.q,
    unlock = Condition.TRUE,
  ),
  MYCELIA(
    "Mycelia",
    Category.NATURE,
    stack = 200.q,
    sink = 10.q,
    energy = 20.q,
    unlock = Condition.TRUE,
  ),
  WOOD(
    "Wood",
    Category.NATURE,
    stack = 200.q,
    sink = 30.q,
    energy = 100.q,
    unlock = Condition.TRUE,
  ),

  HATCHER_REMAINS(
    "Hatcher Remains",
    Category.NATURE,
    stack = 50.q,
    energy = 250.q,
    unlock = Condition.TRUE,
  ),
  HOG_REMAINS(
    "Hog Remains",
    Category.NATURE,
    stack = 50.q,
    energy = 250.q,
    unlock = Condition.TRUE,
  ),
  SPITTER_REMAINS(
    "Spitter Remains",
    Category.NATURE,
    stack = 50.q,
    energy = 250.q,
    unlock = Condition.TRUE,
  ),
  STINGER_REMAINS(
    "Stinger Remains",
    Category.NATURE,
    stack = 50.q,
    energy = 250.q,
    unlock = Condition.TRUE,
  ),

  BLUE_POWER_SLUG(
    "Blue Power Slug",
    Category.NATURE,
    stack = 50.q,
    unlock = Condition.TRUE
  ),
  YELLOW_POWER_SLUG(
    "Yellow Power Slug",
    Category.NATURE,
    stack = 50.q,
    unlock = Condition.TRUE,
  ),
  PURPLE_POWER_SLUG(
    "Purple Power Slug",
    Category.NATURE,
    stack = 50.q,
    unlock = Condition.TRUE,
  );

  enum class Category(
    val displayName: String,
  ) {
    COMPONENTS("Components"),
    EQUIPMENT("Equipment"),
    NATURE("Nature"),
    RESOURCES("Resources"),
  }
}
