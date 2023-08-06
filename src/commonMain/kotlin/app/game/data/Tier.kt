package app.game.data

import app.game.logic.Condition
import app.game.logic.Condition.PhaseCondition

enum class Tier(
  val displayName: String,
  val requirement: Condition,
) {
  TIER_0(
    displayName = "Tier 0: Onboarding",
    requirement = PhaseCondition(Phase.GAME_START),
  ),
  TIER_1(
    displayName = "Tier 1",
    requirement = PhaseCondition(Phase.ONBOARDING),
  ),
  TIER_2(
    displayName = "Tier 2",
    requirement = PhaseCondition(Phase.ONBOARDING),
  ),
  TIER_3(
    displayName = "Tier 3",
    requirement = PhaseCondition(Phase.PHASE_1),
  ),
  TIER_4(
    displayName = "Tier 4",
    requirement = PhaseCondition(Phase.PHASE_1),
  ),
  TIER_5(
    displayName = "Tier 5",
    requirement = PhaseCondition(Phase.PHASE_2),
  ),
  TIER_6(
    displayName = "Tier 6",
    requirement = PhaseCondition(Phase.PHASE_2),
  ),
  TIER_7(
    displayName = "Tier 7",
    requirement = PhaseCondition(Phase.PHASE_3),
  ),
  TIER_8(
    displayName = "Tier 8",
    requirement = PhaseCondition(Phase.PHASE_3),
  ),
}
