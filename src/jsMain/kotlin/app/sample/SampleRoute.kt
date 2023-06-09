package app.sample

import app.themes.ThemeModule
import react.FC
import react.Props

external interface SampleRouteProps : Props

val SampleRoute = FC<SampleRouteProps>("SampleRoute") { props ->
  ThemeModule {
    SamplePage {}
  }
}
