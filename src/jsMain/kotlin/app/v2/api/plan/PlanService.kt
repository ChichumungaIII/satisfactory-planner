package app.v2.api.plan

import app.v2.plans.data.model.Plan

interface PlanService {
  suspend fun create(plan: Plan)
  suspend fun get(id: ULong): Plan
  suspend fun update(plan: Plan)
  suspend fun delete(id: ULong)
  suspend fun list(): List<Plan>
}
