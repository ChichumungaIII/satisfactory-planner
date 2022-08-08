package app.plan

import app.common.ItemAutocomplete
import app.model.PlanProductModel
import app.util.PropsDelegate
import csstype.Display
import csstype.Margin
import csstype.px
import mui.icons.material.Close
import mui.material.Box
import mui.material.IconButton
import mui.material.IconButtonColor
import mui.material.Size
import mui.material.SvgIconSize
import mui.system.sx
import react.FC
import react.Props

external interface PlanProductProps : Props {
    var isFirst: Boolean

    var product: PlanProductModel
    var setProduct: (PlanProductModel) -> Unit
    var onDelete: () -> Unit
}

val PlanProduct = FC<PlanProductProps>("PlanProduct") { props ->
    var product by PropsDelegate(props.product) { next -> props.setProduct(next) }

    Box {
        sx {
            display = Display.flex
            margin = if (props.isFirst) Margin(0.px, 0.px, 12.px) else Margin(12.px, 0.px)
        }

        IconButton {
            sx { height = 56.px }

            color = IconButtonColor.default
            size = Size.large
            Close { fontSize = SvgIconSize.inherit }

            onClick = { props.onDelete() }
        }

        ItemAutocomplete {
            item = product.item()
            setItem = { next -> product = product.setItem(next) }
        }
    }
}
