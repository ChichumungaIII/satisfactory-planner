package app.v2.plans.plan.products

import app.data.Item
import app.util.PropsDelegate
import app.v2.common.input.ItemAutocomplete
import app.v2.common.input.ToggleIconButton
import app.v2.common.input.TooltipIconButton
import app.v2.plans.plan.common.PlanItemAmountInput
import csstype.ClassName
import csstype.px
import mui.icons.material.Clear
import mui.icons.material.GpsFixed
import mui.icons.material.GpsNotFixed
import mui.icons.material.LastPage
import mui.icons.material.Start
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import util.math.Rational

external interface PlanProductComponentProps : Props {
    var item: Item?
    var setItem: (Item?) -> Unit

    var exact: Boolean
    var setExact: (Boolean) -> Unit

    var amount: Rational
    var setAmount: (Rational) -> Unit

    var maximum: Rational?
    var setMaximum: (Rational?) -> Unit

    var onDelete: () -> Unit
}

val PlanProductComponent = FC<PlanProductComponentProps>("PlanProductComponent") { props ->
    var item by PropsDelegate(props.item, props.setItem)
    var exact by PropsDelegate(props.exact, props.setExact)
    var amount by PropsDelegate(props.amount, props.setAmount)
    var maximum by PropsDelegate(props.maximum, props.setMaximum)

    Stack {
        className = ClassName("plan-products__product")
        direction = responsive(StackDirection.row)
        spacing = responsive(4.px)

        TooltipIconButton {
            title = "Delete"
            icon = Clear
            onClick = { props.onDelete() }
        }

        ItemAutocomplete {
            model = item
            setModel = { next -> item = next }
        }

        ToggleIconButton {
            toggle = exact
            setToggle = { next ->
                exact = next
                if (!exact) {
                    maximum = null
                }
            }

            titleOn = "Exactly"
            iconOn = GpsFixed

            titleOff = "At least"
            iconOff = GpsNotFixed
        }

        PlanItemAmountInput {
            label = ReactNode(if (exact) "Amount produced" else "Minimum produced")
            model = amount
            setModel = { next -> amount = next }
        }

        if (!exact) {
            ToggleIconButton {
                toggle = maximum != null
                setToggle = { next -> maximum = amount.takeIf { next } }

                titleOn = "Up to"
                iconOn = LastPage

                titleOff = "Unlimited"
                iconOff = Start
            }

            maximum?.also {
                PlanItemAmountInput {
                    label = ReactNode("Maximum produced")
                    model = it
                    setModel = { next -> maximum = next }
                }
            }
        }
    }
}
