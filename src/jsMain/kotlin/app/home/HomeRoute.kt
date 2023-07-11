package app.home

import app.api.save.v1.ListSavesRequest
import app.api.save.v1.SaveName
import app.api.save.v1.SaveServiceJs
import app.common.layout.AppFrame
import app.common.layout.RouteLoadingIndicator
import app.data.common.RemoteData
import app.data.common.ResourceCache.Insert
import app.data.common.ResourceCache.InsertAll
import app.data.save.SaveCache
import app.util.launchMain
import kotlinx.coroutines.delay
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.create
import react.useContext
import react.useState
import kotlin.time.Duration.Companion.milliseconds

external interface HomeRouteProps : Props

val HomeRoute = FC<HomeRouteProps>("HomeRoute") {
  val saveService = useContext(SaveServiceJs.Context)!!
  val (saveCache, updateSaveCache) = useContext(SaveCache.Context)!!

  val (savesData, setSavesData) = useState<RemoteData<Unit, List<SaveName>>>(RemoteData.empty())

  AppFrame {
    title = Typography.create {
      variant = TypographyVariant.h1
      +"Satisfactory Planner"
    }

    when (savesData) {
      is RemoteData.Empty -> {
        launchMain {
          delay(1000.milliseconds)

          val saves = saveService.listSaves(ListSavesRequest()).saves
          updateSaveCache(InsertAll(saves))
          setSavesData(RemoteData.loaded(Unit, saves.map { it.name }))
        }

        setSavesData(RemoteData.loading(Unit))
      }

      is RemoteData.Loading -> {
        content = RouteLoadingIndicator.create {}
      }

      is RemoteData.Loaded -> {
        content = HomePage.create {
          saves = savesData.data.mapNotNull { saveCache[it] }
          addSave = { save ->
            updateSaveCache(Insert(save))
            setSavesData(RemoteData.loaded(Unit, savesData.data + save.name))
          }
          removeSave = { save ->
            setSavesData(RemoteData.loaded(Unit, savesData.data - save.name))
          }
        }
      }

      is RemoteData.Error -> TODO()
    }
  }
}
