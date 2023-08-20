package app.data.plan

import app.api.plan.v1.ListPlansRequest
import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.plan.v1.PlanService
import app.api.plan.v1.PlanServiceJs
import app.api.save.v1.SaveName
import app.data.common.RemoteData
import app.data.common.ResourceCache
import app.util.launchMain
import kotlinx.coroutines.delay
import react.FC
import react.PropsWithChildren
import react.StateSetter
import react.createContext
import react.useContext
import react.useState
import kotlin.time.Duration.Companion.milliseconds

class PlanCollectionLoader(
  private val planService: PlanService,
  private val collectionCache: ResourceCache<SaveName, PlanCollection>,
  private val cache: ResourceCache<PlanName, Plan>,
  private val names: RemoteData<SaveName, PlanCollection>,
  private val setNames: StateSetter<RemoteData<SaveName, PlanCollection>>,
) {
  companion object {
    val Context = createContext<PlanCollectionLoader>()
    val Provider = FC<PropsWithChildren>("PlanCollectionLoader") {
      val planService = useContext(PlanServiceJs.Context)!!
      val collectionCache = useContext(PlanCollectionCache)!!
      val cache = useContext(PlanCache)!!
      val (names, setNames) = useState(RemoteData.empty<SaveName, PlanCollection>())

      Context(
        PlanCollectionLoader(
          planService,
          collectionCache,
          cache,
          names,
          setNames
        )
      ) { +it.children }
    }
  }

  fun load(name: SaveName) {
    if (names.name == name) return

    collectionCache[name]?.also {
      setNames(RemoteData.loaded(name, it))
      return@load
    }

    launchMain {
      delay(1250.milliseconds)
      val plans = planService.listPlans(ListPlansRequest(parent = name)).plans
      cache.insertAll(plans)

      val collection = PlanCollection(name, plans.map { it.name })
      collectionCache.insert(collection)
      setNames(RemoteData.loaded(name, collection))
    }
    setNames(RemoteData.loading(name))
  }

  fun add(plan: Plan) {
    check(names is RemoteData.Loaded) { "Cannot add Plan prior to initial load." }
    check(plan.parent == names.name) { "Plan [${plan.name.getResourceName()}] cannot be added to save [${names.name.getResourceName()}]." }

    cache.insert(plan)
    val collection = names.value.add(plan.name)
    collectionCache.insert(collection)
    setNames(RemoteData.loaded(names.name, collection))
  }

  fun remove(plan: Plan) {
    check(names is RemoteData.Loaded) { "Cannot remove Plan prior to initial load." }

    val collection = names.value.remove(plan.name)
    collectionCache.insert(collection)
    setNames(RemoteData.loaded(names.name, collection))
  }

  operator fun component1() = names.map { c -> c.plans.map { cache[it]!! } }
  operator fun component2() = this
}
