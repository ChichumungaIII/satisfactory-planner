package app.routes.createsave

import app.common.layout.appframe.AppFrame
import app.common.util.AppTitle
import react.FC
import react.Props
import react.create

external interface CreateSaveRouteProps : Props

val CreateSaveRoute = FC<CreateSaveRouteProps>("CreateSaveRoute") {
  AppFrame {
    title = AppTitle.create { +"Create Save" }
    content = CreateSavePage.create {}
  }
}
