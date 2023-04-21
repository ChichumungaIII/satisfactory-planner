package app.v2.plans.plan

import app.api.OptimizeRequest
import app.api.client.optimize
import app.data.recipe.ProductionRecipe
import app.util.PropsDelegate
import app.v2.AppScope
import app.v2.common.input.TooltipIconButton
import app.v2.plans.data.model.Plan
import app.v2.plans.data.model.PlanInput
import app.v2.plans.data.model.PlanProduct
import app.v2.plans.data.model.PlanResult
import app.v2.plans.plan.inputs.PlanInputsComponent
import app.v2.plans.plan.products.PlanProductsComponent
import app.v2.plans.plan.results.PlanResultsComponent
import kotlinx.coroutines.launch
import mui.icons.material.ArrowRight
import mui.material.Divider
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.Props
import react.createContext

external interface PlanComponentProps : Props {
  var plan: Plan
  var setPlan: (Plan) -> Unit
}

val PlanComponentContext = createContext<PropsDelegate<Plan>>()

val PlanComponent = FC<PlanComponentProps>("PlanComponent") { props ->
  val delegate = PropsDelegate(props.plan) { next ->
    props.setPlan(next)

    AppScope.launch {
      val request = OptimizeRequest(
        recipes = ProductionRecipe.values().toSet(),
        inputs = next.inputs.mapNotNull(PlanInput::toInput),
        outcomes = next.products.mapNotNull(PlanProduct::toOutcome),
      )

      val response = optimize(request)
      val results = ProductionRecipe.values()
        .associateWith { response.outcome[it] }
        .mapNotNull { (recipe, rate) -> rate?.let { recipe to it } }
        .map { (recipe, rate) ->
          PlanResult(recipe, rate, details = next.getResult(recipe)?.details ?: false)
        }
      props.setPlan(
        next.copy(
          results = results,
          minimums = response.minimums,
          maximums = response.maximums,
        )
      )
    }
  }

  PlanComponentContext(delegate) {
    Stack {
      direction = responsive(StackDirection.row)

      PlanInputsComponent {}

      TooltipIconButton {
        title = "Select recipes"
        icon = ArrowRight
        onClick = {}
      }

      PlanProductsComponent {}
    }

    Divider {}

    PlanResultsComponent {}
  }
}
