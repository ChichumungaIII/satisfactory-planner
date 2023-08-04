package app.data.plan

import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.save.v1.SaveName
import app.data.common.ResourceCache

object PlanCache : ResourceCache<PlanName, Plan>() {
  override fun getName(resource: Plan) = resource.name
}

data class PlanCollection(val save: SaveName, val plans: List<PlanName>)
object PlanCollectionCache : ResourceCache<SaveName, PlanCollection>() {
  override fun getName(resource: PlanCollection) = resource.save
}