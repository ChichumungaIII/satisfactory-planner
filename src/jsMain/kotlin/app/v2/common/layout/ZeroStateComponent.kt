package app.v2.common.layout

import csstype.ClassName
import mui.material.Box
import react.FC
import react.PropsWithChildren

external interface ZeroStateComponentProps : PropsWithChildren

val ZeroStateComponent = FC<ZeroStateComponentProps>("ZeroStateComponent") { props ->
  Box {
    className = ClassName("zero-state")
    +props.children
  }
}
