package app.plan

import app.common.ItemAutocomplete
import app.common.RationalInput
import app.common.RationalValidation
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
import react.ReactNode

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
            item = product.item
            setItem = { next -> product = product.copy(item = next) }
        }

        RationalInput {
            label = ReactNode("Minimum required")

            value = product.requirement
            setValue = { next -> next?.let { product = product.copy(requirement = it) } }

            validators = listOf({ value ->
                val maximum = product.maximum
                if (maximum == null || value <= maximum) RationalValidation.pass()
                else RationalValidation.fail("Maximum yield is ${maximum.toDecimal(4)} (${maximum})")
            }, { value ->
                val limit = product.limit
                if (limit == null || value <= limit) RationalValidation.pass()
                else RationalValidation.fail("Requirement cannot exceed limit.")
            })
        }

        RationalInput {
            label = ReactNode("Maximum desired")

            value = product.limit
            setValue = { next -> next?.let { product = product.copy(limit = it) } }

            validators = listOf({ value ->
                val maximum = product.maximum
                if (maximum == null || value <= maximum) RationalValidation.pass()
                else RationalValidation.fail("Maximum yield is ${maximum.toDecimal(4)} (${maximum})")
            }, { value ->
                if (value >= product.requirement) RationalValidation.pass()
                else RationalValidation.fail("Limit must exceed requirement.")
            })
        }

        val target = product.target
        if (target != null) {
            Box {
                +"Produced: ${target.toDecimal(4)} | ($target)"
            }
        }

        val maximum = product.maximum
        if (maximum != null) {
            Box {
                +"Maximum: ${maximum.toDecimal(4)} ($maximum)"
            }
        }
    }
}
