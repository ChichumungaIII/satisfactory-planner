package app.plan

import csstype.AlignItems
import csstype.Display
import csstype.Flex
import csstype.Padding
import csstype.number
import csstype.px
import mui.icons.material.Delete
import mui.icons.material.Edit
import mui.material.Box
import mui.material.IconButton
import mui.material.IconButtonColor
import mui.material.SvgIconSize
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props

external interface PlanHeaderProps : Props {
    var title: String
    var setTitle: (String) -> Unit
}

val PlanHeader = FC<PlanHeaderProps>("PlanHeader") { props ->
    Box {
        sx {
            padding = Padding(12.px, 12.px)

            display = Display.flex
            alignItems = AlignItems.flexEnd
        }
        Typography {
            sx { padding = Padding(0.px, 12.px, 0.px, 0.px) }

            variant = TypographyVariant.h2
            +props.title
        }

        IconButton {
            color = IconButtonColor.default
            Edit { fontSize = SvgIconSize.inherit }
        }

        Box { sx { flex = Flex(number(1.0), number(0.0)) } }

        IconButton {
            color = IconButtonColor.warning
            Delete { fontSize = SvgIconSize.inherit }
        }
    }
}
