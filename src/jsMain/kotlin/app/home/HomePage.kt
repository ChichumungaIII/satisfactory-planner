package app.home

import app.api.save.v1.Save
import mui.material.Container
import mui.material.Grid
import react.FC
import react.Props

external interface HomePageProps : Props {
  var saves: List<Save>
}

val HomePage = FC<HomePageProps>("HomePage") { props ->
  Container {
    Grid {
      container = true

      Grid {
        item = true
        HomePageCard {
          NewSaveCardContent {}
        }
      }
    }
  }
}
