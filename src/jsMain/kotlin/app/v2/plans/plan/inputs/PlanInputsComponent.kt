package app.v2.plans.plan.inputs

import app.common.RationalInput
import app.util.PropsDelegate
import app.v2.common.input.ItemAutocomplete
import app.v2.common.input.TooltipIconButton
import app.v2.plans.data.model.PlanInput
import csstype.ClassName
import csstype.px
import mui.icons.material.Add
import mui.icons.material.Clear
import mui.material.Fab
import mui.material.FabColor
import mui.material.Size
import mui.material.Stack
import mui.material.StackDirection
import mui.material.SvgIconSize
import mui.material.Tooltip
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode

external interface PlanInputsComponentProps : Props {
    var inputs: List<PlanInput>
    var setInputs: (List<PlanInput>) -> Unit
}

val PlanInputsComponent = FC<PlanInputsComponentProps>("PlanInputsComponent") { props ->
    var inputs by PropsDelegate(props.inputs, props.setInputs)

    Stack {
        className = ClassName("plan-inputs")
        direction = responsive(StackDirection.column)
        spacing = responsive(10.px)

        inputs.withIndex().forEach { (index, input) ->
            fun before() = inputs.subList(0, index)
            fun after() = inputs.subList(index + 1, inputs.size)
            fun setInput(input: PlanInput?) {
                inputs = before() + (input?.let { listOf(it) } ?: listOf()) + after()
            }

            Stack {
                className = ClassName("plan-inputs__input")
                direction = responsive(StackDirection.row)
                spacing = responsive(4.px)

                TooltipIconButton {
                    title = "Delete"
                    icon = Clear
                    onClick = { setInput(null) }
                }

                ItemAutocomplete {
                    model = input.item
                    setModel = { item -> setInput(input.copy(item = item)) }
                }

                RationalInput {
                    label = ReactNode("Amount Available")

                    value = input.amount
                    setValue = { next -> next?.also { setInput(input.copy(amount = it)) } }

                    validators = listOf()

                    size = Size.small
                    width = { null }
                    errorSpacer = 0.px
                }
            }
        }

        Tooltip {
            className = ClassName("plan-inputs__add-input")
            title = ReactNode("Add Input")

            Fab {
                color = FabColor.primary
                size = Size.small
                Add { fontSize = SvgIconSize.medium }

                onClick = { inputs = inputs + PlanInput() }
            }
        }
    }
}
