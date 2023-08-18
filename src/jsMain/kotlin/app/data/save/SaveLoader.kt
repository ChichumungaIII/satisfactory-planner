package app.data.save

import app.api.save.v1.GetSaveRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.SaveService
import app.api.save.v1.SaveServiceJs
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

class SaveLoader(
  private val saveService: SaveService,
  private val cache: ResourceCache<SaveName, Save>,
  private val saveData: RemoteData<SaveName, Save>,
  private val setSaveData: StateSetter<RemoteData<SaveName, Save>>,
) {
  companion object {
    val Context = createContext<SaveLoader>()
    val Provider = FC<PropsWithChildren> {
      val saveService = useContext(SaveServiceJs.Context)!!
      val cache = useContext(SaveCache)!!
      val (saveData, setSaveData) = useState(RemoteData.empty<SaveName, Save>())

      Context(SaveLoader(saveService, cache, saveData, setSaveData)) {
        +it.children
      }
    }
  }

  fun load(name: SaveName) {
    if (saveData.name == name) return

    cache[name]?.also {
      setSaveData(RemoteData.loaded(name, it))
      return@load
    }

    launchMain {
      delay(1000.milliseconds)
      val save = saveService.getSave(GetSaveRequest(name = name))
      cache.insert(save)
      setSaveData(RemoteData.loaded(name, save))
    }
    setSaveData(RemoteData.loading(name))
  }

  operator fun component1() = saveData
  operator fun component2() = this
}