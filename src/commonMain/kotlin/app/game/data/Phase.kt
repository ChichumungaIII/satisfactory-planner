package app.game.data

enum class Phase(val displayName: String) {
  GAME_START("Game Start"),
  ONBOARDING("Onboarding"),
  PHASE_1("Phase 1"),
  PHASE_2("Phase 2"),
  PHASE_3("Phase 3"),
  PHASE_4("Phase 4");

  val previous by lazy { (ordinal - 1).takeIf { it >= 0 }?.let { Phase.entries[it] } }
}
