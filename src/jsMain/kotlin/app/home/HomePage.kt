package app.home

import app.api.save.v1.Save
import app.common.util.LoadingIndicator
import app.home.common.HomePageCard
import app.home.newsavecard.NewSaveCard
import app.home.savecard.SaveCard
import app.theme.AppThemeContext
import mui.material.Container
import mui.material.Grid
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.useContext
import react.useState

external interface HomePageProps : Props {
  var saves: List<Save>
}

val HomePage = FC<HomePageProps>("HomePage") { props ->
  val appTheme by useContext(AppThemeContext)!!

  val creatingState = useState(false)
  var creating by creatingState

  Container {
    sx { paddingTop = appTheme.spacing(3) }

    Grid {
      container = true
      spacing = responsive(4)

      props.saves.forEach {
        SaveCard { save = it }
      }

      HomePageCard.takeIf { creating }?.invoke {
        disabled = true
        content = LoadingIndicator.create {}
      }

      NewSaveCard { this.creating = creatingState }
    }
  }
}
