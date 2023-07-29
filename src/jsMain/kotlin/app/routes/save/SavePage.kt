package app.routes.save

import app.api.save.v1.Save
import mui.material.Box
import react.FC
import react.Props

external interface SavePageProps : Props {
  var save: Save
}

val SavePage = FC<SavePageProps>("SavePage") { props ->
  Box {
    +props.save.displayName
  }
}
