package app.data.save

import app.api.save.v1.ListSavesRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.getSaveService
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

class SaveCollectionLoader(
  private val cache: ResourceCache<SaveName, Save>,
  private val names: RemoteData<Unit, List<SaveName>>,
  private val setNames: StateSetter<RemoteData<Unit, List<SaveName>>>,
) {
  companion object {
    val Context = createContext<SaveCollectionLoader>()
    val Provider = FC<PropsWithChildren> {
      val cache = useContext(SaveCache)!!
      val (names, setNames) = useState(RemoteData.empty<Unit, List<SaveName>>())

      Context(SaveCollectionLoader(cache, names, setNames)) {
        +it.children
      }
    }
  }

  fun load() {
    launchMain {
      delay(1500.milliseconds)
      val saves = getSaveService().listSaves(ListSavesRequest()).saves
      cache.insertAll(saves)
      setNames(RemoteData.loaded(Unit, saves.map { it.name }))
    }
    setNames(RemoteData.loading(Unit))
  }

  fun <T> ifLoaded(action: (SaveCollectionLoader) -> T): T? =
    takeIf { names is RemoteData.Loaded }?.let(action)

  fun add(save: Save) {
    check(names is RemoteData.Loaded) { "Cannot add Save prior to initial load." }

    cache.insert(save)
    setNames(RemoteData.loaded(Unit, names.value + save.name))
  }

  fun remove(save: Save) {
    check(names is RemoteData.Loaded) { "Cannot remove Save prior to initial load." }

    setNames(RemoteData.loaded(Unit, names.value - save.name))
  }

  operator fun component1(): RemoteData<Unit, List<Save>> = when (names) {
    is RemoteData.Empty -> RemoteData.empty()
    is RemoteData.Loading -> RemoteData.loading(Unit)
    is RemoteData.Loaded -> RemoteData.loaded(Unit, names.value.map { cache[it]!! })
    is RemoteData.Error -> RemoteData.error(Unit, names.message)
  }

  operator fun component2() = this
}
