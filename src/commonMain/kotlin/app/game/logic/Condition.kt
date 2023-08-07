package app.game.logic

import app.game.data.Item
import app.game.data.Milestone
import app.game.data.Phase
import app.game.data.Research
import app.game.data.Tier

sealed class Condition {
  private val cache = mutableMapOf<Progress, Boolean>()

  fun test(progress: Progress) = cache.getOrPut(progress) { compute(progress) }
  protected abstract fun compute(progress: Progress): Boolean

  data object TRUE : Condition() {
    override fun compute(progress: Progress) = true
  }

  class PhaseCondition(private val phase: Phase) : Condition() {
    override fun compute(progress: Progress) = progress.phases.contains(phase)
  }

  class TierCondition(private val tier: Tier) : Condition() {
    override fun compute(progress: Progress) =
      Milestone.entries.filter { it.tier == tier }
        .map { MilestoneCondition(it) }
        .all { it.test(progress) }
  }

  class MilestoneCondition(private val milestone: Milestone) : Condition() {
    override fun compute(progress: Progress) = progress.milestones.contains(milestone)
  }

  class ResearchCondition(private val research: Research) : Condition() {
    override fun compute(progress: Progress) = progress.research.contains(research)
  }

  class ItemCondition(private val item: Item) : Condition() {
    override fun compute(progress: Progress) = item.unlock.test(progress)
  }

  abstract class ConditionBuilder<T> {
    protected val conditions = mutableListOf<Condition>()

    operator fun Phase.unaryPlus() = +PhaseCondition(this)
    operator fun Tier.unaryPlus() = +TierCondition(this)
    operator fun Milestone.unaryPlus() = +MilestoneCondition(this)
    operator fun Research.unaryPlus() = +ResearchCondition(this)
    operator fun Item.unaryPlus() = +ItemCondition(this)
    operator fun Condition.unaryPlus() = conditions.add(this)

    abstract fun build(): T
  }

  class ConjunctionCondition(private val conditions: List<Condition>) : Condition() {
    override fun compute(progress: Progress) = conditions.all { it.test(progress) }

    class Builder : ConditionBuilder<ConjunctionCondition>() {
      override fun build() = ConjunctionCondition(conditions)
    }
  }

  class DisjunctionCondition(private val conditions: List<Condition>) : Condition() {
    override fun compute(progress: Progress) = conditions.any { it.test(progress) }

    class Builder : ConditionBuilder<DisjunctionCondition>() {
      override fun build() = DisjunctionCondition(conditions)
    }
  }

  companion object {
    fun all(init: ConditionBuilder<*>.() -> Unit): Condition =
      ConjunctionCondition.Builder().also(init).build()

    fun any(init: ConditionBuilder<*>.() -> Unit): Condition =
      DisjunctionCondition.Builder().also(init).build()
  }
}
