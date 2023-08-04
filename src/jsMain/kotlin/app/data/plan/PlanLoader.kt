package app.data.plan

import app.api.plan.v1.GetPlanRequest
import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.plan.v1.PlanService
import app.api.plan.v1.PlanServiceJs
import app.data.common.RemoteData
import app.data.common.ResourceCache
import app.data.common.ResourceCache.Insert
import app.data.common.ResourceCache.ResourceCacheAction
import app.util.launchMain
import kotlinx.coroutines.delay
import react.FC
import react.PropsWithChildren
import react.StateSetter
import react.createContext
import react.useContext
import react.useState
import kotlin.time.Duration.Companion.milliseconds

class PlanLoader private constructor(
  private val planService: PlanService,
  private val cache: ResourceCache<PlanName, Plan>,
  private val updateCache: (action: ResourceCacheAction<Plan>) -> Unit,
  private val planData: RemoteData<PlanName, Plan>,
  private val setPlanData: StateSetter<RemoteData<PlanName, Plan>>,
) {
  companion object {
    val Context = createContext<PlanLoader>()
    val Provider = FC<PropsWithChildren>("PlanLoader") {
      val planService = useContext(PlanServiceJs.Context)!!
      val (cache, updateCache) = useContext(PlanCache.Context)!!
      val (planData, setPlanData) = useState(RemoteData.empty<PlanName, Plan>())

      Context(PlanLoader(planService, cache, updateCache, planData, setPlanData)) {
        +it.children
      }
    }
  }

  fun load(name: PlanName) {
    if (planData.name == name) return

    cache[name]?.also {
      setPlanData(RemoteData.loaded(name, it))
      return@load
    }

    launchMain {
      delay(1500.milliseconds)
      val plan = planService.getPlan(GetPlanRequest(name = name))
      updateCache(Insert(plan))
      setPlanData(RemoteData.loaded(name, plan))
    }
    setPlanData(RemoteData.loading(name))
  }

  operator fun component1() = planData
  operator fun component2() = this
}