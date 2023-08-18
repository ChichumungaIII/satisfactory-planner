package app.data.plan

import app.api.common.Resource
import app.api.plan.v1.PlanName
import app.api.save.v1.SaveName

data class PlanCollection(
  override val name: SaveName,
  val plans: List<PlanName>,
) : Resource<SaveName> {
  fun add(plan: PlanName) = copy(plans = plans + plan)

  fun remove(plan: PlanName) = copy(plans = plans - plan)
}
