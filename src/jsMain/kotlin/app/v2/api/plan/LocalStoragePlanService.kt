package app.v2.api.plan

import app.serialization.AppJson
import app.v2.data.plan.Plan
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

class LocalStoragePlanService : PlanService {
  private val PLANS_STORAGE = "//satisfactory.chichumunga.com/plans"

  private var ids: List<ULong>
    get() = window.localStorage.getItem(PLANS_STORAGE)
      ?.takeUnless { it.isBlank() }
      ?.split(",")
      ?.map { it.toULong() }
      ?.toList()
      ?: listOf()
    set(value) = window.localStorage.setItem(PLANS_STORAGE, value.joinToString(","))

  override suspend fun create(plan: Plan) = update(plan).also { ids = ids + plan.id }

  override suspend fun get(id: ULong) =
    window.localStorage.getItem(storageName(id))?.let { AppJson.decodeFromString<Plan>(it) }?.also { lag() }
      ?: throw IllegalArgumentException("Plan [plans/$id] was not found.")

  override suspend fun update(plan: Plan) =
    window.localStorage.setItem(storageName(plan.id), AppJson.encodeToString(plan)).also { lag() }

  override suspend fun delete(id: ULong) {
    ids = ids - id
  }

  override suspend fun list() = ids.map { get(it) }.also { lag() }

  private fun storageName(id: ULong) = "$PLANS_STORAGE/$id"
  private suspend fun lag() = delay(Random.nextInt(100, 250).milliseconds)
}
