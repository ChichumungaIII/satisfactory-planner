package app.v2.plans.data.model

import app.serialization.AppJson
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import react.FC
import react.PropsWithChildren
import react.createContext
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

val PlanServiceContext = createContext<PlanService>()
val PlanServiceContextProvider = FC<PropsWithChildren>("PlanServiceContextProvider") {
  PlanServiceContext(LocalStoragePlanService()) { +it.children }
}

interface PlanService {
  suspend fun create(plan: Plan)
  suspend fun get(id: ULong): Plan
  suspend fun update(plan: Plan)
  suspend fun delete(id: ULong)
  suspend fun list(): List<Plan>
}

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
  private suspend fun lag() = delay(Random.nextInt(500, 2000).milliseconds)
}
