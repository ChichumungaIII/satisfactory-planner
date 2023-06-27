package app.v2.common.layout

import mui.material.Box
import react.FC
import react.PropsWithChildren
import web.cssom.ClassName

external interface ZeroStateComponentProps : PropsWithChildren

val ZeroStateComponent = FC<ZeroStateComponentProps>("ZeroStateComponent") { props ->
  Box {
    className = ClassName("zero-state")
    +props.children
  }
}
