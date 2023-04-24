package app.v2.plans.plan

import app.themes.ThemeContext
import app.util.PropsDelegate
import app.v2.common.input.TooltipIconButton
import app.v2.plans.data.model.Plan
import app.v2.plans.plan.inputs.PlanInputsComponent
import app.v2.plans.plan.products.PlanProductsComponent
import app.v2.plans.plan.results.PlanResultsComponent
import csstype.ClassName
import csstype.Margin
import csstype.px
import mui.icons.material.ArrowForward
import mui.material.Box
import mui.material.Divider
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.createContext
import react.useContext

external interface PlanComponentProps : Props {
  var plan: Plan
  var setPlan: (Plan) -> Unit
}

val PlanComponentContext = createContext<PropsDelegate<Plan>>()

val PlanComponent = FC<PlanComponentProps>("PlanComponent") { props ->
  val (_, computeOutcome) = useContext(ComputeOutcomeContext)
  val delegate = PropsDelegate(props.plan) { next ->
    props.setPlan(next)
    computeOutcome(ForPlan(next))
  }
  val plan by delegate

  PlanComponentContext(delegate) {
    Box {
      className = ClassName("plan")

      Stack {
        className = ClassName("plan__section")
        direction = responsive(StackDirection.row)
        spacing = responsive(4.px)

        PlanInputsComponent {}

        TooltipIconButton {
          className = ClassName("plan__select-recipes")
          title = "Select recipes"
          icon = ArrowForward
          onClick = {}
        }

        PlanProductsComponent {}
      }

      plan.results?.also { results ->
        Divider { sx { margin = Margin(2.px, 0.px) } }

        PlanResultsComponent {
          className = ClassName("plan__section")
          this.results = results
        }
      }
    }
  }
}
