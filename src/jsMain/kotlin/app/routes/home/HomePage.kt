package app.routes.home

import app.api.save.v1.Save
import app.routes.home.newsavecard.NewSaveCard
import app.routes.home.savecard.SaveCard
import app.theme.AppThemeContext
import mui.material.Container
import mui.material.Grid
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.useContext

external interface HomePageProps : Props {
  var saves: List<Save>
}

val HomePage = FC<HomePageProps>("HomePage") { props ->
  val appTheme by useContext(AppThemeContext)!!

  Container {
    sx { paddingTop = appTheme.spacing(3) }

    Grid {
      container = true
      spacing = responsive(4)

      props.saves.forEach { SaveCard { save = it } }
      NewSaveCard {}
    }
  }
}
