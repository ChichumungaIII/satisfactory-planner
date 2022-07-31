package app

import csstype.Padding
import csstype.px
import mui.material.Box
import mui.system.sx
import react.FC
import react.Props

val HeaderSpacer = FC<Props> {
    Box { sx { padding = Padding(33.px, 0.px) } }
}
