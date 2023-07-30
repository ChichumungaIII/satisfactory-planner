package app.data.plan

import app.api.plan.v1.ListPlansRequest
import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.plan.v1.PlanService
import app.api.plan.v1.PlanServiceJs
import app.api.save.v1.SaveName
import app.data.common.RemoteData
import app.data.common.ResourceCache
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

class PlanCollectionLoader(
  private val planService: PlanService,
  private val collectionCache: ResourceCache<SaveName, PlanCollection>,
  private val updateCollectionCache: (action: ResourceCacheAction<PlanCollection>) -> Unit,
  private val cache: ResourceCache<PlanName, Plan>,
  private val updateCache: (action: ResourceCacheAction<Plan>) -> Unit,
  private val names: RemoteData<SaveName, PlanCollection>,
  private val setNames: StateSetter<RemoteData<SaveName, PlanCollection>>,
) {
  companion object {
    val Context = createContext<PlanCollectionLoader>()
    val Provider = FC<PropsWithChildren>("PlanCollectionLoader") {
      val planService = useContext(PlanServiceJs.Context)!!
      val (collectionCache, updateCollectionCache) = useContext(PlanCollectionCache.Context)!!
      val (cache, updateCache) = useContext(PlanCache.Context)!!
      val (names, setNames) = useState(RemoteData.empty<SaveName, PlanCollection>())

      Context(
        PlanCollectionLoader(
          planService,
          collectionCache,
          updateCollectionCache,
          cache,
          updateCache,
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
      updateCache(ResourceCache.InsertAll(plans))

      val collection = PlanCollection(name, plans.map { it.name })
      updateCollectionCache(ResourceCache.Insert(collection))
      setNames(RemoteData.loaded(name, collection))
    }
    setNames(RemoteData.loading(name))
  }

  operator fun component1(): RemoteData<SaveName, List<Plan>> = when (names) {
    is RemoteData.Empty -> RemoteData.empty()
    is RemoteData.Loading -> RemoteData.loading(names.name)
    is RemoteData.Loaded -> RemoteData.loaded(names.name, names.data.plans.map { cache[it]!! })
    is RemoteData.Error -> RemoteData.error(names.name, names.message)
  }

  operator fun component2() = this
}
