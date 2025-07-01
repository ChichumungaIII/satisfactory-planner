package app.game.data

enum class Tier(
  val displayName: String,
  val phase: Phase,
) {
  TIER_0(
    displayName = "Tier 0: Onboarding",
    phase = Phase.GAME_START,
  ),
  TIER_1(
    displayName = "Tier 1",
    phase = Phase.ONBOARDING,
  ),
  TIER_2(
    displayName = "Tier 2",
    phase = Phase.ONBOARDING,
  ),
  TIER_3(
    displayName = "Tier 3",
    phase = Phase.PHASE_1,
  ),
  TIER_4(
    displayName = "Tier 4",
    phase = Phase.PHASE_1,
  ),
  TIER_5(
    displayName = "Tier 5",
    phase = Phase.PHASE_2,
  ),
  TIER_6(
    displayName = "Tier 6",
    phase = Phase.PHASE_2,
  ),
  TIER_7(
    displayName = "Tier 7",
    phase = Phase.PHASE_3,
  ),
  TIER_8(
    displayName = "Tier 8",
    phase = Phase.PHASE_3,
  ),
}
