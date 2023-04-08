package app.v2.plans.plan

import app.util.PropsDelegate
import app.v2.plans.data.model.Plan
import csstype.ClassName
import mui.icons.material.Circle
import mui.material.Box
import mui.material.ButtonProps
import mui.material.Orientation
import mui.material.Step
import mui.material.StepButton
import mui.material.StepContent
import mui.material.Stepper
import mui.material.SvgIconColor
import react.FC
import react.Props
import react.ReactNode
import react.create

external interface PlanComponentProps : Props {
    var plan: Plan
    var setPlan: (Plan) -> Unit
}

val PlanComponent = FC<PlanComponentProps>("PlanComponent") { props ->
    var plan by PropsDelegate(props.plan, props.setPlan)

    fun setActiveStep(i: Int) {
        plan = plan.copy(activeStep = i)
    }

    Stepper {
        className = ClassName("plan")
        orientation = Orientation.vertical
        nonLinear = true
        activeStep = plan.activeStep

        listOf(
            PlanStepData(
                title = "Inputs",
                summary = ReactNode("Inputs (summary)"),
                content = ReactNode("Inputs (content)"),
            ), PlanStepData(
                title = "Products",
                summary = ReactNode("Products (summary)"),
                content = ReactNode("Products (content)"),
            ), PlanStepData(
                title = "Recipes",
                summary = ReactNode("Recipes (summary)"),
                content = ReactNode("Recipes (content)"),
            ), PlanStepData(
                title = "Results",
                summary = ReactNode("Results (summary)"),
                content = ReactNode("Results (content)"),
            )
        ).withIndex().forEach { (index, step) ->
            Step {
                className = ClassName("plan__step")

                StepButton {
                    unsafeCast<ButtonProps>().onClick = { setActiveStep(index) }
                    icon = Circle.create {
                        color = SvgIconColor.primary
                            ?.takeIf { plan.activeStep == index }
                            ?: SvgIconColor.disabled
                    }
                    +step.title
                }

                step.summary?.takeUnless { plan.activeStep == index }?.let { summary ->
                    Box {
                        className = ClassName("plan__step__summary")
                        +summary
                    }
                }

                StepContent { +step.content }
            }
        }
    }
}

data class PlanStepData(
    var title: String,
    var summary: ReactNode?,
    var content: ReactNode,
)
