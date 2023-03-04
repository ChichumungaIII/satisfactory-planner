package app.v2.common.layout

import csstype.ClassName
import csstype.px
import mui.material.Box
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.PropsWithChildren

external interface ControlBarProps : PropsWithChildren

val ControlBar = FC<ControlBarProps>("ControlBar") { props ->
    Stack {
        className = ClassName("control-bar")
        direction = responsive(StackDirection.row)
        spacing = responsive(6.px)

        +props.children
    }
}

external interface ControlBarItemProps : PropsWithChildren

val ControlBarItem = FC<ControlBarItemProps>("ControlBarItem") { props ->
    Box {
        className = ClassName("control-bar__item")

        +props.children
    }
}
