package app.data.save

import app.api.save.v1.ListSavesRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.getSaveService
import app.data.common.RemoteData
import app.redux.state.AppState
import app.redux.state.resource.InsertSave
import app.redux.state.resource.InsertSaves
import app.redux.state.resource.ResourceCache
import app.redux.useAppDispatch
import app.redux.useAppSelector
import app.util.launchMain
import kotlinx.coroutines.delay
import react.FC
import react.PropsWithChildren
import react.StateSetter
import react.createContext
import react.useState
import redux.RAction
import redux.WrapperAction
import kotlin.time.Duration.Companion.milliseconds

class SaveCollectionLoader(
  private val cache: ResourceCache<SaveName, Save>,
  private val dispatch: (RAction) -> WrapperAction,
  private val names: RemoteData<Unit, List<SaveName>>,
  private val setNames: StateSetter<RemoteData<Unit, List<SaveName>>>,
) {
  companion object {
    val Context = createContext<SaveCollectionLoader>()
    val Provider = FC<PropsWithChildren> {
      val cache = useAppSelector(AppState::saveCache)
      val dispatch = useAppDispatch()
      val (names, setNames) = useState(RemoteData.empty<Unit, List<SaveName>>())

      Context(SaveCollectionLoader(cache, dispatch, names, setNames)) {
        +it.children
      }
    }
  }

  fun load() {
    launchMain {
      delay(1500.milliseconds)
      val saves = getSaveService().listSaves(ListSavesRequest()).saves
      dispatch(InsertSaves(saves))
      setNames(RemoteData.loaded(Unit, saves.map { it.name }))
    }
    setNames(RemoteData.loading(Unit))
  }

  fun <T> ifLoaded(action: (SaveCollectionLoader) -> T): T? =
    takeIf { names is RemoteData.Loaded }?.let(action)

  fun add(save: Save) {
    check(names is RemoteData.Loaded) { "Cannot add Save prior to initial load." }

    dispatch(InsertSave(save))
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
