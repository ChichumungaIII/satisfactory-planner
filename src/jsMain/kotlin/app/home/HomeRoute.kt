package app.home

import app.api.save.v1.ListSavesRequest
import app.api.save.v1.SaveName
import app.api.save.v1.SaveServiceJs
import app.common.layout.AppFrame
import app.common.layout.LoadingIndicator
import app.common.layout.LoadingIndicatorVariant
import app.data.common.RemoteData
import app.data.common.ResourceCache.InsertAll
import app.data.save.SaveCache
import app.util.launchMain
import kotlinx.coroutines.delay
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.useContext
import react.useState
import kotlin.time.Duration.Companion.milliseconds

external interface HomeRouteProps : Props

val HomeRoute = FC<HomeRouteProps>("HomeRoute") {
  val saveService = useContext(SaveServiceJs.Context)!!
  val (saveCache, updateSaveCache) = useContext(SaveCache.Context)!!

  var saves by useState<RemoteData<Unit, List<SaveName>>>(RemoteData.empty())

  AppFrame {
    title = Typography.create {
      variant = TypographyVariant.h1
      +"Satisfactory Planner"
    }

    content = when (saves) {
      is RemoteData.Empty -> {
        launchMain {
          delay(2500.milliseconds)

          val response = saveService.listSaves(ListSavesRequest())
          updateSaveCache(InsertAll(response.saves))
          saves = RemoteData.loaded(Unit, response.saves.map { it.name })
        }

        saves = RemoteData.loading(Unit)
        ReactNode("")
      }

      is RemoteData.Loading -> {
        LoadingIndicator.create { variant = LoadingIndicatorVariant.Large }
      }

      is RemoteData.Loaded -> {
        ReactNode("Complete")
      }

      is RemoteData.Error -> TODO()
    }
  }
}
